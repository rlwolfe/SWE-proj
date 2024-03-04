package unittests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import geometries.Geometries;
import geometries.Plane;
import geometries.Sphere;
import geometries.Triangle;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;


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
		geometries.add(new Plane(new Point(0, 0, 0), new Vector(1, 0, 0)),
                new Sphere(new Point(0, 0, 0), 2),
                new Triangle(new Point(1, 1, 1), new Point(1, 2, 3), new Point(4, 5, 6)));
	    // Create a ray that does not intersect with any of the geometries
	    Ray ray = new Ray(new Point(10, 10, 10), new Vector(1, 0, 0));
	    assertNull(geometries.findIntersections(ray), "No intersection points should be found");
	    
		//All geometries intersect
	    Point rayOrigin = new Point(0, 0, 0);
	    Vector rayDirection = new Vector(1, 1, 1).normalize(); // Direction towards (2, 2, 1)
	    Ray ray1 = new Ray(rayOrigin, rayDirection);
	 // Find intersection points
	    List<Point> intersectionPoints = geometries.findIntersections(ray1);
	    //want to see all intersection points:
	    if (intersectionPoints != null && !intersectionPoints.isEmpty()) {
	        System.out.println("Intersection points:");
	        for (Point intersectionPoint : intersectionPoints) {
	            System.out.println(intersectionPoint);
	        }
	    } else {
	        System.out.println("No intersection points found.");
	    }
	 // end of this
	    
	    
	 // Assert that the intersection points list is not null and contains the expected point
	    assertNotNull(intersectionPoints, "Intersection list should not be null");
	    assertEquals(1, intersectionPoints.size(), "Expected one intersection point");
	    
	    
		
		//Only one geometry intersects
		
		// ============ Equivalence Partitions Tests ==============
		//Several (but not all) geometries intersect
		
	}

}
