# Chapter 5. FOCA Parameters

Digital data processing requires that you create data structures using a defined set of parameters and parameter values organized in a specific format. FOCA provides a precise set of parameter definitions and architected values or ranges of values you can use to create font resources and font references. [FOCA-5-001]

This chapter describes the conventions for defining FOCA parameters. Then, it defines the available FOCA parameters, dividing them into the following five categories:
*   Font-description parameters [FOCA-5-002]
*   Font-metric parameters [FOCA-5-003]
*   Character-metric parameters [FOCA-5-004]
*   Character-shape parameters [FOCA-5-005]
*   Character-mapping parameters [FOCA-5-006]

## Defining FOCA Parameters

This section describes three parameter formats, the parameter types available for each format, and byte and bit numbering conventions. [FOCA-5-007]

### Parameter Formats

FOCA supports a variety of parameter formats, that is, types of syntax. The choice of format depends on the environment where you want to use the font resource. The following are three common formats supported by FOCA. See Chapter 6, “Font Interchange Formats”, for more detailed information about the formatting standards necessary to implement FOCA. [FOCA-5-008]

*   **Fixed-Format**: A fixed-format parameter is defined by its position and length within a string of fixed-format parameters. The variable name and its associated meaning is implied by the position of the parameter in the string. [FOCA-5-009]
*   **Self-Identifying**: A self-identifying parameter has a set of fields that identify the parameter, specify its length, and specify its values. [FOCA-5-010]
*   **Clear-Text**: A clear-text parameter has two fields, which are separated by a delimiter. The first field contains a character string that is the name of the parameter and the second field contains a character string that represents the value of the parameter. [FOCA-5-011]

### Parameter Types

You define a FOCA parameter by identifying it and assigning it a value. The remaining sections of this chapter list the names of the parameters and show **Parameter type =** as requiring a value in the form of one of the following data types: [FOCA-5-012]

*   **Character string**: A character string parameter value is any user, system, or font-supplier defined name. It is composed of alphanumeric characters and can be any specified length. Unless otherwise specified, the default set is IBM Graphic Character Set 103. [FOCA-5-013]
*   **Flag**: A flag parameter value indicates a binary flag (1 or 0). [FOCA-5-014]
*   **Number**: A number parameter value uses real numbers or integer numbers to represent count or magnitude. [FOCA-5-015]
*   **Code**: A code parameter value is a collection of architected choices (integers, letters, or acronyms). [FOCA-5-016]
*   **Undefined**: An undefined parameter value is not defined by the architecture. [FOCA-5-017]

### Byte and Bit Numbering

*   **Byte numbering**: During both transmission and storage, data is viewed as a long horizontal string. Each byte is identified by a positive integer (offset) starting with 0. [FOCA-5-018]
*   **Bit numbering**: Bytes are divided into eight bits, numbered left to right from 0 to 7 (big-endian). Bit 0 is the most significant bit. [FOCA-5-019]

---

## Font-Description Parameters

This section lists and describes the descriptive parameters required to identify a font, select the appropriate font for formatting, and locate the specified font for presentation. [FOCA-5-020]

### Average Weighted Escapement
The Average Weighted Escapement parameter specifies the arithmetic average of the escapement of all, or some subset of, the characters in a font. The escapement value for each character is weighted by its anticipated frequency of use. [FOCA-5-021]

*   **Parameter type =** number [FOCA-5-022]
*   **Synonyms =** average character width [FOCA-5-023]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-024]
*   **Transformation to ISO/IEC 9541 font architecture:** No equivalent parameter exists in the ISO architecture. This parameter should be expressed as a non-iso-property in the Modal Properties property list. [FOCA-5-025]

### Cap-M Height
The Cap-M Height parameter specifies the height above the baseline for uppercase character shapes. Cap-M height is the nominal height of the uppercase characters and is usually equal to the height of the uppercase letter M. [FOCA-5-026]

*   **Parameter type =** number [FOCA-5-027]
*   **Transformation to Eastern Writing systems:** This parameter should be set to the character box height for Eastern writing systems. [FOCA-5-028]
*   **Transformation to ISO/IEC 9541 font architecture:** This parameter corresponds to `capheight` (Capitol Height). It is expressed as a relative rational number. [FOCA-5-029]

### Character Rotation
The Character Rotation parameter specifies the rotation of the character box relative to the character baseline. Refer to “Units of Direction” for an explanation of character rotation. [FOCA-5-030]

*   **Parameter type =** number [FOCA-5-031]
*   **Synonyms =** font character rotation [FOCA-5-032]
*   **Transformation to Eastern Writing systems:** This parameter should be set to 0° or 270° for horizontal or vertical writing respectively. [FOCA-5-033]
*   **Transformation to ISO/IEC 9541 font architecture:** This parameter corresponds to `nomescdir` (nominal escapement direction), and to `nomwrmode` (Nominal Writing Mode). [FOCA-5-034]

### Comment
The Comment parameter allows the creator or the user of the font resource to make comments. The content of this parameter must be non-processable information and is ignored by any processing implementation. [FOCA-5-035]

