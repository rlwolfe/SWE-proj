/**
 * @author Rachel and Bella
 */
package unittests;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PointTests {
	//points needed for tests
	Point point1 = new Point(1, 2, 3);
	Point point2 = new Point(6, 8, 10);
	Point point3 = new Point(5, 10, 15);
	Point point4 = new Point(2, 4, 6);
	Point point5 = new Point(7, 14, 21);
	Point pointDec = new Point(4.0, 6.0, 8.0);
	Point negativePoint = new Point(-1, -1, -1);
	Point zeroPoint = new Point(0, 0, 0);
	Point maxPoint1 = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
	Point maxPoint2 = new Point(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
	Point minPoint1 = new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
	Point minPoint2 = new Point(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);


	//vectors needed for tests
	Vector vector1 = new Vector(5, 6, 7);
	Vector vector2 = new Vector(3, 6, 9);
	Vector negativeVector1 = new Vector(-2, -3, -4);
	Vector negativeVector2 = new Vector(-2, -4, -6);
	Vector zeroVector = new Vector(0, 0, 0);
	Vector maxVector = new Vector(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
	Vector minVector = new Vector(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);

	/**
	 * Test methods for {@link primitives.Point#add(primitives.Vector)}.
	 */
	@Test
	void testAdd() {
		//****** Equivalence test: what it expects to see
		//we have the actual result of the function and then the expected one.
		//our assert checker is going to expected/actual
		assertEquals(point2, point1.add(vector1), "ERROR: adding regular values");

		//****** Boundary tests: edge cases
		assertEquals(point1, point1.add(zeroVector), "ERROR: adding zero vector to a point");  // Adding a zero vector should not change the point

		assertEquals(negativePoint, point1.add(negativeVector1), "ERROR: adding negative vector to a point");  // Adding a negative vector subtracts the vector

		//Adding max values together
		Point testMax = new Point(Double.MAX_VALUE + Double.MAX_VALUE, Double.MAX_VALUE + Double.MAX_VALUE, Double.MAX_VALUE + Double.MAX_VALUE);
		assertEquals(testMax, maxPoint1.add(maxVector), "ERROR: adding max vector to a point");

		//Adding min values together
		Point testMin = new Point(Double.MIN_VALUE + Double.MIN_VALUE, Double.MIN_VALUE + Double.MIN_VALUE, Double.MIN_VALUE + Double.MIN_VALUE);
		assertEquals(testMin, minPoint1.add(minVector), "ERROR: adding min vector to a point");	
	}

	/**
	 * Test methods for {@link primitives.Point#distance(primitives.Point)}.
	 */
	@Test
	void testDistance() {
		//****** Equivalence test: what it expects to see
		double test1 = Math.sqrt(((4-1) *(4-1) ) + ((6-2) * (6-2)) + ((8-3) * (8-3)));
		assertEquals(test1, point1.distance(pointDec), "ERROR: distance with regular points");

		//****** Boundary tests: edge cases
		assertEquals(0, point1.distance(point1), "ERROR: distance with identical points");  //Distance between identical points is zero


		double test2 = Math.sqrt(Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2) +
				Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2) +
				Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2));
		assertEquals(test2, maxPoint1.distance(maxPoint2), "ERROR: distance with max points");	//Distance between identical points is zero even when at max


		double test3 = Math.sqrt(Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2) +
				Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2) +
				Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2));
		assertEquals(test3, minPoint1.distance(minPoint2), "ERROR: distance with min points");	//Distance between identical points is zero even when at min
	}

	/**
	 * Test methods for {@link primitives.Point#distanceSquared(primitives.Point)}.
	 * Equivalence: what it expects to see
	 */
	@Test
	void testDistanceSquared() {
		//****** Equivalence test: what it expects to see
		double test1 = (4-1) * (4-1) + (6-2) * (6-2) + (8-3) * (8-3);
		assertEquals(test1, point1.distanceSquared(pointDec), "ERROR: distance**2 with regular points");

		//****** Boundary tests: edge cases
		assertEquals(0, point1.distanceSquared(point1), "ERROR: distance**2 with identical points");  // Squared distance between identical points is zero

		assertEquals(0, zeroPoint.distanceSquared(zeroPoint), "ERROR: distance**2 with point to itself");  // Squared distance between identical points that are zero is zero

		// Expected result is the squared distance between extreme points
		double test2 = Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2) +
				Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2) +
				Math.pow(Double.MAX_VALUE - Double.MAX_VALUE, 2);
		assertEquals(test2, maxPoint1.distanceSquared(maxPoint2), "ERROR: distance**2 with max points");

		// Expected result is the squared distance between extreme points
		double test = Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2) +
				Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2) +
				Math.pow(Double.MIN_VALUE - Double.MIN_VALUE, 2);
		assertEquals(test, minPoint1.distanceSquared(minPoint2), "ERROR: distance**2 with min points");
	}

	/**
	 * Test method for {@link primitives.Point#subtract(primitives.Point)}.
	 * Equivalence: what it expects to see
	 */
	@Test
	void testSubtract() {

		//****** Equivalence test: what it expects to see
		// Assert the expected result subtracting point2 from point1
		assertEquals(vector2, point3.subtract(point2), "ERROR: subtract with regular points");

		//****** Boundary tests: edge cases
		assertEquals(point1, point1.subtract(zeroVector), "ERROR: subtract with zero vector and point");  // Subtracting a zero vector should not change the point

		assertEquals(zeroVector, point2.subtract(point4), "ERROR: subtract with identical points");  // Subtracting equal points results in a zero vector

		assertEquals(point5, point3.subtract(negativeVector2), "ERROR: subtract with negative vector and point");	//checking for expected result

		Point testMax = new Point(Double.MAX_VALUE - Double.MAX_VALUE, Double.MAX_VALUE - Double.MAX_VALUE, Double.MAX_VALUE - Double.MAX_VALUE);
		assertEquals(testMax, maxPoint1.subtract(maxVector), "ERROR: subtract with max vector and point");  //test if values are max possible

		Point testMin= new Point(Double.MIN_VALUE - Double.MIN_VALUE, Double.MIN_VALUE - Double.MIN_VALUE, Double.MIN_VALUE - Double.MIN_VALUE);
		assertEquals(testMin, minPoint1.subtract(minVector), "ERROR: subtract with min vector and point");  //test if values are min possible
	}

}
