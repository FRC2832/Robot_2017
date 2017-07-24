package org.usfirst.frc2832.Robot2017.commands.auton;

import org.usfirst.frc2832.Robot2017.Robot;
import org.usfirst.frc2832.Robot2017.subsystems.NavX;

import edu.wpi.first.wpilibj.command.Command;

public class RotateToPegPixy extends Command {
    // TODO will need to tune these values
    private static final double turnP = 0.2;
    private static final double errorEpsilon = 2.1;
    private static final double minRotatePower = 0.1;
    
    private double rotation;

    @Override
    protected void initialize() {
        if (Robot.pixyValue == 255) {
            // nothing to do here, cheaters
            // way out, set our timeout to zero
            setTimeout(0);
            return;
        }
        
        // calculate required degrees of rotation from pixy
        
        // pixyValue between 0 and 255 inclusive.  
        // 127 is straight on.  
        // TUrn right is > 127 and 
        // turn left < 127. 
        
        // scale pegLocation as a percent -1 to 1 
        // across our field of view
        double pegLocation = (127.0 - Robot.pixyValue) / 127.0;
        
        // From http://cmucam.org/projects/cmucam5
        // May need to be adjusted (in degrees)
        final double fieldOfView = 75;
        
        // Find desired rotation in degrees based on
        // the total field of view of the camera
        rotation = (fieldOfView / 2.0) * pegLocation;

        // reset user yaw
        NavX.ahrs.zeroYaw();
        
        // set max time in seconds
        setTimeout(3.5);
    }

    @Override
    protected void execute() {
        double power = getError() * turnP;
        
        if (!onTarget() && Math.abs(power) < minRotatePower) {
            power = minRotatePower * Math.signum(getError());
        }
        Robot.driveTrain.setTankDriveCommand(power, -power);
    }

    @Override
    protected boolean isFinished() {
        return isTimedOut() || onTarget();
    }
    
    private boolean onTarget() {
        return Math.abs(getError()) < errorEpsilon;
    }
    
    private double getError() {
        return rotation - NavX.getYaw();
    }

    @Override
    protected void end() {
        Robot.driveTrain.setTankDriveCommand(0, 0);
    }

    @Override
    protected void interrupted() {
        end();
    }
}
