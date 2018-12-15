package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.modules.IMURotate;
import org.firstinspires.ftc.teamcode.modules.MecanumAutoDrive;

@Autonomous (name = "Motor Test", group = "Drive")
public class MotorTestOp extends OpMode {
    private DcMotor motor1;
    private DcMotor motor2;
    private DcMotor motor3;
    private DcMotor motor4;
    private long startTime;
    private long targetTime;

    public void init() {
        motor1 = hardwareMap.dcMotor.get("motor1");
        motor2 = hardwareMap.dcMotor.get("motor2");
        motor3 = hardwareMap.dcMotor.get("motor3");
        motor4 = hardwareMap.dcMotor.get("motor4");

        motor3.setDirection(DcMotor.Direction.REVERSE);
        motor4.setDirection(DcMotor.Direction.REVERSE);

        targetTime = 1000;
        startTime = System.currentTimeMillis();
    }

    @Override
    public void loop() {
        if (System.currentTimeMillis() - startTime <= targetTime) {
            // set motors power
            motor1.setPower(50);
            motor2.setPower(50);
            motor3.setPower(50);
            motor3.setPower(50);
        } else {
            motor1.setPower(0);
            motor2.setPower(0);
            motor3.setPower(0);
            motor4.setPower(0);
        }
    }

}
