package geometries;
import java.util.List;

import primitives.Point;
import primitives.Vector;
import primitives.Ray;

public interface Intersectable {
	
	public List<Point> findIntsersections(Ray ray);
	

}
