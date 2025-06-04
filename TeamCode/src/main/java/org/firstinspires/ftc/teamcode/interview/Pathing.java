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

    public Pathing(HardwareMap hardwareMap) {
        //initializing odo + drive
        drive = new Drive(hardwareMap);
        odo = hardwareMap.get(GoBildaPinpointDriver.class, "odo");
        // Set the odometry pod positions relative to the point that the odometry computer tracks around.
        odo.setOffsets(-84.0, -168.0, DistanceUnit.MM); //these are tuned for 3110-0002-0001 Product Insight #1
        odo.setEncoderResolution(GoBildaPinpointDriver.GoBildaOdometryPods.goBILDA_SWINGARM_POD);
        odo.setEncoderDirections(GoBildaPinpointDriver.EncoderDirection.FORWARD, GoBildaPinpointDriver.EncoderDirection.FORWARD);
        odo.resetPosAndIMU();
    }

    public void updatePosition() {
        // Update the position based on odometry readings
        position = odo.getPosition();
    }

    public void moveTo(double x, double y, double heading) { //mm, mm, degrees
        // Calculate the difference between the current position and the target position
        double deltaX = x - position.getX(DistanceUnit.MM);
        double deltaY = y - position.getY(DistanceUnit.MM);
        double deltaHeading = heading - position.getHeading(AngleUnit.DEGREES);

        drive.setDrive(deltaX, deltaY, deltaHeading);
    }
}
