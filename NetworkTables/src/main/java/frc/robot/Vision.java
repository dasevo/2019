package frc.robot;

import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision
{
    private double[] vision;

    public Vision()
    {
        vision = new double[5];
    }

    public double[] getTable()
    {
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double ta = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ta").getDouble(0);
        double tl = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tl").getDouble(0);
        vision[0]=tv;
        vision[1]=tx;
        vision[2]=ty;
        vision[3]=ta;
        vision[4]=tl;
        return vision;
    }
}