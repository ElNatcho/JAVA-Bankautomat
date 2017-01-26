public class D_Standartfkt
{
    public int x,y,pos_x,pos_y;
    public boolean KL = false;
    public void xy()
    {
        x = Display.x;
        y = Display.y;
        pos_x = Display.pos_x;
        pos_y = Display.pos_y;
    }
   public void allesAusblenden() //Blendet alles aus indem sie die Groessen gleich null (=ausblenden) und manche Positionen auf ihren Standart setzt; steht am Anfang fast jeder Funktion, die das Layout veraendert
    {
        xy();
        Display.Taste1.setzeGroesse(0,0);
        Display.Taste2.setzeGroesse(0,0);
        Display.Taste3.setzeGroesse(0,0);
        Display.Taste4.setzeGroesse(0,0);
        Display.Taste5.setzeGroesse(0,0);
        Display.Taste6.setzeGroesse(0,0);
        Display.Taste7.setzeGroesse(0,0);
        Display.Taste8.setzeGroesse(0,0);
        Display.Taste11.setzeGroesse(0,0);
        Display.Taste21.setzeGroesse(0,0);
        Display.Taste31.setzeGroesse(0,0);
        Display.TasteAusBes.setzeGroesse(0,0);
        Display.TasteEinBes.setzeGroesse(0,0);
        Display.TasteAbmelden.setzeGroesse(0,0);
        Display.TasteKontoauszug.setzeGroesse(0,0);
        Display.Kontotext.setzeDimensionen(x/2-x/4+pos_x,y/3+pos_y,0,0); //Zuruecksetzten der Position da sie in einer anderen Funktion veraendert wird
        Display.Anzeige.setzePosition(x/2-27*x/100+pos_x,y/2+y/4+pos_y); //Zuruecksetzten der Position da sie in einer anderen Funktion veraendert wird
        Display.Anzeige.setzeFarbe("rot");
        Display.Anzeige.setzeGroesse(0);
        Display.eingabeAbheben.setzeGroesse(0,0);
        Display.Gesperrt.setzeGroesse(0,0);
        Display.anderesGesperrt.setzeGroesse(0,0);
        Display.pinTipp.setzeGroesse(0,0);
        Display.Kontonummertext.setzeGroesse(0,0);
        Display.geldAus.setzeGroesse(0,0);
        Display.pinHintergrund.setzeGroesse(0,0);
        Display.Kontoauszug.setzeGroesse(0,0);  
        Display.kontoHintergrund.setzeDimensionen(x/2-28*x/100+pos_x,y/2+y/4-y/200+pos_y,0,0); //Zuruecksetzten der Position da sie in einer anderen Funktion veraendert wird
        Display.pin1.setzeGroesse(0,0);
        Display.pin2.setzeGroesse(0,0);
        Display.pin3.setzeGroesse(0,0);
        Display.pin4.setzeGroesse(0,0);
        Display.Pin1.setzeGroesse(0,0);
        Display.Pin2.setzeGroesse(0,0);
        Display.Pin3.setzeGroesse(0,0);
        Display.Pin4.setzeGroesse(0,0);
        Display.pinAendern.setzeGroesse(0,0);
        Display.Abheben.setzeGroesse(0,0);
        Display.Einzahlen.setzeGroesse(0,0);
        Display.Stern1.setzeGroesse(0,0);
        Display.Stern2.setzeGroesse(0,0);
        Display.Stern3.setzeGroesse(0,0);
        Display.Stern4.setzeGroesse(0,0);
        Display.sternCounter = 0;
        Display.geld_einheit.setzeGroesse(0, 0);
        Display.Kontonummer.setzeGroesse(0, 0);
        Display.Pin.setzeGroesse(0,0);
        Display.GeldBetragAnzeige.setzeGroesse(0,0);
        Display.update_konto = false;
        Display.update_pin = false;
        Display.update_kontonummer = false;
        Display.kreditkarteGesperrt = true;
        Display.KonZ.resetKontonummer();
    }
    
    
    public void hauptmenueAnzeigen() //Zeigt nur manche Display.Tasten an, indem sie ihre Groesse auf die Urspruengliche setzt   
    {
        xy();
        allesAusblenden();
        Display.Taste1.setzeGroesse(x*1/3,y/10);
        Display.Taste2.setzeGroesse(x*1/3,y/10);
        Display.Taste3.setzeGroesse(x*1/3,y/10);
        Display.Taste4.setzeGroesse(x/3,y/10);
        Display.Taste11.setzeGroesse(x*1/3,y/10);
        Display.Taste21.setzeGroesse(x*1/3,y/10);
        Display.Taste31.setzeGroesse(x*1/3,y/10);
        Display.TasteAbmelden.setzeGroesse(2*x/9,x/20);
        Display.TasteKontoauszug.setzeGroesse(x/3,x/20);
        Display.update_konto = false;
        Display.update_pin = false;
        Display.update_kontonummer = false;
        ATM.setKontoAngemeldet(true);
        Display.kreditkarteGesperrt = false;
    } 
}
