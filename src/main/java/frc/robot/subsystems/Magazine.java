/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Magazine extends SubsystemBase {
  public TalonSRX _magazineMotor;
  /**
   * Creates a new Magazine.
   */
  public Magazine(TalonSRX magazineMotor) {
    _magazineMotor = magazineMotor;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMagazine(double speed){
    _magazineMotor.set(ControlMode.PercentOutput, 0-speed);
    SmartDashboard.putNumber("Magazine", speed);
  }
}
