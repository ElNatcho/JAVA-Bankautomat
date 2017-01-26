public class D_Kontonummerzeugs
{
    public D_Standartfkt S = new D_Standartfkt();
    public D_Pinzeugs P = new D_Pinzeugs();
    public D_Kontozeugs Kon = new D_Kontozeugs();
    public int x,y,pos_x,pos_y, aktuellePos;
    int kontonummer[];
    
    public D_Kontonummerzeugs() {
        kontonummer = new int[4];
        kontonummer[0] = -1;
        kontonummer[1] = -1;
        kontonummer[2] = -1;
        kontonummer[3] = -1;
        aktuellePos = 0;
    }
    
    public void xy()
    {
        x = Display.x;
        y = Display.y;
        pos_x = Display.pos_x;
        pos_y = Display.pos_y;
    }
   
    public void fuegeKontonummerZifferHinzu(int ziffer){
        if(aktuellePos < 4 && ziffer >= 0 && ziffer <= 9){
            kontonummer[aktuellePos] = ziffer;
            Display.Pin1.setzeAusgabetext(Display.Pin1.leseText() + ziffer);
            aktuellePos++;
        }else if(ziffer == -1 && aktuellePos > 0){
            aktuellePos--;
            kontonummer[aktuellePos] = -1;
            Display.Pin1.setzeAusgabetext(Display.Pin1.leseText().substring(0, aktuellePos));
        }
    }
    
    public void andereKontonummereingabe()
    {
      xy();
        S.allesAusblenden();  
      Display.Kontonummertext.setzeGroesse(x,x/10); //Zeigt Text an
      Display.Pin1.setzeGroesse(x/8,y/20); //Zeigt erste Stelle des Eingabefelds an
      kontonummer[0] = -1;
      kontonummer[1] = -1;
      kontonummer[2] = -1;
      kontonummer[3] = -1;
      Display.update_kontonummer = true;
      Display.Taste8.setzeGroesse(x/3,y/10); //Zeigt fuenfte Stelle des Eingabefelds an
      aktuellePos = 0;
      ATM.setKontoAngemeldet(false);
    }
    
    public void andereKontonummereingabebest() //Prueft ob die eingegebene Kontonummer existiert, wenn ja leitet es zur Display.Pinabfrage weiter
    {
      xy();
        if(kontonummer[0] == Display.MKonto1.Kontonummer[0] //Prueft ob Eingegebene Kontonummer der ersten Moeglichkeit gleicht, wenn nicht springt es zur naechsten weiter
      && kontonummer[1] == Display.MKonto1.Kontonummer[1]
      && kontonummer[2] == Display.MKonto1.Kontonummer[2]
      && kontonummer[3] == Display.MKonto1.Kontonummer[3])
      {Display.Kontoangemeldet = 0;
       P.anderePinAbfrageAnzeigen();
       resetKontonummer();}  
      
      else if(kontonummer[0] == Display.MKonto2.Kontonummer[0] //Prueft ob Eingegebene Kontonummer der zweiten Moeglichkeit gleicht, wenn nicht springt es zur naechsten weiter
      && kontonummer[1] == Display.MKonto2.Kontonummer[1]
      && kontonummer[2] == Display.MKonto2.Kontonummer[2]
      && kontonummer[3] == Display.MKonto2.Kontonummer[3])
      {Display.Kontoangemeldet = 1;
       P.anderePinAbfrageAnzeigen();
       resetKontonummer();}  
      
      else if(kontonummer[0] == Display.MKonto3.Kontonummer[0] //Prueft ob Eingegebene Kontonummer der dritten Moeglichkeit gleicht, wenn nicht springt es zur naechsten weiter
      && kontonummer[1] == Display.MKonto3.Kontonummer[1]
      && kontonummer[2] == Display.MKonto3.Kontonummer[2]
      && kontonummer[3] == Display.MKonto3.Kontonummer[3])
      {Display.Kontoangemeldet = 2;
       P.anderePinAbfrageAnzeigen();
       resetKontonummer();}  
      
      else if(kontonummer[0] == Display.MKonto4.Kontonummer[0] //Prueft ob Eingegebene Kontonummer der vierten Moeglichkeit gleicht, wenn nicht springt es zur naechsten weiter
      && kontonummer[1] == Display.MKonto4.Kontonummer[1]
      && kontonummer[2] == Display.MKonto4.Kontonummer[2]
      && kontonummer[3] == Display.MKonto4.Kontonummer[3])
      {Display.Kontoangemeldet = 3;
       P.anderePinAbfrageAnzeigen();
       resetKontonummer();}  
      
      else if(kontonummer[0] == Display.MKonto5.Kontonummer[0]//Prueft ob Eingegebene Kontonummer der fuenften(letzten) Moeglichkeit gleicht, wenn nicht setzt es die Eingabe zurueck
      && kontonummer[1] == Display.MKonto5.Kontonummer[1]
      && kontonummer[2] == Display.MKonto5.Kontonummer[2]
      && kontonummer[3] == Display.MKonto5.Kontonummer[3])
      {Display.Kontoangemeldet = 4;
       P.anderePinAbfrageAnzeigen();
       resetKontonummer();}  
       else{ //setzt Eingabe in Textfeldern zurueck
        Display.Pin1.setzeAusgabetext("");
        kontonummer[0] = -1;
        kontonummer[1] = -1;
        kontonummer[2] = -1;
        kontonummer[3] = -1;
        aktuellePos = 0;
        }
    }
    
    public void resetKontonummer() {
        Display.Pin1.setzeAusgabetext("");
        kontonummer[0] = -1;
        kontonummer[1] = -1;
        kontonummer[2] = -1;
        kontonummer[3] = -1;
        aktuellePos = 0;
    }
     
}
