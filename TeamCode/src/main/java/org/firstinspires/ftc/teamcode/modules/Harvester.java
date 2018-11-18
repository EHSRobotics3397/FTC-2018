package org.firstinspires.ftc.teamcode.modules;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

/* A Class Used to control Harvester on robot,
    Created by EHS Robotics on 11/10/18
    with code from 2017 season
 */
public class Harvester {
    //Set up motors, buttons, and numbers for mineral detection
    public static boolean IS_GOLD;
    public static boolean IS_SILVER;
    public static double MIN_DISTANCE;  // The minimum distance a mineral can be from the robot to pick up blocks
    public static float[] YELLOW;
    public static float[] WHITE;
    public static double POWER;
    private DcMotor harvesterMotor;
    private boolean mineral;
    private byte minerals;
    private static byte MAX_MINERALS;
    private DcMotor liftMotor;
    private DcMotor deployMotor;
    private Gamepad gamepad;
    private GameButton liftButton;
    private GameButton isGoldButton;
    private GameButton isSilverButton;
    private GameButton deployButton;
    private ColorSensing colorSensor;
    private ColorSensor color;
    private DistanceSensor distance;

    // Constructor to start auto mode if used
    private void auto() {
        // Do Nothing for now
    }

    public void setup(DcMotor hMotor, DcMotor lMotor, DcMotor dMotor, Gamepad gp, ColorSensor cs, DistanceSensor ds) {
        // Set motors to those inputted
        harvesterMotor = hMotor;
        liftMotor = lMotor;
        gamepad = gp;
        deployMotor = dMotor;

        // Set up GameButtons to work with the dpad
        liftButton = new GameButton(gamepad, GameButton.Label.dpadUp);
        deployButton = new GameButton(gamepad, GameButton.Label.dpadDown);
        isGoldButton = new GameButton(gamepad, GameButton.Label.dpadRight);
        isSilverButton = new GameButton(gamepad, GameButton.Label.dpadLeft);

        IS_GOLD = true;
        IS_SILVER = false;
        MIN_DISTANCE = 0.5;
        MAX_MINERALS = 2;
        POWER = 0;
        YELLOW = new float[3]; //No clue what this should be
        YELLOW[0] = 0;
        YELLOW[1] = 0;
        YELLOW[2] = 0;
        WHITE = new float[3];
        WHITE[0] = 0;
        WHITE[1] = 0;
        WHITE[2] = 0;

        colorSensor = new ColorSensing();
        colorSensor.setup(cs, ds);
    }

    public void update() {
        // Update all buttons
        liftButton.Update();
        deployButton.Update();
        isGoldButton.Update();
        isSilverButton.Update();

        // Test if buttons are pressed Press only happens once
        if (deployButton.Press()) {
            deployHarvester();
        } else if (liftButton.Press()) {
            liftHarvester();
        }

        if (isGoldButton.Press()) {
            mineral = IS_GOLD;
        } else if (isSilverButton.Press()) {
            mineral = IS_SILVER;
        }

        // Test if object is in front of robot
        colorSensor.update();
        float[] color = colorSensor.getHue();
        if ((colorSensor.getDistance() <= MIN_DISTANCE) & (minerals < MAX_MINERALS)) {
            if (color == YELLOW) {
                if (mineral == IS_GOLD) {
                    // Turn on harvester
                    engageHarvester("forward");
                } else {
                    engageHarvester("backward");
                }
            } else if (color == WHITE) {
                if (mineral == IS_SILVER) {
                    engageHarvester("forward");
                }
            }
        }

    }

    private void deployHarvester() {
        /*
        Set motors speed and stuffs
        can't program this till
        robot is built
         */
    }

    private void liftHarvester() {
        /*
        Set motors speed and stuffs
        also cant program until
        robot is built
         */
    }

    private void engageHarvester(String direction) {
        if (direction.equals("forward")) {
            harvesterMotor.setDirection(DcMotor.Direction.FORWARD);
            harvesterMotor.setPower(POWER);
        } else if (direction.equals("backward")) {
            harvesterMotor.setDirection(DcMotor.Direction.REVERSE);
            harvesterMotor.setPower(POWER);
        } else {
            // Do nothing
        }
    }
}
