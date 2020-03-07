package frc.robot.Systems;

// Import motors here
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

/**
 * This is the Launcher sub-system of the robot. It controls the ball launcher.
 * @author Axel Greavette & Sierra Thomson
 */
public class Launcher {

    public static CANSparkMax botThruster;
    public static CANSparkMax topThruster;
    public static VictorSPX SideWheel;
    public static CANSparkMax ballProtector;
    public static CANSparkMax angleSetter;

    public static CANEncoder thrusterEncoderTop;
    public static CANPIDController thrusterPIDTop;
    public static CANEncoder thrusterEncoderBot;
    public static CANPIDController thrusterPIDBot;
    public static CANEncoder protectorEncoder;
    public static CANPIDController protectorPID;
    
    public static double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public static double setPoint;
    public static double sP;
    
    public static double rpmOffset;

    /**
     * This function initializes the motors used to drive the robot.
     * To do this it assigns the CANSparkMax motors to the public variables leftThruster, rightThruster, leftSideWheel, rightSideWheel, and angleSetter.
     */
    public static void init () {
        botThruster = new CANSparkMax(5, MotorType.kBrushless);
        topThruster = new CANSparkMax(4, MotorType.kBrushless);
        ballProtector = new CANSparkMax(13, MotorType.kBrushless);

        SideWheel = new VictorSPX(0);

        botThruster.restoreFactoryDefaults();
        topThruster.restoreFactoryDefaults();

        thrusterPIDTop = topThruster.getPIDController();
        thrusterPIDBot = botThruster.getPIDController();
        protectorPID =  ballProtector.getPIDController();
        thrusterEncoderTop = topThruster.getEncoder();
        thrusterEncoderBot = botThruster.getEncoder();
       // protectorEncoder = ballProtector.getEncoder();

        topThruster.setOpenLoopRampRate(1);
        topThruster.setClosedLoopRampRate(1);
        botThruster.setOpenLoopRampRate(1);
        botThruster.setClosedLoopRampRate(1);
       // ballProtector.setOpenLoopRampRate(1);
       // ballProtector.setClosedLoopRampRate(1);

        thrusterPIDTop.setFeedbackDevice(thrusterEncoderTop);
        thrusterPIDTop.setFF(0.0002);
        thrusterPIDTop.setP(0);

        thrusterPIDBot.setFeedbackDevice(thrusterEncoderBot);
        thrusterPIDBot.setFF(0.0002);
        thrusterPIDBot.setP(0.0001);
        
       /* protectorPID.setFeedbackDevice(protectorEncoder);
        protectorPID.setFF(0.0002);
        protectorPID.setP(0);
        */
        sP = 0;
        
        rpmOffset = 0;
        
        // initiateBallProtector();
    }
    

    /**
     * This function starts the launcher's thruster wheels and sets them to 100% speed.
     */
    public static void startThrusterWheels () {
        botThruster.set(.5);
        topThruster.set(.5);
    }

    /**
     * This function starts the launcher's side wheels, and sets them to 100% speed.
     */
    public static void startSideWheels () {
        SideWheel.set(ControlMode.PercentOutput, 1);
    }

    /**
     * This function stops the launcher's side wheels, setting them to 0% speed.
     */
    public static void stopSideWheels () {
        SideWheel.set(ControlMode.PercentOutput, 0);
    }

    /**
     * This function stops all wheel motors.
     */
    public static void stopLauncher () {
        botThruster.stopMotor();
        topThruster.stopMotor();

        stopSideWheels(); }

    /**
     * This function retrieves the RPM of both thruster motors at any given time.
     */
    public static void rpmStatus (Joystick controller) {
        SmartDashboard.putNumber("Calculated top rpm: ", -sP);
        SmartDashboard.putNumber("Calculated bot rpm: ", sP);
        SmartDashboard.putNumber("Actual top rpm:", topThruster.getEncoder().getVelocity());
        SmartDashboard.putNumber("Actual bot rpm:", botThruster.getEncoder().getVelocity());
        //SmartDashboard.putNumber("Ball Protector Position: ", ballProtector.getEncoder().getPosition());
       // SmartDashboard.putBoolean("Ball Protector In Position? ", ballProtector.getEncoder().getPosition() == 0.128 ? true : false);

        if (controller.getRawButton(5)){
            rpmOffset += 20;
        }
        if (controller.getRawButton(6)){
            rpmOffset -= 20;
        }
    }

    /**
     * This function will simply work.
     */
    public static void theRealThing (double distance) {
        //setPoint = (-30.465 * distance) + 1661.02;
        setPoint = 1575;
        startSideWheels();
        thrusterPIDBot.setReference(setPoint + rpmOffset, ControlType.kVelocity);
        thrusterPIDTop.setReference(-(setPoint + rpmOffset), ControlType.kVelocity);
    }

    /**
     * This function takes the set value (set through Smart Dashboard) and sets it as the reference point for the thrusters PID.
     */
    public static void toGetTestValues () {
        sP = SmartDashboard.getNumber("calculated bot rpm: ", 1000);

        thrusterPIDBot.setReference(sP, ControlType.kVelocity);
        thrusterPIDTop.setReference(-sP, ControlType.kVelocity);
    }

    public static void initiateBallProtector () {
        ballProtector.set(1);
        Timer.delay(.2);
        ballProtector.set(0);
    }

    public static void pullBackBallProtector () {
        ballProtector.set(-1);
        Timer.delay(.2);
        ballProtector.set(0);
    }
}