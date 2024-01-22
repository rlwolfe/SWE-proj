/**
 * @author Rachel and Bella
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import geometries.Cylinder;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

class CylinderTests {


    // ============ Equivalence Partitions Tests ==============

    @Test
    void testGetNormal() {
        //  Test getNormal for a point on surface of the cylinder
        double radius = 1;
        Ray axis = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        double height = 5;
        Cylinder cylinder = new Cylinder(radius, axis, height);

        // Top point on the surface of the cylinder 
        Point surfacePoint = new Point(0, 0, height);

        // Make sure the result is not null
        Vector normal = cylinder.getNormal(surfacePoint);
        assertNotNull(normal, "getNormal() should not return null");

        // TC02: Test getNormal for a different point on the surface
        // Add more specific tests here...

        // TC03: Test getNormal for a point on the base
        // Add more specific tests here...
    }

    // =============== Boundary Values Tests ==================

    @Test
    void testGetNormalBoundary() {
        // TC11: Test getNormal for a point at the center of the base
        // Add more boundary tests here...

        // TC12: Test getNormal for a point at the edge of the base
        // Add more boundary tests here...

        // TC13: Test getNormal for a point on the side of the cylinder
        // Add more boundary tests here...
    }
}

