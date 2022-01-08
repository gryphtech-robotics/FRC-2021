package frc.robot.Systems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * This class controls the intake motors and structure on our robot. 
 * @author Axel Greavette & Sierra Thomson
 */
public class Intake {

    public static CANSparkMax intakeMotors;

    /**
     * This function initializes the intake motors.
     */
    public static void init () {        
        intakeMotors = new CANSparkMax(6, MotorType.kBrushless);
    }

    /**
     * This function reverses the intake motors, effectively "sucking in the balls".
     */
    public static void in () {
        intakeMotors.set(-0.5);
    }    

    /**
     * This function spins the intake motors, effectively "spitting out the balls".
     */
    public static void out () {
        intakeMotors.set(0.5);

        
    }

    /**
     * This function stops all intake motors.
     */
    public static void stop () {
        intakeMotors.set(0);
    }
}