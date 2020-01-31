package org.firstinspires.ftc.team17294.botcore.base;

public class CartesianVelocity
{
	public double vx;
	public double vy;
	public double omega;
	
	public CartesianVelocity() {
		super();
	}
	
	public CartesianVelocity(double vx, double vy, double omega) {
		super();
		this.vx = vx;
		this.vy = vy;
		this.omega = omega;
	}
	
	@Override
	public String toString() {
		return "Plane3DoFVelocity [vx=" + vx + ", vy="
				+ vy + ", omega=" + omega + "]";
	}
}
