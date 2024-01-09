package primitives;

public class Point {
	 final protected Double3 xyz;
	 
	 public static final Point ZERO = new Point(0, 0, 0);
	 
	 public Point(double p1, double p2, double p3){
		 xyz = new Double3(p1, p2, p3);
	 }
	 protected Point(Double3 dot) {
		 xyz = dot;		 
	 }

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if(obj==null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other= (Point) obj;
		return this.xyz.equals(other.xyz);
	}
	@Override 
	public String toString() 
	{
		return xyz.toString();
	}
	public Point add(Vector vec)
	{
		double p1,p2,p3;
		
		p1=this.xyz.d1+vec.xyz.d1;
		p2=this.xyz.d2+vec.xyz.d2;
		p3=this.xyz.d3+vec.xyz.d3;
		return new Point(p1,p2,p3);
	} 

	public double distance (Point p)
	{
		return Math.sqrt(distanceSquared(p));
	}
	
	public double distanceSquared(Point p)
	{
		return (((this.xyz.d1-p.xyz.d1)*(this.xyz.d1-p.xyz.d1))
				+ ((this.xyz.d2+p.xyz.d2)*(this.xyz.d2+p.xyz.d2))
				+ ((this.xyz.d3-p.xyz.d3)*(this.xyz.d3-p.xyz.d3)));
	}
	
	public Vector subtract(Point p)
	{
		double p1,p2,p3;
		
		p1=this.xyz.d1-p.xyz.d1;
		p2=this.xyz.d2-p.xyz.d2;
		p3=this.xyz.d3-p.xyz.d3;
		return new Vector(p1,p2,p3);	
	}
	
}
