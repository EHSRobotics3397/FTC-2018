package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;

public class AutoDrive {
    private DcMotor motor1, motor2, motor3, motor4;;
    private double power[] = new double[4];
    private MecanumSolver solver;

    public AutoDrive(DcMotor m1, DcMotor m2, DcMotor m3, DcMotor m4) {
        motor1 = m1;
        motor2 = m2;
        motor3 = m3;
        motor4 = m4;
        solver = new MecanumSolver();
    }

    public void driveSideways(float pwr, long duration) {
        //this class takes its own time up
        long targetTime = System.currentTimeMillis() + duration;

        while ((targetTime - System.currentTimeMillis()) > 0) {
            Matrix W = solver.solve(0, pwr, 0);

            power[0] = W.element(0, 0);
            power[1] = W.element(1, 0);
            power[2] = W.element(2, 0);
            power[3] = W.element(3, 0);

            motor1.setPower(power[0]);
            motor2.setPower(power[1]);
            motor3.setPower(power[2]);
            motor4.setPower(power[3]);
        }

        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void drive (float pwr, long duration) {
        long targetTime = System.currentTimeMillis() + duration;
        motor1.setPower(pwr);
        motor2.setPower(pwr);
        motor3.setPower(pwr);
        motor4.setPower(pwr);
        while (targetTime - System.currentTimeMillis() > 0) {
            //do nothing
        }
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }

    public void turn (float pwr, long duration) {
        long targetTime = System.currentTimeMillis() + duration;
        motor1.setPower(pwr);
        motor2.setPower(pwr);
        motor3.setPower(-pwr);
        motor4.setPower(-pwr);
        while (targetTime - System.currentTimeMillis() > 0) {
            //do nothing
        }
        motor1.setPower(0);
        motor2.setPower(0);
        motor3.setPower(0);
        motor4.setPower(0);
    }
}
