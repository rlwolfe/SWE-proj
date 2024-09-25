package geometries;
//Bella & Rachel
/**
 * this is an abstract class that inherits from Geometry
 * the constructor accepts a double
 * fields include: radius
 * there are no methods
 */
public abstract class RadialGeometry implements Geometry{
	//double radius;
	private final double radius;
	/**
	 * @param radius
	 * constructor that receives the radius and assigns it to the object
	 */
	public RadialGeometry(double radius) {
	        this.radius = radius;
	    }
	public final double getRadius() {
		return radius;
	}

}
