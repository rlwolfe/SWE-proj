/**
 * @author Rachel and Bella
 */
package unittests;
import geometries.Sphere;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Comparator;
import java.util.List;

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
	
	private final Point p100 = new Point(1, 0, 0);
	private final Vector v100 = new Vector(1, 0, 0);
	
	/**
	 * Test method for {@link geometries.Sphere#findIntersections(primitives.Ray)}
	 */
	@Test
	void testFindIntersectionPoints() {
		
		Sphere sphere = new Sphere(p100, 1d); 
		final Point gp1 = new Point(0.0651530771650466, 0.355051025721682, 0);
		final Point gp2 = new Point(1.53484692283495, 0.844948974278318, 0);
		final var exp = List.of(gp1, gp2);
		final Vector v310 = new Vector(3, 1, 0);
		final Vector v110 = new Vector(1, 1, 0);
		final Point p01 = new Point(-1, 0, 0);
		// ============ Equivalence Partitions Tests ==============
		// TC01: Ray's line is outside the sphere (0 points)
		assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
		
		// TC02: Ray starts before and crosses the sphere (2 points)
		final List<Point> result1 = sphere.findIntersections(new Ray(p01, v310)).stream().sorted(Comparator.comparingDouble(p -> p.distance(p01))).toList();
		assertEquals(2, result1.size(), "Wrong number of points");
		assertEquals(exp, result1, "Ray crosses sphere");
	    
		// TC03a: Ray intersects sphere with the origin inside the sphere
	    Ray rayDub = new Ray(new Point(0.5, 0, 0), v100);
	    assertNotNull(sphere.findIntersections(rayDub), "Failed for a ray originating inside the sphere");

	    // TC03b: Ray intersects sphere and the origin is after sphere surface
	    Ray ray = new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray), "Failed for a ray originating after the sphere surface");
	    
	    // TC04a: Ray does not intersect the sphere and originates outside the sphere (entirely outside)
	    Ray ray1 = new Ray(new Point(2, 0, 0), v100);
	    assertNull(sphere.findIntersections(ray1), "Failed for a ray entirely outside the sphere");

	    // TC04b: Ray that intersects sphere with the origin outside (before) sphere surface
	    Ray ray2 = new Ray(new Point(-2, 0, 0), v100);
	    assertNotNull(sphere.findIntersections(ray2), "Failed for a ray originating before the sphere surface");

	    
	    // =============== Boundary Values Tests ==================
	    //	-----------------origin at center-----------------
	    // Create a sphere with a center at the origin and radius 1
	    Sphere sphereZero = new Sphere(Point.ZERO, 1);

	    // Test case 1: Origin of the ray is at the center of the sphere
	    Ray ray3 = new Ray(Point.ZERO, v100);
	    assertNotNull(sphereZero.findIntersections(ray3), "Failed for origin at center");

	    // Test case 2: Origin of the ray is on the radius of the sphere after the center
	    Ray ray4 = new Ray(new Point(0, 1, 0), v100);
	    assertNotNull(sphereZero.findIntersections(ray4), "Failed for origin on radius after center");

	    // Test case 3: Origin of the ray is on the radius of the sphere before the center
	    Ray ray5 = new Ray(new Point(0, -1, 0), v100);
	    assertNotNull(sphereZero.findIntersections(ray5), "Failed for origin on radius before center");

	    // Test case 4: Origin of the ray is outside the sphere after the center
	    Ray ray6 = new Ray(new Point(0, 2, 0), v100);
	    assertNull(sphereZero.findIntersections(ray6), "Failed for origin outside after center");

	    // Test case 5: Origin of the ray is outside the sphere before the center
	    Ray ray7 = new Ray(new Point(0, -2, 0), v100);
	    assertNull(sphereZero.findIntersections(ray7), "Failed for origin outside before center");

	    // Test case 6: Origin of the ray is inside the sphere after the center
	    Ray ray8 = new Ray(new Point(0, 0.5, 0), v100);
	    assertNotNull(sphereZero.findIntersections(ray8), "Failed for origin inside after center");

	    //-----------------origin not at center-----------------

	    // Test case 1: Origin of the sphere is on the sphere before intersection
	    // The ray starts from the point (-1, 0, 0) and goes to the point (1, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0). The origin of the sphere is on the sphere before intersection.
	    Ray ray9 = new Ray(new Point(-1, 0, 0), v100);
	    assertNotNull(sphereZero.findIntersections(ray9), "Failed for origin on sphere before intersection");

	    // Test case 2: Origin of the sphere is on the sphere after intersection
	    // The ray starts from the point (1, 0, 0) and goes to the point (-1, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0). The origin of the sphere is on the sphere after intersection.
	    Ray ray10 = new Ray(p100, new Vector(-1, 0, 0));
	    assertNotNull(sphereZero.findIntersections(ray10), "Failed for origin on sphere after intersection");

	    //-----------------Tangent line intersect-----------------

	    // Test case 1: Origin of the ray is outside the sphere and intersection point is on (touches) the radius
	    // The ray starts from the point (2, 0, 0) and goes to the point (0, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0) (touches the radius).
	    Ray ray11 = new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0));
	    assertNotNull(sphereZero.findIntersections(ray11), "Failed for origin outside and intersection point on the radius");

	    // Test case 2: Origin of the ray is on the radius
	    // The ray starts from the point (1, 0, 0) (on the sphere) and goes to the point (0, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0) (touches the radius).
	    Ray ray12 = new Ray(p100, new Vector(-1, 0, 0));
	    assertNotNull(sphereZero.findIntersections(ray12), "Failed for origin on the radius");

	    // Test case 3: Origin of the ray is after the intersection point of the sphere (on the radius)
	    // The ray starts from the point (0, 0, 0) and goes to the point (-1, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0) (touches the radius).
	    Ray ray13 = new Ray(Point.ZERO, new Vector(-1, 0, 0));
	    assertNotNull(sphereZero.findIntersections(ray13), "Failed for origin after intersection point on the radius");

	    //-----------------Outside perpendicular to segment-----------------
	    // Define the ray origin outside the sphere and perpendicular to the ray direction
	    Point rayOrigin = new Point(3, 0, 0);
	    Vector rayDirection = v100;
	    Ray rayTest = new Ray(rayOrigin, rayDirection);

	    // Perform the intersection test
	    List<Point> intersections = sphereZero.findIntersections(rayTest);

	    // Verify that intersections list is not null and there are no intersections
	    assertNull(intersections, "Failed for ray outside sphere perpendicular to segment");
	}

}
