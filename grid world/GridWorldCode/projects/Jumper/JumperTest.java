import static org.junit.Assert.*;
import org.junit.Test;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import java.io.IOException;

import javax.imageio.ImageIO;
import imagereader.IImageIO;

public class JumperTest {

	@Test
	public void textAct() {
		ActorWorld world = new ActorWorld();

		//create two jumpers
		Jumper j1 = new Jumper(Color.GREEN);
		Jumper j2 = new Jumper();
		
		//create a bug ,a rock ,a flower 
		Bug bug = new Bug();
		Rock rock = new Rock();
		Flower flower = new Flower();

		//set direction
		j1.setDirection(Location.NORTH);
		j2.setDirection(Location.NORTH);
		bug.setDirection(Location.NORTH);

		world.add(new Location(0,2), j1);
		world.add(new Location(5,3), j2);
		world.add(new Location(6,6), rock);
		world.add(new Location(1,6), flower);
		world.add(new Location(6,0), bug);

		world.show();

		j1.act();
		j2.act();
		bug.act();

		Grid gr1 = j1.getGrid();
		Grid gr2 = j2.getGrid();
		
		/**
		* test jumpers
		* test when the jumper1 facing an edge
		* test when the jumper2 can jump normally
		*/
		assertEquals(0, j1.getLocation().getRow());
		assertEquals(2,j1.getLocation().getCol());
		assertEquals(Location.NORTHEAST, j1.getDirection());
		assertEquals(3, j2.getLocation().getRow());
		assertEquals(3,j2.getLocation().getCol());
		assertEquals(Location.NORTH, j2.getDirection());

		//go two steps
		for (int i = 0; i < 2; i++){
			j1.act();
			j2.act();
			bug.act();
		}

		/**
		* test jumpers
		* After two steps, the jumper1 jump to the direction EAST 
		* and it can jump in this direction<br />
		* test when the location two cells in front of the jumper2
		* is out of the grid<br />
		*/
		assertEquals(0, j1.getLocation().getRow());
		assertEquals(4,j1.getLocation().getCol());
		assertEquals(Location.EAST, j1.getDirection());
		assertEquals(1, j2.getLocation().getRow());
		assertEquals(3,j2.getLocation().getCol());
		assertEquals(Location.NORTHEAST, j2.getDirection());

		// put the jumper two cells against the rock
		j1.moveTo(new Location(6,4));
		j1.setDirection(Location.EAST);
		j1.act();
		//check the state of j1
		assertEquals(6, j1.getLocation().getRow());
		assertEquals(4,j1.getLocation().getCol());
		assertEquals(Location.SOUTHEAST, j1.getDirection());

		//put the jumper two cells against the FLOWER
		j1.moveTo(new Location(3,6));
		j1.setDirection(Location.NORTH);
		j1.act();
		//check the state of j1
		assertEquals(1, j1.getLocation().getRow());
		assertEquals(6,j1.getLocation().getCol());
		assertEquals(Location.NORTH, j1.getDirection());

		//MOVE j1 two cells away from j2
		j1.moveTo(new Location(7,0));
		j1.setDirection(Location.EAST);
		j2.moveTo(new Location(7,2));
		j2.setDirection(Location.EAST);
		j1.act();
		j2.act();
		//check the state of j1
		assertEquals(7, j1.getLocation().getRow());
		assertEquals(0,j1.getLocation().getCol());
		assertEquals(Location.SOUTHEAST, j1.getDirection());

		//MOVE a jumper beside a bug
		bug.moveTo(new Location(7,5));
		j2.act();
		assertEquals(7, j2.getLocation().getRow());
		assertEquals(4,j2.getLocation().getCol());
		assertEquals(Location.SOUTHEAST, j2.getDirection());

	}
}

