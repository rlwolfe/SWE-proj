/**
 * @author Rachel and Bella
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Double3;
import primitives.Vector;

class VectorTests {
	//vectors needed for tests
	Vector v1        	= new Vector(1, 2, 3);
	Vector normV1	 	= v1.normalize();
    Vector v1Opposite	= new Vector(-1, -2, -3);
    Vector v2        	= new Vector(-2, -4, -6);
    Vector v3        	= new Vector(0, 3, -2);
    Vector v4        	= new Vector(1, 2, 2);
    Vector zeroVec		= new Vector(0, 0, 0);
    Vector maxVector = new Vector(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
	Vector minVector = new Vector(Double.MIN_VALUE, Double.MIN_VALUE, Double.MIN_VALUE);
	
	//calculations needed for tests
	double testMaxLength = (Double.MAX_VALUE*Double.MAX_VALUE)+(Double.MAX_VALUE*Double.MAX_VALUE)+(Double.MAX_VALUE*Double.MAX_VALUE);
	double testMinLength = (Double.MIN_VALUE*Double.MIN_VALUE)+(Double.MIN_VALUE*Double.MIN_VALUE)+(Double.MIN_VALUE*Double.MIN_VALUE);
	Vector testMaxAdd = new Vector(Double.MAX_VALUE+Double.MAX_VALUE, Double.MAX_VALUE+Double.MAX_VALUE, Double.MAX_VALUE+Double.MAX_VALUE);
	Vector testMinSub = new Vector(Double.MIN_VALUE+Double.MIN_VALUE, Double.MIN_VALUE+Double.MIN_VALUE, Double.MIN_VALUE+Double.MIN_VALUE);

    
    /**
	 * Test constructor for {@link primitives.Vector)}.
	 */
	@Test
	void testConstructor() {
		//boundary tests
		assertThrows(IllegalArgumentException.class, () -> new Vector(0, 0, 0), "ERROR: attempted to create Zero Vector ");
		
		assertThrows(IllegalArgumentException.class, () -> new Vector(Double3.ZERO), "ERROR: attempted to create Zero Vector ");
	}
    
	/**
	 * Test methods for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		//equivalence test
		assertTrue(isZero(v4.lengthSquared() - 9), "ERROR: regular squared length");
		
		//boundary tests
		//square length of a zero vector
		assertTrue(isZero(zeroVec.lengthSquared()), "ERROR: zero vector squared length");
		
		//squared length of the max vector
		assertEquals(testMaxLength, maxVector.lengthSquared(), "ERROR: max length squared");
		
		//squared length of the min vector
		assertEquals(testMinLength, minVector.lengthSquared(), "ERROR: min length squared");
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		//equivalence test
		assertTrue(isZero(v4.length() - 3), "ERROR: regular length is incorrect");
	
		//boundary tests
		//length of a zero vector
		assertTrue(isZero(zeroVec.length()), "ERROR: zero vector's length");

		//length of the max vector
		assertEquals(Math.sqrt(testMaxLength), maxVector.length(), "ERROR: max length");

		//length of the min vector
		assertEquals(Math.sqrt(testMinLength), minVector.length(), "ERROR: min length");
	}

	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		//equivalence tests
		assertTrue(v1.add(v2).equals(v1Opposite), "ERROR: regular addition on vector");
		
		//boundary tests
		//adding opposite vectors
		assertSame(v1.add(v1Opposite), Vector.ZERO, "ERROR: adding opposite vectors");
		
		//addition of the max vector
		assertEquals(testMaxAdd, maxVector.add(maxVector), "ERROR: max length addition");
	}
	
	/**
	 * Test method for {@link primitives.Vector#subtract(primitives.Vector)}.
	 */
	@Test
	void testSubtractVector() {
		//equivalence test
		assertTrue(v1.subtract(v2).equals(new Vector(3, 6, 9)), "ERROR: regular subtraction on vector");
		
		//boundary test
		//subtracting the zero vector
		assertSame(v1.subtract(v1), Vector.ZERO, "ERROR: subtracting zero vector");
		
		//subtracting of the min vector
		assertEquals(testMinSub, minVector.add(minVector), "ERROR: min length subtraction");
	}

	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		//equivalence test
		assertEquals(new Vector(2, 4, 6), v1.scale(2), "ERROR: regular scalar multiplication");

		//boundary tests
		//scalar multiplication of a zero vector
		assertEquals(zeroVec, zeroVec.scale(2), "ERROR: zero vector scalar");

		//scalar multiple of the max vector
		assertEquals(new Vector(Double.MAX_VALUE * 3, Double.MAX_VALUE * 3, Double.MAX_VALUE * 3), maxVector.scale(3), "ERROR: max scalar mult");

		//scalar multiple of the min vector
		assertEquals(new Vector(Double.MIN_VALUE * 3, Double.MIN_VALUE * 3, Double.MIN_VALUE * 3), minVector.scale(3), "ERROR: min scalar mult");
	}

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {
		//equivalence tests
		assertTrue(isZero(v1.dotProduct(v3)), "ERROR: regular dot product is incorrect");
		
		//boundary tests
		//dot product of positive vector by negative vector
		assertEquals(-28.0 , v1.dotProduct(v2), "ERROR: dot product of pos and neg is incorrect");
		
		//dot product of a zero vector
		assertTrue(isZero(zeroVec.dotProduct(v1)), "ERROR: zero vector dot product");

		//dot prod of the max vector
		assertEquals(testMaxLength, maxVector.dotProduct(maxVector), "ERROR: max dot product");

		//dot prod of the min vector
		assertEquals(testMinLength, minVector.dotProduct(minVector), "ERROR: min dot product");
	}

	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
	    Vector vr = v1.crossProduct(v3);
	    
		//equivalence test
		assertTrue(isZero(vr.dotProduct(v1)) || !isZero(vr.dotProduct(v3)));
		
		//boundary tests
		assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), "ERROR: crossProduct() for parallel vectors does not throw an exception");
		
		assertTrue(isZero(vr.length() - v1.length() * v3.length()), "ERROR: calculating cross product");
		
		assertEquals(zeroVec, zeroVec.crossProduct(v1), "ERROR: zero vector cross product");
	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		//equivalence tests
		assertNotNull(v1.crossProduct(normV1), "ERROR: the normalized vector is not parallel to the original one");

		//boundary tests		
		assertTrue(isZero(normV1.length() - 1), "ERROR: normalized vector's length is not equal to 1");
		assertFalse(v1.dotProduct(normV1) < 0, "ERROR: dot prod of normalized vector is neg");
	}
	
}
