Chapter 1. International data and character
conversion in Db2 for z/OS
In computers, all characters are encoded according to the rules of a particular encoding scheme and
code page. If your database and applications handle data from multiple code pages, that data might be
converted at certain times from one code page to another. This conversion process is called character
conversion.
This situation of handling data from multiple code pages is likely if your database and applications contain
international data or data from multiple character sets, such as Latin-1 and Katakana. In this situation,
character conversions are likely to occur.
Important: For best results, try to avoid character conversions whenever possible, because conversions
can potentially slow performance and sometimes cause data loss. The best way to avoid conversions is to
use the same CCSID for all of your data. For more information, see “Possible consequences of character
conversion”.
The problem with character conversions is that they can degrade performance and potentially cause data
loss. Therefore, you should avoid these conversions if possible. One way to avoid these conversions is to
have all of your data in one code page. If you use multiple character sets, you might considering using
the Unicode code page. This code page includes all characters. If you use Unicode for all of your data,
conversions can be avoided. However, converting all of your data to Unicode is not a simple process.
This information discusses basic principles about character conversion and general recommendations
that you can apply to your environment for optimal performance and storage.
Related concepts
Character conversion (Introduction to Db2 for z/OS)
Encoding scheme and CCSID rules for strings (Introduction to Db2 for z/OS)
Character conversion terminology
To understand the concept of character conversion, you should know the meaning of some basic related
terms.
The following terms are related to character conversion:
application encoding scheme
The CCSID that your application uses to interpret data in host variables. For Db2 for z/OS applications,
typically the application encoding scheme is the value of the ENCODING bind option. (By default the
value of the ENCODING bind option is the subsystem default application encoding scheme, which
is the APPENSCH DECP value.) However, you can also set the CCSID of application data by using
the DECLARE VARIABLE statement with the CCSID option or the CURRENT APPLICATION ENCODING
SCHEME special register.
If you are using the Db2 coprocessor, you can use various language compiler options to override the
Db2 application encoding scheme for an application.
For more information about application encoding schemes, see “Specifying a CCSID for your
application”.
ASCII
Acronym for American Standard Code for Information Interchange, an encoding scheme that is used
to represent characters. In this information, the term ASCII is used to refer to IBM-PC data or ISO
8-bit data.
For more information about ASCII, see “ASCII”. For more information about encoding
schemes in general, see “Encoding schemes”. [DB2Z-1-001]




big endian
A data format in which the most significant byte is stored first, at the memory location with the lowest
address.
For more information about big endian, see “Endianness”.
character conversion
The process of converting characters from one CCSID to another.
For more information about how Db2 performs character conversions, see “How Db2 performs
character conversions”.
character data representation architecture (CDRA)
An IBM architecture that aims to achieve consistent representation, processing, and interchange of
graphic character data in data processing environments. CDRA defines a set of identifiers, services,
supporting resources, and conventions. The identifiers that CDRA defines are CCSIDs.
For more information about CDRA, see “Code pages and CCSIDs”.
character repertoire
A set of characters.
character set
A defined set of characters, in which a character is the smallest component of written language that
has semantic value.
code page
A specification of code points from a defined encoding scheme for each character in a set or in a
collection of character sets. Within a code page, a code point can have only one specific meaning.
Code pages are defined by the IBM Globalization Center of Competency.
For more information about code pages, see “Code pages and CCSIDs”.
code point
A unique bit pattern that represents a character.
For more information about code points, see “Code pages and CCSIDs”.
coded character set
A set of unambiguous rules that establishes a character set and the one-to-one relationships between
the characters of the set and their coded representations. A coded character set is the assignment of
each character in a character set to a unique numeric code value.
coded character set identifier (CCSID)
A 16-bit number that identifies a specific set of encoding scheme identifiers, character set identifiers,
code page identifiers, and additional coding-related information. A CCSID is a number that identifies
an implementation of a code page at a particular point in time. A CCSID is an attribute of strings, just
as length is an attribute of strings. All values of the same string column have the same CCSID.
For more information about CCSIDs, see “Code pages and CCSIDs”.
coded character set identifier (CCSID) set
The single byte CCSID value (SBCS), mixed CCSID value, and double byte CCSID value (DBCS) that are
associated with a particular encoding scheme.
For more information about CCSID sets, see “Specifying subsystem CCSIDs”.
collation name
A string value that specifies how Db2 is to sort data. The collation name specifies attributes such as
the language of the data, whether case should be considered, and how punctuation characters should
be treated.
For more information about collation names, see “Specifying the sorting sequence for a language” on
page 82.
contracting conversion
A character conversion in which the length of the converted string is smaller than that of the source
string.




For more information about contracting conversions, see “Contracting conversion”.
conversion image
A data set that contains the information that z/OS Unicode Services needs to perform character and
case conversions.
For more information about conversion images, see “Conversion image”.
EBCDIC
Acronym for Extended Binary-Coded Decimal Interchange Code, a group of coded character sets that
consists of 8-bit coded characters. EBCDIC coded character sets assign characters to code points.
Each code point consists of 8 bits.
For more information about EBCDIC, see “EBCDIC”. For more information about
encoding schemes in general, see “Encoding schemes”.
encoding scheme
A set of rules that is used to represent character data. All string data that is stored in a table
must use the same encoding scheme. All tables within a table space must use the same encoding
scheme, except for global temporary tables, declared temporary tables, and work file table spaces. An
encoding scheme only describes the type of encoding; it does not specify code points or a code page.
Examples of encoding schemes include ASCII, EBCDIC, and Unicode.
For more information about encoding schemes, see “Encoding schemes”.
endianness
A data attribute that describes byte order.
For more information about endianness, see “Endianness”.
enforced subset conversion
A character conversion in which characters that do not have a code point in the target CCSID are
converted to a single substitution character.
For more information about enforced subset conversions, see “Enforced subset conversion” on page
20.
escaped data
One or more characters that cannot be represented in the target CCSID and that have been identified
as such by some extra syntax.
For more information about escaped data, see “Generating escaped Unicode data”.
expanding conversion
A character conversion in which the length of the converted string is greater than that of the source
string.
For more information about expanding conversions, see “Expanding conversion”.
International Components for Unicode (ICU)
A set of C/C++ and Java™ libraries for Unicode support and software internationalization.
For more information about ICU, see Appendix E, “The International Components for Unicode,” on
page 109.
little endian
A data format in which the least significant byte is stored first, at the memory location with the lowest
address.
For more information about little endian, see “Endianness”.
locale
An attribute that defines the user's cultural environment.
For more information about locales, see “Locale”. [DB2Z-1-002]


Chapter 1. International data and character conversion in Db2 for z/OS  3 [DB2Z-1-003]



