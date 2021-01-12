/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

//import java.util.ResourceBundle.Control;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.ControlPanelLift;

public class LiftControlPanel extends CommandBase {
  private ControlPanelLift _controlPanelLift;
  /**
   * Creates a new LiftControlPanel.
   */
  public LiftControlPanel(ControlPanelLift controlPanelLift) {
    _controlPanelLift = controlPanelLift;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(_controlPanelLift);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _controlPanelLift.setControlPanelLift(0.25);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _controlPanelLift.setControlPanelLift(-0.25);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
