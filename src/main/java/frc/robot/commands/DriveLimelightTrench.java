/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSystemBase;
// import frc.robot.subsystems.DriveSystemBase;
// import frc.robot.subsystems.TankDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;

public class DriveLimelightTrench extends CommandBase {
  //pull data from network tables (communication protocol)

  double minDriveSpeed = 0.2;//was 0.001; usually 0.1, minimum speed that makes robot move; adjustments smaller than this are ignored
  double KpAim = -0.1;//proportional control constant for aim
  double KpDistance = -0.1;//proportional control constant for distance
  double leftSpeed;//speed of left side of drive train
  double rightSpeed;//speed of right side of drive train
  DriveSystemBase _tankDrive;
  double threshold = 0.25;
  int xDivide = 27; //was 40, before that was 47, but could be slower to drop speed
  int yDivide = 27; //was 40, before that was 37
  double speedThreshold = 0.1;
  double finishThreshold = 0.45;
  /**
   * Creates a new DriveLimelight.
   */
  public DriveLimelightTrench(DriveSystemBase tankDrive) {
    _tankDrive = tankDrive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(tankDrive);
  }

 /* @Override
  public void initDefaultCommand() {
    //Set the default command for a subsystem here.
    //setDefaultCommand(new MySpecialCommand());
    //setDefaultCommand();
  }*/

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    SmartDashboard.putBoolean("Beginning DriveLimelight", true);
  }
  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    limelightTable.getEntry("pipeline").setNumber(1);
    NetworkTableEntry tx = limelightTable.getEntry("tx");
    NetworkTableEntry ty = limelightTable.getEntry("ty");
    NetworkTableEntry ta = limelightTable.getEntry("ta");
      
    //update network tables data periodically
    //currently returns default values
    double tX = tx.getDouble(0.0); //x was a double
    double tY = ty.getDouble(0.0); //y was a double
    double area = ta.getDouble(0.0);

    /*double x = tX/27;
    double y = tY/27;*/
    double x = 0;
    double y = 0;

    if (tX < -threshold){
      //turn left
      x = -Math.abs(tX/xDivide);
    } else if (tX > threshold){
      //turn Right
      x = Math.abs(tX/xDivide);
    }
    if (tY < -threshold){
      //drive forward
      y = -Math.abs(tY/yDivide);//was positive
    } else if (tY > threshold){
      //drive backward
      y = Math.abs(tY/yDivide);//was negative
    }
    if (x > speedThreshold){
      x = speedThreshold;
    }
    if (y > speedThreshold){
      y = speedThreshold;
    }
    if (x < -speedThreshold){
      x = -speedThreshold;
    }
    if (y < -speedThreshold){
      y = -speedThreshold;
    }


    //y = Math.abs(tX/27);

    if (Math.abs(x) < minDriveSpeed){
      x = 0;
      minDriveSpeed -= 0.025;
      xDivide += 5;
    }
    if (Math.abs(y) < minDriveSpeed){
      y = 0;
      minDriveSpeed -= 0.025;
      xDivide += 5;
    }

  
    //post data to smart dashboard periodically
    SmartDashboard.putNumber("Limelight X error", tX);
    SmartDashboard.putNumber("Limelight Y error", tY);
    SmartDashboard.putNumber("Limelight Target Area", area);

    //find how to drive "How far off are we? How much should we adjust?"
    /*double headingError = -x;
    double distanceError = -y;
    double steeringAdjust = 0.0;*/

    //adjustments based on how far off we are
    /*if (x > 1) {
      steeringAdjust = -(KpAim*headingError - minDriveSpeed);
    } else if (x < 1) {
      steeringAdjust = -(KpAim*headingError + minDriveSpeed);
    }
    SmartDashboard.putNumber("Steering Adjust", steeringAdjust);

    double distanceAdjust = KpDistance*distanceError;
    SmartDashboard.putNumber("Distance Adjust", distanceAdjust);
*/
    //adjustments to left or right side of driveTrain
    //leftSpeed += steeringAdjust + distanceAdjust;
    //rightSpeed -= steeringAdjust + distanceAdjust;
    //_tankDrive.move(steeringAdjust, distanceAdjust, 0);
    /*x = steeringAdjust;
    y = distanceAdjust;*/

    SmartDashboard.putNumber("X Speed", x);
    SmartDashboard.putNumber("Y Speed", y);

    SmartDashboard.putNumber("Left Drive Speed", -y+x);
    SmartDashboard.putNumber("Right Drive Speed", -(y+x));//negated because motors are mirrored
    _tankDrive.move(x, y, 0);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    SmartDashboard.putBoolean("Beginning DriveLimelight", false);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    NetworkTableEntry tv = limelightTable.getEntry("tv");

    NetworkTableEntry tx = limelightTable.getEntry("tx");
    NetworkTableEntry ty = limelightTable.getEntry("ty");
    double tX = tx.getDouble(0.0); //x was a double
    double tY = ty.getDouble(0.0); //y was a double
    if (Math.abs(tY) < finishThreshold && Math.abs(tX) < finishThreshold) {
      SmartDashboard.putBoolean("Lined Up", true);
      return true;
    } else {
      SmartDashboard.putBoolean("Lined Up", false);
      return false;
  }
}
}