Appendix A. Db2 utilities and Unicode support
You can run Db2 utilities on Unicode data, request that Db2 utilities return data in Unicode, and write
utility control statements in Unicode.
More specifically, you can perform the following tasks with Db2 utilities and Unicode data:
• You can load Unicode data into your tables by using the LOAD utility with the UNICODE option. The [DB2Z-A-001]
target table does not need to be a Unicode table. You can load Unicode data into an ASCII or EBCDIC
table. Likewise, you can load ASCII or EBCDIC data into a Unicode table. However, in these cases, Db2
converts the input data to the CCSID of the table space before loading it.
• You can use the cross-loader function to load data from a EBCDIC, ASCII, or Unicode source table to an [DB2Z-A-002]
EBCDIC, ASCII, or Unicode target table. If the encoding scheme of the source table is different than the
target table, Db2 converts the input data to the encoding scheme of the target table.
• You can unload data in Unicode format by using the UNLOAD utility with the UNICODE option or the IBM [DB2Z-A-003]
Db2 High Performance Unload tool.
Restriction: With the UNLOAD utility, you cannot:
– Unload ASCII or EBCDIC SBCS data to UTF-16 output fields.
– Unload UTF-16 data to ASCII or EBCDIC SBCS output fields.
– Unload UTF-8 data to UTF-16 output fields.
– Unload UTF-16 data to UTF-8 output fields.
For these situations, use the High Performance Unload tool.
• You can write utility control statements in either EBCDIC or UTF-8. [DB2Z-A-004]
• You can use the Db2-supplied stored procedures DSNUTILV or DSNUTILU to invoke a Db2 utility from an [DB2Z-A-005]
application program with a utility control statement that is written in Unicode. Alternatively, you can use
the DSNUTILV or DSNUTILU stored procedure with an EBCDIC utility control statement.
Related concepts
Utility control statements (Db2 Utilities)
Related tasks
Loading data by using the cross-loader function (Db2 Utilities)
Related reference
LOAD (Db2 Utilities)
UNLOAD (Db2 Utilities)
Db2 High Performance Unload for z/OS
DSNUTILU stored procedure (Db2 SQL)
DSNUTILS stored procedure (deprecated) (Db2 SQL)
DSNUTILV stored procedure (Db2 SQL) [DB2Z-A-006]
