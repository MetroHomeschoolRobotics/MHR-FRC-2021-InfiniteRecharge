// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Magazine;

public class MoveHopperDown extends CommandBase {
  /** Creates a new MoveHopperDown. */
  private final Magazine _magazine;
  /*IMPORTANT: THIS NUMBER IS NOT FINAL! PLEASE MAKE SURE TO UPDATE TO BE THE CORRECT ANGLE!*/
  private int _angle = 100; //TODO: make this angle the correct angle for the hopper to be dropped to. 

  public MoveHopperDown(Magazine magazine) {
    // Use addRequirements() here to declare subsystem dependencies.
    _magazine = magazine;
    addRequirements(_magazine);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _magazine.moveHopperServo(_angle);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