*   **Parameter type =** character string [FOCA-5-036]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-037]
*   **Transformation to ISO/IEC 9541 font architecture:** This parameter can be expressed as a non-iso-property. [FOCA-5-038]

### Design General Class (ISO)
The Design General Class parameter specifies the ISO (International Standards Organization) Font Standard General Classification of the font family design. [FOCA-5-039]

*   **Parameter type =** number [FOCA-5-040]
*   **Synonyms =** design class [FOCA-5-041]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-042]
*   **Transformation to ISO/IEC 9541 font architecture:** This parameter corresponds to the class subfield of `dsngroup` (Design Group). The ISO property is a code in the range of 0 to 255. [FOCA-5-043]

### Design Specific Group (ISO)
The Design Specific Group parameter specifies the ISO Font Standard Specific Group of the font family design. [FOCA-5-044]

*   **Parameter type =** number [FOCA-5-045]
*   **Synonyms =** design class [FOCA-5-046]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-047]
*   **Transformation to ISO/IEC 9541 font architecture:** This parameter corresponds to the specific group subfield of `dsngroup` (Design Group). [FOCA-5-048]

### Design Subclass (ISO)
The Design Subclass parameter specifies the ISO Font Standard Subclass of the font family design. [FOCA-5-049]

*   **Parameter type =** number [FOCA-5-050]
*   **Synonyms =** design class [FOCA-5-051]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-052]
*   **Transformation to ISO/IEC 9541 font architecture:** This parameter corresponds to the subclass subfield of `dsngroup` (Design Group). [FOCA-5-053]

### Em-Space Increment
The Em-Space Increment parameter specifies typographic space that corresponds to the space between sentences. Traditionally equals the vertical font size. [FOCA-5-054]

*   **Parameter type =** number [FOCA-5-055]
*   **Synonyms =** em increment [FOCA-5-056]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern Writing systems. [FOCA-5-057]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to the difference between the px,py and the ex,ey values for the Em-space glyph. [FOCA-5-058]

### Extension Font
The Extension Font parameter indicates that this font resource was designed to be an extension (contains user-defined characters) to another font. [FOCA-5-059]

*   **Parameter type =** flag [FOCA-5-060]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-061]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property in the Font Description property list. [FOCA-5-062]

### Family Name
The Family Name parameter specifies the common name for a font design. A font family includes all typeface variations of the font design. [FOCA-5-063]

*   **Parameter type =** character string [FOCA-5-064]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-065]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `fontfamily`. [FOCA-5-066]

### Font Local Identifier
The Font Local Identifier parameter specifies a numeric identifier assigned temporarily to a font resource within the context of another object. [FOCA-5-067]

*   **Parameter type =** number [FOCA-5-068]
*   **Synonyms =** font local ID, font LID [FOCA-5-069]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-070]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property in the Font Description property list. [FOCA-5-071]

### Font Typeface Global Identifier
The Font Typeface Global Identifier parameter (usually called Font Global Identifier, FGID) specifies the unique number assigned to the font typeface. [FOCA-5-072]

*   **Parameter type =** number [FOCA-5-073]
*   **Synonyms =** font global identifier, FGID, typeface identifier [FOCA-5-074]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-075]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property in the Font Description property list. [FOCA-5-076]

### Font Use Code
The Font Use Code parameter specifies the intended use of the graphic characters in a font. [FOCA-5-077]

*   **Parameter type =** code [FOCA-5-078]
*   **Valid choices:** [FOCA-5-079]
    *   0: No font use intent [FOCA-5-080]
    *   1: Image symbol set for text in a graphics object [FOCA-5-081]
    *   3: Pattern symbol set in a graphics object [FOCA-5-082]
    *   4: Marker symbol set in a graphics object [FOCA-5-083]
    *   5: High resolution indicator in a graphics object [FOCA-5-084]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-085]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property in the Font Description property list. [FOCA-5-086]

### Graphic Character Set Global Identifier
The Graphic Character Set Global Identifier (GCSGID) parameter specifies the number assigned to a graphic character set. [FOCA-5-087]

*   **Parameter type =** number [FOCA-5-088]
*   **Synonyms =** graphic-character-set ID, character-set name [FOCA-5-089]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-090]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `incglyphcols` subfield of `glyphcomp`. [FOCA-5-091]

### Hollow Font
The Hollow Font parameter specifies that the graphic characters of the font appear with only the outer edges of the strokes. [FOCA-5-092]

*   **Parameter type =** flag [FOCA-5-093]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-094]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to the Outline code (2) of the `structure` property. [FOCA-5-095]

### Italics
The Italics parameter specifies that the graphic characters are designed with a clockwise incline. [FOCA-5-096]

*   **Parameter type =** flag [FOCA-5-097]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-098]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to the italic value (4) of the ISO `posture` property. [FOCA-5-099]

### Kerning Pair Data
The Kerning Pair Data parameter specifies that kerning pair data is available in the font resource. [FOCA-5-100]

