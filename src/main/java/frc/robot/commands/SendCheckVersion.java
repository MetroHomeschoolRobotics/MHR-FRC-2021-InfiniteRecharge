/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import javax.swing.text.StyleContext.SmallAttributeSet;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.pixy2.Pixy2;
import frc.robot.pixy2.Pixy2.Version;

public class SendCheckVersion extends CommandBase {
  private Pixy2 _pixy2;

  public SendCheckVersion(Pixy2 pixy2) {
    _pixy2 = pixy2;
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    SmartDashboard.putString("Pixy Version", "checking");
    Version version = _pixy2.getVersionInfo();
    if (version != null){
      SmartDashboard.putString("Pixy Version", version.toString());
    } else {
      SmartDashboard.putString("Pixy Version", "not found");
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {
  }
}
