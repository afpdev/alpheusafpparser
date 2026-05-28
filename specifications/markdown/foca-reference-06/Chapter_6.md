# Chapter 6. Font Interchange Formats

<!-- Page 127 -->

Font Information Interchange is defined to be the transfer of font information between or among processing systems, software processes, and hardware devices. You can transfer font information between any two systems, processes, or devices as long as both parties recognize the same format. The font information that you interchange can be a complete font resource, selected components of a font resource, or reference information about a font resource. [FOCA-6-001]

This chapter specifies the public font information interchange formats supported by this architecture. [FOCA-6-002]

## AFP System Font Resource

The format for font resource data, to be loaded and managed by Advanced Function Presentation (AFP) software, is defined by the following syntax specification. The syntax specification is divided into three sections: objects, structured fields, and triplets. [FOCA-6-003]

### Objects

#### Coded Font

A coded font is an AFP font resource object that associates AFP font character set objects with AFP code page objects. [FOCA-6-004]

The structured fields that compose the coded font must appear in the following sequence:

1.  **Begin Coded Font (BCF)** [FOCA-6-005]
2.  **Coded Font Control (CFC)** [FOCA-6-006]
3.  **Coded Font Index (CFI)** [FOCA-6-007]
4.  **End Coded Font (ECF)** [FOCA-6-008]
5.  **No Operation (NOP)**: (Optional) [FOCA-6-009]

#### Code Page

A code page is a font resource object that associates graphic character global IDs (GCGIDs) to code points. [FOCA-6-010]

The structured fields that compose the code page must appear in the following sequence:

1.  **Begin Code Page (BCP)** [FOCA-6-011]
2.  **Code Page Descriptor (CPD)** [FOCA-6-012]
3.  **Code Page Control (CPC)** [FOCA-6-013]
4.  **Code Page Index (CPI)** [FOCA-6-014]
5.  **End Code Page (ECP)** [FOCA-6-015]
6.  **No Operation (NOP)**: (Optional) [FOCA-6-016]

#### Font Character Set

A font character set is a font resource object that contains descriptive and metric information for the whole font, and metric and shape information for each character identifier. [FOCA-6-017]

The structured fields must appear in the following sequence:

1.  **Begin Font (BFN)** [FOCA-6-018]
2.  **Font Descriptor (FND)** [FOCA-6-019]
3.  **Font Control (FNC)** [FOCA-6-020]
4.  **Font Patterns Map (FNM)** [FOCA-6-021]
5.  **Font Orientation (FNO)** [FOCA-6-022]
6.  **Font Position (FNP)** [FOCA-6-023]
7.  **Font Index (FNI)** [FOCA-6-024]
8.  **Font Name Map (FNN)** [FOCA-6-025]
9.  **Font Patterns (FNG)** [FOCA-6-026]
10. **End Font (EFN)** [FOCA-6-027]
11. **No Operation (NOP)**: (Optional) [FOCA-6-028]

---

### Structured Fields

#### Structured Field Introducer

A structured field introducer begins each structured field. [FOCA-6-029]

**Table 13. Structured Field Introducer** [FOCA-6-030]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | 8–32,767 | Length of Structured Field | M [FOCA-6-031] |
| 2–4 | CODE | SFID | See Below | Structured Field Identifier | M [FOCA-6-032] |
| 5 | BITS | SFFlags | See Below | Control Flags | M [FOCA-6-033] |
| 6–7 | UBIN | Sequence Number | 1–32,767 | The number of the structured field in the object. | M [FOCA-6-034] |
| 8 | UBIN | Extension Length | 1–255 | Length of extension data | O [FOCA-6-035] |
| 9–n | UNDF | Extension Data | | Up to 254 bytes of data | O [FOCA-6-036] |
| x–y | UNDF | Padding Data | | Up to 32,759 bytes of data | O [FOCA-6-037] |

**Table 14. Structured-Field Identifiers** [FOCA-6-038]

