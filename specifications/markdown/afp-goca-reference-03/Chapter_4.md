# Chapter 4. Graphics Primitives and Attributes

This chapter describes:
*   Output primitives in general [GOCA-4-001]
*   Current position [GOCA-4-002]
*   The symbols used to draw characters, markers, and shading patterns in areas [GOCA-4-003]
*   The following output primitives and their associated attributes: [GOCA-4-004]
    *   Lines
    *   Areas
    *   Character strings [GOCA-4-005]
    *   Markers [GOCA-4-006]
    *   Images [GOCA-4-007]
*   Output primitive overflow [GOCA-4-008]

## Output Primitives

Output primitives are the basic element from which graphics pictures are built. They are drawn by one or more drawing orders containing the parameters that define the primitive. [GOCA-4-009]

Primitives also use the modal parameters called attributes associated with them, as well as the general drawing process controls. [GOCA-4-010]

The architecture defines exception conditions for invalid values of parameters within drawing orders and assigns exception condition codes, EC-xxxx, to these for reporting purposes. See “Drawing Order Exceptions” for details. [GOCA-4-011]

## Current Position

Current position is a position in Graphics Presentation Space (GPS) remembered by the drawing processor. Current position is updated by the drawing processor as each output primitive value is executed. It is maintained as an $(X_g, Y_g)$ coordinate value in GPS. With the drawing orders that are described in Chapter 7, “Commands and Drawing Orders”, this updating of current position can, in general, be implemented by replacing the old value of current position by an $(X_g, Y_g)$ coordinate from the order being executed. [GOCA-4-012]

Two alternative forms of each output primitive drawing order are provided, each with a different order code:
*   With the first form, all coordinates required to draw the output primitive are contained in the order itself. [GOCA-4-013]
*   With the second form, the current position is used as the first pair of coordinate values of the output primitive. [GOCA-4-014]

The second form of drawing order is shorter than the first form. The second form is used when the initial coordinate of an order is the current position as established by the previous order, and effectively connects the primitives together. [GOCA-4-015]

The drawing order Set Current Position is provided to manipulate current position. [GOCA-4-016]

Current position is set to the origin of GPS—that is, $(X_g=0, Y_g=0)$—at the beginning of each new segment and at the beginning of each new custom pattern definition. [GOCA-4-017]

---

## Symbols

Symbols are used to draw:
*   Characters [GOCA-4-018]
*   Markers [GOCA-4-019]
*   Shading patterns in areas [GOCA-4-020]

A particular symbol can be used as a character, as a marker, or as a pattern. [GOCA-4-021]

For character symbols, the controlling environment provides access to sets of symbols by resolving the local identifier of the character set. [GOCA-4-022]

When drawing character symbols, the concept of precision exists:
*   The minimum degree of accuracy required for the appearance of the symbols is determined by the value of the character precision attribute. [GOCA-4-023]
*   The method of defining a symbol does not limit the operations that can be applied to that symbol. Therefore, the method of symbol definition does not tie that symbol to a particular level of precision. An implementation can choose to support only certain precisions for particular types of symbol definition. Subsets may define what precision is required to be supported. [GOCA-4-024]
*   Precision and the method by which symbols are defined are independent of each other. [GOCA-4-025]

To draw a symbol, an $x,y$ position, a symbol set, and a code point are specified. [GOCA-4-026]

If the requested symbol set does not exist, the appropriate exception condition is raised. The standard action for this exception is to use the appropriate standard default set. [GOCA-4-027]

If the code point identifies a symbol that is not valid or not defined, the appropriate exception condition is raised. The standard action for this exception is to use the appropriate standard default symbol. [GOCA-4-028]

Markers, patterns, and characters are all examples of symbols. Parts of the loading mechanism and handling facilities are common to all types of symbols. [GOCA-4-029]

---

Table 7 summarizes how attributes are set when symbols are used for characters, markers, and patterns. [GOCA-4-030]

### Table 7. Setting Attributes for Character, Marker, and Pattern Symbols

| Attribute | Character Symbols | Marker Symbols | Pattern Symbols |
| :--- | :--- | :--- | :--- |
| **Color** | Set Color, Set Extended Color, or Set Process Color orders | Set Color, Set Extended Color, or Set Process Color orders | Set Color, Set Extended Color, or Set Process Color orders [GOCA-4-031]|
| **(Foreground) Mix** | Set Mix order | Set Mix order | Set Mix order [GOCA-4-032]|
| **Background Mix** | Set Background Mix order | Set Background Mix order | Set Background Mix order [GOCA-4-033]|
| **Precision** | Set Character Precision order | Reserved | Reserved [GOCA-4-034]|
| **Shear** | Set Character Shear order | Reserved | Reserved [GOCA-4-035]|
| **Angle** | Set Character Angle order | Reserved | Reserved [GOCA-4-036]|
| **Cell Size** | Set Character Cell order | Set Marker Cell order | Reserved [GOCA-4-037]|
| **Direction** | Set Character Direction order | Not applicable | Not applicable [GOCA-4-038]|
| **Set** | Set Character Set order | Set Marker Set order | Set Pattern Set order [GOCA-4-039]|
| **Code Point** | Character String order | Set Marker Symbol order | Set Pattern Symbol order [GOCA-4-040]|
| **Reference Position** | Character String order | Marker order | Set Pattern Reference Point order for a custom pattern; not applicable for a gradient; otherwise, device default [GOCA-4-041]|

In raster symbol definitions and in fully described and outline fonts, the foreground color of the symbol is always the current character color attribute value. [GOCA-4-042]

---

## Line Primitives

There are three types of line primitives:
*   Straight Lines [GOCA-4-043]
*   Curved Lines [GOCA-4-044]
*   Boxes

### Straight Lines

The following orders can be used to draw straight lines:
*   The **Line** order draws one or more contiguous straight lines by providing the endpoints of each line. [GOCA-4-045]
*   The **Relative Line** order draws one or more contiguous straight lines by using offset values. [GOCA-4-046]

#### Line Order
The Line order has two forms:
*   Line at Given Position (GLINE) order [GOCA-4-047]
*   Line at Current Position (GCLINE) order [GOCA-4-048]
Straight lines are drawn through the set of points specified as parameters of the order. In general, any number of points can be specified, provided the maximum length count on the order is not exceeded.
The current values of the line attributes are taken into account when the lines are drawn. Current position is set to the last point specified in the order. [GOCA-4-049]

#### Relative Line Order

The Relative Line order has two forms:
*   Relative Line at Given Position (GRLINE) order [GOCA-4-050]
*   Relative Line at Current Position (GCRLINE) order [GOCA-4-051]

The parameters of the order include an initial position, $(X_0, Y_0)$, and a set of offset values, $\{D_1, E_1\}, \dots, \{D_n, E_n\}$. The offsets are one-byte values that give the end point of a line relative to the start of that same line; that is, the differences in the $x,y$ coordinate values of the start and end points of the line. Negative values for these offsets are permitted. [GOCA-4-052]

Straight lines are drawn between the points $(X_0, Y_0), (X_0 + D_1, Y_0 + E_1), (X_0 + D_1 + D_2, Y_0 + E_1 + E_2), \dots, (X_0 + D_1 + \dots + D_n, Y_0 + E_1 + \dots + E_n)$. [GOCA-4-053]

The current values of the line attributes are taken into account when the relative lines are drawn. Current position is set to the last point calculated. [GOCA-4-054]

**Note:** The straight lines are drawn so that the line width is centered on the specified points. [GOCA-4-055]

---

### Curved Lines

Curved lines can be drawn using the following orders:
*   Full Arc [GOCA-4-056]
*   Partial Arc [GOCA-4-057]
*   Fillet [GOCA-4-058]
*   Cubic Bezier Curve [GOCA-4-059]

### Figure 6. Arc Parameters
```
       (0,1)            (R,Q)   (P+R, S+Q)
         +                +-------+
       /   \             /       /
(0,0) +-----+ (1,0)     +-------+
                        (0,0)   (P,S)

   Unit circle         General case
```

---

#### Full Arc

### Figure 7. Full Arc
An ellipse with semi-axes $B = Multiplier \cdot b$, where $b$ is defined by Arc Parameters. [GOCA-4-060]

Full Arc orders use the current value of the arc parameters to define the primitive. The arc parameters specify the required shape and size of an ellipse, which can be a circle. The arc parameters also specify the direction in which the ellipse is drawn: clockwise or counterclockwise. [GOCA-4-061]

The Set Arc Parameters order sets the current value of the arc parameters. The arc parameters are shown in Figure 6. [GOCA-4-062]

The parameters in the Set Arc Parameters order, $P, Q, R,$ and $S$, define a transformation that maps the unit circle to the required ellipse, placed at the origin (0,0):
$X' = P \cdot X + R \cdot Y$
$Y' = S \cdot X + Q \cdot Y$ [GOCA-4-063]

