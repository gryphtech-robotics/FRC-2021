package frc.robot.Systems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//import com.revrobotics.CANEncoder;
//import com.revrobotics.CANPIDController;
//import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.smartdashboard.*;

/**
 * This class controls our robot's elevator.
 * @author Axel Greavette & Sierra Thomson
 */
public class Elevator {
    public static CANSparkMax elevator;
    

    public static void init() {
        elevator = new CANSparkMax(9, MotorType.kBrushed);
        elevator.restoreFactoryDefaults();
        
    }

    //Motor gear ratio = 50:1

    public static void elevate () { 
        
        elevator.set(.5);
    }

    public static void deElevate() {
        elevator.set(-.5);
    }

    public static void stopElevator () {
        elevator.stopMotor();
    }

    public static void elevatorStatus () {
        SmartDashboard.putNumber("Climb rotations: " , elevator.getEncoder().getPosition());
    }
}