*   **Parameter type =** flag [FOCA-5-101]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-102]
*   **Transformation to ISO/IEC 9541 font architecture:** Presence is indicated by the presence of the `peas` (Pairwise Escapement Adjusts) data itself. [FOCA-5-103]

### Maximum Horizontal Font Size
The Maximum Horizontal Font Size parameter specifies the maximum horizontal size for scaling, as specified by a font designer. [FOCA-5-104]

*   **Parameter type =** number [FOCA-5-105]
*   **Synonyms =** maximum character width, maximum horizontal point size [FOCA-5-106]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-107]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `maxanascale` (Maximum Anamorphic Scale). [FOCA-5-108]

### Maximum Vertical Font Size
The Maximum Vertical Font Size parameter specifies the maximum vertical size for scaling purposes as specified by a font designer. [FOCA-5-109]

*   **Parameter type =** number [FOCA-5-110]
*   **Synonyms =** maximum design size, maximum point size [FOCA-5-111]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-112]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `maxsize`. [FOCA-5-113]

### Measurement Units
The Measurement Units parameter specifies the unit base and units per unit base in both X and Y directions. [FOCA-5-114]

*   **Parameter type =** two codes and two numbers [FOCA-5-115]
*   **Valid unit-base code choices:** [FOCA-5-116]
    *   0: Ten inches [FOCA-5-117]
    *   1: Ten centimeters [FOCA-5-118]
    *   2: Relative units [FOCA-5-119]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-120]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `relunits`. [FOCA-5-121]

### MICR Font
The MICR Font parameter indicates that this font resource was designed for use in Magnetic Ink Character Recognition (MICR) applications. [FOCA-5-122]

*   **Parameter type =** flag [FOCA-5-123]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-124]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-125]

### Minimum Horizontal Font Size
The Minimum Horizontal Font Size parameter specifies the minimum horizontal size for scaling, as specified by a font designer. [FOCA-5-126]

*   **Parameter type =** number [FOCA-5-127]
*   **Synonyms =** minimum character width, minimum anamorphic scaling [FOCA-5-128]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-129]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `minanascale`. [FOCA-5-130]

### Minimum Vertical Font Size
The Minimum Vertical Font Size parameter specifies the minimum vertical size for scaling purposes as specified by a font designer. [FOCA-5-131]

*   **Parameter type =** number [FOCA-5-132]
*   **Synonyms =** minimum point size, minimum size [FOCA-5-133]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-134]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `minsize`. [FOCA-5-135]

### Nominal Character Slope
The Nominal Character Slope parameter specifies the slope (stem incline) of the main strokes. Measured clockwise from vertical. [FOCA-5-136]

*   **Parameter type =** number [FOCA-5-137]
*   **Synonyms =** character slope [FOCA-5-138]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-139]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `postureangle`. [FOCA-5-140]

### Nominal Horizontal Font Size
The Nominal Horizontal Font Size parameter specifies the nominal horizontal size for scaling. Corresponds to the escapement of the space character (SP010000). [FOCA-5-141]

*   **Parameter type =** number [FOCA-5-142]
*   **Synonyms =** horizontal font size, nominal horizontal point size [FOCA-5-143]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-144]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-145]

### Nominal Vertical Font Size
The Nominal Vertical Font Size parameter specifies the vertical design size of the font. [FOCA-5-146]

*   **Parameter type =** number [FOCA-5-147]
*   **Synonyms =** nominal point size, design size [FOCA-5-148]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-149]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `dsnsize`. [FOCA-5-150]

### Overstruck Font
The Overstruck Font parameter specifies that the graphic characters of the font appear as though over-struck by another graphic character (often a hyphen). [FOCA-5-151]

*   **Parameter type =** flag [FOCA-5-152]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-153]
*   **Transformation to ISO/IEC 9541 font architecture:** Represented by a unique font family name or non-iso-property. [FOCA-5-154]

### Proportional Spaced
The Proportional Spaced parameter specifies that the character increments for each graphic character in the font resource might vary. [FOCA-5-155]

*   **Parameter type =** flag [FOCA-5-156]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-157]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to the proportional value (2) of `escclass`. [FOCA-5-158]

### Private Use
The Private Use parameter specifies that some or all of the data is privately owned or protected by a licensing agreement. [FOCA-5-159]

*   **Parameter type =** flag [FOCA-5-160]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-161]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-162]

### Resource Name
The Resource Name parameter identifies a resource object by a character string name. [FOCA-5-163]

*   **Parameter type =** character string [FOCA-5-164]
*   **Synonyms =** resource tag [FOCA-5-165]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-166]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `fontname`. [FOCA-5-167]

### Specified Horizontal Font Size
The Specified Horizontal Font Size parameter specifies the horizontal font size indicated by the document creator. [FOCA-5-168]

*   **Parameter type =** number [FOCA-5-169]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-170]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-171]

### Specified Horizontal Scale Factor
The Specified Horizontal Scale Factor parameter specifies uniform or anamorphic scaling. Numerator in a ratio (Scale Factor / Vertical Size). [FOCA-5-172]

