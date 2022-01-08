package frc.robot.Systems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math;

/**
 * This function controls and monitors limelight inputs and uses trigonometetryssdsdfsdf 
 * @author Sierra Thomson
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

    /**
     * This function initializes the Limelight.
     * To do this it connects to a Network Table instance and addes the entries required.
     */
    public static void init () {
        NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
        tx = table.getEntry("tx");
        ty = table.getEntry("ty");
        ta = table.getEntry("ta");
        tv = table.getEntry("tv");
        table.getEntry("ledMode").setNumber(3);
    }

    /**
     * This is the periodic function for the Limelight.
     * It updates the values needed for the Launcher and displays them.
     */
    public static void periodic () {
        //read values periodically
        limeX = tx.getDouble(0.0);
        limeY = ty.getDouble(0.0);
        limeArea = ta.getDouble(0.0);
        limeTarget = ta.getBoolean(false);
        //post to smart dashboard periodically
        SmartDashboard.putNumber("LimelightX: ", limeX);
        SmartDashboard.putNumber("LimelightY: ", limeY);
        SmartDashboard.putNumber("LimelightArea: ", limeArea);
        SmartDashboard.putBoolean("Target acquired? ", limeTarget);
        
        distance = (targetHeight - cameraHeight) / Math.tan(Math.toRadians(mountAngle) - Math.toRadians(limeY));

        SmartDashboard.putNumber("Distance: ", distance);
   }

   /**
    * This function returns the distance to the target selcted by the Limelight.
    */
   public static double math () {
        return distance; 
   }
}