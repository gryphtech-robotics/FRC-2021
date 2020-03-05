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

import java.lang.Math;

//joystick
import edu.wpi.first.wpilibj.Joystick;

/**
 * This is the Launcher sub-system of the robot. It controls the ball launcher.
 * @author Axel Greavette & Sierra Thomson
 */
public class Launcher {

    public static CANSparkMax botThruster;
    public static CANSparkMax topThruster;
    public static VictorSPX SideWheel;
    public static CANSparkMax angleSetter;

    public static CANEncoder thrusterEncoderTop;
    public static CANPIDController thrusterPIDTop;
    public static CANEncoder thrusterEncoderBot;
    public static CANPIDController thrusterPIDBot;
    public static double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public static double setPoint;
    public static double sP;

    /**
     * This function initializes the motors used to drive the robot.
     * To do this it assigns the CANSparkMax motors to the public variables leftThruster, rightThruster, leftSideWheel, rightSideWheel, and angleSetter.
     */
    public static void init () {
        botThruster = new CANSparkMax(5, MotorType.kBrushless);
        topThruster = new CANSparkMax(4, MotorType.kBrushless);
        topThruster.setInverted(true);
        SideWheel = new VictorSPX(0);

        botThruster.restoreFactoryDefaults();
        topThruster.restoreFactoryDefaults();

        thrusterPIDTop = topThruster.getPIDController();
        thrusterPIDBot = botThruster.getPIDController();
        thrusterEncoderTop = topThruster.getEncoder();
        thrusterEncoderBot = botThruster.getEncoder();

        topThruster.setOpenLoopRampRate(1);
        topThruster.setClosedLoopRampRate(1);
        botThruster.setOpenLoopRampRate(1);
        botThruster.setClosedLoopRampRate(1);

        thrusterPIDTop.setFeedbackDevice(thrusterEncoderTop);
        thrusterPIDTop.setFF(0.00001);
        thrusterPIDTop.setP(0);

        thrusterPIDBot.setFeedbackDevice(thrusterEncoderBot);
        thrusterPIDBot.setFF(0.00001);
        thrusterPIDBot.setP(0);
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
     * This function stops all wheel motors.
     */
    public static void stopLauncher () {
        botThruster.stopMotor();
        topThruster.stopMotor();

        SideWheel.set(ControlMode.PercentOutput, 0);
    }

    /**
     * This function retrieves the RPM of both thruster motors at any given time.
     */
    public static void rpmStatus () {
        SmartDashboard.putNumber("Top RPM:", topThruster.getEncoder().getVelocity());
        SmartDashboard.putNumber("Bottom RPM:", botThruster.getEncoder().getVelocity());
        
        System.out.println("RAW: " + topThruster.getEncoder().getVelocity());
        System.out.println("VAR: " + thrusterEncoderTop.getVelocity());
    }

    /**
     * This function will simply work.
     */
    public static void pid (double distance) {
        setPoint = Math.sqrt(39647 * Math.pow(distance, 2) + 838939);

        thrusterPIDBot.setReference(setPoint, ControlType.kVelocity);
        thrusterPIDTop.setReference(setPoint, ControlType.kVelocity);
    }

    public static void test (Joystick controller) {
            double set = 2728;
            double ref = (1 + (-controller.getRawAxis(3))) / 2;

            thrusterPIDBot.setReference(set * ref, ControlType.kVelocity);
            thrusterPIDTop.setReference(set * ref, ControlType.kVelocity);

            //SideWheel.set(ControlMode.PercentOutput, 0.3);
    }

    
}