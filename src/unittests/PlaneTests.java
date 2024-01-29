/**
 * @author Rachel and Bella
 */
package unittests;
import geometries.Plane;
import primitives.Point;
import primitives.Vector;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PlaneTests {

	/**
	 * Equivalence tests methods for {@link geometries.Plane#getNormal()}.
	 * The whole idea for Normal is to get the normal of any point 
	 * normal means the line that is perpendicular, and bc a plane is flat 
	 * each point should have the same normal. 
	 * a flat surface has a perpendicular line going out all the same. 
	 */
	@Test
	void testGetNormal() {
		//equivalence tests for the constructor
		//points 1==2
		Point point1 = new Point(2.0, 1.0, 0.0);//this is the constructor 
		Point point2 = new Point(2.0, 1.0, 0.0);
		Point point3 = new Point(0.0, 0.0, 0.0);
		assertThrows(IllegalArgumentException.class, () -> new Plane(point1, point2, point3), "ERROR: attempted to create plane with 2 identical points");

		//points on same line
		Point pt1 = new Point(0.0, 0.0, 0.0);//this is the constructor 
		Point pt2 = new Point(0.0, 1.0, 0.0);
		Point pt3 = new Point(1.0, 2.0, 3.0);
		assertThrows(IllegalArgumentException.class, () -> new Plane(pt1, pt2, pt3), "ERROR: attempted to create plane with 2 points on the same line");

		//this is the normal constructor 
		Point p1 = new Point(1.0, 0.0, 0.0);
		Point p2 = new Point(0.0, 1.0, 0.0);
		Point p3 = new Point(0.0, 0.0, 1.0);

		Plane plane = new Plane(p1, p2, p3);

		// Point on the plane(each have the same normal or should have)
		Point planePoint = new Point(1.0, 1.0, 1.0);

		// Call getNormal and check if the result is not null
		// this is to show that the point is in fact on the plane, if it returns nulls then it means that
		// point is not on the plane of the crossproduct calculation isnt correct.
		Vector normal = plane.getNormal(planePoint);
		assertNotNull(normal, "getNormal() should not return null");  
	}
	@Test
	void testNormalizedVector() {
		// Create a plane with three points
		Plane plane = new Plane(new Point(0, 0, 0), new Point(1, 0, 0), new Point(0, 1, 0));

		// Get the normal vector
		Vector normal = plane.getNormal();

		// Check that the length of the normal vector is approximately 1
		assertEquals(1.0, normal.length(), 0.000001, "Plane's normal vector is not normalized");
	}

}
