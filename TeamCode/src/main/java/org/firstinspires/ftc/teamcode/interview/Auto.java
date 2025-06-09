package org.firstinspires.ftc.teamcode.interview;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "loopity-loop", group = "autonomous")
public class Auto extends LinearOpMode {

    Pathing pathing;

    @Override
    public void runOpMode() throws InterruptedException {
        //initializing pathing algorithm
        pathing = new Pathing(hardwareMap);

        waitForStart();
        while (opModeIsActive() && !isStopRequested()) {
            pathing.updatePosition();
            pathing.moveTo(0, 0, 90);
            telemetry.addData("Position", pathing.position);
            telemetry.update();
        }
    }
}