| Field Abbr | Assigned Number | Full Field Name |
| :--- | :--- | :--- |
| **BCF** | X'D3A88A' | Begin Coded Font [FOCA-6-039] |
| **BCP** | X'D3A887' | Begin Code Page [FOCA-6-040] |
| **BFN** | X'D3A889' | Begin Font [FOCA-6-041] |
| **CFC** | X'D3A78A' | Coded Font Control [FOCA-6-042] |
| **CFI** | X'D38C8A' | Coded Font Index [FOCA-6-043] |
| **CPC** | X'D3A787' | Code Page Control [FOCA-6-044] |
| **CPD** | X'D3A687' | Code Page Descriptor [FOCA-6-045] |
| **CPI** | X'D38C87' | Code Page Index [FOCA-6-046] |
| **ECF** | X'D3A98A' | End Coded Font [FOCA-6-047] |
| **ECP** | X'D3A987' | End Code Page [FOCA-6-048] |
| **EFN** | X'D3A989' | End Font [FOCA-6-049] |
| **FNC** | X'D3A789' | Font Control [FOCA-6-050] |
| **FND** | X'D3A689' | Font Descriptor [FOCA-6-051] |
| **FNG** | X'D3EE89' | Font Patterns [FOCA-6-052] |
| **FNI** | X'D38C89' | Font Index [FOCA-6-053] |
| **FNM** | X'D3A289' | Font Patterns Map [FOCA-6-054] |
| **FNN** | X'D3AB89' | Font Names (Outline Fonts Only) [FOCA-6-055] |
| **FNO** | X'D3AE89' | Font Orientation [FOCA-6-056] |
| **FNP** | X'D3AC89' | Font Position [FOCA-6-057] |
| **NOP** | X'D3EEEE' | No Operation [FOCA-6-058] |

---

<!-- Page 128 -->

### BCF – D3A88A – Begin Coded Font

The Begin Coded Font (BCF) structured field begins a coded font object. [FOCA-6-059]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | CFName | | Coded Font Name | O [FOCA-6-060] |

---

<!-- Page 129 -->

### BCP – D3A887 – Begin Code Page

The Begin Code Page (BCP) structured field begins a code page object. [FOCA-6-061]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | CPName | | Code Page Name | O [FOCA-6-062] |
| 8–n | UNDF | Triplets | See Below | Self Defining Triplets | O [FOCA-6-063] |

---

<!-- Page 131 -->

### BFN – D3A889 – Begin Font

The Begin Font (BFN) structured field begins the font character set object. [FOCA-6-064]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | CSName | | Font Character Set Name | O [FOCA-6-065] |
| 8–n | UNDF | Triplets | See Below | Self Defining Triplets | O [FOCA-6-066] |

---

<!-- Page 133 -->

### CFC – D3A78A – Coded Font Control

The Coded Font Control (CFC) structured field specifies the length of the repeating group in the Coded Font Index (CFI) structured field. [FOCA-6-067]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | CFIRGLen | X'19' | CFI Repeating Group Length | M [FOCA-6-068] |
| 1 | UBIN | Retired | X'01' | Retired Parameter | M [FOCA-6-069] |
| 2–end | Triplet | | X'79' | Metric Adjustment triplet | O [FOCA-6-070] |

---

<!-- Page 134 -->

### CFI – D38C8A – Coded Font Index

The Coded Font Index (CFI) structured field names the font character sets and code pages for the font. [FOCA-6-071]

**Repeating Group Definition:** [FOCA-6-072]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | FCSName | | Font Character Set Name | M [FOCA-6-073] |
| 8–15 | CHAR | CPName | | Code Page Name | M [FOCA-6-074] |
| 16–17 | UBIN | SVSize | 0–65,535 | Specified Vertical Font Size (in 1440ths) | M [FOCA-6-075] |
| 18–19 | UBIN | SHScale | 0–65,535 | Specified Horizontal Scale Factor (in 1440ths) | M [FOCA-6-076] |
| 20–23 | UNDF | | X'00000000' | Reserved | M [FOCA-6-077] |
| 24 | UBIN | Section | | Section Number | M [FOCA-6-078] |

---

<!-- Page 137 -->

### CPC – D3A787 – Code Page Control

