package org.firstinspires.ftc.teamcode.modules.positioning;
// not done
public class Parabola {
    // stores the 3 numbers modifying x as double array
    // It only uses addition but numbers can be negative
    private boolean possible;
    private Coordinates root1;
    private Coordinates root2;
    private Coordinates vertex;
    private double equationA;
    private double equationB;
    private double equationC;

    public Parabola (Coordinates rt1, Coordinates rt2, Coordinates vrtx) {
        root1 = rt1;
        root2 = rt2;
        vertex = vrtx;

        // check if 3 points given are colinear
        Line test1 = new Line(root1, root2);
        Line test2 = new Line(root2, vertex);

        if (test1.getSlope() == test2.getSlope()) {
            possible = false;
            return;
        } else {
            possible = true;
        }

        // set equation
        equationC = root1.y;
        equationB = (vertex.y - equationC) * 2;
        equationA = -(equationB) + (vertex.y - equationC);
    }

    public boolean isPossible () {
        return possible;
    }

    public double[] getEquation() {
        double[] val = {equationA, equationB, equationC};
        return val;
    }
}
