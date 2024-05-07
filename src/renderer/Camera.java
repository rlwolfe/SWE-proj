package renderer;
import primitives.Color;
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
	private ImageWriter imageWriter;
	private RayTracerBase rayTracer;

	/**
	 * getters
	 */
	public static Builder getBuilder() {
		return new Builder();
	}

	public Point getLocation() {
		return location;
	}

	public Vector getVto() {
		return vto;
	}

	public Vector getVup() {
		return vup;
	}

	public Vector getVright() {
		return vright;
	}

	/**
	 * setters
	 */
	public void setLocation(Point location) {
		this.location = location;
	}

	public void setVto(Vector vto) {
		this.vto = vto;
	}

	public void setVup(Vector vup) {
		this.vup = vup;
	}

	public void setVright(Vector vright) {
		this.vright = vright;
	}

	private Camera() {
		location = null; //new Point(0,0,0);
		vto = null; //new Vector (0,0,0);
		vup = null; //new Vector(0,0,0);
		vright = null; //new Vector(0,0,0);

	} // private default constructor

	/**
	 * runs through the pixels and prints them in order to render the image 
	 *
public void renderImage() {
for(int i = 0; i < imageWriter.getNy(); ++i) {
for(int j = 0; j < imageWriter.getNx(); ++j) {
castRay( imageWriter.getNx(), imageWriter.getNy(), j, i);
}
}
}*/

	/**
	 * create a ray through the center, calculate the color and color in that pixel
	 * @param nx
	 * @param ny
	 * @param j
	 * @param i
	 */
	private void castRay(int nx, int ny, int j, int i) {
		Ray ray = constructRay(nx, ny, j, i);
		Color color = rayTracer.traceRay(ray);
		imageWriter.writePixel(j, i, color);
	}

	/**
	 * creates ray from the camera to the pixel on view plane
	 * @param nX
	 * @param nY
	 * @param j
	 * @param i
	 * @return the ray from the camera to pixel
	 */
	public Ray constructRay(int nX, int nY, int j, int i) {
		Point camPoint = location.add(vto.scale(distance)); //center of view plane

		//calculating the size (height and width) of each pixel
		double ry = height/nY;
		double rx = width/nX;

		//calculating the distance to move on view plane
		double yi = -(i -(nY-1)/2) * ry;
		double xj = (j - (nX-1)/2) * rx;

		if (!isZero(xj)) { // we need to make sure xj != 0 
			camPoint = camPoint.add(vright.scale(xj)); // if it is set as center
		}

		if(!isZero(yi)) { // same check for yi
			camPoint = camPoint.add(vup.scale(yi));
		}
		return new Ray(location, camPoint.subtract(location));

	}

	/**
	 * builder class to help camera class
	 */
	public static class Builder{
		private final Camera camera = new Camera();  // created here in the definition 

		/**
		 * setters
		 */
		public Builder setLocation(Point p) { // setting camera location though this method 
			this.camera.location=p;
			return this;
		}

		public Builder setDirection(Vector vu, Vector vt) {
			if (!isZero(vu.dotProduct(vt))) { // this will check that it is vertical
				throw new IllegalArgumentException("the vectors are not vertical");
			}
			this.camera.vup=vu.normalize(); // will normalize it then set it
			this.camera.vto=vt.normalize();
			this.camera.vright=vu.crossProduct(vt).normalize();
			return this;

		}

		public Builder setVpSize (double h, double w) { // setting up the size of view plane 
			this.camera.height=h;
			this.camera.width=w;
			return this;

		}

		public Builder setVpDistance( double disvp) { //we subtract one from the other to get distance
			this.camera.distance=disvp;
			return this;
		}

		public Builder setImageWriter(ImageWriter iw) {
			this.camera.imageWriter = iw;
			return this;
		}

		public Builder setRayTracer(RayTracerBase rtb) {
			this.camera.rayTracer = rtb;
			return this;
		}

		/**
		 * runs through the pixels and prints them in order to render the image
		 */
		public Builder renderImage() {
			for(int i = 0; i < camera.width; ++i) {
				for(int j = 0; j < camera.height; ++j) {
					camera.castRay( (int)camera.width, (int)camera.height, j, i);
				}
			}
			writeToImage();
			return this;
		}

		/**
		 * prints grid on the background of the image
		 * @param interval
		 * @param color
		 */
		public Builder printGrid(int count, Color color) {
			for(int i = 0; i < camera.imageWriter.getNy() ; ++i) {
				for(int j = 0; j < camera.imageWriter.getNx(); ++j) {
					if(j % count == 0 || i % count == 0) {
						camera.imageWriter.writePixel(j, i, color);
					}
				}
			}
			writeToImage();
			return this;
		}

		/**
		 * calls write to image in imageWriter
		 */
		public Builder writeToImage() {
			camera.imageWriter.writeToImage();
			return this;
		}

		/**
		 * This will check for all the relevant camera fields that have a non-zero value, according to
		 * the type of each field (that is, we did not forget to prepare the value)
		 * creating a check for each one of camera fields
		 * @returns a camera
		 */
		public Camera build() //throws CloneNotSupportedException
		{                
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
			if (camera.vright == null) {
				throw new MissingResourceException("Rendering data is missing",
						"Camera", "Vector right data is missing");
			}

			if (camera.width <= 0 || camera.height <= 0 || camera.distance <= 0) {
				throw new MissingResourceException("Rendering data is missing",
						"Camera", "Width, Height, or Distance value is missing or incorrect");
			}
			camera.vright=camera.vto.crossProduct(camera.vup); // will implement the vright with vupXvto
			camera.vright.normalize();

			if (camera.imageWriter == null) {
				throw new MissingResourceException("imageWriter is missing",
						"Camera", "imageWriter data is missing");
			}

			if (camera.rayTracer == null) {
				throw new MissingResourceException("rayTracer is missing",
						"Camera", "rayTracer data is missing");
			}

			return (Camera) camera;//.clone();

		}
	}
}