The Code Page Control (CPC) contains information about the code page. [FOCA-6-079]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | DefCharID | | Default Graphic Character Global ID | M [FOCA-6-080] |
| 8 | BITS | PrtFlags | See Below | Default Character Use Flags | M [FOCA-6-081] |
| 9 | CODE | CPIRGLen | X'0A', X'0B', X'FE', X'FF' | CPI Repeating Group Length | M [FOCA-6-082] |
| 10 | UBIN | VSCharSN | X'00'–X'FF' | Space Character Section Number | M [FOCA-6-083] |
| 11 | UBIN | VSChar | X'00'–X'FF' | Space Character Code Point | M [FOCA-6-084] |
| 12 | BITS | VSFlags | See Below | Code Page Use Flags | M [FOCA-6-085] |
| +0–3 | UBIN | Unicode | | Optional Unicode value for default ID | O [FOCA-6-086] |

---

<!-- Page 141 -->

### CPD – D3A687 – Code Page Descriptor

The Code Page Descriptor (CPD) structured field describes the code page. [FOCA-6-087]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–31 | CHAR | CPDesc | | Code Page Description | M [FOCA-6-088] |
| 32–33 | UBIN | GCGIDLen | 8 | Graphic Character GID Length | M [FOCA-6-089] |
| 34–37 | UBIN | NumCdPts | 1–65,535 | Number of Assigned Code Points | M [FOCA-6-090] |
| 38–39 | UBIN | GCSGID | | Graphic Character Set GID | M [FOCA-6-091] |
| 40–41 | UBIN | CPGID | | Code Page Global Identifier | M [FOCA-6-092] |
| 42–43 | UBIN | EncScheme | See Below | Encoding Scheme | O [FOCA-6-093] |

---

<!-- Page 143 -->

### CPI – D38C87 – Code Page Index

In a series of repeating groups, the Code Page Index (CPI) associates character IDs with code points. [FOCA-6-094]

**Repeating Group Definition:** [FOCA-6-095]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | GCGID | | Graphic Character Global ID | M [FOCA-6-096] |
| 8 | BITS | PrtFlags | See Below | Graphic Character Use Flags | M [FOCA-6-097] |
| 9 or 9–10 | UBIN | CodePoint | 0–65,535 | Code Point | M [FOCA-6-098] |
| +0 | UBIN | Count | X'00'–X'FF' | Number of Unicode scalar values | O [FOCA-6-099] |
| ++0–3 | UBIN | Unicode | | Unicode scalar value | O [FOCA-6-100] |

---

<!-- Page 147 -->

### ECF – D3A98A – End Coded Font

The End Coded Font (ECF) structured field ends the coded font object. [FOCA-6-101]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | CFName | | Coded Font Name | O [FOCA-6-102] |

---

<!-- Page 148 -->

### ECP – D3A987 – End Code Page

The End Code Page (ECP) structured field ends the code page object. [FOCA-6-103]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | CPName | | Code Page Name | O [FOCA-6-104] |

---

<!-- Page 149 -->

### EFN – D3A989 – End Font

The End Font (EFN) structured field ends the font character set object. [FOCA-6-105]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | CSName | | Font Character Set Name | O [FOCA-6-106] |

---

<!-- Page 150 -->

### FNC – D3A789 – Font Control

