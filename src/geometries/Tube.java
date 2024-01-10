package geometries;
import primitives.Ray;
import primitives.Vector;
import primitives.Point;


public class Tube extends RadialGeometry {
	public Tube(double radius) {
		super(radius);
		
	}
	protected Ray axis;
	public Vector getNormal(Point p)
	{
		return null;
	}

}