where $X$ and $Y$ are the coordinates of the points on the unit circle, and $X'$ and $Y'$ are the coordinates of the points on the defined ellipse. Note that the unit circle has a radius of 1 GPS unit. [GOCA-4-064]

If $P \cdot R + S \cdot Q = 0$, the transform is termed **orthogonal** and the line from the origin (0,0) to the point $(P,S)$ is either a radius of the circle, or half the major/minor axis of the ellipse. The line from the origin to the point $(R,Q)$ is either the radius of the circle, or half the minor/major axis of the ellipse. [GOCA-4-065]

If $P \cdot Q = R \cdot S$, the ellipse degenerates to a straight line or a point. If $P = Q = r$ and $R = S = 0$, the ellipse degenerates to a circle with radius $r$. [GOCA-4-066]

The direction that the circle or ellipse is drawn depends upon the determinant $P \cdot Q - R \cdot S$, as follows:
*   If $P \cdot Q - R \cdot S > 0$, the direction is counterclockwise. [GOCA-4-067]
*   If $P \cdot Q - R \cdot S < 0$, the direction is clockwise. [GOCA-4-068]

**Implementation Note:** For historical reasons, not all output devices have supported the directionality of arcs based on the determinant. However, all output devices that support the Cubic Bezier Curve (GCBEZ, GCCBEZ) drawing orders must support arc directionality based on the determinant. [GOCA-4-069]

---

The Full Arc order draws one complete circle, or a complete ellipse. The parameters of this order are the center point, and a multiplier that specifies by how much the ellipse or circle defined by the Set Arc Parameters order is to be scaled, before being drawn. The Full Arc circle or ellipse is drawn either in a clockwise or counterclockwise direction, depending on the direction of the ellipse as defined by the arc parameters. Figure 7 shows the generation of an ellipse. The small ellipse at the origin is defined by the Set Arc Parameters order with minor axis $2b$. The Full Arc drawing order transforms this ellipse into an ellipse with center at current position or a specified point, and with a multiplier such that the new minor axis $2B = Multiplier \cdot 2b$. The major axis is scaled in the same manner. [GOCA-4-070]

The current values of the line attributes are taken into account when each full arc is drawn. Current position is set to the center point of the arc. [GOCA-4-071]

In AFP environments, the standard default for the arc parameters is:
$P=Q=1$
$R=S=0$

**Note:** The parameter values are specified in GPS L-units. [GOCA-4-072]

#### Partial Arc

### Figure 8. Partial Arc
A line from current position to the start of an arc, followed by the partial arc defined by Start and Sweep angles. [GOCA-4-073]

The Partial Arc primitive draws a line from a specified point or current position to the start of an arc, and then draws the arc. [GOCA-4-074]

The arc is part of the full arc defined by the current arc parameters and the multiplier $M$. The center of the arc is at a point specified within the Partial Arc drawing order. The part of the arc that is drawn is defined by the start-angle and sweep-angle parameters. The direction of drawing is determined by the arc parameters. [GOCA-4-075]

The start angle is the angle between the X axis of the unit circle space and the radius drawn from the center of the arc to the start point of the arc. The sweep angle is the angle subtended at the center of the arc by the two radii drawn from the center of the unit circle to the start and end points of the arc; see Figure 8. [GOCA-4-076]

---

Both angles are specified in the unit-circle space, and hence are transformed by an amount defined by the current arc parameters in the same way that the unit circle is transformed. If the partial arc is part of a circle, the angles following the transform will be the same as the angles on the unit circle. If the partial arc is part of an ellipse, the angles following the transform will, in general, be different than the angles on the unit circle. [GOCA-4-077]

The Partial Arc order can draw arcs with sweep angles greater than or equal to 0° and less than 360°; it cannot draw a complete 360° arc. The Full Arc drawing order can be used to draw a complete arc. [GOCA-4-078]

The current values of the line attributes are taken into account when the partial arc is drawn. Current position is set to the endpoint of the arc. [GOCA-4-079]

#### Fillet

### Figure 9. Fillet
A curve tangential to straight lines $P_0-P_1, P_1-P_2, \dots$ [GOCA-4-080]

This primitive is drawn using the Fillet order. The parameters of the order are the $(X_g, Y_g)$ coordinates for a set of points $P_0, P_1, \dots, P_n$. [GOCA-4-081]

The points specified in the order are joined by conceptual straight lines, to which a curve is fitted. The curve is tangential to the first line at its start point, and to the last line at its end point. If there are more than two lines, the curve is tangential to the intermediate lines at their center points. If only two points are supplied, a straight line is drawn between the points. [GOCA-4-082]

**Architecture Note:** The Fillet drawing order does not support specification of a sharpness parameter. In Figure 9, this parameter would determine how close the drawn curve comes to the points $P_1, P_2,$ and $P_3$. If a quadrant of a circle or quadrant of an ellipse is used to fit the points, the sharpness parameter is not required since the circle or ellipse is completely defined by completing the parallelogram. If a quadrant arc is not used, a sharpness parameter can be used and is defined, in reference to Figure 9, as follows: [GOCA-4-083]

1.  Generate the virtual line $P_0M_1$ [GOCA-4-084]
2.  Find the midpoint of this line, $V_0$ [GOCA-4-085]
3.  Generate $V_0P_1$ [GOCA-4-086]
4.  Call the point where $V_0P_1$ intersects the arc $D_1$ [GOCA-4-087]
5.  The sharpness parameter is defined to be the ratio of $V_0D_1 \div D_1P_1$ [GOCA-4-088]

The recommended value for the sharpness parameter, when used in AFP GOCA, is 0.7. [GOCA-4-089]

The current values of the line attributes are taken into account when the fillet is drawn. Current position is set to the last point specified. [GOCA-4-090]

---

The curve that is drawn is computed as follows (see Figure 9): [GOCA-4-091]

1.  Let the points specified in the order be known as $P_0, P_1, \dots, P_n$. [GOCA-4-092]
2.  Conceptual lines are drawn between the points $P_0$ to $P_1, $ $P_1$ to $P_2, $ $P_2$ to $P_3,$ and so on. [GOCA-4-093]
3.  The midpoints of the lines from $P_1$ to $P_2, $ $P_2$ to $P_3, \dots, P_{n-2}$ to $P_{n-1}$ are computed; call these $M_1, M_2, \dots, $ $M_{n-2}$. [GOCA-4-094]
4.  The points $P_0, P_1, M_1, P_2, M_2, P_3, \dots, M_{n-2}, P_{n-1}, P_n$ are then considered three at a time, starting with $P_0, P_1, M_1$. A quadrant of a circle is scaled, and can be distorted to become a part of an ellipse, in order that the curve be tangential to the line $P_0-P_1$ at the point $P_0$, and tangential to the line $P_1-M_1$ at the point $M_1$. The center point of the ellipse is the point obtained by completing the parallelogram defined by the sides $P_0-P_1$ and $P_1-M_1$. [GOCA-4-095]
5.  The next three points are considered, that is $M_1, P_2, M_2$ and a quadrant of a circle is transformed into part of an ellipse that is tangential to the line $M_1-P_2$ at $M_1$, and tangential to the line $P_2-M_2$ at $M_2$. [GOCA-4-096]
6.  This process continues, with part of an ellipse being fitted to three points in turn, until the last three points $M_{n-2}, P_{n-1}, P_n,$ have been incorporated; see Figure 9. [GOCA-4-097]

**Note:** If all the points $P_0$ through $P_n$ are within the GPS, the actual fillet does not go outside the GPS. [GOCA-4-098]

#### Cubic Bezier Curve

### Figure 10. Cubic Bezier Curve
A polycurve formed by overlapping sets of four points. [GOCA-4-099]

A Cubic Bezier curve primitive is drawn by executing a Cubic Bezier Curve order. The parameters of the order are a set of points $P_0, P_1, \dots, P_n$. These points are considered in sets of four, the first being $P_0, P_1, P_2,$ and $P_3$. The second set is $P_3, P_4, P_5,$ and $P_6$; that is, the set overlaps at $P_3$ with the first set. This process continues, each set overlapping the previous set by one point, up to the final set, which is $P_{n-3}, P_{n-2}, P_{n-1},$ and $P_n$. [GOCA-4-100]

An exception condition, EC-0003, occurs if the length of the order does not provide a number of points equal to $3m + 1$, where $m$ is the number of sets. The number of points provided includes the current position when the order is Cubic Bezier Curve at Current Position. [GOCA-4-101]

A curve is drawn independently for each set of four points. It is computed as follows: [GOCA-4-102]

1.  Let the first set of points be labeled $P_0, P_1, P_2,$ and $P_3$. The curve is drawn from $P_0$ to $P_3$ and is tangential to $P_0P_1$ and $P_2P_3$; see Figure 10. [GOCA-4-103]
2.  The curve drawn is defined by the parametric equations: [GOCA-4-104]
    $x(t) = P_x \cdot t^3 + Q_x \cdot t^2 + R_x \cdot t + P_{0x}$
    $y(t) = P_y \cdot t^3 + Q_y \cdot t^2 + R_y \cdot t + P_{0y}$
    where $t$ takes values from 0 to 1. [GOCA-4-105]

