/**
 * @author Rachel and Bella
 */
package unittests;
import geometries.Plane;
import primitives.Point;
import primitives.Vector;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class PlaneTests {

	/**
	 * Test method for {@link geometries.Plane#getNormal()}.
	 * The whole idea for Normal is to get the normal on ay point 
	 * normal means the line that is perpindicular, and bc a plane is flat 
	 * each point should have the same normal. 
	 * a flat surface has a perpendicular line going out all the same. 
	 */
	
	//Equivalance 
	@Test
    void testGetNormal() {
		
        Point p1 = new Point(1.0, 0.0, 0.0);
        Point p2 = new Point(0.0, 1.0, 0.0);
        Point p3 = new Point(0.0, 0.0, 1.0);
        Plane plane = new Plane(p1, p2, p3);

        // Point on the plane(each have the same normal or should have)
        Point planePoint = new Point(1.0, 1.0, 1.0);

        // Call getNormal and check if the result is not null
        // this is to show that the point is in fact on the plane, if it retuns nulls then it means that
        // point is not on the plane of the crossprodcut calculation isnt correct.
        Vector normal = plane.getNormal(planePoint);
        assertNotNull(normal, "getNormal() should not return null");

        // do this test again for a different point on the plane
        Point diffPoint = new Point(2.0, 2.0, 2.0);
        Vector normal2 = plane.getNormal(diffPoint);

        // Check if the result is not null and is in the correct direction
        // normal will be the same with these vectors but if it isnt will return a false statement
        assertNotNull(normal2, "getNormal() should not return null for a different point on the plane");
        assertTrue(normal.dotProduct(normal2) > 0, "Normals for different points on the plane should have the same direction");
    }
	//Boundry 
	@Test
    void testGetNormalBoundary() {
        //Same Points
        Point samePoint = new Point(1.0, 1.0, 1.0);
        Plane identicalPlane = new Plane(samePoint, samePoint, samePoint);

        // choosing any point bc all have the same normal (included with double coordinates)
        Point anyPoint = new Point(2.0, 2.0, 2.0);

        // check if the result is not null
        Vector normalAtsamePoint = identicalPlane.getNormal(anyPoint);
        assertNotNull(normalAtsamePoint, "getNormal() should not return null for a plane with three identical points");

        // NEW TEST plane with two identical points
        Point point1 = new Point(1.0, 0.0, 0.0);
        Point point2 = new Point(1.0, 1.0, 0.0);
        Plane planeWithTwoIdenticalPoints = new Plane(point1, point2, point2);

        //the point to cmpare it too
        Point anyPoint2 = new Point(2.0, 2.0, 0.0);

        // Make sure its not null 
        Vector normalAtIdenticalPoint2 = planeWithTwoIdenticalPoints.getNormal(anyPoint2);
        assertNotNull(normalAtIdenticalPoint2, "getNormal() should not return null for a plane with two identical points");

        // NEW TEST plane with three collinear points
        Point collinearPoint1 = new Point(1.0, 0.0, 0.0);
        Point collinearPoint2 = new Point(2.0, 0.0, 0.0);
        Point collinearPoint3 = new Point(3.0, 0.0, 0.0);
        Plane collinearPlane = new Plane(collinearPoint1, collinearPoint2, collinearPoint3);

        // Same point as before 
        Point anyPoint3 = new Point(2.0, 2.0, 0.0);

        // Call getNormal and check if the result is not null
        Vector normalAtCollinearPoint = collinearPlane.getNormal(anyPoint3);
        assertNotNull(normalAtCollinearPoint, "getNormal() should not return null for a plane with three collinear points");
    }

	
	@Test
	void testGetNormalPoint() {
		fail("Not yet implemented");
	}

}
