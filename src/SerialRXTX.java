import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Enumeration;


public class SerialRXTX {
	String inputLine; 
	SerialPort serialPort = null;
	String PORT_NAMES[] = { 
		"/dev/tty.usbserial-A9007UX1", // Mac OS X
		"/dev/ttyACM0", // Raspberry Pi
		"/dev/ttyUSB0", // Linux
		"COM18", // Windows
	};

	BufferedReader input = null;
	OutputStream output = null;
	
	CommPortIdentifier portId = null;
	
	public SerialRXTX() throws IOException{
		Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

		//First, Find an instance of serial port as set in PORT_NAMES.
		while (portEnum.hasMoreElements()) {
			CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
			for (String portName : PORT_NAMES) {
				if (currPortId.getName().equals(portName)) {
					portId = currPortId;
					System.out.println("PortId: " + currPortId);
					break;
				}
			}
		}
		int TIME_OUT = 20000;
		int DATA_RATE = 250000; 
		System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");
		
		try {
			// open serial port, and use class name for the appName.
			serialPort = (SerialPort) portId.open("main",TIME_OUT);
			// set port parameters
			serialPort.setSerialPortParams(DATA_RATE, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);

			// open the streams
			input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
			output = serialPort.getOutputStream();

			
		}catch (Exception e){
			System.err.println(e.toString());
		}
		
		while(!input.ready());
		
		while(input.ready()){
			inputLine=input.readLine();
			System.out.println(inputLine);//Initialization Succeeded
		}
	}
	
	public void writeFrame(String fileName, byte[] pixels, boolean finished){

		DecimalFormat f = new DecimalFormat("##.0");
	     
		int frames = pixels.length/(320*240);
		byte[] outPixels = new byte[320*240];
		int index = 0;
		
		try {
			
			output.write("create".getBytes());
	        while(!input.ready());
	        output.flush();
	        inputLine=input.readLine();
			System.out.println(inputLine); //Please input file name:
			
			
			output.write(fileName.getBytes());
			while(!input.ready());
			inputLine=input.readLine();
			System.out.println(inputLine); //filename
			
			
			while(!input.ready());
			inputLine=input.readLine();
			System.out.println(inputLine); //# of frames?
			
			
			output.write(("" + frames).getBytes());
			while(!input.ready());
			inputLine=input.readLine();
			System.out.println(inputLine); //frames
			
			
			long startTime = System.currentTimeMillis();
			for(int i=0; i<frames; i++){
				for(int x=0; x<(320*240); x++){
					outPixels[x] = pixels[index];
					index++;
				}
				
				//handshake w/ due to confirm its done with last frame
				while(!input.ready());
				inputLine=input.readLine();
				
				
				//System.out.println(inputLine); //Finished!
				
				
				output.write(outPixels);
			    System.out.println(f.format((((i+1)/(double)frames)*100)) + "%");
			}
			long elapsedTime = System.currentTimeMillis() - startTime;
			System.out.println((int)(elapsedTime/1000)/60 + " Minutes and " + (elapsedTime/1000)%60 + " Seconds");
	        
		
			output.flush();
			while(!input.ready());
			inputLine=input.readLine();
			System.out.println(inputLine); //Finished!
			
			
			//output.flush();
			Thread.sleep(100);
			if(finished){
				output.write(fileName.getBytes());
				output.flush();
		        while(!input.ready());
				inputLine=input.readLine();
				System.out.println(inputLine); //fileName
			}
			
			
			
			
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void closePort(){
		try{
			output.close();
			input.close();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
}
