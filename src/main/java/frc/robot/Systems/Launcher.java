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
        leftThruster = new CANSparkMax(4, MotorType.kBrushless);
        rightThruster = new CANSparkMax(5, MotorType.kBrushless);
        leftSideWheel = new CANSparkMax(6, MotorType.kBrushless);
        rightSideWheel = new CANSparkMax(7, MotorType.kBrushless);
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
    }

    public static void startSideWheels () {

        leftSideWheel.follow(rightSideWheel);
        
        rightSideWheel.set(1);
    }
}