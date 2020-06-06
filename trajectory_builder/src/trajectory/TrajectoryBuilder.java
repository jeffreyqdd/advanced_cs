package trajectory;

import java.util.ArrayList;
import java.util.List;

public class TrajectoryBuilder
{
	private TrajectoryConstraints trajecetoryConstraints = null;
	private List<SpatialMarker> spatialMarkers;
	private Trajectory trajectory;
	
	
	public TrajectoryBuilder(TrajectoryConstraints trajectoryConstraints){
		this.trajecetoryConstraints = trajectoryConstraints;
		this.spatialMarkers = new ArrayList<>();
	}
	
	
	public SpatialMarker addMarker(SpatialMarker spatialMarker) {
		spatialMarkers.add(spatialMarker);
		return spatialMarker;
	}
	
	public Trajectory build() {
		injectPoints(spatialMarkers);
	}
	
	private void injectPoints(List<SpatialMarker> spatialMarkers) {
		/*
		* ● For every line segment in the path:
○ vector = end_point - start_point
○ num_points_that_fit = Math.ceil(vector.magnitude() / spacing)
○ vector = vector.normalize() * spacing
○ for (i = 0; i<num_points_that_fit; i++):
■ Add (start_point + vector * i) to new_points ● Add the very last point in the path to new_points*/
	
		List<SpatialMarker> injectedMarkers = new ArrayList<>();
		
		for(int i = 0; i < spatialMarkers.size() - 1; i++) {
			double vector1X = spatialMarkers.get(i).x;
			double vector1Y = spatialMarkers.get(i).y;
			double vector2X = spatialMarkers.get(i + 1).x;
			double vector2Y = spatialMarkers.get(i + 1).y;
			
			double differenceX = vector2X - vector1X;
			double differenceY = vector2Y - vector1Y;
			
			
		}
	}
	
	private void smoothTrajectory() {
	
	}
	
	private void annotateTrajctory() {
	
	}
}
