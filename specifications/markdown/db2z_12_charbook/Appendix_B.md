Appendix B. EXPLAIN Unicode support
You can use Db2 EXPLAIN to capture access path information for your queries. This information is stored
in the Db2 EXPLAIN tables, which are encoded in UTF-8.
When you retrieve data from the EXPLAIN tables, be aware that the data is encoded in UTF-8.
The following EXPLAIN table columns store encoding and CCSID information:
PLAN_TABLE columns:
TABLE_ENCODE
Indicates the encoding scheme of the statement. If the statement represents a single CCSID set,
the column contains 'E' for EBCDIC, 'A' for ASCII, or 'U' for Unicode. If the statement is a multiple
CCSID set statement, the column is set to 'M' for multiple CCSID sets.
TABLE_SCCSID
Contains the SBCS CCSID value of the table or zero if the TABLE_ENCODE column is 'M.'
TABLE_MCCSID
Contains the Mixed CCSID value of the table or zero if the TABLE_ENCODE column is 'M.'
TABLE_DCCSID
Contains the DBCS CCSID value of the table or zero if the TABLE_ENCODE column is 'M.'
DSN_STATEMNT_TABLE column:
STMT_ENCODE
Indicates the encoding scheme of the statement. If the statement represents a single CCSID set,
the column contains 'E' for EBCDIC, 'A' for ASCII, or 'U' for Unicode. If the statement is a multiple
CCSID set statement, the column is set to 'M' for multiple CCSID sets.
Related concepts
Investigating SQL performance by using EXPLAIN (Db2 Performance) [DB2Z-B-001]
