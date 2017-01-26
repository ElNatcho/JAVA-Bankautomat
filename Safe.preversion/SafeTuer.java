
public class SafeTuer
{

    public Bild offen;
    public Bild zu;

    public SafeTuer()
    {
        offen=new Bild(30, 200, 300, 300,"Schönescheine.jpg" );
        zu = new Bild(30, 200, 500, 500, "SafeTür.jpg");
    }

    public void oeffnen ()
    {
        zu.setzeDimensionen(1,1,1,1);

    }
    public void schliessen ()
    {
        zu.setzeDimensionen(30, 200, 300, 300);

    }
}

