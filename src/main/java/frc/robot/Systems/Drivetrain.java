package frc.robot.Systems;

//motor control
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//joystick
import edu.wpi.first.wpilibj.Joystick;


/**
 * This class controls and adds methods to control the drivetrain motors.
 * @author Axel Greavette & Sierra Thomson
 */
public class Drivetrain {
    public static Joystick driverController;

    public static CANSparkMax lDrive0;
    public static CANSparkMax rDrive0;
    public static CANSparkMax lDrive1;
    public static CANSparkMax rDrive1;
    
    /**
     * This function initializes the CANSparkMax motors that control the drivetrain, and assigns them to public variables lDrive0, lDrive1, rDrive0, and rDrive1.
     * @param {Joystick} controller The Contoller to use for button mapping and axis.
     */
    public static void init (Joystick controller) {
        driverController = controller;

        lDrive0 = new CANSparkMax(1, MotorType.kBrushless);
        rDrive0 = new CANSparkMax(2, MotorType.kBrushless);
        lDrive1 = new CANSparkMax(7, MotorType.kBrushless);
        rDrive1 = new CANSparkMax(3, MotorType.kBrushless);

        lDrive0.restoreFactoryDefaults();
        rDrive0.restoreFactoryDefaults();
        lDrive1.restoreFactoryDefaults();
        rDrive1.restoreFactoryDefaults();

        lDrive1.follow(lDrive0);
        rDrive1.follow(rDrive0);
    }

    /**
     * This function drives the drivetrain.
     */
    public static void drive () {
        double x = driverController.getRawAxis(0);
        double y = driverController.getRawAxis(1);
        double motorThrottle = (1 + (-driverController.getRawAxis(3))) / 2;
        
            rDrive0.set((y + x) * motorThrottle);
            lDrive0.set(-(y - x) * motorThrottle);
    }

    /**
     * This is our autonomous drive code.
     */
    public static void auto () {
        System.out.println("hoo haa auto maa");
        try {
            lDrive0.set(.10);
            rDrive0.set(-.10);
            Thread.sleep(10000);
            lDrive0.set(0);
            rDrive0.set(0);
        } catch (InterruptedException err) {
            Thread.currentThread().interrupt();
        }
    }
}