Appendix F . Object OID Algorithms
This appendix provides the definitions for the object OID algorithms used in the MO:DCA architecture:
• The object OID algorithm used for TrueType and OpenType fonts. This is the same algorithm previously [MODCA-F-001]
published in the document Using OpenType Fonts in an AFP System; G544-5876-02.
• The object OID algorithm used for Color Management Resources and Data Objects. This is the same [MODCA-F-002]
algorithm previously published in the document AFP Color Management Architecture (ACMA) Release 1.
TrueType and OpenType Font Object OID Generation Algorithm
The object OID that is placed into the TrueType/OpenType Resource Access T able by an application that
installs TrueType/OpenType fonts consists of two parts: (1) a constant part or seed that is based on a fixed
sequence of nodes in the ISO OID naming tree, and (2) a variable part that is algorithmically generated based
on the font object content. This scheme allows the object OID to be regenerated and verified by any
component in the presentation system, such as the print server or the printer control unit. All components that
follow the first five components (that is, those after 1.3.18.0.4) are managed by the InfoPrint Solutions
Company.
Seed (constant part)
The seed is a predefined constant that has seven components defined as follows:
1. ISO, value = 1 [MODCA-F-003]
2. Identified organization, value = 3 [MODCA-F-004]
3. IBM, value = 18 [MODCA-F-005]
4. Objects, value = 0 [MODCA-F-006]
5. InfoPrint - Print, value = 4 [MODCA-F-007]
6. Document Formats, value = 1 [MODCA-F-008]
7. Object OID algorithm, value = 5 [MODCA-F-009]
Therefore, the constant part for a font or TTC object OID takes the following form:
1.3.18.0.4.1.5
Object-unique components (variable part)
This part consists of 5 components defined as follows:
1. MD5 fingerprint, 16 byte hexadecimal value in the human readable form of the OID. This is [MODCA-F-010]
a checksum calculated from the entire object (in stream format, that is, after any
environment-specific encapsulation or blocking has been removed). The algorithm is the
RSA Data Security, Inc. MD5 Message-Digest Algorithm described in RFC 1321. This
algorithm claims to be unique to a 1 in 2
64 probability, given two different byte strings of [MODCA-F-011]
independent size.
2. Size of the object, number of bytes in the object; this is the actual size of the object after [MODCA-F-012]
any environment-specific encapsulation or blocking has been removed. This is a variable-
length value.
3. Supplier ID, value = 0 (for unspecified supplier) or value = 1 (for IBM); additional supplier [MODCA-F-013]
IDs could be registered in the future.
4. Customer ID, value = 0 (for unspecified customer ID); actual customer IDs could be [MODCA-F-014]
registered in the future.
5. Component reserved for future use, value = 0 (for unspecified) . [MODCA-F-015]


Component growth
Since all object OID components except the last four components are a fixed size, component
growth is minimal; an object OID computed from this algorithm is approximately 33 bytes long
in ASN.1 definite short form.
Note: The minimum length of a TTF/OTF font OID or of a TTF/OTF font collection OID,
assuming that the MD5 checksum is a value less than X'7F' preceded by all zeros and
can therefore be represented by 1 byte, has been calculated to be 13 bytes. The
maximum length is 129 bytes.
Example
This example shows an OID in two useful forms:
• Human-readable OID: [MODCA-F-016]
1.3.18.0.4.1.5.X'FFEEDDCCBBAA99887766554433221100'.X'0F4240'.0.0.0
• ASN.1 definite short form OID: [MODCA-F-017]
X'061F2B120004010583FFEEEEF397BAD4E690F7B395A8C39988A200BD8440000000'
For a description of the ASN.1 short form notation, see the description of the FQN triplet, FQNFmt = X'10' -
OID.
Color Management Resource and Data Object OID Generation Algorithm
The object OID that is placed into the CMR Resource Access T able or into the Data Object Resource Access
T able by an application that installs such objects consists of two parts: (1) a constant part or seed that is based
on a fixed sequence of nodes in the ISO OID naming tree, and (2) a variable part that is algorithmically
generated based on the CMR or data object content. This scheme allows the object OID to be regenerated and
verified by any component in the presentation system, such as the print server or the printer control unit. All
components that follow the first four components (that is, those after 1.2.208.171) are managed by the AFP
Consortium.
Seed (constant part)
The seed is a predefined constant that has five components defined as follows:
1. ISO, value = 1 [MODCA-F-018]
2. Member body, value = 2 [MODCA-F-019]
3. Denmark, value = 208 [MODCA-F-020]
4. AFP Consortium, value = 171 [MODCA-F-021]
5. OID algorithm, value = 1 [MODCA-F-022]
Therefore, a seed for a CMR or data object OID takes the following form:
1.2.208.171.1
Object-unique components (variable part)
Two object-unique components are predefined, as follows:
1. MD5 fingerprint, a 16-byte hexadecimal value in the human-readable form of the OID. [MODCA-F-023]
This is a checksum calculated from the entire object (in stream format; that is, after any
environment-specific encapsulation or blocking has been removed). The algorithm is the
RSA Data Security, Inc. MD5 Message-Digest Algorithm described in RFC 1321. This
algorithm claims to be unique to a 1 in 2
64 probability, given two different byte strings of [MODCA-F-024]
independent size.
2. Size of the object, the number of bytes in the object. This is the actual size of the object [MODCA-F-025]
after any environment-specific encapsulation or blocking has been removed. This is a
variable-length value.
Object OID Algorithms [MODCA-F-026]


Component growth
Because all components except the last component are of fixed size, component growth is
minimal. An OID computed using this algorithm is approximately 30 bytes long in ASN.1
definite short form. The minimum length of an OID in this form is calculated to be 10 bytes.
Note: The minimum length of an object OID for a CMR or Data Object, assuming that the MD5
checksum is a value less than X'7F' preceded by all zeros and can therefore be
represented by 1 byte, has been calculated to be 10 bytes. The maximum length is 129
bytes.
Example
This example shows an OID in two useful forms:
• Human-readable OID: [MODCA-F-027]
1.2.208.171.1.X‘FFEEDDCCBBAA99887766554433221100‘.X‘0F4240‘
• ASN.1 definite short form OID: [MODCA-F-028]
X'061C2A8150812B0183FFEEEEF397BAD4E690F7B395A8C39988A200BD8440'
For a description of the ASN.1 short form notation, see the description of the FQN triplet, FQNFmt = X'10' -
OID.
Object OID Algorithms [MODCA-F-029]




