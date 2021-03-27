// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class MoveShooterHoodToPreset extends CommandBase {
  private double _speed;
  private Shooter _shooter;
  private double _currentAngle = 0;
  private double _angle;
  private int _directionofSpin = 1;
  private double _moveSpeed = .25;
  private int _direction = 0;
  /** Creates a new MoveShooterHood. */
  public MoveShooterHoodToPreset(double angle, Shooter shooter) {
    // Use addRequirements() here to declare subsystem dependencies.
_shooter = shooter;
_angle = angle;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    _currentAngle = _shooter.getPotentiometer();
    if(_currentAngle < _angle) {
      _direction = -1;
    } else {
      _direction = 1;
    }
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _shooter.setHoodMotor(_direction*_directionofSpin*_moveSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
_shooter.setHoodMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    _currentAngle = _shooter.getPotentiometer();
    if(_direction == -1 && _currentAngle>= _angle) {
      return true;
    } else if (_direction == 1 && _currentAngle <= _angle) {
      return true;
    }
    return false;
  }
}