---

    $P_{0x}$ and $P_{0y}$ are the $x$ and $y$ coordinates of point $P_0$.
    $P_x, Q_x,$ and $R_x$ are given by the following formulas:
    $P_x = P_{3x} - 3 \cdot P_{2x} + 3 \cdot P_{1x} - P_{0x}$
    $Q_x = 3 \cdot P_{2x} - 6 \cdot P_{1x} + 3 \cdot P_{0x}$
    $R_x = 3 \cdot P_{1x} - 3 \cdot P_{0x}$ [GOCA-4-106]

    **Note:** $P_{1x}$ and $P_{1y}$ are the $x$ and $y$ coordinates of point $P_1$, etc. [GOCA-4-107]

3.  $P_y, Q_y,$ and $R_y$ are given by the same formulas, but using the $y$ coordinates of the points $P_0, P_1, P_2,$ and $P_3$ instead of the $x$ coordinates. [GOCA-4-108]

The choice of control points for the curves determine whether there is a smooth transition from one curve to the next. A smooth transition requires that two curves have the same slope at their connecting point. To ensure that the curves drawn have the same slope at the connecting point, the second control point of the previous curve and the first control point of the new curve must be collinear with the common point of the two curves. In Figure 10, for example, $P_2$ (second control point of the first curve), $P_3$ (common point), and $P_4$ (first control point of the new curve) are on the same line resulting in a smooth transition, whereas $P_5$ (second control point of the second curve), $P_6$ (common point), and $P_7$ (first control point of the third curve) are not collinear, resulting in the two curves joining at an angle. [GOCA-4-109]

#### Boxes

### Figure 11. Box
A rectangular box with optional rounded corners. [GOCA-4-110]

The Box order has the two forms:
*   Box at Given Position (GBOX) [GOCA-4-111]
*   Box at Current Position (GCBOX) [GOCA-4-112]

The Box order draws a rectangular box with corners that are either square, quadrants of a circle, or quadrants of an ellipse. [GOCA-4-113]

The parameters on the order are:
*   The corner positions of a rectangle. There are two corners specified; in the case of the GCBOX order, one of the corners is the current position. [GOCA-4-114]
*   The lengths of the horizontal and vertical axes of an ellipse. [GOCA-4-115]

---

If the lengths of the axes are:
*   Both omitted, or either is specified as zero, square corners are drawn. [GOCA-4-116]
*   Equal and nonzero, each corner is a quadrant of a circle. [GOCA-4-117]
*   Not equal and nonzero, each corner is a quadrant of an ellipse. [GOCA-4-118]

The current values of the line attributes, except for line join, are taken into account when the box is drawn. The line end attribute is used only for the internal ends of dotted or dashed lines. [GOCA-4-119]

Current position is set to the first corner specified for the GBOX order, or is unchanged for the GCBOX order. [GOCA-4-120]

### Line Attributes

Table 8 shows the attributes controlling the drawing of line primitives, that is, straight lines, curved lines, and boxes. [GOCA-4-121]

### Table 8. Attributes Controlling Line Primitives

| Attribute | Standard Default | Length (in bytes) | Meaning |
| :--- | :--- | :--- | :--- |
| **LINE TYPE** | Solid (X'07') | 1 or 4n | Specification of type of line [GOCA-4-122]|
| **LINE WIDTH** | Normal (X'0100') | 2 | Specification of line width as fractional multiplier of normal line width [GOCA-4-123]|
| **NORMAL LINE WIDTH** | Device dependent | 2 | Specification of normal line width in 1440ths of an inch [GOCA-4-124]|
| **LINE END** | Round (X'03') | 1 | Specification of line end [GOCA-4-125]|
| **LINE JOIN** | Round (X'02') | 1 | Specification of line join [GOCA-4-126]|
| **COLOR** | Device dependent | 2 | Color value set into GPS for foreground [GOCA-4-127]|
| **PROCESS COLOR** | Device dependent | 12–14 | Process color value set into GPS for foreground [GOCA-4-128]|
| **MIX** | Overpaint (X'02') | 1 | Specification of Mix mode in GPS for foreground [GOCA-4-129]|

#### Line Type

The line type attribute controls the type of line used to draw lines.
The line type is defined as a series of dots and dashes. As lines are drawn into the GPS, the line type is used repetitively to determine which parts of the line are drawn into the GPS:
*   The dots and dashes are drawn. [GOCA-4-130]
*   The spaces between the dots and dashes are not drawn and have no effect on the GPS. [GOCA-4-131]

---

The sequence of line type dots and dashes is not reset, except by a Move Type order, which is an order that causes current position to be updated to a new value specified in the order before anything is drawn. Move Type orders are defined in Table 9. [GOCA-4-132]

### Table 9. Move Type Orders

| Description | Orders |
| :--- | :--- |
| Any straight or curved line order that explicitly specifies the starting point of the line that is to be drawn | • Box at Given Position (GBOX)<br>• Cubic Bezier Curve at Given Position (GCBEZ)<br>• Fillet at Given Position (GFLT)<br>• Full Arc at Given Position (GFARC)<br>• Line at Given Position (GLINE)<br>• Partial Arc at Given Position (GPARC)<br>• Relative Line at Given Position (GRLINE) [GOCA-4-133]|
| Orders that explicitly or implicitly set current position | • Set Current Position (GSCP) [GOCA-4-134]|
| Other orders that specify an initial position | • Begin Image at Given Position (GBIMG)<br>• Character String at Given Position (GCHST)<br>• Marker at Given Position (GMRK) [GOCA-4-135]|

The line type attribute can be set to a standard or a custom value. Standard values are predefined line types and are set with the Set Line Type drawing order. Custom values are arbitrary, user-defined line types and are set with the Set Custom Line Type drawing order.
The standard line types are defined as follows: [GOCA-4-136]

| Attribute | Meaning |
| :--- | :--- |
| X'00' | Drawing Default [GOCA-4-137]|
| X'01' | Dotted Line [GOCA-4-138]|
| X'02' | Short-dashed Line [GOCA-4-139]|
| X'03' | Dash-dot Line [GOCA-4-140]|
| X'04' | Double-dotted Line [GOCA-4-141]|
| X'05' | Long-dashed Line [GOCA-4-142]|
| X'06' | Dash-double-dot Line [GOCA-4-143]|
| X'07' | Solid Line [GOCA-4-144]|
| X'08' | Invisible Line [GOCA-4-145]|

When the line type attribute is set to invisible, current position is updated, but nothing is drawn into the GPS.
The exact appearance of the standard line types is implementation dependent. For consistent appearance of the standard line types, the following guideline should be used. The guideline defines the line types in terms of drawing the dashes and dots and moving over the spaces between them. The lengths are expressed in units of line width. The first number is the length of the first dash or dot in the sequence, and the second is the length of the move that follows. Further pairs of numbers, defining the dash, dot, and move lengths, are defined for the more complex line types.
The guidelines for generating the standard line types are as follows: [GOCA-4-146]

| Attribute | Sequence (Dash/Move pairs) |
| :--- | :--- |
| X'01' | 0, 2 [GOCA-4-147]|
| X'02' | 3, 3 [GOCA-4-148]|
| X'03' | 6, 4, 0, 4 [GOCA-4-149]|
| X'04' | 0, 3, 0, 7 [GOCA-4-150]|
| X'05' | 8, 3 [GOCA-4-151]|
| X'06' | 6, 3, 0, 3, 0, 3 [GOCA-4-152]|

---

**Note:** Because the size of a dot is zero, it is only visible because of the two concatenated line ends, and will disappear when flat endings are chosen.
A Dash-dot Line with different line endings is illustrated by Figure 12. [GOCA-4-153]

### Figure 12. Example of the Dash-dot Line Type
Dash-dot Line (6,4,0,4)
Flat, Round, Square
$W, 6 \cdot W, 4 \cdot W, 4 \cdot W, 0 \cdot W$ [GOCA-4-154]

A custom line type is a sequence of pairs $d,m$, where $d$ is the length of the dash and $m$ is the length of the move that follows. The lengths are expressed in units of line width. This follows the same format as shown above for the standard line types. A zero value for $d$ indicates a dot. If all values $d$ and $m$ are zero, a solid line is drawn.
Unusual results can occur when small move values (for example, $m \le 1$) are specified in custom line types; see Figure 13. A “dashed” line might end up looking partially or completely solid, possibly with “notches” on the edges or “barbs” protruding. Such lines, however, should be drawn exactly as the custom line type directs, and should not be “smoothed” or otherwise made more pleasing.
It is not possible to use the Set Current Defaults instruction to set a custom line type as a default.
There is only one line type attribute. It has a current value that is either a standard value or a custom value, depending on which drawing order (Set Line Type or Set Custom Line Type) was specified last. [GOCA-4-155]

