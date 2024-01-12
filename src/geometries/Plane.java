package geometries;
import primitives.Point;
import primitives.Vector;

/**
 * this class inherits from Geometry
 * the constructor accepts 3 Points
 * fields include: point and normal
 * public methods consist of: getNormal with Point parameters and without
 * no overridden methods
 */
public class Plane implements Geometry {
    Point point;
    Vector normal;

    /**
     * @param p1 (Point)
     * @param p2 (Point)
     * @param p3 (Point)
     * constructor that receives 3 coordinates, calculates and assigns them to the object
     */
    public Plane(Point p1, Point p2, Point p3){
    	this.point = p1;
    	Vector side1 = p2.subtract(p1);
        Vector side2 = p3.subtract(p1);
        this.normal = side1.crossProduct(side2);
     // Note: At this stage, save a null value in the normal field, as the full implementation
        // of normal calculation will be done in the next stage.
        // For now, you are just approximating the normal vector based on the cross product.
    
    }
    
    /**
     * @param point
     * @param normal
     * constructor that receives a Point and a Vector and assigns them to the object
     */
    public Plane(Point point, Vector normal) {
        this.point = point;
        this.normal = normal.normalize();
        
    }
    
    /**
     * @return current normal of the object
     */
    public Vector getNormal() 
    {
    	return normal;
    }
    
    /**
     * @return current normal of the object with given point
     */
    public Vector getNormal(Point point) {
    	return normal;
    }
}
   
