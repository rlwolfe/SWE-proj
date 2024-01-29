/**
 * @author Rachel and Bella
 */
package unittests;
import geometries.Sphere;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SphereTests {

	/**
	 * Test method for {@link geometries.Sphere#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
	        // Create a sphere with a center at the origin and radius 1
	        Sphere sphere = new Sphere(new Point(0, 0, 0), 1);

	        // Test case: Point on the surface of the sphere (e.g., (1, 0, 0))
	        Point surfacePoint = new Point(1, 0, 0);
	        Vector result = sphere.getNormal(surfacePoint);

	        // The result should be a normalized vector pointing outward from the center
	        Vector expected = new Vector(1, 0, 0);
	        assertEquals(expected, result, "Failed for a point on the surface");
	    }
	

}
