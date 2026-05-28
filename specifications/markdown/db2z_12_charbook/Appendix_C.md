Appendix C. Db2 ODBC Unicode support
Your Db2 for z/OS ODBC programs can manipulate Unicode data and report the CCSID settings of the
subsystem.
If your application manipulates UTF-8 data, set the initialization keyword CURRENTAPPENSCH to
UNICODE or any Unicode CCSID value. When you set CURRENTAPPENSCH for Unicode data, you can
use the following items for UTF-8 data:
• The generic APIs, such as SQLColumnPrivileges. When CURRENTAPPENSCH is set to UNICODE, these [DB2Z-C-001]
APIs accept UTF-8 string arguments and return all character string data in the result set in UTF-8.
• C data type SQL_C_CHAR. When CURRENTAPPENSCH is set to UNICODE, the Db2 for z/OS ODBC [DB2Z-C-002]
driver assumes UTF-8 data for SQL_C_CHAR. This data type is used by the APIs SQLBindCol(),
SQLBindParameter(), and SQLGetData().
If your application manipulates UTF-16 data, use APIs with the suffix W, which are called wide APIs,
on that data. Any generic API that accepts character string arguments has a wide API counterpart. For
example, the corresponding wide API for the SQLConnect() API is SQLConnectW(). Wide APIs accept
UTF-16 string arguments only. The initialization keyword CURRENTAPPENSCH does not affect these
wide APIs. Regardless of what you specify for CURRENTAPPENSCH, these wide APIs always expect
Unicode UTF-16 data. You can also use the SQL_C_WCHAR data type for UTF-16 data. Like wide APIs,
SQL_C_WCHAR also assumes UTF-16 data, regardless of what you specify for CURRENTAPPENSCH.
You can use the SQLGetInfo() API with certain attributes, such as SQL_ASCII_SCCSID, to query the CCSID
settings of the Db2 subsystem.
Related concepts
Application encoding schemes and Db2 ODBC (Db2 Programming for ODBC)
Related reference
Db2 ODBC initialization keywords (Db2 Programming for ODBC)
C and SQL data types (Db2 Programming for ODBC)
SQLGetInfo() - Get general information (Db2 Programming for ODBC) [DB2Z-C-003]
