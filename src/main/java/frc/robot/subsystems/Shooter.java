/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.CANPIDController;
//import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.CANSparkMax;
//import com.ctre.phoenix.motorcontrol.ControlMode;

public class Shooter extends SubsystemBase {
  public CANSparkMax _shooterMotor1;  
  public CANSparkMax _shooterMotor2;
  public Spark _hoodMotor;
  public AnalogPotentiometer _hoodPot;
  private CANPIDController shooterMotor1PID;
  private CANPIDController shooterMotor2PID;
  /**
   * Creates a new Shooter.
   */
  public Shooter(CANSparkMax shooterMotor1, CANSparkMax shooterMotor2, Spark hoodMotor, AnalogPotentiometer hoodPot) {
    _shooterMotor1 = shooterMotor1;
    _shooterMotor2 = shooterMotor2;
    _hoodMotor = hoodMotor;
    _hoodPot = hoodPot;
    shooterMotor1PID = new CANPIDController(shooterMotor1);
    shooterMotor2PID = new CANPIDController(shooterMotor2);
    
    SmartDashboard.putBoolean("Shooter running", false);
  }

  @Override
  public void periodic() {
    // This method will be called oncex per scheduler run
//SmartDashboard.putNumber("Current Potentiometer Value", _hoodPot.get());

  }

public void setHoodMotor(double speed) {
  _hoodMotor.set(speed);
}


public void setShooterRPM(double RPM) {
  shooterMotor1PID.setReference(RPM, com.revrobotics.ControlType.kVelocity);
  shooterMotor2PID.setReference(-RPM, com.revrobotics.ControlType.kVelocity);
}

  public void setShooter(double speed){
    //Shooter motors are both negated on Version 3
    //Shooter motors are mirrored on Version 2 and up, motor 1 is negative, motor 2 is positive
    _shooterMotor1.set(-speed);
    _shooterMotor2.set(-speed);
    //_shooterMotor1.set(-speed);
    //_shooterMotor2.set(speed);
    SmartDashboard.putNumber("Shooter Speed", speed);
    if(speed == 0) {
    SmartDashboard.putBoolean("Shooter Running", false);
    } else {
      SmartDashboard.putBoolean("Shooter Running", true);
    }

  }
  public double getPotentiometer() {
return _hoodPot.get();
  }
  
}
