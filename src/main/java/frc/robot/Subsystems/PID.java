package frc.robot.Subsystems;

import static frc.robot.Constants.motorControllers.controller;
import static frc.robot.Constants.motorControllers.leftMotor1;
import static frc.robot.Constants.motorControllers.rightMotor1;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.networktables.BooleanSubscriber;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class PID {

         public static final NetworkTableInstance inst = NetworkTableInstance.getDefault();
         public static final NetworkTable table = inst.getTable("limelight");
   
         public static final DoubleSubscriber tx = table.getDoubleTopic("tx").subscribe(0);
         public static final DoubleSubscriber ty = table.getDoubleTopic("ty").subscribe(0);
         public static final DoubleSubscriber ta = table.getDoubleTopic("ta").subscribe(0);
         public static final DoubleSubscriber tz = table.getDoubleTopic("tz").subscribe(0);
         public static final DoubleSubscriber tv = table.getDoubleTopic("tv").subscribe(0);
         public static double lastx = 0;
    
         public static void limeLight() {

         double x = tx.get();
         double y = ty.get();
         double area = ta.get();
         double distance = tz.get();

         double ckP = 0.01;
         double ckI = 0.00;
         double ckD = 0;

         double mkP = 0.25;
         double mkI = 0;
         double mkD = 0;

         double error = x;
         double foundTag = tv.get();

         PIDController centerRobot = new PIDController(ckP, ckI, ckD);
         PIDController moveTowardTag = new PIDController(mkP, mkI, mkD);
        
         if(x != 0){
            lastx = x;
         }
         System.out.println(ta.get());

         if (foundTag == 0) {
            if (lastx > 0) {
            leftMotor1.set(TalonSRXControlMode.PercentOutput, -0.15);
            rightMotor1.set(TalonSRXControlMode.PercentOutput, 0.15);
            } else if (lastx < 0) {
            leftMotor1.set(TalonSRXControlMode.PercentOutput, 0.15);
            rightMotor1.set(TalonSRXControlMode.PercentOutput, -0.15);
            } else {
            leftMotor1.set(TalonSRXControlMode.PercentOutput, 0.15);
            rightMotor1.set(TalonSRXControlMode.PercentOutput, -0.15);
            }
        }
         
        if (foundTag == 1) {
            if (Math.abs(x) < 10) {
            leftMotor1.set(TalonSRXControlMode.PercentOutput, -moveTowardTag.calculate(distance, 0.9));
            rightMotor1.set(TalonSRXControlMode.PercentOutput, -moveTowardTag.calculate(distance, 0.9));
         } else {
            if (error > 0 ) {
            leftMotor1.set(TalonSRXControlMode.PercentOutput, centerRobot.calculate(error, 0 ));
            rightMotor1.set(TalonSRXControlMode.PercentOutput, -centerRobot.calculate(error, 0 ));
         } else if (error < 0 ) {
            double error1 = Math.abs(error);
            leftMotor1.set(TalonSRXControlMode.PercentOutput, -centerRobot.calculate(error1, 0 ));
            rightMotor1.set(TalonSRXControlMode.PercentOutput, centerRobot.calculate(error1, 0 ));
         }
         }
        }
        }
      }
   
   
   