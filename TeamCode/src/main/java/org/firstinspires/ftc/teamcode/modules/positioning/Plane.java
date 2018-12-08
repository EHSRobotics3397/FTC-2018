package org.firstinspires.ftc.teamcode.modules.positioning;

import java.util.ArrayList;
import java.util.List;

public class Plane {
    // define public variables
    private int MAX_X;
    private int MAX_Y;
    private int MIN_X;
    private int MIN_Y;

    // define private variables
    // ArrayList object is used because it supports .add
    private List<PhysObject> knownObjects = new ArrayList<>();
    private Coordinates robotPos;

    public Plane (int maxX, int maxY, int minX, int minY) {
        MAX_X = maxX;
        MAX_Y = maxY;
        MIN_X = minX;
        MIN_Y = minY;
        robotPos = new Coordinates(1,1);
    }

    /*
        This function adds new
        PhysObjects to the plane
        PhysObjects here would be generated
        by a shape recognition class
     */

    public void update (PhysObject phys, Coordinates rp) {
        knownObjects.add(phys);
        robotPos = rp;
    }

    public boolean isObjectAt(Coordinates coords) {
        for (int i = 0; i < knownObjects.size(); i++) {
            if (knownObjects.get(i).equals(coords)) {
                return true;
            }
        }

        return false;
    }

    /*
        isBlocked is meant to be used by
        Route class to determine if route is
        blocked
     */

    public boolean isBlocked(Coordinates start, Coordinates end) {
        // if line from start to end intersects with ANY PhysObjects, return true
        List<Line> lines = new ArrayList<Line>();
        //Start by generating lines to test
        for (int i = 0; i < knownObjects.size(); i++) {
            List<Coordinates> objectCorners = knownObjects.get(i).getCorners();
            for (int a = 0; a < 4; a++) {
                
                Line line = new Line(objectCorners.get(a), objectCorners.get(a + 1));
            }
        }

        return false;
    }

    public int maxX () {
        return MAX_X;
    }

    public int maxY () {
        return MAX_Y;
    }

    public int minX () {
        return MIN_X;
    }

    public int minY () {
        return MIN_Y;
    }
}
