#include <VGA.h>
#include <SPI.h>
#include <SdFat.h>

const int width = 320;
const int height = 240;
const int chipSelect = 4;

String fileName = "shinobu";

SdFat sd;
SdFile myFile;

void setup(){
  VGA.begin(320,240,VGA_COLOUR);
  Serial.begin(250000);
  //UART->UART_BRGR = 10; //525000
  
  delay(400);

  if (!sd.begin(chipSelect, SPI_HALF_SPEED)) {
    sd.initErrorHalt();
  }
  
  Serial.println("Initialization Succeeded");
}

void loop(){
  if(Serial.available()){
    String input = Serial.readString();
    if(input == "Create" || input == "create"){
      VGA.end();
      Serial.println("Please input file name: ");
      while(!Serial.available());
      input=Serial.readString();
      
      
      //can't return arrays so double used code
      char fileNameCharArray[input.length()+1];
      input.toCharArray(fileNameCharArray, sizeof(fileNameCharArray));

      Serial.println(input);
      
      if(myFile.open(fileNameCharArray, O_WRITE | O_APPEND | O_CREAT)){
        Serial.println("Number of Frames: "); 
        
        while(!Serial.available());        
        int numberOfFrames = Serial.readString().toInt();
        Serial.println(numberOfFrames);
        
        int currentFrame = 0;
        while(currentFrame < numberOfFrames){
          Serial.println(currentFrame);
          while(!Serial.available());
          while(Serial.available()){
            myFile.print(Serial.readString());
          }
          currentFrame++;
        }
        
        myFile.close();
        Serial.println("Finished! ");
        VGA.begin(320,240,VGA_COLOUR);
      }else{
        Serial.println("Failure!");
      }
    }else{
      fileName=input;
      Serial.println(fileName);
    }
  }
  
  char fileNameCharArray[fileName.length()+1];
  fileName.toCharArray(fileNameCharArray, sizeof(fileNameCharArray));
  //Serial.println(fileNameCharArray);
  
  if (!myFile.open(fileNameCharArray, O_READ)){
    //sd.errorHalt("opening image for read failed");
  }else{
      while(myFile.available()){
        myFile.read(VGA.cb, 320*240);
        //VGA.putCPixelFast(myFile.read(),myFile.read(),myFile.read());
        //delay(10);
        //Serial.println("Frame loaded");
      }
      
      //delay(3000);
      //Serial.println("File Closed");
      myFile.close();
  }
}










