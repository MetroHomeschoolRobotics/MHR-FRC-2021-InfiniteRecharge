/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {
  private DoubleSolenoid _climbSolenoid1;
  private DoubleSolenoid _climbSolenoid2;
  private TalonSRX _climbMotor;
  /**
   * Creates a new Climber.
   */
  public Climber(DoubleSolenoid climbSolenoid1, DoubleSolenoid climbSolenoid2, TalonSRX climbMotor) {
    _climbSolenoid1 = climbSolenoid1;
    _climbSolenoid2 = climbSolenoid2;
    _climbMotor = climbMotor;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void deploySolenoids(){
    _climbSolenoid1.set(Value.kForward);
    _climbSolenoid2.set(Value.kForward);
  }

  public void reverseSolenoids(){//for emergencies and errors
    _climbSolenoid1.set(Value.kReverse);
    _climbSolenoid2.set(Value.kReverse);
  }
  
  public void runWinch(double speed){
    if (_climbMotor != null) {
    _climbMotor.set(ControlMode.PercentOutput, speed);
    SmartDashboard.putNumber("Winch Speed", speed);
    }
  }

  public int getClimberState() {
    int state = 1825;//Error value
    if(_climbSolenoid1.get() == Value.kForward) {
      state = 1;
    } else if(_climbSolenoid1.get() == Value.kReverse) {
      state = -1;
    } else if(_climbSolenoid1.get() == Value.kOff) {
      state = 0;
    }
    return state;
  }
}
