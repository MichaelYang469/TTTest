package org.firstinspires.ftc.teamcode.interview;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Drive {
    DcMotorEx leftFront;
    DcMotorEx leftBack;
    DcMotorEx rightFront;
    DcMotorEx rightBack;

    public Drive(HardwareMap hardwareMap) {
        //configure motors
        leftFront= hardwareMap.get(DcMotorEx.class, "leftFront");
        leftBack = hardwareMap.get(DcMotorEx.class, "leftBack");
        rightFront = hardwareMap.get(DcMotorEx.class, "rightFront");
        rightBack = hardwareMap.get(DcMotorEx.class, "rightBack");

        // Set motor directions
        this.leftFront.setDirection(DcMotorEx.Direction.FORWARD);
        this.leftBack.setDirection(DcMotorEx.Direction.FORWARD);
        this.rightFront.setDirection(DcMotorEx.Direction.REVERSE);
        this.rightBack.setDirection(DcMotorEx.Direction.REVERSE);
    }

    public void setPower(double p1, double p2, double p3, double p4) {
        //setting power
        leftFront.setPower(p1);
        leftBack.setPower(p2);
        rightFront.setPower(p3);
        rightBack.setPower(p4);
    }

    public void setDrive(double x, double y, double rotation) {
        // Calculate power for each motor based on x, y, and rotation
        double leftFrontPower = y + x + rotation;
        double leftBackPower = y - x + rotation;
        double rightFrontPower = y - x - rotation;
        double rightBackPower = y + x - rotation;
        double denom = Math.abs(x) + Math.abs(y) + Math.abs(rotation);
        setPower(leftFrontPower/denom, leftBackPower/denom, rightFrontPower/denom, rightBackPower/denom);
    }
}
