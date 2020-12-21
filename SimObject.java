import java.io.File;

import org.magiclen.magicaudioplayer.AudioPlayer;

public class SimObject {
    protected static PApplet processing;
    protected int xPosition;
    protected int yPosition;
    protected PImage objectImage;
    protected boolean bool_shouldBeRemoved = false;

    protected  AudioPlayer player;  // new added
    protected  void setEffectAudio(String AudioFilePathAndName)  // new added
    {
        File audioFile = new File(AudioFilePathAndName);
        player = AudioPlayer.createPlayer(audioFile);
    }

    public static void setProcessing(PApplet processing)
    {
        SimObject.processing = processing;
    }


    SimObject(String imagePath)
    {
        if(processing == null)
        {
            throw new IllegalStateException("SimObject.setProcessing() must be called before constructing any SimObjects.");
        }
        objectImage = processing.loadImage(imagePath);
        xPosition = Utility.randomInt(processing.width) + 1;
        yPosition = Utility.randomInt(processing.height) + 1;

    }
    SimObject(String imagePath, int x, int y)
    {
        if(processing == null)
        {
            throw new IllegalStateException("SimObject.setProcessing() must be called before constructing any SimObjects.");
        }
        objectImage = processing.loadImage(imagePath);
        xPosition = x;
        yPosition = y;

    }
    public float distanceTo(int x, int y) {
        //  pixel為單位，物件跟其他物間距離為何
        return (float)Math.sqrt(Math.pow(x-xPosition,2) + Math.pow(y-yPosition, 2));
    }
    public void update(){
        //	moveFish(1, 0, processing.width, processing.height);
        //    placeFishInTank();
    }
    public void tryToInteract(SimObject other)
    {
        float Distance;// = other.distanceTo(xPosition, yPosition);
        if(this instanceof Fish && other instanceof Food)
        {
            Distance = other.distanceTo(xPosition, yPosition);
            if (Distance < 40)
            {
                ((Food)other).getEaten();
            }
        }
        else if(this instanceof Hook && other instanceof Fish)
        {
            Distance = other.distanceTo(xPosition, yPosition);
            if (Distance < 40)
            {
                ((Fish)other).getCaught();
            }
        }
    }
    public boolean shouldBeRemoved()
    {
        return bool_shouldBeRemoved;
    }


}
