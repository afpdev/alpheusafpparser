# Chapter 6. Compliance with PTOCA
This chapter:
* Describes the base level of PTOCA [PTOCA-6-001]
* Describes the PT1 subset of PTOCA [PTOCA-6-002]
* Describes the PT2 subset of PTOCA [PTOCA-6-003]
* Describes the PT3 subset of PTOCA [PTOCA-6-004]
* Describes the PT4 subset of PTOCA [PTOCA-6-005]
* States general requirements for compliance [PTOCA-6-006]
## Base Level
The mandatory base level contains functions and facilities required by all implementations. By itself the
mandatory base level is not sufficient; it must always be supplemented by a higher subset such as PT1, PT2,
or PT3. The base level represents the base set of functions defined within PTOCA. It is the minimum set of
functions required to be supported in any environment, and consists of the following minimum general
communication capabilities:
* Recognition of control sequences, chained or unchained [PTOCA-6-007]
* Interpretation and validation of the control sequence [PTOCA-6-008]
* Rejection of control sequences and parameters, including return of error data, that are not supported within [PTOCA-6-009]
the supported subset
* Reporting, on request from the controlling environment, the supported features [PTOCA-6-010]
* Reporting exception conditions to the controlling environment [PTOCA-6-011]

<!-- Page 172 -->

## PT1 Subset
The following table shows the control sequences that are valid for a PT1 subset compliant receiver, and the
associated parameter ranges. The offset shown in the table indicates the beginning of the parameter data
within the control sequence.
Control Sequence Offset (Unchained) Parameter PT1 Range Notes
AMB 4-5 DSPLCMNT X'0000'-X'7FFF' 4
AMI 4-5 DSPLCMNT X'0000'-X'7FFF' 4
BLN 6
BSU 4 LID X'01'-X'7F'
DBR 4-5 RLENGTH X'0000'-X'7FFF' 1,4
6-8 RWIDTH X'0000'-X'00C0' 1,2,3 [PTOCA-6-012]
DIR 4-5 RLENGTH X'0000'-X'7FFF' 1,4
6-8 RWIDTH X'0000'-X'00C0' 1,2,3 [PTOCA-6-013]
ESU 4 LID X'01'-X'7F'
NOP 4-256 IGNDATA 7
RMB 4-5 INCRMENT X'8000'-X'7FFF' 1,4
RMI 4-5 INCRMENT X'8000'-X'7FFF' 1,4
RPS 4-5 RLENGTH X'0000'-X'7FFF'
6-256 RPTDATA 8 [PTOCA-6-014]
SBI 4-5 INCRMENT X'0000'-X'7FFF' 3,4
SCFL 4 LID X'00'-X'7F' 3
SIA 4-5 ADJSTMNT X'0000'-X'0FFF' 3,4
6 DIRECTION X'00' 9 [PTOCA-6-015]
SIM 4-5 DSPLCMNT X'0000'-X'7FFF' 3,4
STC 4-5 FRGCOLOR X'FF07' 3,5
6 PRECISION X'00'-X'01' 3,10 [PTOCA-6-016]
STO 4-5 IORNTION X'0000', X'2D00', X'5A00', X'8700' 3
6-7 BORNTION X'0000', X'2D00', X'5A00', X'8700' 3 [PTOCA-6-017]
SVI 4-5 INCRMENT X'0000'-X'0FFF' 3,4
TRN 4-256 TRNDATA 8
Notes:
1. This parameter is a signed binary number that may be positive or negative. Negative numbers are [PTOCA-6-018]
expressed in twos-complement form.
2. The PTOCA range for RWI DTH is X'8000' - X'7FFF' plus a fractional value byte ranging from X'00' - X'FF'. [PTOCA-6-019]
That is, the fractional values range from 1/2 measurement unit to 1/256 measurement unit. The PT1 subset
range for RWIDTH is X'0000' - X'00C0' in bytes 6 and 7. Byte 8 is always X'00', unless bytes 6 and 7 are
X'FFFF', in which case byte 8 is X'FF'.
3. The default indicator is allowed, meaning obtain a value from the hierarchy. [PTOCA-6-020]
4. The range values shown assume a measurement unit of 1/1440 inch. That is, the measurement base is [PTOCA-6-021]
assumed to be ten inches, and the $X_p$
-units per unit base and $Y_p$
-units per unit base are assumed to be [PTOCA-6-022]
## PT1 Subset

