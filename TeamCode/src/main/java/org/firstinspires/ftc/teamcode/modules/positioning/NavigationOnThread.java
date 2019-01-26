package org.firstinspires.ftc.teamcode.modules.positioning;

import org.firstinspires.ftc.teamcode.modules.positioning.*;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;

public class NavigationOnThread extends Thread {

    private VuforiaNavigation nav;
    public OpenGLMatrix mat;

    public NavigationOnThread(VuforiaNavigation n) {
        nav = n;
    }

    public void run() {
        mat = nav.getRobotPosition();
    }
}
