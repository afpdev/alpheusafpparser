Index
Numerics
3270 applications [DB2Z-IDX-001]
Unicode data 95
A
access paths
for Unicode data 54
accessibility
keyboard ix
shortcut keys ix
AGCCSID 33
AMCCSID 33
APPENSCH
description 33
when Db2 uses 59
application data
determining CCSID 29
application encoding scheme
definition 24, 58
application programming
querying the catalog 24
recommendations 57
Unicode 57
applications
examples of specifying CCSIDs 64
specifying a CCSID 59
ASCCSID 33
ASCII
description 7
ASCII table
inserting Unicode data 51
ASCII_STR function
generating escaped data 88
authorization processes
Unicode data 23
B
best practices
for coding queries 95
big endian
description 14
BIT data
Unicode tables 46
byte order formats
big endian 14
little endian 14
C
C/C++
LOCALE compiler option 72
processing Unicode data 94
specifying CCSID 72
C/C++ (continued)
SQL compiler option with CCSID suboption 72
C/C++ source code
determining CCSID 29
canonically equivalent characters
description 91
case conversions
specifying culturally correct rules 85
catalog tables
CCSID information 73
encoding scheme 24
in Unicode 23
querying 24
SYSSTRINGS 111
CCSID 1047
compared to 500 9
compared to CCSID 37 9
CCSID 1140
code points 5
compared to CCSID 37 66
CCSID 1200
description 13
for table column 46
CCSID 1208
description 13
for table column 46
CCSID 367
code points 11
description 13
for table column 46
relationship to Unicode 10
CCSID 37
code points 5
compared to CCSID 1047 9
compared to CCSID 1140 66
compared to CCSID 500 9
CCSID 500
compared to CCSID 1047 9
compared to CCSID 37 9
CCSID set
specifying Db2 defaults 32
CCSID SQL processing option
description 61
setting 59
CCSID UNICODE clause
CREATE statements 46
CCSID0 69
CCSIDs
C/C++ 72
COBOL applications 66
conversions 15
description 5
determining for data source 29
determining for Db2 data 73
determining subsystem values 35
determining value for string 74
examples of specifying in applications 64 [DB2Z-IDX-002]


Index  123



CCSIDs (continued)
for PL/I applications 69
in EXPLAIN tables 103
multiple in SQL statement 75
specifying for an application 59
specifying for objects 37
specifying for subsystem 32
specifying in Db2 32
subsystem defaults 33
Unicode data 13
where to define valid conversions 27
character columns
Unicode tables 46
character conversion
contracting conversion 19
data loss 16
defining 43
definition 1
determining length 17
effects 16
enforced subset 20
ensuring accurate conversions 27
expanding conversion 17
in Db2 27
Java applications 94
occurrences 15
performance 16
round-trip conversion 20
substitution character 20
SYSIBM.SYSSTRINGS catalog table 111
terminology 1
types 17
where to define valid conversions 27
character conversion definitions
adding 43
checking 43
Chinese 41
finding 43
Japanese 41
Korean 41
SYSIBM.SYSSTRINGS 28
z/OS Unicode Services 39,
41
character data representation architecture (CDRA)
description 5
character repertoire
definition 11
Chinese character sets
conversion definitions 41
CICS Transaction Gateway
determining CCSID 29
COBOL
CODEPAGE compiler option 66
NOSQLCCSID 66
processing Unicode data 92
specifying CCSIDs 66
SQL compiler option with CCSID suboption 66
SQLCCSID 66
COBOL source code
determining CCSID 29
code pages
description 5
code points
description 5
code points (continued)
differences in EBCDIC CCSIDs 9
code units
size for UTFs 11
CODEPAGE compiler option
COBOL 66
PL/I 69
CODEUNITS16
description 80
CODEUNITS32
description 80
collation names 82
COLLATION_KEY function
creating index with 82
specifying sorting sequence 82
columns
estimating length for Unicode data 49
compatibly equivalent characters
description 91
compilers
specifying CCSID 59
contracting conversion
description 19
conversion image
creating 38
description 38
conversion tables
definition 38
converting data
to Unicode 52
cross-loader function
Unicode tables 101
CURRENT APPLICATION ENCODING SCHEME special
register
description 61
setting 59
CURRENT LOCALE LC_CTYPE special register
setting default locale 86
specifying casing rules 85
CURRENTAPPENSCH initialization keyword
specifying ODBC application encoding scheme 105
D
data sources
determining CCSID 29
databases
specifying CCSID 37
Db2 coprocessor
setting the application CCSID 59
Db2 data
determining CCSID 73
Db2 objects
encoding scheme 37
specifying CCSIDs 37
Db2 precompiler
setting the application CCSID 59
Unicode parsing 23
Db2 processes
in Unicode 23
Db2 Query Management Facility (QMF)
Unicode support 107
DBRM
in Unicode 23




