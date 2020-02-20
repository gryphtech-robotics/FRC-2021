/**
 * Copyright 2020 Gryphtech Robotics
 */

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

//limelight
import frc.robot.Systems.Limelight;

//drive!
import frc.robot.Systems.Drivetrain;

//intake
import frc.robot.Systems.Intake;

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

  @Override
  public void robotInit() {
    System.out.println("I happen to exist -");
    System.out.println(("Cogito, ergo, sum"));
    m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
    m_chooser.addOption("My Auto", kCustomAuto);
    SmartDashboard.putData("Auto choices", m_chooser);

    //Launcher
    Launcher.wheelsInit();

    //Intake
    Intake.initIntake();
    
    //motor control
    Drivetrain.init();

    //Driver control
    driverController = new Joystick(0);

    //Limelight
    Limelight.init();
  }

  @Override
  public void robotPeriodic() {
    
  }

  @Override
  public void autonomousInit() {
    m_autoSelected = m_chooser.getSelected();
    // vam_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  @Override
  public void autonomousPeriodic() {
  
    }
  }

  @Override
  public void teleopPeriodic() {

    Drivetrain.drive(driverController);

    if (driverController.getRawButton(#)) {
      Intake.in();
    }    
    if (driverController.getRawButton(#) {
      Launcher.startLauncher();
    }
    Limelight.periodic();
  }

  @Override
  public void testPeriodic() {
  }
}