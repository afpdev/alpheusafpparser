Chapter 3. Setting up Db2 to ensure that it interprets characters correctly
You need to make sure that Db2 uses the correct code page (which is identified by a CCSID) to interpret
your data. Otherwise, Db2 might store or use incorrect data. This situation is most likely to occur when
characters are converted or transferred between systems.
Procedure
To ensure that Db2 interprets characters correctly:
1. Determine the CCSID of your data sources. [DB2Z-3-001]
2. Based on the CCSIDs of your data sources, specify the correct CCSIDs for your subsystem, objects, [DB2Z-3-002]
and applications in Db2. If the CCSIDs of all of your data sources do not match and you need help
determining the appropriate CCSIDs to specify, call IBM Support.
Recommendation: If possible, set up your system, applications, and objects to avoid character
conversion on z/OS, because character conversion has an expensive CPU cost. You can avoid character
conversion by using the same CCSID in all of your data sources. Of course, do not do so at the expense
of data integrity.
3. Set up z/OS Unicode Services. [DB2Z-3-003]
4. Optional: Define any additional character conversions. [DB2Z-3-004]
Character conversions are already defined in the following two places:
The Db2 catalog table SYSIBM.SYSSTRINGS
This table contains character conversion definitions from IBM. You might have also added your
own.
The conversion image in z/OS Unicode Services
You configured this image when you set up z/OS Unicode Services.
However, you might need to define additional conversions. If you are not sure if a particular character
conversion is defined to Db2, check your character conversion definitions.
What to do next
Recommendation: If your Db2 subsystem has users that use different CCSIDs, be careful when you
create and name objects. Choose identifiers, such as table names and column names, that can be
represented on all clients that access the Db2 subsystem.
How Db2 performs character conversions
When character conversions are needed, Db2 for z/OS performs these conversions automatically based
on the CCSIDs of the source and target data. When you set up Db2, you need to identify valid conversion
definitions for source and target CCSIDs to Db2. Some of these definitions are predefined for you.
To perform a conversion from one CCSID to another CCSID, Db2 uses the translation tables that are
identified by the following resources in the order listed.
1. The Db2 catalog table SYSIBM.SYSSTRINGS [DB2Z-3-005]
Each row in this catalog table describes a conversion from one coded character set to another. IBM
supplies some of the rows. You can also add your own rows. If the same pair of CCSIDs are in two
rows, one row that is IBM-supplied and one row that you added, Db2 uses the row that you provided.
Rows that you add have IBMREQD=N. However, some rows that have IBMREQD=N might have been
loaded from maintenance that IBM ships between releases. [DB2Z-3-006]

SYSIBM.SYSSTRINGS describes only those conversions to and from ASCII and EBCDIC CCSIDs.
Conversions to and from Unicode CCSIDs are not included in SYSIBM.SYSSTRINGS.
2. z/OS Unicode Services [DB2Z-3-007]
z/OS Unicode Services uses the conversion definitions in a conversion image data set.
Thus, any conversions that are defined in SYSIBM.SYSSTRINGS override the conversions that are defined
in z/OS Unicode Services. If SYSIBM.SYSSTRINGS does not define a conversion, Db2 uses z/OS Unicode
Services.
If a conversion for a certain combination of source and target CCSIDs is not defined in
SYSIBM.SYSSTRINGS or z/OS Unicode Services, z/OS Unicode Services dynamically adds the conversion.
This ability is available in z/OS 1.7 and later.
Related tasks
Setting up z/OS Unicode Services for Db2 for z/OS
Db2 for z/OS uses z/OS Unicode Services to perform character conversions and case conversions. You
should set up z/OS Unicode Services specifically for Db2 for z/OS to ensure optimal Db2 performance.
Defining additional character conversions
You must define valid conversion definitions for source and target CCSIDs for Db2 to use when performing
character conversions. Many of these definitions already exist in SYSIBM.SYSSTRINGS and in the
conversion image that you set up for z/OS Unicode Services. However, you might need to add more.
Related reference
Manually setting up Unicode Services (z/OS: Unicode Services User’s Guide and Reference)
Creating user-defined conversion tables (z/OS: Unicode Services User’s Guide and Reference)
Conversion Tables Supplied with z/OS Unicode (z/OS: Unicode Services User’s Guide and Reference)
SYSIBM.SYSSTRINGS catalog table
The Db2 catalog table SYSIBM.SYSSTRINGS contains information about valid character conversion
definitions. Each row of SYSSTRINGS contains information about the conversion of character strings from
one CCSID to another CCSID. Db2 uses the conversion tables that are identified by these rows.
GUPI
Db2 automatically performs any required conversions from the CCSID that is identified by the INCCSID
column to the CCSID that is identified by the OUTCCSID column.
Restriction: You cannot update or delete rows that are provided by IBM. These rows are identified by
a value of Y in the IBMREQD column. However, you can add another row with the same pair of CCSIDs.
Rows that you add are identified by a value of N in the IBMREQD column. If two rows exist for the same
pair of CCSIDs (an IBM-supplied row and a row that you added) Db2 uses your row for the conversion.
In some cases, rows with IBMREQD = N are not rows that you added. Sometimes, these rows have been
supplied by IBM Software Support. For an example of adding such rows, look at job DSNTEJ1T.
Example
Assume that the SYSSTRINGS table includes the following rows:
     INCCSID     OUTCCSID  TRANSTYPE  ERRORBYTE  SUBBYTE  TRANSPROC      IBMREQD  TRANSTAB
---------+---------+---------+---------+---------+---------+---------+---------+-------------
        500           37  SS         ---------  -------                  Y        ........... [DB2Z-3-008]
         37          500  SS         ---------  -------                  Y        ........... [DB2Z-3-009]
        948           37  PS         3E         3F                       Y        ........... [DB2Z-3-010]
