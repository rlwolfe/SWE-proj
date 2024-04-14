package unittests.renderer;

import org.junit.jupiter.api.Test;
import primitives.Color;
import renderer.ImageWriter;

/** 
 * Bella & Rachel
 * initial test to create blue and white grid
 */
public class ImageWriterTest {
	/**
	 * test for ImageWriter
	 */
	@Test
    void writeToImage() {
		int x = 800;
        int y = 500;
        //Color blue = new Color(2, 55, 182);
        Color blue = new Color(96, 135, 208);
        Color white = new Color(254, 254, 254);
        ImageWriter imageWriter = new ImageWriter("stage5",x+1,y+1);
        
        for (int i = 0; i <= x; i++) {
            for (int j = 0; j <= y; j++) {
                if(i % 50 == 0 || j % 50 == 0)
                    imageWriter.writePixel(i, j, blue);
                else
                    imageWriter.writePixel(i, j, white);
            }
        }
        imageWriter.writeToImage();
	}
}
