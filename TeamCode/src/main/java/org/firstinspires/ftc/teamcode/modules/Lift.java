package org.firstinspires.ftc.teamcode.modules;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/*
Class to control linear
Accuator by Matt Hull on Feb
2 2019
 */


public class Lift {

    private DcMotor liftMotor;
    private GameButton upButton;
    private GameButton downButton;

    public Lift (DcMotor motor, Gamepad gpad) {
        liftMotor = motor;
        upButton = new GameButton(gpad, GameButton.Label.dpadUp);
        downButton = new GameButton(gpad, GameButton.Label.dpadDown);
    }

    public void update() {
        upButton.Update();
        downButton.Update();
        if (upButton.IsDown()) {
            liftMotor.setPower(100);
        } else if (downButton.IsDown()) {
            liftMotor.setPower(-100);
        } else {
            liftMotor.setPower(0);
        }
    }
}
