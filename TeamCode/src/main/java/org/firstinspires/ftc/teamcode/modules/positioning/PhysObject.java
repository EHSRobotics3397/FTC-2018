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
    public List<Coordinates> corners;


    public PhysObject(Coordinates coor, List<Coordinates> cor) {
        position = coor;
        corners = cor;
        // Calculate 4th corner position, if not giver
        if (corners.size() == 3) {
            
        }
    }

    public void update(Coordinates coor, List<Coordinates> cor) {
        position = coor;
        corners = cor;
    }
}
