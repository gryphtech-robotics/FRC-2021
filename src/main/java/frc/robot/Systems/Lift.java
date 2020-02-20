package frc.robot.Systems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Lift {
    public static CANSparkMax liftLeft;
    public static CANSparkMax liftRight;

    public static void liftInit() {
        liftLeft = new CANSparkMax(10, MotorType.kBrushless);
        liftRight = new CANSparkMax(12, MotorType.kBrushless);
    }
    public static void liftUp(){
        liftLeft.set(1);
        liftRight.follow(liftLeft);

    }
    public static void liftDown(){
        liftLeft.set(-1);
        liftRight.follow(liftLeft);

    }
}