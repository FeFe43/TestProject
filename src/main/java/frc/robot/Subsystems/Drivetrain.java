package frc.robot.Subsystems;

import static frc.robot.Constants.motorControllers.*;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;

public class Drivetrain { 
    
    public static void tankDrive() {
    
    double left = controller.getLeftY()*0.4;
    double right = controller.getRightY()*-0.4; 
    boolean speedBoost = controller.getRightBumper();
    
   if (speedBoost == true) {
    left = left * 1.3;
    right = right * 1.3;
    } 
    leftMotor1.set(TalonSRXControlMode.PercentOutput, left);
    rightMotor1.set(TalonSRXControlMode.PercentOutput, -right);
}
public static void arcadeDrive() {
    double speed = controller.getRightY() * 0.5;
    double turn = controller.getRightX() * -0.3;
   
   double left = speed + turn;
   double right = speed - turn;
   boolean speedBoostPressed = controller.getRightBumper();
      
   if (speedBoostPressed == true) {
    left = left * 1.3;
    right = right * 1.3;
    } 
    leftMotor1.set(TalonSRXControlMode.PercentOutput, left);
    rightMotor1.set(TalonSRXControlMode.PercentOutput, right);
}
}

