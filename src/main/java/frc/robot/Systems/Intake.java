package frc.robot.Systems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Intake {

    public static CANSparkMax intakeMotors;

    public static void initIntake () {
        
        intakeMotors = new CANSparkMax(11, MotorType.kBrushless);
    }

    public static void in () {

        intakeMotors.set(-1);

        intakeMotors.close();
    }    

    public static void out () {

        intakeMotors.set(1);

        intakeMotors.close();
    }

    public static void stop () {
        intakeMotors.set(0);
    }
}