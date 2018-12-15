package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.modules.GameButton;

/* This class will allow robot to be driven
    Backwards, forwards, sideways, and spin

    Created by EHS Robotics on 11/10/18 with
    code from 2017 season
 */

@TeleOp (name = "GamepadTestOp", group = "Drive")
//@Disabled
public class GamepadTestOp extends OpMode {

    //Create driver object and Motor objects & Rotation Finder
    //Also sets up device object
    private GameButton   left_Bumper;
    private GameButton   right_Bumper;
    private GameButton   buttonA;
    private GameButton   buttonB;
    private GameButton   buttonX;


    public void init() {

        buttonA      = new GameButton(gamepad1, GameButton.Label.a);
        buttonB      = new GameButton(gamepad1, GameButton.Label.b);
        buttonX      = new GameButton(gamepad1, GameButton.Label.x);
        right_Bumper  = new GameButton(gamepad1, GameButton.Label.RBumper);
        left_Bumper = new GameButton(gamepad1, GameButton.Label.LBumper);
        telemetry.addData("Status: ", "Telemetry init");

    }
    @Override
    public void loop() {
        buttonA.Update();
        buttonB.Update();
        buttonX.Update();
        right_Bumper.Update();
        left_Bumper.Update();
        String button;
        // test if a button is pressed
        if (buttonA.IsDown())
            button = "A";
        else if (buttonB.IsDown())
            button = "B";
        else if (buttonX.IsDown())
            button = "X";
        else if (right_Bumper.IsDown())
            button = "RB";
        else if (left_Bumper.IsDown())
            button = "LB";
        else
            button = "No Button";
        telemetry.addData("Button Pressed: ", button);
        telemetry.update();
    }
}
