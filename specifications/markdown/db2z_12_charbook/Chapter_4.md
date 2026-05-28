Chapter 4. Storing Unicode data
Db2 for z/OS supports the full Unicode character repertoire, or set of characters. You can store Db2 data
as UTF-8 or UTF-16.
Related concepts
Unicode
Unicode is an encoding scheme that currently provides a unique code point for over 100,000 characters.
This standard enables systems to more easily handle global data, regardless of the platform, program, or
language.
Related reference
UTFs
Deciding whether to store data as UTF-8 or UTF-16
If you create a Unicode database in Db2 for z/OS, you need to decide whether to use UTF-8 or UTF-16.
Db2 for z/OS does not support storing data as UTF-32. UTF-8 and UTF-16 can both represent any Unicode
character that you need to represent, but each format has advantages and disadvantages depending on
your situation.
Procedure
Consider the following recommendations and guidelines:
Performance
Store your data in Db2 in the same format as your application. This setup ensures optimal
performance, because character conversion is avoided.
This recommendation is especially important when the application is written in a language that runs
on z/OS (for example COBOL on z/OS), because the CPU cost of character conversion on z/OS can be
very expensive.
For example:
• COBOL and PL/I on z/OS use UTF-16 or UTF-8 for Unicode data. Even if your compiler supports [DB2Z-4-001]
UTF-8 data, the optimal situation is to store your data in Db2 in UTF-16. UTF-16 data can potentially
take more storage than UTF-8 data, but because no conversion occurs when you use UTF-16 data,
you avoid a significant performance impact.
• For Java applications that use IBM Data Server Driver for JDBC and SQLJ type 4 connectivity, which [DB2Z-4-002]
sends the data in UTF-8, store your data in Db2 as UTF-8 data.
If you have both local and remote applications on different operating systems, choose the format
based on the encoding of the local application.
Storage
After you consider performance, consider your storage requirements. Store the data in the format that
requires the least space for your data.
UTF-16 does not always require more storage than UTF-8. The amount of storage that is required
depends on your data. For example, Latin-1 characters always take 1 byte in UTF-8 and 2 bytes in
UTF-16. However, Japanese characters take 3 to 4 bytes in UTF-8 and 2 to 4 bytes in UTF-16.
For example, Db2 for z/OS uses UTF-8 for the catalog. Because the catalog contains mostly Latin-1
characters, this format uses considerably less space than UTF-16.
MQ, CICS Transaction Gateway, and IMS Connect messages
When messages are passed from one technology to another, everything in the message is usually
converted to characters. You should consider the size of these messages when you decide when
and where to use certain UTFs. For example, suppose that you have COBOL applications, which use [DB2Z-4-003]




