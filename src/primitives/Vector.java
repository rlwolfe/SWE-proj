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
		//TODO
	}
	
	public double length() {
		
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
		
	}
	
	public Vector crossProduct(Vector vec) {
		
	}
	
	public Vector normalize() {
		
	}
}