### Figure 13. Custom Line Type with Small Move Value (with Different Line Ends)
Flat Round Square
Custom dashed line (6, 0.5) [GOCA-4-156]

---

#### Line Width

The current line width attribute controls the width of line used to draw lines.
The line width attribute consists of an integral and fractional part. When only the integral part is set by a Set Line Width order, the fractional part is reset to zero.
The value of the Line Width attribute specifies a multiplier of the normal line width.
The normal line width may be set as a default to an absolute value in 1440ths of an inch in the Set Current Defaults instruction in the data descriptor. In this case, to determine the line width in output-medium pels, first the line width multiplier is determined. An implementation then multiplies the line width multiplier by the absolute value normal line width and selects the nearest supported value in output-medium pels.
If not set to an absolute value, the normal line width and all multiples of the normal line width are device dependent (although see the Implementation Note below for a recommendation).
In any case, if the calculated line width exceeds the maximum supported by the device, that maximum is used. [GOCA-4-157]

| Attribute | Meaning |
| :--- | :--- |
| X'0000' | Drawing Default. The value of the attribute when the graphics processor was invoked. This value was set either by the Set Current Defaults instruction in the Graphics Data Descriptor or by the controlling environment. [GOCA-4-158]|
| X'0100' | Normal line width (multiplier of 1). [GOCA-4-159]|
| X'nnnn' | Multiplier. The high-order byte is an integral multiplier of the normal width, and the low-order byte is a fractional multiplier. [GOCA-4-160]|

**Architecture Note:** The line width should be scaled when the controlling environment specifies a scaling mapping of the GPS window into the usable area (object area). [GOCA-4-161]

**Implementation Note:** If a normal line width is not specified with the Set Current Defaults instruction (SET = X'11'), the following algorithm is recommended for generating line widths.
Line width (in inches) = $MH.MFR \cdot 1/120$ inches = $MH.MFR \cdot 0.00833$ inches
If resulting line width < 1 pixel, set line width to 1 pixel
If resulting line width > 0.13 inches, set line width to 0.13 inches
The term $MH.MFR$ is a decimal number consisting of the integral line width multiplier followed by the fractional line width multiplier. For example, if $MH = 6$ and $MFR = 1/4, MH.MFR = 6.25$. [GOCA-4-162]

#### Line End and Line Join

The line end attribute defines the shape used at the start and end of contiguous lines that are drawn by a set of straight or curved lines.
The line join attribute defines the shape used for the junction between contiguous lines. This attribute is used to define the join between lines in the following instances:
*   At the intermediate points within output primitives that contain multiple lines. [GOCA-4-163]
*   At the junction that occurs between the end point of a line primitive and the start point of a following line primitive specifying at current position, except when any of the following orders occur between the two primitives: [GOCA-4-164]
    *   A Move Type order. See Table 9 for the definition of a Move Type order. [GOCA-4-165]

---

    *   A Set of any Line attribute. [GOCA-4-166]
    *   A Begin Area or End Area order. [GOCA-4-167]
*   At the junction between the start point of a closed figure within an area and the end point of the closed figure. [GOCA-4-168]
See “Areas” for the definition of a closed figure.
The line end attribute is used to define the outline of all other line end points. [GOCA-4-169]

**Notes:**
1. Except as detailed in 2 below, the line end attribute is not applicable to Full Arc or Box as they are closed figures. [GOCA-4-170]
2. The line end attribute applies only to the extreme ends of a set of contiguous lines, and if the line type is dotted or dashed, the internal ends of the dots and dashes. [GOCA-4-171]
3. Line ends are drawn only if the end point is visible, as indicated by the current line type attribute. [GOCA-4-172]
4. Line joins are drawn only if the junction point and the adjacent points of both lines are visible. [GOCA-4-173]
5. Line joins are not applied to the corners of the Box figure. [GOCA-4-174]

##### Line End

The line end attribute defines the shape used for the outline of the start and end of contiguous lines, drawn by one or more Line primitives. If the line is not solid, it also defines the shape used for the internal ends of the dots and dashes.
The attribute can have the following values: [GOCA-4-175]

| Attribute | Meaning |
| :--- | :--- |
| X'00' | Drawing Default. The value of the attribute when the graphics processor was invoked. This value was set either by the Set Current Defaults instruction in the Graphics Data Descriptor or by the controlling environment. [GOCA-4-176]|
| X'01' | **Flat.** The boundary of the end is formed by truncating a line at the end point, perpendicular to the line direction. [GOCA-4-177]|
| X'02' | **Square.** The boundary of the end is formed by extending the line, in the line direction at the end point, by half the line width. This extension is then truncated with a line perpendicular to the line direction. **Note:** The effect is to place a rectangle on the end, even if the line is curved. [GOCA-4-178]|
| X'03' | **Round.** The boundary of the end is formed by a semicircle, centered at the end point, with a radius of half the line width. [GOCA-4-179]|

---

Flat, square, and round line-end shapes are illustrated in Figure 14. [GOCA-4-180]

### Figure 14. Line End Shapes
Geometric point of line end
Flat, Round, Square
Outline of end shape

##### Line Join

The line join attribute defines the shape used for the outline of the junction between contiguous lines.
The attribute can have the following values: [GOCA-4-181]

| Attribute | Meaning |
| :--- | :--- |
| X'00' | Drawing Default. The value of the attribute when the graphics processor was invoked. This value was set either by the Set Current Defaults instruction in the Graphics Data Descriptor or by the controlling environment. [GOCA-4-182]|
| X'01' | **Bevel.** The outline of the join is formed by closing the notch between the lines with a straight line. The effect, if the lines are at an angle to each other, is of a beveled corner on the outside of the join. [GOCA-4-183]|
| X'02' | **Round.** The outline of the join is formed by closing the notch between the lines with a circular arc of radius half the line width. The effect, if the lines are at an angle to each other, is of a rounded corner on the outside of the join. [GOCA-4-184]|
| X'03' | **Miter.** The outline of the join is formed by projecting the inner and outer edges of the two lines until they meet at an angle. The effect of the mitered join is to close the notch on the outside of the join with the quadrilateral formed by the ends of the lines and the extended outer edges of the two lines. The mitered join has no effect on the inside of the join. At any given join, the miter length is the distance from the point at which the inner edges of the lines intersect to the point at which the outer edges of the lines intersect; that is, the diagonal of the miter. This distance increases as the angle between the lines decreases. [GOCA-4-185]|

When the joining lines connect at a sharp angle, a miter join results in a spike that extends well beyond the connection point. To avoid exceptionally long spikes when lines join at sharp angles, a bevel join is used instead of a miter. The angle at which conversion from a miter join to a bevel join takes place is the angle at which the ratio of the miter length to the line width exceeds the value 10; this is approximately 11 degrees. [GOCA-4-186]

---

Bevel, round, and miter line-join shapes are illustrated in Figure 15. [GOCA-4-187]

### Figure 15. Line Join Shapes
Geometric point of line join
Bevel, Round, Miter
Outline of join shape
Miter length

**Note:** When non-solid lines are joined and flat line ends are used, the resulting corner can look strange. For example, with a dashed line that joins another dashed line at an angle, the corner will look different depending on how much of a dash bends around the corner; notches can appear when one of the line segments is shorter than its width, see Figure 16 (Dash-dot line, Flat endings). It is not necessary for an implementation to attempt to compensate for these inherent problems. [GOCA-4-188]

### Figure 16. Miter Line Joins in Combination with Line Types and Line Ends
Dash-dot Line, Dotted Line
Round, Flat, Square

---

## Areas

Areas are two-dimensional, composite primitives defined within a Begin Area/End Area bracket. An area is defined by its boundary, which is filled with a shading pattern.
An area definition may start in one segment and be completed in an appended segment.
The boundary of an area is defined as one or more closed figures that are either constructed or complete; see Figure 17. An example of a complete figure is one defined by the Full Arc order. Each constructed figure consists of a set of straight and curved lines connected together. These lines can be drawn if required. [GOCA-4-189]

### Figure 17. Closed and Open Figures
Open constructed, Closed constructed, Closed complete [GOCA-4-190]

The following description refers to a Move Type order. This term refers to the type of order that causes current position to be updated to a new value specified in the order before anything is drawn. See Table 9 for a list of Move Type orders.
The first constructed figure in an area is defined as starting at the Begin Area order. It is delimited either by an End Area order, or by any Move Type order that is valid in an area definition, which implies the start of a new closed figure. See “Valid Area Definitions” for a list of orders that are valid in an area definition.
Implementations can choose to allow complete figures, such as Full Arc at current position, within a constructed figure.
Each figure in an area must be properly closed, that is, its start and end points must be identical. If the points are not identical, the figure is closed arbitrarily with a straight line connecting the start and end points. The current position is set to the start point of the figure. [GOCA-4-191]

