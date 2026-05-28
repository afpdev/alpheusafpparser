Chapter 6. Debugging CCSID and Unicode problems
Some errors are obviously a problem with a CCSID or Unicode object. In other cases, Db2 returns
unexpected data and you need to check if a CCSID is the cause of the problem. In these cases, you might
not be using Unicode data or doing anything with CCSIDs other than accepting the default values.
Procedure
Consider the symptoms and possible solutions as shown in the following table: [DB2Z-6-001]

Table 31. Possible solutions to Unicode and CCSID problems [DB2Z-6-002]

| Symptom | Possible solution |
| :--- | :--- |
| A single character is displayed incorrectly. | A CCSID is probably set incorrectly somewhere. Check the following settings:<br>• Ensure that your subsystem CCSIDs are correct. See “Finding the CCSID values of your data sources” and “Specifying subsystem CCSIDs”. If you suspect that you need to change one of your subsystem CCSID values, call IBM Support.<br>• Ensure that the application that you are using is bound with the correct CCSID. Use the ENCODING bind option. For example, if you are using SPUFI, make sure that the SPUFI package is bound with the CCSID that matches the one on your terminal emulator. See “Specifying a CCSID for your application”.<br>Also try displaying the character in hexadecimal format to see if you can determine what encoding the character is in. Knowing the encoding can also help IBM Support, if you need to contact them. [DB2Z-6-003]|
| A conversion that you need is not defined in z/OS Unicode Services. You might have received SQLCODE 332 or message DSNT552I. | Add the missing conversion definition. See “Setting up z/OS Unicode Services for Db2 for z/OS”. [DB2Z-6-004]|
| Lowercase special characters do not become uppercase. | Ensure that you are specifying the correct locale. See “Performing culturally correct case conversions”. [DB2Z-6-005]|
| An insert operation of EBCDIC or ASCII data into a Unicode table fails. | Ensure that the column size is large enough to handle any possible data expansion. See “Potential problems when inserting non-Unicode data into a Unicode table”. [DB2Z-6-006]|
| Object names are unreadable in Db2 utility listings | Make sure that the values that are set in DSNHMCID match those values in DSNHDECP. CCSID settings in DSNHMCID are used for certain messages. DSNHMCID settings need to match those same settings in the DSNHDECP load module that the Db2 subsystem is using. For more information about DSNHMCID, see Job DSNTIJUM: define Db2 data-only offline message generator CCSID (DSNHMCID) module (Db2 Installation and Migration). [DB2Z-6-007]|
Potential problems when inserting non-Unicode data into a
Unicode table
If you insert EBCDIC or ASCII data into a Unicode table, the data is converted to Unicode. The length of
this converted data might increase so much that it causes the operation to fail.
If the source encoding scheme is EBCDIC or ASCII and the target encoding scheme is UTF-8, the worst-
case expansion is three times the original. To allow for this worst-case expansion, in the CREATE TABLE
statement, declare your UTF-8 columns to be three times the size of your ASCII or EBCDIC columns. You
might also want to make your columns varying length so that Db2 does not need to perform padding and
truncation on the columns when the length changes due to conversion.
For more information about determining the appropriate column length, see “Estimating the column size
for Unicode data”.
Related concepts
Expanding conversion
An expanding conversion occurs when the length of the converted string is greater than that of the source
string.
Related tasks
Creating a Unicode table
If you plan to store Unicode data, create Unicode tables. If you try to insert Unicode data into an ASCII or
EBCDIC table, data might be lost, unless you use escaped data. [DB2Z-6-008]