UTF-16, but you are concerned about the size of the messages. You might decide to convert the
messages to UTF-8 before you put them on the wire. This setup compresses the messages.
What to do next
If you choose a Unicode format for performance reasons and are concerned about the extra storage that
the format requires, see “Tips for handling any extra storage that Unicode data might require”.
Related reference
UTFs
Creating a Unicode table
If you plan to store Unicode data, create Unicode tables. If you try to insert Unicode data into an ASCII or
EBCDIC table, data might be lost, unless you use escaped data.
About this task
Recommendation: When you create objects, use standard characters for the object names and column
names. Unique characters, such as ü or é, can complicate your applications if conversions are needed.
Procedure
To create a Unicode table:
1. In the CREATE DATABASE, CREATE TABLESPACE, or CREATE TABLE statement, specify the CCSID [DB2Z-4-004]
UNICODE clause.
By default, the encoding scheme of a table is the same as the encoding scheme of its table space.
Also by default, the encoding scheme of the table space is the same as the encoding scheme of its
database. You can override the encoding scheme with the CCSID clause in the CREATE TABLESPACE or
CREATE TABLE statement. However, all tables within a table space must have the same CCSID.
2. In the CREATE TABLE statement, for each column definition, specify the appropriate data type, [DB2Z-4-005]
subtype, and length value.
data type
Use one of the following data types:
• For UTF-8 data, create columns of type CHAR, VARCHAR, or CLOB. [DB2Z-4-006]
• For UTF-16 data, create columns of type GRAPHIC, VARGRAPHIC, or DBCLOB. [DB2Z-4-007]
• For binary data, create columns of type BINARY, VARBINARY, and BLOB. [DB2Z-4-008]
Recommendation: In general, use varying-length columns for Unicode tables because the number
of bytes in a Unicode column usually is two to three times that of an EBCDIC column.
The general guideline is to use variable length for columns that are greater than 18 bytes unless
you know that the entire column is to always be filled. For example, if you store a timestamp in
character form (not as the Db2 TIMESTAMP datatype), you need a column with some number of
characters. In DB2 9, that number would be 26 characters. (In ASCII, EBCDIC, or UTF-8, that
column is 26 bytes. In UTF-16, that column is 52 bytes.) Because the timestamp is always the
same size, using a varying-length column does not save storage. However, suppose that you have
a name field that is in ASCII or EBCDIC and allows for names of 26 characters. (In ASCII SBCS or
EBCDIC SBCS, you use 26 bytes. In UTF-8, you need 78 bytes. In UTF-16, you need 52 bytes.) In
this case, you want to use a varying-length column, because the name field is likely to have many
blanks and you do not want to store them.
subtype
For character columns, optionally specify one the following subtypes for the column by adding the
FOR subtype DATA clause to the column definition: [DB2Z-4-009]



SBCS
Specify this subtype if the column is to contain only those UTF-8 characters that are stored as
1 byte. Those characters are the first 128 characters in the Unicode code page. Data that is [DB2Z-4-010]
stored in a SBCS character column in a Unicode table has a CCSID of 367.
MIXED
Specify this subtype if the column is to contain any UTF-8 data that is more than 1 byte. MIXED
is the default value. Character data in a Unicode table is stored as mixed data by default, even
if your subsystem is defined with a MIXED DECP value of NO. Data that is stored in a MIXED
character column in a Unicode table has a CCSID of 1208.
BIT
This subtype specifies that the column contains BIT data. CCSID 66534 is associated with FOR
BIT DATA columns.
Recommendation: Although you can also specify the subtype BIT for CHAR and VARCHAR
columns that contain BIT data, use the BINARY or VARBINARY data types instead.
Do not use FOR BIT DATA columns for the sole purpose of handling international data. Only use
FOR BIT DATA columns if you have a specific reason, such as encryption. Otherwise, this data
type can cause problems. For example, if you have a string of length 10 and put it in a FOR BIT
DATA column of length 12, Db2 pads the string with two blanks. The hexadecimal value that
is used for those blanks is system specific. For example, X'40' is used for EBCDIC and X'20'
is used for Unicode. These different hexadecimal values can potentially cause problems when
you convert this data.
length
To determine the appropriate the length value, follow the instructions in “Estimating the column
size for Unicode data”.
Db2 associates a certain CCSID with the column depending on the data type that you specify. The
following table summarizes the possible column data types in a Unicode table and the CCSIDs that are
associated with the data in those columns.
Table 16. CCSIDs that are associated with columns in a Unicode table [DB2Z-4-011]

