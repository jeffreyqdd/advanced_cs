package core;

import java.awt.*;


public class PhotoMagic
{

	public static Picture transform(Picture pic, LFSR lfsr) {
		//8 bit
		for(int r = 0; r < pic.width(); r++) {
			for(int c = 0; c < pic.height(); c++) {
				Color color = pic.get(r,c);
				
				//mask red
				int redVal = color.getRed() ^ lfsr.generate(8);
				
				//mask green
				int greenVal = color.getGreen() ^ lfsr.generate(8);
				
				//mask blue
				int blueVal = color.getBlue() ^ lfsr.generate(8);
				
				pic.set(r,c, new Color(redVal, greenVal, blueVal));
			}
		}
		
		return pic;
	}
	

}
