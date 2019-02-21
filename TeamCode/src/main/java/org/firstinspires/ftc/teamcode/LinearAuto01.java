package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Servo.Direction;
import org.firstinspires.ftc.teamcode.modules.*;

@Autonomous (name = "Auto01 Linear", group = "Drive")
public class LinearAuto01 extends LinearOpMode{
    private DcMotor motor1, motor2, motor3, motor4, motor5;
    private Servo servo;
    private AutoDrive driver;

    public void runOpMode() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");
        motor5 = hardwareMap.dcMotor.get("motor5");
        servo = hardwareMap.servo.get("servo1");

        motor3.setDirection(DcMotor.Direction.REVERSE);
        motor4.setDirection(DcMotor.Direction.REVERSE);

        //setup driver
        driver = new AutoDrive(motor1, motor2, motor3, motor4);



        waitForStart();
        //move lin acuator for 1/4 second
        // drive sideways for 1/2 second and drive forward for 3 seconds
        final long LIFT_TIME = 9500;
        long targetTime = System.currentTimeMillis() + LIFT_TIME;
        motor5.setPower(100);
        while (targetTime - System.currentTimeMillis() > 0) {
            //do nothing
        }
        motor5.setPower(0);
        final long SIDEWAYS_TIME = 1000;
        final float SIDEWAYS_POWER = -0.5f;
        driver.driveSideways(SIDEWAYS_POWER, SIDEWAYS_TIME);
        //do driving myself
        //final long DRIVE_TIME = 3000;
        //driver.drive(50, DRIVE_TIME);
        // move servo
        servo.setPosition(1.0);
    }
}
