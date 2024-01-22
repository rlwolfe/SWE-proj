/**
 * @author Rachel and Bella
 */
package unittests;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class PointTests {
	Vector vec;

	/**
	 * Test methods for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAddEq() {
		//Equivalence: what it expects to see;
	
		Point point1= new Point(1,2,3);
		Vector vec1 = new Vector(5,6,7);
		//we have the actual result of the function and then the expected one. 
		Point result = point1.add(vec1);
		Point test= new Point(6,8,10);
		//our assert checker is going to expected/actual
        assertEquals(test, result );	
	}
	@Test
    void testAddZeroVector() {
        Point point = new Point(1, 2, 3);
        Vector zeroVector = new Vector(0, 0, 0);
        Point result = point.add(zeroVector);
        assertEquals(point, result);  // Adding a zero vector should not change the point
    }
	@Test
    void testAddNegativeVector() {
        Point point = new Point(1, 2, 3);
        Vector negativeVector = new Vector(-2, -3, -4);
        Point result = point.add(negativeVector);
        Point test = new Point(-1, -1, -1);
        assertEquals(test, result);  // Adding a negative vector subtracts the vector
    }
	
	@Test
	void testAddBoundedMax() {
		Point p1= new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		
		Vector v1=new Vector(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Point result = p1.add(v1);
        Point test= new Point(Double.MAX_VALUE+Double.MAX_VALUE, Double.MAX_VALUE+Double.MAX_VALUE, Double.MAX_VALUE+Double.MAX_VALUE);
        assertEquals(test, result);
	}

	@Test
	void testAddBoundedMIN() {
		Point p1= new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
		
		Vector v1=new Vector(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
        Point result = p1.add(v1);
        Point test= new Point(Double.MIN_VALUE+Double.MIN_VALUE, Double.MIN_VALUE+Double.MIN_VALUE, Double.MIN_VALUE+Double.MIN_VALUE);
        assertEquals(test, result);	
	}
	
	/**
	 * Test methods for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistanceEq() {
		    Point point1 = new Point(1, 2, 3);
		    Point point2 = new Point(4, 6, 8);

		    double result = point1.distance(point2);
		    double test = Math.sqrt(3 * 3 + 4 * 4 + 5 * 5);

		    assertEquals(test, result);
		    }
	@Test
    void testDistanceZeroPoints() {
        Point point1 = new Point(0, 0, 0);
        Point point2 = new Point(0, 0, 0);
        double result = point1.distance(point2);
        assertEquals(0, result);  // Distance between identical points is zero
    }
		
	@Test
	void testDistanceBoundedMax() {
		Point point1 = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		Point point2 = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		double result = point1.distance(point2);
		double test = Math.sqrt(Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2) +
				Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2) +
	            Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2));
		assertEquals(test, result);
		
	}
	@Test
	void testDistanceBoundedMin() {
		Point point1 = new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
		Point point2 = new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
		double result = point1.distance(point2);
		double test = Math.sqrt(Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2) +
				Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2) +
	            Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2));
		assertEquals(test, result);
		
	}
	

	/**
	 * Test methods for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 */
	@Test
	void testDistanceSquaredEq() {
		Point point1 = new Point(1, 2, 3);
	    Point point2 = new Point(4, 6, 8);

	    double result = point1.distanceSquared(point2);
	    double test = 3 * 3 + 4 * 4 + 5 * 5;

	    assertEquals(test, result);
	}
	@Test
    void testDistanceSquaredZeroPoints() {
        Point point1 = new Point(0, 0, 0);
        Point point2 = new Point(0, 0, 0);
        double result = point1.distanceSquared(point2);
        assertEquals(0, result);  // Squared distance between identical points is zero
    }
	@Test
	void testDistanceSquaredBoundedMax() {
	    // Using extreme values
	    Point point1 = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
	    Point point2 = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);

	    double result = point1.distanceSquared(point2);

	    // Expected result is the squared distance between extreme points
	    double test = Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2) +
	            Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2) +
	            Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2);

	    assertEquals(test, result);
	}
	@Test
	void testDistanceSquaredBoundedMin() {
	    // Using extreme values
	    Point point1 = new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
	    Point point2 = new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);

	    double result = point1.distanceSquared(point2);

	    // Expected result is the squared distance between extreme points
	    double test = Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2) +
	            Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2) +
	            Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2);

	    assertEquals(test, result);
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 */
	@Test
	void testSubtractEq() {
		// Equivalence: what it expects to see;
	    Point point1 = new Point(5, 10, 15);
	    Point point2 = new Point(2, 4, 6);

	    // Subtracting point2 from point1
	    Vector result = point1.subtract(point2);
	    Vector test = new Vector(3, 6, 9);

	    // Assert the expected result
	    assertEquals(test, result);
		
	}
	@Test
    void testSubtractZeroVector() {
        Point point = new Point(1, 2, 3);
        Vector zeroVector = new Vector(0, 0, 0);
        Point result = point.subtract(zeroVector);
        assertEquals(point, result);  // Subtracting a zero vector should not change the point
    }
	@Test
    void testSubtractEqualPoints() {
        Point point1 = new Point(2, 4, 6);
        Point point2 = new Point(2, 4, 6);
        Vector result = point1.subtract(point2);
        Vector test = new Vector(0, 0, 0);
        assertEquals(test, result);  // Subtracting equal points results in a zero vector
    }
	@Test
	void testSubtractNegativeVector() {
	    // Equivalence: what it expects to see;
	    Point point1 = new Point(5, 10, 15);
	    Vector negativeVector = new Vector(-2, -4, -6);

	    // Subtracting a negative vector from the point
	    Point result = point1.subtract(negativeVector);
	    Point test = new Point(7, 14, 21);

	    // Assert the expected result
	    assertEquals(test, result);
	}

	@Test
	void testASubBoundedMax() {
		Point p1= new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
		
		Vector v1=new Vector(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        Point result = p1.subtract(v1);
        Point test= new Point(Double.MAX_VALUE-Double.MAX_VALUE, Double.MAX_VALUE-Double.MAX_VALUE, Double.MAX_VALUE-Double.MAX_VALUE);
        assertEquals(test, result);
	}
	@Test
	void testASubBoundedMIN() {
		Point p1= new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
		
		Vector v1=new Vector(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
        Point result = p1.subtract(v1);
        Point test= new Point(Double.MIN_VALUE-Double.MIN_VALUE, Double.MIN_VALUE-Double.MIN_VALUE, Double.MIN_VALUE-Double.MIN_VALUE);
        assertEquals(test, result);	
	}


}