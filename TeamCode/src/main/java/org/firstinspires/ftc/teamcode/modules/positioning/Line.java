package org.firstinspires.ftc.teamcode.modules.positioning;

// Simple class to represent a line

public class Line {
    private Coordinates start;
    private Coordinates end;
    private double slope;

    public Line(Coordinates st, Coordinates en) {
        start = st;
        end = en;
        slope = (start.y - end.y) / (start.x - end.x);
    }

    public double getSlope() {
        return slope;
    }

    public double getYIntersept() {
        double yIntersept = start.y + ((0 - start.x) * slope);
        return yIntersept;
    }
}
