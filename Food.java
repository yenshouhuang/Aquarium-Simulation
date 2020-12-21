import java.io.File;



public class Food extends SimObject{

//	private int xPosition;
//	private int yPosition;
//	private PImage foodImage;
	/*
	private  AudioPlayer player;  // new added
	private  void setGetEatenAudio(String AudioFilePathAndName)  // new added
	{
		File audioFile = new File(AudioFilePathAndName); 
		player = AudioPlayer.createPlayer(audioFile);		
	}
	*/
    Food(/* PApplet processing, */int x, int y){
        //xPosition = x;
        //yPosition = y;
        //this.processing = processing;
        //objectImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
        super("images" + java.io.File.separator + "FOOD.png", x, y);
        //setGetEatenAudio("audio" + java.io.File.separator + "line_a_3.wav");
    }
    Food(/* PApplet processing, */){
        //xPosition = Utility.randomInt(processing.width) + 1;
        //yPosition = Utility.randomInt(processing.height) + 1;
        //this.processing = processing;
        //objectImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
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
    /*
    public float distanceTo(int x, int y) {
        return (float)Math.sqrt(Math.pow(x-xPosition,2) + Math.pow(y-yPosition, 2));
    }
    */
    public void getEaten() {
        //xPosition = Utility.randomInt(processing.width)+1;
        //yPosition =0;
        if(player == null)
        // 吃到魚餌再來設定音效檔
        {
            setEffectAudio("audio" + java.io.File.separator + "line_a_3.wav");
            player.setVolume(1);
        }
        player.play();
        bool_shouldBeRemoved = true;
    }
}