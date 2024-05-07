package renderer;
import primitives.Point;
import primitives.Vector;
import static primitives.Util.isZero;

import java.util.MissingResourceException;

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

	//public void setVright(Vector vright) {
	//	this.vright = vright;
	//}

/**
 * 
 * 
 * 
 */
	private Camera() {
		location=new Point(0,0,0);
		vto= new Vector (0,0,0);
		vup =  new Vector(0,0,0);
		vright = new Vector(0,0,0);
		
		
		
		} // private default contructor
	
/**
 * 	
 * @param nX
 * @param nY
 * @param j
 * @param i
 * @return
 */
		public Ray constructRay(int nX, int nY, int j, int i) {

			 // Implementation of constructing the ray
	        // Calculate the center point of the pixel
	        double xJ = (j - (nX-1) / 2.0) * (width / nX);
	        double yI = -(i-((nY-1)/2.0)) * (height / nY);
	        
	        
	     // Calculate Pc
	        Point pc = location.add(vto.scale(distance));
	        
	        
	        // Calculate the point Pi,j
	        Point pIJ = pc.add(vright.scale(xJ)).add(vup.scale(yI));
	        
	        // Calculate the ray direction from camera location to Pi,j
	        Vector rayDirection = pIJ.subtract(location);
	        
	        // Return the constructed ray
	        return new Ray(location, rayDirection);
			
		}
		
		public static class Builder{
			private final Camera camera=new Camera();  // created here in the definiton 
			public Builder setLocation(Point p) { // setting camera location though this method 
				this.camera.location=p;
				return this;
			}
			public Builder setDirection(Vector vu, Vector vt)
			{
				if (!isZero(vu.dotProduct(vt))) { // this will check that it is vertical
	                throw new IllegalArgumentException("the vectors are not vertical");
	            }
				this.camera.vup=vu;
				this.camera.vto=vt;
				this.camera.vright=vu.crossProduct(vt);
				
				
				this.camera.vup.normalize(); // will normalize it 
				this.camera.vto.normalize();
				this.camera.vright.normalize();
				return this;
				
			}
			public Builder setVpSize (double h, double w) { // settingup the size of view plane 
				this.camera.height=h;
				this.camera.width=w;
				return this;
				
			}
			public Builder setVpDistance( double disvp) //we subrtact one from the other to get distance
			{
				this.camera.distance=disvp;
				return this;
			}
			public Camera build() //throws CloneNotSupportedException
			{
//				The method will check for all the relevant camera fields that have a non-zero value, according to
//				the type of each field (that is, we did not forget to prepare the value)
				// creating a check for each one of camera fields 
				// location
                
				if (camera.location == null) {
	                throw new MissingResourceException("Rendering data is missing",
	                        "Camera", "Location data is missing");
				}
				if (camera.vto == null) {
	                throw new MissingResourceException("Rendering data is missing",
	                        "Camera", "vector To data is missing");
	                }
				if (camera.vup == null) {
	                throw new MissingResourceException("Rendering data is missing",
	                        "Camera", "Vector up data is missing");
				}
				
				
				if (camera.width <= 0 || camera.height <= 0 || camera.distance <= 0) {
	                throw new MissingResourceException("Rendering data is missing",
	                        "Camera", "Width, Height, or Distance value is missing or incorrect");
	            }
				camera.vright=camera.vto.crossProduct(camera.vup); // will implement the vright with vupXvto
                camera.vright.normalize();
				
                return (Camera) camera;//.clone();
			
			}
		}
	
	

}
	