All of these rows were supplied by IBM because they have the value Y in the IBMREQD column. These
rows have the following meanings:
• The first row describes the conversion from CCSID 500 to CCSID 37. [DB2Z-3-011]
• The second row describes the conversion from CCSID 37 to CCSID 500. [DB2Z-3-012]

• The third row describes a conversion from CCSID 948 to CCSID 37 in which X'3E' is used as an error [DB2Z-3-013]
indicator and X'3F' is used as a substitute code point.
Tip: Use the HEX function to display the values of the ERRORBYTE, SUBBYTE, and TRANSTAB columns.
GUPI
Related concepts
Installation verification phases and programs (Db2 Installation and Migration)
Related reference
SYSSTRINGS catalog table (Db2 SQL)
Finding the CCSID values of your data sources
Before you can specify appropriate CCSID values to Db2, you must know the CCSID values that are in
effect for all of your data sources. Determining these CCSID values is the first step to preserving data
integrity.
About this task
You should know the CCSIDs of all the data that Db2 handles, including all input and output sources, such
as the following sources:
• local input and output devices, such as your 3270 terminal emulators and printers [DB2Z-3-014]
• tape data [DB2Z-3-015]
• source and data from your application, which are handled by either the Db2 precompiler or a compiler [DB2Z-3-016]
and the Db2 coprocessor
• data from gateway products, such as IBM MQ, IMS Connect, CICS Transaction Gateway and any third [DB2Z-3-017]
party products
• FTP data [DB2Z-3-018]
• Any data from a distributed environment [DB2Z-3-019]
Procedure
Use the resources in the following table: [DB2Z-3-020]

### Table 13. How to find the CCSID of your data source

| Source | Where find the CCSID in effect |
| :--- | :--- |
| Application data (in host variables or parameter markers) | Look at the value of the ENCODING bind option unless that value was overridden. For more details about how this option could have been overridden, see “Specifying a CCSID for your application”. [DB2Z-3-021]|
| C/C++ application source code | Look at the Db2 precompiler or compiler listing for the CCSID options that were used. For example, the following listing for the Db2 precompiler shows that the application uses CCSID 1047:<br>OPTIONS USED - SPECIFIED OR DEFAULTED<br>APOST<br>APOSTSQL<br>ATTACH(TSO)<br>CCSID(1047) [DB2Z-3-022]|
| CICS Transaction Gateway | Look at the value of the system initialization parameter CLINTCP. See the following resources:<br>• CLINTCP (CICS Transaction Server for z/OS)<br>• Character data (CICS Transaction Server for z/OS)<br>• ECI applications (CICS Transaction Gateway V5 The WebSphere Connector for CICS) [DB2Z-3-023]|
| COBOL application source code | Look at the Db2 precompiler or compiler listing for the CCSID options that were used. For example, the following listing for the Db2 precompiler shows that the application uses CCSID 37:<br>OPTIONS USED - SPECIFIED OR DEFAULTED<br>APOST<br>APOSTSQL<br>ATTACH(TSO)<br>CCSID(37) [DB2Z-3-024]|
| FTP | See How can I check my CCSIDs for FTP?. [DB2Z-3-025]|
| IMS | Look at the terminal emulator CCSID. (Follow the instructions for ISPF or Personal Communications.) IMS uses this CCSID when communicating to Db2 for z/OS.<br>In IMS Connect, conversion is done by user message exits. Look at those exits for CCSID information. See User exit (EX) ADD command (IMS Connect Extensions). [DB2Z-3-026]|
| ISPF | Look at the value of the ISPF session variable ZTERMCID under ISPF option 7.3 - variable settings. [DB2Z-3-027]|
| Personal Communications | Look at the Host Code-Page session parameter to find the terminal CCSID. See Configuring Sessions (Personal Communications) [DB2Z-3-028]|
| PL/I application source code | Look at the Db2 precompiler or compiler listing for the CCSID options that were used. For example, the following listing for the Db2 precompiler shows that the application uses CCSID 37:<br>OPTIONS USED - SPECIFIED OR DEFAULTED<br>APOST<br>APOSTSQL<br>ATTACH(TSO)<br>CCSID(37) [DB2Z-3-029]|
| QMF | Check your Graphical Data Display Manager (GDDM) code page setting, because QMF uses GDDM to do the display. You can check your GDDM code page setting by looking at the APPCPG parameter, which can be set in one of the following two places:<br>• a defaults module that is called ADMADFT<br>• a file that is referred to as ADMDEFS.<br>If no value is specified for APPCPG, GDDM uses a default CCSID of 00351. For more information about APPCPG, see Customizing GDDM external defaults (GDDM System Customization and Administration Guide).<br>Recommendation: For QMF, set the APPCPG parameter to match the CCSID that is used by Db2 and your terminal emulator. [DB2Z-3-030]|
| Queue Managers in IBM MQ | Follow the instructions for viewing and setting the Queue Manager CCSID in Data Conversion under IBM MQ. You can also check individual MQGET and MQPUT statements. These statements can override the MQ CCSID setting by specifying a CCSID in the statement. [DB2Z-3-031]|
| TSO | Perform one of the following actions:<br>• Specify the CODEPG keyword when issuing the GTTERM -- Get Terminal Attributes (TSO/E Programming Services) macro to retrieve the Character Set and Code Page (CGCSGID) for a TSO session.<br>• Issue the DISPLAY TSOUSER command (z/OS Communications Server: SNA Operation). The output from this command includes the CDCSGID information when it is available. [DB2Z-3-032]|
| z/OS DFSMS | SMS (the file system) CCSID is an attribute of SMS-managed data sets. For more information about how that CCSID is set, see Data Conversion for z/OS Distributed FileManager (z/OS Distributed FileManager Guide and Reference) or Converting data for z/OS Network File System (z/OS Network File System Guide and Reference) or search the CCSID file tagging information in z/OS UNIX System Services Command Reference.<br>However, the access methods (VSAM, BSAM/QSAM, BPAM, etc) for these data sets do not have support to perform conversions. Only DFM supports conversions between CCSIDs for DASD data sets.<br>The CCSID value also can be used when reading or writing magnetic tapes that have ISO/ANSI tape labels. You can code the CCSID keyword on the DD statement or supply it in the data class. You can also supply the CCSID value on the JOB or STEP JCL statement. For more information, see Character Data Conversion (z/OS DFSMS Using Data Sets). [DB2Z-3-033]|