**Implementation Note:** If the Begin Area order specifies that the boundary is to be drawn, and if the area is not properly closed, the generated line to close the figure may or may not be drawn; this is presentation device dependent. The architecture recommendation is that this line not be drawn. If the Begin Area order specifies that the boundary is not to be drawn, the generated line to close the figure is not drawn. [GOCA-4-192]

The figures formed in this way jointly define the area boundary. The interior of the area is shaded using the values of the pattern attributes that were current when the Begin Area order was executed. [GOCA-4-193]

---

The interior of the area can be defined in one of two ways, as specified on the Begin Area order (see Figure 18):
*   **Alternate mode** [GOCA-4-194]
    Whether any point is within the area interior is determined by drawing a conceptual line from that point to infinity, without crossing any vertices. If this line crosses the area boundary an odd number of times, the point is in the area interior and the region containing that point is shaded. When counting line crossings, coincident boundary lines are all counted. Regions with an even number of line crossings from infinity are not shaded.
*   **Nonzero Winding mode** [GOCA-4-195]
    Nonzero Winding mode is similar to alternate mode, in that whether any point is within the area interior is determined by counting the number of times a conceptual line crosses the area boundary. With this mode, however, the direction of the boundary lines is taken into account. Using the same conceptual line, the number of crossings is counted, but boundary lines going in one direction—for example, right-to-left—count as +1, while lines going the other direction—left-to-right—count as -1. The original point is then considered to be inside the interior if the final count is nonzero. Thus a region with a nonzero number of line crossings from infinity is shaded, and a region with a zero number of line crossings from infinity is not shaded. [GOCA-4-196]

**Implementation Note:** It is strongly recommended that implementations that support Nonzero Winding mode also support directionality of full and partial arcs, and directionality of boxes. [GOCA-4-197]

### Figure 18. Determining the Interior of an Area
Alternate mode, Nonzero Winding mode [GOCA-4-198]

The area is filled with the pattern specified by the pattern set and pattern symbol attributes that were current when the Begin Area order was executed. If no such set is available, exception condition EC-6803 is raised, the standard action for which is to use the standard default pattern set. If the code point is undefined in the specified pattern set, exception condition EC-6804 is raised, the standard action for which is to use the standard default pattern symbol. In AFP environments, this is X'10'—Solid fill. [GOCA-4-199]

Logically, an area is constructed as follows:
1. When an End Area order is executed, the closed figures within the area are filled. The values of the pattern set, pattern symbol, pattern mix, and pattern background mix attributes that were current when the Begin Area order was executed are used in generating the fill pattern. When using the default pattern set or a bilevel custom pattern (but not when using a full-color custom pattern or gradient), the value of the pattern color attribute that was current when the Begin Area order was executed is also used in generating the fill pattern. When using a custom pattern, the value of the pattern reference point attribute that was current when the Begin Area order was executed is also used in generating the fill pattern. After the End Area order is executed, the current pattern color, pattern mix, and pattern background mix attributes are updated to reflect any change in the color, mix, and background mix attributes that may have been specified inside the area definition. [GOCA-4-200]

---

2. If indicated by the Begin Area order, the area boundary is drawn in the GPS in the sequence that the drawing orders that define the boundary are executed. The boundary lines are drawn using the values of the Line attributes that are current at the time the orders defining the boundary are executed. [GOCA-4-201]

If no boundary lines are drawn, or the line type specified is invisible, the boundary acts as if it were drawn as a zero-width line.
If two areas are defined that are adjacent to each other, that is, they have at least one boundary line in common, the fill patterns used in the two areas can overlap at the boundary. It is also permissible to overlap the boundary with the fill pattern. Drawing boundaries with solid lines and a mix value of Overpaint ensures consistent results.
The value of the current position is not changed by the Begin Area order itself, but is changed by those orders that are used to define the area boundary. [GOCA-4-202]

#### Valid Area Definitions

An exception condition, EC-6801, occurs if the Begin Area and End Area orders delimiting an area definition are not both in the same segment. Note that an appended segment is part of the segment that it appends. Area orders cannot be nested.
Only the following orders are allowed between the Begin Area order and its corresponding End Area order:
*   Box
*   Comment [GOCA-4-203]
*   Cubic Bezier Curve [GOCA-4-204]
*   Fillet [GOCA-4-205]
*   Full Arc [GOCA-4-206]
*   Line
*   No-Operation [GOCA-4-207]
*   Partial Arc [GOCA-4-208]
*   Relative Line [GOCA-4-209]
*   Set Arc Parameters [GOCA-4-210]
*   Set Background Mix [GOCA-4-211]
*   Set Color [GOCA-4-212]
*   Set Current Position [GOCA-4-213]
*   Set Custom Line Type [GOCA-4-214]
*   Set Extended Color [GOCA-4-215]
*   Set Fractional Line Width [GOCA-4-216]
*   Set Line End [GOCA-4-217]
*   Set Line Join [GOCA-4-218]
*   Set Line Type [GOCA-4-219]
*   Set Line Width [GOCA-4-220]
*   Set Mix [GOCA-4-221]
*   Set Process Color [GOCA-4-222]

Supported orders other than those listed above raise exception condition EC-6802.
**Note:** The Marker and Character String orders are not permitted as part of an area definition. [GOCA-4-223]

---

## Patterns

A pattern is used to fill the interior of an area and is created by selecting a symbol using the pattern set and pattern symbol attributes. For patterns that are not gradients, this symbol is repeated, both in the horizontal and vertical directions, to fill the interior of an area. There are three types of patterns in AFP GOCA:
*   Patterns in the default pattern set [GOCA-4-224]
*   Custom patterns [GOCA-4-225]
*   Gradients [GOCA-4-226]

Note that for all three types of patterns, a Pattern Symbol attribute value of X'00' selects the drawing default symbol. If no drawing default symbol is specified in the graphics data descriptor, the presentation default in AFP GOCA is solid fill, which is pattern symbol X'10' in the default pattern set. [GOCA-4-227]

### Patterns in the Default Pattern Set

The default pattern set contains predefined patterns. Figure 19 shows representative patterns corresponding to attribute values X'01' (1) to X'10' (16) in the default pattern set. A pattern set attribute value of X'00' specifies to use the default pattern set.
Note also that in the default pattern set, a pattern symbol attribute value of X'40' (blank) is treated the same as an attribute value of X'0F' (no fill). [GOCA-4-228]

---

### Figure 19. Default Pattern Set

**Implementation Note:** Implementation differences with respect to dot size, dot spacing, line thickness, and line spacing exist. In particular, some printers use the same line thickness and line spacing for pattern #10 as for pattern #9. However, all future presentation devices should try to replicate the published patterns as closely as possible. [GOCA-4-229]

---

**Architecture Note:** The precise appearance of the patterns in Figure 19 in this edition of this Reference do not exactly match earlier editions of this Reference, although differences are very slight. Any differences are unintentional and do not constitute a changed definition of the patterns. [GOCA-4-230]

### Custom Patterns

Custom patterns can be used to fill areas with any arbitrary pattern. A custom pattern is defined using GOCA drawing orders such that almost any GOCA picture can be used as a custom pattern.
The Begin Custom Pattern drawing order starts the definition of a custom pattern, with a matching End Custom Pattern drawing order ending the definition. The GOCA drawing orders between the Begin Custom Pattern and End Custom Pattern draw a picture, some window of which is the custom pattern to be used to fill areas. The edges of the window are specified in the Begin Custom Pattern drawing order.
A custom pattern definition may start in one segment and be completed in an appended segment.
Once a custom pattern has been defined in a segment, it can then be used to fill areas within that segment (including subsequent appended segments). Implementations must maintain the custom pattern definition throughout the segment, unless it is deleted using the Delete Pattern drawing order. The custom pattern cannot be used outside of the segment in which it is defined.
Once a custom pattern has been defined, it can be deleted using the Delete Pattern drawing order. After the Delete Pattern executes, the deleted custom pattern is no longer available for area fill.
As with any pattern, a custom pattern can be set as the default pattern using the Set Current Defaults instruction. [GOCA-4-231]

#### Custom Pattern Mode

