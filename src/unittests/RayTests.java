package unittests;
import primitives.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

class RayTests {

	@Test
	void findClosestIntersectionTest() {
		Ray r = new Ray(new Point(6.65, -0.52, 0), new Vector(-6.65, 0.52, 1.59));
		Point a = new Point(4.75, -0.37, 0.45);
		Point b = new Point(2.81, -0.22, 0.92);
		Point c = new Point(1.09, -0.09, 1.33);
		Point d = new Point(-1.64, 0.13, 1.98);
		List<Point> noIintersections = new LinkedList();

		List<Point> intersections1 = new LinkedList();
		intersections1.add(b);
		intersections1.add(a);
		intersections1.add(c);
		intersections1.add(d);

		List<Point> intersections2 = new LinkedList();
		intersections2.add(a);
		intersections2.add(b);
		intersections2.add(c);
		intersections2.add(d);
		
		List<Point> intersections3 = new LinkedList();
		intersections3.add(b);
		intersections3.add(c);
		intersections3.add(d);
		intersections3.add(a);
		
	      // ============ Equivalence Partitions Tests ==============
		//TC1 closest point is in middle of list
		assertEquals(r.findClosestPoint(intersections1),a, "Error TC1 EP Test." );
		
	      // =============== Boundary Values Tests ==================
		//TC2 closest point is at the beginning of list
		assertEquals(r.findClosestPoint(intersections2),a, "Error TC2 BV Test." );
		
		//TC3 closest point is at the end of the list
		assertEquals(r.findClosestPoint(intersections3),a, "Error TC3 BV Test." );
		
		//TC4 list of points is empty
		assertEquals(r.findClosestPoint(noIintersections),null, "Error TC4 BV Test." );

	}

}