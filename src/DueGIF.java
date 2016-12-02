import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.*;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


public class DueGIF{
	ArrayList<DueImage> dueImages = new ArrayList<DueImage>();
	int ditherPercent;
	
	public DueGIF(String path){
		this(path, 100);
	}
	
	public DueGIF(String path, int dither){
		ditherPercent = dither;
		File gif = new File(path);
		try{
			ArrayList<BufferedImage> frames = getFrames(gif);
			for(int i=0; i<frames.size(); i++){
				//System.out.println(frames.get(i).getRGB(0,0) + ", " + frames.get(i).getWidth() + ", " + frames.get(i).getHeight());
				dueImages.add(new DueImage(frames.get(i)));
				dueImages.get(i).reduceImagePaletteAndDither(ditherPercent);
				
				//System.out.println(dueImages.get(i).toString());
				//showImage(frames.get(i));
			}
		}catch(IOException e){
			e.printStackTrace();
		}	
	}
	 public ArrayList<DueImage> getDueImages(){
		 return dueImages;
	 }
	
	public void showPreviewFrame(int frame){
		showImage(dueImages.get(frame).getPixelsAsBufferedImage());
	}
	
	private void showImage(BufferedImage img){
		JFrame frame = new JFrame();
		frame.getContentPane().setLayout(new FlowLayout());
		frame.getContentPane().add(new JLabel(new ImageIcon(img)));
		frame.pack();
		frame.setVisible(true);
	}
	
	private ArrayList<BufferedImage> getFrames(File gif) throws IOException{
		ArrayList<BufferedImage> bufferedImageFrames = new ArrayList<BufferedImage>();
		
		String[] imageatt = new String[]{
	            "imageLeftPosition",
	            "imageTopPosition"
	    };
		
	
		ImageReader reader = ImageIO.getImageReadersBySuffix("GIF").next();
		ImageInputStream in = ImageIO.createImageInputStream(gif);
		reader.setInput(in, false);
		
		int width = reader.read(0).getWidth();
		int height = reader.read(0).getHeight();
		
		BufferedImage master = null;
		// http://stackoverflow.com/questions/8933893/convert-each-animated-gif-frame-to-a-separate-bufferedimage#16234122
		for (int i = 0, count = reader.getNumImages(true); i < count; i++){
			
	        BufferedImage image = reader.read(i);
	        IIOMetadata metadata = reader.getImageMetadata(i);
	        Node tree = metadata.getAsTree("javax_imageio_gif_image_1.0");
	        NodeList children = tree.getChildNodes();

	        for (int j = 0; j < children.getLength(); j++) {
	            Node nodeItem = children.item(j);
	            if(nodeItem.getNodeName().equals("ImageDescriptor")){
	                Map<String, Integer> imageAttr = new HashMap<String, Integer>();
	                for (int k = 0; k < imageatt.length; k++){
	                    NamedNodeMap attr = nodeItem.getAttributes();
	                    Node attnode = attr.getNamedItem(imageatt[k]);
	                    imageAttr.put(imageatt[k], Integer.valueOf(attnode.getNodeValue()));
	                }   
	                master = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
	                master.getGraphics().drawImage(image, imageAttr.get("imageLeftPosition"), imageAttr.get("imageTopPosition"), null);
	            }
	        }
	        
	        bufferedImageFrames.add(master);
		}
		
		fillUnchangedPixels(bufferedImageFrames, width, height);
		return bufferedImageFrames;
	}
	
	private ArrayList<BufferedImage> fillUnchangedPixels(ArrayList<BufferedImage> imageFrames, int width, int height){
		for(int i=0; i<imageFrames.size(); i++){
			for(int y=0; y<height; y++){
				for(int x=0; x<width; x++){
					if(imageFrames.get(i).getRGB(x,y) == 0 && i != 0){
						imageFrames.get(i).setRGB(x,y, imageFrames.get(i-1).getRGB(x,y));
					}
				}
			}
		}
		return imageFrames;
	}

	
	//outputs the the image as a byteMap
	public void writeToFile(String fileName) throws IOException{
		FileOutputStream stream = new FileOutputStream(fileName);		
		for(int i=0; i<dueImages.size(); i++){
			stream.write(dueImages.get(i).getPixels());
		}
		stream.close();
	}	
	
	public void writeToDue(SerialRXTX port, String fileName) throws IOException{	
		byte[] pixels = new byte[320*240*dueImages.size()];
		int index = 0;
		
		for(int i=0; i<dueImages.size(); i++){			
			for(int x=0; x<320*240; x++){
				pixels[index] = dueImages.get(i).getPixels()[x];
				index++;
			}
		}
		
		port.writeFrame(fileName, pixels, true);
	}
	
}



