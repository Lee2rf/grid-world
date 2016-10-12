import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

import imagereader.IImageProcessor;

public class ImplementImageProcessor implements IImageProcessor {
	private Toolkit kit = Toolkit.getDefaultToolkit();

	/**
	 * red swap filter
	 * reference api.
	 */
	class RedSwapFilter extends RGBImageFilter {
		public RedSwapFilter() {
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int arg0, int arg1, int arg2) {
			return arg2 & 0xffff0000;
		}
	}
	/**
	 * green swap filter
	 * reference api.
	 */
	class GreenSwapFilter extends RGBImageFilter {
		public GreenSwapFilter() {
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int arg0, int arg1, int arg2) {
			return arg2 & 0xff00ff00;
		}
	}
	/**
	 * blue swap filter
	 * reference api.
	 */
	class BlueSwapFilter extends RGBImageFilter {
		public BlueSwapFilter() {
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int arg0, int arg1, int arg2) {
			return arg2 & 0xff0000ff;
		}
	}
	/**
	 * gray swap filter
	 * reference api.
	 */
	class GraySwapFilter extends RGBImageFilter {
		public GraySwapFilter() {
			canFilterIndexColorModel = true;
		}
		@Override
		public int filterRGB(int arg0, int arg1, int arg2) {
			int red = (arg2 & 0x00ff0000) >> 16;
			int green = (arg2 & 0x0000ff00) >> 8;
			int blue = arg2 & 0x000000ff;
			// get gray from red and green and blue
			int gray = (int)((double)0.299*red+(double)0.587*green+(double)0.114*blue);
			return (arg2 & 0xff000000) + (gray << 16)+(gray<<8) + gray;
		}
	}

	public Image showChanelR(Image imageSource) {
		RedSwapFilter red = new RedSwapFilter();
		return kit.createImage(new FilteredImageSource(imageSource.getSource(), red));
	}
	public Image showChanelG(Image imageSource) {
		GreenSwapFilter green = new GreenSwapFilter();
		return kit.createImage(new FilteredImageSource(imageSource.getSource(), green));
	}
	public Image showChanelB(Image imageSource) {
		BlueSwapFilter blue = new BlueSwapFilter();
		return kit.createImage(new FilteredImageSource(imageSource.getSource(), blue));
	}
	public Image showGray(Image imageSource) {
		GraySwapFilter gray = new GraySwapFilter();
		return kit.createImage(new FilteredImageSource(imageSource.getSource(), gray));
	}
}