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
		//equivalence test
		Point point = new Point(5, 5, 5);
		Vector vector = new Vector(1, 1, 1);
		Ray ray = new Ray(point, vector);
		Tube tube = new Tube(2, ray);
		Vector test = new Vector(-10, -10, -10);
		
		assertEquals(test, tube.getNormal(point), "ERROR: get normal of tube has an issue");
		
		//boundary tests
		//when the connection between the point on the body and the ray’s head creates a 90 degree angle with the ray
		
	}

}
