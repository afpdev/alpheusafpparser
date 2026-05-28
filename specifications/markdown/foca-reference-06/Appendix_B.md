# Appendix B. Mapping of ISO Parameters

This appendix provides information to aid in understanding the relation between the parameters in this architecture and the parameters defined by the ISO/IEC 9541-1 Font Information Interchange standard. Detailed information about the transformation between ISO and FOCA parameters is defined with each FOCA parameter in the body of this document. [FOCA-B-001]

### Table 24. Mapping of ISO Font Parameters

| ISO 9541 Parameters | FOCA Parameters |
| :--- | :--- |
| **FONTNAME** (Req.) | Resource Name [FOCA-B-002]|
| **DATAVERSION** (Opt.) | Function Set Code [FOCA-B-003]|
| **STANDARDVERSION** (Req.) | Function Set Code [FOCA-B-004]|
| **DATASOURCE** (Opt.) | Data Source [FOCA-B-005]|
| **DATACOPYRIGHT** (Opt.) | Intellectual Property Data [FOCA-B-006]|
| **DSNSOURCE** (Req.) | Design Source [FOCA-B-007]|
| **DSNCOPYRIGHT** (Opt.) | Intellectual Property Data [FOCA-B-008]|
| **RELUNITS** (Opt.) | Measurement Units [FOCA-B-009]|
| **TYPEFACE** (Opt.) | Typeface Name [FOCA-B-010]|
| **FONTFAMILY** (Req.) | Family Name [FOCA-B-011]|
| **POSTURE** (Req.) | Posture Class [FOCA-B-012]|
| **POSTUREANGLE** (Opt.) | Nominal Character Slope [FOCA-B-013]|
| **WEIGHT** (Req.) | Weight Class [FOCA-B-014]|
| **PROPWIDTH** (Req.) | Width Class [FOCA-B-015]|
| **STRUCTURE** (Req.) | Structure Class [FOCA-B-016]|
| **DSNGROUP** (Req.) | Design Group / Sub-Group / Specific Group / General Class / Sub-Class [FOCA-B-017]|
| **NUMGLYPHS** (Opt.) | Number of Characters [FOCA-B-018]|
| **INCGLYPHCOLS** (Req.) | Graphic Character Set Global ID [FOCA-B-019]|
| **INCGLYPHS** (Req.) | Graphic Character Global ID [FOCA-B-020]|
| **DSNSIZE** (Req.) | Nominal Vertical Font Size [FOCA-B-021]|
| **MINSIZE** (Req.) | Minimum Vertical Font Size [FOCA-B-022]|
| **MAXSIZE** (Req.) | Maximum Vertical Font Size [FOCA-B-023]|
| **CAPHEIGHT** (Opt.) | Cap-M Height [FOCA-B-024]|
| **LCHEIGHT** (Opt.) | X-Height [FOCA-B-025]|
| **WRMODENAME** (Opt.) | (derived from character rotation) [FOCA-B-026]|
| **NOMESCDIR** (Req.) | Character Rotation [FOCA-B-027]|
| **ESCCLASS** (Req.) | Proportional Spaced (flag) [FOCA-B-028]|
| **AVGESCX/Y** (Req.) | Average Escapement [FOCA-B-029]|
| **AVGLCESCX/Y** (Req.) | Average Lowercase Escapement [FOCA-B-030]|
| **AVGCAPESCX/Y** (Req.) | Average Capital Escapement [FOCA-B-031]|
| **TABESCX/Y** (Req.) | Figure Space Increment [FOCA-B-032]|
| **MAXFONTEXT** (Req.) | Maximum Character Box Height & Width [FOCA-B-033]|
| **SCOREOFFSETX/Y** (Opt.) | Underscore / Overscore / Throughscore Position [FOCA-B-034]|
| **SCORETHICK** (Opt.) | Underscore / Overscore / Throughscore Width [FOCA-B-035]|
| **VSOFFSETX/Y** (Opt.) | Superscript / Subscript X-Axis / Y-Axis Offset [FOCA-B-036]|
| **VSSCALEX/Y** (Opt.) | Superscript / Subscript Vertical / Horizontal Size [FOCA-B-037]|
| **MINLINESP** (Opt.) | Default Baseline Increment [FOCA-B-038]|
| **MINANASCALE** (Opt.) | Minimum Horizontal Font Size [FOCA-B-039]|
| **MAXANASCALE** (Opt.) | Maximum Horizontal Font Size [FOCA-B-040]|
| **COPYFITMEASURE** (Opt.) | Average Weighted Escapement [FOCA-B-041]|
| **GNAME** (Req.) | Graphic Character Global ID [FOCA-B-042]|
| **PX, PY** (Opt.) | (coordinate system origin) [FOCA-B-043]|
| **EX, EY** (Req.) | Character Increment [FOCA-B-044]|
| **EXT** (Req.) | A-Space, B-Space, C-Space, Ascender Height, Descender Depth [FOCA-B-045]|
| **PEAN** (Opt.) | (linkage by data structure, not name) [FOCA-B-046]|
| **PEAX** (Opt.) | Kerning Pair X Adjust [FOCA-B-047]|

**(No corresponding ISO parameter)**:
*   Font Typeface Global ID [FOCA-B-048]
*   Font Use Code [FOCA-B-049]
*   Misc. Control Flags (Kerning Pair Data, MICR Font, etc.) [FOCA-B-050]
*   Em-Space Increment [FOCA-B-051]
*   Maximum/Minimum Character Slope [FOCA-B-052]
*   Nominal Horizontal Font Size [FOCA-B-053]
*   External/Internal Leading [FOCA-B-054]
*   Maximum Ascender Height / Baseline Extent / Baseline Offset / Character Increment / Descender Depth [FOCA-B-055]
*   Minimum A-Space [FOCA-B-056]
*   Space Character Increment [FOCA-B-057]
*   Character Box Height / Width [FOCA-B-058]
*   Frequency of Use [FOCA-B-059]