| Column data type | Associated CCSID | Format in which the data is stored |
| :--- | :--- | :--- |
| CHAR<sup>a</sup> | 1208 | UTF-8 [DB2Z-4-012]|
| CHAR FOR SBCS DATA | 367 | 7-bit ASCII [DB2Z-4-013]|
| CHAR FOR MIXED DATA | 1208 | UTF-8 [DB2Z-4-014]|
| CHAR FOR BIT DATA | 66534 | NA [DB2Z-4-015]|
| VARCHAR<sup>a</sup> | 1208 | UTF-8 [DB2Z-4-016]|
| VARCHAR FOR SBCS DATA | 367 | 7-bit ASCII [DB2Z-4-017]|
| VARCHAR FOR MIXED DATA | 1208 | UTF-8 [DB2Z-4-018]|
| VARCHAR FOR BIT DATA | 66534 | NA [DB2Z-4-019]|
| CLOB<sup>a</sup> | 1208 | UTF-8 [DB2Z-4-020]|
| CLOB FOR SBCS DATA | 367 | 7-bit ASCII [DB2Z-4-021]|
| CLOB FOR MIXED DATA | 1208 | UTF-8 [DB2Z-4-022]|
| GRAPHIC | 1200 | UTF-16 [DB2Z-4-023]|
| VARGRAPHIC | 1200 | UTF-16 [DB2Z-4-024]|
| DBCLOB | 1200 | UTF-16 [DB2Z-4-025]|

**Note:**
a. If you do not specify a subtype, Db2 assumes FOR MIXED DATA.
Example
GUPI
The following CREATE TABLE statement creates a Unicode table.
CREATE TABLE UNITAB
 (C1 CHAR(4)FOR SBCS DATA,
  C2 CHAR(4),
  C3 GRAPHIC(4),
  C4 VARCHAR(4) FOR SBCS DATA,
  C5 VARCHAR(4),
  C6 VARGRAPHIC(4))
CCSID Unicode
GUPI
Columns C1 and C4 can contain only 1-byte UTF-8 data. (This data has CCSID 367 and is stored in 7-bit
ASCII format.) Columns C2 and C5 can contain any UTF-8 data. Columns C3 and C6 can contain UTF-16
data.
The CHAR and VARCHAR columns each have a length of 4 bytes. That length means that each of these
columns can contain one of the following characters or sets of characters:
• one UTF-8 character that is 4 bytes [DB2Z-4-026]
• two UTF-8 characters that are each 2 bytes [DB2Z-4-027]
• one 3-byte UTF-8 character and one one-byte UTF-8 character [DB2Z-4-028]
• four one-byte UTF-8 characters [DB2Z-4-029]
The GRAPHIC and VARGRAPHIC columns each have a length of 4 UTF-16 code units. (A UTF-16 code unit
is 16 bits or 2 bytes.) For UTF-16 characters that are 2 bytes, this length means 4 characters. However,
this length does not always correlate to 4 characters. Consider supplementary UTF-16 characters, which
are each 2 UTF-16 code units or 4 bytes. If you include any supplementary characters in the column, the
column cannot include 4 characters. Thus, the length of this column can contain 2, 3, or 4 characters,
depending on the size of the character. For example, each of these GRAPHIC and VARGRAPHIC columns
can contain one of the following characters or sets of characters:
• four 2-byte UTF-16 characters [DB2Z-4-030]
• two 4-byte UTF-16 characters [DB2Z-4-031]
• one 4-byte UTF-16 character and two 2-byte UTF-16 characters [DB2Z-4-032]
Related tasks
Generating escaped Unicode data
If you pass Unicode characters to an application or object that is not intended to handle Unicode data,
data might be lost unless you escape certain characters. For example, you might need to pass Unicode
data through an application that has EBCDIC host variables. Or you might want to store Unicode data in a
non-Unicode table.
Related reference
UTFs
CREATE DATABASE statement (Db2 SQL)
CREATE TABLE statement (Db2 SQL)
CREATE TABLESPACE statement (Db2 SQL)
MIXED DATA field (MIXED DECP value) (Db2 Installation and Migration) [DB2Z-4-033]



