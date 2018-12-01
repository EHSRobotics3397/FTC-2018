package org.firstinspires.ftc.teamcode.modules.positioning;

public class RouteSeg {
    private Line seg;
    private Parabola quad;
    public static final boolean IS_PARA = true;
    public static final boolean IS_LINE = false;
    public static boolean type;

    public RouteSeg(Coordinates start, Coordinates end, Coordinates vertex, boolean typ) {
        type = typ;
        if (type == IS_PARA) {
            quad = new Parabola(start, end, vertex);
        } else {    // type == IS_LINE
            seg = new Line(start, end);
        }
    }
}