When a Begin Custom Pattern drawing order is encountered, custom pattern mode is entered. While in custom pattern mode, all drawing is done in a separate, temporary GPS, and no drawing is done in the current GPS containing the picture corresponding to the current GOCA object—for discussion purposes, the GPS containing the picture drawn by the current GOCA object will be called the object GPS. The word “temporary” is used since once the End Custom Pattern drawing order is encountered, there is no way to draw into that GPS anymore. Note, however, that when drawing into the temporary GPS, all normal GOCA rules apply, such as raising an exception condition if drawing is attempted outside the GPS.
When a custom pattern is used to fill an area, the window from the temporary GPS is mapped one-to-one into the object GPS. For example, if the pattern window is of size 200x300 in the temporary GPS, it will be drawn at size 200x300 in the object GPS. The custom pattern will be tiled in all directions with the pattern reference point as origin, using as many tiles or partial tiles as are necessary to fill the area in question. The pattern reference point can be set using the Set Pattern Reference Point drawing order.
Before custom pattern mode is entered, all current attributes and the current position must be saved away by the drawing processor and then later restored when custom pattern mode is exited. In this way, adding a custom pattern definition into an existing GOCA segment will not change the picture drawn by that segment in any way (assuming the custom pattern is not used, of course).
Similarly, when custom pattern mode is entered, the current attributes and current position are set by the drawing processor to the drawing defaults—this is the same processing as is done at the beginning of a new segment. In this way, moving a custom pattern definition from one place in a segment to another will not affect the custom pattern definition in any way, since the custom pattern inherits nothing from the surrounding segment. The initialization when entering custom pattern mode is not affected by the presence of a segment prolog in the segment containing the custom pattern definition. [GOCA-4-232]

---

#### Custom Pattern Color Aspects

There are two types of custom patterns: bilevel and full-color. The only difference between the two is at which point color is applied to them.
Bilevel custom patterns are uncolored at definition time, then get a single color applied to them when they are used. This behavior is the same as that for the patterns in the default pattern set. In a bilevel custom pattern definition, any attempt to set a color is ignored, and the custom pattern is uncolored in the temporary GPS.
When the bilevel custom pattern is used to fill an area, the current value of the pattern color attribute at the time the Begin Area drawing order is executed is used as the color of the custom pattern. Bilevel custom patterns, then, appear in only one color (possibly black).
Full-color custom patterns have all color applied to them during their definition, and can therefore contain any number of colors in them (including the possibility of containing only one color, possibly black). The temporary GPS corresponding to a full-color custom pattern contains all the colors to be used in the pattern. When a full-color custom pattern in used to fill an area, the colors in the pattern appear as in the custom pattern definition, and are not affected by the current value of the pattern color attribute.
The type of a custom pattern is specified in the Begin Custom Pattern drawing order that starts the definition of the custom pattern. Every custom pattern, then, is either a bilevel custom pattern or a full-color custom pattern, but never both, and a given custom pattern cannot change its type. [GOCA-4-233]

#### Valid Custom Pattern Definitions

Custom pattern definitions cannot be nested—if a Begin Custom Pattern drawing order is found in a custom pattern definition, exception condition EC-DE00 is raised. Also, custom patterns cannot be used to fill areas inside the definition of a custom pattern; an attempt to do so will cause exception EC-6806 to be raised.
Similarly, gradient definitions cannot be nested inside custom pattern definitions. If a Linear Gradient or Radial Gradient drawing order is found in a custom pattern definition, exception condition EC-DE07 is raised. As with custom patterns, gradients cannot be used in custom pattern definitions, and exception condition EC-6806 is raised if this is attempted.
Just as nested custom patterns or gradients cannot be created inside a custom pattern definition, custom patterns or gradients cannot be deleted inside a custom pattern definition. The Delete Pattern drawing order, then, is not valid between a Begin Custom Pattern order and its corresponding End Custom Pattern order; exception condition EC-DE07 is raised if such a Delete Pattern drawing order is found.
Other than the Begin Custom Pattern, Delete Pattern, Linear Gradient, and Radial Gradient drawing orders, all GOCA drawing orders are allowed between a Begin Custom Pattern drawing order and its corresponding End Custom Pattern drawing area, although normal GOCA rules apply; for example, if an End Prolog drawing order is found, exception condition EC-3E00 would be raised as it would anytime an End Prolog drawing order is found outside the segment prolog.
If, in custom pattern mode, the End Custom Pattern drawing order is not found before the end of the segment, exception condition EC-DE01 is raised. Note, however, that an appended segment is part of the segment that it appends.
The Set Color, Set Extended Color, and Set Process Color drawing orders are allowed in bilevel custom pattern definitions, but are ignored. [GOCA-4-234]

---

### Gradients

Gradients can be used to fill areas. A gradient is an area fill where one color gradually changes to another.
There are two types of gradients in AFP GOCA:
*   Linear gradients [GOCA-4-235]
*   Radial gradients [GOCA-4-236]

An example of a picture with both a linear gradient (from the top to the bottom in the background) and a radial gradient (on the circle) is shown in Figure 20. [GOCA-4-237]

### Figure 20. An Example of Both a Linear Gradient and a Radial Gradient

The two types of gradients are defined using the Linear Gradient and Radial Gradient drawing orders. [GOCA-4-238]

---

#### Linear Gradients

Linear gradients go from a start color located at a start point to an end color located at an end point. The gradient, then, proceeds along a line from the start point to the end point; this line will be called the gradient line. The colors radiate out in a perpendicular fashion from the gradient line such that the gradient becomes 2-dimensional. The color gradually changes, starting at the line that is perpendicular to the gradient line and that goes through the start point, and ending at the line that is perpendicular to the gradient line and that goes through the end point. A linear gradient, then, continues out to the edge of the GPS. As an example, Figure 21 shows the entire GPS and what a gradient defined to go from blue at (0,0) to green at (20000, 10000) looks like. [GOCA-4-239]

### Figure 21. Linear Gradient Extending to the Edge of the GPS
Start at (0,0), End at (20000,10000), Entire GPS range $(\pm 32767, \pm 32768)$. [GOCA-4-240]

As can be seen in Figure 21, a gradient can be quite large in GPS terms, taking up a significant part of the entire GPS, but nonetheless there can be areas of the GPS that are outside the gradient. In Figure 21, the outside areas are:
*   The quadrilateral on the lower left. This area is considered to be on the “start side” of the gradient since it borders the start of the gradient line. [GOCA-4-241]
*   The triangle on the upper right. This area is considered to be on the “end side” of the gradient. [GOCA-4-242]

Also included in the definition of a gradient are indications of how to fill these areas that are outside the gradient—these are the “Outside” values. There is one Outside value for the start side of the gradient and one Outside value for the end side, and the values do not have to be the same. The possible values: [GOCA-4-243]

| Outside value | Meaning |
| :--- | :--- |
| **Pad** | Extend the color on the edge of the gradient as far as necessary to fill the outside area. For example, for a blue to green gradient, the outside area on the start side would be all blue and the outside area on the end side would be all green. [GOCA-4-244]|

---

| Outside value | Meaning [GOCA-4-245]|
| :--- | :--- |
| **Repeat** | The gradient is repeated as many times as necessary to fill the outside areas, by repeating the gradient line in both directions. For example, for a blue to green gradient, the blue to green gradient would be repeated over and over right next to the previous gradient. [GOCA-4-246]|
| **Reflect** | Right next to the gradient, a mirror-image of the gradient is produced. If this does not fill the outside area, the gradient itself is then repeated, followed by another mirror image, followed by the same gradient, and so on. For example, for a blue to green gradient, a blue to green gradient followed immediately by a green to blue gradient would be drawn; continuing this, the end result would be a gradient going from blue to green to blue to green and so on to the edge of the GPS. [GOCA-4-247]|
| **None** | Fill the outside areas with no color. This is equivalent to treating these areas as if they had been filled with the X'0F' (no fill) pattern of the default pattern set. [GOCA-4-248]|

Figure 22 shows the effect of each Outside value on the example gradient from Figure 21; in the figure, both the start and end Outside values are set to the same value. [GOCA-4-249]

### Figure 22. Effect of Different Outside Values
Pad, None, Repeat, Reflect [GOCA-4-250]

---

In addition to the gradient changing from the start color to the end color, additional colors can be specified to occur between the start and end. For example, rather than a gradient simply changing from blue to green, it can be defined that it also be yellow at some point in between. This point is called a color stop. A color stop is defined by the offset along the gradient line where it occurs, and the color to appear at that offset. Multiple color stops can be defined. As an example, Figure 23 shows the example gradient from Figure 21 but with the addition of a yellow color stop at an offset of 60% of the way along the gradient line. [GOCA-4-251]

### Figure 23. A Yellow Color Stop at Offset 60% Added to the Gradient from Figure 21

Two color stops at the same offset define a discontinuity of the gradient. For example, if two color stops are defined at halfway along the gradient line, the first yellow and the second purple, the gradient will smoothly change toward yellow in the first half of the gradient getting to yellow just before the halfway point, abruptly change to purple at the halfway point, then smoothly change away from purple in the second half of the gradient. [GOCA-4-252]

If any part of the gradient is specified to transition from some color C to that same color C, that part of the gradient will be drawn as solid fill with color C. [GOCA-4-253]

#### Radial Gradients

