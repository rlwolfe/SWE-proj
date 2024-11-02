/**
 * 
 */
package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.DirectionalLight;
import lighting.SpotLight;
import primitives.Color;
import primitives.Material;
import primitives.Point;
import primitives.Vector;
import renderer.Camera;
import renderer.ImageWriter;
import renderer.SimpleRayTracer;
import scene.Scene;

/**
 * 
 */
public class FinalPictureTest {
	//building the scene
	private final Scene          scene         = new Scene("Test scene").setBackground(new Color(135, 206, 235)); //sky blue
	private final Scene          sceneFlag         = new Scene("Flag scene").setBackground(new Color(WHITE));
	
	// Camera builder for the tests with triangles
	private final Camera.Builder cameraBuilder = Camera.getBuilder()
			.setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
			.setRayTracer(new SimpleRayTracer(scene));

	@Test
	public void RainbowOverPyramid() {
				int x=50, y=25;
		scene.geometries.add(
		//rainbow
				new Sphere(new Point(x, y, -450), 125d).setEmission(new Color(RED))
					.setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(10).setkT(0.1)),
		
				new Sphere(new Point(x, y, -400), 100d).setEmission(new Color(YELLOW))
					.setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(10).setkT(0.1)),
					
				new Sphere(new Point(x, y, -350), 80d).setEmission(new Color(GREEN))
					.setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(10).setkT(0.1)),
		
				new Sphere(new Point(x, y, -300), 60d).setEmission(new Color(BLUE))
					.setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(10).setkT(0.1)),

				new Sphere(new Point(x, y, -250), 45d).setEmission(new Color(MAGENTA))
					.setMaterial(new Material().setKd(0.4).setKs(0.4).setShininess(10).setkT(0.1)),
					
					
		//pyramids
				new Triangle(new Point(-150, -150, -120), new Point(300, -250, -120), new Point(70, 80, -100))
					.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
					
				new Triangle(new Point(-200, -150, -215), new Point(300, -250, -115), new Point(70, 80, -100))
					.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
					
				new Triangle(new Point(-250, -150, -105), new Point(-50, 90, -105), new Point(100, -150, -105))
					.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
					
				new Triangle(new Point(-250, -150, -115), new Point(-50, 90, -115), new Point(-350, -175, -200))
					.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)));
		
		scene.setAmbientLight(new AmbientLight(new Color(YELLOW), 0.1));
		
		scene.lights.add(new DirectionalLight(new Color(255, 191, 0), new Vector(0, 0, -1)));

		cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(3000)
		.setVpSize(600, 600)
		.setImageWriter(new ImageWriter("RainbowOverPyramid", 600, 600))
		.renderImage()
		.writeToImage()
		.build();
	}

	@Test
	public void Flag() {
		sceneFlag.geometries.add( 
				new Triangle(new Point(0, -60, -10), new Point(-60, 30, 0), new Point(60, 30, 0)).setEmission(new Color(BLUE))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),

				new Triangle(new Point(0, 60, 10), new Point(60, -30, 0), new Point(-60, -30, 0)).setEmission(new Color(BLUE))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
				
				new Triangle(new Point(0, -50, -10), new Point(-50, 25, 10), new Point(50, 25, 10)).setEmission(new Color(WHITE))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),

				new Triangle(new Point(0, 50, 10), new Point(50, -25, 10), new Point(-50, -25, 10)).setEmission(new Color(WHITE))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),

				new Triangle(new Point(-100, -90, 10), new Point(-100, -70, 10), new Point(100, -70, 10)).setEmission(new Color(BLUE))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
				
				new Triangle(new Point(100, -90, 10), new Point(-100, -90, 10), new Point(100, -70, 10)).setEmission(new Color(BLUE))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
				
				new Triangle(new Point(100, 90, 10), new Point(100, 70, 10), new Point(-100, 70, 10)).setEmission(new Color(BLUE))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)),
				
				new Triangle(new Point(-100, 90, 10), new Point(100, 90, 10), new Point(-100, 70, 10)).setEmission(new Color(BLUE))
				.setMaterial(new Material().setKd(0.5).setKs(0.5).setShininess(60)));
		
		scene.setAmbientLight(new AmbientLight(new Color(YELLOW), 0.15));
		
		scene.lights.add(
				new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
				.setKl(4E-5).setKq(2E-7));

		cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(3000)
		.setVpSize(600, 600)
		.setImageWriter(new ImageWriter("Flag", 600, 600))
		.renderImage()
		.writeToImage()
		.build();
	}
}
