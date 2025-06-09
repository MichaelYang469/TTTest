package org.firstinspires.ftc.teamcode.interview;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.pinpoint.GoBildaPinpointDriver;

public class Pathing {
    private Drive drive;
    GoBildaPinpointDriver odo;
    Pose2D position;
    private static final float ticks_per_rev = 74.5027f;

    public Pathing(HardwareMap hardwareMap) {
        //initializing odo + drive
        drive = new Drive(hardwareMap);
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        // DO NOT TOUCH THE FOLLOWING LINES
        odo.setOffsets(131.0, 65.0, DistanceUnit.MM);
        odo.setEncoderResolution(ticks_per_rev);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();
    }

    public void updatePosition() {
        // Update the position based on odometry readings
        position = odo.getPosition();
    }

    public void moveTo(double x, double y, double heading) { //mm, mm, degrees
        // Calculate the difference between the current position and the target position
        double deltaX = x + position.getX(DistanceUnit.MM);
        double deltaY = y - position.getY(DistanceUnit.MM);
        double deltaHeading = heading + position.getHeading(AngleUnit.DEGREES);

        drive.setDrive(deltaY, deltaX, deltaHeading);
    }
}
