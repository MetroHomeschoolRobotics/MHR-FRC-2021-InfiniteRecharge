// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.Joystick;

public class RemoveClimberPin extends CommandBase {
  /** Creates a new removeClimberPin. */
  private Climber _climber;
  private Joystick _driverControl;
  private double speed = -.25;//TODO: make this proper numerical value
  public RemoveClimberPin(Climber climber, Joystick driverControl) {
    // Use addRequirements() here to declare subsystem dependencies.
    _climber = climber;
    _driverControl = driverControl;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
     //if(Timer.getMatchTime()<30) { //CHANGE THIS LATER BEFORE COMPETITION
      if(_driverControl.getRawButton(7)) {
_climber.runPinMotor(speed);
      }
//}
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _climber.runPinMotor(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
