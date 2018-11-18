package org.firstinspires.ftc.teamcode.modules.positioning;

import java.util.ArrayList;
import java.util.List;

/* This is a class that represents
    Physical objects on the playing
    field, using position and angle
    and a virtual cube to represent the
    object
 */
/* First made by Matt
    Hull on 11/17/18
 */
public class PhysObject {
    public Coordinates position;
    public double angle;
    /*
    length is back to front
    faces in direction of double
    angle (originally init. as longest side)

    width is perpendicular
    to length

    height is perpendicular
    to width on z axis
     */
    private List<Coordinates> corners;


    public PhysObject(Coordinates pos, List<Coordinates> crners) {
        position = pos;
        corners = crners;
        // Calculate 4th corner position, if not given
        if (corners.size() == 3) {
            double xDiffer = corners.get(1).x - corners.get(0).x;
            double yDiffer = corners.get(1).y - corners.get(0).y;
            double x = corners.get(2).x + xDiffer;
            double y = corners.get(2).y + yDiffer;

            Coordinates fourth = new Coordinates(x,y);
            corners.add(fourth);
        }
    }

    public void update(Coordinates pos, List<Coordinates> crners) {
        position = pos;
        corners = crners;
        // Calculate 4th corner position, if not given
        if (corners.size() == 3) {
            double xDiffer = corners.get(1).x - corners.get(0).x;
            double yDiffer = corners.get(1).y - corners.get(0).y;
            double x = corners.get(2).x + xDiffer;
            double y = corners.get(2).y + yDiffer;

            Coordinates fourth = new Coordinates(x,y);
            corners.add(fourth);
        }
    }

    public List<Coordinates> getCorners() {
        return corners;
    }
}
