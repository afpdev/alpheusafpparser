Chapter 6. Compliance
This chapter describes compliance rules for generators and receivers of BCOCA data structures.
Generator Rules
A compliant generator is any product that generates semantically and syntactically valid BSD and BSA data
structures as defined in Chapter 4, “BCOCA Data Structures”, . For each bar code symbology type
to be generated, one and only one BSD can be specified. For each BSD, zero or more BSAs can be defined to
generate zero or more bar code symbols of the same type within the bar code presentation space.
Receiver Rules
A compliant receiver is any product that receives and processes BCOCA data structures. A compliant receiver
must:
• Accept and validate all BCOCA data structure values defined in the BCD1 or BCD2 range [BCOCA-6-001]
• Detect and report to the controlling environment all exception conditions for supported values as defined in [BCOCA-6-002]
Chapter 5, “Exception Conditions”,
• Support and generate bar code symbols that conform to the bar code symbology specifications listed in [BCOCA-6-003]
Appendix A, “Bar Code Symbology Specification References”,
A compliant receiver may in addition receive and process any BCOCA data structure value not in BCD1 or
BCD2.




Copyright © AFP Consortium 1991, 2025 171
