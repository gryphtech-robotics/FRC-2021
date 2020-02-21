package frc.robot.Systems;

// Import motors here
// Todo because idk what we're using atm
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


/**
 * This is the Launcher sub-system of the robot. It controls the ball launcher.
 * @author Axel Greavette & Sierra Thomson
 */
public class Launcher {

    public static CANSparkMax leftThruster;
    public static CANSparkMax rightThruster;
    public static CANSparkMax leftSideWheel;
    public static CANSparkMax rightSideWheel;
    public static CANSparkMax angleSetter;

    /**
     * This function initializes the motors used to drive the robot.
     * To do this it assigns the CANSparkMax motors to the public variables leftThruster, rightThruster, leftSideWheel, rightSideWheel, and angleSetter.
     */
    public static void wheelsInit () {
        leftThruster = new CANSparkMax(4, MotorType.kBrushless);
        rightThruster = new CANSparkMax(5, MotorType.kBrushless);
        leftSideWheel = new CANSparkMax(6, MotorType.kBrushless);
        rightSideWheel = new CANSparkMax(7, MotorType.kBrushless);
        angleSetter = new CANSparkMax(8, MotorType.kBrushless);
    }
    
    /**
     * This function sets the wheels in motion. 
     * To do this it first starts the thruster wheels, waits five (5) seconds, then proceeds to start the side wheels.
     */
    public static void startLauncher () {
        try {
            startThrusterWheels();
            Thread.sleep(5000);
            startSideWheels();
        } catch (InterruptedException err) {
            Thread.currentThread().interrupt();
        }
        
    }

    /**
     * This function starts the launcher's thruster wheels and sets them to 100% speed.
     */
    public static void startThrusterWheels () {

        leftThruster.follow(rightThruster);
        
        rightThruster.set(1);
    }

    /**
     * This function starts the launcher's side wheels, and sets them to 100% speed.
     */
    public static void startSideWheels () {

        leftSideWheel.follow(rightSideWheel);
        
        rightSideWheel.set(1);
    }
}