package org.firstinspires.ftc.teamcode.modules.positioning;

public class RouteSeg {
    private Line seg;
    // private Quadratic quad;

    public RouteSeg(Coordinates start, Coordinates end) {
        seg = new Line(start, end);
    }
}