Tips for handling any extra storage that Unicode data might require
Unicode data often requires more storage than EBCDIC or ASCII data, but not always. The amount of
extra storage that is required depends on the type of data and whether it is stored in UTF-8 or UTF-16
format.
Unicode data almost never requires double the amount of storage as EBCDIC or ASCII data. That amount
of extra storage is the extreme worst-case scenario. To figure out how much space your Unicode data
requires, consider the following two factors:
The type of data that you plan to store in Db2
How many character fields do you have? Any increased storage requirement affects mostly character
fields. So if you convert an existing Db2 database to Unicode, look at the character fields that are
defined in your existing database to get an idea of how much the database expands when you convert
it to Unicode.
Is the data Latin-1, Japanese, Chinese, or something else? For example, the first 128 Latin-1 code
points of UTF-8 take up only 1 byte. Those code points include the characters A-Z, a-z, and 0-9. Thus,
these characters do not take up any more space in UTF-8 than they do in EBCDIC or ASCII. Also,
consider that Chinese characters can take up less space in Unicode than EBCDIC.
The UTF format
Are you using UTF-8 or UTF-16? UTF-8 characters can take 1, 2, 3, or 4 bytes. UTF-16 characters can
take 2 or 4 bytes. Even though UTF-16 often takes more storage, UTF-16 is sometimes a wiser choice
for performance reasons. Also, in some cases, UTF-16 takes up less space. For example, Japanese
characters are 3 or 4 bytes in UTF-8, but 2 or 4 bytes in UTF-16.
If possible, use the following general recommendations to minimize the storage impact of Unicode data:
• Use data compression. [DB2Z-4-034]
• Use non-padded indexes. If you are converting data that has padded indexes to Unicode, change those [DB2Z-4-035]
indexes to be non-padded. This type of index can save index storage space.
• If a column length is more than 18 bytes, use variable length data types. [DB2Z-4-036]
• Use 8-KB pages instead of the default 4-KB pages by increasing the size of the buffer pools. (The buffer [DB2Z-4-037]
pool in which you define the table space determines the page size.)
Related tasks
Compressing your data (Db2 Performance)
Saving disk space by using non-Padded indexes (Db2 Performance)
Related reference
UTFs
Estimating the column size for Unicode data
When you create a table to store Unicode data, allocate columns for storage length, not for display length.
Procedure
To estimate the column size for Unicode data, perform one of the following actions:
• For UTF-8 data, allocate three times the column size that you would allocate for a non-Unicode table. [DB2Z-4-038]
For example, if you use CHAR(10) for a name column in an EBCDIC table, use VARCHAR(30) for the
same column in a Unicode table. This column can contain 30 bytes or ten 3-byte characters. In this
case, use VARCHAR instead of CHAR, because the length (30) is greater than 18. (18 is traditionally the
length when VARCHAR should be used instead of CHAR.)
This estimate allows for the worst-case expansion of UTF-8 data. The worst case for SBCS data
is that 1 byte in ASCII or EBCDIC expands to 3 bytes in UTF-8. For mixed data, such as Chinese,
Japanese, or Korean characters, the same worst-case scenario applies. You might have 2-, 3- and 4-byte characters, depending on the encoding, that expand to a four-byte UTF-8 character in the worst case. However, because these characters used more than one byte in ASCII or EBCDIC, the worst-case expansion in UTF-8 is still three times the original size.
• For UTF-16 data, allocate two times the column size that you would allocate for a non-Unicode table, [DB2Z-4-039]
and use the GRAPHIC or VARGRAPHIC data types.
For example, if you use CHAR(10) for a name column in an EBCDIC table, use VARGRAPHIC(10) for the
same column in a Unicode table. CHAR(10) is 10 bytes long. VARGRAPHIC(10) is 20 bytes long or the
equivalent of 10 two-byte characters.
Recommendation: If your application is written in COBOL or PL/I, store your data in UTF-16, and use
the GRAPHIC and VARGRAPHIC data types. Thus, the Unicode format in your application matches the
format in your database. This setup avoids conversion costs.
Related reference
UTFs
Inserting data into a Unicode table
Unicode tables can store any characters. For characters that you can type on your keyboard, INSERT
statements are straightforward. But suppose that you want to insert a character that is not on your
keyboard, such as the yen sign (¥) on the U.S. keyboard. That process requires some extra steps.
Procedure
To insert data into a Unicode table, use one of the following methods:
• Load the data from a data set by using the LOAD utility. If the input data set is already in Unicode, [DB2Z-4-040]
specify the UNICODE option. If the data is not in Unicode, ensure that you specify the appropriate
encoding scheme keyword (ASCII, EBCDIC, or CCSID) in the LOAD utility statement. The default is
EBCDIC. Db2 converts ASCII and EBCDIC data to Unicode when it is loaded into a Unicode table. Be
aware that this conversion might cause the data to expand.
• Load the data from another table by using the cross-loader function. If the data is from an EBCDIC [DB2Z-4-041]
or ASCII table, Db2 converts it to Unicode when it is loaded into the target Unicode table. Be aware
that this conversion might cause the data to expand.
• Insert individual rows by using the INSERT statement. For characters that cannot be typed on your [DB2Z-4-042]
keyboard, use the Unicode constant UX'xxxx'.
This constant is always in UTF-16, which means that you need to specify the value in UTF-16 format.
To determine the Unicode constant for a particular character perform the following steps:
a) Look up the Unicode code point. Use the Unicode character code charts on the Unicode Consortium
web site. For example, the yen sign (¥) is U+00A5.
b) Convert the Unicode code point to UTF-16 format by performing one of the following actions:
– If the Unicode code point U+yyyy is less than U+FFFF, encoding it in UTF-16 is simple. Just
copy the value. For example, the following Unicode code points can be specified as the following
Unicode constants:
Table 17. Unicode code points and their corresponding Unicode constants for Unicode code points that are less than U+FFFF [DB2Z-4-043]

