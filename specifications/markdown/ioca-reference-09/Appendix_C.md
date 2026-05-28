# Appendix C. IOCA Tile Resource
This appendix describes the IOCA Tile Resource. This resource is designed to allow images or image parts that are used multiple times in the same datastream to be downloaded to the receiver only once.
A tile resource is an IOCA tile, subject to the following rules and conditions:
• A tile resource can contain any parameter otherwise allowed within tiles, except the Include Tile parameter. If [IOCA-C-001]
a tile resource does contain the Include Tile parameter, exception EC-B811 (Inconsistent Include Tile) exists when the tile is included.
• The content of the Tile Position parameter in the tile resource is ignored. The receiver uses the Tile Position [IOCA-C-002]
parameter specified in the calling tile instead.
• If both the tile resource and the calling tile contain Transparency Masks, they are combined using the logical [IOCA-C-003]
AND operation. A point in the included tile is in the foreground if it is in foreground in both transparency masks. Otherwise, it belongs to the background.
• If only one of the two possible transparency masks is specified, it is used without changes. [IOCA-C-004]
• At inclusion time, the tile is treated just as if it were specified locally: the Tile Position parameter in the tile [IOCA-C-005]
resource is discarded and the transparency mask from the calling tile, if any, is combined with any transparency mask in the included tile. Finally, the included tile, minus the Tile Position and with the possibly changed or added transparency mask is treated as if it appeared instead of the Include Tile parameter in the calling tile.
• Any defaults are applied as if the tile were specified locally. [IOCA-C-006]
T able 30shows the structure of the tile resource.
Table 30. IOCA Tile Resource Structure X'8C' Begin Tile parameter X'B5' Tile Position parameter X'B6' Tile Size parameter [ X'95' Image Encoding parameter ] [ X'96' IDE Size parameter ] [ X'98' Band Image parameter ] [ X'9B' IDE Structure parameter ]
[ X'9F' External Algorithm Specification parameter (ignored) ] [ X'B7' Tile Set Color parameter ] [ Transparency Mask ] [ Image Data or Band Image Data (S) ] X'8D' End Tile parameter [IOCA-C-007]




