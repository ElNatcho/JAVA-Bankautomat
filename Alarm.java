import sun.audio.*;
import java.io.*;

public class Alarm 
{
    private Rechteck R;
    private Audio S;
    private InputStream in;
    private AudioStream as;
    
    public Alarm()
    {
        R = new Rechteck(0, 0, 2000, 2000);
        R.setzeFarbe("weiss");
        try{
            in = new FileInputStream("sirene.wav");
        }catch(FileNotFoundException e){   
        }
        try{
            if(in != null)
                as = new AudioStream(in);
        }catch(IOException e){       
        }
    }

   
    public void Alarm_an()
    {
        if(in != null)
            AudioPlayer.player.start(as);
        for(int i = 0; i < 10000; i++)
        { 
            if(i%2 == 1) R.setzeFarbe("weiss");
            if(i%2 == 0) R.setzeFarbe("rot");
            StaticTools.warte(400);
        }
    }
    
   
}
