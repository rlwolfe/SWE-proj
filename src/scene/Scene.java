package scene;

import java.util.LinkedList;
import java.util.List;

import geometries.Intersectable;
import lighting.AmbientLight;
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
	//public Geometries geometries = new Geometries();
	public List<Intersectable> intersectables;// = new LinkedList();
	
	public Scene(String sceneName) {
		name = sceneName;
		background = Color.BLACK;
		ambientLight = AmbientLight.NONE;
		//public Geometries geometries = new Geometries();
		intersectables = new LinkedList<Intersectable>();
	}
	
	public Scene setBackground(Color backgroundColorToSet) {
		background = backgroundColorToSet;
		return this; 
	}
	
	public Scene setAmbientLight(AmbientLight ambientLightToSet) {
		ambientLight = ambientLightToSet;
		return this; 
	}
	
	public Scene setIntersectables(List<Intersectable> intersectablesToSet) {
		intersectables = intersectablesToSet;
		return this;	
	}
	/*
	 * public Scene setGeometries(Geometries geometriesToSet) { geometries =
	 * geometriesToSet; return this; }
	 */
}
