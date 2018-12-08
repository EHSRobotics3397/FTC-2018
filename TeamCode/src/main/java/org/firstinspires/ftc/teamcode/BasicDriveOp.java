package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.*;

/* This class will allow robot to be driven
    Backwards, forwards, sideways, and spin

    Created by EHS Robotics on 11/10/18 with
    code from 2017 season
 */

@TeleOp (name = "BasicDriveOp", group = "Drive")
//@Disabled
public class BasicDriveOp extends OpMode {

    //Create driver object and Motor objects & Rotation Finder
    //Also sets up device object
    private MecanumDrive driver;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    private GameButton   left_Bumper;
    private GameButton   right_Bumper;
    private GameButton   buttonA;
    private GameButton   buttonB;
    private GameButton   buttonX;


    public void init() {
        // Set values of driver and motor objects
        driver = new MecanumDrive();
        //rotationFinder = new IMURotate();
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        buttonA      = new GameButton(gamepad1, GameButton.Label.a);
        buttonB      = new GameButton(gamepad1, GameButton.Label.b);
        buttonX      = new GameButton(gamepad1, GameButton.Label.x);
        right_Bumper  = new GameButton(gamepad1, GameButton.Label.RBumper);
        left_Bumper = new GameButton(gamepad1, GameButton.Label.LBumper);

        //Reverse 3 & 4 and initialize driver and Rotation Finder
        motor3.setDirection(DcMotor.Direction.REVERSE);
        motor4.setDirection(DcMotor.Direction.REVERSE);
        driver.setup(motor1, motor2, motor3, motor4, gamepad1);
    }
    @Override
    public void loop() {
        telemetry.addData("Mode", "Looped");

        // Update Rotation Finder and Driver
        //rotationFinder.update(telemetry);
        driver.update(telemetry);
        /*Finite State machine will be used in Auto
        and maybe Main Op
         */
    }
}
