package com.thomas;

import java.io.File;

//import sun.audio.AudioPlayer;  

//import org.magiclen.magicaudioplayer.*;


public class Hook extends SimObject{

	
	Hook(int x, int y){
		super("images" + java.io.File.separator + "HOOK.png", x, y);
	}
	Hook(){
		super("images" + java.io.File.separator + "HOOK.png");
	}
	public void handleClick(int mouseX, int mouseY){
		xPosition = mouseX;
		yPosition = processing.height;
		placeHookInTank();
	}
	public void update(){
		moveHook(0, -(processing.height + 50 - yPosition)/50, processing.width, processing.height);
	    placeHookInTank();
	}
	private void moveHook( int dx, int dy, int width, int height) {
		xPosition += dx;
        if (xPosition == width) {
            xPosition -= width;
        }
        if (xPosition < 0) {
            xPosition += width;
        }
        yPosition += dy;
        if (yPosition == height) {
            yPosition -= height;
        }
        if (yPosition < 0) {
            yPosition += height;
        }

        
    }
	private void placeHookInTank() {
    	processing.image(objectImage,xPosition,yPosition);
    	processing.line(xPosition+4,yPosition-8,xPosition+4,0);
    }
}
