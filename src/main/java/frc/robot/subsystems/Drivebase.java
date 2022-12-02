// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;

public class Drivebase extends SubsystemBase implements AutoCloseable {

  // FIXME using PWMSparkMax because CANSparkMax doesn't have an equivalent simulation class
  // May limit how much we can do in terms of JUnit tests
  private CANSparkMax LBmotor;
  private CANSparkMax RBmotor;
  private CANSparkMax LFmotor;
  private CANSparkMax RFmotor;
  private final DifferentialDrive differentialDrive;
  private final Joystick controller;

  /** Creates a new Drivebase. */
  public Drivebase(Joystick joystick) {
    controller = joystick;
    LBmotor = new CANSparkMax(Constants.LBmotor, MotorType.kBrushless);
    RBmotor = new CANSparkMax(Constants.RBmotor, MotorType.kBrushless);
    LFmotor = new CANSparkMax(Constants.LFmotor, MotorType.kBrushless);
    RFmotor = new CANSparkMax(Constants.RFmotor, MotorType.kBrushless);
    RBmotor.follow(RFmotor); /* this and line beneath are to have the back motors follow the front motors of their respective sides */
    LBmotor.follow(LFmotor);
    LFmotor.setSmartCurrentLimit(40);
    differentialDrive = new DifferentialDrive(LFmotor , RFmotor);
  }

  public void spin(double velocity) {
    LBmotor.set(velocity);
    RBmotor.set(velocity);
    LFmotor.set(velocity);
    RFmotor.set(velocity);
  }

  

  public void tankDrive(){
    differentialDrive.tankDrive(controller.getRawAxis(1), controller.getRawAxis(3));
  }

// figure out two different speed variables for the different speeds (mainly for turning) (remove when completed)
//remove remebering comments once completed


  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Override
  public void simulationPeriodic() {
    // This method will be called once per scheduler run during simulation
  }

  @Override
  public void close() throws Exception {
    // This method will close all device handles used by this object and release any other dynamic memory.
    // Mostly for JUnit tests
    LBmotor.close();
    RBmotor.close();
    LFmotor.close();
    RFmotor.close();
    
  }
}
