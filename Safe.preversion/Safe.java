/**
 * 
 */

public class Safe implements ITuWas
{
    public Tastenfeld Bank;
    public SafeTuer Tuer;
    public Alarmanlage Sirene;
    private int Versuche;
    private boolean Alarm;
    public Taste Bestaetigen = new Taste("Bestätigen",200,80,140,60);
    

    public void tuWas(int ID)
    {
        if (ID==6)
        {
            Betrieb();

        }
        if (ID==7)
        {
            Zumachen();
        }
    }

    public Safe	()
    {
        Bank= new Tastenfeld();
        Tuer=new SafeTuer();
        Sirene=new Alarmanlage();
        Versuche=0;
        Alarm=false;
        Bestaetigen.setzeLink(this,6);
        

    }

    public void Betrieb ()
    {
        if(Bank.a==1&&Bank.b==1&&Bank.c==1&&Bank.d==3)
        {
            Tuer.oeffnen();
            Schliessen.setzeDimensionen(30,550,200,100);
            Versuche=0;
        }
        else
            Versuche=Versuche+1;
        if (Versuche==1)
        {
            Sirene.Versuch1();
        }
        else
        {
            if (Versuche==2)
            {
                Sirene.Versuch2();
            }
            else
            {
                if (Versuche==3)
                {
                    Sirene.Versuch3();
                }
            }
        }
    }

    
}
