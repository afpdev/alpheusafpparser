Appendix F. SYSSTRINGS catalog table
The SYSSTRINGS table contains information about character conversion. Each row describes a conversion
from one coded character set to another. The schema is SYSIBM.
Also refer to Building and using Dynamic Link Libraries (DLLs) (XL C/C++ Programming Guide) for
information on the additional conversions that are supported.
Each row in the table must have a unique combination of values for its INCCSID, OUTCCSID, and
IBMREQD columns. Rows for which the value of IBMREQD is N can be deleted, inserted, and updated
subject to this uniqueness constraint and to the constraints imposed by a VALIDPROC defined on the
table. An inserted row could have values for the INCCSID and OUTCCSID columns that match those of a
row for which the value of IBMREQD is Y. Db2 then uses the information in the inserted row instead of
the information in the IBM-supplied row. Rows for which the value of IBMREQD is Y cannot be deleted,
inserted, or updated. For information about the use of inserted rows for character conversion, see How an
entry in SYSIBM.SYSSTRINGS works with character conversion (Db2 Installation and Migration).
Db2 has two methods for character conversions and applies them in the following order:
1. Conversions specified by the various combinations of the INCCSID and OUTCCSID columns in the [DB2Z-F-001]
SYSIBM.SYSSTRINGS catalog table.
2. Conversions provided by z/OS support for Unicode. For more information, see z/OS Unicode Services [DB2Z-F-002]
User’s Guide and Reference.
If neither of these methods can be used for a particular character conversion, Db2 returns an error. [DB2Z-F-003]

Table 32. SYSIBM.SYSSTRINGS table column descriptions [DB2Z-F-004]

| Column name | Data type | Description | Use |
| :--- | :--- | :--- | :--- |
| INCCSID | INTEGER NOT NULL | The source CCSID for the character conversion represented by this row. The value of the source CCSID must be in the range of 1 to 65533 and must not be the same as the value for the OUTCCSID column. | G [DB2Z-F-005]|
| OUTCCSID | INTEGER NOT NULL | The target CCSID for the character conversion represented by this row. The value of the target CCSID must be in the range of 1 to 65533 and must not be the same as the value for the INCCSID column. | G [DB2Z-F-006]|
| TRANSTYPE | CHAR(2) NOT NULL | Indicates the nature of the conversion. Values can be:<br>GG: GRAPHIC to GRAPHIC<br>MM: EBCDIC MIXED to EBCDIC MIXED<br>MS: EBCDIC MIXED to SBCS<br>PM: ASCII MIXED to EBCDIC MIXED<br>PS: ASCII MIXED to SBCS<br>SM: SBCS to EBCDIC MIXED<br>SS: SBCS to SBCS<br>MP: EBCDIC MIXED to ASCII MIXED<br>PP: ASCII MIXED to ASCII MIXED<br>SP: SBCS to ASCII MIXED | G [DB2Z-F-007]|
| ERRORBYTE | CHAR(1) FOR BIT DATA (Nulls are allowed) | The byte used in the conversion table as an error byte. Any non-null value that is specified for the ERRORBYTE column must not be the same as the value that is specified for the SUBBYTE column. Null indicates the absence of an error byte. | S [DB2Z-F-008]|
| SUBBYTE | CHAR(1) FOR BIT DATA (Nulls are allowed) | The byte used in the conversion table as a substitution character. Any non-null value that is specified for the SUBBYTE column must not be the same as the value that is specified for the ERRORBYTE column. Null indicates the absence of a substitution character. | S [DB2Z-F-009]|
| TRANSPROC | VARCHAR(24) NOT NULL WITH DEFAULT | The name of a module or blanks. A nonblank value must conform to the rules for z/OS program names.<br>If IBMREQD is 'N', a nonblank value is the name of a conversion procedure provided by the user. The first five characters of the name of a user-provided conversion procedure must not be 'DSNXV'; these characters are used to distinguish user-provided conversion procedures from Db2 modules that contain DBCS conversion tables.<br>If IBMREQD is 'Y', a nonblank value is the name of a Db2 module that contains DBCS conversion tables. | G [DB2Z-F-010]|
| IBMREQD | CHAR(1) NOT NULL | A value of Y indicates that the row was provided with the Db2 product code. For all other values, see "Release indicators in the Db2 catalog" in Db2 catalog tables and table spaces (Db2 SQL).<br>The value in this field is not a reliable indicator of release dependencies. | G [DB2Z-F-011]|
| TRANSTAB | VARCHAR(256) FOR BIT DATA NOT NULL WITH DEFAULT | Either a 256-byte conversion table or an empty (0 length) string. | S [DB2Z-F-012]|

Information resources for Db2 for z/OS and related products
You can find the online product documentation for Db2 12 for z/OS and related products in IBM
Documentation.
For all online product documentation for Db2 12 for z/OS, see IBM Documentation (https://www.ibm.com/docs/en/db2-for-zos/12).
For other PDF manuals, see PDF format manuals for Db2 12 for z/OS (https://www.ibm.com/docs/en/SSEPEK_12.0.0/home/src/tpc/db2z_pdfmanuals.html). [DB2Z-F-013]
