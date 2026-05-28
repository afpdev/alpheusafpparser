Chapter 5. Application programming with Unicode
data and multiple CCSIDs
If your application handles Unicode data or data that is in different encoding schemes, you should be
aware of several programming techniques and recommendations in Db2.
Db2 always returns data to your application in the CCSID that your application uses for data. This CCSID is
called the application encoding scheme.
Tip: Use the following general recommendations to guide you in writing and preparing your application
programs.
• If possible, use either Unicode or EBCDIC data, but not both. If you do choose to use multiple encoding [DB2Z-5-001]
schemes, consider the following possible implications for data loss and performance:
– Managing multiple CCSIDs in your application can be difficult. To ensure that data is not lost, you
have to control where the data goes, a path that potentially includes many modules.
– Many environments, such as CICS Transaction Gateway and IBM MQ are message-based. In these
cases, the entire message must be in a single encoding scheme. Because the entire message is in
one encoding, flowing some data through the application in EBCDIC and some in Unicode makes little
sense. You still have to convert all of it to a single encoding, such as Unicode, right before the putting
the message on the wire.
– Db2 tables must be in the same encoding scheme. You cannot make some columns Unicode and
some EBCDIC. If your application processes some columns in Unicode and others in EBCDIC,
character conversion occurs, which likely increases the performance overhead.
• If you are using Unicode data in COBOL or PL/I applications, use the coprocessor. [DB2Z-5-002]
• If your COBOL, PL/I, C/C++ , or Assembler application handles Unicode data, do not place literals in the [DB2Z-5-003]
source code of the application. Because these language compilers do not support Unicode source code,
they could misinterpret these literal values. Instead, place these literal values in a file or Db2 table
that can be accessed at the start of the program to load the values. (Files and host variables are not
precompiled and compiled as application source code.)
• If an expanding or contracting conversion occurs on your data, the length of the data might change. [DB2Z-5-004]
Be aware of these length changes when you use the LENGTH function, CHARACTER_LENGTH function,
SUBSTRING function, and SUBSTR function on the converted string. For CHARACTER_LENGTH and
SUBSTRING, use the CODEUNITS16 and CODEUNITS32 options to specify how you want Db2 to
calculate the length.
• If you need to represent characters from multiple Latin-based character sets, such as Latin-1 and [DB2Z-5-005]
Latin-4, consider using Unicode for your application encoding scheme. An SBCS CCSID does not have
enough code points to represent all of the characters that the combination of the two character sets
require. For example, assume that your application uses an EBCDIC CCSID, such as 277 or 1069. You
might have some data that is represented in the database in Unicode but that cannot be retrieved by the
application without substitution. If your application needs to handle only one language at a time, you
can set up your infrastructure in one of the following ways:
– Have one version of your application that uses CCSID 277 and another version that uses CCSID 1069.
Also have two corresponding subsystems, one that uses CCSID 277 and another that uses CCSID
1069. (You cannot have multiple EBCDIC CCSIDs in one Db2 subsystem.) [DB2Z-5-006]
– Store the data in Unicode and have one version of your application that uses CCSID 277 and another
version that uses CCSID 1069. Then bind these applications with different values for the ENCODING
bind option.
– Store the data in Unicode and have one version of your application that uses an EBCDIC CCSID and
another version that uses Unicode.
However, if you require that a single version of the application handle both Latin-1 and Latin-4 character
sets, your application needs to process data in Unicode. [DB2Z-5-007]




Related concepts
Contracting conversion
A contracting conversion occurs when the length of the converted string is smaller than that of the source
string.
Expanding conversion
An expanding conversion occurs when the length of the converted string is greater than that of the source
string.
Application encoding schemes and Db2 ODBC (Db2 Programming for ODBC)
Related tasks
Specifying how Db2 calculates the length of a string
Generating table and view declarations by using DCLGEN (Db2 Application programming and SQL)
Related reference
LENGTH scalar function (Db2 SQL)
SUBSTR scalar function (Db2 SQL)
SUBSTRING scalar function (Db2 SQL)
Enterprise PL/I for z/OS
Application encoding scheme
The application encoding scheme is the CCSID that your application uses to interpret data in host
variables. For Db2 for z/OS applications, typically the application encoding scheme is the value of the
ENCODING bind option. (By default this value is the subsystem default application encoding scheme.)
However, you can also set the CCSID of application data by using the DECLARE VARIABLE statement with
the CCSID option or the CURRENT APPLICATION ENCODING SCHEME special register. If you are using the
Db2 coprocessor, you can use various language compiler options to override the Db2 application encoding
scheme for an application. For detailed instructions on how to set the application encoding scheme, see
“Specifying a CCSID for your application”.
Db2 automatically converts any data that you select to the application encoding scheme. For example, if
you use SPUFI to select catalog data (which is in CCSID 1208), Db2 converts the data to the application
CCSID of SPUFI. Your version of SPUFI should be bound with a CCSID that matches the CCSID of your
terminal emulator. (By default, SPUFI is bound with CCSID 37. However, you can bind different versions
of SPUFI with different CCSIDs.) Assume that you are following this good practice of having your SPUFI
CCSID match your terminal emulator CCSID. In this case, any character in the selected data that does not
exist in the CCSID of your terminal emulator is not displayed correctly. For example, if SPUFI and your
terminal emulator are set to CCSID 37, the Euro symbol (€) can not be displayed.
Related concepts
Encoding schemes
An encoding scheme standardizes the encoding of character sets by defining a set of rules for representing
character data. Each encoding scheme consists of a number of code pages that adhere to its rules. For
example, code pages 37, 500, and 1047 are all part of the EBCDIC encoding scheme.
Related reference
Character conversion terminology [DB2Z-5-008]



To understand the concept of character conversion, you should know the meaning of some basic related
terms.
Specifying a CCSID for your application
In Db2 for z/OS applications, one CCSID is associated with the source code and one or more CCSIDs can
be associated with the data that your application manipulates. The CCSID that Db2 associates with the
data is called the application encoding scheme.
Before you begin
If the CCSID values for your application do not match the actual CCSID of the data or source, data
corruption might occur.
Important: For best results, try to avoid character conversions whenever possible, because conversions
can potentially slow performance and sometimes cause data loss. The best way to avoid conversions is to
use the same CCSID for all of your data. For more information, see “Possible consequences of character
conversion”.
Procedure
To specify a CCSID for your application, use the following approaches:
1. Use the options as shown in the following table: [DB2Z-5-009]
Table 18. Options to set application CCSIDs [DB2Z-5-010]

| Item for which you want to specify the CCSID | Option to use |
| :--- | :--- |
| Application source code (which includes SQL statements and literal strings in the SQL statements) | If you are using the Db2 precompiler, use the CCSID SQL processing option when you precompile the application. Specify the same CCSID when you compile the application. <br><br> If you are using the Db2 coprocessor, use the language compiler to set the CCSID. For COBOL, PL/I, and C/C++, use the following instructions<sup>2</sup>: <ul><li>“Specifying CCSIDs for COBOL applications when using the Db2 coprocessor”</li><li>“Specifying CCSIDs for PL/I applications when using the Db2 coprocessor”</li><li>“Specifying CCSIDs for C/C++ applications when using the Db2 coprocessor”</li></ul> The default CCSID for the application source code is the subsystem EBCDIC CCSID (DECP value SCCSID or MCCSID). Db2 uses this value if you do not use one of the preceding mechanisms to specify a CCSID. <br><br> **Restriction:** The compilers for high level host languages do not support Unicode source code. [DB2Z-5-011]|
| Application data (values that are passed through host variables and parameter markers) within SQL statements<sup>3</sup> | You can use one or more of the following Db2 approaches to specify CCSID values for the application data<sup>4</sup>, which is called the application encoding scheme: <ul><li>Use the ENCODING bind option.<sup>5</sup> This option typically yields the best performance.</li><li>You can override the CCSID for a particular host variable by using the DECLARE VARIABLE statement with the CCSID option.</li><li>You can override the CCSID for parameter markers in dynamic SQL by specifying the CURRENT APPLICATION ENCODING SCHEME special register. Db2 uses the value of this special register at the time that the statement is executed.</li><li>If you use an SQL descriptor to define application data the previous options in this list might not apply if CCSID overrides are defined for host variables and parameter markers in the “SQL descriptor (SQLDA)”.</li></ul> The default CCSID for the application data is the subsystem default application encoding scheme. For static SQL (host variables), this value is the APPENSCH value from the DECP that is loaded when you bind your application. For dynamic SQL (parameter markers), this value is the APPENSCH value from the DECP that is loaded at the time that the application is executed. <br><br> Alternatively, if you are using the Db2 coprocessor for COBOL or PL/I applications, you can override the ENCODING bind option by using language-specific compiler options. For more information, see the following topics: <ul><li>“Specifying CCSIDs for COBOL applications when using the Db2 coprocessor”</li><li>“Specifying CCSIDs for PL/I applications when using the Db2 coprocessor”</li></ul> [DB2Z-5-012]|
| Application data that is referenced outside of SQL statements | Use the rules of the programming language. In some cases, the CCSID of this data is the same as the CCSID of the source code. [DB2Z-5-013]|

<sup>2</sup> For older compilers that do not pass a CCSID value to the Db2 coprocessor, use the SQL compiler option with the CCSID suboption to specify a value.
<sup>3</sup> You can specify different CCSIDs for different pieces of data in one application. However, if you specify multiple CCSIDs, do so with caution.
<sup>4</sup> Use caution if you specify different CCSIDs for different pieces of data, which can result in character conversion.
<sup>5</sup> For DRDA applications, the ENCODING bind option does not set the CCSID of the data, and CCSIDs are communicated as part of the protocol.
2. Optional: If you want to confirm which CCSID value the Db2 precompiler used, look at the precompiler [DB2Z-5-014]
listing. If you want to confirm which CCSID value the Db2 coprocessor used, look at the compiler
listing.
If you need help finding the CCSID values in these listings, see the example listings in “Finding the
CCSID values of your data sources”.
You can also use these listings to confirm which DECP module Db2 used. Knowing which DECP module
is useful if you modified a DECP value, such as APPENSCH, before you compiled or executed your
program. You can see which DECP module and which values Db2 used.
3 You can specify different CCSIDs for different pieces of data in one application. However, if you specify [DB2Z-5-015]
multiple CCSIDs, do so with caution.
4 Use caution if you specify different CCSIDs for different pieces of data, which can result in character [DB2Z-5-016]
conversion.
5 For DRDA applications, the ENCODING bind option does not set the CCSID of the data, and CCSIDs are [DB2Z-5-017]
communicated as part of the protocol. [DB2Z-5-018]



Related concepts
International data and character conversion in Db2 for z/OS
In computers, all characters are encoded according to the rules of a particular encoding scheme and
code page. If your database and applications handle data from multiple code pages, that data might be
converted at certain times from one code page to another. This conversion process is called character
conversion.
Character conversion (Introduction to Db2 for z/OS)
Encoding scheme and CCSID rules for strings (Introduction to Db2 for z/OS)
Objects with different CCSIDs in the same SQL statement
You can reference data with different CCSIDs from the same SQL statement. This ability is useful if you
use table objects such as tables, views, temporary tables, query tables, and user-defined functions with
different CCSIDs. However, you should understand how Db2 for z/OS processes these queries so that you
can code them correctly.
Related reference
Descriptions of SQL processing options (Db2 Application programming and SQL)
Subsystem CCSIDs and encoding schemes
Each Db2 subsystem has a set of default CCSID and encoding scheme values. Db2 uses these values for
objects and applications that do not otherwise have a CCSID associated with them.
Details of CCSID options for application programs
You have several options in Db2 to set CCSIDs for your applications.
For the overall context of when to use each option, see “Specifying a CCSID for your application”
 The following sections explain the details of each option.
CCSID SQL processing option
If you are using the Db2 precompiler, use this option to specify the CCSID in which the source program
is written. This value ensures that the Db2 precompiler correctly parses the SQL statements and literal
string values in those statements at precompile time. The default value is the subsystem EBCDIC CCSID
(DECP value SCCSID if MIXED=NO or MCCSID if MIXED=YES). For more information, see EBCDIC CCSID
field (SCCSID, MCCSID, and GCCSID DECP values) (Db2 Installation and Migration).
Db2 converts the source code from the specified CCSID to Unicode UTF-8 before it is processed by the
precompiler. The precompiler then parses the source code in Unicode UTF-8.
The value that you specify for the precompiler must match the value that you specify to the compiler
when you compile the program.
If you are using the Db2 coprocessor, do not specify this option. Instead, use the language compiler
options. The coprocessor uses the CCSID that is passed to it from the language compiler to convert
the SQL statement text. If the compiler does not pass a CCSID, the Db2 coprocessor uses the CCSID
suboption of the compiler SQL option. If that suboption is not specified, the Db2 coprocessor uses the
subsystem EBCDIC CCSID (DECP value SCCSID or MCCSID) as the CCSID for the source.
ENCODING bind option
Use this option when you bind your application to specify the CCSID of the data in your application. This
value applies to both host variables in static SQL statements and parameter markers in dynamic SQL
statements unless this value is overridden. For example, you can override the CCSID of host variables
by using certain language compiler options or by specifying a DECLARE VARIABLE statement with the
CCSID option. You can override the CCSID of parameter markers in dynamic SQL statements by using the
CURRENT APPLICATION ENCODING SCHEME special register.
This value can be EBCDIC, ASCII, Unicode, or a valid CCSID. If the value is EBCDIC, ASCII, or Unicode,
Db2 uses the subsystem default CCSID for that encoding scheme. [DB2Z-5-019]


  77