<!-- Page 173 -->

14,400. If a different measurement unit is used, the correct range values can be determined using the
conversion routine described in “Interpreting Ranges”.
5. For the PTOCA range, see “Set Text Color (STC)”. The PT1 range is X'FF07'. [PTOCA-6-023]
6. The Begin Line (BLN) control sequence has no parameters. [PTOCA-6-024]
7. The No Operation (NOP) control sequence may contain any data that does not exceed the field length. The [PTOCA-6-025]
data is ignored.
8. The Transparent Data (TRN) and Repeat String (RPS) control sequences may contain any data that does [PTOCA-6-026]
not exceed the field length. However, the data will be presented as a character string.
9. The default indicator is not allowed for this parameter in this subset. [PTOCA-6-027]
10. The STC PRECISION parameter has been retired; see “Retired Parameters”. New PTOCA [PTOCA-6-028]
generators should not specify this parameter and new receivers should ignore it.. [PTOCA-6-029]
## PT1 Subset

<!-- Page 174 -->

## PT2 Subset
The following table shows the control sequences that are valid for a PT2 subset compliant receiver, and the
associated parameter ranges. The offset shown in the table indicates the beginning of the parameter data
within the control sequence.
Control Sequence Offset (Unchained) Parameter PT2 Range Notes
AMB 4-5 DSPLCMNT X'0000'-X'7FFF' 4
AMI 4-5 DSPLCMNT X'0000'-X'7FFF' 4
BLN 6
BSU 4 LID X'01'-X'7F'
DBR 4-5 RLENGTH X'0000'-X'7FFF' 1,4
6-8 RWIDTH X'0000'-X'00C0' 1,2,3 [PTOCA-6-030]
DIR 4-5 RLENGTH X'0000'-X'7FFF' 1,4
6-8 RWIDTH X'0000'-X'00C0' 1,2,3 [PTOCA-6-031]
ESU 4 LID X'01'-'X'7F'
NOP 4-256 IGNDATA 7
OVS 4 BYPSIDEN X'00'-X'0E' 3
5-6 OVERCHAR X'0000'-X'FFFF' [PTOCA-6-032]
RMB 4-5 INCRMENT X'8000'-X'7FFF' 1,4
RMI 4-5 INCRMENT X'8000'-X'7FFF' 1,4
RPS 4-5 RLENGTH X'0000'-X'7FFF'
6-256 RPTDATA 8 [PTOCA-6-033]
SBI 4-5 INCRMENT X'0000'-X'7FFF' 3,4
SCFL 4 LID X'00'-X'FE' 3
SIA 4-5 ADJSTMNT X'0000'-X'0FFF' 3,4
6 DIRECTION X'00'-X'01' 3 [PTOCA-6-034]
SIM 4-5 DSPLCMNT X'0000'-X'7FFF' 3,4
STC 4-5 FRGCOLOR X'0000', X'FF00', X'FF07', X'FFFF' 3,5
6 PRECISION X'00'-X'01' 3,9 [PTOCA-6-035]
STO 4-5 IORNTION X'0000', X'2D00', X'5A00', X'8700' 3
6-7 BORNTION X'0000', X'2D00', X'5A00', X'8700' 3 [PTOCA-6-036]
SVI 4-5 INCRMENT X'0000'-X'0FFF' 3,4
TBM 4 DIRECTION X'00'-X'03' 3
5 PRECISION X'00'-X'01' 3 [PTOCA-6-037]
6-7 INCRMENT X'0000'-X'7FFF' 3,4 [PTOCA-6-038]
TRN 4-256 TRNDATA 8
USC 4 BYPSIDEN X'00'-X'0E' 3 [PTOCA-6-039]
## PT2 Subset

<!-- Page 175 -->

