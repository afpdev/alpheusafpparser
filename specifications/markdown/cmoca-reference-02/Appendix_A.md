# Appendix A. Tag Registry

This table defines the CMR tags in the base level of the Color Management Object Content Architecture. Support for some CMR types is optional, see Appendix C, “Compliance With Color Management Object Content Architecture”. When a presentation device supports a given CMR type, it must support the tags used by that CMR type, as defined in this table. [CMOCA-A-001]

### Table 51. Tag Registry

| TagID | Tag Name | CMR Type |
| :--- | :--- | :--- |
| X'0004' | Comment | Halftone, Tone Transfer Curve, Color Conversion, Link Color Conversion, Indexed [CMOCA-A-002]|
| X'0008' | Date and Time Stamp | Halftone, Tone Transfer Curve, Color Conversion, Link Color Conversion, Indexed [CMOCA-A-003]|
| X'0011' | Number of Components | Halftone, Tone Transfer Curve [CMOCA-A-004]|
| X'1011' | Halftone Subset | Halftone [CMOCA-A-005]|
| X'1021' | Array Width | Halftone [CMOCA-A-006]|
| X'1025' | Array Height | Halftone [CMOCA-A-007]|
| X'1030' | Max Image Value | Halftone [CMOCA-A-008]|
| X'1035' | Number of Device Levels | Halftone [CMOCA-A-009]|
| X'1040' | Offset Tiling | Halftone [CMOCA-A-010]|
| X'1045' | Bilevel Point-Operation Screen Data | Halftone [CMOCA-A-011]|
| X'1050' | Multilevel Point-Operation Screen Data | Halftone [CMOCA-A-012]|
| X'1055' | Error Diffusion Filter | Halftone [CMOCA-A-013]|
| X'1060' | Location of Current Pixel | Halftone [CMOCA-A-014]|
| X'1065' | Raster Direction | Halftone [CMOCA-A-015]|
| X'1070' | Boundary Condition | Halftone [CMOCA-A-016]|
| X'1075' | Threshold Value | Halftone [CMOCA-A-017]|
| X'1080' | Quantization Boundary Table | Halftone [CMOCA-A-018]|
| X'2004' | Tone Transfer Curve Subset | Tone Transfer Curve [CMOCA-A-019]|
| X'2011' | Tone Transfer Curve Length | Tone Transfer Curve [CMOCA-A-020]|
| X'2015' | Tone Transfer Curve Data | Tone Transfer Curve [CMOCA-A-021]|
| X'2020' | Inverse Tone Transfer Curve Data | Tone Transfer Curve [CMOCA-A-022]|
| X'3011' | ICC Profile Subset | Color Conversion [CMOCA-A-023]|
| X'3015' | ICC Profile Data | Color Conversion [CMOCA-A-024]|
| X'3025' | ICC Profile Filename | Color Conversion [CMOCA-A-025]|
| X'4011' | Link Color Conversion Subset | Link Color Conversion [CMOCA-A-026]|
| X'4015' | Link Audit CMR OID | Link Color Conversion [CMOCA-A-027]|
| X'4020' | Link Instruction CMR OID | Link Color Conversion [CMOCA-A-028]|
| X'4025' | Link Audit CMR Name | Link Color Conversion [CMOCA-A-029]|
| X'4030' | Link Instruction CMR Name | Link Color Conversion [CMOCA-A-030]|
| X'4035' | Default Rendering Intent | Link Color Conversion [CMOCA-A-031]|
| X'4040' | Link LUT Perceptual | Link Color Conversion [CMOCA-A-032]|
| X'4045' | Link LUT Media-Relative Colorimetric | Link Color Conversion [CMOCA-A-033]|
| X'4050' | Link LUT Saturation | Link Color Conversion [CMOCA-A-034]|
| X'4055' | Link LUT ICC-Absolute Colorimetric | Link Color Conversion [CMOCA-A-035]|
| X'4090' | Link CMRE Identifier | Link Color Conversion [CMOCA-A-036]|
| X'5011' | Indexed Subset | Indexed [CMOCA-A-037]|
| X'5015' | Number of Named Colorants | Indexed [CMOCA-A-038]|
| X'5020' | Color Palette Gray | Indexed [CMOCA-A-039]|
| X'5025' | Color Palette CMYK | Indexed [CMOCA-A-040]|
| X'5030' | Color Palette RGB | Indexed [CMOCA-A-041]|
| X'5035' | Color Palette CIELAB | Indexed [CMOCA-A-042]|
| X'5040' | Color Palette Named Colorants | Indexed [CMOCA-A-043]|
| X'5045' | Colorant Identification List | Indexed [CMOCA-A-044]|
| X'FFFF' | End Data | Halftone, Tone Transfer Curve, Color Conversion, Link Color Conversion, Indexed [CMOCA-A-045]|

**Notes:**
1. For an Indexed CMR, at least one of the Color Palette tags must be present. [CMOCA-A-046]
2. Tags X'F000'–X'FFFE' are private tags. [CMOCA-A-047]
