## Page 1

Db2 12 for z/OS
Internationalization Guide
Last updated: 2026-03-26
IBM
SC19-8850-02

## Page 2

 
Notes
Before using this information and the product it supports, be sure to read the general information under
"Notices" at the end of this information.
Subsequent editions of this PDF will not be delivered in IBM Publications Center. Always download the
latest edition from IBM Documentation.
2026-03-26 edition [DB2Z-FM-001]
This edition applies to Db2® 12 for z/OS® (product number 5650-DB2), Db2 12 for z/OS Value Unit Edition (product
number 5770-AF3), and to any subsequent releases until otherwise indicated in new editions. Make sure you are using
the correct edition for the level of the product.
Specific changes are indicated by a vertical bar to the left of a change. A vertical bar to the left of a figure caption
indicates that the figure has changed. Editorial changes that have no technical significance are not noted.
© Copyright International Business Machines Corporation 2003, 2026.
US Government Users Restricted Rights – Use, duplication or disclosure restricted by GSA ADP Schedule Contract with
IBM Corp.

## Page 3

Contents
About this information......................................................................................... vii
Who should read this information............................................................................................................. viii
Db2 Utilities Suite for z/OS........................................................................................................................ viii
Terminology and citations......................................................................................................................... viii
Accessibility features for Db2 for z/OS....................................................................................................... ix
How to send comments............................................................................................................................... x
How to read syntax diagrams.......................................................................................................................x
Chapter 1. International data and character conversion in Db2 for z/OS.................. 1
Character conversion terminology.............................................................................................................. 1
Code pages and CCSIDs...............................................................................................................................5
Encoding schemes....................................................................................................................................... 7
ASCII.......................................................................................................................................................7
EBCDIC................................................................................................................................................... 8
Unicode.................................................................................................................................................10
Endianness.................................................................................................................................................14
Situations in which character conversion occurs......................................................................................15
Possible consequences of character conversion......................................................................................16
Types of character conversion...................................................................................................................17
Expanding conversion.......................................................................................................................... 17
Contracting conversion........................................................................................................................ 19
Round-trip conversion..........................................................................................................................20
Enforced subset conversion.................................................................................................................20
Chapter 2. How Db2 for z/OS uses Unicode...........................................................23
Retrieving data from the Db2 catalog....................................................................................................... 24
Specifying that IFCID output should be in Unicode..................................................................................24
Chapter 3. Setting up Db2 to ensure that it interprets characters correctly............ 27
How Db2 performs character conversions................................................................................................27
SYSIBM.SYSSTRINGS catalog table.................................................................................................... 28
Finding the CCSID values of your data sources........................................................................................ 29
Specifying CCSIDs in Db2.......................................................................................................................... 32
Specifying subsystem CCSIDs............................................................................................................. 32
Specifying object CCSIDs.....................................................................................................................37
Setting up z/OS Unicode Services for Db2 for z/OS.................................................................................. 38
Conversion image................................................................................................................................. 38
Basic character conversions for Db2 in the z/OS conversion image...................................................39
Character conversions for Chinese, Japanese, and Korean character sets in the z/OS
conversion image............................................................................................................................ 41
Defining additional character conversions................................................................................................43
Checking defined character conversions.................................................................................................. 43
Chapter 4. Storing Unicode data...........................................................................45
Deciding whether to store data as UTF-8 or UTF-16................................................................................45
Creating a Unicode table........................................................................................................................... 46
Tips for handling any extra storage that Unicode data might require................................................ 49
Estimating the column size for Unicode data...................................................................................... 49
Inserting data into a Unicode table........................................................................................................... 50
Inserting Unicode data into a non-Unicode table.....................................................................................51
  iii

## Page 4