*   **Parameter type =** number [FOCA-5-173]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-174]
*   **Transformation to ISO/IEC 9541 font architecture:** No equivalent exists in resource architecture; appears only in data stream references. [FOCA-5-175]

### Specified Vertical Font Size
The Specified Vertical Font Size parameter specifies the vertical font size indicated by the document creator. [FOCA-5-176]

*   **Parameter type =** number [FOCA-5-177]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-178]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `dsnsize`. [FOCA-5-179]

### Transformable Font
The Transformable Font parameter specifies that the pattern data allows algorithmic transformation (e.g., scaling). [FOCA-5-180]

*   **Parameter type =** flag [FOCA-5-181]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-182]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-183]

### Typeface Name
The Typeface Name parameter specifies the common name of the typeface (e.g., Sonoran Sans Serif Roman bold condensed). [FOCA-5-184]

*   **Parameter type =** character string [FOCA-5-185]
*   **Synonyms =** facename [FOCA-5-186]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-187]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `typeface`. [FOCA-5-188]

### Underscored Font
The Underscored Font parameter specifies that the graphic character pattern data contains underscores as part of the shape. [FOCA-5-189]

*   **Parameter type =** flag [FOCA-5-190]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-191]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-192]

### Uniform Character Box Font
The Uniform Character Box Font parameter specifies that all raster patterns are of the same size. [FOCA-5-193]

*   **Parameter type =** flag [FOCA-5-194]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-195]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-196]

### Weight Class
The Weight Class parameter indicates the visual weight (degree or thickness of strokes). [FOCA-5-197]

*   **Parameter type =** code [FOCA-5-198]
*   **Valid choices:** [FOCA-5-199]
    *   1: Ultralight [FOCA-5-200]
    *   2: Extralight [FOCA-5-201]
    *   3: Light [FOCA-5-202]
    *   4: Semilight [FOCA-5-203]
    *   5: Medium (normal) [FOCA-5-204]
    *   6: Semibold [FOCA-5-205]
    *   7: Bold [FOCA-5-206]
    *   8: Extrabold [FOCA-5-207]
    *   9: Ultrabold [FOCA-5-208]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-209]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `weight`. [FOCA-5-210]

### Width Class
The Width Class parameter indicates a relative change from the normal aspect ratio. [FOCA-5-211]

*   **Parameter type =** code [FOCA-5-212]
*   **Valid choices:** [FOCA-5-213]
    *   1: Ultracondensed (50%) [FOCA-5-214]
    *   2: Extracondensed (62.5%) [FOCA-5-215]
    *   3: Condensed (75%) [FOCA-5-216]
    *   4: Semicondensed (87.5%) [FOCA-5-217]
    *   5: Normal (100%) [FOCA-5-218]
    *   6: Semiexpanded (112.5%) [FOCA-5-219]
    *   7: Expanded (125%) [FOCA-5-220]
    *   8: Extraexpanded (150%) [FOCA-5-221]
    *   9: Ultraexpanded (200%) [FOCA-5-222]
*   **Transformation to Eastern Writing systems:** This parameter is writing-system independent. [FOCA-5-223]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `propwidth`. [FOCA-5-224]

### X-Height
The X-Height parameter specifies the height of the body of lowercase graphic characters (usually height of 'x'). [FOCA-5-225]

*   **Parameter type =** number [FOCA-5-226]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-227]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `lcheight`. [FOCA-5-228]

---

## Font-Metric Parameters

These parameters apply to all of the font characters and are repeated for each supported character rotation. [FOCA-5-229]

### Default Baseline Increment
The Default Baseline Increment parameter specifies the nominal distance between character reference points in the vertical direction recommended by the designer. [FOCA-5-230]

*   **Parameter type =** number [FOCA-5-231]
*   **Transformation to Eastern Writing systems:** For vertical writing, distance between baselines of two columns. [FOCA-5-232]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `minlinesp`. [FOCA-5-233]

### External Leading
The External Leading parameter specifies recommended additional interline white space. [FOCA-5-234]

*   **Parameter type =** number [FOCA-5-235]
*   **Transformation to Eastern Writing systems:** Supplemental value for extending distance between baselines. [FOCA-5-236]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-237]

### Figure Space Increment
The Figure Space Increment parameter specifies the character increment used for numerals. [FOCA-5-238]

*   **Parameter type =** number [FOCA-5-239]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern Writing systems. [FOCA-5-240]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `tabescx` and `tabescy`. [FOCA-5-241]

### Internal Leading
The Internal Leading parameter specifies nominal white space above and below characters. [FOCA-5-242]

*   **Parameter type =** number [FOCA-5-243]
*   **Transformation to Eastern Writing systems:** Difference between Nominal Horizontal Size and Max Baseline Extent for 270° rotation. [FOCA-5-244]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-245]

### Kerning
The Kerning parameter is a flag that indicates whether any of the metrics contain negative values allowing kerning. [FOCA-5-246]

*   **Parameter type =** flag [FOCA-5-247]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-248]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-249]

### Kerning Pair Character 1
The Kerning Pair Character 1 parameter specifies the first character in a kerning pair. [FOCA-5-250]

