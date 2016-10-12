import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.ImageProducer;
import java.awt.image.MemoryImageSource;
import java.io.File;
import java.io.FileInputStream;

import java.io.IOException;

import javax.imageio.ImageIO;
import imagereader.IImageIO;

public class ImplementImageIO implements IImageIO {

	private int getByteData(byte info[] ,int s) {
		return ((info[s] & 0xff) << 24 |(info[s-1] & 0xff) << 16 |
				(info[s-2] & 0xff) << 8  |(info[s-3] & 0xff));
	}

	/**
	 * read Bitmap file use binary stream
	 * can use api to create image 
	 */
	public Image myRead(String filePath) throws IOException {
		try {
			FileInputStream fi  = new FileInputStream(filePath);
			Image img;
			/**
	 		 * create bit map with 14 byte.
	 		 * create info map with 40 byte.
	 		 * store the info need
			 */
			byte header[] = new byte[14];
			byte info[] = new byte[40];

			fi.read(header, 0, 14);
			fi.read(info, 0, 40);

			// 4-7 bit in info is the width of the bitmap
			int widthOfBitmap = getByteData(info, 7);
			// 8-11 bit in info is the height of the bitmap
			int heightOfBitmap = getByteData(info, 11);
			//  store the size of the bitmap
			int sizeOfBitmap = getByteData(info, 23);
			// judge whether the bitmap is 24 bit
			int byteNumOfBitmap = ((info[15] & 0xff) << 8  | (info[14] & 0xff));

			// if the bitmap is 24 bit
			if (byteNumOfBitmap == 24) {
				int emptyByte = sizeOfBitmap / heightOfBitmap - 3 * widthOfBitmap;
				// if the byte the bitmap use is not 4s, it will cause blank.
				emptyByte = (emptyByte == 4) ? 0 : emptyByte;

				byte bitmapByte[] = new byte[sizeOfBitmap];
				int arrayOfPx[] = new int[heightOfBitmap*widthOfBitmap];
				fi.read(bitmapByte, 0,sizeOfBitmap);

				int temp = 0;
				for (int i = heightOfBitmap - 1; i >= 0; i--) { //read from bottom to top
					for (int j = 0; j < widthOfBitmap; j++) { // from left to right
						arrayOfPx[widthOfBitmap*i+j] = (0xff << 24 ) |
													   ((bitmapByte[temp+2] & 0xff) << 16) |
													   ((bitmapByte[temp+1] & 0xff) << 8 ) |
													   ((bitmapByte[temp] & 0xff));
						temp += 3;
					}
					temp += emptyByte;
				} 
				img = Toolkit.getDefaultToolkit().createImage((ImageProducer) new MemoryImageSource
					(widthOfBitmap, heightOfBitmap, arrayOfPx, 0, widthOfBitmap));
			} 
			else  img = (Image)null;
			fi.close();
			return img;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// this method get from api
	public Image myWrite(Image img, String filePath) {
		try {
			// attribute
			// create file in the direction
			File imgFile = new File(filePath);
			BufferedImage bufferedImage = new BufferedImage(img.getWidth(null), img.getHeight(null), 
															BufferedImage.TYPE_INT_RGB);
			bufferedImage.createGraphics().drawImage(img,0,0,null);;
			ImageIO.write(bufferedImage, "bmp", imgFile);
			return img;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}