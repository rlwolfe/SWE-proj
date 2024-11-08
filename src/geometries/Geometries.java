package geometries;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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
	
	protected List<GeoPoint> findGeoIntersectionsHelper(Ray ray) {
		if (geometries.isEmpty())
			return null;
			
		List<GeoPoint> intersections = null;

	    for (Intersectable geometry : geometries) {
	        var geoPoints = geometry.findGeoIntersections(ray);
	        if (geoPoints != null) {
	        	if (intersections == null)
	        		intersections = new ArrayList<GeoPoint>();
	            intersections.addAll(geoPoints);
	        }
	    }
	    if(intersections == null)
	    	return null;
	    return intersections;
	}
}