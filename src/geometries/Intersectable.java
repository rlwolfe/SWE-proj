package geometries;
import java.util.List;

import primitives.Point;
import primitives.Ray;

/**
 * Bella & Rachel
 * Intersectable abstract class
 * no fields
 * public methods consist of: findIntersections
 */
public abstract class Intersectable {
	
	/**
	 * @param ray
	 * @return nothing because this is part of an interface 
	 */
	public List<Point> findIntersections(Ray ray) {
		var geoList = findGeoIntersections(ray);
		return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
	}
	
	public final List<GeoPoint> findGeoIntersections(Ray ray) {
		return findGeoIntersectionsHelper(ray);
	}
	
	protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);
	
	/**
	 * @param geometry and point
	 */
	public static class GeoPoint {
		public Geometry geometry;
		public Point point;
		/**
		 * @param geometry
		 * @param point
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

		    return (this.geometry == other.geometry)
		    		&& (this.point == other.point);
		}
	
		@Override
		public String toString() {
			return "geometry=" + this.geometry.toString()
					+ ", point=" + this.point.toString();
		}
	}

}
