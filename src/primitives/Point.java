package primitives;

/**
 * Bella & Rachel
 * constructors accept 3 doubles or a single Double3
 * fields include: xyz (double)
 * public methods consist of: add, distance, distanceSquared, and subtract
 * Overridden methods are: toSring and equals
 */
public class Point {
	 final protected Double3 xyz;
	 
	 /**
	 * returns Point object with all zeros for x,y and z coordinates
	 */
	public static final Point ZERO = new Point(0, 0, 0);
	 
	 /**
	 * @param p1
	 * @param p2
	 * @param p3
	 * constructor that receives 3 coordinates and assigns them to the object 
	 */
	public Point(double p1, double p2, double p3) {
		 xyz = new Double3(p1, p2, p3);
	 }
	
	 /**
	 * @param dot
	 * constructor that receives coordinates in the form of a Double3 and assigns it to the object 
	 */
	protected Point(Double3 dot) {
		super();
		 xyz = dot;		 
	 }

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    
	    if (obj == null || !(obj instanceof Point other))
	        return false;

	    return this.xyz.equals(other.xyz);
	}

	/**
	 * 
	 * @return xyz point's coordinates in Double3 format 
	 */
	public Double3 getXyz() {
		return xyz;
	}

	@Override 
	public String toString() {
		return xyz.toString();
	}
	
	/**
	 * @param vec
	 * @return point with the coordinates from the given parameters
	 */
	public Point add(Vector vec) {
		return new Point(xyz.add(vec.xyz));
	} 

	/**
	 * @param p
	 * @return calculated distance between the object that called the function and the parameter
	 */
	public double distance(Point p) {
		return Math.sqrt(distanceSquared(p));
	}
	
	/**
	 * @param p
	 * @return calculated distance squared between the object that called the function and the parameter
	 */
	public double distanceSquared(Point p) {
		return (((p.xyz.d1-this.xyz.d1)*(p.xyz.d1-this.xyz.d1))
				+ ((p.xyz.d2-this.xyz.d2)*(p.xyz.d2-this.xyz.d2))
				+ ((p.xyz.d3-this.xyz.d3)*(p.xyz.d3-this.xyz.d3)));
	}
	
	/**
	 * @param p
	 * @return vector subtracted by the coordinates from the given point
	 */
	public Vector subtract(Point p) {
		return new Vector(xyz.subtract(p.xyz));
	}
	
}
