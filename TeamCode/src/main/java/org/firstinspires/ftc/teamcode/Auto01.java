package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.teamcode.modules.*;
import org.firstinspires.ftc.teamcode.modules.positioning.*;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;

import static org.firstinspires.ftc.teamcode.modules.MecanumAutoDrive.*;

/*
Autonomous opmode made by matt hull
feb 2, 2019

Uses a finite state machine
tasks are executed by calling functions
which start states, during a state
a run function is called
 */

@Autonomous (name = "Auto01", group = "Drive")
public class Auto01 extends OpMode {
    private enum State {IDLE, DRIVING, TURNING, COMPLETED, FAILED, TURNINGBYANGLE, DRIVINGTOLOCATION};
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

    BNO055IMU imu;
    Position globalPosition;
    Orientation lastAngles = new Orientation();
    double globalAngle;

    //navigation and tracking
    private VuforiaNavigation vuNav;
    private NavigationOnThread navThread;
    private MineralTracking minTrack;
    private MineralTrackingOnThread minThread;
    private OpenGLMatrix driveLocation;

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
        state = State.COMPLETED;
        startTime = System.currentTimeMillis();
        //decide how many steps you want

        //set up vuforia navigation and thread to run nav program
        vuNav = new VuforiaNavigation();
        navThread = new NavigationOnThread(vuNav);
        navThread.start();
        //set up thread to track minerals
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        minTrack = new MineralTracking(tfodMonitorViewId);
        minThread = new MineralTrackingOnThread(minTrack);
        minThread.start();

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = hardwareMap.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);
    }



    @Override
    public void loop() {
        //update driver and wait till start
        runRobot();
        switch (state) {
            case IDLE:
                //do nothing
                runIdle();
                lastState = State.IDLE;
                break;
            case DRIVING:
                runDrive();
                lastState = State.DRIVING;
                break;
            case TURNING:
                runTurn();
                lastState = State.TURNING;
                break;
            case TURNINGBYANGLE:
                runTurnByAngle();
                lastState = State.TURNINGBYANGLE;
                break;
            case DRIVINGTOLOCATION:
                runDriveToLocation();
                lastState = State.DRIVINGTOLOCATION;
                break;
            case COMPLETED:
                //do nothing
                lastState = State.COMPLETED;
                break;
            case FAILED:
                runFailed();
                lastState = State.FAILED;
                break;
        }

        telemetry.update();
        //global angle is updated by the change in angle.

        Orientation angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);

        double deltaAngle = angles.firstAngle - lastAngles.firstAngle;

        //handle roll through +180, -180
        if (deltaAngle < -180)
            deltaAngle += 360;
        else if (deltaAngle > 180)
            deltaAngle -= 360;

        globalAngle += deltaAngle;

        lastAngles = angles;
        driver.update(telemetry, (float) globalAngle); //need to add imu
    }

    private void driveToLocation (OpenGLMatrix loc) {
        driveLocation  = loc;
        state = State.DRIVINGTOLOCATION;
    }

    private void runDriveToLocation() {
        //see if it is pointed at target
        OpenGLMatrix target = driveLocation;
        OpenGLMatrix pos = navThread.mat;
        //get relative angle

    }

    private void runIdle() {
        if (System.currentTimeMillis() >= idleTime) {
            state = State.COMPLETED;
        }
    }

    private void runDrive() {
        if (lastState == State.DRIVING) {
            //do nothing
        } else {
            driver.Drive(driveDuration, drivePower);
        }
        // check if driver has completed
        if (driver.getState() == MecanumAutoDrive.State.COMPLETED) {
            state = State.COMPLETED;
        }

        //check if driver has failed
        if (driver.getState() == MecanumAutoDrive.State.FAILED) {
            state = State.FAILED;
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
            case DRIVINGTOLOCATION:
                lastStateString = "DRIVINGTOLOCATION";
                break;
        }
        telemetry.addData("Robot Proccess Failed: ", lastStateString + ", " + driver.FailReason());
        driver.Reset();
    }

    private void runTurn() {
        if (!(lastState == State.TURNING)) {
            //turn the robot
            driver.Turn(turnDuration, turnPower);
        }
        //else do nothing

        //check if turning is completed
        if (driver.getState() == MecanumAutoDrive.State.COMPLETED) {
            state = State.COMPLETED;
        }

        //check if turning is failed
        if (driver.getState() == MecanumAutoDrive.State.FAILED) {
            state = State.FAILED;
        }
    }

    private void runTurnByAngle() {
        if (!(lastState == State.TURNINGBYANGLE)) {
            //turn the robot
            driver.TurnByAngle(turnAngle, turnPower);
        }
        //else do nothing

        //check if turning is completed
        if (driver.getState() == MecanumAutoDrive.State.COMPLETED) {
            state = State.COMPLETED;
        }

        //check if turning has failed
        if (driver.getState() == MecanumAutoDrive.State.FAILED) {
            state = State.FAILED;
        }
    }

    private void runRobot() {
        //for now lets turn robot for 1 second, then move forward at 1/2 power for 3 seconds
        //steps go in order so no need for lastStep variable
        if (state == State.COMPLETED) {
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
        state = State.IDLE;   //probably dont need this, but its there
        long targetTime = System.currentTimeMillis() + timeMillis;
        //wait for time to pass
        while (!(System.currentTimeMillis() >= targetTime)) {
            //do nothing
        }
        state = State.COMPLETED;
    }

    private void idle(long timeMillis) {
        idleTime = System.currentTimeMillis() + timeMillis;
        state = State.IDLE;
    }

    private void drive(float duration, float power) {
        driveDuration = duration;
        drivePower = power;
        state = State.DRIVING;
    }

    private void turn(float duration, float power) {
        turnDuration = duration;
        turnPower = power;
        state = State.TURNING;
    }

    private void turnByAngle(float angle, float power) {
        turnAngle = angle;
        turnPower = power;
        state = State.TURNINGBYANGLE;
    }
}
