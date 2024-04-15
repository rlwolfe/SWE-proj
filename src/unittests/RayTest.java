package unittests;

import static org.junit.jupiter.api.Assertions.*;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import org.junit.jupiter.api.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;

/**
 * Bella & Rachel
 */
class RayTest {

	/**
	 * tests getPoint in ray
	 */
	@Test
	void testGetPoint() {
		Point p1= new Point(1,2,3);
		Vector vec= new Vector(2,4,6);
		Ray r1= new Ray (p1, vec);
		double tpos=1;
		double tneg=-1;
		double tzero=0;
		Point testpositive=new Point(3,6,9);
		Point testneg= new Point(-1, -2, -3);	
	
		assertEquals(testpositive, r1.getPoint(tpos), "Error, positive point calculation from Ray");
		assertEquals(testneg, r1.getPoint(tneg), "Error, negative point calculation from Ray");
		assertEquals(p1, r1.getPoint(tzero), "Error, doesnt return head when t is zero");

	}
	
	/**
	 * tests getFindCloseestPoint in ray
	 */
	@Test
	void testFindClosestPoint() {
		
		Point point1 = new Point(1, 0, 0);
		Point point2 = new Point(4, 0, 0);
		Ray ray = new Ray( new Point(0.5, 0, 0), new Vector(1, 0, 0));
	    Triangle triangle = new Triangle(new Point(4, -2, -1),new Point(4, 2, -1),new Point(4, 0, 1));
	    Sphere sphere = new Sphere(new Point(2, 0, 0), 1);
	    Plane plane = new Plane(new Point(5, 2, 2),new Vector(1, 0, 0));
	    Geometries geometries1 = new Geometries(triangle, sphere, plane);
	    Geometries geometries2 = new Geometries(sphere, triangle, plane);
	    Geometries geometries3 = new Geometries(plane, triangle);
	    Geometries emptyGeometries = new Geometries();
	    		
		// ============ Equivalence Partitions Tests ==============

	    // TC01: point is found in the middle of the list
		assertEquals(point1, ray.findClosestPoint(geometries1.findIntersections(ray)), "point is not the closest");
		
		// =============== Boundary Values Tests ==================
		// TC10: point is not found because the list is empty
		assertNull(ray.findClosestPoint(emptyGeometries.findIntersections(ray)), "empty list");
		
		// TC11: point is found as the first in the list
        assertEquals(point1, ray.findClosestPoint(geometries2.findIntersections(ray)), "wrong intersection point when point is first");
		
	    // TC12: point is found as the last in the list
        assertEquals(point2,ray.findClosestPoint(geometries3.findIntersections(ray)), "wrong intersection point when point is last");
		
			
	}
}