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
import edu.wpi.first.wpilibj.Servo;

public class Magazine extends SubsystemBase {
  public TalonSRX _magazineMotor;
  public Servo _hopperServo;
  /**
   * Creates a new Magazine.
   */
  public Magazine(TalonSRX magazineMotor, Servo hopperServo) {
    _magazineMotor = magazineMotor;
    _hopperServo = hopperServo;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setMagazine(double speed){
    _magazineMotor.set(ControlMode.PercentOutput, 0-speed);
    SmartDashboard.putNumber("Magazine", speed);
  }
  public void moveHopperServo(int angle) {
    //angle must be within 0 to 170
    _hopperServo.setAngle(angle);
  }
}
