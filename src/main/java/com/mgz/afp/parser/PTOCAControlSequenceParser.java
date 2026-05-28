/*
Copyright 2015 Rudolf Fiala

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

package com.mgz.afp.parser;

import com.mgz.afp.base.StructuredField;
import com.mgz.afp.exceptions.AFPParserException;
import com.mgz.afp.ptoca.controlSequence.ControlSequencePool;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.ControlSequenceFunctionType;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.ControlSequenceIntroducer;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.GraphicCharacters;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.SEA_SetEncryptedAlternate;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.SKI_SetKeyInformation;
import com.mgz.afp.ptoca.controlSequence.PTOCAControlSequence.UCT_UnicodeComplexText;
import com.mgz.util.MnemonicPerformanceMonitor;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.function.Supplier;

public class PTOCAControlSequenceParser {

  private static final EnumMap<ControlSequenceFunctionType, Supplier<PTOCAControlSequence>>
      CS_SUPPLIERS = new EnumMap<>(ControlSequenceFunctionType.class);

  static {
    CS_SUPPLIERS.put(ControlSequenceFunctionType.SIM_SetInlineMargin, PTOCAControlSequence.SIM_SetInlineMargin::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.SIA_SetIntercharacterAdjustment, PTOCAControlSequence.SIA_SetIntercharacterAdjustment::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.SVI_SetVariableSpaceCharacterIncrement, PTOCAControlSequence.SVI_SetVariableSpaceCharacterIncrement::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.AMI_AbsoluteMoveInline, PTOCAControlSequence.AMI_AbsoluteMoveInline::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.RMI_RelativeMoveInline, PTOCAControlSequence.RMI_RelativeMoveInline::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.SBI_SetBaselineIncrement, PTOCAControlSequence.SBI_SetBaselineIncrement::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.AMB_AbsoluteMoveBaseline, PTOCAControlSequence.AMB_AbsoluteMoveBaseline::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.RMB_RelativeMoveBaseline, PTOCAControlSequence.RMB_RelativeMoveBaseline::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.BLN_BeginLine, PTOCAControlSequence.BLN_BeginLine::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.STO_SetTextOrientation, PTOCAControlSequence.STO_SetTextOrientation::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.TRN_TransparentData, PTOCAControlSequence.TRN_TransparentData::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.RPS_RepeatString, PTOCAControlSequence.RPS_RepeatString::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.NOP_NoOperation, PTOCAControlSequence.NOP_NoOperation::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.DIR_DrawIaxisRule, PTOCAControlSequence.DIR_DrawIaxisRule::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.DBR_DrawBaxisRule, PTOCAControlSequence.DBR_DrawBaxisRule::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.STC_SetTextColor, PTOCAControlSequence.STC_SetTextColor::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.SEC_SetExtendedTextColor, PTOCAControlSequence.SEC_SetExtendedTextColor::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.SCFL_SetCodedFontLocal, PTOCAControlSequence.SCFL_SetCodedFontLocal::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.BSU_BeginSuppression, PTOCAControlSequence.BSU_BeginSuppression::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.ESU_EndSuppression, PTOCAControlSequence.ESU_EndSuppression::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.UCT_UnicodeComplexText, PTOCAControlSequence.UCT_UnicodeComplexText::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.GLC_GlyphLayoutControl, PTOCAControlSequence.GLC_GlyphLayoutControl::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.ENC_EncryptedData, PTOCAControlSequence.ENC_EncryptedData::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.SKI_SetKeyInformation, PTOCAControlSequence.SKI_SetKeyInformation::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.SEA_SetEncryptedAlternate, PTOCAControlSequence.SEA_SetEncryptedAlternate::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.GIR_GlyphIdRun, PTOCAControlSequence.GIR_GlyphIdRun::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.GAR_GlyphAdvanceRun, PTOCAControlSequence.GAR_GlyphAdvanceRun::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.GOR_GlyphOffsetRun, PTOCAControlSequence.GOR_GlyphOffsetRun::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.OVS_Overstrike, PTOCAControlSequence.OVS_Overstrike::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.USC_Underscore, PTOCAControlSequence.USC_Underscore::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.TBM_TemporaryBaselineMove, PTOCAControlSequence.TBM_TemporaryBaselineMove::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.GraphicCharacters, PTOCAControlSequence.GraphicCharacters::new);
    CS_SUPPLIERS.put(ControlSequenceFunctionType.Undefined, com.mgz.afp.ptoca.controlSequence.Undefined::new);
  }
  public static List<PTOCAControlSequence> parseControlSequences(byte[] sfData, int offset, int length, AFPParserConfiguration config) throws AFPParserException {

    List<PTOCAControlSequence> controlSequences = new ArrayList<PTOCAControlSequence>();

    int actualLength = StructuredField.getActualLength(sfData, offset, length);
    int pos = 0;
    boolean isChained = false;
    PTOCAControlSequence lastCS = null;
    while (pos < actualLength) {
      if (!isChained && (pos + 1 >= actualLength || sfData[offset + pos] != 0x2B || sfData[offset + pos + 1] != (byte) 0xD3)) {
        int runStart = pos;
        while (pos < actualLength) {
          if (pos + 1 < actualLength && sfData[offset + pos] == 0x2B && sfData[offset + pos + 1] == (byte) 0xD3) {
            break;
          }
          pos++;
        }
        GraphicCharacters gc = (GraphicCharacters) ControlSequencePool.acquire(ControlSequenceFunctionType.GraphicCharacters);
        if (gc == null) {
          gc = new GraphicCharacters();
        }
        MnemonicPerformanceMonitor.startParse(gc);
        long startTime = config.isPtxDebug() ? System.nanoTime() : 0;
        int runLen = pos - runStart;
        try {
          gc.decodeAFP(sfData, offset + runStart, runLen, config);
        } finally {
          MnemonicPerformanceMonitor.endParse();
          if (config.isPtxDebug()) {
            com.mgz.util.PTXPerformanceMonitor.recordPtocaParse("GraphicCharacters", System.nanoTime() - startTime, runLen);
          }
        }
        controlSequences.add(gc);
        continue;
      }

      int remaining = actualLength - pos;
      int csiLen = isChained ? 2 : 4;
      if (remaining < csiLen) {
        throw new AFPParserException("Truncated PTOCA control sequence introducer at offset " + pos);
      }

      ControlSequenceIntroducer csi = ControlSequenceIntroducer.parseCSI(isChained, sfData, offset + pos, -1, config);

      PTOCAControlSequence cs = createControlSequenceInstance(csi);
      // Move pos to begin of CS payload.
      if (isChained) {
        pos += 2;
      } else {
        pos += 4;
      }

      if (csi.getLength() < 2) {
        throw new AFPParserException("Invalid PTOCA control sequence length: " + csi.getLength());
      }
      if (pos + csi.getLength() - 2 > actualLength) {
        throw new AFPParserException("Truncated PTOCA control sequence payload at offset " + pos);
      }

      MnemonicPerformanceMonitor.startParse(cs);
      long startTime = config.isPtxDebug() ? System.nanoTime() : 0;
      int payloadLen = csi.getLength() - 2;
      try {
        cs.decodeAFP(sfData, offset + pos, payloadLen, config);
      } finally {
        MnemonicPerformanceMonitor.endParse();
        if (config.isPtxDebug()) {
          com.mgz.util.PTXPerformanceMonitor.recordPtocaParse(cs.getClass().getSimpleName(), System.nanoTime() - startTime, payloadLen);
        }
      }

      if (cs instanceof UCT_UnicodeComplexText uct) {
        int ctLen = uct.getCtLength();
        if (ctLen > 0 && (pos + (csi.getLength() - 2) + ctLen) <= actualLength) {
          var text = new byte[ctLen];
          System.arraycopy(sfData, offset + pos + (csi.getLength() - 2), text, 0, ctLen);
          uct.setComplexText(text);
        }
        pos += ctLen;
        isChained = false; // The UCT control sequence always terminates chaining.
      } else {
        isChained = cs.getCsi().isChained();
      }

      // Concatenation support for SEA and SKI
      if (cs instanceof SEA_SetEncryptedAlternate currentSea && lastCS instanceof SEA_SetEncryptedAlternate lastSea) {
        if (currentSea.getAlternateText() == null) {
          // Reset
          lastSea.setAlternateText(null);
        } else {
          var oldText = lastSea.getAlternateText();
          if (oldText == null) {
            lastSea.setAlternateText(currentSea.getAlternateText());
          } else {
            var newText = new byte[oldText.length + currentSea.getAlternateText().length];
            System.arraycopy(oldText, 0, newText, 0, oldText.length);
            System.arraycopy(currentSea.getAlternateText(), 0, newText, oldText.length, currentSea.getAlternateText().length);
            lastSea.setAlternateText(newText);
          }
        }
        ControlSequencePool.release(cs);
      } else if (cs instanceof SKI_SetKeyInformation currentSki && lastCS instanceof SKI_SetKeyInformation lastSki) {
        if (currentSki.getKeyInfo() == null) {
          // Reset
          lastSki.setKeyInfo(null);
        } else {
          var oldInfo = lastSki.getKeyInfo();
          if (oldInfo == null) {
            lastSki.setKeyInfo(currentSki.getKeyInfo());
          } else {
            var newInfo = new byte[oldInfo.length + currentSki.getKeyInfo().length];
            System.arraycopy(oldInfo, 0, newInfo, 0, oldInfo.length);
            System.arraycopy(currentSki.getKeyInfo(), 0, newInfo, oldInfo.length, currentSki.getKeyInfo().length);
            lastSki.setKeyInfo(newInfo);
          }
        }
        ControlSequencePool.release(cs);
      } else {
        controlSequences.add(cs);
        lastCS = cs;
      }
      pos += csi.getLength() - 2;
    }

    return controlSequences;
  }

  public static final PTOCAControlSequence createControlSequenceInstance(ControlSequenceIntroducer csi)
      throws AFPParserException {
    PTOCAControlSequence cs = ControlSequencePool.acquire(csi.getControlSequenceFunctionType());
    if (cs == null) {
      cs = CS_SUPPLIERS.getOrDefault(
          csi.getControlSequenceFunctionType(), com.mgz.afp.ptoca.controlSequence.Undefined::new).get();
    }
    cs.setCsi(csi);
    return cs;
  }

}
