import java.io.File;

//import sun.audio.AudioPlayer;

//import org.magiclen.magicaudioplayer.*;


public class Hook extends SimObject{
//	private PApplet processing;
//	private int xPosition;
//	private int yPosition;
//	private PImage hookImage;


    Hook( /*PApplet processing,*/ int x, int y){
        //xPosition = x;
        //yPosition = y;
        //this.processing = processing;
        //objectImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
        super("images" + java.io.File.separator + "HOOK.png", x, y);
    }
    Hook( /*PApplet processing,*/){
        //xPosition = Utility.randomInt(processing.width) + 1;
        //yPosition = Utility.randomInt(processing.height) + 1;
        //this.processing = processing;
        //objectImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
        super("images" + java.io.File.separator + "HOOK.png");
    }
    public void handleClick(int mouseX, int mouseY){
        xPosition = mouseX;
        yPosition = processing.height;
        placeHookInTank();
    }
    public void update(){
        moveHook(0, -(processing.height + 50 - yPosition)/50, processing.width, processing.height);
        placeHookInTank(); // 非等速的魚鉤
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
        processing.image(objectImage,xPosition,yPosition); // draw icon
        processing.line(xPosition+4,yPosition-8,xPosition+4,0); // draw line
    }
	/*
	public void tryToCatch(Fish fish) {
		float fishDistance = fish.distanceTo(xPosition, yPosition);
		if (fishDistance < 40){
			fish.getCaught();
		}
	}
	*/
}
