package org.firstinspires.ftc.teamcode.modules.positioning;

import java.util.ArrayList;
import java.util.List;

public class Plane {
    // define public variables
    public int MAX_X;
    public int MAX_Y;
    public int MIN_X;
    public int MIN_Y;

    // define private variables
    // ArrayList object is used be cuase it supports .add
    private List<PhysObject> knownObjects = new ArrayList<>();

    public Plane (int maxX, int maxY, int minX, int minY) {
        MAX_X = maxX;
        MAX_Y = maxY;
        MIN_X = minX;
        MIN_Y = minY;
    }

    /*
        This function adds new
        PhysObjects to the plane
        PhysObjects here would be generated
        by a shape recognition class
     */

    public void addPhysObject (PhysObject phys) {
        knownObjects.add(phys);
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
        List<Line> lines = new ArrayList<>();
        //Start by generating lines to test
        for (int i = 0; i < knownObjects.size(); i++) {
            
        }

        return false;
    }
}