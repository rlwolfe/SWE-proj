package geometries;
import static primitives.Util.isZero;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;


/**
 * 
 */
public class Geometries implements Intersectable {
	private final List<Intersectable> geometries;

	public List<Intersectable> getGeometries() {
		return geometries;
	}
	/**
	 * Default constructor
	 */
	public Geometries() {
		geometries = new LinkedList<>();
	}

	/**
	 * Constructor with variable number of geometries
	 * @param geometries
	 */
	public Geometries(Intersectable... geometries) {
		this(); // Call default constructor to initialize the list
		add(geometries); // Add geometries using the add method
	}
	
	/**
	 * Function to add geometries to collection
	 * @param geometries
	 */
	public void add(Intersectable... geometries) {
		for(Intersectable geometry : geometries) {
			this.geometries.add(geometry);
		}
	}
	
	@Override
	public List<Point> findIntersections(Ray ray) {
		List<Point> intersections = new ArrayList<>();
		int i = 0;

		for(Intersectable geometry : geometries) {
			Geometry geoObj = (Geometry)geometry;
			for(Point point : geoObj.findIntersections(ray)) {
				if(intersections.isEmpty())
					intersections.add(point);	
				else {
					for(Point intersect : intersections)
						if(point.distance(ray.head) < intersect.distance(ray.head)) {
							i = intersections.indexOf(intersect);
							intersections.add(i, point);
						}
				}
			}
		}
		if (intersections.size() == 0) { return null; }
		
		return intersections;
	}
}
