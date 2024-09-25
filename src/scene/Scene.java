package scene;

import java.util.LinkedList;
import java.util.List;

import geometries.Geometries;
import geometries.Intersectable;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

/**
 * Bella & Rachel
 * constructor accepts a string and sets it as the scene name 
 * fields are the scene name, background color, ambient light and geometries
 * public methods are the setters for the background color, the ambient light, and the geometries
 * no Overridden methods
 */
public class Scene {
	public final String name;
	public Color background;// = Color.BLACK;
	public AmbientLight ambientLight;// = AmbientLight.NONE;
	public Geometries geometries = new Geometries();
	public List<Intersectable> intersectables;// = new LinkedList<Intersectable>();
	public List<LightSource> lights;// = new LinkedList<LightSource>();
	
	
	public Scene(String sceneName) {
		name = sceneName;
		background = Color.BLACK;
		ambientLight = AmbientLight.NONE;
		geometries = new Geometries();
		intersectables = new LinkedList<Intersectable>();
		lights = new LinkedList<LightSource>();
	}
	
	/**
	 * @param backgroundColorToSet
	 * @return
	 */
	public Scene setBackground(Color backgroundColorToSet) {
		background = backgroundColorToSet;
		return this; 
	}
	
	/**
	 * @param ambientLightToSet
	 * @return
	 */
	public Scene setAmbientLight(AmbientLight ambientLightToSet) {
		ambientLight = ambientLightToSet;
		return this; 
	}
	
	/**
	 * @param intersectablesToSet
	 * @return
	 */
	public Scene setIntersectables(List<Intersectable> intersectablesToSet) {
		intersectables = intersectablesToSet;
		return this;	
	}
	
	/**
	 * @param geometriesToSet
	 * @return
	 */
	public Scene setGeometries(Geometries geometriesToSet) {
		geometries = geometriesToSet;
		return this;
	}
	 
	
	/**
	 * @param lightsToSet
	 * @return
	 */
	public Scene setLights(List<LightSource> lightsToSet) {
		lights = lightsToSet;
		return this;	
	}
}
