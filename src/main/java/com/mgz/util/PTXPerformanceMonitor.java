/*
Copyright 2026 Rudolf Fiala

This file is part of Alpheus AFP Parser.

Alpheus AFP Parser is free software: you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation, either version 3 of the License, or
(at your option) any later version.

Alpheus AFP Parser is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with Alpheus AFP Parser.  If not, see <http://www.gnu.org/licenses/>
*/

package com.mgz.util;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

/**
 * Utility to track detailed PTX and PTOCA performance statistics.
 */
public class PTXPerformanceMonitor {

  private static volatile boolean enabled = false;

  private static final LongAdder totalPtxCount = new LongAdder();
  private static final LongAdder totalPtxParseTime = new LongAdder();
  private static final LongAdder totalPtxWriteTime = new LongAdder();
  private static final LongAdder totalPtxPayloadSize = new LongAdder();
  private static final LongAdder totalPtxControlSequences = new LongAdder();

  private static final Map<String, LongAdder> ptocaFunctionCounts = new ConcurrentHashMap<>();
  private static final Map<String, LongAdder> ptocaFunctionParseTimes = new ConcurrentHashMap<>();
  private static final Map<String, LongAdder> ptocaFunctionWriteTimes = new ConcurrentHashMap<>();
  private static final Map<String, LongAdder> ptocaFunctionPayloadSizes = new ConcurrentHashMap<>();

  private static final ThreadLocal<LocalPtxStats> localStats = ThreadLocal.withInitial(LocalPtxStats::new);

  public static void setEnabled(boolean enabled) {
    PTXPerformanceMonitor.enabled = enabled;
  }

  public static boolean isEnabled() {
    return enabled;
  }

  public static void recordPtxParse(long durationNs, int payloadSize, int csCount) {
    if (!enabled) return;
    LocalPtxStats local = localStats.get();
    local.totalPtxCount++;
    local.totalPtxParseTime += durationNs;
    local.totalPtxPayloadSize += payloadSize;
    local.totalPtxControlSequences += csCount;
  }

  public static void recordPtxWrite(long durationNs) {
    if (!enabled) return;
    localStats.get().totalPtxWriteTime += durationNs;
  }

  public static void recordPtocaParse(String functionName, long durationNs, int payloadSize) {
    if (!enabled) return;
    LocalPtocaStats stats = localStats.get().ptocaStats.computeIfAbsent(functionName, k -> new LocalPtocaStats());
    stats.count++;
    stats.parseTime += durationNs;
    stats.payloadSize += payloadSize;
  }

  public static void recordPtocaWrite(String functionName, long durationNs) {
    if (!enabled) return;
    LocalPtocaStats stats = localStats.get().ptocaStats.computeIfAbsent(functionName, k -> new LocalPtocaStats());
    stats.writeTime += durationNs;
  }

  /**
   * Merges local thread statistics into the global counters and clears local state.
   */
  public static void merge() {
    LocalPtxStats local = localStats.get();
    if (local.totalPtxCount == 0 && local.totalPtxWriteTime == 0 && local.ptocaStats.isEmpty()) return;

    totalPtxCount.add(local.totalPtxCount);
    totalPtxParseTime.add(local.totalPtxParseTime);
    totalPtxWriteTime.add(local.totalPtxWriteTime);
    totalPtxPayloadSize.add(local.totalPtxPayloadSize);
    totalPtxControlSequences.add(local.totalPtxControlSequences);

    local.ptocaStats.forEach((name, stats) -> {
      ptocaFunctionCounts.computeIfAbsent(name, k -> new LongAdder()).add(stats.count);
      ptocaFunctionParseTimes.computeIfAbsent(name, k -> new LongAdder()).add(stats.parseTime);
      ptocaFunctionWriteTimes.computeIfAbsent(name, k -> new LongAdder()).add(stats.writeTime);
      ptocaFunctionPayloadSizes.computeIfAbsent(name, k -> new LongAdder()).add(stats.payloadSize);
    });

    local.reset();
  }

  public static void printSummary() {
    merge();

    if (totalPtxCount.sum() == 0) {
      System.out.println("No PTX statistics collected.");
      return;
    }

    long count = totalPtxCount.sum();
    System.out.println("\n### PTX Debug Performance Summary");
    System.out.println();
    System.out.println("| Metric | Value |");
    System.out.println("| :--- | ---: |");
    System.out.println(String.format("| Total PTX Fields | %d |", count));
    System.out.println(String.format("| Total Parse Time | %d ms |", totalPtxParseTime.sum() / 1_000_000));
    System.out.println(String.format("| Total Write Time | %d ms |", totalPtxWriteTime.sum() / 1_000_000));
    System.out.println(String.format("| Total Payload Size | %d bytes |", totalPtxPayloadSize.sum()));
    System.out.println(String.format("| Total Ctrl Sequences | %d |", totalPtxControlSequences.sum()));
    System.out.println(String.format("| Avg CS per PTX | %.2f |", (double) totalPtxControlSequences.sum() / count));
    System.out.println(String.format("| Avg Payload per PTX | %.2f bytes |", (double) totalPtxPayloadSize.sum() / count));

    if (!ptocaFunctionCounts.isEmpty()) {
      System.out.println("\n#### PTOCA Function Breakdown");
      System.out.println();
      System.out.println(String.format("| %-30s | %10s | %12s | %12s | %15s | %15s |", "Function", "Count", "Parse (ms)", "Write (ms)", "Total Payload", "Avg Payload"));
      System.out.println("| :--- | ---: | ---: | ---: | ---: | ---: |");
      new TreeMap<>(ptocaFunctionCounts).forEach((name, fCount) -> {
        long countValue = fCount.sum();
        long pTime = ptocaFunctionParseTimes.getOrDefault(name, new LongAdder()).sum();
        long wTime = ptocaFunctionWriteTimes.getOrDefault(name, new LongAdder()).sum();
        long payload = ptocaFunctionPayloadSizes.getOrDefault(name, new LongAdder()).sum();
        double avgPayload = countValue > 0 ? (double) payload / countValue : 0;
        System.out.println(String.format("| %-30s | %10d | %12d | %12d | %15d | %15.2f |",
            name, countValue, pTime / 1_000_000, wTime / 1_000_000, payload, avgPayload));
      });
    }
    System.out.println();
  }

  private static class LocalPtxStats {
    long totalPtxCount = 0;
    long totalPtxParseTime = 0;
    long totalPtxWriteTime = 0;
    long totalPtxPayloadSize = 0;
    long totalPtxControlSequences = 0;
    final Map<String, LocalPtocaStats> ptocaStats = new HashMap<>();

    void reset() {
      totalPtxCount = 0;
      totalPtxParseTime = 0;
      totalPtxWriteTime = 0;
      totalPtxPayloadSize = 0;
      totalPtxControlSequences = 0;
      ptocaStats.clear();
    }
  }

  private static class LocalPtocaStats {
    long count = 0;
    long parseTime = 0;
    long writeTime = 0;
    long payloadSize = 0;
  }
}
