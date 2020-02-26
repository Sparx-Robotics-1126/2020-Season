package frc.sensors;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;
import com.revrobotics.ColorMatch;

public class ColorSensor {

	String colorString;

	private final static ColorMatch colorMatcher = new ColorMatch();

	private final static Color BlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
	private final static Color GreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
	private final static Color RedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
	private final static Color YellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

	private static Color getColor() {
		colorMatcher.addColorMatch(BlueTarget);
		colorMatcher.addColorMatch(GreenTarget);
		colorMatcher.addColorMatch(RedTarget);
		colorMatcher.addColorMatch(YellowTarget);
		return detectedColor;
	}

	static Color detectedColor = ColorSensor.getColor();

	ColorMatchResult match = colorMatcher.matchClosestColor(detectedColor);
	{

		if (match.color == BlueTarget) {
			colorString = "Blue";
		} else if (match.color == RedTarget) {
			colorString = "Red";
		} else if (match.color == GreenTarget) {
			colorString = "Green";
		} else if (match.color == YellowTarget) {
			colorString = "Yellow";
		} else {
			colorString = "Unknown";
		}

		SmartDashboard.putNumber("Red", detectedColor.red);
		SmartDashboard.putNumber("Green", detectedColor.green);
		SmartDashboard.putNumber("Blue", detectedColor.blue);
		SmartDashboard.putNumber("Confidence", match.confidence);
		SmartDashboard.putString("Detected Color", colorString);

	}
}
