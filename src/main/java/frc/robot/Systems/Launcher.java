package frc.robot.Systems;

// Import motors here
// Todo because idk what we're using atm
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Launcher {
    public static CANSparkMax leftThruster;
    public static CANSparkMax rightThruster;
    public static CANSparkMax leftSideWheel;
    public static CANSparkMax rightSideWheel;

    public static void wheelsInit () {
        //WE NEED TO FILL THIS IN WITH OUR MOTORS
        leftThruster = new CANSparkMax(6, MotorType.kBrushless);
        rightThruster = new CANSparkMax(7, MotorType.kBrushless);
        //WE REALLY NEED TO CHANGE THESE MOTORS TOO
        leftSideWheel = new CANSparkMax(8, MotorType.kBrushless);
        rightSideWheel = new CANSparkMax(9, MotorType.kBrushless);
    }
    public static void startLauncher () {
        try {
            startThrusterWheels();
            Thread.sleep(5000);
            startSideWheels();
        } catch (InterruptedException err) {
            Thread.currentThread().interrupt();
        }
        
    }

    public static void startThrusterWheels () {

        leftThruster.follow(rightThruster);
        
        rightThruster.set(1);

        leftThruster.close();
        rightThruster.close();
    }

    public static void startSideWheels () {

        leftSideWheel.follow(rightSideWheel);
        
        rightSideWheel.set(1);

        leftSideWheel.close();
        rightSideWheel.close();
    }
}