Differences between Unicode and EBCDIC sorting sequences
In Unicode, numeric characters are sorted before alphabetic characters. In EBCDIC, alphabetic
characters are sorted before numeric characters.
Because the Db2 catalog is stored in Unicode, any queries that you issue against Unicode tables in the
catalog use the Unicode sorting sequence.
Also, consider any SQL statements that include syntax that requires that the data be sorted. Examples
of such syntax include the GROUP BY clause, range predicates such as BETWEEN, and functions such
as MIN and MAX. These statements might return different results when they are issued on Unicode data
than on EBCDIC data.
The following table shows some example encoding differences to consider when specifying these clauses,
predicates, and functions in your SQL statements.
Table 21. Example encoding differences [DB2Z-5-020]

| EBCDIC: Characters | EBCDIC: Hexadecimal value | Unicode and ASCII: Characters | Unicode and ASCII: Hexadecimal value |
| :--- | :--- | :--- | :--- |
| space | X'40' | space | X'20' [DB2Z-5-021]|
| lowercase characters | X'81' - X'89', X'91' - X'99', X'A1' - X'A9' | uppercase characters | X'41' - X'5A' [DB2Z-5-022]|
| numerals | X'F0' - X'F9' | numerals | X'30' - X'39' [DB2Z-5-023]|
| uppercase characters | X'C1' - X'C9', X'D1' - X'D9', X'E1' - X'E9' | lowercase characters | X'61' - X'7A' [DB2Z-5-024]|
Equal predicates are not affected by the different sorting sequences.
Examples
GUPI
For the following examples, assume that a table called MYTABLES has a NAME column that is type
VARCHAR(128). This column contains the following values: TEST1, TEST2, TEST3, TESTA, TESTB, and
TESTC.
Example query with ORDER BY
Suppose that you issue the following SQL query:
SELECT NAME FROM MYTABLES
ORDER BY NAME
If MYTABLES is encoded in Unicode, Db2 returns the following result:
TEST1
TEST2
TEST3
TESTA
TESTB
TESTC



If MYTABLES is encoded in EBCDIC, Db2 returns the following result:
TESTA
TESTB
TESTC
TEST1
TEST2
TEST3
Example of query with ORDER BY and BETWEEN predicate
Assume that you issue the following SQL query:
SELECT * FROM MYTABLES
WHERE NAME BETWEEN 'TEST2' AND 'TESTB'
ORDER BY NAME
If MYTABLES is encoded in Unicode, Db2 returns the following result:
TEST3
TESTA
If MYTABLES is encoded in EBCDIC, Db2 returns the following result:
TESTC
TEST1
Example of simulating the EBCDIC sorting sequence
To simulate the behavior of the ORDER BY clause on EBCDIC data, use the CAST function and the
ORDER BY clause when you query the Db2 catalog or other Unicode data.
Suppose that MYTABLES is encoded in Unicode. You can modify the preceding query as follows to
return the Unicode data in the same order that you would expect for EBCDIC data:
SELECT CAST(NAME AS VARCHAR(128) CCSID EBCDIC) AS E_NAME
FROM MYTABLES
ORDER BY E_NAME
Db2 returns the following result:
TESTA
TESTB
TESTC
TEST1
TEST2
TEST3
However, be aware that, in this situation, Db2 cannot use an index to support the ORDER BY clause.
Db2 must sort the data.
You can also apply this same technique to a catalog table in UTF-8, as shown in the following example
SELECT CAST(NAME AS VARCHAR(128) CCSID EBCDIC) AS E_NAME
FROM SYSIBM.SYSTABLES
ORDER BY E_NAME
If the NAMES column of SYSIBM.SYSTABLES contains the values TEST1, TEST2, TEST3, TESTA,
TESTB, and TESTC, Db2 returns the following result:
TESTA
TESTB
TESTC
TEST1
TEST2
TEST3
GUPI


  79



Specifying how Db2 calculates the length of a string
If you use certain length functions, you can specify whether you want Db2 to calculate the length by bytes
or characters. This distinction is important for multibyte characters. If you convert Db2 data to Unicode
and the data expands, consider updating some of these function calls to specify the appropriate unit of
measurement.
For example, consider the string Jürgen in UTF-8. This string consists of 6 characters. However, it takes 7
bytes of storage, because the character ü takes 2 bytes in UTF-8. You can specify whether you want Db2
to count the length as 6 or 7.
The key is to specify the size code unit that you want Db2 to use when calculating the length. A code unit
is the minimal bit combination that can represent a character.
Procedure
If you are using any of the following length functions, specify the appropriate unit of measurement:
GUPI
Applicable functions:
• CHARACTER_LENGTH [DB2Z-5-025]
• CLOB
• DBCLOB [DB2Z-5-026]
• GRAPHIC [DB2Z-5-027]
• LEFT
• LOCATE [DB2Z-5-028]
• LOCATE_IN_STRING [DB2Z-5-029]
• OVERLAY [DB2Z-5-030]
• POSITION [DB2Z-5-031]
• RIGHT
• SUBSTRING [DB2Z-5-032]
• VARCHAR [DB2Z-5-033]
• VARGRAPHIC [DB2Z-5-034]
GUPI
Options to specify unit of measurement:
CODEUNITS16
Specifies that Db2 is to count the length by 16-bit (or 2-byte) code units. For every character that is 2
bytes or less in the string, Db2 counts a length of 1.
CODEUNITS32
Specifies that Db2 is to count the length by 32-bit (or 4-byte) code units. For every character that is 4
bytes or less in the string, Db2 counts a length of 1. CODEUNITS32 always returns the same value as
CODEUNITS16 unless you have supplementary characters.
OCTETS
Specifies that Db2 is to count the length by bytes. For every byte in the string, Db2 counts a length of
1.
The OCTETS option is not available for all of the listed functions.
Examples
GUPI



Example: CHARACTER_LENGTH
Assume that NAME is a VARCHAR(128) column that is encoded in Unicode UTF-8 and contains
'Jürgen'. The character ü requires two bytes in UTF-8.
The following two queries both return the value 6, because Db2 counts the string Jürgen as 6
characters. In the first query, CODEUNITS32 means that any character that is 4 bytes or less is
counted as 1. In the second query, CODEUNITS16 means that any character that is 2 bytes or less is
counted as 1. In both cases, the result is the same.
SELECT CHARACTER_LENGTH(NAME,CODEUNITS32)
        FROM T1 WHERE NAME = 'Jürgen';
SELECT CHARACTER_LENGTH(NAME,CODEUNITS16)
        FROM T1 WHERE NAME = 'Jürgen';
However the following two queries return the value 7, because the string contains 7 bytes. In the
first query, OCTETS means that length is to be calculated in bytes. In the second query, the LENGTH
function always counts by bytes.
SELECT CHARACTER_LENGTH(NAME,OCTETS)
        FROM T1 WHERE NAME = 'Jürgen';
SELECT LENGTH(NAME)
        FROM T1 WHERE NAME = 'Jürgen';
Example: LOCATE_IN_STRING
The LOCATE_IN_STRING function returns the position at which an occurrence of an argument starts
within a specified string. This function is similar to POSITION, but adds a parameter to specify
which instance of the search string to find. The following statement sets the value of the host
variable POSITION to 26, because the character ß is the 26th character in the string. In this case,
CODEUNITS32 means that any character that is 4 bytes or less is counted as 1.
SET :POSITION = LOCATE_IN_STRING('Jürgen lives on Hegelstraße','ß',-1,CODEUNITS32);
-- search from end [DB2Z-5-035]
The following statement sets the value of the host variable POSITION to 6. Db2 starts at position 1
and looks for the third occurrence of the character N. In this case, OCTETS means that Db2 counts the
length by bytes.
SET :POSITION = LOCATE_IN_STRING('WINNING','N',1,3,OCTETS);
Examples of other length functions
The following table shows examples of the CODEUNITS16, CODEUNITS32, and OCTET options.
Table 22. Examples of length functions [DB2Z-5-036]

| Function | Result | Hexadecimal result value |
| :--- | :--- | :--- |
| LEFT('Jürgen', 2, CODEUNITS32) | 'Jü' | X'4AC3BC' [DB2Z-5-037]|
| LEFT('Jürgen', 2, CODEUNITS16) | 'Jü' | X'4AC3BC' [DB2Z-5-038]|
| LEFT('Jürgen', 2, OCTETS) | 'J ' | X'4A20' (a truncated string) [DB2Z-5-039]|
| LEFT('Jürgen', 2) | 'J?' | X'4AC3' (The letter 'J' and a partial character)<sup>1</sup> [DB2Z-5-040]|
| RIGHT('Jürgen', 5, CODEUNITS32) | 'ürgen' | X'C3BC7267656E' [DB2Z-5-041]|
| RIGHT('Jürgen', 5, CODEUNITS16) | 'ürgen' | X'C3BC7267656E' [DB2Z-5-042]|
| RIGHT('Jürgen', 5, OCTETS) | 'rgen' | X'207267656E' (a truncated string) [DB2Z-5-043]|
| RIGHT('Jürgen', 5) | '?rgen' | X'BC7267656E' (a partial character followed by 'rgen')<sup>1</sup> [DB2Z-5-044]|
| SUBSTRING('Jürgen', 1, 2, CODEUNITS32) | 'Jü' | X'4AC3BC' [DB2Z-5-045]|
| SUBSTRING('Jürgen', 1, 2, CODEUNITS16) | 'Jü' | X'4AC3BC' [DB2Z-5-046]|
| SUBSTRING('Jürgen', 1, 2, OCTETS) | 'J ' | X'4A20' (a truncated string) [DB2Z-5-047]|
| SUBSTR('Jürgen', 1, 2) | 'J?' | X'4AC3' (a partial character) [DB2Z-5-048]|
| SUBSTRING('Jürgen', 8, CODEUNITS16) | '' | a zero-length string [DB2Z-5-049]|
| SUBSTRING('Jürgen', 8, 4, OCTETS) | '' | a zero-length string [DB2Z-5-050]|

<sup>1</sup> If conversion occurs on a string with a partial character, SQLCODE -330 results.
GUPI
Related concepts
Difference between CODEUNITS16 and CODEUNITS32 (Db2 SQL)
String unit specifications (Db2 SQL)
Related reference
CHARACTER_LENGTH or CHAR_LENGTH scalar function (Db2 SQL)
LEFT or STRLEFT scalar function (Db2 SQL)
LENGTH scalar function (Db2 SQL)
LOCATE_IN_STRING scalar function (Db2 SQL)
RIGHT or STRRIGHT scalar function (Db2 SQL)
SUBSTR scalar function (Db2 SQL)
SUBSTRING scalar function (Db2 SQL)
Specifying the sorting sequence for a language
If your application sorts non-English data, you should specify the sorting sequence to ensure that Db2
sorts the data in a culturally correct manner.
For example, suppose your data contains the following strings: cote, coté, côte, côté. You need to specify
how you want these strings sorted.
Procedure
To specify the sorting sequence for a language, perform one of the following actions: [DB2Z-5-051]



• In your SQL statement, use the COLLATION_KEY function with the collation-name parameter to [DB2Z-5-052]
specify a particular sorting sequence.
A collation name specifies how Db2 is to sort data. It specifies attributes such as the language of
the data, whether case should be considered, and how punctuation characters should be treated. You
must specify a value that is acceptable for the z/OS CUNBOPR_Collation_Keyword parameter.
The COLLATION_KEY function returns a binary value that can be used to sort data according to the
rules that are specified in the Unicode Collation algorithm.
GUPI
For example, suppose that you issue the following query:
SELECT FIRSTNAME, LASTNAME
FROM EMPLOYEE
ORDER BY COLLATION_KEY(LASTNAME, 'UCA400R1_AS_LSV_S2');
GUPI
This query orders the employees by their surnames (in the LASTNAME column) based on the
following options that are specified in the collation name UCA400R1_AS_LSV_S2:
Table 23. Example collation options and corresponding collation keywords [DB2Z-5-053]

| Corresponding collation keyword | Option |
| :--- | :--- |
| UCA400R1 | Use Unicode Collation Algorithm (UCA) version 4.0.1 [DB2Z-5-054]|
| AS | Ignore spaces, punctuation and symbols [DB2Z-5-055]|
| LSV | Use Swedish linguistic conventions [DB2Z-5-056]|
| S2 | Compare case-insensitively [DB2Z-5-057]|

• Create an index that maintains the sorting sequence by using the COLLATION_KEY function in the CREATE INDEX statement. [DB2Z-5-058]

Invoking the COLLATION_KEY function for every row in the table can slow performance. Creating an [DB2Z-5-059]

Table 24. Example collation options and corresponding collation keywords (continued) [DB2Z-5-060]

| Corresponding collation keyword | Option |
| :--- | :--- |
| FO | Specifies that the French sorting attribute is to be used. (F = French attribute, O = On) Strings are to be sorted by examining the accents starting from the end of the string. This attribute is automatically set to on for the French locales. Therefore, in this case, it is not required. [DB2Z-5-061]|
You might want to check if you can improve the performance of this query by creating an index on C1
that is based on the collation key. The following example statements show how to create such an index
and use EXPLAIN statements to confirm that the index is used for faster access. You can view the
results of the EXPLAIN statements by querying the plan table. The EXPLAIN output for this example
shows only some of the plan table columns.
EXPLAIN ALL SET QUERYNO = 110 FOR SELECT C1 FROM T1
   ORDER BY COLLATION_KEY(C1,'UCA410_LFR_FO');
