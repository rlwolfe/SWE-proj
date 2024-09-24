package geometries;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;


/**
 * Bella & Rachel
 * constructors accept a list of geometries or nothing (default)
 * fields include: list of all geometries
 * public methods consist of: add that adds a new geometry (or more) to the list
 * Overridden methods are: findIntersections
 */
public class Geometries extends Intersectable {
	private final List<Intersectable> geometries;

	/**
	 * @return list of geometries
	 */
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
//	@Override
//	public List<Point> findIntersections(Ray ray) {
//		var geoList = findGeoIntersections(ray); return geoList == null ? null
//				: geoList.stream().map(gp -> gp.point).toList();
//	    
//		List<Point> intersections = new ArrayList<>();
//
//	    for (Intersectable geometry : geometries) {
//	        List<Point> geometryIntersections = geometry.findIntersections(ray);
//	        if (geometryIntersections != null) {
//	            intersections.addAll(geometryIntersections);
//	        }
//	    }
//
//	    // Sort the intersections based on their distance from the ray's head
//	    intersections.sort(Comparator.comparingDouble(p -> p.distance(ray.getHead())));
//	    if(intersections.isEmpty())
//	    	return null;
//	    
//	    return intersections;
//	}

	@Override
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		List<GeoPoint> intersections = new ArrayList<>();
		
			    for (Intersectable geometry : geometries) {
			        List<GeoPoint> geometryIntersections = geometry.findGeoIntersections(ray);
			        if (geometryIntersections != null) {
			            intersections.addAll(geometryIntersections);
			        }
			    }
		
			    // Sort the intersections based on their distance from the ray's head
			    //intersections.sort(Comparator.comparingDouble(p -> p.distance(ray.getHead())));
			    if(intersections.isEmpty())
			    	return null;
			    
			    return intersections;
	}
}
	
//	@Override
//	public List<Point> findIntersections(Ray ray) {
//		List<Point> intersections = new ArrayList<>();
//		int i = 0;
//
//		for(Intersectable geometry : geometries) {
//			Geometry geoObj = (Geometry)geometry;
//			for(Point point : geoObj.findIntersections(ray)) {
//				if(intersections.isEmpty())		//there are no points intersected yet 
//					intersections.add(point);	//add this intersection point
//				else {
//					for(Point intersect : intersections)	//there are other points already intersecting
//						if(point.distance(ray.head) < intersect.distance(ray.head)) {	//find which intersection is first
//							i = intersections.indexOf(intersect);		//if the new one is closer it replaces another, find the location
//							intersections.add(i, point);				//and put it before that other one
//							break;			// no need to check more intersections after we see one is greater
//						}
//				}
//			}
//		}
//		if (intersections.size() == 0) { return null; }
//		
//		return intersections;
//	}