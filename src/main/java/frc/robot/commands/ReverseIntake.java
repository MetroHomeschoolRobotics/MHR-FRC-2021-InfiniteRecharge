/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class ReverseIntake extends CommandBase {
  private final Intake _intake;
  private final Joystick _driverControl;
  
  /**
   * Creates a new RunIntake.
   */
  public ReverseIntake(Intake intake, Joystick driverControl) {
    // Use addRequirements() here to declare subsystem dependencies.
    _intake = intake;
    _driverControl = driverControl;
    addRequirements(intake);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    /*double rate = _driverControl.getRawAxis(3);//get triggers
    double threshold = 0.1;
    if (Math.abs(rate) < threshold) {
      rate = 0;
    }
    _intake.setIntake(rate);*/
    _intake.setIntake(-0.8);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _intake.setIntake(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
