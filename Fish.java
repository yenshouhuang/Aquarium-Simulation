import java.io.File;

//import org.magiclen.magicaudioplayer.AudioPlayer;

public class Fish extends SimObject{
    //	private PApplet processing;
//	private int xPosition;
//	private int yPosition;
//	private PImage fishImage;
/*
	private  AudioPlayer player;  // new added
	private  void setGetCaughtAudio(String AudioFilePathAndName)  // new added
	{
		File audioFile = new File(AudioFilePathAndName);
		player = AudioPlayer.createPlayer(audioFile);
	}
*/
    Fish(/* PApplet processing, */ int x, int y){
        //xPosition = x;
        //yPosition = y;
        //this.processing = processing;
        //objectImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
        super("images" + java.io.File.separator + "FISH.png", x, y);
        setEffectAudio("audio" + java.io.File.separator + "lineapp_end_16k.wav");
    }
    Fish(/* PApplet processing, */){
        //xPosition = Utility.randomInt(processing.width) + 1;
        //yPosition = Utility.randomInt(processing.height) + 1;
        //this.processing = processing;
        //objectImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
        super("images" + java.io.File.separator + "FISH.png");
        setEffectAudio("audio" + java.io.File.separator + "lineapp_end_16k.wav");
    }
    public void update(){
        moveFish(1, 0, processing.width, processing.height);
        placeFishInTank();
        // icon 掛在座標
    }
    private void moveFish( int dx, int dy, int width, int height) {
        xPosition += dx;
        // 判定繞回頭
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
    /*
    public void tryToEat(Food food) {
        float foodDistance = food.distanceTo(xPosition, yPosition);
        if (foodDistance < 40){
            food.getEaten();
        }
    }
    */
	/*
	public float distanceTo(int x, int y) {
		return (float)Math.sqrt(Math.pow(x-xPosition,2) + Math.pow(y-yPosition, 2));
	}
	*/
    public void getCaught() {
        player.play();
        yPosition = Utility.randomInt(processing.height)+1;
        xPosition =0;
    }
}