lossless conversion
A character conversion in which all characters in the source CCSID exist in the target CCSID, and thus,
no character is lost.
For more information about lossless conversions, see “Possible consequences of character
conversion”.
normalization
A process that produces a unique code point sequence for all sequences that are equivalent, either
canonically or compatibly.
For more information about normalization, see “Normalization of Unicode strings”.
round-trip conversion
A character conversion that ensures the integrity of all character data from the source CCSID to the
target CCSID and back to the source. Even if the target CCSID does not support a given character, the
character regains its original hexadecimal value after the conversion back to the original CCSID.
For more information about round-trip conversions, see “Round-trip conversion”.
substitution character
A unique character that is substituted during character conversion for any characters in the source
CCSID that do not have a match in the target CCSID.
For more information about substitution characters, see “Enforced subset conversion”.
supplementary characters
Characters that have a code point between U+10000 and U+10FFFF.
For more information about supplementary characters, see “How Db2 handles Unicode
supplementary characters”.
Unicode
An international character code for information processing that is designed to encode all characters
that are used for written communication in a simple and consistent manner. The Unicode character
encoding was established to provide enough code points for all the scripts and technical symbols in
common usage around the world, plus some ancient scripts.
For more information about Unicode, see “Unicode”. For more information about
encoding schemes in general, see “Encoding schemes”.
Unicode Consortium
A non-profit organization that develops and maintains international standards, including the Unicode
Standard.
For more information about the Unicode Consortium, see Unicode Consortium.
Unicode transformation formats (UTFs)
Forms of Unicode encoding that were devised by the Unicode Consortium to ensure that systems
can communicate efficiently. UTF-8, UTF-16, and UTF-32 were each designed for different processing
objectives.
For more information about the UTFs, see “UTFs”.
z/OS Unicode Services
A set of functions that are provided by z/OS. Among the services are case conversion service and
character conversion service.
For more information about the z/OS Unicode Services, see “Setting up z/OS Unicode Services for Db2
for z/OS”.




Code pages and CCSIDs
Because computers store only numbers, they store letters and other characters by assigning a number
to them. Which number is mapped to each character depends on the CCSID and code page that is
associated with that character.
A code page is a mapping of hexadecimal numbers to particular characters. For example, the following
table shows code page 37. [DB2Z-1-004]
### Table 1. Code page 37 with CCSID 37

