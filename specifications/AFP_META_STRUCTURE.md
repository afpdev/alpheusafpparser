# AFP (MO:DCA) Meta-Structure Hierarchy

This table defines the nested meta-structure of a MO:DCA data stream (AFP) as specified in AFPC-0004-10 (MO:DCA Reference 10), Chapter 4.

## Component Hierarchy Legend
- **(S)**: Repeatable
- **[ ]**: Optional
- **+**: Can appear in any order relative to others with '+'
- **F2**: Format 2 Structured Field

| Hierarchy / Component | Acronym | Hex ID | Status | Notes |
| :--- | :---: | :---: | :---: | :--- |
| **Print File** | **BPF** | **D3A8A5** | **[Required]** | Optional as a pair with EPF. |
| &nbsp;&nbsp;&nbsp;&nbsp;[ Resource Group ] | [BRG] | [D3A8C6] | [Optional] | Inline resources for the print file. |
| &nbsp;&nbsp;&nbsp;&nbsp;[ Document Index ] | [BDI] | [D3A8A7] | [Optional] | Implicitly tied to the next document. |
| &nbsp;&nbsp;&nbsp;&nbsp;**Document (S)** | **BDT** | **D3A8A8** | **Required** | Highest level document component. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Metadata Container (S) ] | [BOC] | [D3A892] | [Optional] | MO Type only. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ Invoke Medium Map (S) ] | [IMM] | [D3ABCC] | [Optional] | Activates a Medium Map. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ Include Page (S) ] | [IPG] | [D3AFAF] | [Optional] | Includes a page resource. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ Link Logical Element (S) ] | [LLE] | [D3B490] | [Optional] | Defines linkages. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ Medium Map (S) ] | [BMM] | [D3A8CC] | [Optional] | Internal (inline) medium map. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ Resource Environment Group (S) ] | [BSG] | [D3A8D9] | [Optional] | Maps resources for following pages. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ **Page Group (S)** | **BNG** | **D3A8AD** | **[Optional]** | Named set of sequential pages. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Tag Logical Element (S) ] | [TLE] | [D3A090] | [Optional] | Attribute information. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Page / Resource Nesting ] | | | | Supports Page/REG/Medium Map/etc. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**End Page Group** | **ENG** | **D3A9AD** | **Required** | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ **Page (S)** | **BPG** | **D3A8AF** | **Required** | Independent presentation component. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Active Environment Group** | **BAG** | **D3A8C9** | **Required** | Defines page environment. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Presentation Env Control ] | [PEC] | [D3A7A8] | [Optional] | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Map Coded Font (S) ] | [MCF] | [D3AB8A] | [Optional] | Format 2 (F2). |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Map Data Resource (S) ] | [MDR] | [D3ABC3] | [Optional] | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Map Page ] | [MPG] | [D3ABAF] | [Optional] | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Map Page Overlay (S) ] | [MPO] | [D3ABD8] | [Optional] | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Map Page Segment (S) ] | [MPS] | [D3B15F] | [Optional] | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Page Descriptor** | **PGD** | **D3A6AF** | **Required** | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Object Area Descriptor ] | [OBD] | [D3A66B] | [Optional] | For text without OEG. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Object Area Position ] | [OBP] | [D3AC6B] | [Optional] | For text without OEG. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**Presentation Text Descriptor** | **PTD** | **D3B19B** | **Required** | Format 2 (F2). |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**End Active Env Group** | **EAG** | **D3A9C9** | **Required** | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ Include Object (S) ] | [IOB] | [D3AFC3] | [Optional] | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ Include Page Overlay (S) ] | [IPO] | [D3AFD8] | [Optional] | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ Include Page Segment (S) ] | [IPS] | [D3AF5F] | [Optional] | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ [ Tag Logical Element (S) ] | [TLE] | [D3A090] | [Optional] | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;+ **Data Object (S)** | | | **[Optional]** | Bar Code, Graphics, Image, Text, or Container. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Object Env Group ] | [BOG] | [D3A8C7] | [Optional] | Defines object area and mapping. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;[ Data ] | | | | PTX, GAD, IPD, BDA, OCD. |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**End Page** | **EPG** | **D3A9AF** | **Required** | |
| &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;**End Document** | **EDT** | **D3A9A8** | **Required** | |
| &nbsp;&nbsp;&nbsp;&nbsp;**End Print File** | **EPF** | **D3A9A5** | **[Required]** | Required if BPF is present. |

### Note on Resource Objects
Resource objects (Overlays, Page Segments, Fonts) follow similar structures to Pages but are wrapped in `Begin Resource (BRS)` and `End Resource (ERS)` when carried in a Print File level Resource Group.

- **Overlay (BMO/EMO)**: Identical structure to Page (AEG + Objects).
- **Page Segment (BPS/EPS)**: Minimal structure; contains peer Data Objects (GOCA, IOCA, BCOCA) without an AEG.
- **Object Container (BOC/EOC)**: Envelopes non-AFP data or specialty objects.
- **Form Map (BFM/EFM)**: Contains Document Environment Group (DEG) and Medium Maps.
