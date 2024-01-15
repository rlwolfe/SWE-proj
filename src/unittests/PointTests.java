/**
 * @author Rachel and Bella
 */
package unittests;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
/**
 * 
 */
class PointTests {
	Vector vec;

	/**
	 * Test method for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAddEq() {
		//Equivalance: what it expects to see;
	
		Point point1= new Point(1,2,3);
		Vector vec1 = new Vector(5,6,7);
		//we have the actual result of the function and then the expected one. 
		Point result = point1.add(vec1);
		Point test= new Point(6,8,10);
		//our assert checker is going to expected/actual
        assertEquals(test, result );	
	}
	void testAddBoundedMax() {
		Point p1= new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		
		Vector v1=new Vector(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Point result = p1.add(v1);
        Point test= new Point(Double.MAX_VALUE+Double.MAX_VALUE, Double.MAX_VALUE+Double.MAX_VALUE, Double.MAX_VALUE+Double.MAX_VALUE);
        assertEquals(test, result);
	}
	void testAddBoundedMIN() {
		Point p1= new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
		
		Vector v1=new Vector(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
        Point result = p1.add(v1);
        Point test= new Point(Double.MIN_VALUE+Double.MIN_VALUE, Double.MIN_VALUE+Double.MIN_VALUE, Double.MIN_VALUE+Double.MIN_VALUE);
        assertEquals(test, result);	
	}
	/**
	 * Test method for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquared() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtract() {
		fail("Not yet implemented");
	}

}
