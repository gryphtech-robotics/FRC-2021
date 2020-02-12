package frc.robot.Systems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math;

public class Limelight {
    public static NetworkTableEntry tx;
    public static NetworkTableEntry ty;
    public static NetworkTableEntry ta;

    public static double limeX;
    public static double limeY;
    public static double limeArea;

    // Initialization code
    public static void init () {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
    }

    public static void periodic () {
        //read values periodically
        limeX = tx.getDouble(0.0);
        limeY = ty.getDouble(0.0);
        limeArea = ta.getDouble(0.0);
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", limeX);
        SmartDashboard.putNumber("LimelightY", limeY);
        SmartDashboard.putNumber("LimelightArea", limeArea);
   }

   public static double math () {
        double cameraHeight = 420; // Funny weed number!! :LAUGHING FACE EMOGY:  BLAME AXEL FOR THIS JOKE
        double targetHeight = 36;
    
        double mountAngle = 420; // Funny weed number!! :LAUGHING FACE EMOGY: BLAME AXEL FOR THIS WEEK

        double distance = (targetHeight - cameraHeight) / Math.tan(Math.toRadians(mountAngle) - Math.toRadians(limeY));

        return distance;
   }
}