| Character | Unicode code point | UTF-16 format | Unicode constant |
| :--- | :--- | :--- | :--- |
| ¥ | U+00A5 | X'00A5' | UX'00A5' [DB2Z-4-044]|
| ĸ | U+0138 | X'0138' | UX'0138' [DB2Z-4-045]|
|   | U+270E | X'270E' | UX'270E' [DB2Z-4-046]|



– If the Unicode code point U+yyyy is greater than or equal to U+FFFF, encode that character as
UTF-16 format, and use that encoded value. For example, Unicode code point U+200D0 can be
encoded in UTF-16 as X'D840DCD0'. Thus, the Unicode constant is UX'D840DCD0'.
You can find the steps for how to manually encode and decode Unicode data on the Unicode
Consortium web site. Alternatively, you can use a converter tool to do the conversion for you.
Example
The following INSERT statement inserts a row with Unicode character U+200D0, which is
 , in the
second column.
INSERT INTO UNITAB VALUES ('7A907',UX'D840DCD0','A');
Related concepts
Expanding conversion
An expanding conversion occurs when the length of the converted string is greater than that of the source
string.
Graphic string constants (Db2 SQL)
Related tasks
Loading data by using the cross-loader function (Db2 Utilities)
Related reference
LOAD (Db2 Utilities)
UTFs
Related information
UTF-8, UTF-16, UTF-32 & BOM (on Unicode Consortium website)
Inserting Unicode data into a non-Unicode table
If you insert Unicode data into an EBCDIC or ASCII table, use escaped data for those characters that
cannot be represented in the target encoding scheme. Using escaped data ensures that those characters
are preserved.
Procedure
To insert Unicode data into a non-Unicode table, perform one of the following actions:
• If the target table is ASCII, use the ASCII_STR function to generate escaped data for those characters [DB2Z-4-047]
that do not exist in ASCII.
• If the target table is EBCDIC, use the EBCDIC_STR function to generate escaped data for those [DB2Z-4-048]
characters that do not exist in EBCDIC.
Related concepts
Potential problems when inserting non-Unicode data into a Unicode table
If you insert EBCDIC or ASCII data into a Unicode table, the data is converted to Unicode. The length of
this converted data might increase so much that it causes the operation to fail.
Related tasks
Generating escaped Unicode data
If you pass Unicode characters to an application or object that is not intended to handle Unicode data,
data might be lost unless you escape certain characters. For example, you might need to pass Unicode
data through an application that has EBCDIC host variables. Or you might want to store Unicode data in a
non-Unicode table.
Related reference
ASCII_STR or ASCIISTR scalar function (Db2 SQL)
EBCDIC_STR scalar function (Db2 SQL) [DB2Z-4-049]





