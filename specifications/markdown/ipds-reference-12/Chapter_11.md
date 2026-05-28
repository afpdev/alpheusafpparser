# Chapter 11. Metadata Command Set
The Metadata command set contains the IPDS commands for associating metadata with objects in the IPDS
data stream. This command set contains the following commands:
Table 52. Metadata Commands [IPDS-11-001]

| Command | Code | Description | In MO1 Subset? |
| :--- | :--- | :--- | :---: |
| DHM | X'D658' | “Delete Home-State Metadata” | Yes [IPDS-11-002]|
| WMC | X'D68A' | “Write Metadata control” | Yes [IPDS-11-003]|
| WM | X'D68B' | “Write Metadata” | Yes [IPDS-11-004]|
Metadata can be associated with many different objects in the IPDS data stream, in multiple ways. The
location of the WMC command in the data stream determines the object or objects the metadata is associated
with. For the details, see “Metadata”.
The host sends a Write Metadata control (WMC) command to the IPDS receiver to associate metadata with an
object or objects. The metadata is sent to the IPDS receiver in one or more Write Metadata (WM) commands.
For WMC commands received in home state, the metadata is added to the current home-state metadata; such
metadata is deleted with a Delete Home-State Metadata (DHM) command. [IPDS-11-005]


Delete Home-State Metadata
The Delete Home-State Metadata (DHM) command directs the printer to delete home-state metadata. The
home-state metadata had been previously added using a Write Metadata control (WMC) command received in
home state.
The home-state metadata to be deleted is determined based on the MDLevel specified. A special MDLevel
value, X'0000', is available to specify that all home-state metadata, at all levels, should be deleted.
```
Length X'D658' Flag CID Data
```
The length of the DHM command can be:
Without CID X'0009'
With CID X'000B'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The DHM command data field is as follows: [IPDS-11-006]

| Offset | Type | Name | Range | Meaning | Required |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | MDLevel | X'0000'<br>X'0001' – X'FFFF' | Used to delete all home-state metadata<br>Level of the home-state metadata being deleted | X'0000'<br>X'0001' – X'FFFF' [IPDS-11-007]|
| 2–3 | X'0000' | Reserved | X'0000' [IPDS-11-008]| | |
Bytes 0–1 MDLevel
These bytes specify the level of the home-state metadata to be deleted.
When the MDLevel is a value in the range X'0001' – X'FFFF', all home-state metadata that
was added with that level is deleted. If there is no existing home-state metadata at level
MDLevel, exception ID X'02D0..00' exists.
The MDLevel value X'0000' specifies to delete all existing home-state metadata at all levels. A
request to delete all home-state metadata when there is no home-state metadata to be
deleted is effectively a NOP .
Bytes 2–3 Reserved


Write Metadata control
Length X'D68A' Flag CID Data (MDD)
The length of the WMC command can be:
Without CID X'0005' or X'000D'–X'7FFF'
With CID X'0007' or X'000F'–X'7FFF'
However, the length of the MDD self-defining-field (if present) must also be valid. Exception ID X'0202..02'
exists if the command length is invalid or unsupported.
The Write Metadata control (WMC) command causes the IPDS receiver to enter metadata state. When
received in home state, parameters in this command can specify a metadata level for the metadata being
added.
The WMC command is followed by zero or more Write Metadata (WM) commands that contain the metadata
object. Metadata processing ends when the IPDS receiver receives an End command in the metadata state. If
not enough data is specified, exception ID X'0205..01' exists.
In many states, the WMC command(s) must immediately follow the “begin” command for the state. See
“Metadata” for more details. If a WMC is received in a correct state, but not immediately following
the begin command, exception ID X'8002..00' exists.
The WMC data field consists of one optional self-defining field:
• Metadata Data Descriptor (MDD) [IPDS-11-009]
The MDD contains a two-byte length field, a two-byte self-defining field ID, and a data field.
If an invalid self-defining field is specified, or the MDD appears more than once, exception ID X'020B..05'
exists.


Metadata Data Descriptor
The Metadata Data Descriptor (MDD) is the only self-defining field in the data portion of the WMC command.
This self-defining field specifies how a WMC received in home state is to be processed. When a WMC is
received in home state, the MDD is mandatory; if no MDD is present, exception ID X'020B..05' exists.
An MDD is optional, and is not expected to be included, in a WMC received in a state other than home state,
but for possible future enhancement, such an MDD should be tolerated by receivers without an exception.
The format of the MDD is as follows: [IPDS-11-010]

| Offset | Type | Name | Range | Meaning | MO1 Range |
| :--- | :--- | :--- | :--- | :--- | :--- |
| 0–1 | UBIN | Length | X'0008' to end of MDD | Length of MDD, including this length field | X'0008' to end of MDD [IPDS-11-011]|
| 2–3 | CODE | SDF ID | X'A6BC' | Self-defining-field ID | X'A6BC' [IPDS-11-012]|
| 4–5 | X'0000' | Reserved | X'0000' [IPDS-11-013]| | |
| 6–7 | UBIN | MDLevel | X'0001' – X'FFFF' | Level of the home-state metadata being added | X'0001' – X'FFFF' [IPDS-11-014]|
| 8 to end of MDD | UNDF | | | Data without architectural definition [IPDS-11-015]| |
Bytes 0–1 Self-defining-field length
Exception ID X'0202..05' exists if an invalid length value is specified.
Bytes 2–3 Self-defining-field ID
Bytes 4–5 Reserved
Bytes 6–7 MDLevel
These bytes specify the level of the home-state metadata being added.
This value can be any arbitrary value chosen by the host, and has no semantic meaning
whatsoever. The value is used only to be able to delete home-state metadata at the
appropriate time, without deleting other home-state metadata.
The level is stored with the metadata, for later use when a Delete Home-State Metadata
(DHM) is received. There is no limitation to how many different metadata objects can have the
same value of MDLevel.
If the MDLevel is specified as X'0000', exception ID X'02D0..01' exists.
Bytes 8 to end
of MDD
Data without architectural definition
This is a reserved field that might be used for future expansion. IPDS receivers should accept,
but ignore this field; generators should not specify this field. [IPDS-11-016]


Write Metadata
Length X'D68B' Flag CID Metadata object
The length of the WM command can be:
Without CID X'0005'–X'7FFF'
With CID X'0007'–X'7FFF'
Exception ID X'0202..02' exists if the command length is invalid or unsupported.
The Write Metadata (WM) command transmits metadata to the IPDS receiver. The data consists of one
metadata object (MO), as defined in the Metadata object Content Architecture Reference. Zero or more WM
commands follow the WMC command.
There are no restrictions on how much or how little data is sent to the IPDS receiver in a single WM command,
except for the 32K length limit of the command. The end result of concatenating all data received in all the WM
commands is the one MO.
The IPDS receiver must support at least the metadata object support defined by the MOCA MS1 subset. Refer
to the Metadata object Content Architecture Reference for a definition of the MS1 subset.
IPDS exception IDs of the form X'06nn..nn' exist when problems are found within the MO; refer to the Metadata
Object Content Architecture Reference for more information about MO syntax and exception conditions.
Note: Only Anystate commands are valid between concatenated WM commands; refer to Figure 45 for a list of Anystate commands. [IPDS-11-017]




