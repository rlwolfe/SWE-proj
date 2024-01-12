package primitives;

/**
 * constructors accept two doubles or a single Double3
 * currently doesn't have any of its own fields, inherits 3 doubles from Point
 * public methods consist of: length, lengthSquared, add, scale, dotProduct, crossProduct and normalize
 * Overridden methods are: toSring and equals   
 */
public class Vector extends Point{
	// double size; ???
	// direction
	 
	 /**
	 * @param p1 (double)
	 * @param p2 (double)
	 * @param p3 (double)
	 * constructor that receives 3 coordinates and assigns them to the object
	 */
	public Vector(double p1, double p2, double p3){
		 super(p1, p2, p3);
		 try{
			 if (this.equals(Point.ZERO))
				 throw new IllegalArgumentException("Vector cannot be zero");
		 }
		 catch (IllegalArgumentException e)
		 {
			 System.out.println("Illegal Argument Exception thrown, " + e);
		 }
	 }
	 
	 /**
	 * @param dot
	 * constructor that receives 3 coordinates in the form of type Double3 and assigns them to the object
	 */
	public Vector(Double3 dot) {
		 super(dot);
		 try{
			 if (this.equals(Double3.ZERO))
				 throw new IllegalArgumentException("Vector cannot be zero");
		 }
		 catch (IllegalArgumentException e)
		 {
			 System.out.println("Illegal Argument Exception thrown, " + e);
		 }
	 }
	
	@Override 
	public String toString()
	{
		 return "->" + super.toString(); 
	}
	@Override
	public boolean equals(Object obj) {
	if (this == obj) 
		return true;
	return (obj instanceof Vector other && super.equals(other));
	}
	
	/**
	 * @return of double the length of the current vector squared
	 */
	public double lengthSquared() {
		double result=0.0;
		result+=((this.xyz.d1*this.xyz.d1)+(this.xyz.d2 * this.xyz.d2)+(this.xyz.d3*this.xyz.d3));
		return result;
	}
	
	/**
	 * @return the length of the current vector
	 */
	public double length() {
		return (Math.sqrt(lengthSquared()));
	}
	
	/**
	 * @param vec
	 * @return Vector with the sum of the current plus given
	 */
	public Vector add(Vector vec) {
		double p1,p2,p3;
		p1=this.xyz.d1+vec.xyz.d1;
		p2=this.xyz.d2+vec.xyz.d2;
		p3=this.xyz.d3+vec.xyz.d3;
		return new Vector(p1,p2,p3);
	}
	
	/**
	 * @param scalar
	 * @return scalar multiple of the current vector and the given
	 */
	public Vector scale(double scalar) {
		double p1,p2,p3;		
		p1=this.xyz.d1*scalar;
		p2=this.xyz.d2*scalar;
		p3=this.xyz.d3*scalar;
		return new Vector(p1,p2,p3);
	}
	
	/**
	 * @param vec
	 * @return dot product of the current vector and the given one
	 */
	public Vector dotProduct(Vector vec) {
		double p1,p2,p3;
		p1 = this.xyz.d1 * vec.xyz.d1;
		p2 = this.xyz.d2 * vec.xyz.d2;
		p3 = this.xyz.d3 * vec.xyz.d3;
		return new Vector(p1,p2,p3);
	}
	
	/**
	 * @param vec
	 * @return the cross product of the current vector and given one
	 */
	public Vector crossProduct(Vector vec) {
		double x1, x2, x3;
		x1=((this.xyz.d2 * vec.xyz.d3) - (this.xyz.d3 *vec.xyz.d2));
		x2=((this.xyz.d3*vec.xyz.d1)- (this.xyz.d1*vec.xyz.d3));
		x3=((this.xyz.d1*vec.xyz.d2) - (this.xyz.d2 *vec.xyz.d1));
		return new Vector(x1,x2,x3);
	}
	
	/**
	 * @return the current vector, but normalized 
	 */
	public Vector normalize() {
		double normx=0.0, normy=0.0, normz=0.0;
		normx=this.xyz.d1/length();
		normy=this.xyz.d2/length();
		normz=this.xyz.d3/length();
		return new Vector(normx,normy,normz);
		
	}
}