| 2nd↓ \ 1st→ | 4- | 5- | 6- | 7- | 8- | 9- | A- | B- | C- | D- | E- | F- |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **-0** | (sp) | & | - | ø | Ø | º | µ | ^ | { | } | \ | 0 [DB2Z-1-005]|
| **-1** | (rsp) | é | / | É | a | j | ~ | £ | A | J | ÷ | 1 [DB2Z-1-006]|
| **-2** | â | ê | Â | Ê | b | k | s | ¥ | B | K | S | 2 [DB2Z-1-007]|
| **-3** | ä | ë | Ä | Ë | c | l | t | • | C | L | T | 3 [DB2Z-1-008]|
| **-4** | à | è | À | È | d | m | u | © | D | M | U | 4 [DB2Z-1-009]|
| **-5** | á | í | Á | Í | e | n | v | § | E | N | V | 5 [DB2Z-1-010]|
| **-6** | ã | î | Ã | Î | f | o | w | ¶ | F | O | W | 6 [DB2Z-1-011]|
| **-7** | å | ï | Å | Ï | g | p | x | ¼ | G | P | X | 7 [DB2Z-1-012]|
| **-8** | ç | ì | Ç | Ì | h | q | y | ½ | H | Q | Y | 8 [DB2Z-1-013]|
| **-9** | ñ | ß | Ñ | ` | i | r | z | ¾ | I | R | Z | 9 [DB2Z-1-014]|
| **-A** | ¢ | ! | ¦ | : | ≪ | ª | ¡ | [ (SHY) | ¹ | ² | ³ [DB2Z-1-015]| |
| **-B** | . | $ | , | # | ≫ | º | ¿ | ] | ô | û | Ô | Û [DB2Z-1-016]|
| **-C** | < | * | % | @ | ð | æ | Ð | ‾ | ö | ü | Ö | Ü [DB2Z-1-017]|
| **-D** | ( | ) | _ | ' | ý | ¸ | Ý | ¨ | ò | ú | Ò | Ù [DB2Z-1-018]|
| **-E** | + | ; | > | = | þ | Æ | Þ | ´ | ó | ú | Ó | Ú [DB2Z-1-019]|
| **-F** | \| | ¬ | ? | “ | ± | ¤ | ® | × | õ | ÿ | Õ | (EO) [DB2Z-1-020]|
Within a code page, each hexadecimal number representation for a character is called a code point.
When looking at a code page, you can find the hexadecimal code point value for a particular character by
concatenating the column header with the row header. For example, find the character 'A' in the preceding
code page 37. The character 'A' is in column C and row 1. Therefore, the corresponding code point for the
character 'A' is X'C1'. As another example, find the character 'a' in this same code page. The character 'a'
is in column 8 and row 1. Therefore, the corresponding code point is X'81'.
A coded character set identifier (CCSID) is a number that identifies an implementation of a code page at a
particular point in time. For example, the preceding code page 37, which is the US-English code page, has
a CCSID of 37.
CCSIDs are defined by the IBM character data representation architecture (CDRA). CDRA is an
architecture that aims to achieve consistent representation, processing, and interchange of graphic
character data in data processing environments. To achieve this consistency, CDRA defines a set of
services, supporting resources, conventions, and identifiers, one of which is a CCSID. IBM maintains a
repository list of all CCSIDs that are defined by CDRA. [DB2Z-1-021]


Chapter 1. International data and character conversion in Db2 for z/OS  21 [DB2Z-1-022]









Chapter 1. International data and character conversion in Db2 for z/OS  5 [DB2Z-1-023]



Db2 for z/OS uses CCSIDs. However, Db2 for Linux, UNIX, and Windows uses code pages. The difference
between code pages and CCSIDs is the stability. Code pages might change. However, because CCSIDs
capture a code page at a particular point in time, the code page that it references does not change.
For example, consider code page 37. At some point, this code page was changed so that code point X'9F'
no longer mapped to the international currency symbol (¤). Instead, this code point was mapped to the
euro symbol (€). CCSID 37 refers to the original code page 37. The altered code page has CCSID 1140.
CCSID 1140 and CCSID 37 differ by only that one character at code point X'9F'. The following table shows
CCSID 1140.
### Table 2. Code page 37 with CCSID 1140

| 2nd↓ \ 1st→ | 4- | 5- | 6- | 7- | 8- | 9- | A- | B- | C- | D- | E- | F- |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **-0** | (sp) | & | - | ø | Ø | º | µ | ^ | { | } | \ | 0 [DB2Z-1-024]|
| **-1** | (rsp) | é | / | É | a | j | ~ | £ | A | J | ÷ | 1 [DB2Z-1-025]|
| **-2** | â | ê | Â | Ê | b | k | s | ¥ | B | K | S | 2 [DB2Z-1-026]|
| **-3** | ä | ë | Ä | Ë | c | l | t | • | C | L | T | 3 [DB2Z-1-027]|
| **-4** | à | è | À | È | d | m | u | © | D | M | U | 4 [DB2Z-1-028]|
| **-5** | á | í | Á | Í | e | n | v | § | E | N | V | 5 [DB2Z-1-029]|
| **-6** | ã | î | Ã | Î | f | o | w | ¶ | F | O | W | 6 [DB2Z-1-030]|
| **-7** | å | ï | Å | Ï | g | p | x | ¼ | G | P | X | 7 [DB2Z-1-031]|
| **-8** | ç | ì | Ç | Ì | h | q | y | ½ | H | Q | Y | 8 [DB2Z-1-032]|
| **-9** | ñ | ß | Ñ | ` | i | r | z | ¾ | I | R | Z | 9 [DB2Z-1-033]|
| **-A** | ¢ | ! | ¦ | : | ≪ | ª | ¡ | [ (SHY) | ¹ | ² | ³ [DB2Z-1-034]| |
| **-B** | . | $ | , | # | ≫ | º | ¿ | ] | ô | û | Ô | Û [DB2Z-1-035]|
| **-C** | < | * | % | @ | ð | æ | Ð | ‾ | ö | ü | Ö | Ü [DB2Z-1-036]|
| **-D** | ( | ) | _ | ' | ý | ¸ | Ý | ¨ | ò | ú | Ò | Ù [DB2Z-1-037]|
| **-E** | + | ; | > | = | þ | Æ | Þ | ´ | ó | ú | Ó | Ú [DB2Z-1-038]|
| **-F** | \| | ¬ | ? | “ | ± | € | ® | × | õ | ÿ | Õ | (EO) [DB2Z-1-039]|
The exception to this idea of fixed CCSIDs is the CCSID set that Db2 for z/OS uses for Unicode code pages.
For Unicode data, Db2 for z/OS uses CCSIDs that have the ability to grow as the Unicode standard grows.
For more information about those CCSIDs, see “Unicode CCSIDs”.
In Db2 for z/OS, all character data is associated with a CCSID. If the data does not have one, Db2 uses the
subsystem defaults. You specify these subsystem default CCSID values when you install Db2. Character
conversion is described in terms of CCSIDs of the source and target.
Related concepts
Euro symbol support (Db2 Installation and Migration)
Related tasks
Specifying CCSIDs in Db2 [DB2Z-1-040]




You must communicate to Db2 the correct CCSIDs to use for your data to ensure that Db2 correctly
interprets your data. You can specify default subsystem CCSIDs. You can also specify CCSIDs for
individual applications and Db2 objects.
Related information
Coded character sets sorted by CCSID
Encoding schemes
An encoding scheme standardizes the encoding of character sets by defining a set of rules for representing
character data. Each encoding scheme consists of a number of code pages that adhere to its rules. For
example, code pages 37, 500, and 1047 are all part of the EBCDIC encoding scheme.
The major encoding schemes are EBCDIC, ASCII, and Unicode. The EBCDIC encoding scheme is typically
used on IBM Z (z/OS) and iSeries (AS/400). The ASCII encoding scheme is used on Intel-based systems,
such as Windows, UNIX based systems, such as AIX®, and the Linux operating system. The Unicode
encoding scheme is supported by many operating systems.
Related concepts
Character conversion (Introduction to Db2 for z/OS)
Encoding scheme and CCSID rules for strings (Introduction to Db2 for z/OS)
ASCII
The American Standard Code for Information Interchange (ASCII) is an encoding scheme. ASCII is
typically used on Intel-based systems, such as Windows, and UNIX-based systems, such as Linux.
ASCII was developed by a committee of the American Standards Association. The first ASCII standard
was published in 1963.
Certain characters are the same on every ASCII code page. Those characters are called invariant
characters. Other characters might vary from one code page to the next. These characters are called
variant characters.
Some example ASCII CCSIDs are 367, 819, and 912.
The following table shows the code page for ASCII CCSID 367. [DB2Z-1-041]
### Table 3. CCSID 367

| 2nd↓ \ 1st→ | 0- | 1- | 2- | 3- | 4- | 5- | 6- | 7- |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **-0** | (sp) | | | 0 | @ | P | ` | p [DB2Z-1-042]|
| **-1** | | | ! | 1 | A | Q | a | q [DB2Z-1-043]|
| **-2** | | | " | 2 | B | R | b | r [DB2Z-1-044]|
| **-3** | | | # | 3 | C | S | c | s [DB2Z-1-045]|
| **-4** | | | $ | 4 | D | T | d | t [DB2Z-1-046]|
| **-5** | | | % | 5 | E | U | e | u [DB2Z-1-047]|
| **-6** | | | & | 6 | F | V | f | v [DB2Z-1-048]|
| **-7** | | | ' | 7 | G | W | g | w [DB2Z-1-049]|
| **-8** | | | ( | 8 | H | X | h | x [DB2Z-1-050]|
| **-9** | | | ) | 9 | I | Y | i | y [DB2Z-1-051]|
| **-A** | | | * | : | J | Z | j | z [DB2Z-1-052]|
| **-B** | | | + | ; | K | [ | k | { [DB2Z-1-053]|
| **-C** | | | , | < | L | \ | l | \ [DB2Z-1-054]| |
| **-D** | | | - | = | M | ] | m | } [DB2Z-1-055]|
| **-E** | | | . | > | N | ^ | n | ~ [DB2Z-1-056]|
| **-F** | | | / | ? | O | _ | o [DB2Z-1-057]| |
Related concepts
Variant characters
Variant characters are characters that correspond to different code points across a given set of code
pages. For example, the character # is variant. It corresponds to code point X'7B' in CCSIDs 37, 273, 500,
and 1047. However, this character corresponds to code point X'4A' in CCSID 277.
EBCDIC
Extended Binary Coded Decimal Interchange Code (EBCDIC) is an encoding scheme that is typically used
on IBM Z (z/OS) and iSeries (System i).
EBCDIC was developed by IBM in 1963.
Certain characters are the same on every EBCDIC code page. Those characters are called invariant
characters. Other characters might vary from one code page to the next. These characters are called
variant characters.
Some example EBCDIC CCSIDs are 37, 500, and 1047.
The following table shows the code page for EBCDIC CCSID 37. [DB2Z-1-058]
### Table 4. Code page 37 with CCSID 37

| 2nd↓ \ 1st→ | 4- | 5- | 6- | 7- | 8- | 9- | A- | B- | C- | D- | E- | F- |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **-0** | (sp) | & | - | ø | Ø | º | µ | ^ | { | } | \ | 0 [DB2Z-1-059]|
| **-1** | (rsp) | é | / | É | a | j | ~ | £ | A | J | ÷ | 1 [DB2Z-1-060]|
| **-2** | â | ê | Â | Ê | b | k | s | ¥ | B | K | S | 2 [DB2Z-1-061]|
| **-3** | ä | ë | Ä | Ë | c | l | t | • | C | L | T | 3 [DB2Z-1-062]|
| **-4** | à | è | À | È | d | m | u | © | D | M | U | 4 [DB2Z-1-063]|
| **-5** | á | í | Á | Í | e | n | v | § | E | N | V | 5 [DB2Z-1-064]|
| **-6** | ã | î | Ã | Î | f | o | w | ¶ | F | O | W | 6 [DB2Z-1-065]|
| **-7** | å | ï | Å | Ï | g | p | x | ¼ | G | P | X | 7 [DB2Z-1-066]|
| **-8** | ç | ì | Ç | Ì | h | q | y | ½ | H | Q | Y | 8 [DB2Z-1-067]|
| **-9** | ñ | ß | Ñ | ` | i | r | z | ¾ | I | R | Z | 9 [DB2Z-1-068]|
| **-A** | ¢ | ! | ¦ | : | ≪ | ª | ¡ | [ (SHY) | ¹ | ² | ³ [DB2Z-1-069]| |
| **-B** | . | $ | , | # | ≫ | º | ¿ | ] | ô | û | Ô | Û [DB2Z-1-070]|
| **-C** | < | * | % | @ | ð | æ | Ð | ‾ | ö | ü | Ö | Ü [DB2Z-1-071]|
| **-D** | ( | ) | _ | ' | ý | ¸ | Ý | ¨ | ò | ú | Ò | Ù [DB2Z-1-072]|
| **-E** | + | ; | > | = | þ | Æ | Þ | ´ | ó | ú | Ó | Ú [DB2Z-1-073]|
| **-F** | \| | ¬ | ? | “ | ± | ¤ | ® | × | õ | ÿ | Õ | (EO) [DB2Z-1-074]|
Related concepts
Variant characters
Variant characters are characters that correspond to different code points across a given set of code
pages. For example, the character # is variant. It corresponds to code point X'7B' in CCSIDs 37, 273, 500,
and 1047. However, this character corresponds to code point X'4A' in CCSID 277.
Code point differences between EBCDIC CCSIDs
Although many EBCDIC code pages are similar, code points for certain characters vary from code page to
code page. These characters are called variant characters and can potentially cause problems.
Characters A-Z, a-z, and 0-9 correspond to the same hexadecimal code points on most EBCDIC code
pages. Other characters, such as the left bracket ([) correspond to different code points depending on the
CCSID. Therefore, to ensure that Db2 interprets your data correctly, you should specify the correct CCSID,
especially when you use characters other than A-Z, a-z, and 0-9.
The following tables show some code point differences between several common EBCDIC CCSIDs.
The following table shows the difference in code points between EBCDIC CCSID 37 and EBCDIC CCSID
500.
### Table 5. Code point differences between EBCDIC CCSID 37 and EBCDIC CCSID 500

| Code point | Character CCSID 37 | Character CCSID 500 |
| :--- | :--- | :--- |
| X'4A' | ¢ (cent sign) | [ (left bracket) [DB2Z-1-075]|
| X'4F' | \| (vertical bar) | ! (exclamation point) [DB2Z-1-076]|
| X'5A' | ! (exclamation point) | ] (right bracket) [DB2Z-1-077]|
| X'5F' | ¬ (logical not) | ^ (circumflex accent) [DB2Z-1-078]|
| X'B0' | ^ (circumflex accent) | ¢ (cent sign) [DB2Z-1-079]|
| X'BA' | [ (left bracket) | ¬ (logical not) [DB2Z-1-080]|
| X'BB' | ] (right bracket) | \| (vertical bar) [DB2Z-1-081]|
The following table shows the difference in code points between EBCDIC CCSID 37 and EBCDIC CCSID
1047.
### Table 6. Code point differences between EBCDIC CCSID 37 and EBCDIC CCSID 1047

| Code point | Character CCSID 37 | Character CCSID 1047 |
| :--- | :--- | :--- |
| X'5F' | ¬ (logical not) | ^ (circumflex accent) [DB2Z-1-082]|
| X'AD' | Ý (uppercase Y with acute accent) | [ (left bracket) [DB2Z-1-083]|
| X'B0' | ^ (circumflex accent) | ¬ (logical not) [DB2Z-1-084]|
| X'BA' | [ (left bracket) | Ý (uppercase Y with acute accent) [DB2Z-1-085]|
| X'BB' | ] (right bracket) | ¨ (umlaut) [DB2Z-1-086]|
| X'BD' | ¨ (umlaut) | ] (right bracket) [DB2Z-1-087]|
The following table shows the difference in code points between EBCDIC CCSID 500 and EBCDIC CCSID
1047.
### Table 7. Code point differences between EBCDIC CCSID 500 and EBCDIC CCSID 1047

| Code point | Character CCSID 500 | Character CCSID 1047 |
| :--- | :--- | :--- |
| X'4A' | [ (left bracket) | ¢ (cent sign) [DB2Z-1-088]|
| X'4F' | ! (exclamation point) | \| (vertical bar) [DB2Z-1-089]|
| X'5A' | ] (right bracket) | ! (exclamation point) [DB2Z-1-090]|
| X'AD' | Ý (uppercase Y with acute accent) | [ (left bracket) [DB2Z-1-091]|
| X'B0' | ¢ (cent sign) | ¬ (logical not) [DB2Z-1-092]|
| X'BA' | ¬ (logical not) | Ý (uppercase Y with acute accent) [DB2Z-1-093]|
| X'BB' | \| (vertical bar) | ¨ (umlaut) [DB2Z-1-094]|
| X'BD' | ¨ (umlaut) | ] (right bracket) [DB2Z-1-095]|
Related concepts
Variant characters
Variant characters are characters that correspond to different code points across a given set of code
pages. For example, the character # is variant. It corresponds to code point X'7B' in CCSIDs 37, 273, 500,
and 1047. However, this character corresponds to code point X'4A' in CCSID 277.
Unicode
Unicode is an encoding scheme that currently provides a unique code point for over 100,000 characters.
This standard enables systems to more easily handle global data, regardless of the platform, program, or
language.
Before Unicode was defined, no single encoding was adequate for all available letters and symbols. For
example, consider the following restrictions for EBCDIC and ASCII:
• These encoding schemes have one code page per character set. For example, they have one code page [DB2Z-1-096]
for Japanese characters and another code page for German characters.
• These encoding schemes often encode data in different positions. For example, the letter A is encoded [DB2Z-1-097]
as X'C1' in most EBCDIC code pages, but it is encoded as X'41' in most ASCII code pages.
• Even within encoding schemes, characters might be mapped differently. For example, the letter ä is [DB2Z-1-098]
encoded as X'C0' in EBCDIC code page 273, but it is encoded as X'43' in EBCDIC code page 37. (Code
page 37 has the left brace character ( { ) at position X'C0'.) This same letter ä is encoded as X'E4' in
ASCII code page 819 and as X'7B' in ASCII code page 1011.
Thus, handling data from more than one character set, such as German characters and Arabic characters,
was difficult when ASCII or EBCDIC was used. [DB2Z-1-099]




Unicode avoids these problems by having a single standard that can provide a unique code point
for over a million characters. Currently, the standard has defined code points for just over 100,000
characters. You can view the Unicode code points by looking at the Unicode character code charts on the
Unicode Consortium web site. For example, if you look up Unicode code point U+41, you can see that it
corresponds to the character 'A'.
The following table shows the first 128 Unicode code points from U+00 to U+7E. These code points are
the same as those in ASCII 367. [DB2Z-1-100]
### Table 8. The first 128 code points for Unicode and ASCII CCSID 367

| 2nd↓ \ 1st→ | 0- | 1- | 2- | 3- | 4- | 5- | 6- | 7- |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **-0** | NUL | DLE | (sp) | 0 | @ | P | ` | p [DB2Z-1-101]|
| **-1** | SOH | DC1 | ! | 1 | A | Q | a | q [DB2Z-1-102]|
| **-2** | STX | DC2 | " | 2 | B | R | b | r [DB2Z-1-103]|
| **-3** | ETX | DC3 | # | 3 | C | S | c | s [DB2Z-1-104]|
| **-4** | EOT | DC4 | $ | 4 | D | T | d | t [DB2Z-1-105]|
| **-5** | ENQ | NAK | % | 5 | E | U | e | u [DB2Z-1-106]|
| **-6** | ACK | SYN | & | 6 | F | V | f | v [DB2Z-1-107]|
| **-7** | BEL | ETB | ' | 7 | G | W | g | w [DB2Z-1-108]|
| **-8** | BS | CAN | ( | 8 | H | X | h | x [DB2Z-1-109]|
| **-9** | HT | EM | ) | 9 | I | Y | i | y [DB2Z-1-110]|
| **-A** | LF | SUB | * | : | J | Z | j | z [DB2Z-1-111]|
| **-B** | VT | ESC | + | ; | K | [ | k | { [DB2Z-1-112]|
| **-C** | FF | FS | , | < | L | \ | l | \ [DB2Z-1-113]| |
| **-D** | CR | GS | - | = | M | ] | m | } [DB2Z-1-114]|
| **-E** | SO | RS | . | > | N | ^ | n | ~ [DB2Z-1-115]|
| **-F** | SI | US | / | ? | O | _ | o | DEL [DB2Z-1-116]|
Related concepts
Code pages and CCSIDs
Because computers store only numbers, they store letters and other characters by assigning a number
to them. Which number is mapped to each character depends on the CCSID and code page that is
associated with that character.
Related information
Unicode Consortium
Unicode Character Code Charts (on Unicode Consortium website)
Displaying Unicode services (UNI) (z/OS MVS System Commands)
UTFs
Each Unicode code point can be expressed in several different formats. These formats are called Unicode
transformation formats (UTFs). For example, the letter M is the Unicode code point U+004D. In UTF-8,
this code point is represented as X'4D'. In UTF-16, this code point can be represented as X'004D'. 1
A UTF maps each Unicode code point to a unique code unit sequence. A code unit is the minimal bit
combination that can represent a character. Each UTF uses a different code unit size. For example, UTF-8 [DB2Z-1-117]


Chapter 1. International data and character conversion in Db2 for z/OS  11 [DB2Z-1-118]



is based on 8-bit code units. Therefore, each character can be 8 bits (1 byte), 16 bits (2 bytes), 24 bits (3
bytes), or 32 bits (4 bytes). Likewise, UTF-16 is based on 16-bit code units. Therefore, each character can
be 16 bits (2 bytes) or 32 bits (4 bytes).
All UTFs include the full Unicode character repertoire, or set of characters. Each UTF can represent any
Unicode character that you need to represent.
The following UTFs are defined by the Unicode Consortium:
UTF-8
UTF-8 is based on 8-bit code units. Each character is encoded as 1 to 4 bytes.
The first 128 Unicode code points are encoded as 1 byte in UTF-8. These code points are the same as
those in ASCII CCSID 367. Any other character is encoded with more than 1 byte in UTF-8.
In IBM, UTF-8 is also known as Unicode CCSID 1208.
Db2 uses UTF-8 to encode data in the following ways:
• Db2 uses UTF-8 to encode data in CHAR, VARCHAR, and CLOB columns in Unicode tables. [DB2Z-1-119]
• Db2 parses SQL statements and precompiles source code in UTF-8. [DB2Z-1-120]
• The Db2 catalog tables that have the Unicode encoding scheme are encoded in UTF-8. [DB2Z-1-121]
UTF-16
UTF-16 is based on 16-bit code units. Each character is encoded as at least 2 bytes. Some characters
that are encoded with a 1-byte code unit in UTF-8 are encoded with a 2-byte code unit in UTF-16.
Characters that are surrogate or supplementary characters use 4 bytes and thus require additional
storage. These characters can also be stored in UTF-8 or UTF-32, but, because they always require 4
bytes of storage, neither of these formats provide any space savings.
In IBM, UTF-16 is also known as Unicode CCSID 1200.
Db2 uses UTF-16 to encode data in GRAPHIC, VARGRAPHIC, and DBCLOB columns in Unicode tables.
UTF-32
UTF-32 is based on 32-bit code units. Each character is encoded as at least 4 bytes. Db2 does not
store data in UTF-32.
The following table shows example UTF encodings for several characters. [DB2Z-1-122]
### Table 9. Example UTF encodings

| Character | Unicode code point | ASCII | UTF-8 | UTF-16 (Big Endian format)¹ | UTF-32 (Big Endian format) |
| :--- | :--- | :--- | :--- | :--- | :--- |
| A | U+0041 | X'41' | X'41' | X'0041' | X'00000041' [DB2Z-1-123]|
| a | U+0061 | X'61' | X'61' | X'0061' | X'00000061' [DB2Z-1-124]|
| 9 | U+0039 | X'39' | X'39' | X'0039' | X'00000039' [DB2Z-1-125]|
| Å | U+00C5 | X'C5' | X'C385'² | X'00C5' | X'000000C5' [DB2Z-1-126]|
|  | U+9860 | X'CDDB' (CCSID 939) | X'E9A1A0' | X'9860' | X'00009860' [DB2Z-1-127]|
|  | U+200D0 | Does not exist | X'F0A08390' | X'D840DCD0' | X'000200D0' [DB2Z-1-128]|

**Notes:**
1. z/OS uses Big Endian format only. Little Endian format is used in other operating systems. [DB2Z-1-129]
2. X'C5' becomes double-byte in UTF-8. [DB2Z-1-130]
Notice that for some characters, the UTF encodings are fairly predictable. For example, the character A,
which is Unicode code point U+0041, is encoded as X'41' in ASCII and UTF-8, and as X'0041' in UTF-16
and as X'00000041' in UTF-32. However, the UTF encodings for a character like Å or
  do not follow the
same pattern.
The process of converting a value from its Unicode code point to its UTF hexadecimal value is called
encoding. For example, Unicode code point U+0041 is encoded in UTF-8 as X'41'. The reverse process,
converting a UTF hexadecimal value to its Unicode code point, is called decoding. For example, suppose
that you see the hexadecimal value X'00C5' in trace output and you know that the data is in UTF-16. You
can decode the value to find that it corresponds to Unicode code point U+00C5. You can then look up this
Unicode code point on the Unicode character code charts on the Unicode Consortium web site and find
that it corresponds to the character Å.
You can find the steps for how to manually encode and decode Unicode data on the Unicode Consortium
web site. Alternatively, you can use a converter tool to do the conversion for you.
Related concepts
Endianness
Endianness is a data attribute that describes byte order. When applications exchange data, they need to
know the ordering convention for multi-byte data. Otherwise, data can be misinterpreted.
Related information
Unicode Consortium
UTF-8, UTF-16, UTF-32 & BOM (on Unicode Consortium website)
Unicode Character Code Charts (on Unicode Consortium website)
Unicode CCSIDs
Db2 for z/OS uses CCSIDs 367, 1200, and 1208 for Unicode data.
367
Db2 uses ASCII CCSID 367 for single-byte character data (SBCS) because the first 128 code points in
Unicode UTF-8 are the same as the those in ASCII CCSID 367.
Therefore, Db2 uses CCSID 367 for CHAR, VARCHAR, and CLOB columns that are defined with FOR
SBCS DATA in Unicode tables.
1208
Db2 uses CCSID 1208 for Unicode UTF-8 data, which Db2 always considers to be mixed data. This
CCSID is the default CCSID value for Unicode tables.
Therefore, Db2 uses CCSID 1208 for CHAR, VARCHAR, and CLOB columns that are defined with FOR
MIXED DATA in Unicode tables. FOR MIXED DATA is the default subtype specification.
1200
Db2 uses CCSID 1200 for Unicode UTF-16 data, which is double-byte data (DBCS). This CCSID
applies to GRAPHIC and VARGRAPHIC Unicode data.
Therefore, Db2 uses CCSID 1200 for GRAPHIC, VARGRAPHIC, and DBCLOB columns in Unicode
tables.


Chapter 1. International data and character conversion in Db2 for z/OS  13 [DB2Z-1-131]



CCSIDs usually refer to a code page at a particular point in time. However, the Unicode CCSIDs that Db2
for z/OS uses are an exception. They can expand to include more characters as the Unicode standard
grows. For example, CCSID 1200 can include the characters from the Unicode standard code pages
13488 (Unicode 2.0) and 17584 (Unicode 3.0). You can determine the CCSID for each Unicode standard [DB2Z-1-132]
code page by looking at the list of registered CCSIDs.
Because Db2 uses this architecture for CCSIDs, you can easily migrate to new versions of the Unicode
standard by just updating your conversion image. However, the disadvantage to this architecture is that
the CCSID value does not clearly tell you which characters are supported. To check which Unicode
standard is currently supported for a particular conversion, issue the system DISPLAY UNI command.
Example: DISPLAY UNI command
The following example output is from the command d uni,all :
  CUN3000I 09.33.37 UNI DISPLAY 754
   ENVIRONMENT: CREATED       01/25/2010 AT 00.20.12
                MODIFIED      01/25/2010 AT 00.25.10
                IMAGE CREATED --/--/---- AT --.--.--
       SERVICE: CHARACTER      CASE           NORMALIZATION  COLLATION
                STRINGPREP     BIDI           CONVERSION INF
       STORAGE: ACTIVE       1995 PAGES
                FIXED           0 PAGES
                LIMIT      524288 PAGES
      CASECONV: ENABLED
      CASE VER: UNI300  NORMAL  SPECIAL LOCALE
     NORMALIZE: DISABLED
      NORM VER: NONE
       COLLATE: DISABLED
    COLL RULES: NONE
   STRPROFILES: NONE
    CONVERSION: 00367-05123-R               00437-00819-R
                00273-01208-R               01140-01252-E
                01140-01252-R               00437-00850-E
                00437-00850-R               01200(17584)-01140-E
                01200(17584)-01141-E        01200(17584)-01142-E
                01200(17584)-01144-E        00273-01252-E
                00273-01252-R               01200(17584)-01148-E
                00367-05210-R               00850-01200(13488)-R
                01142-00367-E               00836-00367-E
                01386-00836-R               01148-01200(17584)-R
                01386-00935-RE              00437-01140-E
                00437-01140-R               00437-01148-E
                00437-01148-R               00437-01208-R
    ...
The CONVERSION section of this output lists all of the CCSID conversions that are defined. For example,
the line 01200(17584)-01141-E defines the conversion between CCSID 1200 and CCSID 1141. Db2
uses CCSID 1200 for Unicode UTF-16 data. The number in parentheses after CCSID 1200, 17584, means
that in this conversion, CCSID 1200 uses Unicode standard 3.0. In the line 00850-01200(13488)-R,
CCSID 1200 is followed by a different number, 13488. For this conversion, CCSID 1200 uses Unicode
standard 2.0. The letters E and R represent the type of conversion. E means that the conversion is an
enforced subset conversion. R means that the conversion is a round-trip conversion.
Related information
Displaying Unicode services (UNI) (z/OS MVS System Commands)
Endianness
Endianness is a data attribute that describes byte order. When applications exchange data, they need to
know the ordering convention for multi-byte data. Otherwise, data can be misinterpreted.
Data can have the following byte order formats:
Big endian
A format in which the most significant byte is stored first. The other bytes follow in decreasing order of
significance. For example, for a four-byte word, the byte order is 0, 1, 2, 3. For a two-byte word, it is 0,
1.
Big endian format is used by pSeries, IBM Z, iSeries, Sun, and HP. [DB2Z-1-133]




Little endian
A format in which the least significant byte is stored first. The other bytes follow in increasing order of
significance. For example, for a four-byte word, the byte order is 3, 2, 1, 0. For a two-byte word, it is 1,
0.
Little endian format is used by Intel-based machines, including xSeries.
Endianness affects only multi-byte data. Within a single byte, the bits are always ordered in the same way.
Bit order within a byte is always 7, 6, 5, 4, 3, 2, 1, 0.
UTF-8 data is not affected by endianness, even if the data is stored as more than 1 byte. UTF-16 data
and UTF-32 data are affected by endianness. For example, the character 'A' is encoded for UTF-16 and
UTF-32 as shown in the following table: [DB2Z-1-134]
### Table 10. Example encoding for the character 'A'

| | UTF-16 | UTF-32¹ |
| :--- | :--- | :--- |
| **Big endian** | X'0041' | X'00000041' [DB2Z-1-135]|
| **Little endian** | X'4100' | X'41000000' [DB2Z-1-136]|

**Note:**
1. Db2 for z/OS does not store data in UTF-32. [DB2Z-1-137]
Endianness becomes a potential problem when data is exchanged between systems and applications that
use different endian formats and the data is not properly converted. Be aware of the endian format of
the data that your system or application handles. You might notice endianness problems when looking at
character encoding values in traces. Such a problem might exist if you notice that numeric byte values
have been switched. For example, you expect X'0041' but see X'4100'.
Example
Suppose that you are loading data in UTF-16 little endian format (CCSID 1202) from a .NET application.
Db2 for z/OS does not support storing data in CCSID 1202. However, Db2 does support conversions to
and from CCSID 1202. Thus, Db2 converts the data and stores it in UTF-16 big endian format (CCSID
1200). In this case, you should be aware that the data format has changed.
Related reference
UTFs
Situations in which character conversion occurs
Character conversion is the process of converting data from one CCSID to another CCSID. This process
can occur when data is transferred between a remote and local system or when data is manipulated
within the local system.
Important: For best results, try to avoid character conversions whenever possible, because conversions
can potentially slow performance and sometimes cause data loss. The best way to avoid conversions is to
use the same CCSID for all of your data. For more information, see “Possible consequences of character
conversion”.
Character conversion is more likely to occur when you are accessing data remotely because this situation
often involves different platforms and encoding schemes. For example, in a client/server environment, a
requester might send the values of host variables in SELECT predicates and INSERT column values to the
current server. The current server might then send the values of result columns back to the requester. In
either transaction, if the string data has a different representation at the sending and receiving systems,
conversion occurs.
Conversion can also occur during string operations on the same system, as in the following examples:
• A DECLARE VARIABLE statement specifies an overriding CCSID. [DB2Z-1-138]
• A SQL descriptor (SQLDA) specifies an overriding CCSID for a string column. [DB2Z-1-139]


Chapter 1. International data and character conversion in Db2 for z/OS  15 [DB2Z-1-140]



• You compare or combine data from multiple CCSIDs in an SQL statement. [DB2Z-1-141]
• You use SPUFI, which processes EBCDIC data, to insert data into a Unicode table. [DB2Z-1-142]
• The value of the ENCODING bind option (for static SQL statements) or the CURRENT APPLICATION [DB2Z-1-143]
ENCODING SCHEME special register (for dynamic SQL statements) is different than the encoding
scheme of the data that is being inserted or retrieved.
• An ASCII or EBCDIC application provides SQL statement text to Db2 in a PREPARE statement. Db2 [DB2Z-1-144]
converts the statement text to Unicode for parsing.
For more information, see .
Related concepts
Objects with different CCSIDs in the same SQL statement
You can reference data with different CCSIDs from the same SQL statement. This ability is useful if you
use table objects such as tables, views, temporary tables, query tables, and user-defined functions with
different CCSIDs. However, you should understand how Db2 for z/OS processes these queries so that you
can code them correctly.
Related reference
DECLARE VARIABLE statement (Db2 SQL)
SQL descriptor area (SQLDA) (Db2 SQL)
BIND and REBIND options for packages, plans, and services (Db2 Commands)
CURRENT APPLICATION ENCODING SCHEME special register (Db2 SQL)
PREPARE statement (Db2 SQL)
Possible consequences of character conversion
For best results, try to avoid character conversions whenever possible, because conversions can
potentially slow performance and sometimes cause data loss. The best way to avoid conversions is to
use the same CCSID for all of your data.
Character conversion is the process of converting data from one CCSID to another CCSID. This process
can occur when data is transferred between a remote and local system or when data is manipulated
within the local system. For more information, see “Situations in which character conversion occurs” on
page 15.
The best character conversion is no conversion, because conversion always has a performance cost. The
cost depends on the extent of the conversion. For example, if you have a Unicode table and select every
row into an EBCDIC application, the performance cost is probably noticeable. However if you issue a
SELECT MAX(xxxx) FROM  on a Unicode table, and then convert the result to EBCDIC, you might not
notice the performance cost.
The second best conversion is a lossless conversion. A lossless conversion is one in which all characters in
the source CCSID exist in the target CCSID and thus, no character is lost. For example, a conversion from
CCSID 37 to CCSID 500 is lossless, because they both include the same set of characters. The difference
between these two CCSIDs is the placement of 7 characters. These seven characters have different code
points in each of these CCSIDs. A conversion from CCSID 1208 (UTF-8) to CCSID 1200 (UTF-16) is also
lossless, because they both include the same repertoire of characters.
If the conversion is not a lossless conversion, certain characters might be lost. ("Lost" means that these
characters are replaced by substitution characters.) Thus, the integrity of your data can be compromised.
Related concepts
Application encoding scheme
The application encoding scheme is the CCSID that your application uses to interpret data in host
variables. For Db2 for z/OS applications, typically the application encoding scheme is the value of the
ENCODING bind option. (By default this value is the subsystem default application encoding scheme.)
Related tasks
Specifying a CCSID for your application [DB2Z-1-145]




In Db2 for z/OS applications, one CCSID is associated with the source code and one or more CCSIDs can
be associated with the data that your application manipulates. The CCSID that Db2 associates with the
data is called the application encoding scheme.
Determining current subsystem CCSID and encoding scheme values
For an existing subsystem, you can check the CCSID values, but do not make changes. If you suspect that
the specified CCSIDs are incorrect or you need to change them, contact IBM Support.
Types of character conversion
Character conversions can be characterized by their effect on the length of the string. Conversions can be
expanding, contracting or neither. Character conversions can also be characterized by how they handle
characters that do not exist in the target CCSID. They can be round-trip conversions or enforced subset
conversions.
Expanding conversion
An expanding conversion occurs when the length of the converted string is greater than that of the source
string.
For example, an expanding conversion occurs when an ASCII mixed data string that contains DBCS
characters is converted to an EBCDIC mixed data string. The expansion occurs because of the addition
of shift-out and shift-in control characters. Expanding conversions can also occur when string data is
converted to or from Unicode.
Expanding conversions typically affect European and Asian Pacific languages. For example, the German
name Jürgen expands when it is converted from ASCII or EBCDIC to Unicode. Also, Japanese, Korean,
and Chinese strings expand when they are converted from ASCII to EBCDIC.
Expanding conversions can have the following effects on Db2:
• Expanding conversions might cause problems with fixed-length variables. For example, when a fixed-length host variable needs to be converted from ASCII mixed data to EBCDIC mixed data, an error [DB2Z-1-146]
occurs. The problem occurs because the conversion is an expanding conversion, but the host variable
is fixed-length. The solution is to use a varying-length string variable with a maximum length that is
sufficient to contain the expansion.
• Expanding conversions can affect fixed-length strings. If you use a fixed-length string and an expanding [DB2Z-1-147]
conversion occurs, Db2 truncates the string. Db2 examines the characters that are being truncated
to ensure that significant data is not truncated. For example, trailing blanks are insignificant. In this
situation, consider using the VARCHAR data type for these strings.
• Expanding conversions can affect the results of length functions, such as LENGTH, [DB2Z-1-148]
CHARACTER_LENGTH, SUBSTRING, and SUBSTR on the converted string. For CHARACTER_LENGTH
and SUBSTRING, use the CODEUNITS16 and CODEUNITS32 options to limit the effects of expanding
conversions.
• Expanding conversions can affect the length of the object names, such as table names and column [DB2Z-1-149]
names. You can avoid these problems by not using special characters in object names.
To determine the worst-case result length of a CCSID conversion, use the following table. [DB2Z-1-150]


Chapter 1. International data and character conversion in Db2 for z/OS  17 [DB2Z-1-151]



### Table 11. Worst case result length of CCSID conversion

Where **X** represents **LENGTH(string in bytes)**. [DB2Z-1-152]

| From CCSID \ To CCSID | EBCDIC SBCS | EBCDIC Mixed | EBCDIC DBCS | ASCII SBCS | ASCII Mixed | ASCII DBCS | Unicode SBCS | Unicode UTF-8 | Unicode UTF-16 |
| :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- | :--- |
| **EBCDIC SBCS** | X | X | X * 2¹ | X | X | X * 2¹ | X¹ | X * 3 | X * 2 [DB2Z-1-153]|
| **EBCDIC Mixed** | X | X | X * 2¹ | X | X | X * 2¹ | X¹ | X * 3 | X * 2 [DB2Z-1-154]|
| **EBCDIC DBCS** | X * 0.5¹ | X + 2 | X | X * 0.5¹ | X | X | X * 0.5 | X * 1.5 | X [DB2Z-1-155]|
| **ASCII SBCS** | X | X | X * 2¹ | X | X | X * 2¹ | X¹ | X * 3 | X * 2 [DB2Z-1-156]|
| **ASCII Mixed** | X | X * 1.8 | X * 2¹ | X | X | X * 2¹ | X¹ | X * 3 | X * 2 [DB2Z-1-157]|
| **ASCII DBCS** | X * 0.5¹ | X + 2 | X | X * 0.5¹ | X | X | X * 0.5 | X * 1.5 | X [DB2Z-1-158]|
| **Unicode SBCS** | X | X | X * 2 | X | X | X * 2 | X | X | X * 2 [DB2Z-1-159]|
| **Unicode UTF-8** | X | X * 1.25 | X | X | X | X | X | X | X * 2 [DB2Z-1-160]|
| **Unicode UTF-16** | X * 0.5 | X + 2 | X | X * 0.5 | X | X | X * 0.5 | X * 1.5 | X [DB2Z-1-161]|

**Note:**
1. Because of the high probability of data loss, IBM does not provide conversion tables for this combination of two CCSIDs and data subtypes. [DB2Z-1-162]

### Table 12. Example of a character string in different encoding schemes

| Data type and encoding scheme | Character representation | Hexadecimal representation (with spaces separating each character) |
| :--- | :--- | :--- |
| **9 bytes in ASCII** | genki | 8CB3 67 65 6E 8B43 6B 69 [DB2Z-1-163]|
| **13 bytes in EBCDIC** | sO sI gen sO sI ki | 0E 4695 0F 87 85 95 0E 45B9 0F 92 89 [DB2Z-1-164]|
| **11 bytes in Unicode UTF-8** | genki | E5 85 83 67 65 6E E6 B0 97 6B 69 [DB2Z-1-165]|




If you convert this string from ASCII to EBCDIC, notice that shift-in and shift-out characters are
added. This conversion is an example of an expanding conversion. The length increases from 9 bytes
to 13 bytes.
Related reference
CHARACTER_LENGTH or CHAR_LENGTH scalar function (Db2 SQL)
LENGTH scalar function (Db2 SQL)
SUBSTR scalar function (Db2 SQL)
SUBSTRING scalar function (Db2 SQL)
Contracting conversion
A contracting conversion occurs when the length of the converted string is smaller than that of the source
string.
For example, a contracting conversion occurs when an EBCDIC mixed data string that contains DBCS
characters is converted to ASCII mixed. The contraction occurs because shift characters are removed.
Contracting conversions can also occur when string data is converted to or from Unicode.
Contracting conversions typically affect European and Asian Pacific languages. For example, the German
word straße contracts when it is converted from Unicode to ASCII or EBCDIC. Also, Japanese, Korean,
and Chinese strings might contract when they are converted from EBCDIC to ASCII.
Contracting conversions can have the following effects on Db2:
• Contracting conversions can affect the results of the length functions, such as LENGTH, [DB2Z-1-166]
CHARACTER_LENGTH, SUBSTRING, and SUBSTR, on the converted string. For CHARACTER_LENGTH
and SUBSTRING, use the CODEUNITS16 and CODEUNITS32 options to limit the effects of contracting
conversions.
• Contracting conversions can affect the length of the object names, such as table names and column [DB2Z-1-167]
names. You can avoid these problems by not using special characters in object names.
To determine the worst-case result length of a CCSID conversion, use the table in “Expanding conversion”
.
Examples
Example
In UTF-8 CCSID 1208, the character Å is represented by the code point X'C385'. In ASCII CCSID 819,
this character is represented by X'C5'. Thus, the conversion of the character Å from CCSID 1208 to
CCSID 819 is a contracting conversion.
Example
The German name Jürgen contracts when it is converted from Unicode to ASCII or EBCDIC and
expands when it is converted from ASCII or EBCDIC to Unicode.
Related concepts
String unit specifications (Db2 SQL)
Related reference
CHARACTER_LENGTH or CHAR_LENGTH scalar function (Db2 SQL)
LENGTH scalar function (Db2 SQL)
SUBSTR scalar function (Db2 SQL)
SUBSTRING scalar function (Db2 SQL) [DB2Z-1-168]


Chapter 1. International data and character conversion in Db2 for z/OS  19 [DB2Z-1-169]



Round-trip conversion
A round-trip conversion ensures the integrity of all character data from the source CCSID to the target
CCSID and back to the source. Even if the target CCSID does not support a given character, the character
regains its original hexadecimal value after it is converted back to the source CCSID.
One alternative to a round-trip conversion is an enforced subset conversion, during which characters that
do not exist in the target CCSID are lost. Whether a particular conversion uses a round-trip conversion or
an enforced subset conversion depends on how your system is set up to do conversions. For example, in
Db2 for z/OS, many conversions are defined by z/OS Unicode Services. Each of the conversion definitions
specifies whether to use a round-trip or enforced subset conversion.
A round-trip conversion works only in a two-tier homogenous environment where the data makes the
complete round trip. For example, if you pass data from Db2 for Linux, UNIX, and Windows to Db2 for
z/OS and then back to Db2 for Linux, UNIX, and Windows with a round-trip conversion, no data is lost. The
data was converted back to its original format. However, if you have a more complicated environment, a
round-trip conversion does not necessarily preserve data integrity. For example, if you pass data from Db2
for z/OS to Db2 for Linux, UNIX, and Windows and then to Linux on a Java client, two conversions have
potentially occurred. Because the data was not converted back to its original format before the second
conversion, data might have been lost even if round-trip conversions are used.
Example
In ASCII CCSID 1252, the trademark symbol ™ is code point X'99'. In EBCDIC CCSID 37, this code point
does not exist. During character conversion from ASCII CCSID 1252 to EBCDIC CCSID 37, the trademark
symbol is converted to a control character, X'39'. If you use SPUFI to select the data, the data displays
as the unprintable character that you specified in your emulator, which is generally a quotation mark (")
or a period (.). If you issue a SELECT HEX(column) statement, the data displays as X'39', which is the DEL
control character. When the client uses a round-trip conversion to convert this character back to ASCII
CCSID 1252, the trademark symbol is preserved as code point X'99'. Notice that this conversion is not
lossless unless it is converted back to the original format.
Enforced subset conversion
An enforced subset conversion occurs when a character in the source CCSID does not have a code point in
the target CCSID. In this case, the character is converted to a single substitution character.
The default substitution characters (SUB) are:
• X'3F' for SBCS EBCDIC [DB2Z-1-170]
• X'1A' or X'7F' for SBCS ASCII [DB2Z-1-171]
• X'1A'for UTF-8 [DB2Z-1-172]
• X'001A' for UTF-16 [DB2Z-1-173]
For DBCS data, the substitution character varies depending on the CCSID.
One alternative to an enforced subset conversion is a round-trip conversion, which preserves characters
if they are converted back to the originally CCSID. Whether a particular conversion uses a round-trip
conversion or an enforced subset conversion depends on how your system is set up to do conversions. For
example, in Db2 for z/OS, many conversions are defined by z/OS Unicode Services. Each of the conversion
definitions specifies whether to use a round-trip or enforced subset conversion.
Examples
Example
In ASCII CCSID 1252, the trademark symbol ™ is represented by the code point X'99'. In EBCDIC
CCSID 37, this code point does not exist. During an enforced subset character conversion to EBCDIC
CCSID 37, this code point is converted to the substitution character X'3F'. When the code point
is converted back to ASCII CCSID 1252, the character remains a substitution character and is
represented by the code point X'1A'. [DB2Z-1-174]




Example
In ASCII CCSID 5348, the euro symbol (€ ) is represented by the code point X'80'. In EBCDIC CCSID
37, this code point does not exist. During an enforced subset character conversion to EBCDIC CCSID
37, this code point is converted to the substitution character X'3F'. When the code point is converted
back to ASCII CCSID 5348, the character remains a substitution character and is represented by the
code point X'1A'.
z/OS Unicode Services uses enforced subset conversions when converting from Unicode to ASCII or
EBCDIC to handle characters that do not exist in the target CCSID. In this situation, enforced subset
conversions are required because Unicode has room to include over 1 million code points, but ASCII and
EBCDIC single-byte character sets can include only 256 code points. [DB2Z-1-175]
