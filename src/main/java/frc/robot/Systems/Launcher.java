package frc.robot.Systems;

// Import motors here
// Todo because idk what we're using atm
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Launcher {
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
        //WE NEED TO FILL THIS IN WITH OUR MOTORS
        CANSparkMax left = new CANSparkMax(6, MotorType.kBrushless);
        CANSparkMax right = new CANSparkMax(7, MotorType.kBrushless);

        left.follow(right);
        
        right.set(1);

        left.close();
        right.close();
    }

    public static void startSideWheels () {
        //WE REALLY NEED TO CHANGE THESE MOTORS TOO
        CANSparkMax left = new CANSparkMax(8, MotorType.kBrushless);
        CANSparkMax right = new CANSparkMax(9, MotorType.kBrushless);

        left.follow(right);
        
        right.set(1);

        left.close();
        right.close();
    }
}