
/**
 * Copyright 2020 Gryphtech Robotics
 * # axelsgreat
 */

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;

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

  // Initialize Joysticks
  public Joystick driverController;
  public Joystick systemsController;

  @Override
  public void robotInit() {
    System.out.println("I happen to exist -");
    System.out.println(("Cogito, ergo, sum"));

    // Joysticks
    driverController = new Joystick(0);
    systemsController = new Joystick(1);

    
    //Launcher
    Launcher.init();

    //Intake
    Intake.init();
    
    //motor control
    Drivetrain.init(driverController);

    //Limelight
    Limelight.init();

    //Driver Camera
    CameraServer.getInstance().startAutomaticCapture();

  }

  @Override
  public void robotPeriodic() {
    
  }

  @Override
  public void autonomousInit() {
   
  }

  @Override
  public void autonomousPeriodic() {
  
  }

  @Override
  public void teleopPeriodic() {
    boolean intakeActive = false;

    Drivetrain.drive();
    if (systemsController.getRawButton(2)) {
      intakeActive = !intakeActive;
    }
    
    if (intakeActive == true) {
      Intake.in();
    }  else {
      Intake.stop();
    }  

    if (systemsController.getRawButton(8)) {
      Launcher.startLauncher();
    }
    Limelight.periodic();
  }

  @Override
  public void testPeriodic() {
  }
}
