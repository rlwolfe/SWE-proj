package unittests.renderer;

import static java.awt.Color.*;

import org.junit.jupiter.api.Test;

import geometries.*;
import lighting.AmbientLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Test rendering a basic image
 * @author Dan */
public class RenderTests {
   /** Scene of the tests */
   private final Scene          scene  = new Scene("Test scene");
   /** Camera builder of the tests */
   private final Camera.Builder camera = Camera.getBuilder()
      .setRayTracer(new SimpleRayTracer(scene))
      .setLocation(Point.ZERO)
      .setDirection(new Vector(0, 0, -1), new Vector(0, 1, 0))
      .setVpDistance(100)
      .setVpSize(500, 500);

   /** Produce a scene with basic 3D model and render it into a png image with a
    * grid */
   @Test
   public void renderTwoColorTest() {
      scene.intersectables.add(new Sphere(new Point(0, 0, -100), 50d));
      scene.intersectables.add(new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))); // up
                           // left
      scene.intersectables.add(new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))); // down
                           // left
      scene.intersectables.add(new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))); // down
      scene.setAmbientLight(new AmbientLight(new Color(60, 60, 255), Double3.ONE))
         .setBackground(new Color(WHITE));

      // right
      camera
         .setImageWriter(new ImageWriter("base render test", 500, 500))
         .renderImage()
         .printGrid(100, new Color(BLUE))
         .writeToImage()
         .build();
   }

// For stage 6 - please disregard in stage 5
   /**
    * Produce a scene with basic 3D model - including individual lights of the
    * bodies and render it into a png image with a grid
    */
   @Test
   public void renderMultiColorTest() {
      scene.intersectables.add( // center
                           new Sphere(new Point(0, 0, -100), 50d));
      scene.intersectables.add( // up left
                           new Triangle(new Point(-100, 0, -100), new Point(0, 100, -100), new Point(-100, 100, -100))
                              .setEmission(new Color(GREEN)));
      scene.intersectables.add( // down left
                           new Triangle(new Point(-100, 0, -100), new Point(0, -100, -100), new Point(-100, -100, -100))
                              .setEmission(new Color(RED)));
      scene.intersectables.add( // down right
                           new Triangle(new Point(100, 0, -100), new Point(0, -100, -100), new Point(100, -100, -100))
                              .setEmission(new Color(BLUE)));
      scene.setAmbientLight(new AmbientLight(new Color(WHITE), new Double3(0.2, 0.2, 0.2))); //

      camera
         .setImageWriter(new ImageWriter("color render test", 500, 500))
         .renderImage()
         .printGrid(50, new Color(WHITE))
         .writeToImage()
         .build();
   }
   
   /** Test for XML based scene - for bonus **
	 * @Test public void basicRenderXml() { // enter XML file name and parse from
	 * XML file into scene object // using the code you added in appropriate
	 * packages // ... // NB: unit tests is not the correct place to put XML parsing
	 * code
	 * 
	 * camera .setImageWriter(new ImageWriter("xml render test", 1000, 1000))
	 * .build() .renderImage() .printGrid(100, new Color(YELLOW)) .writeToImage(); }
	 */
}