The Font Control (FNC) structured field provides defaults and information about the font character set. [FOCA-6-107]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Retired | X'01' | Retired Parameter | M [FOCA-6-108] |
| 1 | CODE | PatTech | X'05', X'1E', X'1F' | Pattern Technology Identifier | M [FOCA-6-109] |
| 2 | UNDF | Reserved | X'00' | Reserved | M [FOCA-6-110] |
| 3 | BITS | FntFlags | See Below | Font Use Flags | M [FOCA-6-111] |
| 4 | CODE | XUnitBase | X'00', X'02' | X Unit Base | M [FOCA-6-112] |
| 5 | CODE | YUnitBase | X'00', X'02' | Y Unit Base | M [FOCA-6-113] |
| 6–7 | UBIN | XftUnits | | X Units per Unit Base | M [FOCA-6-114] |
| 8–9 | UBIN | YftUnits | | Y Units per Unit Base | M [FOCA-6-115] |
| 10–11 | UBIN | MaxBoxWd | 0–32,767 | Max Character Box Width | M [FOCA-6-116] |
| 12–13 | UBIN | MaxBoxHt | 0–32,767 | Max Character Box Height | M [FOCA-6-117] |
| 14 | UBIN | FNORGLen | X'1A' | FNO Repeating Group Length | M [FOCA-6-118] |
| 15 | UBIN | FNIRGLen | | FNI Repeating Group Length | M [FOCA-6-119] |
| 16 | CODE | PatAlign | X'00', X'02', X'03' | Pattern Data Alignment Code | M [FOCA-6-120] |
| 17–19 | UBIN | RPatDCnt | | Raster Pattern Data Count | M [FOCA-6-121] |
| 20 | UBIN | FNPRGLen | X'16' | FNP Repeating Group Length | M [FOCA-6-122] |
| 21 | UBIN | FNMRGLen | X'00', X'08' | FNM Repeating Group Length | M [FOCA-6-123] |
| 22 | CODE | ResXUBase | X'00' | Shape resolution X Unit Base | O [FOCA-6-124] |
| 23 | CODE | ResYUBase | X'00' | Shape resolution Y Unit Base | O [FOCA-6-125] |
| 24–25 | UBIN | XfrUnits | | Shape resolution X units | O [FOCA-6-126] |
| 26–27 | UBIN | YfrUnits | | Shape resolution Y units | O [FOCA-6-127] |
| 28–31 | UBIN | OPatDCnt | | Outline Pattern Data Count | O [FOCA-6-128] |
| 32–34 | UNDF | Reserved | X'000000' | Reserved | O [FOCA-6-129] |
| 35 | UBIN | FNNRGLen | X'0C' | FNN Repeating Group Length | O [FOCA-6-130] |
| 36–39 | UBIN | FNNDCnt | | FNN Data Count | O [FOCA-6-131] |
| 40–41 | UBIN | FNNMapCnt | 0–65,535 | FNN Name Count | O [FOCA-6-132] |
| 42–nn | Triplets | | | Self-Defining Triplets | O [FOCA-6-133] |

---

<!-- Page 157 -->

### FND – D3A689 – Font Descriptor

The Font Descriptor (FND) specifies the overall characteristics of a font character set. [FOCA-6-134]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–31 | CHAR | TypeFcDesc | | Typeface Description | M [FOCA-6-135] |
| 32 | CODE | FtWtClass | 1–9 | Weight Class | M [FOCA-6-136] |
| 33 | CODE | FtWdClass | 1–9 | Width Class | M [FOCA-6-137] |
| 34–35 | UBIN | MaxPtSize | | Max Vertical Size (10ths of Pt) | M [FOCA-6-138] |
| 36–37 | UBIN | NomPtSize | | Nominal Vertical Size | M [FOCA-6-139] |
| 38–39 | UBIN | MinPtSize | | Min Vertical Size | M [FOCA-6-140] |
| 40–41 | UBIN | MaxHSize | 0–X'7FFE' | Max Horizontal Size (1440ths) | M [FOCA-6-141] |
| 42–43 | UBIN | NomHSize | 0–X'7FFE' | Nominal Horizontal Size | M [FOCA-6-142] |
| 44–45 | UBIN | MinHSize | 0–X'7FFE' | Minimum Horizontal Size | M [FOCA-6-143] |
| 46 | CODE | DsnGenCls | 0–255 | Design General Class (ISO) | M [FOCA-6-144] |
| 47 | CODE | DsnSubCls | 0–255 | Design Subclass (ISO) | M [FOCA-6-145] |
| 48 | CODE | DsnSpcGrp | 0–255 | Design Specific Group (ISO) | M [FOCA-6-146] |
| 49–63 | UBIN | Reserved | | Reserved | M [FOCA-6-147] |
| 64–65 | BITS | FtDsFlags | See Below | Font Design Flags | M [FOCA-6-148] |
| 66–75 | UBIN | Reserved | | Reserved | M [FOCA-6-149] |
| 76–77 | UBIN | GCSGID | | Graphic Character Set GID | M [FOCA-6-150] |
| 78–79 | UBIN | FGID | | Font Typeface GID (FGID) | M [FOCA-6-151] |
| 80–nn | Triplets | | | Self Defining Triplets (X'02') | O [FOCA-6-152] |

---

<!-- Page 162 -->

### FNG – D3EE89 – Font Patterns

