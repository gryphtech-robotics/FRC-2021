package frc.robot.Systems;

//motor control
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//joystick
import edu.wpi.first.wpilibj.Joystick;

public class Drivetrain {
    public static CANSparkMax lDrive0;
    public static CANSparkMax rDrive0;
    public static CANSparkMax lDrive1;
    public static CANSparkMax rDrive1;
    
    public static void init () {
        lDrive0 = new CANSparkMax(0, MotorType.kBrushless);
        rDrive0 = new CANSparkMax(2, MotorType.kBrushless);
        lDrive1 = new CANSparkMax(1, MotorType.kBrushless);
        rDrive1 = new CANSparkMax(3, MotorType.kBrushless);

        lDrive0.restoreFactoryDefaults();
        rDrive0.restoreFactoryDefaults();
        lDrive1.restoreFactoryDefaults();
        rDrive1.restoreFactoryDefaults();

        lDrive1.follow(lDrive0);
        rDrive1.follow(rDrive0);
    }

    public static void drive (Joystick driverController) {
        double x = driverController.getRawAxis(0);
        double y = driverController.getRawAxis(1);
        double motorThrottle = (1 + (-driverController.getRawAxis(3))) / 2;

        rDrive0.set((y + x) * motorThrottle);
        lDrive0.set(-(y - x) * motorThrottle);
    }
}