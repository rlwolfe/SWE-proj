/**
 * 
 */
package unittests;

import static org.junit.jupiter.api.Assertions.*;
import static primitives.Util.isZero;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Vector;

/**
 * 
 */
class VectorTests {

	Point  p1         = new Point(1, 2, 3);
    Point  p2         = new Point(2, 4, 6);
    Point  p3         = new Point(2, 4, 5);

    Vector v1         = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v3         = new Vector(0, 3, -2);
    Vector v4         = new Vector(1, 2, 2);
    
    // Test add & subtract
   
   
    
	/**
	 * Test method for {@link primitives.Vector#add(primitives.Vector)}.
	 */
	@Test
	void testAddVector() {
		//check that vector throws error when adding negative of itself
		 assertThrows(IllegalArgumentException.class, ()-> v1.add(v1Opposite), "ERROR: Vector+ -itself does not throw an exception");
		 
		 //ASK JOYCE HOW TO CHECK CORRECT ERROR THROWN
		 /*
	} catch (IllegalArgumentException ignore) {} catch (Exception ignore) {
		       out.println("ERROR: Vector + itself throws wrong exception");
		    }	}
		    	 */
		 //testing that vector addition works
		 assertEquals(v1.add(v2), v1Opposite, "ERROR: Vector + Vector does not work correctly");

		 
	}
		 @Test
		 void testSubtractVector() {
			 
		//check that vector throws error when subtracting itself by itself
		 assertThrows(IllegalArgumentException.class, ()-> v1.subtract(v1), "ERROR: Vector+ -itself does not throw an exception");
/*
 * 			AGAIN ASK JOYCE
		    } catch (IllegalArgumentException ignore) {} catch (Exception ignore) {
		       out.println("ERROR: Vector + itself throws wrong exception");
		    }
		    */
	
		    //testing vector subtraction works
		    assertEquals(v1.subtract(v2), new Vector(3, 6, 9),"ERROR: Vector + Vector does not work correctly" );
	}
	
	/**
	 * Test method for {@link primitives.Vector#scale(double)}.
	 */
	@Test
	void testScale() {
		Vector v = new Vector(2,4,6);
		
		// ============ Equivalence Partitions Tests ==============
		
		//regular did the scaling work
		assertEquals(v1.scale(2),v, "ERROR: Vector scale does not work properly");
				
		//negative vector positive scalar
		assertEquals(v1Opposite.scale(2), v2, "ERROR: Vector scalar does not work with a negative vector positive scalar");
				
		//negative vector negative scalar
		assertEquals(v1Opposite.scale(-2),v, "ERROR: Vector scalar does not work with a negative vector and negative scalar");
								
		
		
		// =============== Boundary Values Tests ==================

		//scalar is zero 
	      assertThrows(IllegalArgumentException.class, ()-> v1.scale(0), "ERROR: vector doesn't throw exception when given 0 scalar");

	    //negative scalar positive vector
			assertEquals(v1.scale(-2), v2, "ERROR: Vector scale does not work properly with a negative number");
			
		//non whole number
		assertEquals(v.scale(.5), v1, "ERROR: Vector scale does not work properly with a non whole number");
			
		//negative vector with Zero scalar
		assertThrows(IllegalArgumentException.class, ()-> v1Opposite.scale(0), "ERROR: vector with negative vector doesn't throw exception when given 0 scalar");
	}
				
				
				
		

	/**
	 * Test method for {@link primitives.Vector#dotProduct(primitives.Vector)}.
	 */
	@Test
	void testDotProduct() {

	      // test Dot-Product
	      assertEquals(v1.dotProduct(v3), 0,"ERROR: dotProduct() for orthogonal vectors is not zero" );
	      
	      assertEquals(v1.dotProduct(v2) + 28, 0, "ERROR: dotProduct() wrong value");
	}
	
	/**
	 * Test method for {@link primitives.Vector#crossProduct(primitives.Vector)}.
	 */
	@Test
	void testCrossProduct() {
	      
		// ============ Equivalence Partitions Tests ==============
		 Vector vr = v1.crossProduct(v3);
		 // TC01: Test that length of cross-product is proper (orthogonal vectors taken
		 // for simplicity)
		 assertEquals(v1.length() * v3.length(), vr.length(), 0.00001, "crossProduct() wrong result length");
		 // TC02: Test cross-product result orthogonality to its operands
		 assertTrue(isZero(vr.dotProduct(v1)), "crossProduct() result is not orthogonal to 1st operand");
		 assertTrue(isZero(vr.dotProduct(v3)), "crossProduct() result is not orthogonal to 2nd operand");
		 // =============== Boundary Values Tests ==================
		 // TC11: test zero vector from cross-productof co-lined vectors
		 assertThrows(IllegalArgumentException.class, () -> v1.crossProduct(v2), 
		"crossProduct() for parallel vectors does not throw an exception");
		 }
	
	

	/**
	 * Test method for {@link primitives.Vector#lengthSquared()}.
	 */
	@Test
	void testLengthSquared() {
		// test length
	      assertEquals(v4.lengthSquared() - 9, 0,"ERROR: lengthSquared() wrong value");

	
	}

	/**
	 * Test method for {@link primitives.Vector#length()}.
	 */
	@Test
	void testLength() {
		 // test length
	      assertEquals(v4.length() - 3, 0,"ERROR: length() wrong value" );
	         	}

	/**
	 * Test method for {@link primitives.Vector#normalize()}.
	 */
	@Test
	void testNormalize() {
		// test vector normalization vs vector length and cross-product
	      Vector u = v1.normalize();
	      assertEquals(u.length() - 1, 0,"ERROR: the normalized vector is not a unit vector");
	      
	      // test that the vectors are parallel 
	      assertThrows(IllegalArgumentException.class, ()-> v1.crossProduct(u), "ERROR: the normalized vector is not parallel to the original one");
	      
	      assertTrue( v1.dotProduct(u) >= 0, "ERROR: the normalized vector is opposite to the original one");
	}

}