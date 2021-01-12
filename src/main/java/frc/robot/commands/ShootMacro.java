/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShootMacro extends CommandBase {
  private final Intake _intake;
  private final Magazine _magazine;
  private final Shooter _shooter;
  private final Transition _transition;
  /**
   * Creates a new ShootMacro.
   */
  public ShootMacro(Intake intake, Magazine magazine, Shooter shooter, Transition transition) {
    // Use addRequirements() here to declare subsystem dependencies.
    _intake = intake;
    _magazine = magazine;
    _shooter = shooter;
    _transition = transition;
    addRequirements(_intake, _magazine, _shooter, _transition);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("ShootMacro Running", true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    _shooter.setShooter(0.8);// was 0.75
    Timer.delay(2);
    //Use has elapsed if it fails
    _transition.setTransitionMotor(1);
    _magazine.setMagazine(0.8);
    _intake.setIntake(-0.8);
    Timer.delay(2);//was 3.5
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    _shooter.setShooter(0);
    _transition.setTransitionMotor(0);
    _magazine.setMagazine(0);
    _intake.setIntake(0);
    SmartDashboard.putBoolean("ShootMacro Running", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