CREATE INDEX I1 ON T1 (COLLATION_KEY(C1,'UCA410_LFR_FO'));
EXPLAIN ALL SET QUERYNO = 210 FOR SELECT C1 FROM T1
   ORDER BY COLLATION_KEY(C1,'UCA410_LFR_FO');
SELECT * FROM PLAN_TABLE;
The last statement returns the following output: [DB2Z-5-062]

+----------------------------------------------------------------------------------------- [DB2Z-5-063]
     | QUERYNO | QBLOCKNO | PROGNAME | PLANNO | METHOD | CREATOR | TNAME | TABNO [DB2Z-5-064]|
ACCESSTYPE [DB2Z-5-065]|

+----------------------------------------------------------------------------------------- [DB2Z-5-066]
   1_|     110 |        1 | DSNTEP2  |      1 |      0 | ADMF001 | T1    |     1 [DB2Z-5-067]|
R [DB2Z-5-068]|
   2_|     110 |        1 | DSNTEP2  |      2 |      3 |         |       |     0 [DB2Z-5-069]
|            |
   3_|     210 |        1 | DSNTEP2  |      1 |      0 | ADMF001 | T1    |     1 [DB2Z-5-070]|
I [DB2Z-5-071]|

+-----------------------------------------------------------------------------------------
     -------------------------------------------------------------------------------- [DB2Z-5-072]
     | MATCHCOLS | ACCESSNAME | INDEXONLY | SORTN_UNIQ | SORTN_JOIN | SORTN_ORDERBY [DB2Z-5-073]|
     -------------------------------------------------------------------------------- [DB2Z-5-074]
   1_|         0 |            | N         | N          | N          | N [DB2Z-5-075]|
   2_|         0 |            | N         | N          | N          | N [DB2Z-5-076]|
   3_|         0 | I1         | N         | N          | N          | N [DB2Z-5-077]|
     --------------------------------------------------------------------------------
     ---------------------------------------------------------------------------------------- [DB2Z-5-078]
     | SORTN_GROUPBY | SORTC_UNIQ | SORTC_JOIN | SORTC_ORDERBY | SORTC_GROUPBY | PREFETCH [DB2Z-5-079]|

---------------------------------------------------------------------------------------- [DB2Z-5-080]
   1_| N             | N          | N          | N             | N             |  S [DB2Z-5-081]|
   2_| N             | N          | N          | Y             | N [DB2Z-5-082]|            |
   3_| N             | N          | N          | N             | N [DB2Z-5-083]|            |

----------------------------------------------------------------------------------------
Related reference
COLLATION_KEY scalar function (Db2 SQL)
CREATE INDEX statement (Db2 SQL)
EXPLAIN statement (Db2 SQL)
Related information
Unicode Technical Standard #10: Unicode Collation Algorithm [DB2Z-5-084]



Performing culturally correct case conversions
Rules for uppercase and lowercase conversion vary according to language and country. If you plan to use
the UPPER or LOWER function, you need to ensure that Db2 uses the culturally correct casing rules. For
example, you need to tell Db2 how to convert characters such as ß and ó to uppercase.
Before you begin
Before you use the UPPER or LOWER function on Unicode or ASCII data, you need to set up z/OS Unicode
Services.
Procedure
To ensure that Db2 uses the correct casing rules for a language and country:
• When you use the UPPER function or LOWER function, ensure that Db2 uses the appropriate locale by [DB2Z-5-085]
performing one of the following actions:
– Specify a value for the locale-name parameter of the UPPER or LOWER function:
- For EBCDIC data, specify an LE locale, such as En_US or Fr_FR. [DB2Z-5-086]
- For Unicode and ASCII data, specify a locale value that is supported by the case conversion [DB2Z-5-087]
service of z/OS Unicode Services, such as EN_US. For a list of locale values that are supported
by the case conversion service, see Locales supported for case service (z/OS: Unicode Services
User’s Guide and Reference). You can also specify the values UNI, UNI_60, or UNI_90, which
mean that the case conversion service of z/OS Unicode Services is to use the normal and special
casing rules.
– If you do not specify a value for the locale-name parameter of the UPPER and LOWER function,
ensure that the value of the CURRENT LOCALE LC_CTYPE special register is correct. You can change
the value by using the SET CURRENT LOCALE LC_CTYPE statement.
As an alternative to using the UPPER function, you can use the TRANSLATE function with only one
parameter. In both cases, the strings are converted to uppercase.
Example
GUPI
Example 1
The following statements show how to ensure that the German character ß is handled correctly when
Db2 converts it to upper case. In uppercase, ß should be 'SS.'
The first set of statements creates a table, inserts one row, and confirms that the value Hegelstraße
was properly inserted.
CREATE TABLE T1 (C1 VARCHAR(15)) VOLATILE CCSID UNICODE;
INSERT INTO T1 VALUES('Hegelstraße',1);
SELECT C1 FROM T1 ;
The SELECT statement returns the following result:
C1
Hegelstraße
If you do not specify a locale when you use the UPPER function on this value, the result is technically
incorrect, as shown in the following example. In upper case, the German ß should be converted to SS.
SELECT UPPER(C1)AS C1 FROM T1 ;
This SELECT statement returns the following result: [DB2Z-5-088]


  85



C1
HEGELSTRAßE
The following query returns output with the German ß correctly converted to SS.
SELECT UPPER(C1,'De_DE') AS C1 FROM T1 ;
This SELECT statement returns the following result:
C1
HEGELSTRASSE
This query works correctly, because the locale De_DE is passed as a parameter to the UPPER function.
Example 2
Suppose that table T1 contains the Unicode data Chrysóstomo in column C1. Assume that you issue
the following query with the UPPER function.
SELECT UPPER(C1)AS C1 FROM T1 ;
If you did not add the CASE SPECIAL and CASE LOCALE statements to your conversion image when
setting up z/OS Unicode Services, this query returns the following result:
CHRYSóSTOMO
However, after setting up the conversion image with the CASE SPECIAL and CASE LOCALE statements
and setting the LOCALE special register, you get the following correct result:
CHRYSÓSTOMO
Be aware that the UPPER function can result in expansion if the text contains certain characters,
such as ó in this example. Ensure that the result string is large enough to contain the result of the
expression.
GUPI
Related reference
LOWER scalar function (Db2 SQL)
UPPER scalar function (Db2 SQL)
TRANSLATE scalar function (Db2 SQL)
CURRENT LOCALE LC_CTYPE special register (Db2 SQL)
Internationalization: Locales and Character Sets (XL C/C++ Programming Guide)
Locales for collation and case support (z/OS: Unicode Services User’s Guide and Reference)
Locale
A locale defines your cultural environment. Specifying the correct locale ensures that Db2 handles case
conversions and sorts according to the rules for a particular language.
You can set the locale for your subsystem by using the CURRENT LOCALE LC_CTYPE special register.
Alternatively, you can specify a locale when you perform specific functions that depend on locale, such as
UPPER and LOWER.
Depending on the encoding scheme of the data, use one of the following locale formats:
LE locales
Specify this locale format for EBCDIC data. [DB2Z-5-089]



An LE locale consists of two components: the first component represents a specific language and
country, and the second component is a CCSID. For example, in the locale Fr_CA.IBM-1047, Fr_CA
represents the language and country (French Canadian), and IBM-1047 is the associated CCSID.
When you specify an LE locale to Db2 for z/OS, specify only the first component, which is the language
and country. Db2 appends "IBM-" and the CCSID.
The following table shows some example LE locales that you can specify to Db2.
Table 25. Examples of LE locales that you can specify [DB2Z-5-090]

| Locale | Language | Country |
| :--- | :--- | :--- |
| En_US | English | United States [DB2Z-5-091]|
| De_CH | German | Switzerland [DB2Z-5-092]|
| De_DE | German | Germany [DB2Z-5-093]|
| Fr_CA | French | Canada [DB2Z-5-094]|
| It_IT | Italian | Italy [DB2Z-5-095]|
| Ja_JP | Japanese | Japan [DB2Z-5-096]|
For a complete list of supported LE locales, see Compiled locales (LE locales) (XL C/C++ Programming
Guide).
z/OS Unicode Services
Specify one of the following z/OS Unicode Services locale formats for ASCII and Unicode data.
Locale format for case conversion services
Use this format when you need a locale to affect how data is converted to uppercase and
lowercase, such as in the UPPER and LOWER functions.
The locale format for case conversion is Lxx_Ryy where:
Lxx
Language
Ryy
Region
You can use any of the locale values that are supported by z/OS Unicode Services for
CUNBAPRM_Locale (31-bit) or CUN4BAPR_Locale (64-bit). The following table lists some
example locale values for case conversion services.
Table 26. Example locale values for the case conversion services of z/OS Unicode Services [DB2Z-5-097]

| Locale value | Language | Region |
| :--- | :--- | :--- |
| Cs_CZ | Czech | Czech Republic [DB2Z-5-098]|
| De_DE | German | Germany [DB2Z-5-099]|
| En_US | English | United States [DB2Z-5-100]|
| En_GB | English | Great Britain [DB2Z-5-101]|
| Es_MX | Spanish | Mexico [DB2Z-5-102]|
| Fr_FR | French | France [DB2Z-5-103]|
| Ja_JP | Japanese | Japan [DB2Z-5-104]|
| Sv_SE | Swedish | Sweden [DB2Z-5-105]|
For a complete list of supported locales for case conversion services, see Locales supported for
case service (z/OS: Unicode Services User’s Guide and Reference). [DB2Z-5-106]


  87



Locale format for collation conversion services
Use this format when you need a locale to affect how the data is sorted.
The collation locale format is Lxx_Ryy_Vzz where:
Lxx
Language
Ryy
Region
Vzz
Variant
You can use any of the locale values that are supported by z/OS Unicode Services for
CUNBOPRM_Collation_Keyword/CUN4BOPR_Collation_Keyword The following table lists some
example locale values for collation
Table 27. Example locale values for collation conversions in z/OS Unicode Services [DB2Z-5-107]

| Locale value | Language | Region | Variant |
| :--- | :--- | :--- | :--- |
| LCS_RCZ | Czech | Czech Republic | None [DB2Z-5-108]|
| LDE_RDE | German | Germany | None [DB2Z-5-109]|
| LDE_RDE_PREEURO | German | Germany | Pre Euro support [DB2Z-5-110]|
| LEN | English | None | None [DB2Z-5-111]|
| LEN_RGB | English | Great Britain | None [DB2Z-5-112]|
| LES_RMX | Spanish | Mexico | None [DB2Z-5-113]|
| LFR_RFR | French | France | None [DB2Z-5-114]|
| LJA | Japanese | None | None [DB2Z-5-115]|
| LSV | Swedish | None | None [DB2Z-5-116]|
For a complete list of supported locales for collation conversion services, see Locales supported
for collation (z/OS: Unicode Services User’s Guide and Reference).
Generating escaped Unicode data
If you pass Unicode characters to an application or object that is not intended to handle Unicode data,
data might be lost unless you escape certain characters. For example, you might need to pass Unicode
data through an application that has EBCDIC host variables. Or you might want to store Unicode data in a
non-Unicode table.
About this task
You might also want to select Unicode characters from an application that runs on a 3270 terminal
emulator, such as SPUFI. If the CCSID setting of the emulator does not include those Unicode characters,
those characters do not display properly in the output.
In these situations, those Unicode characters that cannot be represented in the encoding scheme of the
application or object are lost unless you escape them. Escaped data is one or more characters that cannot
be represented in the target CCSID and is instead represented by the encoding value. This representation
preserves the data. For example, the escaped version of the Unicode character
  is \0434. Thus, the
following ASCII string contains the escaped character
 : 'The escaped character is \0434'
If you insert escaped data into a Unicode table, Db2 does not interpret your data and modify it to be
un-escaped. Escaped data is stored as is in a Db2 table, regardless of whether the table is an ASCII,
EBCDIC, or Unicode table. [DB2Z-5-117]