Converting existing Db2 data to Unicode
If your database and applications handle international data, consider converting your Db2 data to
Unicode. Using Unicode might prevent character conversions and thus improve performance and help
ensure data integrity. However, Unicode data might require more space. Depending on the data, these
characters can be two to three times the size of EBCDIC or ASCII characters.
Before you begin
Before you convert your existing Db2 data to Unicode, think about the following items:
• Consider the affects on all associated applications and tools. For example, consider the affects on any [DB2Z-4-050]
green screen applications.
• Understand where the data originally came from. If the data was already converted from its original [DB2Z-4-051]
form, it might contain substitution characters. If so, consider converting the data back to its original
form and then converting it to Unicode.
For example, suppose that you convert data from EBCDIC to Unicode, and the data was originally in
ASCII. You might need to convert the data to its original ASCII format and then to Unicode. Do this extra
conversion if the original ASCII data underwent a round-trip conversion to EBCDIC and not all of the
characters exist in EBCDIC. For example, suppose that you converted data from ASCII CCSID 1252 to
EBCDIC CCSID 37. CCSID 1252 contains characters that do not exist in CCSID 37. Thus, the EBCDIC
data has control characters in place of any characters that existed in ASCII but not in EBCDIC. (Consider
the example of the trademark symbol ™ in “Round-trip conversion”.) Converting the data to
ASCII first recovers the original values before you convert to Unicode.
Procedure
To convert existing Db2 data to Unicode:
1. Create one or more Unicode tables for this data. [DB2Z-4-052]
2. Use one of the following techniques to load the existing data into your new Unicode tables. Db2 [DB2Z-4-053]
converts the data to Unicode when it loads it.
• Use the INSERT statement with a subselect. [DB2Z-4-054]
INSERT INTO UNICODETABLE
     SELECT *
     FROM EBCDICTABLE;
For this example, make sure that the columns for both UNICODETABLE and EBCDICTABLE
are compatible. For example, if the first column of EBCDICTABLE is a character column, the
first column of UNICODETABLE should also be a character column; if the second column of
EBCDICTABLE is a numeric column, the second column of UNICODETABLE should also be a
numeric column.
• Use the UNLOAD utility to unload the data as is into an EBCDIC or ASCII data set. Then, LOAD [DB2Z-4-055]
the data into your new Unicode table. Specify the appropriate encoding scheme keyword (ASCII,
EBCDIC, or CCSID) in the LOAD statement.
Recommendation: Use the PUNCHDDN option of the UNLOAD utility to generate corresponding
LOAD utility statements for the data as Db2 unloads it.
The following example JCL performs the following actions:
– STEP1 creates and populates two tables. T1 is a Unicode table. T2 is an EBCDIC table.
– STEP2 unloads the data from EBCDIC table T2. The UNLOAD statement contains the PUNCHDDN
option. This option generates (in the SYSPUNCH data set) corresponding LOAD statements to
load the data back into the original table, T2. To use this SYSPUNCH file to load the unloaded
data to table T1, you must modify the SYSPUNCH or JCL.
– STEP3 then loads the data that was unloaded in STEP2 into Unicode table T1. Because the
catalog defines the table as Unicode, the data is converted to Unicode when it is loaded. [DB2Z-4-056]



