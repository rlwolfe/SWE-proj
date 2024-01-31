/**
 * @author Rachel and Bella
 */
package unittests;


import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import geometries.Tube;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

class TubeTests {

	/**
	 * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
	 */
	@Test
	void testGetNormal() {
		Vector vector = new Vector(1, 0, 0);
		Ray ray = new Ray(Point.ZERO, vector);
		Tube tube = new Tube(5, ray);
		Point point1 = new Point(5, 5, 0);
		Point point2 = new Point(0, 5, 0);
		Vector test = new Vector(0, 1, 0);
		
		//equivalence test
		assertEquals(test, tube.getNormal(point1), "ERROR: get normal of tube has an issue");
		
		//boundary tests
		assertEquals(test, tube.getNormal(point2), "ERROR: get normal of tube has an issue with orthogonal point");
		//when the connection between the point on the body and the ray’s head creates a 90 degree angle with the ray	
	}
}
