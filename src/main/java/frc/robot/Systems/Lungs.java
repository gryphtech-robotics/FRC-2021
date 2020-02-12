package frc.robot.Systems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Lungs {

    public static CANSparkMax xtendedLungs;

    public static void extendlungs() {
        
        xtendedLungs = new CANSparkMax(11, MotorType.kBrushless);
    }

    public static void inhale () {

        xtendedLungs.set(-1);

        xtendedLungs.close();
    }    

    public static void exhale () {

        xtendedLungs.set(1);

        xtendedLungs.close();
    }

    public static void removeLungExtensions () {
        xtendedLungs.set(0);
    }
}