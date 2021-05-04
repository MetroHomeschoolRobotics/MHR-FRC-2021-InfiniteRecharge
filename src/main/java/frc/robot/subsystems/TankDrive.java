/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANError;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class TankDrive extends DriveSystemBase {
  private CANSparkMax _frontLeft;
  private CANSparkMax _frontRight;
  private CANSparkMax _rearLeft;
  private CANSparkMax _rearRight;
  private CANEncoder _frontLeftEncoder;
  private CANEncoder _frontRightEncoder;
  private CANEncoder _rearLeftEncoder;
  private CANEncoder _rearRightEncoder;

 // public TankDrive(TalonSRX frontLeft, TalonSRX frontRight, TalonSRX rearLeft, TalonSRX rearRight){
  public TankDrive(CANSparkMax frontLeft, CANSparkMax frontRight, CANSparkMax rearLeft, CANSparkMax rearRight){
    _frontLeft = frontLeft;
    _frontRight = frontRight;
    _rearLeft = rearLeft;
    _rearRight = rearRight;
    _frontLeftEncoder = new CANEncoder(_frontLeft);
    _frontRightEncoder = new CANEncoder(_frontRight);
    _rearLeftEncoder = new CANEncoder(_rearLeft);
    _rearRightEncoder = new CANEncoder(_rearRight);
    _frontLeft.setSmartCurrentLimit(30, 40);
    _frontLeft.burnFlash();
    _frontRight.setSmartCurrentLimit(30, 40);
    _frontRight.burnFlash();
    _rearLeft.setSmartCurrentLimit(30, 40);
    _rearLeft.burnFlash();
    _rearRight.setSmartCurrentLimit(30, 40);
    _rearRight.burnFlash();

    
  }
  // @Override
  // public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  //}
    @Override
    public double getLeftEncoder() {
      // TODO Auto-generated method stub
      return 0;
    }

    @Override
    public double getRightEncoder() {
      // TODO Auto-generated method stub
      return 0;
    }

    
  @Override
  public void move(double x, double y, double z){
    _frontLeft.set(-y+x);
    _rearLeft.set(-y+x);
    _frontRight.set(y+x);
    _rearRight.set(y+x);
    
    SmartDashboard.putNumber("TankDrive X", x);
    SmartDashboard.putNumber("TankDrive Y", y);
    SmartDashboard.putNumber("TankDrive Z", z);
  }
@Override
  public void moveTank(double left, double right) {
_frontLeft.set(left);
_frontRight.set(right);
_rearLeft.set(left);
_rearRight.set(right);
  }
}
