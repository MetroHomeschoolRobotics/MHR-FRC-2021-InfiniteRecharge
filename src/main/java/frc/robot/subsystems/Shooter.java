/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
//import com.ctre.phoenix.motorcontrol.ControlMode;

public class Shooter extends SubsystemBase {
  public CANSparkMax _shooterMotor1;
  public CANSparkMax _shooterMotor2;
  /**
   * Creates a new Shooter.
   */
  public Shooter(CANSparkMax shooterMotor1, CANSparkMax shooterMotor2) {
    _shooterMotor1 = shooterMotor1;
    _shooterMotor2 = shooterMotor2;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setShooter(double speed){
    //Shooter motors are both negated on Version 3
    //Shooter motors are mirrored on Version 2, motor 1 is negative, motor 2 is positive
    _shooterMotor1.set(-speed);
    _shooterMotor2.set(-speed);
    //_shooterMotor1.set(-speed);
    //_shooterMotor2.set(speed);
    SmartDashboard.putNumber("Shooter Speed", speed);
  }
}
