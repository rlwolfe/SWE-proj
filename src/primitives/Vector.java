package primitives;

public class Vector extends Point{
	// double size; ???
	// direction
	 
	 public Vector(double p1, double p2, double p3){
		 super(p1, p2, p3);
		 try{
			 if (this.equals(Double3.ZERO))
				 throw new IllegalArgumentException("Vector cannot be zero");
		 }
		 catch (IllegalArgumentException e)
		 {
			 System.out.println("Illegal Argument Exception thrown, " + e);
		 }
	 }
	 
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
	
	public double lengthSquared() {
		double result=0.0;
		result+=((this.xyz.d1*this.xyz.d1)+(this.xyz.d2 * this.xyz.d2)+(this.xyz.d3*this.xyz.d3));
		return result;
	}
	
	public double length() {
		return (Math.sqrt(lengthSquared()));
	}
	
	public Vector add(Vector vec) {
		double p1,p2,p3;
		
		p1=this.xyz.d1+vec.xyz.d1;
		p2=this.xyz.d2+vec.xyz.d2;
		p3=this.xyz.d3+vec.xyz.d3;
		return new Vector(p1,p2,p3);
	}
	
	public Vector scale(double scalar) {
		double p1,p2,p3;
		
		p1=this.xyz.d1*scalar;
		p2=this.xyz.d2*scalar;
		p3=this.xyz.d3*scalar;
		return new Vector(p1,p2,p3);
	}
	
	public Vector dotProduct(Vector vec) {
		double result=0.0;
		result +=this.xyz.d1 * vec.xyz.d1;
		result +=this.xyz.d2 * vec.xyz.d2;
		result +=this.xyz.d3 * vec.xyz.d3;
		return result;
	}
	
	public Vector crossProduct(Vector vec) {
		double x1, x2, x3;
		x1=((this.xyz.d2 * vec.xyz.d3) - (this.xyz.d3 *vec.xyz.d2));
		x2=((this.xyz.d3*vec.xyz.d1)- (this.xyz.d1*vec.xyz.d3));
		x3=((this.xyz.d1*vec.xyz.d2) - (this.xyz.d2 *vec.xyz.d1));
		return new Vector(x1,x2,x3);
	}
	
	public Vector normalize() {
		double normx=0.0, normy=0.0, normz=0.0;
		normx=this.xyz.d1/length();
		normy=this.xyz.d2/length();
		normz=this.xyz.d3/length();
		return new Vector(normx,normy,normz);
		
	}
}