Converting existing Db2 data to Unicode..................................................................................................52
Effects on access paths when converting data to Unicode ..................................................................... 54
Chapter 5. Application programming with Unicode data and multiple CCSIDs........57
Application encoding scheme................................................................................................................... 58
Specifying a CCSID for your application....................................................................................................59
Details of CCSID options for application programs.............................................................................61
Examples of specifying CCSIDs for application data.......................................................................... 64
Specifying CCSIDs for COBOL applications when using the Db2 coprocessor.................................. 66
Specifying CCSIDs for PL/I applications when using the Db2 coprocessor....................................... 69
Specifying CCSIDs for C/C++ applications when using the Db2 coprocessor....................................72
Determining the CCSID of Db2 data..........................................................................................................73
Determining the CCSID of a string value in an SQL statement................................................................. 74
Objects with different CCSIDs in the same SQL statement......................................................................75
Differences between Unicode and EBCDIC sorting sequences............................................................... 78
Specifying how Db2 calculates the length of a string...............................................................................80
Specifying the sorting sequence for a language....................................................................................... 82
Performing culturally correct case conversions........................................................................................85
Locale....................................................................................................................................................86
Generating escaped Unicode data............................................................................................................ 88
Normalization of Unicode strings.............................................................................................................. 91
How Db2 handles Unicode supplementary characters............................................................................ 91
Processing Unicode data in COBOL applications......................................................................................92
Processing Unicode data in PL/I applications...........................................................................................93
Processing Unicode data in C/C++ applications....................................................................................... 94
Java applications and Unicode data..........................................................................................................94
Green screen applications and Unicode data........................................................................................... 95
Variant characters......................................................................................................................................95
DRDA character type parameters in Unicode........................................................................................... 96
Chapter 6. Debugging CCSID and Unicode problems............................................. 99
Potential problems when inserting non-Unicode data into a Unicode table......................................... 100
Appendix A. Db2 utilities and Unicode support .................................................. 101
Appendix B. EXPLAIN Unicode support.............................................................. 103
Appendix C. Db2 ODBC Unicode support.............................................................105
Appendix D. IBM Db2 Tools for z/OS Unicode support......................................... 107
Appendix E. The International Components for Unicode......................................109
Appendix F. SYSSTRINGS catalog table.............................................................. 111
Information resources for Db2 for z/OS and related products..............................115
Notices..............................................................................................................117
Programming interface information........................................................................................................ 118
Trademarks.............................................................................................................................................. 119
Terms and conditions for product documentation................................................................................. 119
Privacy policy considerations.................................................................................................................. 119
Glossary............................................................................................................ 121
iv  

## Page 5

Index................................................................................................................ 123
  v

## Page 6

vi  

## Page 7

