package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.modules.*;
import org.firstinspires.ftc.robotcore.external.Telemetry;

import static org.firstinspires.ftc.teamcode.modules.MecanumAutoDrive.State.*;
import static org.firstinspires.ftc.teamcode.modules.MecanumAutoDrive.*;

@Autonomous (name = "Auto01", group = "Drive")
public class Auto01 extends OpMode {
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    MecanumAutoDrive driver;

    private State state;
    private long startTime;
    private long idleTime;
    private State lastState;

    private String lastStateString;

    //driving variables
    private float drivePower;
    private float driveDuration;
    private float turnDuration;
    private float turnPower;
    private float turnAngle;

    private float imuDirection;

    //used in a higher-level finite state machine for step-by-step programming
    private int step;

    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        motor3.setDirection(DcMotor.Direction.REVERSE);
        motor4.setDirection(DcMotor.Direction.REVERSE);

        //setup driver
        driver = new MecanumAutoDrive();
        driver.setup(motor1, motor2, motor3, motor4);
        state = COMPLETED;
        startTime = System.currentTimeMillis();
        //decide how many steps you want

    }



    @Override
    public void loop() {
        //update driver and wait till start
        runRobot();
        switch (state) {
            case IDLE:
                //do nothing
                runIdle();
                lastState = IDLE;
                break;
            case DRIVING:
                runDrive();
                lastState = DRIVING;
                break;
            case TURNING:
                runTurn();
                lastState = TURNING;
                break;
            case TURNINGBYANGLE:
                runTurnByAngle();
                lastState = TURNINGBYANGLE;
                break;
            case COMPLETED:
                //do nothing
                lastState = COMPLETED;
                break;
            case FAILED:
                runFailed();
                lastState = FAILED;
                break;
        }

        telemetry.update();
        driver.update(telemetry, imuDirection); //need to add imu
    }

    private void runIdle() {
        if (System.currentTimeMillis() >= idleTime) {
            state = COMPLETED;
        }
    }

    private void runDrive() {
        if (lastState == DRIVING) {
            //do nothing
        } else {
            driver.Drive(driveDuration, drivePower);
        }

        if (driver.getState() == COMPLETED) {
            state = COMPLETED;
        }

        //check if driver has failed
        if (driver.getState() == FAILED) {
            state = FAILED;
        }
    }

    private void runFailed() {
        switch (lastState) {
            case COMPLETED:
                lastStateString = "COMPLETED";
                break;
            case IDLE:
                lastStateString = "IDLE";
                break;
            case DRIVING:
                lastStateString = "DRIVING";
                break;
            case FAILED:
                lastStateString = "FAILED";
                break;
            case TURNING:
                lastStateString = "TURNING";
                break;
            case TURNINGBYANGLE:
                lastStateString = "TURNINGBYANGLE";
                break;
        }
        telemetry.addData("Robot Proccess Failed: ", lastStateString + ", " + driver.FailReason());
        driver.Reset();
    }

    private void runTurn() {
        if (!(lastState == TURNING)) {
            //turn the robot
            driver.Turn(turnDuration, turnPower);
        }
        //else do nothing

        //check if turning is completed
        if (driver.getState() == COMPLETED) {
            state = COMPLETED;
        }

        //check if turning is failed
        if (driver.getState() == FAILED) {
            state = FAILED;
        }
    }

    private void runTurnByAngle() {
        if (!(lastState == TURNINGBYANGLE)) {
            //turn the robot
            driver.TurnByAngle(turnAngle, turnPower);
        }
        //else do nothing

        //check if turning is completed
        if (driver.getState() == COMPLETED) {
            state = COMPLETED;
        }

        //check if turning has failed
        if (driver.getState() == FAILED) {
            state = FAILED;
        }
    }

    private void runRobot() {
        //for now lets turn robot for 1 second, then move forward at 1/2 power for 3 seconds
        //steps go in order so no need for lastStep variable
        if (state == COMPLETED) {
            switch (step) {
                case 0:
                    idle(3000);
                    break;
                case 1:
                    //drive fowards for 2 seconds
                    drive(2, 50);
                    break;
                case 2:
                    //turn right for 2 seconds
                    turn(2, 50);
                    break;
            }
            step++;
        }
    }

    private void freeze(long timeMillis) {
        state = IDLE;   //probably dont need this, but its there
        long targetTime = System.currentTimeMillis() + timeMillis;
        //wait for time to pass
        while (!(System.currentTimeMillis() >= targetTime)) {
            //do nothing
        }
        state = COMPLETED;
    }

    private void idle(long timeMillis) {
        idleTime = System.currentTimeMillis() + timeMillis;
        state = IDLE;
    }

    private void drive(float duration, float power) {
        driveDuration = duration;
        drivePower = power;
        state = DRIVING;
    }

    private void turn(float duration, float power) {
        turnDuration = duration;
        turnPower = power;
        state = TURNING;
    }

    private void turnByAngle(float angle, float power) {
        turnAngle = angle;
        turnPower = power;
        state = TURNINGBYANGLE;
    }
}
