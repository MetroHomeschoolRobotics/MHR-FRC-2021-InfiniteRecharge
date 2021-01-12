/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeLifter;

public class MoveIntake extends CommandBase {
  private IntakeLifter _intakeLifter;
  private int state;
  /**
   * Creates a new MoveIntake.
   */
  public MoveIntake(IntakeLifter intakeLifter) {
    // Use addRequirements() here to declare subsystem dependencies.
    _intakeLifter = intakeLifter;
    addRequirements(_intakeLifter);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    state = _intakeLifter.getIntakeLifterState();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (state == 1){
      _intakeLifter.setIntakeLifterUp();
    } else if (state == -1 || state == 0){
      _intakeLifter.setIntakeLifterDown();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
