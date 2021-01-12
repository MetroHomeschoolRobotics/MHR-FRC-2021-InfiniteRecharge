/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ControlPanelLift extends SubsystemBase {
  private Spark _controlPanelLiftMotor;
  private Encoder _controlPanelLiftEncoder;
  /**
   * Creates a new ControlPanelLift.
   */
  public ControlPanelLift(Spark controlPanelLiftMotor, Encoder controlPanelLiftEncoder) {
    _controlPanelLiftMotor = controlPanelLiftMotor;
    _controlPanelLiftEncoder = controlPanelLiftEncoder;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Control Panel Lift", getEncoder());
  }

  public int getEncoder() {
    return _controlPanelLiftEncoder.get();
  }

  public void setControlPanelLift(double speed) {
    if(getEncoder() < 11 && speed > 0) {
      _controlPanelLiftMotor.set(speed);
    } else {
      _controlPanelLiftMotor.set(0);
    }
    if(getEncoder() > 0 && speed < 0) {
      _controlPanelLiftMotor.set(speed);
    } else {
      _controlPanelLiftMotor.set(0);
    }
  }
}
