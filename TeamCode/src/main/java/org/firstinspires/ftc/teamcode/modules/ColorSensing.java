package org.firstinspires.ftc.teamcode.modules;

import android.graphics.Color;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.ColorSensor;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;


/**
 * Created by greenteam on 1/27/18.
 */

public class ColorSensing {
    private ColorSensor sensorColor;
    private DistanceSensor sensorDistance;
    private double distance;
    private int alpha;
    private int red;
    private int green;
    private int blue;
    private float[] hue;

    public void setup(ColorSensor aSensor, DistanceSensor aDistanceSensor)
    {
        sensorColor = aSensor;
        sensorDistance = aDistanceSensor;
    }

    public void update()
    {
        final double SCALE_FACTOR = 255;
        float hsvValues[] = {0F, 0F, 0F};

        int alph = (int)(sensorColor.alpha() * SCALE_FACTOR);
        int rd = (int)(sensorColor.red() * SCALE_FACTOR);
        int gren = (int)(sensorColor.green() * SCALE_FACTOR);
        int ble = (int)(sensorColor.blue() * SCALE_FACTOR);

        Color.RGBToHSV((red), (green), (blue), hsvValues);

        distance = sensorDistance.getDistance(DistanceUnit.CM);
        alpha = alph;
        red = rd;
        green = gren;
        blue = ble;
        hue = hsvValues;

    }

    public int[] getColors() {
        int[] colors = {alpha, red, green, blue};
        return colors;
    }

    public float[] getHue() {
        return hue;
    }

    public double getDistance() {
        return distance;
    }
}
