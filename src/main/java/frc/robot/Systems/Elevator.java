package frc.robot.Systems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Elevator {
    public static CANSparkMax strongBoi;

    public static void elevatorInit() {
        strongBoi = new CANSparkMax(9, MotorType.kBrushless);
        strongBoi.restoreFactoryDefaults();
        
    }
    public static void elevate () {  // LAZY PROGRAMMERS FIX THIS LATER (NEED TO MAKE ELEVATOR STOP AT MAX HEIGHT)
        strongBoi.set(1);
    }
    public static void deElevate() {
        strongBoi.set(-1);
    }
    public static void stopElevator () {
        strongBoi.set(0);
    }
}