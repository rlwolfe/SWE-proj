package geometries;
import primitives.Point;
import primitives.Vector;

public abstract class RadialGeometry implements Geometry{
	
	double radius;
	public RadialGeometry(double radius) {
	        this.radius = radius;
	    }
	

}