The Font Patterns (FNG) structured field carries the character shape data (raster patterns or outline data). [FOCA-6-153]

**Outline Font Repeating Group:** [FOCA-6-154]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–3 | UBIN | OFLLen | | Length of the outline font object | M [FOCA-6-155] |
| 4–7 | UBIN | Checksum | | Checksum value | M [FOCA-6-156] |
| 8–9 | UBIN | TIDLen | | Technology-specific ID length | M [FOCA-6-157] |
| 10–n | UBIN | TIDName | | Technology-specific ID | O [FOCA-6-158] |
| n+1..n+2 | UBIN | ODescLen | | Descriptor length | O [FOCA-6-159] |
| n+3..m | UNDF | ObjDesc | | Descriptor data | O [FOCA-6-160] |
| m+1..end | UNDF | ObjData | | Object data | O [FOCA-6-161] |

---

<!-- Page 166 -->

### FNI – D38C89 – Font Index

For each character in a raster font, the Font Index (FNI) describes specific characteristics and points to an entry in the Font Patterns Map (FNM). [FOCA-6-162]

**Repeating Group Definition:** [FOCA-6-163]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | CHAR | GCGID | | Graphic Character Global ID | M [FOCA-6-164] |
| 8–9 | UBIN | CharInc | | Character Increment | M [FOCA-6-165] |
| 10–11 | SBIN | AscendHt | | Ascender Height | O [FOCA-6-166] |
| 12–13 | SBIN | DescendDp | | Descender Depth | O [FOCA-6-167] |
| 14–15 | UBIN | Reserved | X'0000' | Reserved | O [FOCA-6-168] |
| 16–17 | UBIN | FNMCnt | | FNM Index | O [FOCA-6-169] |
| 18–19 | SBIN | ASpace | | A-Space | O [FOCA-6-170] |
| 20–21 | UBIN | BSpace | | B-space | O [FOCA-6-171] |
| 22–23 | SBIN | CSpace | | C-Space | O [FOCA-6-172] |
| 24–25 | UBIN | Reserved | X'0000' | Reserved | O [FOCA-6-173] |
| 26–27 | SBIN | BaseOset | | Baseline Offset | O [FOCA-6-174] |

---

<!-- Page 171 -->

### FNM – D3A289 – Font Patterns Map

The Font Patterns Map (FNM) structured field describes some characteristics of the font character patterns. [FOCA-6-175]

**Repeating Group Definition:** [FOCA-6-176]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | CharBoxWd | | Character Box Width | M [FOCA-6-177] |
| 2–3 | UBIN | CharBoxHt | | Character Box Height | M [FOCA-6-178] |
| 4–7 | UBIN | PatDOset | | Pattern Data Offset | M [FOCA-6-179] |

---

<!-- Page 173 -->

### FNN – D3AB89 – Font Name Map

The Font Name Map is used to map IBM character names to the character names in outline fonts. [FOCA-6-180]

**Section 1:**

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | CODE | IBM format | X'02' | IBM character ID format | M [FOCA-6-181] |
| 1 | CODE | Technology | X'03', X'05' | Tech-specific ID format | M [FOCA-6-182] |

**Section 2 (Repeating Groups):** [FOCA-6-183]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–7 | UNDF | GCGID | | Graphic Character Global ID | O [FOCA-6-184] |
| 8–11 | UBIN | TSOffset | | Tech-specific identifier offset | O [FOCA-6-185] |

**Section 3 (Variable Length):** [FOCA-6-186]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | TSIDLen | 2–128 | Tech-specific ID length | O [FOCA-6-187] |
| 1–n | UNDF | TSID | | Tech-specific identifier | O [FOCA-6-188] |

#### FNN Example

| Offset | Meaning | Value | Comments [FOCA-6-189] |
| :--- | :--- | :--- | :--- |
| 0 | GCGID Type | X'02' | Second section in EBCDIC [FOCA-6-190] |
| 1 | GCGID Type | X'03' | Third section in Adobe ASCII [FOCA-6-191] |
| 2–9 | GCGID | LA010000 | GCGID for lowercase 'a' [FOCA-6-192] |
| 10–13 | Offset | X'0000005F' | 95 [FOCA-6-193] |
| 14–21 | GCGID | LA020000 | GCGID for uppercase 'A' [FOCA-6-194] |
| 22–25 | Offset | X'00000061' | 97 [FOCA-6-195] |
| ... | ... | ... | ... [FOCA-6-196] |
| 95 | TSID Length | 2 [FOCA-6-197] | |
| 96 | TSID | a [FOCA-6-198] | |
| 97 | TSID Length | 2 [FOCA-6-199] | |
| 98 | TSID | A [FOCA-6-200] | |

