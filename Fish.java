package com.thomas;

import java.io.File;



public class Fish extends SimObject{

	Fish(/* PApplet processing, */ int x, int y){
		super("images" + java.io.File.separator + "FISH.png", x, y);
		setEffectAudio("audio" + java.io.File.separator + "lineapp_end_16k.wav");
	}
	Fish(/* PApplet processing, */){
		super("images" + java.io.File.separator + "FISH.png");
		setEffectAudio("audio" + java.io.File.separator + "lineapp_end_16k.wav");
	}
	public void update(){
		moveFish(1, 0, processing.width, processing.height);
	    placeFishInTank();
	}
	private void moveFish( int dx, int dy, int width, int height) {
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
	private void placeFishInTank() {
    	processing.image(objectImage,xPosition,yPosition);
    }

	public void getCaught() {
		player.play();
		yPosition = Utility.randomInt(processing.height)+1;
		xPosition =0;
	}
}
