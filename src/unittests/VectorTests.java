/**
 * @author Rachel and Bella
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Double3;
import primitives.Vector;

/**
 * 
 */
class VectorTests {
	Vector v1        	= new Vector(1, 2, 3);
	Vector normV1	 	= v1.normalize();
    Vector v1Opposite	= new Vector(-1, -2, -3);
    Vector v2        	= new Vector(-2, -4, -6);
    Vector v3        	= new Vector(0, 3, -2);
    Vector v4        	= new Vector(1, 2, 2);
    Vector vr 			= v1.crossProduct(v3);
    
    
    /**
	 * Test constructor for {@link primitives.Vector)}.
	 */
	@Test
	void testConstructor() {
		assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0),
				"ERROR: attempted to create Zero Vector ");
		
		assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO),
				"ERROR: attempted to create Zero Vector ");
	}
    
	/**
	 * Test methods for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		assertTrue(isZero(v4.lengthSquared() - 9));
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		assertTrue(isZero(v4.length() - 3));
		
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		//equivalence tests
		assertTrue(v1.add(v2).equals(v1Opposite));
		
		//boundary tests
		assertSame(v1.add(v1Opposite), Vector.ZERO);
	}
	
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	void testSubtractVector() {
		//equivalence tests
		assertTrue(v1.subtract(v2).equals(new Vector(3, 6, 9)));
		
		//boundary tests
		assertSame(v1.subtract(v1), Vector.ZERO);
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		//equivalence tests
		assertTrue(isZero(v1.dotProduct(v3)));
		
		//boundary tests
		assertTrue(isZero(v1.dotProduct(v2) + 28));
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		//equivalence tests
		assertTrue(isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)));
		
		//boundary tests
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2),
				"ERROR: crossProduct() for parallel vectors does not throw an exception");
		
		assertTrue(isZero(vr.length() - v1.length() * v3.length()));
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		//equivalence tests
		assertNotNull(v1.crossProduct(normV1),
				"ERROR: the normalized vector is not parallel to the original one");

		//boundary tests		
		assertTrue(isZero(normV1.length() - 1));
		assertFalse(v1.dotProduct(normV1) < 0);
	}
	
}
