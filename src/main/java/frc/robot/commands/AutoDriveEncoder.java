/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystemBase;
import frc.robot.subsystems.TankDrive;

public class AutoDriveEncoder extends CommandBase {
  DriveSystemBase _tankDrive;
  double _leftSeekValue;
  double _rightSeekValue;
  double _leftEncoderCurrent;
  double _rightEncoderCurrent;
  double _valueThreshold = 1;
  double _leftSpeed;
  double _rightSpeed;
  double _maxSpeed;
  /**
   * Creates a new AutoDrive.
   */
  public AutoDriveEncoder(DriveSystemBase tankDrive, double leftSeekValue, double rightSeekValue, double maxSpeed) {
    // Use addRequirements() here to declare subsystem dependencies.
    _tankDrive = tankDrive;
    _leftSeekValue = leftSeekValue;
    _rightSeekValue = rightSeekValue;
    _maxSpeed = _maxSpeed;
    addRequirements(_tankDrive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("AutoDriving", true);
    
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _leftEncoderCurrent = _tankDrive.getLeftEncoder();
    _rightEncoderCurrent = _tankDrive.getRightEncoder();
    if(_leftEncoderCurrent < _leftSeekValue) {
      _leftSpeed = _maxSpeed;
    } else if(_leftEncoderCurrent> _leftSeekValue) {
      _leftSpeed = -_maxSpeed;
    }
    if(_rightEncoderCurrent < _rightSeekValue) {
      _rightSpeed = _maxSpeed;
     } else if(_rightEncoderCurrent> _rightSeekValue) {
      _rightSpeed = -_maxSpeed;
     }

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("AutoDriving", false);
    _tankDrive.move(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    if(_leftEncoderCurrent < (_leftSeekValue+_valueThreshold) && _leftEncoderCurrent>(_leftSeekValue-_valueThreshold)) {//If left encoder is within the threshold of the desired value
      if(_rightEncoderCurrent < (_rightSeekValue+_valueThreshold) && _rightEncoderCurrent>(_rightSeekValue-_valueThreshold)) {//If right encoder is within the threshold of the desired value
        return true;
      } else {
        return false;
      }
    } else {
      return false;
    }
  }
}
