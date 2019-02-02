package org.firstinspires.ftc.teamcode;

/*
Drive opmode made by Matthew Hull
Feb 2 2019
 */
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.modules.*;

@TeleOp(name = "Manual01", group = "drive")
//@Disabled
public class Manual01 extends OpMode{

    //Create driver object and Motor objects & Rotation Finder
    //Also sets up device object
    private MecanumDrive driver;
    private Lift lifter;
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    private DcMotor motor5;
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
        //motor 5 is for lifter
        motor5 = hardwareMap.dcMotor.get("motor5");

        /*buttonA      = new GameButton(gamepad1, GameButton.Label.a);
        buttonB      = new GameButton(gamepad1, GameButton.Label.b);
        buttonX      = new GameButton(gamepad1, GameButton.Label.x);
        right_Bumper  = new GameButton(gamepad1, GameButton.Label.RBumper);
        left_Bumper = new GameButton(gamepad1, GameButton.Label.LBumper);*/

        //Reverse 3 & 4 and initialize driver and Rotation Finder
        //Motor 3 and 4 are reversed as they are facing the other direction
        motor3.setDirection(DcMotor.Direction.REVERSE);
        motor4.setDirection(DcMotor.Direction.REVERSE);
        driver.setup(motor1, motor2, motor3, motor4, gamepad1);
        lifter = new Lift(motor5, gamepad1);
    }

    @Override
    public void loop() {
        telemetry.update();

        // Update Rotation Finder and Driver
        //rotationFinder.update(telemetry);
        driver.update();
        lifter.update();
        /*Finite State machine will be used in Auto
        and maybe Main Op
         */
    }
}
