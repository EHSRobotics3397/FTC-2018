package org.firstinspires.ftc.teamcode.modules.positioning;

import java.util.ArrayList;
import java.util.List;

public class Route {
    public List<RouteSeg> segs;
    private Coordinates start;
    private Coordinates end;

    public Route (Coordinates st, Coordinates en) {
        start = st;
        end = en;

        segs = new ArrayList<RouteSeg>(1);
        RouteSeg tmpSeg = new RouteSeg(start, end, null, RouteSeg.IS_LINE);
        segs.get(0) = tmpSeg;
    }
}