Related concepts
Differences between the Db2 coprocessor and the Db2 precompiler (Db2 Application programming and
SQL)
Related reference
Internationalization: Locales and Character Sets (XL C/C++ Programming Guide)
Compiler Options (C/C++) (XL C/C++ User's Guide)
Planning to modify compiler option default values (COBOL) (Enterprise COBOL for z/OS Customization
Guide)
Compiler options (COBOL) (Enterprise COBOL for z/OS Programming Guide)
Z variables (ISPF session variables)
Changing the default options (PL/I) (Enterprise PL/I for z/OS Programming Guide:)
Compile-time option descriptions (PL/I) (Enterprise PL/I for z/OS Programming Guide:)
Related information
IBM MQ documentation


Specifying CCSIDs in Db2
You must communicate to Db2 the correct CCSIDs to use for your data to ensure that Db2 correctly
interprets your data. You can specify default subsystem CCSIDs. You can also specify CCSIDs for
individual applications and Db2 objects.
About this task
Specifying appropriate CCSIDs also ensures that Db2 performs accurate character conversions when
distributed systems access Db2.
Procedure
To specify CCSIDs in Db2:
• When you install Db2, specify default subsystem CCSIDs. [DB2Z-3-034]
• When you create objects, specify object CCSIDs. [DB2Z-3-035]
• When you create applications, specify application CCSIDs. [DB2Z-3-036]
Related concepts
Euro symbol support (Db2 Installation and Migration)
Specifying subsystem CCSIDs
You specify the default subsystem CCSIDs when you install Db2. Db2 uses these values for objects and
applications if no other CCSID values are specified.
Before you begin
Before you specify subsystem CCSIDs, determine the CCSID of your data sources. Knowing the CCSID of
your data sources helps you determine what the subsystem CCSIDs should be. Ideally, your subsystem
CCSIDs should match the CCSIDs in the majority of your data sources. If you need help determining the
correct values, contact IBM Support.
About this task
Important: Never change CCSIDs on an existing Db2 subsystem without guidance from IBM Support.
If you think you need to change your subsystem CCSIDs, first consider the effects on all of your tools,
applications, and utilities. Then contact IBM Support.
Procedure
When you install Db2, specify the CCSIDs for the subsystem by using installation panel DSNTIPF or
installation job DSNTIJUZ.
• For the subsystem EBCDIC and ASCII CCSIDs, you must specify values according to the following [DB2Z-3-037]
criteria:
– You must specify valid, non-zero CCSIDs for single-byte character set (SBCS) data. You should
specify the CCSID values that you want to use for EBCDIC and ASCII data and objects by default. For
a list of valid CCSIDs, see EBCDIC and ASCII support.
– If you use languages with double-byte characters, such as Chinese, Japanese, or Korean, you
must also specify valid, non-zero CCSIDs for multibyte character set (MBCS) data and double-byte
character set (DBCS) data. All three of these values, the single-byte CCSID value (SBCS), the mixed
CCSID value, and the double-byte CCSID value (DBCS), that are associated with a particular encoding
scheme are collectively called a CCSID set. If you set these three values by using the installation
panel DSNTIPF, you need to explicitly specify only the MBCS value. Db2 calculates the value of the
other two based on the MBCS value. If you specify these values in job DSNTIJUZ, you need specify all
three values.

• For the subsystem Unicode CCSIDs, the values are provided for you, and you cannot change them. [DB2Z-3-038]
These CCSIDs are the only ones that Db2 uses for Unicode objects.
All of these CCSIDs are stored in dsnhdecp and must be valid. dsnhdecp is the DSNHDECP module or a
user-specified application defaults module.
During startup processing, if Db2 detects invalid CCSID values, Db2 issues a message and terminates.
Related concepts
Euro symbol support (Db2 Installation and Migration)
Related reference
DSNTIPF: Application programming defaults panel 1 (Db2 Installation and Migration)
Job DSNTIJUM: define Db2 data-only offline message generator CCSID (DSNHMCID) module (Db2
Installation and Migration)
EBCDIC and ASCII support (Db2 Installation and Migration)
Subsystem CCSIDs and encoding schemes
Each Db2 subsystem has a set of default CCSID and encoding scheme values. Db2 uses these values for
objects and applications that do not otherwise have a CCSID associated with them.
The subsystem CCSIDs are listed in the following table. [DB2Z-3-039]

### Table 14. Subsystem CCSIDs

| Subsystem CCSID | Field on installation panel DSNTIPF where the value is set | Description | Corresponding dsnhdecp values<sup>1, 3</sup> |
| :--- | :--- | :--- | :--- |
| Subsystem default ASCII CCSID | ASCII CCSID | Specifies the default CCSID for ASCII-encoded character data that is stored in your Db2 subsystem or data sharing group.<br>For a MIXED=NO subsystem, specify the ASCII SBCS CCSID only. In this case, the mixed and graphic CCSIDs are set to 65534 in dsnhdecp.<br>For a MIXED=YES subsystem, specify the ASCII mixed CCSID. Based on the value that you entered, Db2 determines the SBCS CCSID and graphic CCSID.<sup>2</sup> | • ASCCSID (for single-byte data)<br>• AMCCSID (for mixed data)<br>• AGCCSID (for graphic data) [DB2Z-3-040]|
| Subsystem default EBCDIC CCSID | EBCDIC CCSID | Specifies the default CCSID for EBCDIC-encoded character data that is stored in your Db2 subsystem or data sharing system.<br>For a MIXED=NO subsystem, specify the EBCDIC SBCS CCSID only. In this case, the mixed and graphic CCSIDs are set to 65534 in dsnhdecp.<br>For a MIXED=YES subsystem, specify the EBCDIC mixed CCSID. Based on the value that you entered, Db2 determines the SBCS CCSID and graphic CCSID.<sup>2</sup> | • SCCSID (for single-byte data)<br>• MCCSID (for mixed data)<br>• GCCSID (for graphic data) [DB2Z-3-041]|
| Subsystem default Unicode CCSID | UNICODE CCSID | Specifies the default CCSID for Unicode character data that is stored in your Db2 subsystem or data sharing system.<br>This field is pre-filled with the default value of 1208, which is the CCSID for UTF-8. You cannot change this value. | Because the value of UNICODE CCSID is always 1208, the dsnhdecp values are always as follows:<br>• USCCSID (for single-byte data): 367<br>• UMCCSID (for mixed data): 1208<br>• UGCCSID (for graphic data): 1200 [DB2Z-3-042]|

notes:
1. The three CCSID values, one for SBCS, one for mixed, and one for graphic, are called a CCSID set. [DB2Z-3-043]
2. Whether the subsystem is a MIXED=YES subsystem or MIXED=NO subsystem depends on the value that [DB2Z-3-044]
you specified for the MIXED field on the same panel when you installed Db2. MIXED=NO is the default
setting.
Recommendation: Do not change the MIXED value after you install Db2.
3. dsnhdecp is the DSNHDECP module or a user-specified application defaults module. [DB2Z-3-045]
The subsystem encoding schemes are listed in the following table. [DB2Z-3-046]

### Table 15. Subsystem encoding schemes

| Subsystem encoding scheme | Field on installation panel DSNTIPF where the value is set | Description | Corresponding dsnhdecp values<sup>1</sup> |
| :--- | :--- | :--- | :--- |
| Subsystem default encoding scheme | DEF ENCODING SCHEME | Specifies which default subsystem CCSID (ASCII, EBCDIC, or Unicode) Db2 is to use for objects. | ENSCHEME [DB2Z-3-047]|
| Subsystem default application encoding scheme | APPLICATION ENCODING | Specifies which default subsystem CCSID (ASCII, EBCDIC, or Unicode) Db2 is to use for application data. | APPENSCH [DB2Z-3-048]|

notes:
1. dsnhdecp is the DSNHDECP module or a user-specified application defaults module. [DB2Z-3-049]
Related concepts
Encoding schemes
An encoding scheme standardizes the encoding of character sets by defining a set of rules for representing
character data. Each encoding scheme consists of a number of code pages that adhere to its rules. For
example, code pages 37, 500, and 1047 are all part of the EBCDIC encoding scheme.
Application encoding scheme
The application encoding scheme is the CCSID that your application uses to interpret data in host
variables. For Db2 for z/OS applications, typically the application encoding scheme is the value of the
ENCODING bind option. (By default this value is the subsystem default application encoding scheme.)
Related tasks
Determining current subsystem CCSID and encoding scheme values
For an existing subsystem, you can check the CCSID values, but do not make changes. If you suspect that
the specified CCSIDs are incorrect or you need to change them, contact IBM Support.
Specifying a CCSID for your application
In Db2 for z/OS applications, one CCSID is associated with the source code and one or more CCSIDs can
be associated with the data that your application manipulates. The CCSID that Db2 associates with the
data is called the application encoding scheme.
Related reference
DSNTIPF: Application programming defaults panel 1 (Db2 Installation and Migration)
Determining current subsystem CCSID and encoding scheme values
For an existing subsystem, you can check the CCSID values, but do not make changes. If you suspect that
the specified CCSIDs are incorrect or you need to change them, contact IBM Support.
Procedure
To determine current subsystem CCSID and encoding scheme values, perform one of the following
actions:
• Use the GETVARIABLE function. [DB2Z-3-050]
GUPI
In each of the following example GETVARIABLE calls, :hv3 is a varying-length character variable with a
maximum length of 20. [DB2Z-3-051]

– The following example code retrieves the value of the subsystem EBCDIC CCSID, which contains
three comma-delimited values that correspond to the SBCS, MIXED, and GRAPHIC CCSIDs for the
encoding scheme:
SET :hv3 = GETVARIABLE('SYSIBM.SYSTEM_EBCDIC_CCSID');
– The following example code retrieves the value of the subsystem default encoding scheme:
SET :hv3 = GETVARIABLE('SYSIBM.ENCODING_SCHEME');
– The following example code retrieves the value of the subsystem default application encoding
scheme:
SET :hv3 = GETVARIABLE('SYSIBM.APPLICATION_ENCODING_SCHEME');
GUPI
• Run the DSNJU004 utility for the current subsystem or member, and look at the SYSTEM CCSIDS [DB2Z-3-052]
section in the output.
Restriction: DSNJU004 does not return the subsystem encoding scheme values (DECP values
ENSCHEME and APPENSCH). To get those values, use the GETVARIABLE function.
For example, the following code shows example JCL to execute DSNJU004 and the relevant portion of
the output.
//PLM EXEC PGM=DSNJU004
//GROUP DD DSN=DBD1.BSDS01,DISP=SHR
//SYSPRINT DD SYSOUT=*
//SYSIN DD *
MEMBER *
/*
…
SYSTEM CCSIDS
18:12:47 MAY 18, 2005
SYSTEM CCSIDS
--------------------
ASCII SBCS = 1252
ASCII MIXED = 65534
ASCII DBCS = 65534
EBCDIC SBCS = 37
EBCDIC MBCS = 65534
EBCDIC DBCS = 65534
UNICODE SBCS = 367
UNICODE MBCS = 1208
UNICODE DBCS = 1200
DSNJ200I DSNJU004 PRINT LOG UTILITY PROCESSING COMPLETED SUCCESSFULLY
This output shows that the default ASCII CCSID is 1252 and the default EBCDIC CCSID is 37. This
subsystem does not have CCSIDs defined for ASCII or EBCDIC data that is mixed or double-byte. The
Unicode CCSIDs are the default CCSIDs that are predefined by Db2. You cannot change these values.
• Run job DSNTEJ6Z, which calls the ADMIN_INFO_SYSPARM stored procedure to list your current [DB2Z-3-053]
subsystem parameter settings.
To determine the subsystem CCSIDs, examine the values of the following subsystem parameters:
– MIXED
– AGCCSID
– AMCCSID
– ASCCSID
– GCCSID
– MCCSID
– SCCSID
– UGCCSID
– UMCCSID

– USCCSID
Related concepts
Application encoding scheme
The application encoding scheme is the CCSID that your application uses to interpret data in host
variables. For Db2 for z/OS applications, typically the application encoding scheme is the value of the
ENCODING bind option. (By default this value is the subsystem default application encoding scheme.)
Job DSNTEJ6Z (Db2 Installation and Migration)
Related reference
Subsystem CCSIDs and encoding schemes
Each Db2 subsystem has a set of default CCSID and encoding scheme values. Db2 uses these values for
objects and applications that do not otherwise have a CCSID associated with them.
GETVARIABLE scalar function (Db2 SQL)
DSNJU004 (print log map) (Db2 Utilities)
Specifying object CCSIDs
The default encoding scheme for all Db2 objects is the value of ENSCHEME in dsnhdecp. dsnhdecp is the
DSNHDECP module or a user-specified application defaults module. However, you can override this value
for a particular object.
About this task
The ENSCHEME value was set during installation on panel DSNTIPF in the DEF ENCODING SCHEME field.
Do not change the ENSCHEME DECP value without first considering the implications. This value controls
the default encoding scheme of any newly created objects.
Procedure
Use the CCSID clause in the CREATE statement for any of the following objects:
• Database [DB2Z-3-054]
• Table space [DB2Z-3-055]
• Table
• Procedure or function [DB2Z-3-056]
You can specify one of the following values in the CCSID clause:
ASCII
Use the subsystem default ASCII CCSID.
EBCDIC
Use the subsystem default EBCDIC CCSID.
Unicode
Use the subsystem default Unicode CCSID.
If you do not specify the CCSID clause, the object uses the subsystem default encoding scheme value
(ENSCHEME in dsnhdecp).
Related tasks
Creating a Unicode table
If you plan to store Unicode data, create Unicode tables. If you try to insert Unicode data into an ASCII or
EBCDIC table, data might be lost, unless you use escaped data.
Related reference
Subsystem CCSIDs and encoding schemes [DB2Z-3-057]


Each Db2 subsystem has a set of default CCSID and encoding scheme values. Db2 uses these values for
objects and applications that do not otherwise have a CCSID associated with them.
DSNTIPF: Application programming defaults panel 1 (Db2 Installation and Migration)
Setting up z/OS Unicode Services for Db2 for z/OS
Db2 for z/OS uses z/OS Unicode Services to perform character conversions and case conversions. You
should set up z/OS Unicode Services specifically for Db2 for z/OS to ensure optimal Db2 performance.
About this task
z/OS Unicode Services uses a conversion image to determine how to handle various conversions. The
conversion image tells z/OS Unicode Services which conversion tables to load and use for character and
case conversions. This task explains how to set up such a conversion image. Starting in z/OS 1.7, if at any
time Db2 needs a conversion that is not in your image, z/OS Unicode Services loads it on demand.
Tip: Even though you are not required to create your own conversion image, do so anyway by performing
the steps in this task. Db2 for z/OS requires that certain conversions be available before it can start. When
you define your own conversion image, as described in this task, those conversions are loaded when z/OS
is IPLed and are available when Db2 starts. Otherwise, Db2 might be suspended by z/OS multiple times
during startup as each of the required conversion tables is loaded by z/OS on demand.
Procedure
Follow the instructions in these sections in the z/OS Unicode Services information:
• Manually setting up Unicode Services (z/OS: Unicode Services User’s Guide and Reference) [DB2Z-3-058]
• Creating user-defined conversion tables (z/OS: Unicode Services User’s Guide and Reference) [DB2Z-3-059]
What to do next
If you later need to alter your conversion image in any way, use the SETUNI command.
Related reference
Conversion Tables Supplied with z/OS Unicode (z/OS: Unicode Services User’s Guide and Reference)
Conversion image
A conversion image is a data set that contains the information that z/OS Unicode Services needs when
performing character and case conversions. The conversion image defines which conversion tables z/OS
is to load and use for these conversions.
You create a conversion image by invoking the z/OS Unicode Services image generator when you set up
z/OS Unicode Services. The image generator creates the conversion image according to what you specify
in the SYSIN DD statement in the job that invokes the image generator, CUNJIUTL. The generated image is
stored in the data set that is identified in the SYSIMG DD statement. Messages from this process are listed
in the data set that is identified by the SYSPRINT DD statement.
You can activate a conversion image during IPL or by issuing the z/OS SET UNI or SETUNI system
command.
If you have z/OS 1.7 or later, conversions are loaded on request. However, to avoid waiting while
conversion tables are loaded, you can create your own conversion image.
You can create more than one conversion image. These images are kept in different data sets. Use the
SET UNI or SETUNI command to merge these images into the existing z/OS Unicode Services conversion
image. Any tables in the new image that intersect with tables in the existing image are not loaded.
You can add, delete, or replace conversion images by using the SET UNI or SETUNI command.
Related reference
z/OS SETUNI Command (z/OS MVS System Commands) [DB2Z-3-060]

Creating a conversion image (z/OS: Unicode Services User’s Guide and Reference)
Basic character conversions for Db2 in the z/OS conversion image
When you set up z/OS Unicode Services for Db2, you need to define a set of basic conversions between
various CCSIDs.
To define these basic character conversions, add the basic conversion statements to high-level-
qualifier.SCUNJCL(CUNJIUTL). These conversions are then added to the z/OS conversion image. Any
duplicate statements are ignored.
In these CONVERSION statements, the variables have the following meanings:
your sccsid
The EBCDIC SBCS CCSID that is specified in your dsnhdecp module.
your asccsid
The ASCII SBCS CCSID that is specified in your dsnhdecp module.
your mccsid
The EBCDIC MBCS CCSID that is specified in your dsnhdecp module.
your amccsid
The ASCII MBCS CCSID that is specified in your dsnhdecp module.
your gccsid
The EBCDIC graphic CCSID that is specified in your dsnhdecp module.
your agccsid
The ASCII graphic CCSID that is specified in your dsnhdecp module.
client ccsid
The CCSID from a client that makes remote connections to this Db2 subsystem.
dsnhdecp is the user-specified application defaults module.
Basic CONVERSION statements
• Specify the following conversion definitions between your ASCII and EBCDIC system CCSIDs and [DB2Z-3-061]
CCSIDs 367, 1208, and 1200:
CONVERSION your sccsid ,00367,ER;
CONVERSION your sccsid ,01200,ER;
CONVERSION your sccsid ,01208,ER;
CONVERSION your sccsid ,your asccsid ,ER;
CONVERSION 00367, your sccsid ,ER;
CONVERSION 00367,01200,ER;
CONVERSION 00367,01208,ER;
CONVERSION 00367, your asccsid ,ER;
CONVERSION 01200, your sccsid ,ER;
CONVERSION 01200,00367,ER;
CONVERSION 01200,01208,ER;
CONVERSION 01200, your asccsid ,ER;
CONVERSION 01208, your sccsid ,ER;
CONVERSION 01208,00367,ER;
CONVERSION 01208,01200,ER;
CONVERSION 01208, your asccsid ,ER;
CONVERSION your asccsid ,your sccsid ,ER;
CONVERSION your asccsid ,00367,ER;
CONVERSION your asccsid ,01200,ER;
CONVERSION your asccsid ,01208,ER;
• If you use the samples that are provided with Db2, also define the following conversions: [DB2Z-3-062]
CONVERSION 00037, 00367, ER;
CONVERSION 00037, 01200, ER;
CONVERSION 00037, 1208, ER;
CONVERSION 00367, 0037, ER;
CONVERSION 01200, 00037, ER;
CONVERSION 1208, 00037, ER;
CONVERSION 01047, 00367, ER;
CONVERSION 01047, 01200, ER;
CONVERSION 01047, 1208, ER; [DB2Z-3-063]

CONVERSION 00367, 1047, ER;
CONVERSION 01200, 1047, ER;
CONVERSION 1208, 1047, ER;
• Optional: For completeness, define the following conversions between CCSID 37 and CCSID 1047: [DB2Z-3-064]
CONVERSION 00037, 01047, ER;
CONVERSION 001047, 0037, ER;
• If your dsnhdecp module specifies an EBCDIC SBCS CCSID other than CCSID 37 or CCSID 1047, define [DB2Z-3-065]
the following conversions:
CONVERSION your sccsid , 00367, ER;
CONVERSION your sccsid , 01200, ER;
CONVERSION your sccsid , 01208, ER;
CONVERSION 00367, your sccsid , ER;
CONVERSION 01200, your sccsid , ER;
CONVERSION 01208, your sccsid , ER;
• Optional: For completeness, define the following conversions between the EBCDIC SBCS CCSID that is [DB2Z-3-066]
defined in your dsnhdecp module and CCSIDs 37 and 1047.
CONVERSION 00037, your sccsid , ER;
CONVERSION your sccsid , 00037, ER;
CONVERSION 01047, your sccsid , ER;
CONVERSION your sccsid , 01047, ER;
• If your Db2 subsystem uses mixed-byte or double-byte CCSIDs for EBCDIC and ASCII, specify the [DB2Z-3-067]
following conversions:
CONVERSION your sccsid ,your mccsid ,ER;
CONVERSION your sccsid ,your amccsid ,ER;
CONVERSION your mccsid ,00367,ER;
CONVERSION your mccsid ,01200,ER;
CONVERSION your mccsid ,01208,ER;
CONVERSION your mccsid ,your sccsid ,ER;
CONVERSION your mccsid ,your asccsid ,ER;
CONVERSION your mccsid ,your amccsid ,ER;
CONVERSION your gccsid ,00367,ER;
CONVERSION your gccsid ,01200,ER;
CONVERSION your gccsid ,01208,ER;
CONVERSION your gccsid ,your agccsid ,ER;
CONVERSION your asccsid ,your mccsid ,ER;
CONVERSION your asccsid ,your amccsid ,ER;
CONVERSION your amccsid ,your mccsid ,ER;
CONVERSION your amccsid ,00367,ER;
CONVERSION your amccsid ,01200,ER;
CONVERSION your amccsid ,01208,ER;
CONVERSION your amccsid ,your asccsid ,ER;
CONVERSION your amccsid ,your sccsid ,ER;
CONVERSION your agccsid ,your gccsid ,ER;
CONVERSION your agccsid ,00367,ER;
CONVERSION your agccsid ,01200,ER;
CONVERSION your agccsid ,01208,ER;
CONVERSION 00367, your mccsid ,ER;
CONVERSION 00367, your gccsid ,ER;
CONVERSION 00367, your amccsid ,ER;
CONVERSION 00367, your agccsid ),ER;
CONVERSION 01200, your mccsid ,ER;
CONVERSION 01200, your gccsid ),ER;
CONVERSION 01200, your amccsid ,ER;
CONVERSION 01200, your agccsid ),ER;
CONVERSION 01208, your mccsid ,ER;
CONVERSION 01208, your gccsid ,ER;
CONVERSION 01208, your amccsid ,ER;
CONVERSION 01208, your agccsid ,ER;
• If your dsnhdecp module specifies an EBCDIC SBCS CCSID other than CCSID 37, specify the following [DB2Z-3-068]
conversions:
CONVERSION 00037,00367,ER;
CONVERSION 00037,00500,ER;
CONVERSION 00037,01047,ER;
CONVERSION 00037,01200,ER;
CONVERSION 00037,01208,ER;
CONVERSION 00037,(your asccsid),ER; [DB2Z-3-069]

CONVERSION 00367,00037,ER;
CONVERSION 01200,00037,ER;
CONVERSION 01208,00037,ER;
CONVERSION your asccsid ,00037,ER;
• If your dsnhdecp module specifies an EBCDIC SBCS CCSID other than CCSID 500, specify the following [DB2Z-3-070]
conversions:
CONVERSION 00500,00037,ER;
CONVERSION 00500,00367,ER;
CONVERSION 00500,01047,ER;
CONVERSION 00500,01200,ER;
CONVERSION 00500,01208,ER;
CONVERSION 00500, your asccsid ,ER;
CONVERSION 00367,00500,ER;
CONVERSION 01200,00500,ER;
CONVERSION 01208,00500,ER;
CONVERSION your asccsid ,00500,ER;
• If your dsnhdecp module specifies an EBCDIC SBCS CCSID other than CCSID 1047, specify the [DB2Z-3-071]
following conversions:
CONVERSION 01047,00037,ER;
CONVERSION 01047,00367,ER;
CONVERSION 01047,00500,ER;
CONVERSION 01047,01200,ER;
CONVERSION 01047,01208,ER;
CONVERSION 01047, your asccsid ,ER;
CONVERSION 00367,01047,ER;
CONVERSION 01200,01047,ER;
CONVERSION 01208,01047,ER;
CONVERSION your asccsid ,01047,ER;
• Define the following conversions for each additional CCSID that is presented by clients that make [DB2Z-3-072]
remote connections to this Db2 subsystem:
CONVERSION client ccsid ,00367,ER;
CONVERSION client ccsid ,01200,ER;
CONVERSION client ccsid ,01208,ER;
CONVERSION 00367, client ccsid ,ER;
CONVERSION 01200, client ccsid ,ER;
CONVERSION 01208, client ccsid ,ER;
Character conversions for Chinese, Japanese, and Korean character sets in
the z/OS conversion image
If you use Chinese, Japanese, or Korean character sets, you need to specify several conversions for z/OS
Unicode Services in addition to the basic conversions.
To define these conversions add the additional conversion statements to high-level-
qualifier.SCUNJCL(CUNJIUTL). These conversions are then added to the z/OS conversion image. Any
duplicate statements are ignored.
In these CONVERSION statements, the variables have the following meanings:
your sccsid
The EBCDIC SBCS CCSID that is specified in your dsnhdecp module.
your mccsid
The EBCDIC MBCS CCSID that is specified in your dsnhdecp module.
your gccsid
The EBCDIC DBCS CCSID that is specified in your dsnhdecp module.
your asccsid
The ASCII SBCS CCSID that is specified in your dsnhdecp module.
your amccsid
The ASCII MBCS CCSID that is specified in your dsnhdecp module. [DB2Z-3-073]


your agccsid
The ASCII DBCS CCSID that is specified in your dsnhdecp module.
dsnhdecp is the user-specified application defaults module.
Additional CONVERSION statements
• Specify the following conversions between your EBCDIC MBCS CCSID and the Unicode CCSIDs: [DB2Z-3-074]
CONVERSION your mccsid , 00367, ER;
CONVERSION your mccsid , 01200, ER;
CONVERSION your mccsid , 01208, ER;
CONVERSION 00367, your mccsid , ER;
CONVERSION 01200, your mccsid , ER;
CONVERSION 01208, your mccsid , ER;
• Specify the following conversions between your EBCDIC DBCS CCSID and the Unicode CCSIDs: [DB2Z-3-075]
CONVERSION your gccsid , 00367, ER;
CONVERSION your gccsid , 01200, ER;
CONVERSION your gccsid , 01208, ER;
CONVERSION 00367, your gccsid , ER;
CONVERSION 01200, your gccsid , ER;
CONVERSION 01208, your gccsid , ER;
• Specify the following conversions between your ASCII SBCS CCSID and the Unicode CCSIDs: [DB2Z-3-076]
CONVERSION your asccsid , 00367, ER;
CONVERSION your asccsid , 01200, ER;
CONVERSION your asccsid , 01208, ER;
CONVERSION 00367, your asccsid , ER;
CONVERSION 01200, your asccsid , ER;
CONVERSION 01208, your asccsid , ER;
• Optional: For completeness, specify the following conversions between your ASCII SBCS CCSID and [DB2Z-3-077]
CCSID 37, and between your ASCII SBCS CCSID and CCSID 1047:
CONVERSION 00037, your asccsid , ER;
CONVERSION your asccsid , 00037, ER;
CONVERSION 01047, your asccsid , ER;
CONVERSION your asccsid , 01047, ER;
• Specify the following conversions between your ASCII MBCS CCSID and the Unicode CCSIDs: [DB2Z-3-078]
CONVERSION your amccsid , 00367, ER;
CONVERSION your amccsid , 01200, ER;
CONVERSION your amccsid , 01208, ER;
CONVERSION 00367, your amccsid , ER;
CONVERSION 01200, your amccsid , ER;
CONVERSION 01208, your amccsid , ER;
• Specify the following conversions between your ASCII DBCS CCSID and the Unicode CCSIDs: [DB2Z-3-079]
CONVERSION your agccsid , 00367, ER;
CONVERSION your agccsid , 01200, ER;
CONVERSION your agccsid , 01208, ER;
CONVERSION 00367, your agccsid , ER;
CONVERSION 01200, your agccsid , ER;
CONVERSION 01208, your agccsid , ER;
• If your dsnhdecp module specifies an EBCDIC SBCS CCSID other than CCSID 37 or CCSID 1047, specify [DB2Z-3-080]
the following conversions:
CONVERSION your sccsid , your asccsid , ER;
CONVERSION your asccsid , your sccsid , ER;
• Optional: Specify the following conversions between your system EBCDIC MBCS CCSID and ASCII [DB2Z-3-081]
MBCS CCSID and between your system EBCDIC DBCS CCSID and your ASCII DBCS CCSID:
CONVERSION your mccsid , your amccsid , ER;
CONVERSION your amccsid , your mccsid , ER; [DB2Z-3-082]

CONVERSION your gccsid , your agccsid , ER;
CONVERSION your agccsid , your gccsid , ER;
Defining additional character conversions
You must define valid conversion definitions for source and target CCSIDs for Db2 to use when performing
character conversions. Many of these definitions already exist in SYSIBM.SYSSTRINGS and in the
conversion image that you set up for z/OS Unicode Services. However, you might need to add more.
Procedure
Add the definition to one of the following places:
• The Db2 catalog table SYSIBM.SYSSTRINGS [DB2Z-3-083]
Insert a row with the appropriate definition. The definitions in this table take precedence over the
definitions in z/OS Unicode Services with several exceptions, which are described after this list. Rows
that you insert have a value of 'N' in the IBMREQD column and take precedence over the IBM-supplied
rows.
• z/OS Unicode Services [DB2Z-3-084]
You can either load a new conversion image that contains the conversion definitions or add a single
conversion definition to the existing image. For instructions on how to load or alter conversion images,
see “Setting up z/OS Unicode Services for Db2 for z/OS”.
Related concepts
How Db2 performs character conversions
When character conversions are needed, Db2 for z/OS performs these conversions automatically based
on the CCSIDs of the source and target data. When you set up Db2, you need to identify valid conversion
definitions for source and target CCSIDs to Db2. Some of these definitions are predefined for you.
SYSIBM.SYSSTRINGS catalog table
The Db2 catalog table SYSIBM.SYSSTRINGS contains information about valid character conversion
definitions. Each row of SYSSTRINGS contains information about the conversion of character strings from
one CCSID to another CCSID. Db2 uses the conversion tables that are identified by these rows.
Checking defined character conversions
Character conversion definitions identify valid conversions for source and target CCSIDs for Db2. Many of
these definitions are predefined. If you are not sure if a particular character conversion that you need is
defined to Db2, check your character conversion definitions.
Procedure
To check defined character conversions:
1. Query the Db2 catalog table SYSIBM.SYSSTRINGS. [DB2Z-3-085]
Each row in the catalog table describes a conversion from one CCSID to another. IBM supplies some of
the rows. You can also add your own rows.
GUPIFor example, you can use the following query to view the defined conversions for CCSID 500:
SELECT INCCSID, OUTCCSID, TRANSTYPE, HEX(ERRORBYTE) AS ERRORBYTE,
       HEX(SUBBYTE) AS SUBBYTE, TRANSPROC, IBMREQD, HEX(TRANSTAB) AS TRANSTAB
       FROM SYSIBM.SYSSTRINGS WHERE CCSID=500
GUPI
2. Check the conversion image for z/OS Unicode Services by using the DISPLAY UNI command. [DB2Z-3-086]
This image contains character conversion definitions. If a definition for a particular source CCSID and
target CCSID already exists in SYSIBM.SYSSTRINGS, Db2 uses that definition instead. The exception [DB2Z-3-087]

is for Unicode CCSIDs. If the source or target CCSID is 1200 or 1208, Db2 uses the definition in the
conversion image for z/OS Unicode Services
For an example of the DISPLAY UNI output, see “Unicode CCSIDs”
Related concepts
How Db2 performs character conversions
When character conversions are needed, Db2 for z/OS performs these conversions automatically based
on the CCSIDs of the source and target data. When you set up Db2, you need to identify valid conversion
definitions for source and target CCSIDs to Db2. Some of these definitions are predefined for you.
SYSIBM.SYSSTRINGS catalog table
The Db2 catalog table SYSIBM.SYSSTRINGS contains information about valid character conversion
definitions. Each row of SYSSTRINGS contains information about the conversion of character strings from
one CCSID to another CCSID. Db2 uses the conversion tables that are identified by these rows.
Related information
Displaying Unicode services (UNI) (z/OS MVS System Commands) [DB2Z-3-088]
