package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.Vector;

class GeometriesTest {
	Geometries geometries = new Geometries();
	Point point = new Point(1, 2, 3);
	Vector vec = new Vector(1, 1, 1);
	Plane plane = new Plane(point, vec);
	Point p2 = new Point(1, 1, 1);
	Point p3 = new Point(4, 4, 4);
	Triangle triangle = new Triangle(point, p2, p3);
	Sphere sphere = new Sphere(point, 2);
	
	@Test
	void testAdd() {
		// =============== Boundary Values Tests ==================
		//Empty geometries collection
		assertEquals(0, geometries.getGeometries().size(), "");

		// ============ Equivalence Partitions Tests ==============
		//adding a geometry
		geometries.add(plane);
		assertEquals(1, geometries.getGeometries().size(), "");
	}

	@Test
	void testFindIntersections() {
		// =============== Boundary Values Tests ==================
		//No intersection point
		
		//All geometries intersect
		
		//Only one geometry intersects
		
		// ============ Equivalence Partitions Tests ==============
		//Several (but not all) geometries intersect
		
	}

}
