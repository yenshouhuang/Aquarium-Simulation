package com.thomas;

import java.io.File;

//import org.magiclen.magicaudioplayer.AudioPlayer;

//import org.magiclen.magicaudioplayer.AudioPlayer;   


public class Food extends SimObject{

	Food(int x, int y){
		super("images" + java.io.File.separator + "FOOD.png", x, y);
	}
	Food(){
		super("images" + java.io.File.separator + "FOOD.png");
		
	}
	public void update(){
		moveFood(-1, 1, processing.width, processing.height);
	    placeFoodInTank();
	}
	private void moveFood( int dx, int dy, int width, int height) {
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
	private void placeFoodInTank() {
    	processing.image(objectImage,xPosition,yPosition);
    }

	public void getEaten() {
		if(player == null)
		{
			setEffectAudio("audio" + java.io.File.separator + "line_a_3.wav");
			player.setVolume(1);
		}
		player.play();
		bool_shouldBeRemoved = true;
	} 
}