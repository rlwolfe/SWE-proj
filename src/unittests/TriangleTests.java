/**
 * @author Rachel and Bella
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;
import geometries.Triangle;
import primitives.Point;
import primitives.Vector;
import primitives.Double3;
import primitives.Ray;

import org.junit.jupiter.api.Test;
import geometries.Plane;
import java.util.List;

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
        
        // ensure there are no exceptions
        assertDoesNotThrow(() -> triangle.getNormal(new Point(0, 0, 0)), "Error in getNormal for Triangle");
        
        System.out.println("Triangle normal: " + triangle.getNormal(new Point(0, 0, 0)));

        // generate the test result
        Vector result = triangle.getNormal(new Point(0, 0, 0));

        // ensure getNormal doesn't return null
        assertNotNull(result, "getNormal() should not return null");
        
        // ensure |result| = 1
        assertEquals(1, result.length(), DELTA, "Triangle's normal is not a unit vector");

        // ensure the result is orthogonal to the triangle
        Vector edge1 = p2.subtract(p1);
        Vector edge2 = p3.subtract(p1);
        Vector normalToTriangle = edge1.crossProduct(edge2).normalize();

        System.out.println("Result: " + result);
        System.out.println("Expected: " + normalToTriangle);
        System.out.println("Dot Product: " + result.dotProduct(normalToTriangle));

        assertEquals(0d, result.dotProduct(edge1.subtract(edge2).normalize()), DELTA,
        		"Triangle's normal is not orthogonal to one of the edges");

        //assertEquals(0, result.dotProduct(normalToTriangle), DELTA, "Triangle's normal is not orthogonal to the triangle");
    }
    @Test 
	void testFindIntersectionPoints() {


            Triangle tr = new Triangle(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));

            Plane pl = new Plane(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0));

            Ray ray;

            // ============ Equivalence Partitions Tests ==============

            // TC01: Inside triangle

            ray = new Ray(new Point(1, 1, 1), new Vector(-1, -1, -1));

            assertEquals(List.of(new Point(1d / 3, 1d / 3, 1d / 3)), tr.findIntersections(ray),

                    "Bad intersection");



            // TC02: Against edge

            ray = new Ray(new Point(0, 0, -1), new Vector(1, 1, 0));

            assertEquals(List.of(new Point(1, 1, -1)), pl.findIntersections(ray),

                    "Wrong intersection with plane");

            assertNull(tr.findIntersections(ray), "Bad intersection");



            // TC03: Against vertex

            ray = new Ray(new Point(0, 0, 2), new Vector(-1, -1, 0));

            assertEquals(List.of(new Point(-0.5, -0.5, 2)), pl.findIntersections(ray),

                    "Wrong intersection with plane");

            assertNull(tr.findIntersections(ray), "Bad intersection");



            // =============== Boundary Values Tests ==================

            // TC11: In vertex

            ray = new Ray(new Point(-1, 0, 0), new Vector(1, 1, 0));

            assertEquals(List.of(new Point(0, 1, 0)), pl.findIntersections(ray),

                    "Wrong intersection with plane");

            assertNull(tr.findIntersections(ray), "Bad intersection");



            // TC12: On edge

            ray = new Ray(new Point(-1, -1, 0), new Vector(1, 1, 0));

            assertEquals(List.of(new Point(0.5, 0.5, 0)), pl.findIntersections(ray),

                    "Wrong intersection with plane");

            assertNull(tr.findIntersections(ray), "Bad intersection");



            // TC13: On edge continuation

            ray = new Ray(new Point(-2, 0, 0), new Vector(1, 1, 0));

            assertEquals(List.of(new Point(-0.5, 1.5, 0)), pl.findIntersections(ray),

                    "Wrong intersection with plane");

            assertNull(tr.findIntersections(ray), "Bad intersection");

        }
    	  @Test
    	    void findIntersections() {
    	        Triangle test = new Triangle(new Point(0,0,0),new Point(-2,0,0),new Point(0,0,2));
    	        Point testPoint = new Point(0,-2,0);
    	        List<Point> result;
    	        // ============ Equivalence Partitions Tests ==============

    	        // TC01: Inside triangle
    	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-0.83,2,0.58))));
    	        assertEquals(result.get(0),new Point(-0.83,0,0.58));
    	        // TC02: Against edge
    	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-2,2,2))));
    	        assertNull(result);
    	        // TC03: Against vertex
    	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(0.5,2,0))));
    	        assertNull(result);

    	        // =============== Boundary Values Tests ==================

    	        // TC11: In vertex
    	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-1.61,-0.13,0))));
    	        assertNull(result);
    	        // TC12: On edge
    	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-1,2,0))));
    	        assertEquals(result.get(0),new Point(-1,0,0));
    	        // TC13: On edge continuation
    	        result = test.findIntersections(new Ray(testPoint,new Vector(new Double3(-2,2,0))));
    	        assertEquals(result.get(0),new Point(-2,0,0));
    	    }
    }
