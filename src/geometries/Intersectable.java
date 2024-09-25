package geometries;
import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * Bella & Rachel
 * Intersectable interface
 * no fields
 * public methods consist of: findIntersections
 */
public abstract class Intersectable {
	
	/**
	 * @param ray
	 * @return nothing because this is part of an interface 
	 */
	public List<Point> findIntersections(Ray ray) {
		return null;
	}
	
	public final List<GeoPoint> findGeoIntersections(Ray r){
		return findGeoIntersectionsHelper(r);
	}
	
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray r);
	
	/**
	 * GeoPoint static class
	 * geometry and point fields 
	 */
	public static class GeoPoint {
		public Geometry geometry;
		public Point point;
		
		/**
		 * @param geo
		 * @param pt
		 */
		public GeoPoint(Geometry geo, Point pt) {
			this.geometry = geo;
			this.point = pt;
		}
		
		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null || !(obj instanceof GeoPoint other))
				return false;
			
			return (geometry == other.geometry) && (point == other.point);
		}
		
		@Override
		public String toString() {
			return "geometry=" + geometry.toString() + ", point=" + point.toString();
		}

	}

}