*   **Parameter type =** character string [FOCA-5-251]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-252]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `gname`. [FOCA-5-253]

### Kerning Pair Character 2
The Kerning Pair Character 2 parameter specifies the second character in a kerning pair. [FOCA-5-254]

*   **Parameter type =** character string [FOCA-5-255]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-256]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `gname` component of `peax` / `peay`. [FOCA-5-257]

### Kerning Pair X-Adjust
The Kerning Pair X-Adjust parameter specifies the escapement adjustment in the x direction for the specified pair. [FOCA-5-258]

*   **Parameter type =** number [FOCA-5-259]
*   **Synonyms =** pairwise escapement x-adjust [FOCA-5-260]
*   **Transformation to Eastern Writing systems:** This parameter does not apply to Eastern writing systems. [FOCA-5-261]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to the adjustment component of `peax`. [FOCA-5-262]

### Maximum Ascender Height
The Maximum Ascender Height parameter specifies the maximum height from the baseline to the top of any character box. [FOCA-5-263]

*   **Parameter type =** number [FOCA-5-264]
*   **Transformation to Eastern Writing systems:** Max distance from baseline to right-hand edge of character box for vertical writing. [FOCA-5-265]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to maximum x or y value of `maxfontext`. [FOCA-5-266]

### Maximum Baseline Extent
The Maximum Baseline Extent parameter specifies the space parallel to the baseline required to place characters. Sum of max baseline offset and max descender depth. [FOCA-5-267]

*   **Parameter type =** number [FOCA-5-268]
*   **Transformation to Eastern Writing systems:** Max Ascender Height plus Max Descender Depth. [FOCA-5-269]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-270]

### Maximum Baseline Offset
The Maximum Baseline Offset parameter specifies the maximum distance from the baseline to the upper edge of the character box. [FOCA-5-271]

*   **Parameter type =** number [FOCA-5-272]
*   **Transformation to Eastern Writing systems:** Equal to Maximum Ascender Height. [FOCA-5-273]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-274]

### Maximum Character Box Height
The Maximum Character Box Height parameter specifies the height of uniform boxes or max height of variable boxes. [FOCA-5-275]

*   **Parameter type =** number [FOCA-5-276]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-277]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-278]

### Maximum Character Box Width
The Maximum Character Box Width parameter specifies the width of uniform boxes or max width of variable boxes. [FOCA-5-279]

*   **Parameter type =** number [FOCA-5-280]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-281]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-282]

### Maximum Character Increment
The Maximum Character Increment parameter specifies the maximum increment for all characters in a font rotation. [FOCA-5-283]

*   **Parameter type =** number [FOCA-5-284]
*   **Transformation to Eastern Writing systems:** Max increment for 270° rotation metrics. [FOCA-5-285]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-286]

### Maximum Descender Depth
The Maximum Descender Depth parameter specifies the maximum depth from the baseline to the bottom of any character box. [FOCA-5-287]

*   **Parameter type =** number [FOCA-5-288]
*   **Transformation to Eastern Writing systems:** Max distance from baseline to left-hand edge of box for vertical writing. [FOCA-5-289]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to minimum x or y value of `maxfontext`. [FOCA-5-290]

### Maximum Lowercase Ascender Height
The Maximum Lowercase Ascender Height parameter specifies the max ascender height of lowercase (a–z) at 0° rotation. [FOCA-5-291]

*   **Parameter type =** number [FOCA-5-292]
*   **Transformation to Eastern Writing systems:** This parameter does not apply. [FOCA-5-293]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-294]

### Maximum Lowercase Descender Depth
The Maximum Lowercase Descender Depth parameter specifies the max descender depth of lowercase (a–z) at 0° rotation. [FOCA-5-295]

*   **Parameter type =** number [FOCA-5-296]
*   **Transformation to Eastern Writing systems:** This parameter does not apply. [FOCA-5-297]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-298]

### Maximum V(y)
The Maximum V(y) parameter is the maximum of all Adobe ATM V(y) values (y coordinate of distance from origin to positioning point). [FOCA-5-299]

*   **Parameter type =** number [FOCA-5-300]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-301]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to maximum of all `P(y)` values. [FOCA-5-302]

### Maximum W(y)
The Maximum W(y) parameter is the maximum of all Adobe ATM W(y) values (y coordinate of distance from positioning point to escapement point). [FOCA-5-303]

*   **Parameter type =** number [FOCA-5-304]
*   **Synonyms =** Vertical Character Increment [FOCA-5-305]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-306]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to maximum absolute magnitude of all `(P(y)-E(y))` values. [FOCA-5-307]

### Minimum A-space
The Minimum A-space parameter specifies the smallest A-space (most negative or least positive) value. [FOCA-5-308]

*   **Parameter type =** number [FOCA-5-309]
*   **Transformation to Eastern Writing systems:** Least A-space for 270° rotation metrics. [FOCA-5-310]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-311]

### Nominal Character Increment
The Nominal Character Increment parameter specifies the most commonly repeated character increment. [FOCA-5-312]

