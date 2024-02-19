package unittests;

import static org.junit.jupiter.api.Assertions.*;
import primitives.Point;
import primitives.Vector;
import primitives.Ray;

import org.junit.jupiter.api.Test;

class RayTest {

	@Test
	void testGetPoint() {
		Point p1= new Point(1,2,3);
		Vector vec= new Vector(2,4,6);
		Ray r1= new Ray (p1, vec);
		double tpos=1;
		double tneg=-1;
		double tzero=0;
		Point testpositive=new Point(3,6,9);
		Point testneg= new Point(-1, -2, -3);
		
		
		
	
		assertEquals(testpositive, r1.getPoint(tpos), "Error, positive point calculation from Ray");
		assertEquals(testneg, r1.getPoint(tneg), "Error, negative point calculation from Ray");
		assertEquals(p1, r1.getPoint(tzero), "Error, doesnt return head when t is zero");

		
		
		
		
	}

}
