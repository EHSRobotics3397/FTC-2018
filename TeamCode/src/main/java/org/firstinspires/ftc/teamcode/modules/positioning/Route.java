package org.firstinspires.ftc.teamcode.modules.positioning;

import java.util.ArrayList;
import java.util.List;

/*
    This class represents a route for
    the robot to follow in auto mode
 */
public class Route {
    /*
        Segments are stored
        in ArrayList
        start and end are
        coordinates objects
     */
    private List<RouteSeg> segs;
    private Coordinates start;
    private Coordinates end;
    public boolean status = false;

    public Route (Coordinates st, Coordinates en) {
        start = st;
        end = en;

        segs = new ArrayList<RouteSeg>(0);
        RouteSeg tmpSeg = new RouteSeg(start, end, null, RouteSeg.IS_LINE);
        segs.add(tmpSeg);
    }

    public void changeSeg(RouteSeg seg, int i) {
        try {
            segs.set(i, seg);
        } catch (Exception e) {
            status = true;
            return;
        }

        status = false;
    }

    public void addSeg
}
