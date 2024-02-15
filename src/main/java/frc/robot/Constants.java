package frc.robot;

import com.revrobotics.CANSparkLowLevel.MotorType;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;

public interface Constants {
    
    interface motorControllers {
  
        public TalonSRX leftMotor1 = new TalonSRX(3);
                TalonSRX leftMotor2 = new TalonSRX(4);  
                TalonSRX rightMotor1 = new TalonSRX(7);
                TalonSRX rightMotor2 = new TalonSRX(2);


        public CANSparkMax shooterMotor = new CANSparkMax(5, MotorType.kBrushed);

        public static XboxController controller = new XboxController(0);
    }
}