About this information
This information describes how to handle international data when working in a Db2 12 for z/OS (Db2 for
z/OS) environment.
This information provides basic guidance about storing and manipulating Unicode data or data from
different code pages in a Db2 for z/OS environment. Topics include detailed information about the
following tasks:
1. How to set up your subsystem so that Db2 correctly interprets data in any encoding scheme [DB2Z-FM-002]
2. How to store and manipulate Unicode data [DB2Z-FM-003]
3. How to store and manipulate data in multiple encoding schemes [DB2Z-FM-004]
4. How to write applications that correctly interpret data according to the encoding scheme [DB2Z-FM-005]
Throughout this content, "Db2" means "Db2 12 for z/OS." References to other Db2 products use complete
names or more-specific abbreviations.
Important: To find the most up to date content for Db2 12 for z/OS, always use IBM® Documentation
or download the latest PDF file from PDF format manuals for Db2 12 for z/OS (Db2 for z/OS in IBM
Documentation).
This Db2 12 for z/OS product documentation generally assumes that the highest available function level is
activated and that your applications are running with the highest available application compatibility level,
with the following exceptions:
• The following documentation sections describe the Db2 12 migration process and how to activate new [DB2Z-FM-006]
capabilities in function levels:
– Migrating to Db2 12 (Db2 Installation and Migration)
– What's new in Db2 12 (Db2 for z/OS What's New?)
– Adopting new capabilities in Db2 12 continuous delivery (Db2 for z/OS What's New?)
• FL 501  Labels like this one usually mark new content or changes for Db2 function levels. The lable is [DB2Z-FM-007]
also a link to the description of the function level that introduces the changes. For more information, see
How Db2 function levels are documented (Db2 for z/OS What's New?).
Continuous delivery in Db2 12
The availability of most new-function application capabilities in Db2 12 depends on the type of
enhancement, the activated function level, and the application compatibility levels of each application.
For a list of all available function levels that are currently available in Db2 12, see Db2 12 function levels
(Db2 for z/OS What's New?).
Many new-function enhancements take effect at any function level when you apply a PTF in each Db2
subsystem or data sharing member. For more information, see New-function APARs for Db2 12 (Db2 for
z/OS What's New?).
Virtual storage enhancements
Virtual storage enhancements become available at the activation of the function level that introduces
them or higher. Activation of function level 100 introduces all virtual storage enhancements in
the initial Db2 12 release. That is, activation of function level 500 introduces no virtual storage
enhancements.
Subsystem parameters
New subsystem parameter settings are in effect only when the function level that introduced them or
a higher function level is activated. Many subsystem parameter changes in the initial Db2 12 release
take effect in function level 500. For more information about subsystem parameter changes in Db2
12, see Subsystem parameter changes in Db2 12 (Db2 for z/OS What's New?).
© Copyright IBM Corp. 2003, 2026 vii [DB2Z-FM-008]

## Page 8

Optimization enhancements
Optimization enhancements become available after the activation of the function level that introduces
them or higher, and full prepare of the SQL statements. When a full prepare occurs depends on the
statement type:
• For static SQL statements, after bind or rebind of the package [DB2Z-FM-009]
• For non-stabilized dynamic SQL statements, immediately, unless the statement is in the dynamic [DB2Z-FM-010]
statement cache
• For stabilized dynamic SQL statements, after invalidation, free, or changed application compatibility [DB2Z-FM-011]
level
Activation of function level 100 introduces all optimization enhancements in the initial Db2 12
release. That is, function level 500 introduces no optimization enhancements.
SQL capabilities
New SQL capabilities become available after the activation of the function level that introduces them
or higher, for applications that run at the equivalent application compatibility level or higher. New SQL
capabilities in the initial Db2 12 release become available in function level 500 for applications that
run at the equivalent application compatibility level or higher. You can continue to run SQL statements
compatibly with lower function levels, or previous Db2 releases, including Db2 11 and DB2® 10. For
details, see Application compatibility (APPLCOMPAT) levels in Db2 12 (Db2 Application programming
and SQL)
Who should read this information
This information is primarily intended for people who are responsible for using character conversion in a
Db2 for z/OS environment. It assumes that the user is familiar with the following concepts:
• The basic concepts and facilities of Db2 for z/OS environment [DB2Z-FM-012]
• The basic concepts of Structured Query Language (SQL) [DB2Z-FM-013]
Db2 Utilities Suite for z/OS
Important: Db2 Utilities Suite for z/OS is available as an optional product. You must separately order
and purchase a license to such utilities, and discussion of those utility functions in this publication is not
intended to otherwise imply that you have a license to them.
Db2 12 utilities can use the DFSORT program regardless of whether you purchased a license for DFSORT
on your system. For more information about DFSORT, see https:/ /www.ibm.com/support/pages/dfsort.
Db2 utilities can use IBM Db2 Sort for z/OS as an alternative to DFSORT for utility SORT and MERGE
functions. Use of Db2 Sort for z/OS requires the purchase of a Db2 Sort for z/OS license. For more
information about Db2 Sort for z/OS, see Db2 Sort for z/OS documentation.
Related concepts
Db2 utilities packaging (Db2 Utilities)
Terminology and citations
When referring to a Db2 product other than Db2 for z/OS, this information uses the product's full name to
avoid ambiguity.
The following terms are used as indicated:
Db2
Represents either the Db2 licensed program or a particular Db2 subsystem.
IBM rebranded DB2 to Db2, and Db2 for z/OS is the new name of the offering that was previously
known as "DB2 for z/OS". As a result, you might sometimes still see references to the original names,
such as "DB2 for z/OS" and "DB2", in different IBM web pages and documents. If the PID, Entitlement
viii  About this information [DB2Z-FM-014]

## Page 9

Entity, version, modification, and release information match, assume that they refer to the same
product.
IBM OMEGAMON® AI for Db2
Refers to any of the following products:
• IBM IBM OMEGAMON AI for Db2 [DB2Z-FM-015]
• IBM IBM OMEGAMON AI for Db2 [DB2Z-FM-016]
• IBM Db2 Performance Expert for Multiplatforms and Workgroups [DB2Z-FM-017]
• IBM Db2 Buffer Pool Analyzer for z/OS [DB2Z-FM-018]
C, C++, and C language
Represent the C or C++ programming language.
CICS®
Represents CICS Transaction Server for z/OS.
IMS
Represents the IMS Database Manager or IMS Transaction Manager.
MVS
Represents the MVS element of the z/OS operating system, which is equivalent to the Base Control
Program (BCP) component of the z/OS operating system.
RACF®
Represents the functions that are provided by the RACF component of the z/OS Security Server.
Accessibility features for Db2 for z/OS
Accessibility features help a user who has a physical disability, such as restricted mobility or limited
vision, to use information technology products successfully.
Accessibility features
The following list includes the major accessibility features in z/OS products, including Db2 for z/OS. These
features support:
• Keyboard-only operation. [DB2Z-FM-019]
• Interfaces that are commonly used by screen readers and screen magnifiers. [DB2Z-FM-020]
• Customization of display attributes such as color, contrast, and font size [DB2Z-FM-021]
Tip: IBM Documentation (which includes information for Db2 for z/OS) and its related publications are
accessibility-enabled for the IBM Home Page Reader. You can operate all features using the keyboard
instead of the mouse.
Keyboard navigation
For information about navigating the Db2 for z/OS ISPF panels using TSO/E or ISPF, refer to the z/OS
TSO/E Primer, the z/OS TSO/E User's Guide, and the z/OS ISPF User's Guide. These guides describe how
to navigate each interface, including the use of keyboard shortcuts or function keys (PF keys). Each guide
includes the default settings for the PF keys and explains how to modify their functions.
Related accessibility information
Online documentation for Db2 for z/OS is available in IBM Documentation. For more information, see IBM
Db2 for z/OS documentation.
IBM and accessibility
For more information about the commitment that IBM has to accessibility, see the IBM Accessibility Center
at http:/ /www.ibm.com/able.
About this information  ix [DB2Z-FM-022]

## Page 10

How to send your comments about Db2 for z/OS documentation
Your feedback helps IBM to provide quality documentation.
Send any comments about Db2 for z/OS and related product documentation by email to
db2zinfo@us.ibm.com.
To help us respond to your comment, include the following information in your email:
• The product name and version [DB2Z-FM-023]
• The address (URL) of the page, for comments about online documentation [DB2Z-FM-024]
• The book name and publication date, for comments about PDF manuals [DB2Z-FM-025]
• The topic or section title [DB2Z-FM-026]
• The specific text that you are commenting about and your comment [DB2Z-FM-027]
Related concepts
About Db2 12 for z/OS product documentation (Db2 for z/OS in IBM Documentation)
Related reference
PDF format manuals for Db2 12 for z/OS (Db2 for z/OS in IBM Documentation)
How to read syntax diagrams
Certain conventions apply to the syntax diagrams that are used in IBM documentation.
Apply the following rules when reading the syntax diagrams that are used in Db2 for z/OS documentation:
• Read the syntax diagrams from left to right, from top to bottom, following the path of the line. [DB2Z-FM-028]
The ►►─── symbol indicates the beginning of a statement.
The ───► symbol indicates that the statement syntax is continued on the next line.
The ►─── symbol indicates that a statement is continued from the previous line.
The ───►◄ symbol indicates the end of a statement.
• Required items appear on the horizontal line (the main path). [DB2Z-FM-029]
required_item
• Optional items appear below the main path. [DB2Z-FM-030]
required_item
optional_item
If an optional item appears above the main path, that item has no effect on the execution of the
statement and is used only for readability.
required_item
optional_item
• If you can choose from two or more items, they appear vertically, in a stack. [DB2Z-FM-031]
If you must choose one of the items, one item of the stack appears on the main path.
required_item required_choice1
required_choice2
If choosing one of the items is optional, the entire stack appears below the main path.
x  About this information [DB2Z-FM-032]

## Page 11

required_item
optional_choice1
optional_choice2
If one of the items is the default, it appears above the main path and the remaining choices are shown
below.
required_item
default_choice
optional_choice
optional_choice
• An arrow returning to the left, above the main line, indicates an item that can be repeated. [DB2Z-FM-033]
required_item repeatable_item
If the repeat arrow contains a comma, you must separate repeated items with a comma.
required_item
,
repeatable_item
A repeat arrow above a stack indicates that you can repeat the items in the stack.
• Sometimes a diagram must be split into fragments. The syntax fragment is shown separately from the [DB2Z-FM-034]
main syntax diagram, but the contents of the fragment should be read as if they are on the main path of
the diagram.
required_item fragment-name
fragment-name
required_item
optional_name
• For some references in syntax diagrams, you must follow any rules described in the description for that [DB2Z-FM-035]
diagram, and also rules that are described in other syntax diagrams. For example:
– For expression, you must also follow the rules described in Expressions (Db2 SQL).
– For references to fullselect, you must also follow the rules described in fullselect (Db2 SQL).
– For references to search-condition, you must also follow the rules described in Search conditions
(Db2 SQL).
• With the exception of XPath keywords, keywords appear in uppercase (for example, FROM). Keywords [DB2Z-FM-036]
must be spelled exactly as shown.
• XPath keywords are defined as lowercase names, and must be spelled exactly as shown. [DB2Z-FM-037]
• Variables appear in all lowercase letters (for example, column-name). They represent user-supplied [DB2Z-FM-038]
names or values.
• If punctuation marks, parentheses, arithmetic operators, or other such symbols are shown, you must [DB2Z-FM-039]
enter them as part of the syntax.
Related concepts
About commands in Db2 for z/OS (Db2 Commands)
Db2 online utilities (Db2 Utilities)
Db2 stand-alone utilities (Db2 Utilities)
About this information  xi [DB2Z-FM-040]

## Page 12

xii  Db2 12 for z/OS: Internationalization Guide (Last updated: 2026-03-26) [DB2Z-FM-041]

## Page 13
