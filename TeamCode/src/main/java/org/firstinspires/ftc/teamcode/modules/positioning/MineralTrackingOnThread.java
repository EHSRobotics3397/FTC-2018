package org.firstinspires.ftc.teamcode.modules.positioning;

import org.firstinspires.ftc.teamcode.modules.positioning.*;

public class MineralTrackingOnThread extends Thread {
    private MineralTracking minTrk;
    public int[] minerals;

    public MineralTrackingOnThread(MineralTracking min) {
        minTrk = min;
    }

    public void run() {
        minerals = minTrk.getMinerals();
    }
}
