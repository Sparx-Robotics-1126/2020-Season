package frc.climbing.commands;

import frc.climbing.ClimbingCommand;
import frc.climbing.ClimbingOutput;
import frc.climbing.ClimbingSensorsInterface;
import frc.health.HealthReport;

public class TestWinch extends ClimbingCommand{
	
	double previousDistance;
	double currentDistance;
	
	public TestWinch(ClimbingSensorsInterface sensors) {
		super(sensors);
		
		previousDistance = sensors.getWinchDistance();
		currentDistance = sensors.getWinchDistance();
	}

	public ClimbingOutput execute() {
		
		previousDistance = currentDistance;
		currentDistance = sensors.getWinchDistance();
		
		return new ClimbingOutput(0.1,false);
	}	
	
	@Override 
	public HealthReport checkHealth() {
		
		
		boolean directionIsCorrect = true;
		boolean isMoving = true;
		
		if(previousDistance > currentDistance) {
			directionIsCorrect = false;
		}
		
		if(previousDistance == currentDistance) {
			isMoving = false;
		}
		
		if(directionIsCorrect == false) {
			return new HealthReport(true,"Winch is moving the wrong way!");
		}
		
		if(isMoving == false) {
			return new HealthReport(true,"Winch is not moving!");
		}
		
		return new HealthReport();
	}

}