Notes:
1. This parameter is a signed binary number that may be positive or negative. Negative numbers are [PTOCA-6-040]
expressed in twos-complement form.
2. The PTOCA range for RWI DTH is X'8000' - X'7FFF' plus a fractional value byte ranging from X'00' - X'FF'. [PTOCA-6-041]
That is, the fractional values range from 1/2 measurement unit to 1/256 measurement unit. The PT2 subset
range for RWIDTH is X'0000' - X'00C0' in bytes 6 and 7. Byte 8 is always X'00', unless bytes 6 and 7 are
X'FFFF', in which case byte 8 is X'FF'.
3. The default indicator is allowed, meaning obtain a value from the hierarchy. [PTOCA-6-042]
4. The range values shown assume a measurement unit of 1/1440 inch. That is, the measurement base is [PTOCA-6-043]
assumed to be ten inches, and the $X_p$
-units per unit base and $Y_p$
-units per unit base are assumed to be
14,400. If a different measurement unit is used, the correct range values can be determined using the
conversion routine described in “Interpreting Ranges”.
5. For the PTOCA range, see “Set Text Color (STC)”. The PT2 range is X'0000', X'FF00', [PTOCA-6-044]
X'FF07', and X'FFFF'.
6. The Begin Line (BLN) control sequence has no parameters. [PTOCA-6-045]
7. The No Operation (NOP) control sequence may contain any data that does not exceed the field length. [PTOCA-6-046]
8. The Transparent Data (TRN) and Repeat String (RPS) control sequences may contain any data that does [PTOCA-6-047]
not exceed the field length. However, the data is presented as a character string.
9. The STC PRECISION parameter has been retired; see “Retired Parameters”. New PTOCA [PTOCA-6-048]
generators should not specify this parameter and new receivers should ignore it. [PTOCA-6-049]
## PT2 Subset

<!-- Page 176 -->

## PT3 Subset
The following table shows the control sequences that are valid for a PT3 subset compliant receiver, and the
associated parameter ranges. The offset shown in the table indicates the beginning of the parameter data
within the control sequence.
Control Sequence Offset (Unchained) Parameter PT3 Range Notes
AMB 4-5 DSPLCMNT X'0000'-X'7FFF' 4
AMI 4-5 DSPLCMNT X'0000'-X'7FFF' 4
BLN 6
BSU 4 LID X'01'-X'7F'
DBR 4-5 RLENGTH X'0000'-X'7FFF' 1,4
6-8 RWIDTH X'0000'-X'00C0' 1,2,3 [PTOCA-6-050]
DIR 4-5 RLENGTH X'0000'-X'7FFF' 1,4
6-8 RWIDTH X'0000'-X'00C0' 1,2,3 [PTOCA-6-051]
ESU 4 LID X'01'-'X'7F'
NOP 4-256 IGNDATA 7
OVS 4 BYPSIDEN X'00'-X'0E' 3
5-6 OVERCHAR X'0000'-X'FFFF' [PTOCA-6-052]
RMB 4-5 INCRMENT X'8000'-X'7FFF' 1,4
RMI 4-5 INCRMENT X'8000'-X'7FFF' 1,4
RPS 4-5 RLENGTH X'0000'-X'7FFF'
6-256 RPTDATA 8 [PTOCA-6-053]
SBI 4-5 INCRMENT X'0000'-X'7FFF' 3,4
SCFL 4 LID X'00'-X'FE' 3
SEC 5
10
1 1
12
13
14-17
COLSPCE
COLSIZE1
COLSIZE2
COLSIZE3
COLSIZE4
COL Value
X'01', X'04', X'06'
X'08', X'40'
X'01'-X'08', X'10'
X'00'-X'08'
X'00'-X'08'
X'00'-X'08'
Full range allowed by
the color space
SIA 4-5 ADJSTMNT X'0000'-X'0FFF' 3,4
6 DIRECTION X'00'-X'01' 3 [PTOCA-6-054]
SIM 4-5 DSPLCMNT X'0000'-X'7FFF' 3,4
STC 4-5 FRGCOLOR X'0000', X'FF00', X'FF07', X'FFFF' 3,5
6 PRECISION X'00'-X'01' 3,9 [PTOCA-6-055]
STO 4-5 IORNTION X'0000', X'2D00', X'5A00', X'8700' 3
6-7 BORNTION X'0000', X'2D00', X'5A00', X'8700' 3 [PTOCA-6-056]
SVI 4-5 INCRMENT X'0000'-X'0FFF' 3,4
TBM 4 DIRECTION X'00'-X'03' 3 [PTOCA-6-057]
## PT3 Subset

<!-- Page 177 -->