*   **Parameter type =** number [FOCA-5-313]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-314]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-315]

### Space Character Increment
The Space Character Increment parameter specifies the default character increment for the space character. [FOCA-5-316]

*   **Parameter type =** number [FOCA-5-317]
*   **Transformation to Eastern Writing systems:** Space increment for 270° rotation. [FOCA-5-318]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to difference between `px,py` and `ex,ey` for Normal Space. [FOCA-5-319]

### Subscript Vertical Font Size
The Subscript Vertical Font Size parameter specifies designer's recommended size for subscript characters. [FOCA-5-320]

*   **Parameter type =** number [FOCA-5-321]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-322]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `vsscaley`. [FOCA-5-323]

### Subscript X-Axis Offset
The Subscript X-Axis Offset parameter specifies recommended vertical offset from baseline to subscript baseline. [FOCA-5-324]

*   **Parameter type =** number [FOCA-5-325]
*   **Transformation to Eastern Writing systems:** Does not apply. [FOCA-5-326]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `vsoffsetx`. [FOCA-5-327]

### Superscript Vertical Font Size
The Superscript Vertical Font Size parameter specifies designer's recommended size for superscript characters. [FOCA-5-328]

*   **Parameter type =** number [FOCA-5-329]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-330]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `vsscaley`. [FOCA-5-331]

### Superscript X-Axis Offset
The Superscript X-Axis Offset parameter specifies recommended vertical offset from baseline to superscript baseline. [FOCA-5-332]

*   **Parameter type =** number [FOCA-5-333]
*   **Transformation to Eastern Writing systems:** Does not apply. [FOCA-5-334]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `vsoffsetx`. [FOCA-5-335]

### Throughscore Position
The Throughscore Position parameter specifies recommendation for drawing throughscores (perpendicular distance from baseline to top of stroke). [FOCA-5-336]

*   **Parameter type =** number [FOCA-5-337]
*   **Transformation to Eastern Writing systems:** Distance between center line and vertical score line. [FOCA-5-338]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `scoreoffsetx` / `scoreoffsety`. [FOCA-5-339]

### Throughscore Width
The Throughscore Width parameter specifies recommended width (thickness) of the throughscore. [FOCA-5-340]

*   **Parameter type =** number [FOCA-5-341]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-342]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `scorethick`. [FOCA-5-343]

### Underscore Position
The Underscore Position parameter specifies recommendation for drawing underscores (distance from baseline to top of stroke). [FOCA-5-344]

*   **Parameter type =** number [FOCA-5-345]
*   **Transformation to Eastern Writing systems:** Distance between center line and vertical score line running to the left. [FOCA-5-346]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `scoreoffsetx` / `scoreoffsety` (`RIGHTSCORE`). [FOCA-5-347]

### Underscore Width
The Underscore Width parameter specifies recommended width (thickness) of underscores. [FOCA-5-348]

*   **Parameter type =** number [FOCA-5-349]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-350]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `scorethick`. [FOCA-5-351]

### Uniform A-space
The Uniform A-space parameter specifies that a uniform amount of A-space was removed from all patterns. [FOCA-5-352]

*   **Parameter type =** flag [FOCA-5-353]
*   **Transformation to Eastern Writing systems:** A-space for 270° rotation metrics. [FOCA-5-354]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-355]

### Uniform Baseline Offset
The Uniform Baseline Offset parameter specifies that all patterns have a common baseline offset. [FOCA-5-356]

*   **Parameter type =** flag [FOCA-5-357]
*   **Transformation to Eastern Writing systems:** Uniform Baseline Offset for 270° rotation metrics. [FOCA-5-358]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-359]

### Uniform Character Increment
The Uniform Character Increment parameter specifies that all increments are the same. [FOCA-5-360]

*   **Parameter type =** flag [FOCA-5-361]
*   **Transformation to Eastern Writing systems:** Uniform Character Increment for 270° rotation metrics. [FOCA-5-362]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-363]

---

## Character-Metric Parameters

These parameters apply to individual characters and are repeated for each character in each rotation. [FOCA-5-364]

### A-space
Distance from character reference point to the least positive character coordinate system x-axis value. [FOCA-5-365]

*   **Parameter type =** number [FOCA-5-366]
*   **Transformation to Eastern Writing systems:** Distance between reference point and least positive x-axis value of 270° shape. [FOCA-5-367]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to distance between `px,py` and closest `ext` property value. [FOCA-5-368]

### Ascender Height
Height of the topmost mark of a graphic character relative to the baseline. [FOCA-5-369]

*   **Parameter type =** number [FOCA-5-370]
*   **Transformation to Eastern Writing systems:** Distance between reference point and most positive y-axis value of 270° shape. [FOCA-5-371]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to max y (0° rotation) or max x (270° rotation). [FOCA-5-372]

### Baseline Offset
Distance from character baseline to the topmost edge of a character box. [FOCA-5-373]

*   **Parameter type =** number [FOCA-5-374]
*   **Transformation to Eastern Writing systems:** Equal to Ascender Height. [FOCA-5-375]
*   **Transformation to ISO/IEC 9541 font architecture:** Derived from `ext` (Extents) property. [FOCA-5-376]

