public class Alarmanlage
{
   Kreis K = new  Kreis(400,300,50);
   SiebenSegment S = new SiebenSegment(0,0,100);
   Taste Alarm=new Taste("ALARM!",1,1,1,1);
   
   
   
    public Alarmanlage()
    {
       K.setzeFarbe("hellgrau");
       S.setzeFarbe("schwarz");
       S.anzeige(0);
       Alarm.setzeSchriftfarbe("rot");
       
       
        
    }

   
    public void Alarm()
    {
       Alarm.setzeHintergrundfarbe("orange");
        Alarm.setzeDimensionen(350,200,200,50);
        for(int i = 0; i < 10000; i++)
       { if(i%2 == 1) K.setzeFarbe("gelb");
           if(i%2 == 0) K.setzeFarbe("rot");
           StaticTools.warte(100);
        }
           
    }
    
    public void Versuch1()
    { S.setzeFarbe("schwarz");
      S.anzeige(1);
    
    }
    
     public void Versuch2()
    { S.setzeFarbe("orange");
      S.anzeige(2);
    
    }
    
     public void Versuch3()
    { S.setzeFarbe("rot");
      S.anzeige(3);
      Alarm();
  
    }
}
