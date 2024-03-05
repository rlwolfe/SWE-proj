package renderer;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;
import primitives.Ray;


public class Camera implements Cloneable {
	private Point location;
	private Vector vto;
	private Vector vup;
	private Vector vright;
	double width;
	double height;
	double distance;
	
	public static Builder getBuilder() {
		return new Builder();
	}
	public Point getLocation() {
		return location;
	}

	public void setLocation(Point location) {
		this.location = location;
	}

	public Vector getVto() {
		return vto;
	}

	public void setVto(Vector vto) {
		this.vto = vto;
	}

	public Vector getVup() {
		return vup;
	}

	public void setVup(Vector vup) {
		this.vup = vup;
	}

	public Vector getVright() {
		return vright;
	}

	public void setVright(Vector vright) {
		this.vright = vright;
	}

	private Camera() {
		location=new Point(0,0,0);
		vto= new Vector (0,0,0);
		vup =  new Vector(0,0,0);
		vright = new Vector(0,0,0);
		
		
		
		} // private default contructor
	
		public Ray constructRay(int nX, int nY, int j, int i) {
			return null;
			
		}
		
		public static class Builder{
			private final Camera camera=new Camera();
			Builder setLocation(Point p) {
				this.camera.location=p;
				return this;
			}
			Builder setDirection(Vector vu, Vector vt)
			{
				if (!isZero(vu.dotProduct(vt))) {
	                throw new IllegalArgumentException("the vectors are not vertical");
	            }
				this.camera.vup=vu;
				this.camera.vto=vt;
				
				
				this.camera.vup.normalize();
				this.camera.vto.normalize();
				return this;
				
			}
			Builder setVpSize (double h, double w) {
				this.camera.height=h;
				this.camera.width=w;
				return this;
				
			}
			Builder setVpDistance(double disc, double disvp) //we subrtact one from the other
			{
				this.camera.distance=disvp-disc;
				return this;
			}
			public Camera  build()
			{
//				The method will check for all the relevant camera fields that have a non-zero value, according to
//				the type of each field (that is, we did not forget to prepare the value)
				
				
			}
			
			
		}
	
	
}
	


