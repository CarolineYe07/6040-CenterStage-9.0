package org.firstinspires.ftc.teamcode;

import android.util.Size;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.Camera;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

@TeleOp
public class FirstAprilTagTest extends LinearOpMode {

    private AprilTagProcessor tagProcessor;
    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();

    @Override
    public void runOpMode() throws InterruptedException {

        // the AprilTagProcessor extracts data from images
        // these method names are fairly self explanatory and help display vision
        tagProcessor = new AprilTagProcessor.Builder()
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .build();

        // VisionPortalProcessor
        VisionPortal visionPortal = new VisionPortal.Builder()
                .addProcessor(tagProcessor)
                .setCamera(hardwareMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .enableLiveView(true)
                .build();



        waitForStart();

        while (opModeIsActive()) {
            telemetry.addLine("OpMode is running");
            // this condition checks whether an AprilTag is seen
            // I assume .getDetections() returns a list of all detected tags
            if (tagProcessor.getDetections().size() > 0) {
                telemetry.addLine("something is detected");
                // get the first detected tag
                AprilTagDetection tag = tagProcessor.getDetections().get(0);

                // retrieves a ton of position data
                // tag.ftcPose

                telemetry.addData("x", tag.ftcPose.x); // left and right
                telemetry.addData("y", tag.ftcPose.y); // straight out
                telemetry.addData("z", tag.ftcPose.z); // up and down
                telemetry.addData("pitch", tag.ftcPose.pitch); // rotation around x
                telemetry.addData("roll", tag.ftcPose.roll); // rotation around y axis
                telemetry.addData("yaw", tag.ftcPose.yaw); // rotation around z axis
                telemetry.addData("range", tag.ftcPose.range); // distance to the tag
                telemetry.addData("bearing", tag.ftcPose.bearing); // rotation <- -> to align with tag
                telemetry.addData("elevation", tag.ftcPose.elevation); // rotation ^ and down to align with tag


                dashboardTelemetry.addData("x", tag.ftcPose.x); // left and right
                dashboardTelemetry.addData("y", tag.ftcPose.y); // straight out
                dashboardTelemetry.addData("z", tag.ftcPose.z); // up and down
                dashboardTelemetry.addData("pitch", tag.ftcPose.pitch); // rotation around x
                dashboardTelemetry.addData("roll", tag.ftcPose.roll); // rotation around y axis
                dashboardTelemetry.addData("yaw", tag.ftcPose.yaw); // rotation around z axis
                dashboardTelemetry.addData("range", tag.ftcPose.range); // distance to the tag
                dashboardTelemetry.addData("bearing", tag.ftcPose.bearing); // rotation <- -> to align with tag
                dashboardTelemetry.addData("elevation", tag.ftcPose.elevation); // rotation ^ and down to align with tag

            }

            telemetry.update();
            dashboardTelemetry.update();
        }
    }
}
