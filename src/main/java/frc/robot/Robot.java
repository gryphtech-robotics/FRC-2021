package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//motor control
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//Driver control
import edu.wpi.first.wpilibj.Joystick;

//camera
import edu.wpi.first.cameraserver.CameraServer;

//launcher
import frc.robot.Systems.Launcher;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  private String m_autoSelected;
  private final SendableChooser<String> m_chooser = new SendableChooser<>();

  //motor control
  private CANSparkMax lDrive0;
  private CANSparkMax rDrive0;
  private CANSparkMax lDrive1;
  private CANSparkMax rDrive1;

  //Driver Control
  public Joystick driverController;


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //motor control
    lDrive0 = new CANSparkMax(1, MotorType.kBrushless);
    rDrive0 = new CANSparkMax(4, MotorType.kBrushless);
    lDrive1 = new CANSparkMax(2, MotorType.kBrushless);
    rDrive1 = new CANSparkMax(5, MotorType.kBrushless);

    lDrive0.restoreFactoryDefaults();
    rDrive0.restoreFactoryDefaults();
    lDrive1.restoreFactoryDefaults();
    rDrive1.restoreFactoryDefaults();

    lDrive1.follow(lDrive0);
    rDrive1.follow(rDrive0);

    //Driver control
    driverController = new Joystick(0);

    //camera
    CameraServer.getInstance().startAutomaticCapture();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    double x = driverController.getRawAxis(0);
    double y = driverController.getRawAxis(1);
    double motorThrottle = (1+(-driverController.getRawAxis(3)))/2;

    rDrive0.set((y+x)*motorThrottle);
    lDrive0.set(-(y-x)*motorThrottle);

    //LAUNCHER IN THEORY SHOULD WORKETH IF THE LORD WILLS IT
    Launcher.startLauncher();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