Control Sequence Offset (Unchained) Parameter PT3 Range Notes
5 PRECISION X'00'-X'01' 3 [PTOCA-6-058]
6-7 INCRMENT X'0000'-X'7FFF' 3,4 [PTOCA-6-059]
TRN 4-256 TRNDATA 8
USC 4 BYPSIDEN X'00'-X'0E' 3
Notes:
1. This parameter is a signed binary number that may be positive or negative. Negative numbers are [PTOCA-6-060]
expressed in twos-complement form.
2. The PTOCA range for RWI DTH is X'8000' - X'7FFF' plus a fractional value byte ranging from X'00' - X'FF'. [PTOCA-6-061]
That is, the fractional values range from 1/2 measurement unit to 1/256 measurement unit. The PT3 subset
range for RWIDTH is X'0000' - X'00C0' in bytes 6 and 7. Byte 8 is always X'00', unless bytes 6 and 7 are
X'FFFF', in which case byte 8 is X'FF'.
3. The default indicator is allowed, meaning obtain a value from the hierarchy. [PTOCA-6-062]
4. The range values shown assume a measurement unit of 1/1440 inch. That is, the measurement base is [PTOCA-6-063]
assumed to be ten inches, and the $X_p$
-units per unit base and $Y_p$
-units per unit base are assumed to be
14,400. If a different measurement unit is used, the correct range values can be determined using the
conversion routine described in “Interpreting Ranges”.
5. For the PTOCA range, see “Set Text Color (STC)”. The PT3 range is X'0000', X'FF00', [PTOCA-6-064]
X'FF07', and X'FFFF'.
6. The Begin Line (BLN) control sequence has no parameters. [PTOCA-6-065]
7. The No Operation (NOP) control sequence may contain any data that does not exceed the field length. [PTOCA-6-066]
8. The Transparent Data (TRN) and Repeat String (RPS) control sequences may contain any data that does [PTOCA-6-067]
not exceed the field length. However, the data is presented as a character string.
9. The STC PRECISION parameter has been retired; see “Retired Parameters”. New PTOCA [PTOCA-6-068]
generators should not specify this parameter and new receivers should ignore it.. [PTOCA-6-069]
## PT3 Subset

<!-- Page 178 -->

## PT4 Subset
The following table shows the control sequences that are valid for a PT4 subset compliant receiver, and the
associated parameter ranges. The offset shown in the table indicates the beginning of the parameter data
within the control sequence.
Control Sequence Offset (Unchained) Parameter PT4 Range Notes
AMB 4-5 DSPLCMNT X'0000'-X'7FFF' 3
AMI 4-5 DSPLCMNT X'0000'-X'7FFF' 3
BLN 5
BSU 4 LID X'01'-X'7F'
DBR 4-5 RLENGTH X'8000'-X'7FFF' 1, 3
6-7
8
RWIDTH X'8000'-X'7FFF'
X'00'-X'FF'
1,2
DIR 4-5 RLENGTH X'8000'-X'7FFF' 1,3
6-7
8
RWIDTH X'8000'-X'7FFF'
X'00'-X'FF'
1,2
ESU 4 LID X'01'-'X'7F'
GAR 4–253 (chained) ADVANCE X'0000'-'X'FFFF' 3
GIR 4–253 (chained) GLYPHID X'0000'-'X'FFFF'
GLC 4-5 IADVNCE X'8000'-X'7FFF' 1, 3
6 OIDLGTH 0, 13-129 [PTOCA-6-070]
7 FFNLGTH 0-(255-(10+OIDLGTH)) [PTOCA-6-071]
12 -n FONTOID (first byte) X'06' [PTOCA-6-072]
(n+1)–p FFONTNME valid UTF-16BE code points
GOR 4–253 (chained) OFFSET X'8000'-X'7FFF' 1
NOP 4-256 IGNDATA 6
OVS 4 BYPSIDEN X'00'-X'0E' 2
5-6 OVERCHAR X'0000'-X'FFFF' [PTOCA-6-073]
RMB 4-5 INCRMENT X'8000'-X'7FFF' 1,3
RMI 4-5 INCRMENT X'8000'-X'7FFF' 1,3
RPS 4-5 RLENGTH X'0000'-X'7FFF'
6-256 RPTDATA 7 [PTOCA-6-074]
SBI 4-5 INCRMENT X'0000'-X'7FFF' 2,3
SCFL 4 LID X'00'-X'FE' 2
SEC 5
10
1 1
12
13
14-17
COLSPCE
COLSIZE1
COLSIZE2
COLSIZE3
COLSIZE4
COL Value
X'01', X'04', X'06'
X'08', X'40'
X'01'-X'08', X'10'
X'00'-X'08'
X'00'-X'08'
X'00'-X'08'
Full range allowed by
the color space
## PT4 Subset