---

<!-- Page 177 -->

### FNO – D3AE89 – Font Orientation

Each repeating group in the Font Orientation (FNO) structured field applies to one character rotation. [FOCA-6-201]

**Repeating Group Definition:** [FOCA-6-202]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Reserved | X'0000' | Reserved | M [FOCA-6-203] |
| 2–3 | UBIN | CharRot | | Character Rotation | M [FOCA-6-204] |
| 4–5 | SBIN | MaxBOset | | Max Baseline Offset | M [FOCA-6-205] |
| 6–7 | UBIN | MaxCharInc | | Max Character Increment | M [FOCA-6-206] |
| 8–9 | UBIN | SpCharInc | | Space Character Increment | M [FOCA-6-207] |
| 10–11 | UBIN | MaxBExt | | Max Baseline Extent | M [FOCA-6-208] |
| 12 | BITS | OrntFlgs | | Orientation Control Flags | M [FOCA-6-209] |
| 13 | UBIN | Reserved | X'00' | Reserved | M [FOCA-6-210] |
| 14–15 | UBIN | EmSpInc | | Em-Space Increment | M [FOCA-6-211] |
| 16–17 | UBIN | Reserved | X'0000' | Reserved | M [FOCA-6-212] |
| 18–19 | UBIN | FigSpInc | | Figure Space Increment | M [FOCA-6-213] |
| 20–21 | UBIN | NomCharInc | | Nominal Character Increment | M [FOCA-6-214] |
| 22–23 | UBIN | DefBInc | 0–65,535 | Default Baseline Increment | M [FOCA-6-215] |
| 24–25 | SBIN | MinASp | | Minimum A-Space | M [FOCA-6-216] |

---

<!-- Page 182 -->

### FNP – D3AC89 – Font Position

The Font Position (FNP) structured field describes the common characteristics of all characters. [FOCA-6-217]

**Repeating Group Definition:** [FOCA-6-218]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Reserved | X'0000' | Reserved | M [FOCA-6-219] |
| 2–3 | SBIN | LcHeight | | Lowercase Height | M [FOCA-6-220] |
| 4–5 | SBIN | CapMHt | | Cap-M Height | M [FOCA-6-221] |
| 6–7 | SBIN | MaxAscHt | | Max Ascender Height | M [FOCA-6-222] |
| 8–9 | SBIN | MaxDesDp | | Max Descender Depth | M [FOCA-6-223] |
| 10–14 | UBIN | Reserved | | Reserved | M [FOCA-6-224] |
| 15 | UBIN | Retired | X'01' | Retired Parameter | M [FOCA-6-225] |
| 16 | UBIN | Reserved | X'00' | Reserved | M [FOCA-6-226] |
| 17–18 | UBIN | UscoreWd | | Underscore Width (units) | M [FOCA-6-227] |
| 19 | UBIN | UscoreWdf | X'00' | Underscore Width (fraction) | M [FOCA-6-228] |
| 20–21 | SBIN | UscorePos | | Underscore Position | M [FOCA-6-229] |

---

<!-- Page 184 -->

### NOP – D3EEEE – No Operation

Can carry comments or unarchitected data. [FOCA-6-230]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–end | UNDF | NopData | Any value | Comment data | O [FOCA-6-231] |

---

## Triplets

<!-- Page 185 -->

### X'02' – Fully Qualified Name Triplet

Enables identification using Global Identifiers. [FOCA-6-232]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | TripLen | 5–254 | Triplet Length | M [FOCA-6-233] |
| 1 | CODE | TripID | X'02' | Triplet Identifier | M [FOCA-6-234] |
| 2 | CODE | FQNType | X'07', X'08' | GID usage type | M [FOCA-6-235] |
| 3 | | | 0 | Reserved | M [FOCA-6-236] |
| 4–253 | CHAR | FQName | | GID of the object | M [FOCA-6-237] |

