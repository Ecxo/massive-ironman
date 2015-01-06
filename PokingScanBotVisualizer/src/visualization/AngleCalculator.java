package visualization;

public class AngleCalculator {

	private int currentAngle;
	private int angleIncrease;
	private double calculatedX;
	private double calculatedY;
	private int centerX;
	private int centerY;

	public AngleCalculator(int accuracy, int x, int y) {
		this.currentAngle = 0;
		this.angleIncrease = 360/accuracy;
		this.centerX = x;
		this.centerY = y;

	}

	public void resetAngle() {
		this.currentAngle = 0;

	}

	public void calculateCoordinates(int distance) {
		double angle = Math.toRadians(this.currentAngle);
		
		calculatedX = centerX + distance * Math.cos(angle);
		calculatedY = centerY + distance * Math.sin(angle);
		currentAngle = currentAngle + angleIncrease;
		

	}
	
	public double getCalculatedX() {
		return this.calculatedX;
	}
	
	public double getCalculatedY() {
		return this.calculatedY;
	}
	
	public int getRoundedX() {
		return (int) Math.round(calculatedX);
	}
	
	public int getRoundedY() {
		return (int) Math.round(calculatedY);
	}

	public int getCenterX() {
		
		return centerX;
	}
	
	public int getCenterY() {
		
		return centerY;
	}

	public void setAccuracy(int acc) {
		this.angleIncrease = 360/acc;

	}
	
	

}
