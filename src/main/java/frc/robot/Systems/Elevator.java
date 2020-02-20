package frc.robot.Systems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Elevator {
    public static CANSparkMax elevator;

    public static void elevatorInit() {
        elevator = new CANSparkMax(8, MotorType.kBrushless);
        elevator.restoreFactoryDefaults();
    }

    public static void elevate () { 
        //(NEED TO MAKE ELEVATOR STOP AT MAX HEIGHT)
        elevator.set(1);
    }

    public static void deElevate() {
        elevator.set(-1);
    }

    public static void stopElevator () {
        elevator.set(0);
    }
}