debugging problems
with CCSIDs 99
with Unicode data 99
DECLARE VARIABLE statement
description 61
setting host variable CCSID 59
decoding
definition 11
DESCRIBE statement
checking CCSID of string 74
disability ix
DISPLAY UNI command
checking character conversion definitions 43
checking supported Unicode characters 13
DRDA
effect of ENCODING bind option 59
setting CCSID 59
DRDA Unicode parameters 96
DSNHDECP
CCSID information 32
subsystem CCSIDs 33
DSNJU004
determining subsystem CCSID values 35
DSNTIJUZ job
specifying CCSIDs 32
DSNTIPF installation panel
specifying CCSIDs 32
DSNUTILU
invoking utilities 101
E
EBCDIC
CCSID code point differences 9
description 8
EBCDIC sorting sequence
differences from Unicode 78
EBCDIC table
inserting Unicode data 51
EBCDIC_STR function
generating escaped data 88
encoding
definition 11
ENCODING bind option
C/C++ 72
COBOL 66
description 61
DRDA 59
PL/I 69
setting 59
encoding schemes
ASCII 7
description 7
EBCDIC 8
subsystem defaults 33
Unicode 10
endianness
description 14
enforced subset conversion
description 20
ENSCHEME 33
ERRORBYTE column of SYSSTRINGS catalog table 111
escaped data
description 88
escaped data (continued)
generating 88
expanding conversion
description 17
effects 17
when loading Unicode tables 100
EXPLAIN
Unicode support 103
EXPLAIN tables
CCSID information 103
in UTF-8 103
F
fixed-length strings
expanding conversions 17
fixed-length variables
expanding conversions 17
FTP data
determining CCSID 29
functions
calculating length 80
specifying CCSID 37
G
GCCSID 33
general-use programming information, described 118
GETVARIABLE
determining subsystem CCSID values 35
graphic columns
Unicode tables 46
green screen applications
Unicode data 95
GUPI symbols 118
H
High Performance Unload
Unicode support 107
I
IBM Data Studio
Unicode support 107
IBMREQD
SYSIBM.SYSSTRINGS column 28
IBMREQD column
SYSSTRINGS catalog table 111
ICU 109
IFCID
Unicode output 24
image generator
description 38
IMS
determining CCSID 29
INCCSID column of SYSSTRINGS catalog table 111
installation job DSNTIJUZ
specifying CCSIDs 32
installation panel DSNTIPF
specifying CCSIDs 32
International Components for Unicode 109
invariant characters 95 [DB2Z-IDX-003]


Index  125



ISPF
determining CCSID 29
J
Japanese character sets
conversion definitions 41
Java
character conversion 94
K
Korean character sets
conversion definitions 41
L
language casing rules
specifying 85
language-specific sorting sequences 82
LE locales 86
length
estimating for Unicode columns 49
expansion when inserting Unicode data 100
specifying how Db2 calculates 80
length functions
effect of contracting conversions 19
effect of expanding conversions 17
links
non-IBM Web sites
119
little endian
description 14
LOAD utility
Unicode data 101
local system
character conversions 15
LOCALE
C/C++ compiler option
72
locales
Db2 functions 85
description 86
LE locales 86
z/OS Unicode Services
86
LOWER function
ensuring culturally correct results 85
M
MCCSID 33
mixed data
Unicode tables 46
multiple CCSIDs
referenced in the same SQL statement 75
N
NOCCSID0 69
Normalization Form Canonical Composition (NFC) 91
Normalization Form Canonical Decomposition (NFD) 91
Normalization Form Compatibly Composition (NFKC) 91
Normalization Form Compatibly Decomposition (NFKD) 91
NORMALIZE_STRING function
ensuring well-formed data 91
normalizing Unicode strings 91
normalizing
Unicode strings 91
NOSQLCCSID 66
O
object names
contracting conversions 19
expanding conversions 17
objects
determining CCSID 73
OCTETS
description 80
ODBC
Unicode support 105
ORDER BY clause
differences by encoding scheme 78
OUTCCSID column of SYSSTRINGS catalog table 111
P
package names
Unicode 23
Personal Communications
determining CCSID 29
PL/I
CCSID0 69
CODEPAGE compiler option 69
NOCCSID0 69
PP compiler option 72
processing Unicode data 93
specifying CCSIDs 69
SQL Preprocessor 72
PL/I source code
determining CCSID 29
PP compiler option 72
predefined conversion definitions
checking 43
preparing Db2
for character conversion 27
procedures
specifying CCSID 37
product-sensitive programming information, described 118
programming interface information, described 118
PSPI symbols 118
Q
QMF
determining CCSID 29
queries
best practices for coding 95
Queue Managers in IBM MQ
determining CCSID 29
R
remote applications
passing DRDA parameters in Unicode 96 [DB2Z-IDX-004]




