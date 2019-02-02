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
    private Gamepad gamePad;
    private GameButton upButton;
    private GameButton downButton;

    public Lift (DcMotor motor, Gamepad gpad) {
        liftMotor = motor;
        upButton.buttonLabel = GameButton.Label.dpadUp;
        downButton.buttonLabel = GameButton.Label.dpadDown;
    }

    public void update() {
        upButton.Update();
        downButton.Update();
        if (upButton.IsDown()) {
            liftMotor.setPower(50);
        } else if (downButton.IsDown()) {
            liftMotor.setPower(-50);
        } else {
            liftMotor.setPower(0);
        }
    }
}
