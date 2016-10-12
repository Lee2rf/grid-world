import static org.junit.Assert.*;
import org.junit.Test;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;

import javax.imageio.ImageIO;
public class ImageTest {
	
	@Test
	public void testRed() throws IOException {
		//test red
		FileInputStream fileIn = new FileInputStream("/home/Administrator/Desktop/grid world/bmptest/goal/1_red_goal.bmp");
		BufferedImage goal = ImageIO.read(fileIn);

		ImplementImageIO imageioer = new ImplementImageIO();
		Image source = imageioer.myRead("/home/Administrator/Desktop/grid world/bmptest/1.bmp");
		ImplementImageProcessor pro = new ImplementImageProcessor();
		Image red = pro.showChanelR(source);
		int width = red.getWidth(null);
		int height = red.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		test.getGraphics().drawImage(red, 0, 0, width, height, null);
		assertEquals(goal.getWidth(null), red.getWidth(null));
		assertEquals(goal.getHeight(null), red.getHeight(null));
		for (int i = goal.getHeight(null)-1; i >= 0; i--) {
			for (int j = 0; j < goal.getWidth(null); j++)
				assertEquals(goal.getRGB(i,j), test.getRGB(i,j));
		}
	}
	@Test
	public void testGreen() throws IOException {
		//test green
		FileInputStream fileIn = new FileInputStream("/home/Administrator/Desktop/grid world/bmptest/goal/1_green_goal.bmp");
		BufferedImage goal = ImageIO.read(fileIn);

		ImplementImageIO imageioer = new ImplementImageIO();
		Image source = imageioer.myRead("/home/Administrator/Desktop/grid world/bmptest/1.bmp");
		ImplementImageProcessor pro = new ImplementImageProcessor();
		Image green = pro.showChanelG(source);
		int width = green.getWidth(null);
		int height = green.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		test.getGraphics().drawImage(green, 0, 0, width, height, null);
		assertEquals(goal.getWidth(null), green.getWidth(null));
		assertEquals(goal.getHeight(null), green.getHeight(null));
		for (int i = goal.getHeight(null)-1; i >= 0; i--) {
			for (int j = 0; j < goal.getWidth(null); j++)
				assertEquals(goal.getRGB(i,j), test.getRGB(i,j));
		}
	}
	@Test
	public void testBlue() throws IOException {
		//test blue
		FileInputStream fileIn = new FileInputStream("/home/Administrator/Desktop/grid world/bmptest/goal/1_blue_goal.bmp");
		BufferedImage goal = ImageIO.read(fileIn);

		ImplementImageIO imageioer = new ImplementImageIO();
		Image source = imageioer.myRead("/home/Administrator/Desktop/grid world/bmptest/1.bmp");
		ImplementImageProcessor pro = new ImplementImageProcessor();
		Image blue = pro.showChanelB(source);
		int width = blue.getWidth(null);
		int height = blue.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		test.getGraphics().drawImage(blue, 0, 0, width, height, null);
		assertEquals(goal.getWidth(null), blue.getWidth(null));
		assertEquals(goal.getHeight(null), blue.getHeight(null));
		for (int i = goal.getHeight(null)-1; i >= 0; i--) {
			for (int j = 0; j < goal.getWidth(null); j++)
				assertEquals(goal.getRGB(i,j), test.getRGB(i,j));
		}
	}
	@Test
	public void testGray() throws IOException {
		//test gray
		FileInputStream fileIn = new FileInputStream("/home/Administrator/Desktop/grid world/bmptest/goal/1_gray_goal.bmp");
		BufferedImage goal = ImageIO.read(fileIn);

		ImplementImageIO imageioer = new ImplementImageIO();
		Image source = imageioer.myRead("/home/Administrator/Desktop/grid world/bmptest/1.bmp");
		ImplementImageProcessor pro = new ImplementImageProcessor();
		Image gray = pro.showGray(source);
		int width = gray.getWidth(null);
		int height = gray.getHeight(null);
		BufferedImage test = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		test.getGraphics().drawImage(gray, 0, 0, width, height, null);
		assertEquals(goal.getWidth(null), gray.getWidth(null));
		assertEquals(goal.getHeight(null), gray.getHeight(null));
		for (int i = goal.getHeight(null)-1; i >= 0; i--) {
			for (int j = 0; j < goal.getWidth(null); j++)
				assertEquals(goal.getRGB(i,j), test.getRGB(i,j));
		}
	}
}