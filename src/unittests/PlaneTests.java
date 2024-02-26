/**
 * @author Rachel and Bella
 */
package unittests;
import geometries.Plane;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;


import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import java.util.LinkedList;

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
		// point is not on the plane of the crossproduct calculation isn't correct.
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
	
	
	@Test
	void testRayIntersectsPlane() {
	    // Create a plane and a ray that intersects the plane
	    Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
	    Ray ray = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));

	    // Test case: Ray intersects the plane
	    List<Point> intersections = plane.findIntersections(ray);
	    assertNotNull(intersections, "Ray intersects the plane");
	    assertEquals(1, intersections.size(), "Incorrect number of intersection points");
	    assertEquals(new Point(0, 0, 0), intersections.get(0), "Incorrect intersection point");
	}
	@Test
	void testRayDoesNotIntersectPlane() {
	    // Create a plane and a ray that does not intersect the plane
	    Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
	    Ray ray = new Ray(new Point(1, 1, -1), new Vector(0, 0, -1));

	    // Test case: Ray does not intersect the plane
	    List<Point> intersections = plane.findIntersections(ray);
	    assertNotNull(intersections, "Intersection list should not be null");
	    assertEquals(0, intersections.size(), "Ray does not intersect the plane, should have no intersections");
	}


	@Test
	void testParallelRay() {
		// Create a plane and a parallel ray
		Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
		Ray parallelRay = new Ray(new Point(1, 1, 0), new Vector(1, 1, 0));

		// Test case: Ray is parallel to the plane
		List<Point> intersections = plane.findIntersections(parallelRay);
		assertNull(intersections, "Ray is parallel to the plane");
	}
	@Test
	void testRayOrthogonalBeforePlane() {
		// Create a plane and a ray orthogonal to the plane before it
		Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
		Ray orthogonalRay = new Ray(new Point(0, 0, -1), new Vector(0, 0, 1));

		// Test case: Ray is orthogonal to the plane and before it
		List<Point> intersections = plane.findIntersections(orthogonalRay);
		assertNotNull(intersections, "Ray is orthogonal to the plane and before it");
		assertEquals(1, intersections.size(), "Incorrect number of intersection points");
	}
	@Test
	void testRayOrthogonalInPlane() {
		// Create a plane and a ray orthogonal to the plane at a point in the plane
		Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
		Ray orthogonalRay = new Ray(new Point(1, 1, 0), new Vector(0, 0, 1));

		// Test case: Ray is orthogonal to the plane and in it
		List<Point> intersections = plane.findIntersections(orthogonalRay);
		assertNotNull(intersections, "Ray is orthogonal to the plane and in it");
		assertEquals(1, intersections.size(), "Incorrect number of intersection points");
	}
	@Test
	void testRayOrthogonalAfterPlane() {
	    // Create a plane and a ray orthogonal to the plane after it
	    Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
	    Ray orthogonalRay = new Ray(new Point(0, 0, 1), new Vector(0, 0, 1));

	    // Test case: Ray is orthogonal to the plane and after it
	    List<Point> intersections = plane.findIntersections(orthogonalRay);
	    assertNotNull(intersections, "Intersection list should not be null");
	    assertEquals(0, intersections.size(), "Ray is orthogonal to the plane and after it, should have no intersections");
	}

	@Test
	void testRayNotOrthogonalNotParallelBeforePlane() {
	    // Create a plane and a ray neither orthogonal nor parallel to the plane before it
	    Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
	    Ray ray = new Ray(new Point(0, 0, -1), new Vector(1, 1, -1));

	    // Test case: Ray is neither orthogonal nor parallel to the plane and before it
	    List<Point> intersections = plane.findIntersections(ray);
	    assertNotNull(intersections, "Ray is neither orthogonal nor parallel to the plane and before it");
	    assertTrue(intersections.isEmpty(), "Ray is neither orthogonal nor parallel to the plane and before it");
	}

	@Test
	void testRayNotOrthogonalNotParallelInPlaneOnRefPoint() {
	    // Create a plane and a ray neither orthogonal nor parallel to the plane, starts at a reference point in the plane
	    Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
	    Ray ray = new Ray(new Point(0, 0, 0), new Vector(1, 1, 1));

	    // Test case: Ray is neither orthogonal nor parallel to the plane and starts at a reference point in the plane
	    List<Point> intersections = plane.findIntersections(ray);
	    assertEquals(0, intersections.size(), "Ray is neither orthogonal nor parallel to the plane and starts at a reference point in the plane");
	}

	@Test
	void testRayNotOrthogonalNotParallelInPlaneNotOnRefPoint() {
	    // Create a plane and a ray neither orthogonal nor parallel to the plane, starts in the plane but not at a reference point
	    Plane plane = new Plane(new Point(0, 0, 0), new Vector(0, 0, 1));
	    Ray ray = new Ray(new Point(1, 1, 0), new Vector(1, 1, 1));

	    // Test case: Ray is neither orthogonal nor parallel to the plane and starts in the plane but not at a reference point
	    List<Point> intersections = plane.findIntersections(ray);
	    assertNotNull(intersections, "Intersection list should not be null");
	    assertEquals(0, intersections.size(), "Ray is neither orthogonal nor parallel to the plane and starts in the plane but not at a reference point, should have no intersections");
	}

}


