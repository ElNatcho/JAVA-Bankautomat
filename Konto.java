
public class Konto
{
    public int Guthaben;// variable G erstellt lel
    public int GuthabenDevisen;
    public int Pin[] = {0,0,0,0}; //variable P jo
    public boolean Test;
    public int[] Kontonummer = {0,0,0,0};
    
    public Konto()//Konstructor 
    {
        Guthaben = 0;
        GuthabenDevisen = 0;
    }
    
    public void Pinsetzen(int[] _pin)
    {
        // Muss so kopiert werden da sonst eine Referenz der Elemente von _pin mit in Pin kopiert werden
        //Pin = {_pin[0], _pin[1], _pin[2], _pin[3]};
        for(int i = 0; i < 4; i++){
            //System.out.println(_pin[i]);
            int temp = _pin[i];
            Pin[i] = _pin[i];
        }
    }
     
    public int Kontonummergeben()
    {
        return Kontonummer[0]+Kontonummer[1]+Kontonummer[2]+Kontonummer[3];
    }
   
    public int gibGuthaben ()
    {
        return Guthaben;//gibt wert von guthaben aus
    }
    
    public void Guthabenabzug (int x)// Variable x festlegen 
    {
        Guthaben = Guthaben-x; // neues guthaben nach abzug
    }
    public void Einzahlen (int y)// -``-
    {
        Guthaben = Guthaben+y; // neues guthben nach +       
    }
    public int getPin()
    {
      return 0;//Pin;
    }
    public boolean Pinabfrage()
    {
      if (Pin[0] == Display.EingegebenerPinAusgabe[0]
       && Pin[1] == Display.EingegebenerPinAusgabe[1]
       && Pin[2] == Display.EingegebenerPinAusgabe[2]
       && Pin[3] == Display.EingegebenerPinAusgabe[3])
        {Test = true;}
        else 
        Test = false;
        return Test;

        
    }
}

