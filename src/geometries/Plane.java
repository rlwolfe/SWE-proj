package geometries;
import primitives.Point;
import primitives.Vector;

public class Plane implements Geometry {
    Point point;
    Vector normal;

    public Plane(Point p1, Point p2, Point p3){
    	this.point = p1;
    	Vector side1 = p2.subtract(p1);
        Vector side2 = p3.subtract(p1);
        this.normal = side1.crossProduct(side2);
     // Note: At this stage, save a null value in the normal field, as the full implementation
        // of normal calculation will be done in the next stage.
        // For now, you are just approximating the normal vector based on the cross product.
    
    }
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
        
    }
    
 
    Vector getNormal() 
    {
    	return normal;
    }
    
    public // this should override the Geometry operation (DOESNT WORK IN ECLIPSE)
    Vector getNormal(Point point) {
    	return normal;
    }
}
   
