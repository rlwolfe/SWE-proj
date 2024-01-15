/**
 * @author Rachel and Bella
 */
package unittests;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Vector;

/**
 * 
 */
class VectorTests {
	Vector v0		  = new Vector(0, 0, 0);
	Vector v1         = new Vector(1, 2, 3);
	Vector normV1	  = v1.normalize();
    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v3         = new Vector(0, 3, -2);
    Vector v4         = new Vector(1, 2, 2);
    
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
		//equivalence tests
		assertTrue(isZero(v4.length() - 3));
		
		//boundary tests
		
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
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
		fail("Not yet implemented");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		fail("Not yet implemented");
	}
	
}
