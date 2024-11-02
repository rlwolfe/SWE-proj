package renderer;

import java.util.List;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.Color;
import primitives.Double3;
import primitives.Material;
import primitives.Ray;
import primitives.Util;
import primitives.Vector;
import scene.Scene;

/**
 * Bella & Rachel
 * inherits and implements from RayTracerBase
 */
public class SimpleRayTracer extends RayTracerBase {

	/**
	 * @param s
	 */
	public SimpleRayTracer(Scene s) { super(s); }
	/**
	 * amount to move ray's head (shadow rays)
	 */
	private static final int MAX_CALC_COLOR_LEVEL = 10;
	private static final double MIN_CALC_COLOR_K = 0.001;
	private static final Double3 INITIAL_K = new Double3(1.0);

	@Override
	public Color traceRay(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		return intersections == null ? scene.background : calcColor(findClosestIntersection(ray), ray);
	}

	/**
	 * @param gp
	 * @param ls
	 * @param l
	 * @param n
	 * @return detect interaction from objects (intersecting or light bouncing off ) 
	 */
	private Double3 transparency(GeoPoint gp, LightSource ls, Vector l, Vector n) {
		Vector lightDirection = l.scale(-1);
		Ray lightRay = new Ray(gp.point, lightDirection, n);

		List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay);

		Double3 ktr = Double3.ONE;
		if (intersections == null) return ktr;

		double rayIntersectionDistance, rayLightDistance = ls.getDistance(lightRay.getHead());
		
		for (GeoPoint geopoint : intersections) {
			rayIntersectionDistance = lightRay.getHead().distance(geopoint.point);
			if (rayIntersectionDistance < rayLightDistance)
				ktr = geopoint.geometry.getMaterial().kT.product(ktr);
		}
		return ktr;
	}
	
	/**
	 * @param gp
	 * @param ray
	 * @return recursively runs to get all light in the scene
	 */
	private Color calcColor(GeoPoint gp, Ray ray) {
		return calcColor(gp, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
	}

	/**
	 * @param point
	 * @return the color calculated using both 'sunlight' and other lights in scene
	 */
	private Color calcColor(GeoPoint gp, Ray ray, int level, Double3 k) {
		Color color = gp.geometry.getEmission().add(calcLocalEffects(gp, ray, k));
		return 1 == level ? color : color.add(calcGlobalEffects(gp , ray, level, k));
	}
	
	/**
	 * @param gp
	 * @param ray
	 * @param level
	 * @param k
	 * @return calculate total light from all factors
	 */
	private Color calcGlobalEffects(GeoPoint gp, Ray ray, int level, Double3 k) {
		Color color = Color.BLACK;
		Material material = gp.geometry.getMaterial();
		Double3 kr = material.kR;
		Double3 kkr = k.product(kr);
		Double3 kt = material.kT;
		Double3 kkt = k.product(kt);

		if(!kkr.lowerThan(MIN_CALC_COLOR_K)){
			Ray reflectedRay = constructReflectedRay(gp, ray.getDirection());
			color = color.add(calcGlobalEffects(reflectedRay, kr, level, kkr));
		}

		if(!kkt.lowerThan(MIN_CALC_COLOR_K)) {
			Ray refractedRay = constructRefractedRay(gp, ray);
			color = color.add(calcGlobalEffects(refractedRay, kt, level, kkt));
		}
		return color;
	}

	/**
	 * @param gp
	 * @param vector
	 * @return light bounced of shiny object
	 */
	private Ray constructReflectedRay(GeoPoint gp, Vector vector) {
		Vector norm = gp.geometry.getNormal(gp.point).normalize();
		Vector vtr = vector.subtract(norm.scale(2 * (vector.dotProduct(norm))));
		return new Ray(gp.point, vtr, norm);
	}

	/**
	 * @param gp
	 * @param ray
	 * @return ray of light once passing through translucent object
	 */
	private Ray constructRefractedRay(GeoPoint gp, Ray ray) {
        return new Ray(gp.point, ray.getDirection(), gp.geometry.getNormal(gp.point));
	}

	/**
	 * @param ray
	 * @param kx
	 * @param level
	 * @param kkx
	 * @return background or color of object based on surrounding objects and total light
	 */
	private Color calcGlobalEffects(Ray ray, Double3 kx, int level, Double3 kkx) {
		GeoPoint gp = findClosestIntersection(ray);
		return gp == null ? scene.background : calcColor(gp, ray, level - 1, kkx).scale(kx);
	}

	/**
	 * @param ray
	 * @return closest point to light
	 */
	private GeoPoint findClosestIntersection(Ray ray) {
		var intersections = scene.geometries.findGeoIntersections(ray);
		if (intersections == null)
			return null;
		return ray.findClosestGeoPoint(intersections);
	}

	/**
	 * @param gp
	 * @param ray
	 * @param k
	 * @return color from close light and objects
	 */
	private Color calcLocalEffects(GeoPoint gp, Ray ray, Double3 k) {
		Vector v = ray.getDirection();
		Vector l, n = gp.geometry.getNormal(gp.point);
		double nl, nv = Util.alignZero(n.dotProduct(v));
		Material material= gp.geometry.getMaterial();
		Color color = Color.BLACK; 
		Double3 ktr;
		
		for (LightSource lightSource: scene.lights) {
			l = lightSource.getL(gp.point);
			nl= Util.alignZero(n.dotProduct(l));
			
			if (nl * nv > 0) { // sign(nl) == sign(nv)
				ktr = transparency(gp, lightSource, l, n);
				if (!(ktr.product(k).lowerThan(MIN_CALC_COLOR_K))) {
					Color lI = lightSource.getIntensity(gp.point).scale(ktr);
					color = color.add(calcDiffusive(material, nl, lI), calcSpecular(material, n, l, v, lI));
				}
			}
		}
		return color;
	}
	
	/**
	 * @param material
	 * @param nl
	 * @param lI
	 * @return light intensity once it was dimmed from another object
	 */
	private Color calcDiffusive(Material material, double nl, Color lI) {
		nl = Math.abs(nl);
		return lI.scale(material.kD.scale(nl));
	}

	/**
	 * @param material
	 * @param n
	 * @param l
	 * @param v
	 * @param lI
	 * @return the light intensity of the object once it's bounced of another object 
	 */
	private Color calcSpecular(Material material, Vector n, Vector l, Vector v, Color lI) {
		Vector vtr = l.subtract(n.scale(2 * (l.dotProduct(n))));
		double spec = Math.max(0, v.scale(-1).dotProduct(vtr));
		double pow = Math.pow(spec, material.nShininess);
		return lI.scale(material.kS.scale(pow));
	}

}
