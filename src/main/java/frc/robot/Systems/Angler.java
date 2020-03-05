package frc.robot.Systems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.lang.Math;

import edu.wpi.first.wpilibj.Joystick;

public class Angler {
    public static CANSparkMax angleMotor;
    public static CANEncoder angleEncoder;
    public static CANPIDController anglePIDController;
    public static double kP, kI, kD, kIz, kFF, kMaxOutput, kMinOutput, maxRPM;
    public static double setPoint;
    public static double angle;

    public static void init () {
        angleMotor = new CANSparkMax(13, MotorType.kBrushless);
        /*angleEncoder = angleMotor.getEncoder();
        anglePIDController = angleMotor.getPIDController();

        kP = 6e-5; 
        kI = 0;
        kD = 0; 
        kIz = 0; 
        kFF = 0.000015; 
        kMaxOutput = 1; 
        kMinOutput = -1;
        maxRPM = 3000;

        anglePIDController.setP(kP);
        anglePIDController.setI(kI);
        anglePIDController.setD(kD);
        anglePIDController.setIZone(kIz);
        anglePIDController.setFF(kFF);
        anglePIDController.setOutputRange(kMinOutput, kMaxOutput);
        */
    }

    public static void pid (double distance) {
        angle = Math.atan(4.6 / distance);

        setPoint = angle; // not actually though, change this lol

        anglePIDController.setReference(setPoint, ControlType.kPosition);
    }    

    public static void linearActuator (double speed) {
        angleMotor.set(speed);
    }
}