### B-space
Width of the (bounded) character box. [FOCA-5-377]

*   **Parameter type =** number [FOCA-5-378]
*   **Transformation to Eastern Writing systems:** Distance between most/least positive x-axis value of 270° shape. [FOCA-5-379]
*   **Transformation to ISO/IEC 9541 font architecture:** Derived from `minx`/`maxx` or `miny`/`maxy`. [FOCA-5-380]

### Character Box Height
Height of the character box for a graphic character. [FOCA-5-381]

*   **Parameter type =** number [FOCA-5-382]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-383]
*   **Transformation to ISO/IEC 9541 font architecture:** Derived from difference between `miny` and `maxy`. [FOCA-5-384]

### Character Box Width
Width of the character box for a character. [FOCA-5-385]

*   **Parameter type =** number [FOCA-5-386]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-387]
*   **Transformation to ISO/IEC 9541 font architecture:** Derived from difference between `minx` and `maxx`. [FOCA-5-388]

### Character Increment
Algebraic sum of A-space, B-space, and C-space. [FOCA-5-389]

*   **Parameter type =** number [FOCA-5-390]
*   **Transformation to Eastern Writing systems:** Distance between reference point and escapement point for 270° shape. [FOCA-5-391]
*   **Transformation to ISO/IEC 9541 font architecture:** Derived from difference between `px,py` and `ex,ey`. [FOCA-5-392]

### C-space
Width of the space from the bounded character box to the escapement point. [FOCA-5-393]

*   **Parameter type =** number [FOCA-5-394]
*   **Transformation to Eastern Writing systems:** Distance between escapement point and least positive x-axis value of 270° shape. [FOCA-5-395]
*   **Transformation to ISO/IEC 9541 font architecture:** Derived from ISO glyph properties. [FOCA-5-396]

### Descender Depth
Distance from baseline to bottom of character box. [FOCA-5-397]

*   **Parameter type =** number [FOCA-5-398]
*   **Transformation to Eastern Writing systems:** Distance between reference point and most negative y-axis value of 270° shape. [FOCA-5-399]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `miny` (0° rotation) or `minx` (270° rotation). [FOCA-5-400]

### Graphic Character Global Identifier
Registered identifier of a graphic character. Default encoding is EBCDIC, length is 8 bytes. [FOCA-5-401]

*   **Parameter type =** character string [FOCA-5-402]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-403]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `gname`. [FOCA-5-404]

---

## Character-Shape Parameters

Required for presentation of the character shapes. Repeated for each technology supported. [FOCA-5-405]

### Design Resolution X
Intended presentation resolution in the x direction. [FOCA-5-406]

*   **Parameter type =** number [FOCA-5-407]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-408]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-409]

### Design Resolution Y
Intended presentation resolution in the y direction. [FOCA-5-410]

*   **Parameter type =** number [FOCA-5-411]
*   **Transformation to Eastern Writing systems:** Writing-system independent. [FOCA-5-412]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-413]

### Linkage Code
Specifies whether character IDs in CMAP are linked to Name Map. [FOCA-5-414]

*   **Parameter type =** code [FOCA-5-415]
*   **Valid Choices:** [FOCA-5-416]
    *   0: Linked [FOCA-5-417]
    *   1: Unlinked [FOCA-5-418]
*   **Transformation to Eastern Writing systems:** Primarily used with eastern writing systems. [FOCA-5-419]
*   **Transformation to ISO/IEC 9541 font architecture:** No equivalent. [FOCA-5-420]

### Object Type
Identifies various objects embedded in a font resource (e.g., CMAP, CID, PFB). [FOCA-5-421]

*   **Parameter type =** code [FOCA-5-422]
*   **Valid Choices:** [FOCA-5-423]
    *   0: No information [FOCA-5-424]
    *   1: CMAP file [FOCA-5-425]
    *   5: CID file [FOCA-5-426]
    *   6: PFB file [FOCA-5-427]
    *   7: AFM file [FOCA-5-428]
    *   8: File Name Map [FOCA-5-429]
*   **Transformation to ISO/IEC 9541 font architecture:** No equivalent. [FOCA-5-430]

### Pattern Data
The actual pattern data for the shape representation technique. [FOCA-5-431]

*   **Parameter type =** undefined data [FOCA-5-432]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-433]

### Pattern Data Alignment Code
Specifies alignment of beginning of each character's pattern data (exponent of base 2). [FOCA-5-434]

*   **Parameter type =** code [FOCA-5-435]
*   **Valid choices:** [FOCA-5-436]
    *   0: One-byte Alignment [FOCA-5-437]
    *   1: Two-byte Alignment [FOCA-5-438]
    *   2: Four-byte Alignment [FOCA-5-439]
    *   3: Eight-byte Alignment [FOCA-5-440]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-441]

### Pattern Data Alignment Value
Byte alignment multiplier of the Pattern Data Offset. [FOCA-5-442]

*   **Parameter type =** number [FOCA-5-443]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-444]

