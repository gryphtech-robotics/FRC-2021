
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
    System.out.println(("Cogito, ergo sum"));

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
    Launcher.auto();
  }

  @Override
  public void teleopPeriodic() {
    
    Drivetrain.drive();

    if (driverController.getRawButton(1)) {
      Intake.in();
    } else {
      Intake.stop();
    }

    if (driverController.getRawButton(1) && driverController.getRawButton(2)) {
      Intake.out();
    }
    
    //True launcher code - comment out while testing
     if (systemsController.getRawButton(8)) {
      Launcher.theRealThing(Limelight.math());
    } else {
      Launcher.stopLauncher();
    }

    if (systemsController.getRawButton(4)) {
      Launcher.initiateBallProtector();
    }
    if (systemsController.getRawButton(2)){
      Launcher.pullBackBallProtector();
    }
    
    if (systemsController.getRawButtonPressed(3)){
      Launcher.pullBackBallProtector();
    } else if (systemsController.getRawButtonReleased(3)) {
      Launcher.initiateBallProtector();
    }
    
    //Test launcher code - comment out for competition
    //Launcher.toGetTestValues();

    Launcher.rpmStatus(systemsController);
    
    Limelight.periodic();

     if (driverController.getRawButton(3)){
      Elevator.elevate();
    }

    if (driverController.getRawButton(4)){
      Elevator.deElevate();
    }
    
    if (driverController.getRawButton(6)){
      Elevator.stopElevator();
    }
/*
    if (systemsController.getRawButton(5)){
      Launcher.startSideWheels();
      Launcher.startThrusterWheels();
    }else{
      Launcher.stopSideWheels();
      Launcher.stopLauncher();
    } */
  } 

  @Override
  public void testPeriodic() {
  
  }
}
