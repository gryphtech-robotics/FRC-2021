package frc.robot.Systems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math;

/**
 * This function controls and monitors limelight inputs and uses trigonometetryssdsdfsdf 
 */
public class Limelight {
    public static NetworkTableEntry tx;
    public static NetworkTableEntry ty;
    public static NetworkTableEntry ta;
    public static NetworkTableEntry tv;

    public static double limeX;
    public static double limeY;
    public static double limeArea;
    public static boolean limeTarget;

    public static double cameraHeight = 0.4572; 
    public static double targetHeight = 2.49;
    public static double mountAngle = 28;
    public static double distance; 

    // Initialization code
    public static void init () {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        tv = table.getEntry("tv");
    
    }

    public static void periodic () {
        //read values periodically
        limeX = tx.getDouble(0.0);
        limeY = ty.getDouble(0.0);
        limeArea = ta.getDouble(0.0);
        limeTarget = ta.getBoolean(false);
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX", limeX);
        SmartDashboard.putNumber("LimelightY", limeY);
        SmartDashboard.putNumber("LimelightArea", limeArea);
        SmartDashboard.putBoolean("Target acquired? ", limeTarget);
        
        distance = (targetHeight - cameraHeight) / Math.tan(Math.toRadians(mountAngle) - Math.toRadians(limeY));

        SmartDashboard.putNumber("distance: ", distance);
   }

   public static double math () {
        return distance; 
   }
}