remote system
character conversions 15
round-trip conversion
description 20
S
SBCS data
Unicode tables 46
SCCSID 33
setting up Db2
for character conversion 27
SETUNI command
activating conversion image 38
shortcut keys
keyboard ix
sorting data
with language specific rules 82
sorting sequence
EBCDIC 78
language-specific 82
specifying 82
Unicode 78
special characters
typing 50
special registers
Unicode 23
SQL compiler option with CCSID suboption
C/C++ 72
COBOL 66
SQL preprocessor for PL/I 72
SQL statements
different CCSIDs 75
SQL_C_WCHAR data type
ODBC applications 105
SQLCCSID 66
SQLDA
checking CCSID information 74
SQLGetInfo API
retrieving subsystem CCSID information in ODBC
applications 105
storage
Unicode, tips 49
string length
determining 80
strings
determining CCSID 74
SUBBYTE column of SYSSTRINGS catalog table 111
substitution character
in character conversions 20
subsystem
CCSIDs 33
determining CCSID values 35
encoding schemes 33
specifying CCSIDs 32
subsystem CCSIDs
retrieving in ODBC applications 105
subsystem default application encoding scheme 33
subsystem default ASCII CCSID 33
subsystem default EBCDIC CCSID 33
subsystem default encoding scheme 33
subsystem default Unicode CCSID 33
supplementary characters
description 91
supplementary characters (continued)
how Db2 handles 91
syntax diagram
how to read x
SYSIBM.SYSSTRINGS
adding character conversion definitions 43
description 28
how Db2 uses for character conversion 27
querying 43
system default application encoding scheme
when Db2 uses 59
T
Table Editor
Unicode support 107
table spaces
specifying CCSID 37
tables
creating in Unicode 46
estimating column size for Unicode data 49
inserting Unicode data 50
specifying CCSID 37
terminology
for character conversion 1
tools
Unicode support 107
trace
Unicode output 24
traces
Unicode 23
TRANSLATE function
ensuring culturally correct results 85
TRANSPROC column of SYSSTRINGS catalog table 111
TRANSTAB column of SYSSTRINGS catalog table 111
TRANSTYPE column of SYSSTRINGS catalog table 111
U
UGCCSID 33
UMCCSID 33
Unicode
application programming 57
CCSIDs 13
converting Db2 data to 52
debugging 99
description 10
differences from EBCDIC 78
EXPLAIN support 103
generating escaped data 88
in Db2 23
normalizing strings 91
ODBC support 105
preserving data 88
problems inserting data 100
sorting sequence 78
storage tips 49
supplementary characters 91
utilities 101
Unicode data
access paths 54
C/C++ applications 94
COBOL applications 92 [DB2Z-IDX-005]


Index  127



Unicode data (continued)
green screen applications 95
inserting into non-Unicode table 51
PL/I applications 93
storing 45
Unicode on Demand 38
Unicode support
Db2 High Performance Unload for z/OS
107
Db2 Table Editor for z/OS 107
IBM Data Studio 107
IBM tools 107
Unicode tables
creating 46
determining column type 46
estimating column size 49
inserting data 50
Unicode Transformation Formats (UTFs)
Db2 support 11
UNICODE_STR function
interpreting escaped data 88
UNLOAD utility
Unicode data 101
UPPER function
ensuring culturally correct results 85
USCCSID 33
UTF-16
compared to UTF-8 45
Db2 support 45
description 11
endianness 14
UTF-32
description 11
endianness 14
UTF-8
compared to UTF-16 45
Db2 support 45
description 11
endianness 14
UTFdetermining which to use 45
utilities
control statements in Unicode 23
Unicode support 101
V
variant characters 95
W
wide APIs
ODBC UTF-16 data 105
Z
z/OS
determining CCSID 29
z/OS Unicode Services
adding character conversion definitions 43
basic character conversions 39
Chinese, Japanese, and Korean character conversions
41
conversion image 38
z/OS Unicode Services (continued)
how Db2 uses for character conversion 27
locales 86
setting up 38








IBM®
Product Number: 5650-DB2
  5770-AF3
SC19-8850-02



index based on the collation key shifts this performance cost from query time to insert or update time.
That performance shift assumes that Db2 chooses to use the index for the query.
GUPIFor example, suppose that you want to use the following basic query:
SELECT C1 FROM T1 ORDER BY C1
However, you want to ensure that the result is ordered according to the rules for a particular
locale. For this example, assume the language of the data is French. In this case, you can use the
COLLATION_KEY function, as shown in the following statement:
SELECT C1 FROM T1 ORDER BY COLLATION_KEY(C1,'UCA410_LFR_FO')
GUPIThe collation name UCA410_LFR_FO has the following meaning:
Table 24. Example collation options and corresponding collation keywords
Corresponding collation keyword Option
UCA410 Specifies that Db2 is to use the collation service
UCA410
LFR Specifies that the locale is French. (L = language,
FR= French)