Radial gradients are conceptually very similar to linear gradients. They transition from a start color to an end color with any number of color stops in between. They have two Outside values to define how to fill areas outside the gradient. However, rather than the gradient changing color along a gradient line, radial gradients change color radiating from a start full arc to an end full arc. Conceptually, the color changes through an infinite number of intermediate full arcs that occur in the process of transitioning from the start full arc to the end full arc. For example, if the start full arc is smaller than the end full arc and its center is directly to the left of the end full arc’s center, the first intermediate full arc between the start and the end will be a tiny bit larger than, and a tiny bit to the right of, the start full arc. [GOCA-4-254]

---

Figure 24 shows a radial gradient transitioning from yellow at the inside, start full arc to red at the outside, end full arc. Figure 25 shows a similar radial gradient, from yellow to red, but with overlapping start and end full arcs. Figure 26 shows the same radial gradient with full arcs that are completely outside each other. Note that, as can been seen in figures 25 and 26, conceptually the intermediate full arcs are drawn beginning at the start full arc and ending at the end full arc, which results in a picture where the end full arc looks closer to the viewer. [GOCA-4-255]

### Figure 24. A Simple Radial Gradient
### Figure 25. A Radial Gradient when the Start and End Full Arcs Overlap
### Figure 26. A Radial Gradient when the Start and End Full Arcs Are Outside Each Other

Color stops in radial gradients work the same as those in linear gradients, but note that a color stop at an offset of, for example 60%, defines the color of the intermediate full arc that is 60% of the way between the start full arc and end full arc. [GOCA-4-256]

---

There is an important difference between linear and radial gradients regarding the filling of areas outside the gradient. In cases where one of the full arcs is not contained inside the other full arc, as in Figure 25, a given intermediate full arc does not surround the previous intermediate full arc; instead it overlaps the previous intermediate full arc. The intermediate full arcs move in some specific direction rather than expanding in all directions; in Figure 25, the intermediate full arcs are always moving to the right and slightly upward. Thus it can be seen that if Outside=pad, the continuation of the gradient would only cause there to be more intermediate full arcs that would fill certain areas to the right and left, but would not fill the area above and below—this is shown in Figure 27. Thus, a radial gradient, even when taking into account the Outside values, does not necessarily completely fill the GPS; that is, in some cases, there are parts of the GPS that are outside the gradient but that cannot be filled using the Outside values. Note that when one full arc is completely inside the other, as was the case for Figure 24, the Outside values can fill the entire GPS; see Figure 28. [GOCA-4-257]

### Figure 27. The Radial Gradient from Figure 25 when Both Outside Values Are Pad
### Figure 28. The Radial Gradient from Figure 24 when Both Outside Values Are Pad

---

#### Gradients as Patterns

When a gradient is defined, it is assigned a pattern set value and a pattern symbol value that can be used to subsequently identify the gradient.
Once a gradient has been defined in a segment, it can then be used to fill areas within that segment (including subsequent appended segments). Implementations must maintain the gradient definition throughout the segment, unless it is deleted using the Delete Pattern drawing order. The gradient cannot be used outside of the segment in which it is defined.
Once a gradient has been defined, it can be deleted using the Delete Pattern drawing order. After the Delete Pattern executes, the deleted gradient is no longer available for area fill.
As with any pattern, a gradient can be set as the default pattern using the Set Current Defaults instruction. [GOCA-4-258]

---

### Area (Pattern) Attributes

Table 10 shows the attributes controlling the drawing of Area primitives. [GOCA-4-259]

### Table 10. Attributes Controlling Area Primitives

| Attribute | Standard Default | Length (in bytes) | Meaning |
| :--- | :--- | :--- | :--- |
| **PATTERN SET** | Default pattern set (X'00') | 1 | Specification of pattern set identifier [GOCA-4-260]|
| **SYMBOL** | Solid fill (X'10') | 1 | Specification of pattern symbol code point [GOCA-4-261]|
| **COLOR** | Device dependent | 2 | Color value set into GPS for foreground [GOCA-4-262]|
| **PROCESS COLOR** | Device dependent | 12–14 | Process color value set into GPS for foreground [GOCA-4-263]|
| **MIX** | Overpaint (X'02') | 1 | Specification of mix mode in GPS for foreground [GOCA-4-264]|
| **BACKGROUND MIX** | Leave Alone (X'05') | 1 | Specification of mix mode in GPS for background [GOCA-4-265]|
| **REFERENCE POINT** | (0,0) | 4 | Specification of pattern reference point for custom patterns [GOCA-4-266]|

---

## Character Strings

Two orders are supported for drawing character strings:
*   The Character String at Given Position order draws a character string starting at a given point $(X_0, Y_0)$, and sets the current position to $(X_0, Y_0)$. [GOCA-4-267]
*   The Character String at Current Position order draws a character string starting at the current position, and does not change the current position. [GOCA-4-268]
See “Character String (GCHST, GCCHST) Orders” for details. [GOCA-4-269]

The font from which the character definitions are to be obtained is given by the value of the character set attribute.
The color of all characters in the string is given by the value of the color or process color attribute.
The way in which characters in the string are merged with any output primitives that have already been drawn is controlled by the values of the character mix and character background mix attributes.
The current values of the line type, line width, pattern set, and pattern symbol attributes have no effect on the appearance of the characters in the string.
A character string can be defined in which some of its characters need to be drawn outside the boundaries of the GPS. The result of executing a Character String order where this occurs is implementation dependent.
The appearance and relative positions of the characters in the string are dependent on the values of:
*   Code points in the order [GOCA-4-270]
*   Character Attributes (see “Character Attributes”) [GOCA-4-271]

A character string is drawn based upon the specified character precision:
*   **Precision 1:** Device-specific (string) precision [GOCA-4-272]
*   **Precision 2:** Device-specific (character) precision [GOCA-4-273]
*   **Precision 3:** Stroke precision (not supported in AFP GOCA) [GOCA-4-274]

---

Figure 29 shows two different methods used in AFP environments for positioning GOCA character strings. [GOCA-4-275]

### Figure 29. Methods for Positioning Character Strings
Font Positioning Method, Cell Positioning Method.
Horizontal Baseline, Vertical Baseline. [GOCA-4-276]

---

#### Device-Specific (Character) Precision

Character precision has been implemented differently on different devices; it is not consistent among implementations. The intent of this precision is that characters are positioned and drawn as follows. Note that the character reference point is not always placed at the current position. Scale and rotation are not necessarily applied when drawing the symbol. [GOCA-4-277]

1.  The position of the first character is determined by the character direction attribute. Each device uses one of the two methods of locating the points R,E,T,B shown in Figure 29; refer to your device documentation for specific implementation information. [GOCA-4-278]
    *   When the character direction is left to right, point R for the first character is positioned at the current or given position from the Character String order. [GOCA-4-279]
    *   When the character direction is right to left, point E for the first character is positioned at the current or given position from the Character String order. [GOCA-4-280]
    *   When the character direction is top to bottom, point T for the first character is positioned at the current or given position from the Character String order. [GOCA-4-281]
    *   When the character direction is bottom to top, point B for the first character is positioned at the current or given position from the Character String order. [GOCA-4-282]
2.  The character is then drawn taking the following character attributes into account: [GOCA-4-283]
    *   For devices that scale GOCA characters, the symbol is scaled using the character cell-size attribute. This scaling is independent in the X-axis and Y-axis. [GOCA-4-284]
    Note that for some devices, if the character cell size is specified as negative values, a mirror image of the character is generated. That is, if the cell width is negative, the character is mirrored about the Y-axis, and if the cell height is negative, the character is mirrored about the X-axis.
    *   The character cell is rotated by the angle given in the character angle attribute. [GOCA-4-285]
    *   For some devices, the character is rotated within the cell based on the selected font rotation. [GOCA-4-286]
3.  The next character in the string is positioned. [GOCA-4-287]
    *   When the character direction is left to right, a vector is generated from the left edge of the character box to the right edge, and successive characters are placed in the direction of this vector. [GOCA-4-288]
    *   When the character direction is top to bottom, a vector is generated from the top edge of the character box to the bottom edge, and successive characters are placed in the direction of this vector. [GOCA-4-289]
    *   When the character direction is right to left, a vector is generated from the right edge of the character box to the left edge, and successive characters are placed in the direction of this vector. [GOCA-4-290]
    *   When the character direction is bottom to top, a vector is generated from the bottom edge of the character box to the top edge, and successive characters are placed in the direction of this vector. [GOCA-4-291]
4.  Subsequent characters in the string are positioned and drawn in the same manner. Figure 30 shows the effect of the character direction and character angle attributes when the device uses the font positioning method. Figure 31 shows the effect of the character direction and character angle attributes when the device uses the cell positioning method. [GOCA-4-292]

---

### Figure 30. Font Positioning Method
Character baseline, Current graphics position, Character reference point of first character. [GOCA-4-293]

---

### Figure 31. Cell Positioning Method
Character baseline, Current graphics position, Character reference point of first character. [GOCA-4-294]

---

#### Device-Specific (String) Precision