Procedure
To generate escaped Unicode data:
1. Use the ASCII_STR function or the EBCDIC_STR function. [DB2Z-5-118]
These functions convert a Unicode string to an ASCII or EBCDIC string. Characters that do not exist in
ASCII or EBCDIC are converted to the form \xxxx, where xxxx represents a UTF-16 code unit.
For more information about how to convert characters to UTF-16 format, see step 2 under the
instructions for the INSERT statement in “Inserting data into a Unicode table”.
2. If you later need to convert the EBCDIC or ASCII string with escaped data back to Unicode, use the [DB2Z-5-119]
UNICODE_STR function.
The short form of the function name is UNISTR. This function interprets escaped data in the source
string. Values that are preceded by a backslash ('\') are treated as Unicode UTF-16 characters. For
example '\0041' is the Unicode UTF-16 representation for 'A'.
Examples
GUPI
Example: Escaping data
Assume that T1.C1 contains 'Hi, my name is
А
р
е
 '. Notice that the characters in
А
р
е
  are all
Cyrillic characters, even though some of them do resemble Latin characters. Suppose that you issue
the following query in SPUFI:
SELECT C1 FROM T1;
The result of this query is displayed as follows on a 3270 terminal emulator with the CCSID set to 37:
‘Hi, my name is ......'
Because the characters in
А
р
е
  do not exist in CCSID 37, this name is instead displayed
as ....... To solve this problem, you can add the EBCDIC_STR function, as shown in the following
example:
SELECT EBCDIC_STR(C1)FROM T1;
Db2 returns the following output with escaped data:
‘Hi, my name is \0410\043D\0434\0440\0435\0439'
Notice that 0410 is the UTF-16 value for
А , 043D is the UTF-16 value for
  and so on.
Example: Un-escaping data
Assume that T1.C1 contains '
А
р
е
 '. Suppose that you issue the following query:
SELECT HEX(UNISTR(ASCII_STR(C1))) FROM T1;
Db2 interprets this query as follows:
Table 28. How Db2 interprets query with UNISTR [DB2Z-5-120]

| Part of SELECT statements | Result | Explanation |
| :--- | :--- | :--- |
| ASCII_STR(C1) | \0410\043D\0434\0440\0435\0439 | Db2 returns the value in C1 (Андрей) as an ASCII string. Because these characters cannot be represented in ASCII, they are escaped. [DB2Z-5-121]|
| UNISTR(ASCII_STR(C1)) | Андрей | Db2 then converts the escaped ASCII string to a Unicode UTF-8 string. UTF-8 includes all of the characters, so they no longer have to be escaped. [DB2Z-5-122]|
| HEX(UNISTR(ASCII_STR(C1))) | D090D0BDD0B4D180D0B5D0B9 | Db2 then returns the hexadecimal value of the UTF-8 string. [DB2Z-5-123]|
Thus, the final result of this query is:
D090D0BDD0B4D180D0B5D0B9
Suppose that you issue the following similar query:
SELECT HEX(UNISTR(ASCII_STR(C1),UTF16)) FROM T1;
Db2 interprets this query as follows:
Table 29. How Db2 interprets query with UNISTR and UTF16 parameter [DB2Z-5-124]

| Part of SELECT statements | Result | Explanation |
| :--- | :--- | :--- |
| ASCII_STR(C1) | \0410\043D\0434\0440\0435\0439 | Db2 returns the value in C1 (Андрей) as an ASCII string. Because these characters cannot be represented in ASCII, they are escaped. [DB2Z-5-125]|
| UNISTR(ASCII_STR(C1),UTF16) | Андрей | Db2 then converts the escaped ASCII string to a Unicode UTF-16 string. UTF_16 includes all of the characters, so they no longer have to be escaped. [DB2Z-5-126]|
| HEX(UNISTR(ASCII_STR(C1))) | 0410043D0434044004350439 | Db2 then returns the hexadecimal value of the UTF-16 string. [DB2Z-5-127]|
Thus, the final result of this query is:
0410043D0434044004350439
GUPI
Related concepts
Situations in which character conversion occurs
Character conversion is the process of converting data from one CCSID to another CCSID. This process
can occur when data is transferred between a remote and local system or when data is manipulated
within the local system.
Related tasks
Inserting Unicode data into a non-Unicode table [DB2Z-5-128]



If you insert Unicode data into an EBCDIC or ASCII table, use escaped data for those characters that
cannot be represented in the target encoding scheme. Using escaped data ensures that those characters
are preserved.
Related reference
ASCII_STR or ASCIISTR scalar function (Db2 SQL)
EBCDIC_STR scalar function (Db2 SQL)
HEX scalar function (Db2 SQL)
UNICODE_STR or UNISTR scalar function (Db2 SQL)
Normalization of Unicode strings
Your application should treat as equal those characters that are functionally and visually equivalent but
have different code point representations. This behavior is important when you search, sort, or compare
Unicode strings. To accomplish this goal, you might need to normalize the strings. However, normalization
can degrade performance.
Unicode strings can be canonically equivalent or compatibly equivalent. If they are canonically equivalent,
they are also compatibly equivalent.
Canonically equivalent characters are those characters that are equivalent both functionally and visually,
but might have different code point representations. To users, these characters are indistinguishable in
that they are displayed identically. For example, the character ü is canonically equivalent to the sequence
u and ¨.
Compatibly equivalent characters are characters with plain text that is equivalent, regardless of the
semantic meaning. These characters might also have different code point representations. For example,
superscript and subscript numerals are compatibly equivalent to their decimal-digit counterparts.
The process of normalization of Unicode strings produces a unique code point sequence for all sequences
that are equivalent, either canonically or compatibly. Therefore, all canonically equivalent characters have
the same binary representation. You can normalize a Unicode string into one of the following normalized
forms that are defined by the Unicode Standard:
Normalization Form Canonical Decomposition (NFD)
Characters are decomposed by canonical equivalence.
Normalization Form Canonical Composition (NFC)
Characters are decomposed and then recomposed by canonical equivalence.
Normalization Form Compatibly Decomposition (NFKD)
Characters are decomposed by compatibly equivalence.
Normalization Form Compatibly Composition (NFKC)
Characters are decomposed by compatibly equivalence and then recomposed by canonical
equivalence.
To normalize Unicode strings, use the NORMALIZE_STRING built-in function.
Related reference
NORMALIZE_STRING scalar function (Db2 SQL)
Related information
Canonical Equivalence in Applications (Unicode Consortium)
How Db2 handles Unicode supplementary characters
Unicode supplementary characters are those characters that have a code point between U+10000
and U+10FFFF. These characters include certain math symbols and certain characters from Chinese,
Japanese, and some historic scripts.
Supplementary characters are also known as surrogate characters. Each one of these characters takes
up 4 bytes in either UTF-8 and UTF-16. In UTF-8, each one of these characters takes up four 8-bit code
units. In UTF-16, each one of these characters takes up two 16-bit code units. [DB2Z-5-129]


  91



Db2 detects any supplementary data that is not well formed only if Db2 has to manipulate the data
in some way. For example, if Db2 converts the data or processes it as part of a built-in function, Db2
can detect if it is not well formed. Any built-in function that has the CODEUNITS32, CODEUNITS16,
and OCTETS options, such as CHARACTER_LENGTH and LOCATE_IN_STRING, can detect whether
supplementary characters are well formed. Other operations are also "character aware." For example,
LIKE predicates, the truncation of host variables, and character conversion operations need to know the
content of any character data.
However, suppose that you insert data into a column and Db2 does not need to manipulate it in any
way. In this case, Db2 does not detect problems with data that is not well formed. For example, if your
COBOL application, which uses UTF-16 data, inserts garbage data into a GRAPHIC column, Db2 does not
report any problems. You can use the NORMALIZE_STRING function to process data and ensure that it is
well-formed according to one of the Unicode standard forms. However, using this function might degrade
performance.
Related concepts
String unit specifications (Db2 SQL)
Related reference
CHARACTER_LENGTH or CHAR_LENGTH scalar function (Db2 SQL)
LOCATE_IN_STRING scalar function (Db2 SQL)
NORMALIZE_STRING scalar function (Db2 SQL)
Related information
Unicode Consortium
Processing Unicode data in COBOL applications
COBOL and Db2 for z/OS support UTF-16 data and UTF-8 data.
Procedure
To process Unicode data in COBOL applications for Db2 for z/OS, perform the following recommended
actions:
• For Unicode UTF-16 data, use one of the national data types. For example, specify PIC N(10) USAGE [DB2Z-5-130]
NATIONAL. For Unicode UTF-8 data, use the BYTE-LENGTH phrase of the PICTURE clause and the
UTF-8 phrase of the USAGE clause. For example, specify PIC U BYTE-LENGTH 10 USAGE UTF-8.
If you use a COBOL application to retrieve UTF-8 data from Db2 into a variable other than a UTF-8
variable, Db2 converts the output to the format that is required by the application. For example, if you
query the Db2 catalog, Db2 converts the data for the COBOL application from UTF-8 to either UTF-16
(for PIC N USAGE NATIONAL variables) or EBCDIC (for PIC X variables). However, you should not store
unconverted UTF-8 data in a COBOL variable. For example, if you have UTF-8 data in a PIC X variable,
COBOL thinks that the data is EBCDIC and the data could get corrupted. Even something as simple as
moving this UTF-8 value from one variable to another variable could corrupt the data, because COBOL
pads the variable with X'40' for EBCDIC instead of X'20' for UTF-8.
• Store your data in Db2 using the same encoding scheme as the input data. You gain CPU savings in [DB2Z-5-131]
processing because Db2 and COBOL are both using UTF-16 data or are both using UTF-8 data, and no
conversions are needed.
• Use the Db2 coprocessor to prepare your application. [DB2Z-5-132]
• Specify the appropriate CCSID for your COBOL application source and data according to the [DB2Z-5-133]
instructions in “Specifying a CCSID for your application”.
Recommendation: Use the ENCODING bind option to specify the CCSID of the data. This option
typically yields the best performance. However, depending on the situation, you might consider the
other options for “Specifying a CCSID for your application”.
• Specify ENCODING UNICODE as a bind option in these situations: [DB2Z-5-134]



– Your program uses Db2 variables that are declared as Unicode UTF-16 variables, and specifies the
COBOL compiler option SQLCCSID.
– Your program uses Db2 variables that are declared as Unicode UTF-8 variables.
Related concepts
Processing UTF-8 data using UTF-16 (national) data types (Enterprise COBOL for z/OS Programming
Guide)
Processing UTF-8 data by using UTF-8 data types (Enterprise COBOL for z/OS Programming Guide)
Related tasks
Specifying CCSIDs for COBOL applications when using the Db2 coprocessor
If you are using the Db2 coprocessor to prepare a COBOL application with SQL statements, use the
COBOL compiler to specify the CCSID of the application source code. For optimal performance, use Db2
to specify the CCSID of the application data in SQL statements.
Related reference
Enterprise COBOL for z/OS
Using national data (Unicode) in COBOL (Enterprise COBOL for z/OS Programming Guide)
Compiler options (COBOL) (Enterprise COBOL for z/OS Programming Guide)
Db2 considerations (Enterprise COBOL for z/OS Programming Guide)
Processing Unicode data in PL/I applications
PL/I and Db2 for z/OS support UTF-16 and UTF-8 data.
Procedure
To process Unicode data in PL/I applications for Db2 for z/OS, consider the following recommended
actions.
• Use the WIDECHAR data type. This data type supports UTF-16 data in PL/I. [DB2Z-5-135]
Although PL/I supports UTF-8 data, you can still use a PL/I application to retrieve UTF-8 data from
Db2 into a WIDECHAR variable. For example, if you retrieve data from the Db2 catalog, and the host
variable has data type WIDECHAR, Db2 converts the Unicode data for the PL/I application from UTF-8
to UTF-16.
• Use UTF-16 for your Unicode data in your PL/I application and store your application Unicode data [DB2Z-5-136]
in Db2 in UTF-16. This format often requires more space than UTF-8. However, you might gain CPU
savings in processing because Db2 and PL/I are both using UTF-16, and no conversions are needed.
For additional Db2 CCSID resolution during bind processing and to achieve optimal performance, refer
to Character conversion (Introduction to Db2 for z/OS).
• Prepare your application with the Db2 coprocessor. [DB2Z-5-137]
• Specify the appropriate CCSID for your PL/I application source and data. [DB2Z-5-138]
• Ensure that your ENCODING bind option matches the data. Depending on the situation, you might [DB2Z-5-139]
consider the other options that are described in “Specifying a CCSID for your application”.
Related tasks
Specifying CCSIDs for PL/I applications when using the Db2 coprocessor
If you are using the Db2 coprocessor to prepare a PL/I application with SQL statements, use the PL/I
compiler to specify the CCSID of the application source code. For optimal performance, use Db2 to
specify the CCSID of the application data in SQL statements.
Related reference
Enterprise PL/I for z/OS [DB2Z-5-140]


  93



Processing Unicode data in C/C++ applications
C/C++ supports UTF-16 data. C/C++ also supports UTF-32 data, but Db2 for z/OS does not.
About this task
Db2 for z/OS, however, supports UTF-8 and UTF-16 data.
Procedure
To process Unicode data in C/C++ applications for Db2 for z/OS:
• For UTF-16 data, use the data type char16_t and prefix these literal values with u. [DB2Z-5-141]
In C, char16_t is defined inside the <uchar.h> header. In C++, char16_t is a separate built-in type.
• For SBCS UTF-8 data (UTF-8 data that corresponds to only the first 128 code points in Unicode), [DB2Z-5-142]
specify the ASCII compiler option. When you specify this option, the compiler converts all data to
ISO8859-1 (CCSID 819).
Restriction: You must have an XPLINK application to use the ASCII compiler option.
• If you are using UTF-16 data, store your data in Db2 in UTF-16. This format often requires more [DB2Z-5-143]
space than UTF-8. However, you gain CPU savings in processing, because Db2 and C/C++ are both
processing in the UTF-16, and no conversions are needed.
• Specify the appropriate CCSID for your C/C++ application source and data according to the [DB2Z-5-144]
instructions in “Specifying a CCSID for your application”.
Related reference
The Unicode standard (C/C++) (XL C/C++ Language Reference)
Compiler Options (C/C++) (XL C/C++ User's Guide)
Java applications and Unicode data
Java is Unicode-based, and all character processing inside a Java application occurs in Unicode.
Character data that is not already in Unicode must be converted before being passed to a Java
application. These conversions are handled by Db2 or by the JDBC driver and are transparent to the
application.
You can also pass binary data to a Java application to convert into character data. (This statement
assumes that you provide the correct Java encoding.)
From a Java programming perspective, you are manipulating objects and do not need to be concerned
with the underlying encoding. However, when your Java application communicates with another
technology, such as Db2 for z/OS, conversion might occur. This conversion is handled by Db2 or the
JDBC driver, but you should be aware of any conversion costs.
The conversion depends on how you use the driver and how your data is stored in Db2. With IBM Data
Server Driver for JDBC and SQLJ type 2 connectivity on Db2 for z/OS, the driver sends the data in the
target server encoding scheme. With IBM Data Server Driver for JDBC and SQLJ type 4 connectivity, the
driver sends the data in UTF-8.
IBM Data Server Driver for JDBC and SQLJ type 2 connectivity on Db2 for z/OS uses an SQLDA override
to tell Db2 if the encoding scheme is different than the one that was specified at bind time. IBM Data
Server Driver for JDBC and SQLJ type 4 connectivity uses DRDA data flows to describe the data. Because
this environment is a DRDA environment, Db2 does not use the ENCODING bind option to determine the
CCSID of the data or to encode data.
Java can handle both big endian and little endian data. (This statement assumes that you provide the
correct Java encoding.)
Related concepts
DRDA character type parameters in Unicode [DB2Z-5-145]



Remote Db2 applications can send and receive DRDA command and reply message parameters that
contain character type data encoded in Unicode CCSID 1208 (UTF-8). Using Unicode instead of EBCDIC
for these DRDA parameters can improve performance and avoid potential character conversion errors.
Related reference
SQL descriptor area (SQLDA) (Db2 SQL)
Green screen applications and Unicode data
Green screen applications are applications that run on 3270 terminal emulators. These applications do
not support Unicode data.
If you migrate your Db2 data to Unicode, consider the following affects on any green screen applications:
Decreased performance
Green screen applications have an EBCDIC encoding scheme. Thus, character conversion might occur
between the Db2 data in Unicode and the application. This conversion can increase performance
overhead.
Data loss
Unicode data might be lost in the output, unless the content of the data is somehow controlled to
ensure that the data is convertible to the EBCDIC CCSID that is used by the 3270 application.
To decide how to handle these problems, consider the reason that you are converting data to Unicode.
Is your purpose to accommodate international data or to allow for expansion and flexibility in the future
or something else? Knowing your purpose for converting to Unicode can help you choose the appropriate
solution for your green screen applications.
If it is acceptable to not have the fields display correctly, you can leave the application as is. For example,
some internal reports include names, but they are not required, such as a bank report that lists the first
10 customers by largest deposit. In this case, the name is a “nice to have” field in the report, but not [DB2Z-5-146]
necessary.
If your application is an output only device, and data is not updated, one possible solution is to
use romanization. Romanization is the process of creating the Latin representation of a word. To
implement this solution, you can have one column for the original data and one column for the phonetic
pronunciation in the Latin-1 alphabet. For example, one column might contain
А
р
е
  and another
column, the romanization column, can contain Andrei. One practical implementation of this solution is in a
banking situation. It might be acceptable for a period of time for tellers to have green screen applications
that do not display customer names correctly, but provide the phonetic pronunciation. You might need
to add logic to be prevent the tellers from updating names, addresses and other information if the teller
device is not capable of correctly representing all data.
If you need to display international characters properly, a possible solution is to add a presentation layer
to your environment. Consider migrating to a client/server environment, such as the following examples:
• Use CICS Transaction Gateway to access CICS to then access Db2 for z/OS. [DB2Z-5-147]
• Use an IMS or CICS application that uses WebSphere® MQ to access Db2 for z/OS. [DB2Z-5-148]
Variant characters
Variant characters are characters that correspond to different code points across a given set of code
pages. For example, the character # is variant. It corresponds to code point X'7B' in CCSIDs 37, 273, 500,
and 1047. However, this character corresponds to code point X'4A' in CCSID 277.
An invariant character is a character that corresponds to the same code point regardless of CCSID.
Ideally, you should use invariant characters when possible. However, if you do use variant characters,
ensure that Db2 uses the correct CCSID to interpret them.
For example, consider the following national characters: #, @, and $. Although you can use these
characters in object identifiers, you should be aware that they are all variant characters. The following [DB2Z-5-149]


  95



table shows the corresponding hexadecimal code point values for these characters in several different
code pages.
Table 30. Variant characters that you can use in identifiers [DB2Z-5-150]

| Character | Corresponding hexadecimal value by code page: CCSID 37 | Corresponding hexadecimal value by code page: CCSID 500 | Corresponding hexadecimal value by code page: CCSID 1047 | Corresponding hexadecimal value by code page: CCSID 277 | Corresponding hexadecimal value by code page: CCSID 273 |
| :--- | :--- | :--- | :--- | :--- | :--- |
| # | X'7B' | X'7B' | X'7B' | X'4A' | X'7B' [DB2Z-5-151]|
| @ | X'7C' | X'7C' | X'7C' | X'80' | X'B5' [DB2Z-5-152]|
| $ | X'5B' | X'5B' | X'5B' | X'67' | X'5B' [DB2Z-5-153]|
You need to be careful when you use these characters in identifiers, such as package names, table space
names, index space names, and field procedure names. All of these objects have corresponding data
sets, DBRMs, or load modules that are defined in z/OS with corresponding names. Problems can occur
if you use a different CCSID when the object is created than when it is referenced. In this case, the
corresponding data sets, DBRMs, or load modules might not be found in z/OS because of the variant
characters in the names.
Another example of a variant character that might cause problems is the double quotation mark ("). In the
Turkish code page CCSID 1026 this character corresponds to code point X'FC'. However, this code point is
not the same in other EBCDIC code pages.
Also avoid using variant characters in SQL statements. For example, suppose that you want to use an
operator to mean "not equal." Coding <> is the best choice, because these characters are invariant across
most EBCDIC CCSIDs. However, depending on the situation, Db2 might tolerate other operators for "not
equal" such as !=, or ¬=. For details about the conditions that need to be satisfied for Db2 to tolerate
those operators, see Basic predicate (Db2 SQL). Even if these conditions are satisfied, the exclamation
point character (!) and the not character (¬) are variant and can therefore cause other problems. For
example, these characters might not be displayed correctly on a client. Also, you might have conversion
issues if the SQL statement is copied from the catalog or read by another system.
To prevent such problems with variant characters, use the following recommendations.
Best practices:
• Use invariant characters in identifiers and SQL statements. [DB2Z-5-154]
• When you name Db2 objects, use only those characters that you can type on your keyboard. Do not [DB2Z-5-155]
use hexadecimal values in object names. Doing so can unnecessarily complicate your applications and
queries.
• Use CONCAT instead of || when you need to concatenate values. [DB2Z-5-156]
• Use <> to mean "not equal" instead of != or ¬=. [DB2Z-5-157]
• Do not use variant hexadecimal code points from another code page. Doing so might cause conversion [DB2Z-5-158]
errors.
Related reference
Code point differences between EBCDIC CCSIDs
Although many EBCDIC code pages are similar, code points for certain characters vary from code page to
code page. These characters are called variant characters and can potentially cause problems.
DRDA character type parameters in Unicode
Remote Db2 applications can send and receive DRDA command and reply message parameters that
contain character type data encoded in Unicode CCSID 1208 (UTF-8). Using Unicode instead of EBCDIC
for these DRDA parameters can improve performance and avoid potential character conversion errors.
Prior to DB2 10, remote applications passed DRDA command and reply message parameters that contain
character type data in EBCDIC. These applications might incur additional CPU costs and character
conversion errors for the following reasons: [DB2Z-5-159]



• Db2 for z/OS stores metadata and catalog data in Unicode (UTF-8). Therefore, Db2 converts incoming [DB2Z-5-160]
DRDA EBCDIC data to Unicode (UTF-8).
• The IBM Data Server driver or client must convert DRDA character type data to EBCDIC before sending [DB2Z-5-161]
it to Db2 for z/OS. The driver or client must also convert the data that is received from Db2 for z/OS, in
EBCDIC, before returning it to the application.
• Other remote applications might need to convert the DRDA parameters to and from EBCDIC. [DB2Z-5-162]
Passing these character type parameters in Unicode removes this extra conversion step.
From an application programming perspective, you do not need to perform any extra action to send DRDA
character type parameters in Unicode. Db2 for z/OS automatically negotiates use of Unicode data with
remote client systems that support the exchanging of DRDA character type data parameters in Unicode
(UTF-8).
Because of this new ability to pass DRDA character type parameters in Unicode, potential problems might
exist with certain package names and collection IDs that contain special characters. To prevent these
problems, run the premigration queries.
Related tasks
Run premigration queries (DSNTIJPC) (Db2 Installation and Migration) [DB2Z-5-163]


  61



The default value is the subsystem default application encoding scheme (the DECP value APPENSCH),
which, by default, is EBCDIC. The Db2 sample applications are bound with ENCODING EBCDIC. For
more information, see APPLICATION ENCODING field (APPENSCH DECP value) (Db2 Installation and
Migration).
For example, some possible uses of the ENCODING bind option are as follows:
• You have a C/C++ program that accesses an ASCII library on z/OS. In this case, bind the program with [DB2Z-5-164]
ENCODING ASCII.
• You use QMF and have a data center in Germany and 3270 emulators in France. You might want to bind [DB2Z-5-165]
a special version of QMF for French by specifying ENCODING 1147.
In general, any time the CCSID of your source data does not match the subsystem default CCSID, use the
ENCODING option to tell Db2 the correct CCSID. The source data can come from a terminal emulator, a
message queue, or elsewhere.
If you use the Db2 coprocessor on a COBOL application that contains PIC X variables and specify
the NOSQLCCSID compiler option, do not specify ENCODING UNICODE. If you specify this option, Db2
interprets these character variables as UTF-8, but COBOL does not support UTF-8.
In a DRDA environment, the CCSIDs are communicated as part of the protocol. Db2 does not use the
ENCODING bind option to determine the CCSID of data from a remote application or to encode data to
send to a remote application. However, the ENCODING bind option can influence internal Db2 processing.
Db2 uses the value of this bind option when it processes SET statements or any statement that contains
multiple CCSIDs. For example, Db2 uses the ENCODING option that was specified when the package was
bound to evaluate the following statement:
SET :hv1 = SUBSTR(:hv_locator, 1, 100);
For more information, see ENCODING bind option (Db2 Commands).
CURRENT APPLICATION ENCODING SCHEME special register
Use this special register to specify the CCSID for data that is passed through parameter markers in
dynamic SQL statements. This value does not apply to static SQL statements.
You can set the value of this special register by using the SET CURRENT APPLICATION ENCODING
SCHEME statement in your application program. For more information, see SET CURRENT APPLICATION
ENCODING SCHEME (Db2 SQL).
The value can be EBCDIC, ASCII, Unicode, or a valid CCSID. If the value is EBCDIC, ASCII, or Unicode,
Db2 uses the subsystem default CCSID for that encoding scheme.
The default value is the value of the ENCODING bind option. For native SQL procedures, the default
value is the APPLICATION ENCODING SCHEME option of the CREATE PROCEDURE or ALTER PROCEDURE
statement. If you do not specify these values, the default value is the subsystem default application
encoding scheme.
For more information, see CURRENT APPLICATION ENCODING SCHEME special register (Db2 SQL).
DECLARE VARIABLE statement with the CCSID option
Use this statement in your application to define a CCSID for a particular host variable. This value overrides
the CURRENT APPLICATION ENCODING SCHEME special register value, the ENCODING bind option and
any compiler and precompiler CCSID options.
The value for the CCSID option can be EBCDIC, ASCII, Unicode, or a valid CCSID. If the value is EBCDIC,
ASCII, or Unicode, Db2 uses the subsystem default CCSID for that encoding scheme.
Use the DECLARE VARIABLE statement with the CCSID option when your application handles a piece of
data that you know has a different CCSID. [DB2Z-5-166]



In the case of bit data, no CCSID is needed. For this type of data, use a DECLARE VARIABLE statement so
that no CCSID is associated with the variable, as shown in the following COBOL example:
EXEC SQL DECLARE : hv1 VARIABLE FOR BIT DATA END-EXEC.
If you are using DCLGEN, you can specify DCLBIT(YES) to create DECLARE VARIABLE statements for
columns that are declared with the FOR BIT DATA clause. For example, the following DCLGEN output
shows such a declaration for a COBOL application:
GUPI
******************************************************************
* DCLGEN TABLE(ADMF001.T1)                                       * [DB2Z-5-167]
*        LIBRARY(USER.DBRMLIB.DATA(T1))                          * [DB2Z-5-168]
*        LANGUAGE(COBOL)                                         * [DB2Z-5-169]
*        QUOTE                                                   * [DB2Z-5-170]
*        DBCSSYMBOL(N)                                           * [DB2Z-5-171]
*        DCLBIT(YES)                                             * [DB2Z-5-172]
* ... IS THE DCLGEN COMMAND THAT MADE THE FOLLOWING STATEMENTS   * [DB2Z-5-173]
******************************************************************
     EXEC SQL DECLARE ADMF001.T1 TABLE
     ( NAME                           VARGRAPHIC(15),
       ADDRESS                        VARGRAPHIC(25),
       …
       PASSWORD                       CHAR(8)
      ) END-EXEC.
******************************************************************
* DECLARED VARIABLES FOR 'FOR BIT DATA' COLUMNS                  * [DB2Z-5-174]
******************************************************************
     EXEC SQL DECLARE
      :PASSWORD
     VARIABLE FOR BIT DATA END-EXEC.
******************************************************************
* COBOL DECLARATION FOR TABLE ADMF001.T1                         * [DB2Z-5-175]
******************************************************************
 01  DCLT1. [DB2Z-5-176]
     10 NAME.
        49 NAME-LEN          PIC S9(4) USAGE COMP. [DB2Z-5-177]
        49 NAME-TEXT         PIC N(15). [DB2Z-5-178]
     10 ADDRESS. [DB2Z-5-179]
        49 ADDRESS-LEN       PIC S9(4) USAGE COMP. [DB2Z-5-180]
        49 ADDRESS-TEXT      PIC N(25). [DB2Z-5-181]
     10 CITY.
        49 CITY-LEN          PIC S9(4) USAGE COMP. [DB2Z-5-182]
        49 CITY-TEXT         PIC N(20). [DB2Z-5-183]
     10 STATE                PIC N(2). [DB2Z-5-184]
     10 ZIP                  PIC N(5). [DB2Z-5-185]
     10 PASSWORD             PIC (8). [DB2Z-5-186]
******************************************************************
* THE NUMBER OF COLUMNS DESCRIBED BY THIS DECLARATION IS 6       * [DB2Z-5-187]
******************************************************************
GUPI
In this example, notice the DBCSSYMBOL option for DCLGEN. You can use this option to specify how
you want COBOL graphic variables to be generated. If you plan to use Unicode variables and the Db2
coprocessor, you should specify DBCSSYMBOL(N) so that you get PIC N variables. For more information
see DCLGEN (declarations generator) subcommand (DSN) (Db2 Commands).
For more information, see DECLARE VARIABLE statement (Db2 SQL).
SQL descriptor (SQLDA)
If you use an SQL descriptor to define application data in parameter markers and host variables, such as
when you specify the USING DESCRIPTOR descriptor-name clause in the EXECUTE statements,
the CCSID values for host variables and parameter markers are defined by the SQLDA that is specified
in descriptor-name and not the actual application data, and the other options described in this topic
might not apply. For more information about the USING DESCRIPTOR clause, see EXECUTE statement
(Db2 SQL).
Before invoking the EXECUTE statement, you must set the following fields in the SQLDA: [DB2Z-5-188]


  97








  63



• SQLN to indicate the number of SQLVAR occurrences that are provided in the SQLDA [DB2Z-5-189]
• SQLABC to indicate the number of bytes of storage that are allocated for the SQLDA [DB2Z-5-190]
• SQLD to indicate the number of variables that are used in the SQLDA when processing the statement [DB2Z-5-191]
• SQLVAR entries to indicate the attributes of the variables [DB2Z-5-192]
The SQLDATA fields of the SQLVAR entries contain the CCSID values. For more information, see SQLDATA
field of the SQLDA (Db2 SQL).
If the SQLDAID field is used, it contains the text 'SQLDA '. A plus sign (+) in the 6th byte (for example, the
first six bytes contain 'SQLDA+') indicates that the SQLNAME field contains an overriding CCSID. A '2' in
the 7th byte indicates the two SQLVAR entries were allocated for each column or parameter. Otherwise,
the SQLDAID field is not used. For more information about the SQLDAID field, see The SQLDA Header
(Db2 SQL).
Db2 interprets the third and fourth byte of the data portion of SQLNAME as the CCSID of the host variable
if all of the following are true and the third and fourth byte are not X'0000':
• The sixth byte of SQLDAID is '+' (x'4E'). [DB2Z-5-193]
• SQLTYPE indicates the host variable is a string variable. [DB2Z-5-194]
• The length of SQLNAME is 8. [DB2Z-5-195]
• The first two bytes of the data portion of SQLNAME are X'0000'. [DB2Z-5-196]
If the third and fourth byte of the data portion of SQLNAME are X'0000', Db2 uses the appropriate default
CCSID.
For more information about the SQLNAME field, see Field descriptions of an occurrence of a base SQLVAR
(Db2 SQL).
Related tasks
Generating table and view declarations by using DCLGEN (Db2 Application programming and SQL)
Related reference
Subsystem CCSIDs and encoding schemes
Each Db2 subsystem has a set of default CCSID and encoding scheme values. Db2 uses these values for
objects and applications that do not otherwise have a CCSID associated with them.
Examples of specifying CCSIDs for application data
If your applications handle international or Unicode data, you probably need to specify a different
application CCSID than the default. Also, if you deploy applications to international locations, you
probably need to bind different versions of the application with the appropriate CCSIDs.
Example: ENCODING(UNICODE) bind option
Assume that the package MY_PACK is bound with the option ENCODING(UNICODE). Db2 assumes that all
character input and output host variables are encoded using CCSID 1208. Db2 assumes that all graphic
input and output host variables are encoded using CCSID 1200.
Example: Setting CCSIDs in a distributed environment
Assume that your Db2 for z/OS subsystem is located in the United States and you have users around the
world that connect to this subsystem. The following figure illustrates this scenario. [DB2Z-5-197]



Db2 for z/OS server
EBCDIC CCSID 37
3270 Client - CCSID 284 [DB2Z-5-198]
DRDA - CCSID 12523270 Client - CCSID 273
DRDA - CCSID 1208
3270 Client - CCSID 373270 Client - CCSID 285 [DB2Z-5-199]
Figure 1. Example of setting CCSIDs in a distributed environment
The users that use DRDA do not need to use the ENCODING bind option to handle CCSID conversions
because DRDA handles all conversions. However, users might choose to specify the ENCODING bind
option to influence internal Db2 processing.
The users that use 3270 terminal emulators need to set up their emulators to use a CCSID that
corresponds to the country in which they reside. In this example, the CCSID of one of those terminal
emulators is 285. You need to bind the plans or packages that this client uses with ENCODING(285).
Likewise, for the terminal emulator that has CCSID 284, you need to bind the plans or packages that this
client uses with ENCODING(284). Also, for the terminal emulator that has CCSID 273, you need to bind
the plans or packages that this client uses with ENCODING(273).
Example: Ensuring that remote users access the correct version of the SPUFI application according
to their terminal CCSID
Suppose that you want to prevent users in the U.K. from using the U.S. version of SPUFI. Instead of
granting the EXECUTE privilege for the SPUFI packages to public, restrict access to only those users in the
U.S. Then, bind additional SPUFI packages with the ENCODING bind option that specifies the appropriate
terminal CCSID for the U.K. (For instructions on how to find the terminal CCSID value in ISPF, TSO, or
CICS, see “Finding the CCSID values of your data sources”.) Authorize the U.K. users to use
only this version of SPUFI.
Related tasks
Making SPUFI work with different terminal CCSIDs (Db2 Installation and Migration)
Related reference
Z variables (ISPF session variables)
Configuring Sessions (Personal Communications) [DB2Z-5-200]


  65



Specifying CCSIDs for COBOL applications when using the Db2 coprocessor
If you are using the Db2 coprocessor to prepare a COBOL application with SQL statements, use the
COBOL compiler to specify the CCSID of the application source code. For optimal performance, use Db2
to specify the CCSID of the application data in SQL statements.
About this task
The COBOL compiler accepts only one CCSID value that it uses for both the application source code and
data. However, Db2 can accept one CCSID value for the source code and one or more CCSID values for the
data that is manipulated in SQL statements through host variables and parameter markers.
Procedure
To specify CCSIDs for COBOL applications when using the Db2 coprocessor:
1. To specify the CCSID of the COBOL application source code, use the CODEPAGE compiler option. 6 [DB2Z-5-201]
For example, both of the following JCL EXEC statements for COBOL compile jobs specify a CCSID of
37:
//COB  EXEC  PGM=IGYCRCTL,PARM='...,SQL,CODEPAGE(037),...
//COB EXEC PGM=IGYCRCTL,PARM='...,SQL,CP(37),...
Otherwise, if you do not specify the CODEPAGE compiler option, the default COBOL compiler CCSID
is passed to the Db2 coprocessor and is used as the CCSID for the source code. The default COBOL
compiler CCSID is 1140 unless you changed it.
The following JCL EXEC statement for a COBOL compile job does not explicitly specify a CCSID. In this
case, the COBOL compiler passes the default CCSID, 1140, to the Db2 coprocessor.
//COB EXEC PGM=IGYCRCTR,PARM='...,SQL(),...'
CCSID 1140 is the equivalent to CCSID 37 plus the euro symbol (€). However, be aware that
conversions, and thus conversion cost, still occur between CCSID 1140 and CCSID 37.
Recommendation: If you are using the Db2 coprocessor on a COBOL application, do not specify the
SQL compiler option with the CCSID suboption. If you specify it anyway, and it conflicts with the
CODEPAGE compiler value, Db2 issues a warning. 7 For example, the following EXEC statement for a
COBOL compile job specifies a CCSID value of 1140; the CCSID value 37 is ignored:
//COB EXEC PGM=IGYCRCTR,PARM='CP(1140),SQL("CCSID(37)")'
To verify which CCSID value was used, look at the compiler listing and check the CCSID option.
2. To specify whether Db2 or the COBOL compiler determines the CCSID of application data for host [DB2Z-5-202]
variables and parameter markers in the program, specify one of the following compiler options:
NOSQLCCSID (Recommended option)
Specifies that the CCSID that is passed from the COBOL compiler is used only for the COBOL
application source and string literals. That CCSID is not used for the host variables and parameter
markers in SQL statements. For host variables and parameter markers, Db2 uses the CCSIDs that
are specified through Db2 mechanisms, as described in the next step.
6 If you are using an older compiler that does not otherwise pass a CCSID value to Db2, use the SQL compiler [DB2Z-5-203]
option with the CCSID suboption to specify the CCSID of the application source. For example, the following
EXEC statement for a COBOL compile job specifies a source CCSID of 1140.
//COB EXEC PGM=IGYCRCTR,PARM='SQL("CCSID(1140)"'
7 The exception is if you are using an older compiler that does not otherwise pass a CCSID value to the Db2 [DB2Z-5-204]
coprocessor. In this case, you need to specify the SQL compiler option with the CCSID suboption. [DB2Z-5-205]



Specifying NOSQLCCSID typically yields better performance, because you can then use a Db2
mechanism to specify the host variable CCSIDs.
NOSQLCCSID simulates the behavior of the precompiler. Specify this option for existing
applications that previously used the Db2 precompiler and now use the Db2 coprocessor. By
default, the Db2 coprocessor uses the same CCSID value that is passed from the COBOL
CODEPAGE(nnnnn) compiler option for both the source code and data. This behavior is different
than the Db2 precompiler, which does not use the CCSID from the COBOL compiler. For
applications that use the Db2 precompiler, you specify the CCSID for the data through Db2
mechanisms only. When you specify NOSQLCCSID, Db2 does not use the COBOL CCSID for the
application data.
SQLCCSID (default option)
Specifies that the Db2 coprocessor is to use the CCSID from the COBOL CODEPAGE(nnnnn)
compiler option for your application data. 7 The CCSID value is the same one that you specified for
your COBOL source code.
3. Optional: If you specified the NOSQLCCSID compiler option, you can specify different encoding [DB2Z-5-206]
schemes for data processed by the program. You can use one or more of the following Db2 approaches
to specify CCSID values for the application data8, which is called the application encoding scheme:
• Use the ENCODING bind option .9 This option typically yields the best performance. [DB2Z-5-207]
• You can override the CCSID for a particular host variable by using the DECLARE VARIABLE statement [DB2Z-5-208]
with the CCSID option.
• You can override the CCSID for parameter markers in dynamic SQL by specifying the CURRENT [DB2Z-5-209]
APPLICATION ENCODING SCHEME special register. Db2 uses the value of this special register at the
time that the statement is executed.
• If you use an SQL descriptor to define application data the previous options in this list might not [DB2Z-5-210]
apply if CCSID overrides are defined for host variables and parameter markers in the “SQL descriptor
(SQLDA)”.
4. If you want to specify a Unicode CCSID for a particular variable, declare it as a PIC N USAGE NATIONAL [DB2Z-5-211]
variable.
For COBOL PIC N USAGE NATIONAL variables, the Db2 coprocessor always uses the CCSID 1200. You
do not need to use a DECLARE VARIABLE statement with the CCSID clause for these variables.
Example
The following table shows examples of the CCSID that Db2 uses for data in COBOL applications
depending on the options that you specify. This table assumes that you did not specify any DECLARE
VARIABLE statements with the CCSID clause.
Table 19. CCSID resolution for data in COBOL applications that use the Db2 coprocessor [DB2Z-5-212]

| Variable | ENCODING bind option | COBOL compiler options: CODEPAGE(nnnn)<sup>1</sup> | COBOL compiler options: (NO)SQLCCSID | CCSID that Db2 uses for the data |
| :--- | :--- | :--- | :--- | :--- |
| PIC X | not explicitly specified | 1140 | SQLCCSID | 1140<sup>2</sup> [DB2Z-5-213]|
| PIC X | not explicitly specified | 1140 | NOSQLCCSID | Subsystem default application encoding scheme (DECP value APPENSCH)<sup>3</sup> [DB2Z-5-214]|
| PIC X | 273 | 1140 | SQLCCSID | 1140<sup>2</sup> [DB2Z-5-215]|
| PIC X | 273 | 1140 | NOSQLCCSID | 273<sup>4</sup> [DB2Z-5-216]|
| PIC X | UNICODE | 1140 | SQLCCSID | 1140<sup>2</sup> [DB2Z-5-217]|
| PIC X | UNICODE | 1140 | NOSQLCCSID | 1208 (This CCSID does not logically make sense for COBOL.)<sup>5</sup> [DB2Z-5-218]|
| PIC N USAGE NATIONAL | 1140 | 1140 | NOSQLCCSID | 1200<sup>6</sup> [DB2Z-5-219]|

**Notes:**
1. This value can be the value that you explicitly specify with the CODEPAGE compiler option or the default COBOL compiler code page. [DB2Z-5-220]
2. Because you specified SQLCCSID, Db2 uses the code page value from the COBOL compiler. [DB2Z-5-221]
3. Because you specified NOSQLCCSID, Db2 does not use the COBOL code page value. Additionally, because [DB2Z-5-222]
you did not explicitly specify a value for the ENCODING bind option, Db2 uses the default application
encoding scheme.
4. Because you specified NOSQLCCSID, Db2 does not use the COBOL code page value. Instead, Db2 uses the [DB2Z-5-223]
value that you specified for the ENCODING bind option.
5. Because you specified NOSQLCCSID and the ENCODING bind option UNICODE, Db2 uses CCSID 1208, [DB2Z-5-224]
which is UTF-8. However, COBOL does not support 1208 as a native data type. So, although you can specify
this combination of options, do not do so.
6. Because you specified a PIC N USAGE NATIONAL variable, Db2 uses CCSID 1200. [DB2Z-5-225]
Related tasks
Controlling the CCSID for COBOL host variables (Db2 Application programming and SQL)
Related reference
Compiler options (COBOL) (Enterprise COBOL for z/OS Programming Guide)
Planning to modify compiler option default values (COBOL) (Enterprise COBOL for z/OS Customization
Guide)
Using national data (Unicode) in COBOL (Enterprise COBOL for z/OS Programming Guide)
ENCODING bind option (Db2 Commands)
DECLARE VARIABLE statement (Db2 SQL)
Subsystem CCSIDs and encoding schemes [DB2Z-5-226]



Each Db2 subsystem has a set of default CCSID and encoding scheme values. Db2 uses these values for
objects and applications that do not otherwise have a CCSID associated with them.
Specifying CCSIDs for PL/I applications when using the Db2 coprocessor
If you are using the Db2 coprocessor to prepare a PL/I application with SQL statements, use the PL/I
compiler to specify the CCSID of the application source code. For optimal performance, use Db2 to
specify the CCSID of the application data in SQL statements.
About this task
The PL/I compiler accepts only one CCSID value that it uses for both the application source code and
data. However, Db2 can accept one CCSID value for the source code and one or more CCSID values for the
data that is manipulated in SQL statements through host variables and parameter markers.
Procedure
To specify CCSIDs for PL/I applications when using the Db2 coprocessor:
1. To specify the CCSID of the PL/I application source code, use the CODEPAGE compiler option. 10 [DB2Z-5-227]
For example, both of the following JCL EXEC statements for PL/I compile jobs specify a CCSID of 37:
//PLI EXEC PGM=IBMZPLI,PARM='...,PP(SQL,...),CODEPAGE(37)...'
//PLI EXEC PGM=IBMZPLI,PARM='...,PP(SQL,...),CP(37)...'
Otherwise, if you do not specify the CODEPAGE compiler option, the default PL/I compiler CCSID
is passed to the Db2 coprocessor and is used as the CCSID for the source code. The default PL/I
compiler CCSID is 1140 unless you changed it.
The following JCL EXEC statement for a PL/I compile job does not explicitly specify a CCSID. In this
case, the PL/I compiler passes the default CCSID, 1140, to the Db2 coprocessor.
//PLI EXEC PGM=IBMZPLI,PARM='PP(SQL())'
CCSID 1140 is equivalent to CCSID 37 plus the euro symbol (€). However, be aware that conversions,
and thus conversion cost, still occur between CCSID 1140 and CCSID 37.
Recommendation: If you are using the Db2 coprocessor to prepare a PL/I application, do not
specify the PL/I preprocessor option SQL with the CCSID suboption. If you specify it anyway, and
it conflicts with the CODEPAGE compiler value, Db2 issues a warning. 7 For example, the following
EXEC statement for a PL/I compile job specifies the CODEPAGE compiler option with a CCSID value of
1140; the CCSID value 37 is ignored:
//PLI EXEC PGM=IBMZPLI,PARM='CP(1140),PP(SQL("CCSID(37)"))'
To verify which CCSID value was used, look at the compiler listing and check the CCSID option.
2. To specify whether Db2 or the PL/I compiler determines the CCSID of application data for host [DB2Z-5-228]
variables and parameter markers in the program, specify one of the following PP compiler options.
CCSID0 (default)
Specifies that only host variables with the WIDECHAR data type are to be assigned a CCSID value
by the Db2 coprocessor (PL/I SQL preprocessor). Host variables with the WIDECHAR data type are
always assigned a CCSID value of 1200.
10 If you are using an older compiler that does not otherwise pass a CCSID value to Db2, use the PL/I [DB2Z-5-229]
preprocessor option 'PP(SQL...' with the CCSID suboption to specify the CCSID of the application source.
For example, if you are using an older compiler that does not pass a CCSID value to the Db2 coprocessor,
the following EXEC statement for a PL/I compile job specifies a source CCSID of 37.
/PLI EXEC PGM=IBMZPLI,PARM='...,PP(SQL("CCSID(37)"),...),...' [DB2Z-5-230]


  69



If your program updates FOR BIT DATA columns with a data type that is not BIT data, choose
CCSID0. CCSID0 informs Db2 that the host variable is not associated with a CCSID, allowing the
assignment to be made.
Specifying CCSID0 can yield better performance, because you can then use a Db2 mechanism,
such as the ENCODING bind option, to specify the host variable CCSIDs.
CCSID0 simulates the behavior of the Db2 precompiler, which does not use the CCSID from the
PL/I compiler for host variable data.
The following examples of the PARM clause specify the PP PL/I preprocessor option of the PL/I
compiler with the SQL suboption CCSID0.
PARM='S,XREF,PP(SQL(APOSTSQL,CCSID0))'
PARM='S,XREF,PP(SQL(APOSTSQL))'
NOCCSID0
Allows host variables to be assigned a CCSID value by the PL/I SQL preprocessor. If the PL/I
SQL preprocessor CODEPAGE option is not also specified, or if the PL/I SQL preprocessor
NOCODEPAGE option is also specified, the compiler CODEPAGE option is used as the CCSID for
SQL host variables of the CHARACTER data type.
The following example of the PARM clause specifies the PP PL/I preprocessor option of the PL/I
compiler with the SQL suboption NOCCSID0.
PARM='S,XREF,PP(SQL(APOSTSQL,NOCCSID0))'
CODEPAGE
Specifies that the compiler CODEPAGE option is always used as the CCSID for SQL host variables
of the CHARACTER type. The following example of the PARM clause specifies the PP PL/I
preprocessor option of the PL/I compiler with the SQL suboption CODEPAGE.
PARM='S,XREF,PP(SQL(APOSTSQL,CODEPAGE))'
For more information ,see.“PL/I PP compiler option”.
3. Optional: If you specified the CCSID0 option, you can specify a different encoding scheme for data [DB2Z-5-231]
processed by the application. You can use one or more of the following Db2 approaches to specify
CCSID values for the application data11, which is called the application encoding scheme:
• Use the ENCODING bind option .12 This option typically yields the best performance. [DB2Z-5-232]
• You can override the CCSID for a particular host variable by using the DECLARE VARIABLE statement [DB2Z-5-233]
with the CCSID option.
• You can override the CCSID for parameter markers in dynamic SQL by specifying the CURRENT [DB2Z-5-234]
APPLICATION ENCODING SCHEME special register. Db2 uses the value of this special register at the
time that the statement is executed.
• If you use an SQL descriptor to define application data the previous options in this list might not [DB2Z-5-235]
apply if CCSID overrides are defined for host variables and parameter markers in the “SQL descriptor
(SQLDA)”.
4. If you want to specify a Unicode CCSID for a particular variable, declare it as a WIDECHAR variable, [DB2Z-5-236]
and use the default SQL preprocessor option of CCSID0. For PL/I variables in applications that are
processed by the Db2 coprocessor, the Db2 coprocessor always uses CCSID 1200 for WIDECHAR
variables. You do not need to use a DECLARE VARIABLE statement with the CCSID clause for these
variables.
11 Use caution if you specify different CCSIDs for different pieces of data, which can result in character [DB2Z-5-237]
conversion.
12 For DRDA applications, the ENCODING bind option does not set the CCSID of the data, and CCSIDs are [DB2Z-5-238]
communicated as part of the protocol. [DB2Z-5-239]



Example
The following table shows examples of the CCSID that Db2 uses for data in PL/I applications depending
on the options that you specify.
Table 20. CCSID resolution for data in PL/I applications that use the Db2 coprocessor [DB2Z-5-240]

| Variable | ENCODING bind option | PL/I options: PL/I CODEPAGE compiler option<sup>1</sup> | PL/I options: PP(SQL) suboption | CCSID that Db2 uses for the data |
| :--- | :--- | :--- | :--- | :--- |
| CHAR(n) | not explicitly specified | 1140 | NOCCSID0 | 1140<sup>2</sup> [DB2Z-5-241]|
| CHAR(n) | not explicitly specified | 1140 | CCSID0 | Subsystem default application encoding scheme (DECP value APPENSCH)<sup>3</sup> [DB2Z-5-242]|
| CHAR(n) | 500 | 1140 | NOCCSID0 | 1140<sup>2</sup> [DB2Z-5-243]|
| CHAR(n) | 500 | 1140 | CCSID0 | 500<sup>4</sup> [DB2Z-5-244]|
| CHAR(n) | UNICODE | 1140 | NOCCSID0 | 1140<sup>2</sup> [DB2Z-5-245]|
| CHAR(n) | UNICODE | 1140 | CCSID0 | 1208 (This CCSID and options combination does not logically make sense for PL/I.)<sup>5</sup> [DB2Z-5-246]|
| UCHAR(n) | 1208 | 1140 | CCSID0 | 1208<sup>4</sup> [DB2Z-5-247]|
| WIDECHAR(n) | 1140 | 1140 | CCSID0 | 1200<sup>6</sup> [DB2Z-5-248]|

**Note:**
1. This value can be the value that you explicitly specify with the CODEPAGE compiler option or the default PL/I compiler code page. [DB2Z-5-249]
2. Because you specified NOCCSID0, Db2 uses the code page value from the PL/I compiler. [DB2Z-5-250]
3. Because you specified CCSID0, Db2 does not use the PL/I code page value. Additionally, because you did not explicitly specify a value for the ENCODING bind option, Db2 uses the default application encoding scheme. [DB2Z-5-251]
4. Because you specified CCSID0, Db2 does not use the PL/I code page value. Instead, Db2 uses the value that you specified for the ENCODING bind option. [DB2Z-5-252]
5. Because you specified CCSID0 and the ENCODING bind option UNICODE, Db2 uses CCSID 1208, which is UTF-8. Although you can specify this combination of options, do not do so. Specify compiler option CODEPAGE(1208) and the NOCCSID0 and CODEPAGE Db2 coprocessor options instead. [DB2Z-5-253]
6. Because you specified a WIDECHAR variable, Db2 uses CCSID 1200. [DB2Z-5-254]
Related reference
Compile-time option descriptions (PL/I) (Enterprise PL/I for z/OS Programming Guide:)
Changing the default options (PL/I) (Enterprise PL/I for z/OS Programming Guide:)
SQL preprocessor options (PL/I) (Enterprise PL/I for z/OS Programming Guide:) [DB2Z-5-255]


  71



ENCODING bind option (Db2 Commands)
DECLARE VARIABLE statement (Db2 SQL)
Subsystem CCSIDs and encoding schemes
Each Db2 subsystem has a set of default CCSID and encoding scheme values. Db2 uses these values for
objects and applications that do not otherwise have a CCSID associated with them.
PL/I PP compiler option
When you specify the CCSID for a PL/I application, you might need to use the PP compiler option.
This option enables you to specify SQL processing options to the Db2 coprocessor and the PL/I SQL
preprocessor.
The following code shows an example of the PP compiler option:
PP(SQL('APOSTSQL,FLOAT(IEEE)'))
PL/I has an SQL preprocessor that works with the Db2 coprocessor to process SQL statements. Some of
the SQL suboptions for the PP compiler option are for the PL/I SQL preprocessor. Other suboptions are
for the Db2 coprocessor. For example, the NOCCSID0 and the CCSID0 suboptions are for the PL/I SQL
preprocessor.
The following code shows an example of specifying SQL suboptions for both the Db2 coprocessor and
PL/I SQL preprocessor:
PP(SQL('APOSTSQL,FLOAT(IEEE),NOCCSID0'))
Example
Suppose that you specify the following statement with the PP compiler option and SQL suboption
NOCCSID0:
PARM='S,XREF,PP(SQL("APOSTSQL,NOCCSID0"))'
The following output listing shows the options that are in effect for both the PL/I SQL preprocessor and
the Db2 coprocessor:
SQL Preprocessor Options Used
NOCCSID0
LOB(DB2)
OPTIONS
DB2 for z/OS Coprocessor Options used
APOST
APOSTSQL
ATTACH(TSO)
CCSID(1140)
Specifying CCSIDs for C/C++ applications when using the Db2 coprocessor
If you use the Db2 coprocessor to prepare a C/C++ application with SQL statements, use the C/C++
compiler to specify the CCSID of the application source. Use Db2 mechanisms to specify the CCSID of the
application data that is manipulated in SQL statements.
Procedure
To specify CCSIDs for C/C++ applications when using the Db2 coprocessor:
1. Specify the CCSID for the source code by specifying a LOCALE value. [DB2Z-5-256]
For example, the following JCL EXEC statements for C compile jobs specify a CCSID of 1047. The
first statement specifies a LOCALE for U.S. applications. The second statement specifies a LOCALE for
German applications.
//C EXEC PGM=CCNDRVR,PARM='SQL(),SO,LIST,LOCALE(En_US.IBM-1047)' [DB2Z-5-257]



//C EXEC PGM=CCNDRVR,PARM='SQL(),SO,LIST,LOCALE(De_CH.IBM-1047)'
Alternatively, you can use other more advanced methods to specify the CCSID of the source code
and any data that is outside of SQL statements. For more information about those methods, see the
internationalization information in the C/C++ Programming Guide.
Otherwise, if you do not specify a CCSID to the C/C++ compiler, the default C/C++ LOCALE of 1047 is
passed to the Db2 coprocessor.
For examples, the following JCL EXEC statements for a C compile job does not specify a LOCALE value.
Therefore, the default value of 1047 is passed to the Db2 coprocessor.
//C EXEC PGM=CCNDRVR,PARM='SQL()'
Recommendation: If you are using the Db2 coprocessor on a C/C++ application, do not specify the
SQL compiler option with the CCSID suboption. If you specify it anyway, and it conflicts with the
LOCALE value, Db2 issues a warning. For example, the following EXEC statement for a C compile job
specifies a CCSID value of 1047; the CCSID value 37 is ignored:
//C EXEC PGM=CCNDRVR,PARM='SQL(CCSID(37)),LOCALE(De_CH.IBM-1047)'
2. Optional: You can specify different encoding schemes for application data that the program processes. [DB2Z-5-258]
You can use one or more of the following Db2 approaches to specify CCSID values for the application
data13, which is called the application encoding scheme:
• Use the ENCODING bind option .14 This option typically yields the best performance. [DB2Z-5-259]
• You can override the CCSID for a particular host variable by using the DECLARE VARIABLE statement [DB2Z-5-260]
with the CCSID option.
• You can override the CCSID for parameter markers in dynamic SQL by specifying the CURRENT [DB2Z-5-261]
APPLICATION ENCODING SCHEME special register. Db2 uses the value of this special register at the
time that the statement is executed.
• If you use an SQL descriptor to define application data the previous options in this list might not [DB2Z-5-262]
apply if CCSID overrides are defined for host variables and parameter markers in the “SQL descriptor
(SQLDA)”.
Related reference
Compiler Options (C/C++) (XL C/C++ User's Guide)
DECLARE VARIABLE statement (Db2 SQL)
ENCODING bind option (Db2 Commands)
APPLICATION ENCODING field (APPENSCH DECP value) (Db2 Installation and Migration)
Subsystem CCSIDs and encoding schemes
Each Db2 subsystem has a set of default CCSID and encoding scheme values. Db2 uses these values for
objects and applications that do not otherwise have a CCSID associated with them.
Determining the CCSID of Db2 data
Db2 can store EBCDIC, ASCII, and Unicode data.
Procedure
To determine the CCSID of Db2 data, use one of the following techniques:
• To find the CCSID of data that is stored in Db2 tables, check one of the following catalog tables: [DB2Z-5-263]
– SYSIBM.SYSCOLUMNS (the FOREIGNKEY and CCSID columns)
13 Use caution if you specify different CCSIDs for different pieces of data, which can result in character [DB2Z-5-264]
conversion.
14 For DRDA applications, the ENCODING bind option does not set the CCSID of the data, and CCSIDs are [DB2Z-5-265]
communicated as part of the protocol. [DB2Z-5-266]


  73



SELECT FOREIGNKEY, CCSID
  FROM SYSIBM.SYSCOLUMNS
  WHERE NAME = 'column-name'
– SYSIBM.SYSDATABASE (the SBCS_CCSID, MIXED_CCSID, and DBCS_CCSID columns)
SELECT SBCS_CCSID, MIXED_CCSID, DBCS_CCSID
  FROM SYSIBM.SYSDATABASE
  WHERE NAME = 'database-name'
– SYSIBM.SYSTABLES (the ENCODING_SCHEME column)
SELECT ENCODING_SCHEME
  FROM SYSIBM.SYSTABLES
  WHERE NAME = 'table-name'
– SYSIBM.SYSTABLESPACE (the SBCS_CCSID, MIXED_CCSID, and DBCS_CCSID columns)
SELECT SBCS_CCSID, MIXED_CCSID, DBCS_CCSID
  FROM SYSIBM.SYSTABLESPACE
  WHERE NAME = 'tablespace-name'
– SYSIBM.SYSKEYTARGETS (the CCSID column)
SELECT CCSID
  FROM SYSIBM.SYSKEYTARGETS
  WHERE IXNAME = 'keytarget-name'
• To find the CCSID of a distinct type, check the SYSIBM.SYSDATATYPES catalog table (the [DB2Z-5-267]
ENCODING_SCHEME column).
• To find the CCSID of routine parameters, check one of the following catalog tables: [DB2Z-5-268]
– SYSIBM.SYSPARMS (the CCSID column)
– SYSIBM.SYSROUTINES (the PARAMETER_CCSID column)
• To find the CCSID of application data, check one of the following catalog tables: [DB2Z-5-269]
– SYSIBM.SYSPACKAGE (the ENCODING_CCSID column)
– SYSIBM.SYSPLAN (the ENCODING_CCSID column)
– SYSIBM.SYSENVIRONMENT (the APPLICATION_ ENCODING_CCSID column)
• To find the subsystem CCSIDs, follow the instructions for “Determining current subsystem CCSID and [DB2Z-5-270]
encoding scheme values”.
Related reference
Db2 catalog tables (Db2 SQL)
Determining the CCSID of a string value in an SQL statement
Knowing the CCSID of a particular string value in an SQL statement helps you determine how Db2
evaluates the statement. This knowledge also helps you plan for character conversions. You can
determine whether character conversion is necessary and what character conversions you need to define.
About this task
The CCSID that is associated with a string value depends on the SQL statement in which the data is
referenced and the type of expression.
Procedure
To determine the CCSID of a string value in Db2, use one or more of the following techniques:
• Use the rules for determining the CCSID that is associated with string data, as specified in Encoding [DB2Z-5-271]
scheme and CCSID rules for strings (Introduction to Db2 for z/OS). [DB2Z-5-272]



• Use the DESCRIBE statement and then check the SQLDA. The SQLDA contains one SQLVAR entry [DB2Z-5-273]
for each column or host variable that is described. For string columns and parameters, look in the
SQLDATA field of the appropriate SQLVAR entry to find the CCSID of that column or parameter. For
more information, see SQLDATA field of the SQLDA (Db2 SQL).
Related concepts
Code pages and CCSIDs
Because computers store only numbers, they store letters and other characters by assigning a number
to them. Which number is mapped to each character depends on the CCSID and code page that is
associated with that character.
Related reference
DESCRIBE statements (Db2 SQL)
SQL descriptor area (SQLDA) (Db2 SQL)
SQLDA field descriptions (Db2 SQL)
Objects with different CCSIDs in the same SQL statement
You can reference data with different CCSIDs from the same SQL statement. This ability is useful if you
use table objects such as tables, views, temporary tables, query tables, and user-defined functions with
different CCSIDs. However, you should understand how Db2 for z/OS processes these queries so that you
can code them correctly.
Although the data that the statement references can have different CCSIDs, the SQL statement, including
string constants, is written in only one CCSID. The CCSID that the SQL statement is written in is the source
CCSID for your application.
Db2 for z/OS considers any SQL statement that satisfies at least one of the following criteria to be a
statement that references objects with multiple CCSIDs:
GUPI
• References table objects with different CCSIDs [DB2Z-5-274]
• Contains any of the following functions: [DB2Z-5-275]
– ASCII_CHR
– ASCII_STR
– ASCIISTR
– EBCDIC_CHR
– EBCDIC_STR
– CAST with the CCSID clause
– CHR
– DECRYPT_BIT
– DECRYPT_CHAR
– DECRYPT_DB
– GETVARIABLE
– GX
– NORMALIZE_STRING
– UNICODE_STR
– UNISTR
– UX
– XML2CLOB
– XMLSERIALIZE
– XMLTABLE
– A table user-defined function [DB2Z-5-276]


  75



• Is one of the following SQL statements: [DB2Z-5-277]
– CALL
– SET host-variable assignment
– SET special register
– VALUES
– VALUES INTO
GUPI
If a statement references objects with multiple CCSIDs, Db2 processes the statement as follows:
1. Db2 first determines the CCSID for each item that the statement references. Db2 uses the rules in the [DB2Z-5-278]
table that describes the operand types in Conversion rules for comparisons (Db2 SQL).
2. Db2 then evaluates the predicates according to the rules that are listed in the "Operand that supplies [DB2Z-5-279]
the CCSID for character conversion" table in Conversion rules for comparisons (Db2 SQL).
Regardless of the CCSIDs of the referenced data, your application can receive the data in any CCSID that
it wants. For example, suppose that your application selects rows from SYSIBM.SYSTABLES. The CCSIDs
of the retrieved data are all Unicode CCSIDs. However, when you issue the SELECT statement, the data
is returned to your application in your application encoding CCSID. This behavior is evident in the SPUFI
application, which uses the EBCDIC encoding scheme. When you run a query against the Db2 catalog in
SPUFI, EBCDIC data is returned.
Example statements that reference objects with different CCSIDs
GUPI
Example
Assume that EBCDICTABLE is encoded in EBCDIC, and the host variables are encoded in the
application encoding scheme. SYSIBM.SYSTABLES is encoded in Unicode. Consider the following
statement that references these objects with different CCSIDs:
SELECT A.NAME, A.CREATOR, B.CHARCOL, 'ABC', :hvchar, X'C1C2C3'
FROM SYSIBM.SYSTABLES A, EBCDICTABLE B
WHERE A.NAME = B.NAME AND
      B.NAME > 'B' AND
      A.CREATOR = 'SYSADM'
ORDER BY B.NAME
Db2 uses the following CCSIDs for each item that the statement references:
Part of statement
Corresponding CCSID that Db2 uses during
evaluation of the statement
A.NAME Unicode CCSID
A.CREATOR Unicode CCSID
B.CHARCOL EBCDIC CCSID
'ABC' Application encoding scheme CCSID1
:hvchar, Application encoding scheme CCSID1
X'C1C2C3' Application encoding scheme CCSID1
B.NAME EBCDIC
Notes:
1. Application encoding scheme CCSID is the value of the ENCODING bind option. [DB2Z-5-280]
Db2 then evaluates the statement as follows: [DB2Z-5-281]



Part of statement
Corresponding CCSID that Db2
uses during evaluation of the
statement Reason
A.NAME = B.NAME Unicode CCSID Because both operands are
columns and the CCSIDs are
different, Db2 uses Unicode.
B.NAME > 'B' EBCDIC CCSID Because the first operand is a
column and the second operand
is a string, Db2 uses the CCSID
of the first operand, which is
EBCDIC.
A.CREATOR = 'SYSADM' Unicode CCSID Because the first operand is a
column and the second operand
is a string, Db2 uses the CCSID
of the first operand, which is
Unicode.
The result of this statement contains multiple CCSIDs. However, your application receives the result of
this statement in the application encoding CCSID.
Example
Assume that you issue the following statements to create and populate a Unicode table and EBCDIC
table:
CREATE TABLE TCCSIDU (CU1 VARCHAR(12)) CCSID UNICODE;
CREATE TABLE TCCSIDE (CE1 VARCHAR(12)) CCSID EBCDIC;
INSERT INTO TCCSIDU VALUES (‘Jürgen');
INSERT INTO TCCSIDE VALUES ('Jürgen');
The following query joins those two tables.
SELECT LENGTH(A.CU1) AS L1, HEX(A.CU1) AS H1,
LENGTH(B.CE1) AS L2, HEX(B.CE1) AS H2
FROM TCCSIDU A, TCCSIDE B WHERE A.CU1 = B.CE1;
The WHERE predicate compares two columns with different CCSIDs. Column A.CU1 is encoded in
Unicode. Column B.CE1 is encoded in EBCDIC. For this comparison, Db2 promotes B.CE1 to Unicode.
Therefore Db2 evaluates the EBCDIC value 'Jürgen' in B.CE1 as equal to the Unicode value 'Jürgen' in
A.CU1. This query returns the following result:
L1 H1 L2 H2
7 4AC3BC7267656E 6 D1DC99878595 [DB2Z-5-282]
Even though B.CE1 was promoted to Unicode for the comparison in the WHERE clause, the result still
shows the EBCDIC hexadecimal value for B.CE1.
GUPI
Related tasks
Specifying a CCSID for your application
In Db2 for z/OS applications, one CCSID is associated with the source code and one or more CCSIDs can
be associated with the data that your application manipulates. The CCSID that Db2 associates with the
data is called the application encoding scheme.
Related reference
DESCRIBE statements (Db2 SQL)
SQL descriptor area (SQLDA) (Db2 SQL) [DB2Z-5-283]
