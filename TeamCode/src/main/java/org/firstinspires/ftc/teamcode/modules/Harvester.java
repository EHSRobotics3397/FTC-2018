package org.firstinspires.ftc.teamcode.modules;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;

/* A Class Used to control Harvester on robot,
    Created by EHS Robotics on 11/10/18
    with code from 2017 season
 */
public class Harvester {
    //Set up motors, buttons, and numbers for mineral detection
    private DcMotor harvesterMotor;
    public boolean isTakeGold;
    private static byte MAX_MINERALS;
    private DcMotor liftMotor;
    private DcMotor deployMotor;
    private Gamepad gamepad;
    private GameButton liftButton;
    private GameButton isGoldButton;
    private GameButton isSilverButton;
    private GameButton deployButton;

    // Constructor to start auto mode if used
    private void auto() {
        // Do Nothing for now
    }

    public void setup(DcMotor hMotor, DcMotor lMotor, DcMotor dMotor, Gamepad gp) {
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
        } else if (liftButton.IsDown()) {
            // Set motors speed
        }

        if (isGoldButton.Press()) {
            isTakeGold = true;
        } else if (isSilverButton.Press()) {
            isTakeGold = false;
        }
    }

    private void deployHarvester() {
        // Nothing yet
    }
}
