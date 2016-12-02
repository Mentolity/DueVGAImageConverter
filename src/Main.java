import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException{
		SerialRXTX port = new SerialRXTX();
		
		//DueImage conversionTest = new DueImage("C:/Users/Nyarlathotep/Pictures/1435104668315.png");
		//conversionTest.reduceImagePalette();
		//conversionTest.reduceImagePaletteAndDither(100);
		//System.out.print(conversionTest.toString());
		//conversionTest.writeToFile("test-13");
		
		
		/*Image nichijou = new Image("C:/Users/Nyarlathotep/Documents/Arduino/nichijou.png");
		nichijou.writeToFile("nichijou");
		Image totoro = new Image("C:/Users/Nyarlathotep/Documents/Arduino/totoro.png");
		totoro.writeToFile("totoro");*/
		
		/*DueImage[] lucinaBun = new DueImage[31];
		for(int i=1; i<2; i++){
			System.out.println("C:/Users/Nyarlathotep/Documents/Arduino/lucina-" + i + ".png");
			lucinaBun[i-1] = new DueImage("C:/Users/Nyarlathotep/Documents/Arduino/lucina-" + i + ".png");
			System.out.println(lucinaBun[i-1].toString());
			//lucinaBun[i-1].writeToFile("lucinaBun-" + i);
		}*/
		
		//Image palette = new Image("C:/Users/Nyarlathotep/Documents/Arduino/palette.png");
		
		/*DueGIF lucinaBun = new DueGIF("C:/Users/Nyarlathotep/Pictures/1442540326623.gif");
		lucinaBun.writeToFile("lucinaTestGIF");*/
		
		
		/*DueGIF spikeTestGIF = new DueGIF("C:/Users/Nyarlathotep/Pictures/Needs to be sorted/tumblr_m2qodllLRz1r5y3xho1_500.gif");
		spikeTestGIF.writeToFile("spikeTestGIF");*/
		
		//DueGIF lucinaBun = new DueGIF("C:/Users/Nyarlathotep/Pictures/Needs to be sorted/tumblr_m5j4lhy3MB1r8udhoo1_1280.gif",100);
		//lucinaBun.showPreviewFrame(2);
		//lucinaBun.writeToFile("angelTestGif");
		
		/*DueGIF rain = new DueGIF("C:/Users/Nyarlathotep/Pictures/1451479275317.gif",100);
		rain.showPreviewFrame(2);
		rain.writeToFile("rain");*/
		
		
		
		/*DueGIF kitty = new DueGIF("C:/Users/Nyarlathotep/Pictures/bxZoKT7.gif",100);
		kitty.showPreviewFrame(5);
		kitty.writeToFile("jager");*/
		
		
		/*DueImage boat = new DueImage("C:/Users/Nyarlathotep/Pictures/Needs to be sorted/Landscape/1348970341446.jpg");
		boat.reduceImagePaletteAndDither(0);
		boat.showImage();
		boat.writeToFile("boat-0");*/
		

		

		/*DueImage spectrum = new DueImage("C:/Users/Nyarlathotep/Pictures/1431500802399.jpg");
		spectrum.reduceImagePaletteAndDither(100);
		spectrum.writeToDue(port, "test16");
		System.out.println("----------1");*/
		
		
		/*DueImage test = new DueImage("C:/Users/Nyarlathotep/Pictures/untitled_by_lenatderirinc-d9knaax.jpg");
		test.reduceImagePaletteAndDither(100);
		//test.writeToFile("red");
		test.writeToDue(port, "red");*/
		
		
		
		DueImage test = new DueImage("C:/Users/Nyarlathotep/Pictures/Patrasche_Light_Novel.png");
		test.reduceImagePaletteAndDither(100);
		//test.writeToFile("red");
		test.writeToDue(port, "rem-1");
		

		
		/*DueGIF JakeTheDog = new DueGIF("C:/Users/Nyarlathotep/Pictures/c988305d1cae5e1f56b2178a1c43b74d.gif");
		JakeTheDog.writeToDue(port, "spooky_pokemon");*/
		
	}
}