– STEP4 outputs the current data in both tables.
//STEP1    EXEC  TSOBATCH
//SYSTSIN  DD *
DSN S(SSTR) R(1) T(1)
RUN PROGRAM DSNTEP2 PLAN(DSNTEPC1)
END
//SYSIN DD *
    DROP DATABASE DB1;
    COMMIT;
    CREATE DATABASE DB1 CCSID UNICODE;
    CREATE TABLESPACE TS1 IN DB1;
    CREATE TABLE T1 (C1 CHAR(7)) IN DB1.TS1; [DB2Z-4-057]

    DROP DATABASE DB2;
    COMMIT;
    CREATE DATABASE DB2 CCSID EBCDIC;
    CREATE TABLESPACE TS2 IN DB2;
    CREATE TABLE T2 (C1 CHAR(7)) IN DB2.TS2;
    INSERT INTO T2 VALUES ('ABCDEFG'); [DB2Z-4-058]

    INSERT INTO T1 (SELECT * FROM T2); [DB2Z-4-059]

    SELECT * FROM SYSADM.T1;
    SELECT * FROM SYSADM.T2;
/*
//***************************************************
//STEP2    EXEC DSNUPROC,UID='SMPLUNLD',UTPROC='',SYSTEM='SSTR'
//SYSPRINT DD SYSOUT=*
//SYSUT1   DD UNIT=SYSDA,SPACE=(CYL,(5,5),RLSE)
//SYSUT2   DD UNIT=SYSDA,SPACE=(CYL,(5,5),RLSE)
//SORTOUT  DD UNIT=SYSDA,SPACE=(CYL,(5,5),RLSE)
//SYSIN    DD *
  TEMPLATE REC DSN TEST123.STEP2.UNLOAD.SYSREC SPACE(15,5)
           CYL UNIT(3390) VOLUMES(SCR03)
  TEMPLATE CARD DSN TEST123.STEP2.UNLOAD.SYSPUNCH SPACE(15,5)
           CYL UNIT(3390) VOLUMES(SCR03)
  UNLOAD DATA FROM TABLE SYSADM.T2
     (C1  CHAR(7))
         UNLDDN REC PUNCHDDN CARD SHRLEVEL CHANGE
/*
/*
//**********************************************************************
//* DSNUPROC UTILITY STEP
//**********************************************************************
//STEP3    EXEC DSNUPROC,UID='LI848.LOAD1',TIME=1440,
//         UTPROC='',
//         SYSTEM='SSTR',DB2LEV=DB2A
//SYSUT1   DD DSN=TEST123.STEP3.LOAD.SYSUT1,DISP=(MOD,DELETE,CATLG),
//         UNIT=SYSDA,SPACE=(4000,(20,20),,,ROUND)
//SORTOUT  DD DSN=TEST123.STEP3.LOAD.SORTOUT,DISP=(MOD,DELETE,CATLG),
//         UNIT=SYSDA,SPACE=(4000,(20,20),,,ROUND)
//SYSMAP   DD DSN=TEST123.STEP3.LOAD.SYSMAP,DISP=(MOD,DELETE,CATLG),
//         UNIT=SYSDA,SPACE=(4000,(20,20),,,ROUND)
//SYSIN    DD *
TEMPLATE CWPH5APV
     DSN('TEST123.STEP2.UNLOAD.SYSREC')
     DISP(OLD,KEEP,KEEP)
LOAD DATA INDDN CWPH5APV LOG NO  RESUME YES
  EBCDIC CCSID(00037,00000,00000)
 INTO TABLE
 "SYSADM".
 "T1"
 WHEN(00001:00002) = X'0006'
 NUMRECS                    1
 ( "C1"
  POSITION(  00004:00010) CHAR MIXED(007)
                          NULLIF(00003)=X'FF'
 )
//********************************************************************
//STEP4    EXEC TSOBATCH,DB2LEV=DB2A
//SYSTSIN  DD *
DSN SYSTEM(SSTR)
RUN PROGRAM DSNTEP2 PLAN(DSNTEPC1))
//SYSIN    DD *
SELECT * FROM SYSADM.T1;
SELECT * FROM SYSADM.T2;
/*





• Use the cross-loader function to load the output of a dynamic SQL SELECT statement into your new [DB2Z-4-060]
Unicode table. The SELECT statement selects the entire table. In the following example, assume
that table T1 is in Unicode and table T2 is in EBCDIC. This example uses a cursor to select all data
from T2 and then load it into T1. This process is known as the cross-loader function. The data is
converted to Unicode when it is loaded.
//STEP5    EXEC DSNUPROC,UID='LOADIT',TIME=1440,COND=(EVEN),
//         UTPROC='',
//         SYSTEM='SSTR',DB2LEV=DB2A
//SYSUT1   DD DSN=TEST123.STEP5.LOAD.SYSUT1,DISP=(MOD,DELETE,CATLG),
//         UNIT=SYSDA,SPACE=(4000,(20,20),,,ROUND)
//SORTOUT  DD DSN=TEST123.STEP5.LOAD.SORTOUT,DISP=(MOD,DELETE,CATLG),
//         UNIT=SYSDA,SPACE=(4000,(20,20),,,ROUND)
//SYSCOPY  DD DSN=TEST123.STEP5.LOAD.COPY,DISP=(MOD,DELETE,CATLG),
//         UNIT=SYSDA,SPACE=(4000,(20,20),,,ROUND)
//SYSIN    DD *
EXEC SQL
  DECLARE C1 CURSOR FOR SELECT C1 FROM SYSADM.T2
ENDEXEC

LOAD DATA REPLACE INCURSOR C1 INTO TABLE SYSADM.T1 [DB2Z-4-061]

//********************************************************************
//STEP1    EXEC TSOBATCH,DB2LEV=DB2A
//SYSTSIN  DD *
DSN SYSTEM(SSTR)
RUN PROGRAM DSNTEP2 PLAN(DSNTEPC1)
//SYSIN    DD *
SELECT * FROM SYSADM.T1;
SELECT * FROM SYSADM.T2;
/*
3. Modify any SQL in your applications to account for length differences. If you use any length functions, [DB2Z-4-062]
such as CHARACTER_LENGTH and SUBSTRING, use the CODEUNITS16 and CODEUNITS32 options to
specify how you want Db2 to calculate the length.
Related concepts
Round-trip conversion
A round-trip conversion ensures the integrity of all character data from the source CCSID to the target
CCSID and back to the source. Even if the target CCSID does not support a given character, the character
regains its original hexadecimal value after it is converted back to the source CCSID.
Related tasks
Generating escaped Unicode data
If you pass Unicode characters to an application or object that is not intended to handle Unicode data,
data might be lost unless you escape certain characters. For example, you might need to pass Unicode
data through an application that has EBCDIC host variables. Or you might want to store Unicode data in a
non-Unicode table.
Specifying how Db2 calculates the length of a string
Loading data by using the cross-loader function (Db2 Utilities)
Related reference
INSERT statement (Db2 SQL)
LOAD (Db2 Utilities)
UNLOAD (Db2 Utilities)
Effects on access paths when converting data to Unicode
If you convert your data to Unicode, the access paths for queries on that data do not change simply
because the data is now in Unicode. However, valid reasons might exist for a change in the access path for
a Unicode table.
For example, the schemas are likely different in Unicode tables than EBCDIC tables. The index key might
be longer. For example, an index might be 5 levels in Unicode instead of 4 levels in EBCDIC. Also, the
number of rows per page might be fewer.
All of the regular rules for access paths and tuning queries still apply to Unicode tables. [DB2Z-4-063]



Related tasks
Writing efficient SQL queries (Db2 Performance) [DB2Z-4-064]






