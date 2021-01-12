/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Transition extends SubsystemBase {
  private Spark _transitionMotor;
  /**
   * Creates a new Transition.
   */
  public Transition(Spark transitionMotor) {
    _transitionMotor = transitionMotor;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setTransitionMotor(double speed) {
    _transitionMotor.set(speed);
    SmartDashboard.putNumber("Transition Speed", speed);
  }
} 
