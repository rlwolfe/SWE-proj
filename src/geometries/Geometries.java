package geometries;
import java.util.LinkedList;
import java.util.List;

import primitives.Point;
import primitives.Ray;


public class Geometries implements Intersectable {
	  private final List<Intersectable> geometries;


	  //Default constructor
	  public Geometries() {
		  geometries = new LinkedList<>();
	  	}
	// Constructor with variable number of geometries
	    public Geometries(Intersectable... geometries) {
	        this(); // Call default constructor to initialize the list
	        add(geometries); // Add geometries using the add method
	    }
	 // Function to add geometries to collection
	    public void add(Intersectable... geometries) {
	        for (Intersectable geometry : geometries) {
	            this.geometries.add(geometry);
	        }
	    }
	     // Implementation of findIntersections method
	        @Override
	        public List<Point> findIntsersections(Ray ray) {
	            // Since this implementation is return null, simply return null
	            return null;
	        }

}