String precision has been implemented differently on different devices; it is not consistent among implementations. String precision differs from character precision in the following respects:
*   The character angle attribute can be ignored. [GOCA-4-295]
*   The positioning of the first character can be approximate. [GOCA-4-296]
*   Drawing of the symbol need take no account of scale or rotation. [GOCA-4-297]
*   The positioning of further characters in the string need not be scaled according to the character cell-size attribute. [GOCA-4-298]

#### Character Attributes

Table 11 shows the attributes controlling the appearance of character strings. [GOCA-4-299]

### Table 11. Attributes Controlling Character String Primitives

| Attribute | Standard Default | Length (in bytes) | Meaning |
| :--- | :--- | :--- | :--- |
| **ANGLE** | X,Y No rotation | 4 | Character rotation $x$ and $y$ values [GOCA-4-300]|
| **CELLSIZE** | Device dependent | 4 | Specification of character cell width and height [GOCA-4-301]|
| **CELLSIZEF** | Device dependent | 4 | Specification of fractional extension of character cell width and height [GOCA-4-302]|
| **DIRECTION** | Left to right (X'01') | 1 | Specification of character direction [GOCA-4-303]|
| **PRECISION** | Character precision (X'02') | 1 | Specification of character precision [GOCA-4-304]|
| **CHARACTER SET** | Device dependent | 1 | Specification of character set local identifier [GOCA-4-305]|
| **SHEAR** | X,Y No shear | 4 | Shear $x$ and $y$ values; not supported in AFP GOCA [GOCA-4-306]|
| **SYMBOL** | Device dependent | 1 | Specification of default character code point [GOCA-4-307]|
| **COLOR** | Device dependent | 2 | Color value set into GPS for foreground [GOCA-4-308]|
| **PROCESS COLOR** | Device dependent | 12–14 | Process color value set into GPS for foreground [GOCA-4-309]|
| **MIX** | Overpaint (X'02') | 1 | Specification of mix mode in GPS for foreground [GOCA-4-310]|
| **BACKGROUND MIX** | Leave Alone (X'05') | 1 | Specification of mix mode in GPS for background [GOCA-4-311]|

---

## Markers

A marker is a symbol that is used to indicate a position. It is similar to a character drawn at a specified $(X_g, Y_g)$ position. When a marker is used, however, the center of the cell in which the marker is drawn is placed at the specified position.
Two orders are provided for drawing markers:
*   The Marker at Given Position order draws a marker at one or more points. [GOCA-4-312]
*   The Marker at Current Position order draws a marker at the current position and at one or more further points. [GOCA-4-313]

The particular symbol that is drawn is the one identified by the current marker symbol from the current marker set. This symbol is drawn at all the positions specified in the one order. The only marker set supported in AFP GOCA is the default marker set, shown in Figure 32. [GOCA-4-314]

### Figure 32. Default Marker Set
X'00' Drawing default (blank), X'01' to X'0A', X'40' blank. [GOCA-4-315]

The color of all markers drawn by an order is given by the value of the current marker color. The way in which markers are merged with any output primitives that have already been drawn is controlled by the values of the marker mix and marker background mix attributes.
The current values of the line type, line width, pattern set, and pattern symbol attributes have no effect on the appearance of the markers.
A marker symbol whose position is inside the GPS, but is placed such that part of the marker lies outside the GPS, is not an error. The appearance of that marker in the GPS is implementation dependent.
Markers are drawn taking into account all marker attributes, and are centered at the specified position.
Markers are scaled along with the rest of the GPS if scaling is necessary in the mapping from the GPS window into the usable area (object area). [GOCA-4-316]

**Implementation note:** In earlier versions of AFP GOCA, there existed a marker precision attribute. This attribute has been made obsolete; for more details, see Appendix C, “AFP GOCA Migration Functions”. The marker precision attribute allowed implementations to draw markers taking into account only the marker symbol and marker set attributes, and the positioning of the marker could be approximate. Some implementations, then, might draw markers in such a way. However, all implementations that do not treat the Set Marker Cell (GSMC) drawing order as a No-Op must:
*   Draw markers taking into account all marker attributes. [GOCA-4-317]
*   Center the marker at the specified position. [GOCA-4-318]
*   Treat the marker precision attribute as obsolete. [GOCA-4-319]
*   Follow the recommendation for the standard default value for marker cell-size specified in "Set Marker Cell (GSMC) Order". [GOCA-4-320]

#### Marker Attributes

Table 12 shows the attributes controlling the appearance of markers. [GOCA-4-321]

### Table 12. Attributes Controlling Marker Primitives

| Attribute | Standard Default | Length (in bytes) | Meaning |
| :--- | :--- | :--- | :--- |
| **CELLSIZE** | Device dependent | 4 | Specification of marker cell width and height [GOCA-4-322]|
| **MARKER SET** | Device dependent | 1 | Specification of marker set local identifier [GOCA-4-323]|
| **SYMBOL** | Cross (X'01') | 1 | Specification of marker symbol code point [GOCA-4-324]|
| **COLOR** | Device dependent | 2 | Color value set into GPS for foreground [GOCA-4-325]|
| **PROCESS COLOR** | Device dependent | 12–14 | Process color value set into GPS for foreground [GOCA-4-326]|
| **MIX** | Overpaint (X'02') | 1 | Specification of mix mode in GPS for foreground [GOCA-4-327]|
| **BACKGROUND MIX** | Leave Alone (X'05') | 1 | Specification of mix mode in GPS for background [GOCA-4-328]|

---

## Image Primitives

Images are rectangular arrays of points that are included directly in the graphics picture definition. An image is represented by a sequence of orders. The first order is a Begin Image order, which is followed by one or more Image Data orders. The sequence must end with an End Image order. [GOCA-4-329]

**Note:** The only other orders permitted within the Begin Image/End Image order bracket are the No-Operation and Comment orders. [GOCA-4-330]

Only one format of image data is defined: `FORMAT=X'00'`. With this format, each Image Data order contains the data for one row of the image. [GOCA-4-331]

Image points are mapped to presentation-device pels. The size of the image is given by the WIDTH and HEIGHT parameters in the Begin Image order. There must be as many Image Data orders as the HEIGHT parameter, and each Image Data order must contain the number of bits specified by the WIDTH parameter, plus padding to a byte boundary. [GOCA-4-332]

The position of the image in GPS is specified in GPS L-units. [GOCA-4-333]

The current values of the image attributes are taken into account when drawing the image. An image must be completely defined in one segment. However, it may start in one segment and be completed in an appended segment. [GOCA-4-334]

#### Image Attributes

Table 13 shows the attributes controlling the appearance of an image. [GOCA-4-335]

### Table 13. Attributes Controlling Image Primitives

| Attribute | Standard Default | Length (in bytes) | Meaning |
| :--- | :--- | :--- | :--- |
| **COLOR** | Device dependent | 2 | Color value set into GPS for foreground [GOCA-4-336]|
| **PROCESS COLOR** | Device dependent | 12–14 | Process color value set into GPS for foreground [GOCA-4-337]|
| **MIX** | Overpaint (X'02') | 1 | Specification of mix mode in GPS for foreground [GOCA-4-338]|
| **BACKGROUND MIX** | Leave Alone (X'05') | 1 | Specification of mix mode in GPS for background [GOCA-4-339]|

---

## Output Primitive Overflow

It is possible to define an output primitive such that it starts inside the GPS, but some part of it lies outside the GPS.
If a primitive, such as the Full Arc or Partial Arc primitive, starts inside the GPS, ends inside the GPS, leaves the current position in the GPS, but goes outside the GPS for some part of its path, exception EC-000D is raised. The standard action for exception EC-000D is to draw the primitive in an implementation-dependent manner. For presentation devices that cannot maintain a position outside the GPS, such as devices that are limited to 2-byte arithmetic, this exception is mandatory. For devices that can maintain a position outside the GPS, it is optional.
An error is also generated when a primitive, such as the Relative Line or Partial Arc primitive, is specified that causes the current position to fall outside the GPS. In that case a drawing process check is generated and there is no standard action. For the Relative Line primitive, the exception is EC-E100. For the Partial Arc primitive, it is EC-E300. For presentation devices that cannot maintain a position outside the GPS, these exceptions are mandatory. For devices that can maintain a position outside the GPS, they are optional.
A coordinate point that is outside the GPS is characterized by an arithmetic overflow in its $X_g$ or $Y_g$ coordinate.
Because AFP GOCA uses 16-bit signed integers to specify GPS coordinates, a point outside the GPS is characterized by a 2-byte arithmetic overflow. Note that this does not mean that AFP GOCA processors are limited to 2-byte arithmetic. It simply means that they need to be able to detect 2-byte arithmetic overflows. For a definition of the geometric parameter format used in AFP GOCA, see “Parameter Type” and “Drawing Order Subset”. [GOCA-4-340]

---

Copyright © AFP Consortium 1997, 2017 61
