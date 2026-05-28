# Appendix E. Cross-References
This appendix provides tables that list:
• MO:DCA structured fields sorted by identifier [MODCA-E-001]
• MO:DCA structured fields sorted by acronym [MODCA-E-002]
• MO:DCA triplets sorted by identifier [MODCA-E-003]
• MO:DCA triplets sorted by name [MODCA-E-004]
Note: The MO:DCA architecture serves as a central registry for MO:DCA-like structures, such as structured
fields and triplets, that are used in other AFP architectures, such as the Font Object Content
Architecture (FOCA), the AFP Line Data Architecture, and the Intelligent Printer Data Stream (IPDS)
Architecture. While the IDs of these structures are registered in the MO:DCA architecture and their
syntax is based on the MO:DCA syntax, these structures are formally defined in the documents that
define these respective architectures, that is, the Font Object Content Architecture Reference, the
Advanced Function Presentation: Programming Guide and Line Data Reference, and the Intelligent
Printer Data Stream Reference. Therefore these IDs are not listed as MO:DCA structured fields and
triplets in this appendix; for more information on these structures, consult the referenced architecture
documents.
Architecture Note: The MO:DCA-L format is no longer documented in the MO:DCA reference; therefore
MO:DCA-L structured fields are no longer included in the following tables. For a definition of the
MO:DCA-L format, see the document MO:DCA-L: The OS/2 Presentation Manager Metafile (.met)
Format, available at www.afpcinc.org.
MO:DCA Structured Fields Sorted by Identifier
Table 53. Structured Fields Sorted by ID
Identifier Acronym Structured Field Name Page
X'D3A088' MFC Medium Finishing Control 265
X'D3A090' TLE T ag Logical Element 342
X'D3A288' MCC Medium Copy Count 233
X'D3A66B' OBD Object Area Descriptor 300
X'D3A67B' IID IM Image Input Descriptor (C) 603
X'D3A688' MDD Medium Descriptor 244
X'D3A692' CDD Container Data Descriptor 170
X'D3A69B' PTD-1 Presentation T ext Descriptor Format-1 (C) 600
X'D3A6AF' PGD Page Descriptor 310
X'D3A6BB' GDD Graphics Data Descriptor 195
X'D3A6C5' FGD Form Environment Group Descriptor (O) 555
X'D3A6EB' BDD Bar Code Data Descriptor 124
X'D3A6FB' IDD Image Data Descriptor 196
X'D3A77B' IOC IM Image Output Control (C) 605
X'D3A788' MMC Medium Modification Control 276 [MODCA-E-005]