---

<!-- Page 190 -->

### X'62' – Local Date and Time Stamp Triplet

Identifies object creation/revision date and time. [FOCA-6-238]

| Offset | Type | Name | Range | Meaning | M/O |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | TripLen | 17 | Triplet Length | M [FOCA-6-239] |
| 1 | CODE | TripID | X'62' | Triplet Identifier | M [FOCA-6-240] |
| 2 | CODE | TSType | 0, 1, 3 | Time Stamp Type | M [FOCA-6-241] |
| ... | ... | ... | ... | ... | ... [FOCA-6-242] |

---

<!-- Page 192 -->

### X'63', Type 1 – CRC Resource Management Triplet

| Offset | Type | Name | Range | Meaning | M/O [FOCA-6-243] |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | TripLen | 6 | Triplet Length | M [FOCA-6-244] |
| 1 | CODE | TripID | X'63' | Triplet Identifier | M [FOCA-6-245] |
| 2 | CODE | FmtQual | X'01' | Format Qualifier | M [FOCA-6-246] |
| 3–4 | UBIN | RMValue | | Retired RM value | M [FOCA-6-247] |
| 5 | BITS | ResClassFlg | | Resource Class Flags | M [FOCA-6-248] |

---

<!-- Page 194 -->

### X'64' – Object Origin Identifier Triplet

| Offset | Type | Name | Range | Meaning | M/O [FOCA-6-249] |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | TripLen | 61 | Triplet Length | M [FOCA-6-250] |
| 1 | CODE | TripID | X'64' | Triplet Identifier | M [FOCA-6-251] |
| 2 | CODE | FmtQual | | Format Qualifier | M [FOCA-6-252] |
| 3–10 | CHAR | HostID | | Host/System Identifier | M [FOCA-6-253] |
| 11–16 | CHAR | MediaID | | Media Identifier | M [FOCA-6-254] |
| 17–60 | CHAR | DataSID | | Data Set Identifier | M [FOCA-6-255] |

---

<!-- Page 196 -->

### X'6D' – Extension Font Triplet

| Offset | Type | Name | Range | Meaning | M/O [FOCA-6-256] |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | TripLen | 4 | Triplet Length | M [FOCA-6-257] |
| 1 | CODE | TripID | X'6D' | Triplet Identifier | M [FOCA-6-258] |
| 2–3 | CODE | GCSGID | | Base font GCSGID | M [FOCA-6-259] |

---

<!-- Page 198 -->

### X'79' – Metric Adjustment Triplet

| Offset | Type | Name | Range | Meaning | M/O [FOCA-6-260] |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0 | UBIN | Length | X'0F' | Triplet Length | M [FOCA-6-261] |
| 1 | CODE | TID | X'79' | Triplet Identifier | M [FOCA-6-262] |
| 2 | CODE | UnitBase | X'00' | Unit base | M [FOCA-6-263] |
| 3–4 | UBIN | XUPUB | | Units per unit base X | M [FOCA-6-264] |
| 5–6 | UBIN | YUPUB | | Units per unit base Y | M [FOCA-6-265] |
| 7–8 | SBIN | H-Inc | | Horizontal increment | M [FOCA-6-266] |
| 9–10 | SBIN | V-Inc | | Vertical increment | M [FOCA-6-267] |
| 11–12 | SBIN | H-Base | | Horizontal baseline adjustment | M [FOCA-6-268] |
| 13–14 | SBIN | V-Base | | Vertical baseline adjustment | M [FOCA-6-269] |

---

## Supplemental Font Formats

*   **SAA CPI System Font Resource**: Query Font Metrics calls. [FOCA-6-270]
*   **IPDS Device Font Resource**: Loaded-Font Command Set. [FOCA-6-271]
*   **MO:DCA Presentation Document Font Reference**: Map Coded Font (MCF). [FOCA-6-272]
*   **SAA CPI System Font Reference**: Create Logical Font program call. [FOCA-6-273]
*   **IPDS Device Font Reference**: Load Font Equivalence (LFE) command. [FOCA-6-274]
