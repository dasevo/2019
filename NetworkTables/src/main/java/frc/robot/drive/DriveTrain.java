package frc.robot;

import edu.wpi.first.wpilibj.drive.MecanumDrive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class DriveTrain extends TimedRobot {

    MecanumDrive mDrive;
    XboxController xboxController;
    WPI_TalonSRX frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor;
    AHRS ahrs;
    PowerDistributionPanel pdp;

    double speed, heading, ySpeed, xSpeed, zRotation;

    public DriveTrain() {
        xboxController = new XboxController(Consts.xBoxComPort);
        ahrs = new AHRS(SPI.Port.kMXP);
        pdp = new PowerDistributionPanel(0);
        frontLeftMotor = new WPI_TalonSRX(Consts.fLeftMotor);
        rearLeftMotor = new WPI_TalonSRX(Consts.bLeftMotor);
        frontRightMotor = new WPI_TalonSRX(Consts.fRightMotor);
        rearRightMotor = new WPI_TalonSRX(Consts.bRightMotor);

        configTalon(frontLeftMotor);
        configTalon(rearLeftMotor);
        configTalon(frontRightMotor);
        configTalon(rearRightMotor);

        mDrive = new MecanumDrive(frontLeftMotor, rearLeftMotor, frontRightMotor, rearRightMotor);
    }

    public void configTalon(WPI_TalonSRX talon) {
        talon.configNominalOutputForward(0, Consts.timeOutMs);
		talon.configNominalOutputReverse(0, Consts.timeOutMs);
		talon.configPeakOutputForward(1, Consts.timeOutMs);
		talon.configPeakOutputReverse(-1, Consts.timeOutMs);
		talon.configAllowableClosedloopError(0, 0, Consts.timeOutMs);
		talon.configNeutralDeadband(0.05, Consts.timeOutMs); 
		talon.setNeutralMode(com.ctre.phoenix.motorcontrol.NeutralMode.Brake);
		talon.setInverted(false);

		// Peak current and duration must be exceeded before corrent limit is activated.
		// When activated, current will be limited to continuous current.
		// Set peak current params to 0 if desired behavior is to immediately
		// current-limit.
		talon.enableCurrentLimit(true);
		talon.configContinuousCurrentLimit(30, Consts.timeOutMs); // Must be 5 amps or more
		talon.configPeakCurrentLimit(30, Consts.timeOutMs); // 100 A
		talon.configPeakCurrentDuration(200, Consts.timeOutMs); // 200 ms
    }

    public void teleopPeriodic() {
        speed = (xboxController.getY(GenericHID.Hand.kLeft));
        heading = (xboxController.getX(GenericHID.Hand.kRight));

        if (xboxController.getTriggerAxis(GenericHID.Hand.kLeft) > 0.85) {
            speed = (xboxController.getY(GenericHID.Hand.kLeft)) * -1;
            heading = (xboxController.getX(GenericHID.Hand.kRight)) * 0.75;
        }

        mDrive.driveCartesian(ySpeed, xSpeed, zRotation);
    }

    public void printSmartdasboard() {
        
    }
}