
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

//Launcher angle
import frc.robot.Systems.Angler;

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

    //Launcher Angle
    Angler.init();

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
    
    Drivetrain.drive();
    
    if (systemsController.getRawButton(7)) {
      Intake.in();
    } else {
      Intake.stop();
    }

    if (systemsController.getRawButton(9) && systemsController.getRawButton(7)) {
      Intake.out();
    }
    
    if (systemsController.getRawButton(8)) {
      Launcher.pid(Limelight.math());
    } else {
      Launcher.stopLauncher();
    }
    
    Limelight.periodic();
    
    Angler.pid(Limelight.math());
  }

  @Override
  public void testPeriodic() {
    Launcher.rpmStatus();

    Launcher.test(driverController);

    double speed = driverController.getRawAxis(0);
    Angler.linearActuator(speed);
  }
}