### Pattern Data Count
Total quantity of shape data in bytes. [FOCA-5-445]

*   **Parameter type =** number [FOCA-5-446]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-447]

### Pattern Data Offset
Used with Alignment Code to calculate actual byte offset. [FOCA-5-448]

*   **Parameter type =** number [FOCA-5-449]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-450]

### Pattern Technology Identifier
Specifies the technologies for font graphic patterns. [FOCA-5-451]

*   **Parameter type =** code [FOCA-5-452]
*   **Valid choices:** [FOCA-5-453]
    *   5: Laser Matrix N-Bit Wide Horizontal Sections [FOCA-5-454]
    *   30: CID Keyed Outline Font Technology [FOCA-5-455]
    *   31: Type 1 PFB Outline Font Technology [FOCA-5-456]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-457]

### Precedence Code
Specifies if an object is primary or auxiliary. [FOCA-5-458]

*   **Parameter type =** code [FOCA-5-459]
*   **Valid Choices:** [FOCA-5-460]
    *   0: Primary [FOCA-5-461]
    *   1: Auxiliary [FOCA-5-462]
*   **Transformation to ISO/IEC 9541 font architecture:** No equivalent. [FOCA-5-463]

### Shape Pattern Index
Index into Pattern Data Offset repeating group. [FOCA-5-464]

*   **Parameter type =** number [FOCA-5-465]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-466]

### Writing Direction Code
Nominal direction in which characters are written or read. [FOCA-5-467]

*   **Parameter type =** code [FOCA-5-468]
*   **Valid Choices:** [FOCA-5-469]
    *   0: No information [FOCA-5-470]
    *   1: Horizontal [FOCA-5-471]
    *   2: Vertical [FOCA-5-472]
    *   3: Both [FOCA-5-473]
*   **Transformation to ISO/IEC 9541 font architecture:** Corresponds to `Writing Mode`. [FOCA-5-474]

---

## Character-Mapping Parameters

Used to map code points to graphic character identifiers. [FOCA-5-475]

### Code Page Description
Descriptive title or short description of the code page. [FOCA-5-476]

*   **Parameter type =** character string [FOCA-5-477]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-478]

### Code Page Global Identifier
Number assigned to a code page (CPGID). [FOCA-5-479]

*   **Parameter type =** number [FOCA-5-480]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-481]

### Code Point
Value of the integer assigned to a graphic character in a list. [FOCA-5-482]

*   **Parameter type =** number [FOCA-5-483]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-484]

### Encoding Scheme
Identifies the scheme used to code character data (e.g., EBCDIC, ASCII, UCS). [FOCA-5-485]

*   **Parameter type =** code [FOCA-5-486]
*   **Valid choices (Basic structure):** [FOCA-5-487]
    *   0: No specified organization [FOCA-5-488]
    *   2: IBM-PC Data [FOCA-5-489]
    *   6: EBCDIC Presentation [FOCA-5-490]
    *   8: UCS Presentation [FOCA-5-491]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-492]

### Graphic Character Identifier Type
Identifies the naming source and method used to identify graphic characters. [FOCA-5-493]

*   **Parameter type =** code [FOCA-5-494]
*   **Valid choices:** [FOCA-5-495]
    *   2: IBM Registered EBCDIC GCGID [FOCA-5-496]
    *   3: Font Specific ASCII Character Name [FOCA-5-497]
    *   5: CMap Binary Code Point [FOCA-5-498]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-499]

### Graphic Character GID Length
Specifies the length of the graphic character identifier (default 8 bytes). [FOCA-5-500]

*   **Parameter type =** number [FOCA-5-501]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-502]

### Invalid Coded Character
Specifies that the character is not valid and should not be used. [FOCA-5-503]

*   **Parameter type =** flag [FOCA-5-504]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-505]

### No Increment
Specifies that the character increment should be ignored. [FOCA-5-506]

*   **Parameter type =** flag [FOCA-5-507]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-508]

### No Presentation
Specifies that the character should not be presented. [FOCA-5-509]

*   **Parameter type =** flag [FOCA-5-510]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-511]

### Number of Coded Graphic Characters Assigned
Number of assigned code points in a code page. [FOCA-5-512]

*   **Parameter type =** number [FOCA-5-513]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-514]

### Section Number
Specifies the section number of a multibyte code page (first byte of two-byte character). [FOCA-5-515]

*   **Parameter type =** number [FOCA-5-516]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-517]

### Space Character Code Point
Code point assigned as the space character. [FOCA-5-518]

*   **Parameter type =** number [FOCA-5-519]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-520]

### Space Character Section Number
Section number for the space character code point. [FOCA-5-521]

*   **Parameter type =** number [FOCA-5-522]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-523]

### Unspecified Coded Character Identifier
Identifier used whenever a font doesn't contain info for a GCGID associated with a code point. [FOCA-5-524]

*   **Parameter type =** character string (named GCGID) [FOCA-5-525]
*   **Transformation to ISO/IEC 9541 font architecture:** Expressed as a non-iso-property. [FOCA-5-526]
