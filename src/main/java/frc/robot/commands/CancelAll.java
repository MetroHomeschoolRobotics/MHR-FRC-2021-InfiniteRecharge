/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;

public class CancelAll extends CommandBase {
  private final ControlPanel _controlPanel;
  private final Intake _intake;
  private final Magazine _magazine;
  private final Shooter _shooter;
  private final TankDrive _tankDrive;
  
  /**
   * Creates a new CancelAll.
   */
  public CancelAll(ControlPanel controlPanel, Intake intake, Magazine magazine, Shooter shooter, DriveSystemBase tankDrive) {
    // Use addRequirements() here to declare subsystem dependencies.
    _controlPanel = controlPanel;
    _intake = intake;
    _magazine = magazine;
    _shooter = shooter;
    _tankDrive = (TankDrive) tankDrive;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Cancel all", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
   // _controlPanel.setControlPanel(-0);
    _intake.setIntake(0);
    _magazine.setMagazine(0);
    _shooter.setShooter(0);
    _tankDrive.move(0, 0, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Cancel all", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
