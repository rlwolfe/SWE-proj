/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * 
 */
class PointTests {

    Point  p1         = new Point(1, 2, 3);
    Point  p2         = new Point(2, 4, 6);
    Point  p3         = new Point(2, 4, 5);

    Vector v1         = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v3         = new Vector(0, 3, -2);
    Vector v4         = new Vector(1, 2, 2);

 // Test point operations ================================================

    
    /**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		 // Add vector to point
	    assertEquals(p1.add(v1), p2, "ERROR: (point + vector) = other point does not work correctly");

	    assertEquals(p1.add(v1Opposite), Point.ZERO, "ERROR: (point + vector) = center of coordinates does not work correctly");
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		 // Subtract points doesn't work
	    assertEquals(p2.subtract(p1), v1,"ERROR: (point2 - point1) does not work correctly");
	     
	    //subtracting point by itself doesn't throw error
	    assertThrows( IllegalArgumentException.class, () -> p1.subtract(p1), "Subtracting point by itself does not throw exception");
	    
	    //can't figure out how to check if the exception thrown is the correct one. ask joyce
	    //assertEquals(IllegalArgumentException.class, () -> p1.subtract(p1), "\"Error! cannot create vector with coordinates 0,0,0\"");
	}

	//COME BACK IF MORE TIME
	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
	}

	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
	}

}