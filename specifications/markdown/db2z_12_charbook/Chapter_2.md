Chapter 2. How Db2 for z/OS uses Unicode
Even if you do not use the Unicode encoding scheme for your data, you should be aware that Db2 uses
Unicode in many of its internal processes. This use might affect your applications, queries, storage, and
performance.
Db2 uses Unicode in the following ways:
Application preparation and processing:
• DBRMs that are produced in supported Db2 releases are stored in Unicode UTF-8. [DB2Z-2-001]
• Db2 parses DBRMs in Unicode UTF-8, regardless of the original Db2 release that produced the DBRM. [DB2Z-2-002]
• Db2 converts application source code to Unicode UTF-8 before it is processed by the precompiler. The [DB2Z-2-003]
precompiler then parses the source code in UTF-8. SQL statements and literals are considered part of
the application source and are also parsed in UTF-8. SQL statement text is converted to UTF-8 if it is not
already in UTF-8.
Db2 objects and data:
• Most Db2 catalog data is encoded in UTF-8. (The data in string columns that are not FOR BIT DATA [DB2Z-2-004]
columns in Unicode tables in the catalog is in UTF-8.) When you query the catalog, be aware that many
string columns are VARCHAR(128). This data type and length enable you to easily port applications that
run on other operating systems.
• The names of plans and packages are stored in Unicode UTF-8. [DB2Z-2-005]
• The values of some special registers are stored in Unicode UTF-8. [DB2Z-2-006]
• All EXPLAIN table data is encoded in Unicode UTF-8. [DB2Z-2-007]
• SYSIBM.SYSDUMMYU is encoded in Unicode UTF-8. For more information about the [DB2Z-2-008]
SYSIBM.SYSDUMMYx tables, see SYSDUMMYx tables (Introduction to Db2 for z/OS).
Authorization:
• Db2 authorization processes work on Unicode data. When using certain external authorization [DB2Z-2-009]
processes, such as RACF, Db2 needs to convert the data to EBCDIC.
Traces:
• You can specify that Db2 return trace data in Unicode. [DB2Z-2-010]
SQL statement processing:
• If you join Unicode and non-Unicode tables, Db2 performs some operations in Unicode. For example, [DB2Z-2-011]
if you compare columns from a Unicode table and an EBCDIC table, Db2 performs the comparison in
Unicode.
Utility control statements:
• Utilities can process control statements that are written in Unicode UTF-8. [DB2Z-2-012]
DRDA:
• Remote client systems can send and receive DRDA command and reply messages with character type [DB2Z-2-013]
data in Unicode (UTF-8).
Related concepts
DRDA character type parameters in Unicode
Remote Db2 applications can send and receive DRDA command and reply message parameters that
contain character type data encoded in Unicode CCSID 1208 (UTF-8). Using Unicode instead of EBCDIC
for these DRDA parameters can improve performance and avoid potential character conversion errors.
Related tasks
Specifying a CCSID for your application [DB2Z-2-014]

In Db2 for z/OS applications, one CCSID is associated with the source code and one or more CCSIDs can
be associated with the data that your application manipulates. The CCSID that Db2 associates with the
data is called the application encoding scheme.
Setting up Db2 to ensure that it interprets characters correctly
You need to make sure that Db2 uses the correct code page (which is identified by a CCSID) to interpret
your data. Otherwise, Db2 might store or use incorrect data. This situation is most likely to occur when
characters are converted or transferred between systems.
Related reference
Descriptions of SQL processing options (Db2 Application programming and SQL)
Retrieving data from the Db2 catalog
Beginning in DB2 version 8, most of the Db2 catalog data is stored in UTF-8. However, when you query the
catalog data, Db2 converts the data to the application encoding scheme.
About this task
GUPI
Having the catalog in Unicode enables your SQL statements to use names and literals that contain
characters that are not included in the subsystem EBCDIC CCSID.
Although most of the catalog is stored in UTF-8, several catalog tables are exceptions. SYSIBM.SYSCOPY
in DSNDB06.SYSTSCPY is not stored in UTF-8. Also the non-Unicode SYSIBM.SYSDUMMYx tables are not
stored in UTF-8.
You can select data from the catalog regardless of the application encoding scheme.
Procedure
To retrieve data from the Db2 catalog, perform all of the following actions:
• Ensure that you anticipate the sequence of the query result. Because the Db2 catalog is in Unicode [DB2Z-2-015]
UTF-8, queries against the catalog return the data according to the Unicode sorting sequence. In
Unicode, numeric characters are sorted before alphabetic characters.
• If you are using application host variables to store the results of any catalog string column values, [DB2Z-2-016]
ensure that these variables are large enough to hold those values.
Many string columns in the Db2 catalog are VARCHAR(128).GUPI
Related tasks
Specifying a CCSID for your application
In Db2 for z/OS applications, one CCSID is associated with the source code and one or more CCSIDs can
be associated with the data that your application manipulates. The CCSID that Db2 associates with the
data is called the application encoding scheme.
Related reference
Differences between Unicode and EBCDIC sorting sequences
In Unicode, numeric characters are sorted before alphabetic characters. In EBCDIC, alphabetic
characters are sorted before numeric characters.
Specifying that IFCID output should be in Unicode
You can start a performance, accounting, statistics, auditing, or monitoring trace by specifying the
appropriate instrumentation facility component identifier (IFCID) in the START TRACE command. Many
of these IFCIDs can write UTF-8 fields in the trace output.
Procedure
Set the UIFCIDS subsystem parameter to YES. [DB2Z-2-017]

This parameter is called UIFCIDS in DSN6SYSP. It is also Option 11 (Unicode IFCIDS) on installation panel
DSNTIPN. The default value is NO.
Results
Only a subset of the IFCID character fields are encoded in Unicode UTF-8. Those fields are identified
in the IFCID record definition by a %U in the comment area to the right of the field declaration in the
DSNDQWxx copy files.
If the UIFCIDS subsystem parameter is set to NO, the fields that are identified with %U are displayed in
EBCDIC.
Related reference
-START TRACE command (Db2) (Db2 Commands)
DSNTIPN: Tracing parameters panel (Db2 Installation and Migration) [DB2Z-2-018]
