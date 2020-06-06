package trajectory;

import debug.StdDraw;
import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class TrajectoryBuilder
{
	private TrajectoryConstraints trajectoryConstraints;
	private List<Vector2D> spatialMarkers;
	private List<Double> velTemp;
	
	
	public TrajectoryBuilder(TrajectoryConstraints trajectoryConstraints){
		this.trajectoryConstraints = trajectoryConstraints;
		this.spatialMarkers = new ArrayList<>();
	}
	
	
	public Vector2D addMarker(Vector2D spatialMarker) {
		spatialMarkers.add(spatialMarker);
		return spatialMarker;
	}
	
	public Trajectory build() {
		spatialMarkers = injectPoints(spatialMarkers, trajectoryConstraints.spacing);
		spatialMarkers = smoothTrajectory(spatialMarkers, trajectoryConstraints.weightData,
				trajectoryConstraints.weightSmooth,
				trajectoryConstraints.tolerance);
		
		annotateTrajectory(spatialMarkers, trajectoryConstraints.maxVelocity, trajectoryConstraints.maxAccel, trajectoryConstraints.turnConstant);
		
		
		
		return new Trajectory();
	}
	
	private List<Vector2D> injectPoints(List<Vector2D> path, double injectionDistance) {

		
		List<Vector2D> injectedMarkers = new ArrayList<>();
		for(int i = 0; i < path.size() - 1; i++) {
			Vector2D vector = path.get(i + 1).subtract(path.get(i));
			
			double magnitude = Math.sqrt(Math.pow(vector.getX(), 2) + Math.pow(vector.getY(), 2));
			
			int numPointsBetween = (int) Math.ceil(magnitude / injectionDistance);
			vector = vector.normalize();
			for(int j = 0; j < numPointsBetween; j++) {
				injectedMarkers.add(path.get(i).add(vector.scalarMultiply(j * injectionDistance)));
			}
		}
		injectedMarkers.add(path.get(path.size() - 1));
		System.out.println(injectedMarkers);
		return injectedMarkers;
	}
	
	private List<Vector2D> smoothTrajectory(List<Vector2D> path, double a, double b, double tolerance) {
		
		List<Vector2D> newPath = new ArrayList<>(path);
		
		double change = tolerance;
		while(change >= tolerance) {
			change = 0.0;
			for(int i = 1; i < path.size() - 1; i++) {
				double aux1 = newPath.get(i).getX();
				double aux2 = newPath.get(i).getY();
				
				double newX = aux1 + a * (path.get(i).getX() - newPath.get(i).getX())
						+ b * (newPath.get(i - 1).getX() + newPath.get(i + 1).getX()
						- 2.0 * newPath.get(i).getX());
				
				double newY = aux2 + a * (path.get(i).getY() - newPath.get(i).getY())
						+ b * (newPath.get(i - 1).getY() + newPath.get(i + 1).getY()
						- 2.0 * newPath.get(i).getY());
				
				newPath.set(i, new Vector2D(newX, newY));
				change += Math.abs(aux1 - newPath.get(i).getX()) +
						  Math.abs(aux2 - newPath.get(i).getY());
			}
		}
		
		return newPath;
		
	}
	
	private void annotateTrajectory(List<Vector2D> path, double maxVelocity, double maxAcceleration, double turnConstant) {
		List<Double> pathCurvature = determinePathCurvature(path);
		
		velTemp = determinePathVelocity(path, pathCurvature, maxVelocity, maxAcceleration, turnConstant);
	}
	private List<Double> determinePathCurvature(List<Vector2D> path) {
		List<Double> pathCurvature = new ArrayList<>();
		for(int i = 0; i < path.size() - 3; i++)
		{
			Vector2D v1 = path.get(i), v2 = path.get(i + 1), v3 = path.get(i + 2);
			
			double k1 = 0.5 * (
					Math.pow(v1.getX(), 2) +
							Math.pow(v1.getY(), 2) -
							Math.pow(v2.getX(), 2) -
							Math.pow(v2.getY(), 2)) /
					(v1.getX() - v2.getX() + 0.001);
			
			double k2 = (v1.getY() - v2.getY()) / (v1.getX() - v2.getX() + 0.001);
			double b = 0.5 * (
					Math.pow(v2.getX(), 2) -
							2 * v2.getX() * k1 +
							Math.pow(v2.getY(), 2) -
							Math.pow(v3.getX(), 2) +
							2 * v3.getX() * k1 -
							Math.pow(v3.getY(), 2)) /
					(v3.getX() * k2 -
							v3.getY() +
							v2.getY() -
							v2.getX() * k2
					);
			double a = k1 - k2 * b;
			double r = Math.sqrt(Math.pow(v1.getX() - a, 2) + Math.pow(v1.getY() - b, 2));
			if (r < 0.0001) r = 0.0001;
			double curvature = Double.isNaN(r) ? 0 : 1.0d / r;
			pathCurvature.add(curvature);
		}
		
		for(int i = 0; i < 3; i++) pathCurvature.add(pathCurvature.get(pathCurvature.size() - 1));
		
		return pathCurvature;
	}
	private List<Double> determinePathVelocity(List<Vector2D> path, List<Double> pathCurvature, double maxVelocity, double maxAcceleration, double turnConstant) {
		List<Double> pathVelocity = new ArrayList<>();
		
		for(int i = 0; i < path.size(); i++)
		{
			double optimalVelocity = Math.min(maxVelocity, turnConstant / pathCurvature.get(i));
			pathVelocity.add(optimalVelocity);
		}
		
		//backward scanner
		pathVelocity.set(pathVelocity.size() - 1, 0d);
		for(int i = pathVelocity.size() - 2; i >= 0; i--) {
			double distance = path.get(i).distance(path.get(i + 1));
			double newVelocity = Math.min(pathVelocity.get(i),  Math.sqrt(Math.pow(pathVelocity.get(i + 1),2) + 2.0d * maxAcceleration * distance ) );
			pathVelocity.set(i, newVelocity);
		}
		
		//forward scanner
		pathVelocity.set(0, Math.sqrt(0.0d + 2.0d * maxAcceleration * 3.0d));
		for(int i = 1; i < pathVelocity.size(); i++) {
			double distance =  path.get(i).distance(path.get(i - 1));
			double newVelocity = Math.min(pathVelocity.get(i), Math.sqrt(Math.pow(pathVelocity.get(i - 1),2) + 2.0d * maxAcceleration * distance));
			pathVelocity.set(i, newVelocity);
		}
		
		
		
		return pathVelocity;
	}
	
	public static void main(String[] args)
	{
		long before = System.currentTimeMillis();
		TrajectoryBuilder builder = new TrajectoryBuilder(new TrajectoryConstraints());
		
		builder.addMarker(new Vector2D(-60,-60));
		builder.addMarker(new Vector2D(-60,60));
		builder.addMarker(new Vector2D(60,60));
		builder.addMarker(new Vector2D(0,0));
		builder.addMarker(new Vector2D(72, -72));
		builder.build();
		long after = System.currentTimeMillis();
		
		StdDraw.setXscale(-72, 72);
		StdDraw.setYscale(-72, 72);
		System.out.println(builder.velTemp);
		System.out.println("size: " + builder.spatialMarkers.size() + ", " + builder.velTemp.size());
		for(int i = 0; i < builder.velTemp.size() - 1; i++) {
			Vector2D v1 = builder.spatialMarkers.get(i), v2 = builder.spatialMarkers.get(i + 1);
			
			double proportion = builder.velTemp.get(i) / builder.trajectoryConstraints.maxVelocity;
			StdDraw.setPenRadius(0.01);
			StdDraw.setPenColor(255 - (int)(proportion * 255), (int) (proportion * 255), 0);
			StdDraw.line(v1.getX(), v1.getY(), v2.getX(), v2.getY());
		}
		StdDraw.setPenColor(Color.black);
		for(Vector2D v : builder.spatialMarkers)
			StdDraw.filledCircle(v.getX(), v.getY(), 0.3);
		
		//System.out.println( (double) (after - before) / 1000.0);
	}
}
