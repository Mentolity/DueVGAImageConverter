import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class DueImage {
	
	private static int[] colorPalette = {0xff000000,0xff012302,0xff054706,0xff0b6b0d,0xff128f16,0xff1ab21e,0xff21d626,0xff29fa2e,0xff010553,0xff022554,0xff064854,0xff0c6b55,0xff138f57,0xff1ab359,
		0xff22d65c,0xff29fa5f,0xff0414a7,0xff062ba7,0xff094ba7,0xff0f6da8,0xff1590a9,0xff1cb4a9,0xff23d7ab,0xff2afaac,0xff0b24fb,0xff0c34fb,0xff0f50fb,0xff1371fb,0xff1993fc,0xff1fb5fc,0xff25d8fd,
		0xff2cfcfe,0xff230001,0xff242402,0xff254706,0xff276b0e,0xff2a8f16,0xff2eb21e,0xff33d626,0xff38fa2e,0xff240653,0xff242554,0xff254854,0xff286b55,0xff2b8f57,0xff2eb359,0xff33d65c,0xff38fa5f,
		0xff2514a7,0xff252ba7,0xff274ba7,0xff296da8,0xff2c90a9,0xff2fb4a9,0xff34d7ab,0xff39faac,0xff2724fb,0xff2834fb,0xff2950fb,0xff2b71fb,0xff2d93fc,0xff31b5fc,0xff35d8fd,0xff3afcfe,0xff470102,
		0xff472404,0xff484708,0xff496b0f,0xff4b8f17,0xff4db21f,0xff4fd627,0xff52fa2e,0xff470654,0xff472654,0xff484855,0xff496c56,0xff4b8f57,0xff4db359,0xff4fd65c,0xff52fa5f,0xff4815a7,0xff482ba7,
		0xff494ba7,0xff4a6da8,0xff4b90a9,0xff4db4aa,0xff50d7ab,0xff53faac,0xff4924fb,0xff4935fb,0xff4a50fb,0xff4b71fb,0xff4c93fc,0xff4eb5fc,0xff51d8fd,0xff54fcfe,0xff6b0206,0xff6b2407,0xff6b480b,
		0xff6c6b12,0xff6d8f18,0xff6eb220,0xff70d628,0xff72fa2f,0xff6b0754,0xff6b2654,0xff6b4855,0xff6c6c56,0xff6d8f58,0xff6eb35a,0xff70d65c,0xff72fa60,0xff6b15a7,0xff6b2ca7,0xff6c4ba8,0xff6c6da8,
		0xff6d90a9,0xff6fb4aa,0xff70d7ab,0xff72faad,0xff6c25fb,0xff6c35fb,0xff6c51fb,0xff6d71fb,0xff6e93fc,0xff6fb5fc,0xff71d8fd,0xff73fcfe,0xff8e040a,0xff8e250c,0xff8f480f,0xff8f6b15,0xff908f1b,
		0xff91b222,0xff92d629,0xff93fa31,0xff8e0955,0xff8e2755,0xff8f4956,0xff8f6c57,0xff908f58,0xff91b35a,0xff92d65d,0xff94fa60,0xff8f16a8,0xff8f2ca8,0xff8f4ca8,0xff8f6ea8,0xff9091a9,0xff91b4aa,
		0xff92d7ab,0xff94fbad,0xff8f25fb,0xff8f35fb,0xff8f51fb,0xff9071fb,0xff9193fc,0xff92b6fc,0xff93d9fd,0xff94fcfe,0xffb20610,0xffb22611,0xffb24814,0xffb36c18,0xffb38f1e,0xffb4b324,0xffb5d62b,
		0xffb6fa32,0xffb20b56,0xffb22756,0xffb24957,0xffb36c58,0xffb38f59,0xffb4b35b,0xffb5d65e,0xffb6fa61,0xffb218a8,0xffb22da8,0xffb24ca8,0xffb36ea9,0xffb391a9,0xffb4b4aa,0xffb5d7ac,0xffb6fbad,
		0xffb326fb,0xffb336fb,0xffb351fb,0xffb371fc,0xffb493fc,0xffb4b6fd,0xffb5d9fd,0xffb6fcfe,0xffd60915,0xffd62716,0xffd64918,0xffd66c1c,0xffd68f21,0xffd7b327,0xffd8d62e,0xffd9fa34,0xffd60e57,
		0xffd62857,0xffd64a58,0xffd66c59,0xffd6905a,0xffd7b35c,0xffd8d75f,0xffd9fa62,0xffd619a8,0xffd62ea9,0xffd64da9,0xffd66ea9,0xffd791aa,0xffd7b4ab,0xffd8d7ac,0xffd9fbae,0xffd627fb,0xffd636fc,
		0xffd652fc,0xffd672fc,0xffd793fc,0xffd7b6fd,0xffd8d9fe,0xffd9fcff,0xfff90d1b,0xfff9281c,0xfff9491e,0xfffa6c21,0xfffa8f25,0xfffab32a,0xfffbd630,0xfffcfa37,0xfff91158,0xfff92a58,0xfff94a59,
		0xfffa6d5a,0xfffa905c,0xfffab35e,0xfffbd760,0xfffcfa63,0xfff91ba9,0xfff92fa9,0xfff94da9,0xfffa6faa,0xfffa91ab,0xfffbb4ac,0xfffbd7ad,0xfffcfbae,0xfffa28fc,0xfffa37fc,0xfffa52fc,0xfffa72fc,
		0xfffa94fd,0xfffbb6fd,0xfffbd9fe,0xffffffff};
	
	private static int[] bytePalette = {0x00,0x04,0x08,0x0C,0x10,0x14,0x18,0x1C,0x01,0x05,0x09,0x0D,0x11,0x15,0x19,0x1D,0x02,0x06,0x0A,0x0E,0x12,0x16,0x1A,0x1E,0x03,0x07,0x0B,0x0F,0x13,0x17,0x1B,
		0x1F,0x20,0x24,0x28,0x2C,0x30,0x34,0x38,0x3C,0x21,0x25,0x29,0x2D,0x31,0x35,0x39,0x3D,0x22,0x26,0x2A,0x2E,0x32,0x36,0x3A,0x3E,0x23,0x27,0x2B,0x2F,0x33,0x37,0x3B,0x3F,0x40,0x44,0x48,0x4C,
		0x50,0x54,0x58,0x5C,0x41,0x45,0x49,0x4D,0x51,0x55,0x59,0x5D,0x42,0x46,0x4A,0x4E,0x52,0x56,0x5A,0x5E,0x43,0x47,0x4B,0x4F,0x53,0x57,0x5B,0x5F,0x60,0x64,0x68,0x6C,0x70,0x74,0x78,0x7C,0x61,
		0x65,0x69,0x6D,0x71,0x75,0x79,0x7D,0x62,0x66,0x6A,0x6E,0x72,0x76,0x7A,0x7E,0x63,0x67,0x6B,0x6F,0x73,0x77,0x7B,0x7F,0x80,0x84,0x88,0x8C,0x90,0x94,0x98,0x9C,0x81,0x85,0x89,0x8D,0x91,0x95,
		0x99,0x9D,0x82,0x86,0x8A,0x8E,0x92,0x96,0x9A,0x9E,0x83,0x87,0x8B,0x8F,0x93,0x97,0x9B,0x9F,0xA0,0xA4,0xA8,0xAC,0xB0,0xB4,0xB8,0xBC,0xA1,0xA5,0xA9,0xAD,0xB1,0xB5,0xB9,0xBD,0xA2,0xA6,0xAA,
		0xAE,0xB2,0xB6,0xBA,0xBE,0xA3,0xA7,0xAB,0xAF,0xB3,0xB7,0xBB,0xBF,0xC0,0xC4,0xC8,0xCC,0xD0,0xD4,0xD8,0xDC,0xC1,0xC5,0xC9,0xCD,0xD1,0xD5,0xD9,0xDD,0xC2,0xC6,0xCA,0xCE,0xD2,0xD6,0xDA,0xDE,
		0xC3,0xC7,0xCB,0xCF,0xD3,0xD7,0xDB,0xDF,0xE0,0xE4,0xE8,0xEC,0xF0,0xF4,0xF8,0xFC,0xE1,0xE5,0xE8,0xEC,0xF0,0xF4,0xF8,0xFC,0xE2,0xE6,0xEA,0xEE,0xF2,0xF6,0xFA,0xFE,0xE3,0xE7,0xEB,0xEF,0xF3,
		0xF7,0xFB,0xFF};

	
	private static BufferedImage img;
	private static BufferedImage pixelsAsBufferedImage;
	private int width;
	private int height;
	private int[] originalPixels;
	private byte[] pixels;
	
	
	public DueImage(String path){	
		this(null, path);
	}
	
	
	public DueImage(BufferedImage image){
		this(image, null);
	}

	private DueImage(BufferedImage image, String path){
		//open the image
		if(image != null){
			img = image;
		}
			
		
		if(path != null){
			try{
				img = ImageIO.read(new File(path));
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		
		//determine the height and width of the image
		width = img.getWidth();
		height = img.getHeight();
		
		//if resolution is not 320x240 fix
		if(width != 320 || height != 240){
			img = toBufferedImage(img.getScaledInstance(320, 240, Image.SCALE_SMOOTH));
			width = 320;
			height = 240;
		}
		
		//added to generate preview image after dithering
		pixelsAsBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		//initiate pixels and original pixels arrays
		pixels = new byte[width*height];
		originalPixels = new int[width*height];
		int index = 0;
		
		//load the arrays with the pixel data
		for(int y=0; y<height; y++){
			for(int x=0; x<width; x++){
				//System.out.println(x + ", " + y);
				originalPixels[index] = img.getRGB(x,y);
				//System.out.println(originalPixels[index]);
				pixels[index] = (byte)(colorConverter(img.getRGB(x,y)));
				index++;
			}
		}
	}
	
	public byte[] getPixels(){
		return pixels;
	}
	
	public BufferedImage getPixelsAsBufferedImage(){
		//BufferedImage pixelsAsBufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB)
		return pixelsAsBufferedImage;
	}
	
	
	//converts an Image to a BufferedImage
	private static BufferedImage toBufferedImage(Image img){
		if (img instanceof BufferedImage){
	        return (BufferedImage) img;
	    }
	    // Create a buffered image with transparency
	    BufferedImage bimage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);
	    // Draw the image on to the buffered image
	    Graphics2D bGr = bimage.createGraphics();
	    bGr.drawImage(img, 0, 0, null);
	    bGr.dispose();
	    // Return the buffered image
	    return bimage;
	}
	
	//determines the distance between two colors in 3-Dimensions
	public double similarTo(int c1, int c2){
		int b1 = c1 & 0xff;
		int g1 = (c1 & 0xff00) >> 8;
		int r1 = (c1 & 0xff0000) >> 16;
		
		int b2 = c2 & 0xff;
		int g2 = (c2 & 0xff00) >> 8;
		int r2 = (c2 & 0xff0000) >> 16;
		
		return Math.sqrt((r2-r1)*(r2-r1) + (g2-g1)*(g2-g1) + (b2-b1)*(b2-b1));
	}
	
	//associates each pixel in the BufferedImage to its closest match in the colorPalette
	public void reduceImagePalette(){
		for(int i=0; i<originalPixels.length; i++){
			int bestIndex = 0;
			double match = Double.MAX_VALUE;
			for(int j=0; j<colorPalette.length; j++){
				double currentCompare = similarTo(colorPalette[j],originalPixels[i]);
				if(currentCompare < match){
					match = currentCompare;
					bestIndex = j;
				}
			}
			pixelsAsBufferedImage.setRGB((i)%320, (Integer)(i/320), colorPalette[bestIndex]);
			pixels[i] = (byte)(colorConverter(colorPalette[bestIndex]));
		}
	}
	
	//reduces image pallete and uses Floyd-Steinberg dithering algorithm
	public void reduceImagePaletteAndDither(int ditherPercentage){
		
		for(int i=0; i<originalPixels.length; i++){
			int bestIndex = 0;
			double match = Double.MAX_VALUE;
			for(int j=0; j<colorPalette.length; j++){
				double currentCompare = similarTo(colorPalette[j],originalPixels[i]);
				if(currentCompare < match){
					match = currentCompare;
					bestIndex = j;
				}
			}
			int bError = (originalPixels[i] & 0xff) - (colorPalette[bestIndex] & 0xff);
			int gError = ((originalPixels[i] & 0xff00) >> 8) - ((colorPalette[bestIndex] & 0xff00) >> 8);
			int rError = ((originalPixels[i] & 0xff0000) >> 16) - ((colorPalette[bestIndex] & 0xff0000) >> 16);
			
			
			originalPixels[i] = colorPalette[bestIndex]; //x

			if((i+1)%320 != 0)
				originalPixels[i+1] = disperseError(i, 1, 7, rError, gError, bError, ditherPercentage); //right 7/16
			
			if((i+1)%320 !=0 && i<((height*width)-321))
				originalPixels[i+width+1] = disperseError(i, width+1, 1, rError, gError, bError, ditherPercentage); //bottom right 1/16
			
			if(i<((height*width)-321)){
				originalPixels[i+width] = disperseError(i, width, 5, rError, gError, bError, ditherPercentage); //bottom 5/16
				originalPixels[i+width-1] = disperseError(i, width-1, 3, rError, gError, bError, ditherPercentage); //bottom left 3/16
			}
			
			pixelsAsBufferedImage.setRGB((i)%320, (Integer)(i/320), colorPalette[bestIndex]);
			pixels[i] = (byte)(colorConverter(colorPalette[bestIndex]));
		}
	}
	
	private int disperseError(int index, int pos, int multi, int rError, int gError, int bError, int percent){

		int red = (((originalPixels[index + pos] & 0xff0000) >> 16) + ((rError*multi) >> 4)*percent/100);
		int green = (((originalPixels[index + pos] & 0xff00) >> 8) + ((gError*multi) >> 4)*percent/100);
		int blue = ((originalPixels[index + pos] & 0xff) + ((bError*multi) >> 4)*percent/100);
		
		/*System.out.println("Old: " + ((originalPixels[index + pos] & 0xff0000) >> 16) + "," + ((originalPixels[index + pos] & 0xff00) >> 8) + "," + ((originalPixels[index + pos] & 0xff)));
		System.out.println("Error: " + rError + "," + gError + "," + bError + " Weight: " + multi);
		System.out.println("New: " + red + "," + green + "," + blue);*/
		
		if(red < 0)
			red = 0;
		if(red > 255)
			red = 255;
		
		if(green < 0)
			green = 0;
		if(green > 255)
			green = 255;
		
		if(blue < 0)
			blue = 0;
		if(blue > 255)
			blue = 255;
		
		int rgb = red;
		rgb = (rgb << 8) + green;
		rgb = (rgb << 8) + blue;
		
		//System.out.println("RGB: " + ((rgb & 0xff0000) >> 16) + "," + ((rgb & 0xff00) >> 8) + "," + (rgb & 0xff));
		
		return rgb;
	}
	
	
	//toString
	public String toString(){
		StringBuilder sb = new StringBuilder();
		for(Byte s: pixels){
			sb.append(String.format("0x%02X,", s));
		}
		return sb.toString();
	}
	
	
	//outputs the the image as a byteMap
	public void writeToFile(String fileName) throws IOException{
		FileOutputStream stream = new FileOutputStream(fileName);

		try {
		    stream.write(pixels);
		} finally {
		    stream.close();
		}
	}
	
	public void showImage(){
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(pixelsAsBufferedImage)));
		frame.pack();
		frame.setVisible(true);
	}
	
	
	/*public void writeToDue(String fileName) throws IOException{
		writeToDue(fileName, pixels);
	}*/
	
	public void writeToDue(SerialRXTX port, String fileName) throws IOException{
		writeToDue(port, fileName, true);
	}
	
	public void writeToDue(SerialRXTX port, String fileName, boolean finished) throws IOException{
		port.writeFrame(fileName, pixels, finished);
	}
	
	//converts to appropriate byte value for the Due
	private int colorConverter(int input){
		for(int i=0; i<colorPalette.length; i++){
			//System.out.println((input == colorPalette[i]) + ": " + input + " == " + colorPalette[i]);
			if(input == colorPalette[i])
				return bytePalette[i];
		}
		return 0xFF;
	}
}