<!-- Page 179 -->

Control Sequence Offset (Unchained) Parameter PT4 Range Notes
SIA 4-5 ADJSTMNT X'0000'-X'7FFF' 2,3
6 DIRECTION X'00'-X'01' 2 [PTOCA-6-075]
SIM 4-5 DSPLCMNT X'0000'-X'7FFF' 2,3
STC 4-5 FRGCOLOR X'0000'-X'0010', X'FF00'-X'FF08',
X'FFFF'
2,4
6 PRECISION X'00'-X'01' 2,8 [PTOCA-6-076]
STO 4-5 IORNTION X'0000', X'2D00', X'5A00', X'8700' 2
6-7 BORNTION X'0000', X'2D00', X'5A00', X'8700' 2 [PTOCA-6-077]
SVI 4-5 INCRMENT X'0000'-X'7FFF' 2,3
TBM 4 DIRECTION X'00'-X'03' 2
5 PRECISION X'00'-X'01' 2 [PTOCA-6-078]
6-7 INCRMENT X'0000'-X'7FFF' 2,3 [PTOCA-6-079]
TRN 4-256 TRNDATA 7
UCT 9
USC 4 BYPSIDEN X'00'-X'0E' 2
Notes:
1. This parameter is a signed binary number that may be positive or negative. Negative numbers are [PTOCA-6-080]
expressed in twos-complement form.
2. The default indicator is allowed, meaning obtain a value from the hierarchy. [PTOCA-6-081]
3. The range values shown assume a measurement unit of 1/1440 inch. That is, the measurement base is [PTOCA-6-082]
assumed to be ten inches, and the $X_p$
-units per unit base and $Y_p$
-units per unit base are assumed to be
14,400. If a different measurement unit is used, the correct range values can be determined using the
conversion routine described in “Interpreting Ranges”.
4. For the PTOCA range, see “Set Text Color (STC)”. The PT4 range is the full Standard OCA [PTOCA-6-083]
Color Value Table found in the MO:DCA Reference.
5. The Begin Line (BLN) control sequence has no parameters. [PTOCA-6-084]
6. The No Operation (NOP) control sequence may contain any data that does not exceed the field length. [PTOCA-6-085]
7. The Transparent Data (TRN) and Repeat String (RPS) control sequences may contain any data that does [PTOCA-6-086]
not exceed the field length. However, the data is presented as a character string.
8. The STC PRECISION parameter has been retired; see “Retired Parameters”. New PTOCA [PTOCA-6-087]
generators should not specify this parameter and new receivers should ignore it.
9. The UCT must be chained to a GAR or GOR and is not rendered; all parameters are ignored. [PTOCA-6-088]
## PT4 Subset

<!-- Page 180 -->

General Requirements for Compliance
In claiming support as a PTOCA receiver, a product is stating that it has identified the subsets sufficient for its
needs, and has implemented all mandatory and optional control sequences, parameters, and ranges within
those subsets, including support of the PTOCA default values specified for those control sequences and
parameters. A receiver may also support additional function beyond the PTOCA subset that it claims to
support.
In claiming support as a PTOCA generator, a product is stating that it has identified which subset is sufficient
for its needs, and that the Presentation Text object content it produces is limited to the control sequences and
parameters defined in that subset. Additionally, it is stating that it has not violated the syntactic, semantic, and
pragmatic restrictions specified in PTOCA. There is no requirement that any control sequence or parameter
must appear in the object.
A receiver need not check the Presentation Text object for compliance to the PTOCA subset beyond what the
receiver requires for the processing that represents its function. A printer, for example, normally checks all the
control sequences, parameters, and ranges within the object it is presenting. But a transform product might
only need to check the structured field introducers. A receiver may optionally provide warnings if it detects non-
compliance.
General Requirements for Compliance [PTOCA-6-089]

<!-- Page 181 -->

Copyright © AFP Consortium 1997, 2025 163