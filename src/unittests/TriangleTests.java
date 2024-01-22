/**
 * @author Rachel and Bella
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;
import geometries.Triangle;
import primitives.Point;
import primitives.Vector;

import org.junit.jupiter.api.Test;

/**
 * 
 */
class TriangleTests {
	private final double DELTA = 0.000001;

	/**
	 * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
	 */

	

    @Test
    public void testGetNormal() {
    	Point p1 = new Point(0, 0, 0);
    	Point p2 = new Point(1, 0, 0);
    	Point p3 = new Point(0, 1, 0);

    	Triangle triangle = new Triangle(p1, p2, p3);



        System.out.println("Triangle vertices: " + p1 + ", " + p3 + ", " + p2);
        System.out.println("Triangle normal: " + triangle.getNormal(new Point(0, 0, 0)));

        // ensure there are no exceptions
        assertDoesNotThrow(() -> triangle.getNormal(new Point(0, 0, 0)), "Error in getNormal for Triangle");

        // generate the test result
        Vector result = triangle.getNormal(new Point(0, 0, 0));

        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Triangle's normal is not a unit vector");

        // ensure the result is orthogonal to the triangle
        Vector edge1 = p2.subtract(p1);
        Vector edge2 = p3.subtract(p1);
        Vector normalToTriangle = edge1.crossProduct(edge2).normalize();

        System.out.println("Result: " + result);
        System.out.println("Expected: " + normalToTriangle);
        System.out.println("Dot Product: " + result.dotProduct(normalToTriangle));

        assertEquals(0, result.dotProduct(normalToTriangle), DELTA, "Triangle's normal is not orthogonal to the triangle");
    }
}