Table 53 Structured Fields Sorted by ID (cont'd.)
Identifier Acronym Structured Field Name Page
X'D3A79B' CTC Composed T ext Control (O) 554
X'D3A7A8' PEC Presentation Environment Control 306
X'D3A7AF' PMC Page Modification Control 327
X'D3A85F' BPS Begin Page Segment 155
X'D3A87B' BII Begin IM Image (C) 601
X'D3A892' BOC Begin Object Container 144
X'D3A89B' BPT Begin Presentation T ext Object 157
X'D3A8A5' BPF Begin Print File 150
X'D3A8A7' BDI Begin Document Index 126
X'D3A8A8' BDT Begin Document 128
X'D3A8AD' BNG Begin Named Page Group 140
X'D3A8AF' BPG Begin Page 152
X'D3A8BB' BGR Begin Graphics Object 132
X'D3A8C4' BDG Begin Document Environment Group 125
X'D3A8C5' BFG Begin Form Environment Group (O) 554
X'D3A8C6' BRG Begin Resource Group 159
X'D3A8C7' BOG Begin Object Environment Group 149
X'D3A8C9' BAG Begin Active Environment Group 120
X'D3A8CC' BMM Begin Medium Map 136
X'D3A8CD' BFM Begin Form Map 131
X'D3A8CE' BRS Begin Resource 161
X'D3A8D9' BSG Begin Resource Environment Group 169
X'D3A8DF' BMO Begin Overlay 138
X'D3A8EB' BBC Begin Bar Code Object 121
X'D3A8FB' BIM Begin Image Object 134
X'D3A95F' EPS End Page Segment 189
X'D3A97B' EII End IM Image (C) 602
X'D3A992' EOC End Object Container 185
X'D3A99B' EPT End Presentation T ext Object 190
X'D3A9A5' EPF End Print File 187
X'D3A9A7' EDI End Document Index 176
X'D3A9A8' EDT End Document 177
X'D3A9AD' ENG End Named Page Group 183
X'D3A9AF' EPG End Page 188
X'D3A9BB' EGR End Graphics Object 179
Cross-References


Table 53 Structured Fields Sorted by ID (cont'd.)
Identifier Acronym Structured Field Name Page
X'D3A9C4' EDG End Document Environment Group 175
X'D3A9C5' EFG End Form Environment Group (O) 555
X'D3A9C6' ERG End Resource Group 191
X'D3A9C7' EOG End Object Environment Group 186
X'D3A9C9' EAG End Active Environment Group 173
X'D3A9CC' EMM End Medium Map 181
X'D3A9CD' EFM End Form Map 178
X'D3A9CE' ERS End Resource 192
X'D3A9D9' ESG End Resource Environment Group 193
X'D3A9DF' EMO End Overlay 182
X'D3A9EB' EBC End Bar Code Object 174
X'D3A9FB' EIM End Image Object 180
X'D3AB88' MMT Map Media Type 289
X'D3AB8A' MCF Map Coded Font 237
X'D3AB92' MCD Map Container Data 235
X'D3AB9B' MPT Map Presentation T ext 297
X'D3ABAF' MPG Map Page 292
X'D3ABBB' MGO Map Graphics Object 273
X'D3ABC3' MDR Map Data Resource 246
X'D3ABCC' IMM Invoke Medium Map 199
X'D3ABCD' MMD Map Media Destination 286
X'D3ABD8' MPO Map Page Overlay 294
X'D3ABEA' MSU Map Suppression 298
X'D3ABEB' MBC Map Bar Code Object 232
X'D3ABFB' MIO Map Image Object 274
X'D3AC6B' OBP Object Area Position 302
X'D3AC7B' ICP IM Image Cell Position (C) 602
X'D3ACAF' PGP-1 Page Position Format-1 (C) 600
X'D3ADC3' PPO Preprocess Presentation Object 329
X'D3AF5F' IPS Include Page Segment 224
X'D3AFAF' IPG Include Page 219
X'D3AFC3' IOB Include Object 201
X'D3AFD8' IPO Include Page Overlay 222
X'D3B15F' MPS Map Page Segment 296
Cross-References


Table 53 Structured Fields Sorted by ID (cont'd.)
Identifier Acronym Structured Field Name Page
X'D3B18A' MCF-1 Map Coded Font Format-1 (C) 598
X'D3B19B' PTD Presentation T ext Data Descriptor 340
X'D3B1AF' PGP Page Position 313
X'D3B1DF' MMO Map Medium Overlay 288
X'D3B288' PFC Presentation Fidelity Control 308
X'D3B2A7' IEL Index Element 197
X'D3B490' LLE Link Logical Element 226
X'D3EE7B' IRD IM Image Raster Data (C) 607
X'D3EE92' OCD Object Container Data 305
X'D3EE9B' PTX Presentation T ext Data 341
X'D3EEBB' GAD Graphics Data 194
X'D3EEEB' BDA Bar Code Data 123
X'D3EEEE' NOP No Operation 299
X'D3EEFB' IPD Image Picture Data 218
Key:
O Obsolete
R Retired
C Coexistence
Cross-References


MO:DCA Structured Fields Sorted by Acronym
Table 54. Structured Fields Sorted by Acronym
Acronym Identifier Structured Field Name Page
BAG X'D3A8C9' Begin Active Environment Group 120
BBC X'D3A8EB' Begin Bar Code Object 121
BDA X'D3EEEB' Bar Code Data 123
BDD X'D3A6EB' Bar Code Data Descriptor 124
BDG X'D3A8C4' Begin Document Environment Group 125
BDI X'D3A8A7' Begin Document Index 126
BDT X'D3A8A8' Begin Document 128
BFG X'D3A8C5' Begin Form Environment Group (O) 554
BFM X'D3A8CD' Begin Form Map 131
BGR X'D3A8BB' Begin Graphics Object 132
BII X'D3A87B' Begin IM Image (C) 601
BIM X'D3A8FB' Begin Image Object 134
BMM X'D3A8CC' Begin Medium Map 136
BMO X'D3A8DF' Begin Overlay 138
BNG X'D3A8AD' Begin Named Page Group 140
BOC X'D3A892' Begin Object Container 144
BOG X'D3A8C7' Begin Object Environment Group 149
BPF X'D3A8A5' Begin Print File 150
BPG X'D3A8AF' Begin Page 152
BPS X'D3A85F' Begin Page Segment 155
BPT X'D3A89B' Begin Presentation T ext Object 157
BRG X'D3A8C6' Begin Resource Group 159
BRS X'D3A8CE' Begin Resource 161
BSG X'D3A8D9' Begin Resource Environment Group 169
CDD X'D3A692' Container Data Descriptor 170
CTC X'D3A79B' Composed T ext Control (O) 554
EAG X'D3A9C9' End Active Environment Group 173
EBC X'D3A9EB' End Bar Code Object 174
EDG X'D3A9C4' End Document Environment Group 175
EDI X'D3A9A7' End Document Index 176
EDT X'D3A9A8' End Document 177
EFG X'D3A9C5' End Form Environment Group (O) 555
Cross-References


Table 54 Structured Fields Sorted by Acronym (cont'd.)
Acronym Identifier Structured Field Name Page
EFM X'D3A9CD' End Form Map 178
EGR X'D3A9BB' End Graphics Object 179
EII X'D3A97B' End IM Image (C) 602
EIM X'D3A9FB' End Image Object 180
EMM X'D3A9CC' End Medium Map 181
EMO X'D3A9DF' End Overlay 182
ENG X'D3A9AD' End Named Page Group 183
EOC X'D3A992' End Object Container 185
EOG X'D3A9C7' End Object Environment Group 186
EPF X'D3A9A5' End Print File 187
EPG X'D3A9AF' End Page 188
EPS X'D3A95F' End Page Segment 189
EPT X'D3A99B' End Presentation T ext Object 190
ERG X'D3A9C6' End Resource Group 191
ERS X'D3A9CE' End Resource 192
ESG X'D3A9D9' End Resource Environment Group 193
FGD X'D3A6C5' Form Environment Group Descriptor (O) 555
GAD X'D3EEBB' Graphics Data 194
GDD X'D3A6BB' Graphics Data Descriptor 195
ICP X'D3AC7B' IM Image Cell Position (C) 602
IDD X'D3A6FB' Image Data Descriptor 196
IEL X'D3B2A7' Index Element 197
IID X'D3A67B' Image Input Descriptor (C) 603
IMM X'D3ABCC' Invoke Medium Map 199
IOB X'D3AFC3' Include Object 201
IOC X'D3A77B' IM Image Output Control (C) 605
IPD X'D3EEFB' Image Picture Data 218
IPG X'D3AFAF' Include Page 219
IPO X'D3AFD8' Include Page Overlay 222
IPS X'D3AF5F' Include Page Segment 224
IRD X'D3EE7B' IM Image Raster Data (C) 607
LLE X'D3B490' Link Logical Element 226
MBC X'D3ABEB' Map Bar Code Object 232
MCC X'D3A288' Medium Copy Count 233
MCD X'D3AB92' Map Container Data 235
Cross-References


Table 54 Structured Fields Sorted by Acronym (cont'd.)
Acronym Identifier Structured Field Name Page
MCF X'D3AB8A' Map Coded Font 237
MCF-1 X'D3B18A' Map Coded Font Format-1 (C) 598
MDD X'D3A688' Medium Descriptor 244
MDR X'D3ABC3' Map Data Resource 246
MFC X'D3A088' Medium Finishing Control 265
MGO X'D3ABBB' Map Graphics Object 273
MIO X'D3ABFB' Map Image Object 274
MMC X'D3A788' Medium Modification Control 276
MMD X'D3ABCD' Map Media Destination 286
MMO X'D3B1DF' Map Medium Overlay 288
MMT X'D3AB88' Map Media Type 289
MPG X'D3ABAF' Map Page 292
MPO X'D3ABD8' Map Page Overlay 294
MPS X'D3B15F' Map Page Segment 296
MPT X'D3AB9B' Map Presentation T ext 297
MSU X'D3ABEA' Map Suppression 298
NOP X'D3EEEE' No Operation 299
OBD X'D3A66B' Object Area Descriptor 300
OBP X'D3AC6B' Object Area Position 302
OCD X'D3EE92' Object Container Data 305
PEC X'D3A7A8' Presentation Environment Control 306
PFC X'D3B288' Presentation Fidelity Control 308
PGD X'D3A6AF' Page Descriptor 310
PGP X'D3B1AF' Page Position 313
PGP-1 X'D3ACAF' Page Position Format-1 (C) 600
PMC X'D3A7AF' Page Modification Control 327
PPO X'D3ADC3' Preprocess Presentation Object 329
PTD X'D3B19B' Presentation T ext Data Descriptor 340
PTD-1 X'D3A69B' Presentation T ext Descriptor Format-1 (C) 600
PTX X'D3EE9B' Presentation T ext Data 341
TLE X'D3A090' T ag Logical Element 342
Key:
O Obsolete
R Retired
C Coexistence
Cross-References


MO:DCA Triplets Sorted by Identifier
Table 55. Triplets Sorted by ID
Triplet ID Triplet Name Page
X'01' Coded Graphic Character Set Global ID 348
X'02' Fully Qualified Name 351
X'04' Mapping Option 360
X'10' Object Classification 363
X'10' MDD Two-up (R) 557
X'18' MO:DCA Interchange Set 367
X'1D' T ext Orientation (R) 558
X'1F' Font Descriptor Specification 369
X'20' Font Coded Graphic Character Set Global Identifier 373
X'21' Resource Object Type 374
X'21' Object Function Set Specification (R) 559
X'22' Extended Resource Local ID 376
X'24' Resource Local ID 378
X'25' Resource Section Number 379
X'26' Character Rotation 380
X'27' Line Data Object Position Migration (R) 561
X'2D' Object Byte Offset 381
X'36' Attribute Value 382
X'43' Descriptor Position 383
X'45' Media Eject Control 384
X'46' Page Overlay Conditional Processing (R) 564
X'47' Resource Usage Attribute (R) 566
X'4B' Object Area Measurement Units 388
X'4C' Object Area Size 389
X'4D' Area Definition 390
X'4E' Color Specification 391
X'50' Encoding Scheme ID 395
X'56' Medium Map Page Number 398
X'57' Object Byte Extent 399
X'58' Object Structured Field Offset 400
X'59' Object Structured Field Extent 401
X'5A' Object Offset 402
X'5D' Font Horizontal Scale Factor 404
Cross-References


Table 55 Triplets Sorted by ID (cont'd.)
Triplet ID Triplet Name Page
X'5E' Object Count 405
X'62' Local Date and Time Stamp 407
X'63' Object Checksum (R) 567
X'64' Object Origin Identifier (R) 568
X'65' Comment 409
X'68' Medium Orientation 410
X'6C' Resource Object Include 412
X'70' Presentation Space Reset Mixing 414
X'71' Presentation Space Mixing Rules 416
X'72' Universal Date and Time Stamp 418
X'73' IMM Insertion (R) 569
X'74' T oner Saver 420
X'75' Color Fidelity 421
X'78' Font Fidelity 424
X'80' Attribute Qualifier 425
X'81' Page Position Information 426
X'82' Parameter Value 427
X'83' Presentation Control 428
X'84' Font Resolution and Metric T echnology 429
X'85' Finishing Operation 430
X'86' T ext Fidelity 442
X'87' Media Fidelity 444
X'88' Finishing Fidelity 445
X'8B' Data-Object Font Descriptor 447
X'8C' Locale Selector 451
X'8E' UP3i Finishing Operation 454
X'8F' MO:DCA Function Set 455
X'91' Color Management Resource Descriptor 456
X'95' Rendering Intent 458
X'96' CMR T ag Fidelity 461
X'97' Device Appearance 463
X'9A' Image Resolution 464
X'9C' Object Container Presentation Space Size 466
X'9D' Keep Group T ogether 468
X'9E' Setup Name 469
Cross-References


Table 55 Triplets Sorted by ID (cont'd.)
Triplet ID Triplet Name Page
X'FF' Triplet Extender 470
Key:
O Obsolete
R Retired
C Coexistence
Cross-References


MO:DCA Triplets Sorted by Name
Table 56. Triplets Sorted by Name
Triplet Name Triplet ID Page
Area Definition X'4D' 390
Attribute Qualifier X'80' 425
Attribute Value X'36' 382
Character Rotation X'26' 380
CMR T ag Fidelity X'96' 461
Coded Graphic Character Set Global ID X'01' 348
Color Fidelity X'75' 421
Color Management Resource Descriptor X'91' 456
Color Specification X'4E' 391
Comment X'65' 409
Data-Object Font Descriptor X'8B' 447
Descriptor Position X'43' 383
Device Appearance X'97' 463
Encoding Scheme ID X'50' 395
Extended Resource Local ID X'22' 376
Finishing Fidelity X'88' 445
Finishing Operation X'85' 430
Font Coded Graphic Character Set Global Identifier X'20' 373
Font Descriptor Specification X'1F' 369
Font Fidelity X'78' 424
Font Horizontal Scale Factor X'5D' 404
Font Resolution and Metric T echnology X'84' 429
Fully Qualified Name X'02' 351
Image Resolution X'9A' 464
IMM Insertion (R) X'73' 569
Keep Group T ogether X'9D' 468
Line Data Object Position Migration (R) X'27' 561
Local Date and Time Stamp X'62' 407
Locale Selector X'8C' 451
Mapping Option X'04' 360
MDD Two-up (R) X'10' 557
Media Eject Control X'45' 384
Media Fidelity X'87' 444
Cross-References


Table 56 Triplets Sorted by Name (cont'd.)
Triplet Name Triplet ID Page
Medium Map Page Number X'56' 398
Medium Orientation X'68' 410
MO:DCA Function Set X'8F' 455
MO:DCA Interchange Set X'18' 367
Object Area Measurement Units X'4B' 388
Object Area Size X'4C' 389
Object Byte Extent X'57' 399
Object Byte Offset X'2D' 381
Object Checksum (R) X'63' 567
Object Classification X'10' 363
Object Container Presentation Space Size X'9C' 466
Object Count X'5E' 405
Object Function Set Specification (R) X'21' 559
Object Offset X'5A' 402
Object Origin Identifier (R) X'64' 568
Object Structured Field Extent X'59' 401
Object Structured Field Offset X'58' 400
Page Overlay Conditional Processing (R) X'46' 564
Page Position Information X'81' 426
Parameter Value X'82' 427
Presentation Control X'83' 428
Presentation Space Mixing Rules X'71' 416
Presentation Space Reset Mixing X'70' 414
Rendering Intent X'95' 458
Resource Local ID X'24' 378
Resource Object Include X'6C' 412
Resource Object Type X'21' 374
Resource Section Number X'25' 379
Resource Usage Attribute (R) X'47' 566
Setup Name X'9E' 469
T ext Fidelity X'86' 442
T ext Orientation (R) X'1D' 558
T oner Saver X'74' 420
Triplet Extender X'FF' 470
Universal Date and Time Stamp X'72' 418
Cross-References


Table 56 Triplets Sorted by Name (cont'd.)
Triplet Name Triplet ID Page
UP3i Finishing Operation X'8E' 454
Key:
O Obsolete
R Retired
C Coexistence
Cross-References




