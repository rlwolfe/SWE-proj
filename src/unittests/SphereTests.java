/**
 * @author Rachel and Bella
 */
package unittests;
import geometries.Sphere;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import static org.junit.jupiter.api.Assertions.*;

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
	
	￼private final Point p001 = new Point(0, 0, 1);
	private final Point p100 = new Point(1, 0, 0);
	private final Vector v001 = new Vector(0, 0, 1);
	
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
		// TC01: Ray's line is outside the sphere (0 points) assertNull(sphere.findIntersections(new Ray(p01, v110)), "Ray's line out of sphere");
		// TC02: Ray starts before and crosses the sphere (2 points)
		final var result1 = sphere.findIntersections(new Ray(p01, v310)) .stream().sorted(Comparator.comparingDouble(p) -> p.distance(p01)))
		.toList();
		assertEquals(2, result1.size(), "Wrong number of points");
		assertEquals(exp, result1, "Ray crosses sphere");
		
	    // Create a sphere with a center at the origin and radius 1
	    Sphere sphere = new Sphere(new Point(0, 0, 0), 1);
	    
	    // Test case 1: Ray does not intersect the sphere and originates outside the sphere (entirely outside)
	    Ray ray1 = new Ray(new Point(2, 0, 0), new Vector(1, 0, 0));
	    assertNull(sphere.findIntersections(ray1), "Failed for a ray entirely outside the sphere");

	    // Test case 2: Ray that intersects sphere with the origin outside (before) sphere surface
	    Ray ray2 = new Ray(new Point(-2, 0, 0), new Vector(1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray2), "Failed for a ray originating before the sphere surface");

	    // Test case 3: Ray intersects sphere with the origin inside the sphere
	    Ray ray3 = new Ray(new Point(0.5, 0, 0), new Vector(1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray3), "Failed for a ray originating inside the sphere");

	    // Test case 4: Ray intersects sphere and the origin is after sphere surface
	    Ray ray4 = new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray4), "Failed for a ray originating after the sphere surface");
	    
	}
	@Test
	void testBVAOriginAtCenter() {
	    // Create a sphere with a center at the origin and radius 1
	    Sphere sphere = new Sphere(Point.ZERO, 1);

	    // Test case 1: Origin of the ray is at the center of the sphere
	    Ray ray1 = new Ray(Point.ZERO, new Vector(1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray1), "Failed for origin at center");

	    // Test case 2: Origin of the ray is on the radius of the sphere after the center
	    Ray ray2 = new Ray(new Point(0, 1, 0), new Vector(1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray2), "Failed for origin on radius after center");

	    // Test case 3: Origin of the ray is on the radius of the sphere before the center
	    Ray ray3 = new Ray(new Point(0, -1, 0), new Vector(1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray3), "Failed for origin on radius before center");

	    // Test case 4: Origin of the ray is outside the sphere after the center
	    Ray ray4 = new Ray(new Point(0, 2, 0), new Vector(1, 0, 0));
	    assertNull(sphere.findIntersections(ray4), "Failed for origin outside after center");

	    // Test case 5: Origin of the ray is outside the sphere before the center
	    Ray ray5 = new Ray(new Point(0, -2, 0), new Vector(1, 0, 0));
	    assertNull(sphere.findIntersections(ray5), "Failed for origin outside before center");

	    // Test case 6: Origin of the ray is inside the sphere after the center
	    Ray ray6 = new Ray(new Point(0, 0.5, 0), new Vector(1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray6), "Failed for origin inside after center");
	}
	@Test
	void testBVAIntersectNotAtCenter() {
	    // Create a sphere with a center at the origin and radius 1
	    Sphere sphere = new Sphere(Point.ZERO, 1);

	    // Test case 1: Origin of the sphere is on the sphere before intersection
	    // The ray starts from the point (-1, 0, 0) and goes to the point (1, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0). The origin of the sphere is on the sphere before intersection.
	    Ray ray1 = new Ray(new Point(-1, 0, 0), new Vector(1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray1), "Failed for origin on sphere before intersection");

	    // Test case 2: Origin of the sphere is on the sphere after intersection
	    // The ray starts from the point (1, 0, 0) and goes to the point (-1, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0). The origin of the sphere is on the sphere after intersection.
	    Ray ray2 = new Ray(new Point(1, 0, 0), new Vector(-1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray2), "Failed for origin on sphere after intersection");
	}
	@Test
	void testBVATangentLineIntersect() {
	    // Create a sphere with a center at the origin and radius 1
	    Sphere sphere = new Sphere(Point.ZERO, 1);

	    // Test case 1: Origin of the ray is outside the sphere and intersection point is on (touches) the radius
	    // The ray starts from the point (2, 0, 0) and goes to the point (0, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0) (touches the radius).
	    Ray ray1 = new Ray(new Point(2, 0, 0), new Vector(-1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray1), "Failed for origin outside and intersection point on the radius");

	    // Test case 2: Origin of the ray is on the radius
	    // The ray starts from the point (1, 0, 0) (on the sphere) and goes to the point (0, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0) (touches the radius).
	    Ray ray2 = new Ray(new Point(1, 0, 0), new Vector(-1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray2), "Failed for origin on the radius");

	    // Test case 3: Origin of the ray is after the intersection point of the sphere (on the radius)
	    // The ray starts from the point (0, 0, 0) and goes to the point (-1, 0, 0),
	    // which intersects the sphere at the point (1, 0, 0) (touches the radius).
	    Ray ray3 = new Ray(new Point(0, 0, 0), new Vector(-1, 0, 0));
	    assertNotNull(sphere.findIntersections(ray3), "Failed for origin after intersection point on the radius");
	}
	@Test
	void testBVARayOutsidePerpendicularToSegment() {
	    // Create a sphere with a center at the origin and radius 1
	    Sphere sphere = new Sphere(Point.ZERO, 1);

	    // Define the ray origin outside the sphere and perpendicular to the ray direction
	    Point rayOrigin = new Point(3, 0, 0);
	    Vector rayDirection = new Vector(1, 0, 0);
	    Ray ray = new Ray(rayOrigin, rayDirection);

	    // Perform the intersection test
	    List<Point> intersections = sphere.findIntersections(ray);

	    // Verify that intersections list is not null and there are no intersections
	    assertNull(intersections, "Failed for ray outside sphere perpendicular to segment");
	}

}
