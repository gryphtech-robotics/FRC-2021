
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

//climb
import frc.robot.Systems.Elevator;

public class Robot extends TimedRobot {

  // Initialize Joysticks
  public Joystick driverController;
  public Joystick systemsController;

  public static boolean go = false;

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

    Elevator.init();

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
    
    //True launcher code - comment out while testing
     if (systemsController.getRawButton(8)) {
      Launcher.theRealThing(Limelight.math());
    } else {
      Launcher.stopLauncher();
    }
    
    //Test launcher code - comment out for competition
    Launcher.toGetTestValues();

    Launcher.rpmStatus();
    
    Limelight.periodic();

    /* if (systemsController.getRawButton(1)){
      Elevator.elevate();
    }
    if (systemsController.getRawButton(2)){
      Elevator.deElevate();
    }
    */
  }

  @Override
  public void testPeriodic() {
  
  }
}
