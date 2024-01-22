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
		
        Point p1 = new Point(1.0, 0.0, 0.0);//this is the constructor 
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
        
        
    }
	
	
	@Test
	void testGetNormalPoint() {
		
	}

}
