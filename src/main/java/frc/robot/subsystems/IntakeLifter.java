/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;

public class IntakeLifter extends SubsystemBase {
  private DoubleSolenoid _intakeSolenoid1;
  private DoubleSolenoid _intakeSolenoid2;
  /**
   * Creates a new IntakeLifter.
   */
  public IntakeLifter(DoubleSolenoid intakeSolenoid1, DoubleSolenoid intakeSolenoid2) {
    _intakeSolenoid1 = intakeSolenoid1;
    _intakeSolenoid2 = intakeSolenoid2;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setIntakeLifterDown() {
    _intakeSolenoid1.set(Value.kForward);
    _intakeSolenoid2.set(Value.kForward);
  }

  public void setIntakeLifterUp() {
    _intakeSolenoid1.set(Value.kReverse);
    _intakeSolenoid2.set(Value.kReverse);
  }

  public void setIntakeLifterOff() {
    _intakeSolenoid1.set(Value.kOff);
    _intakeSolenoid2.set(Value.kOff);
  }

  public int getIntakeLifterState() {
    int state = 1825;//Error value
    if(_intakeSolenoid1.get() == Value.kForward) {
      state = 1;
    } else if(_intakeSolenoid1.get() == Value.kReverse){
      state = -1;
    } else if(_intakeSolenoid1.get() == Value.kOff) {
      state = 0;
    }
    return state;
  }
}
