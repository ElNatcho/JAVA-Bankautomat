public class D_Pinzeugs
{
    public D_Standartfkt S = new D_Standartfkt();
    public int x,y,pos_x,pos_y, aktuellePos, login_versuche;
    int pin[];
    
    public D_Pinzeugs(){
        pin = new int[4];
        pin[0] = -1;
        pin[1] = -1;
        pin[2] = -1;
        pin[3] = -1;
        aktuellePos = 0;
        login_versuche = 0;
    }
    
    public void fuegePinZifferHinzu(int ziffer) {
        if(aktuellePos < 4 && ziffer >= 0 && ziffer <= 9){
            pin[aktuellePos] = ziffer;
            Display.Pin1.setzeAusgabetext(Display.Pin1.leseText() + ziffer);
            aktuellePos++;
        }else if(ziffer == -1 && aktuellePos > 0){
            aktuellePos--;
            pin[aktuellePos] = -1;
            Display.Pin1.setzeAusgabetext(Display.Pin1.leseText().substring(0, aktuellePos));
        }
    }
    
    public void xy()
    {
        x = Display.x;
        y = Display.y;
        pos_x = Display.pos_x;
        pos_y = Display.pos_y;
    }  
    
    public void anderePinAbfrageAnzeigen() //Zeigt nur manche Objekte an; Passworteingabe mit Eingabefeld, da KeyListener anscheinend nicht mit ITuWas funktioniert (Methode manuell ausfuehren geht,sobald eine Taste mit in den Start des 
    {                                      //Keylisteners eingebunden wird, startet er nicht mehr)
      xy();
      S.allesAusblenden();
      Display.Pin1.setzeGroesse(x/8,y/20); //Anzeigen des ersten Eingabefelds
      Display.Pin1.setzeAusgabetext("");
      Display.anderesGesperrt.setzeDimensionen(x/4+pos_x,y/3+pos_y,x,x/10); //Anzeigen des Textfeldes fuer zweite Passworteingabe
      Display.Taste7.setzeGroesse(x/3,y/10); //Anzeigen der Bestaetigentaste
      pin[0] = -1;
      pin[1] = -1;
      pin[2] = -1;
      pin[3] = -1;
      aktuellePos = 0;
      login_versuche = 0;
      Display.update_pin = true;
    }  
    
    private void _pinAendernErfolgreich() {
        pin[0] = -1;
        pin[1] = -1;
        pin[2] = -1;
        pin[3] = -1;
        aktuellePos = 0;
    }
    
    private void _anmeldungErfolgreich() {
        S.hauptmenueAnzeigen();
        _pinAendernErfolgreich();
        login_versuche = 0;
    }
    
    public void Anmeldebestaetigung() //Prueft ob der eingegebene Pin mit dem im Array PINx[] gespeicherten Pin uebereinstimmt, wenn ja, zeigt es das Hauptmenue an, wenn nicht setzt es die Werte in den Eingabefeldern zurueck
    {
        //System.out.println((Display.MKonto1.Pin[0]) + "" + (Display.MKonto1.Pin[1]) + "" + (Display.MKonto1.Pin[2]) + "" + (Display.MKonto1.Pin[3]));
        if(Display.Kontoangemeldet == 0 //Prueft welches Konto angemeldet ist
      && pin[0] == Display.MKonto1.Pin[0] //Prueft ob der Pin mit dem Eingegebenen uebereinstimmt
      && pin[1] == Display.MKonto1.Pin[1] 
      && pin[2] == Display.MKonto1.Pin[2] 
      && pin[3] == Display.MKonto1.Pin[3])
      {_anmeldungErfolgreich();} 
               
      else if(Display.Kontoangemeldet == 1 //Prueft welches Konto angemeldet ist
      && pin[0] == Display.MKonto2.Pin[0] //Prueft ob der Pin mit dem Eingegebenen uebereinstimmt
      && pin[1] == Display.MKonto2.Pin[1] 
      && pin[2] == Display.MKonto2.Pin[2] 
      && pin[3] == Display.MKonto2.Pin[3])
      {_anmeldungErfolgreich();} 
      
      else if(Display.Kontoangemeldet == 2 //Prueft welches Konto angemeldet ist
      && pin[0] == Display.MKonto3.Pin[0] //Prueft ob der Pin mit dem Eingegebenen uebereinstimmt
      && pin[1] == Display.MKonto3.Pin[1] 
      && pin[2] == Display.MKonto3.Pin[2] 
      && pin[3] == Display.MKonto3.Pin[3])
      {_anmeldungErfolgreich();} 
      
      else if(Display.Kontoangemeldet == 3 //Prueft welches Konto angemeldet ist
      && pin[0] == Display.MKonto4.Pin[0] //Prueft ob der Pin mit dem Eingegebenen uebereinstimmt
      && pin[1] == Display.MKonto4.Pin[1] 
      && pin[2] == Display.MKonto4.Pin[2] 
      && pin[3] == Display.MKonto4.Pin[3])
      {_anmeldungErfolgreich();} 
      
      else if(Display.Kontoangemeldet == 4 //Prueft welches Konto angemeldet ist
      && pin[0] == Display.MKonto5.Pin[0] //Prueft ob der Pin mit dem Eingegebenen uebereinstimmt
      && pin[1] == Display.MKonto5.Pin[1] 
      && pin[2] == Display.MKonto5.Pin[2] 
      && pin[3] == Display.MKonto5.Pin[3])
      {_anmeldungErfolgreich();} 
      else {
         Display.Pin1.setzeAusgabetext(""); //Setzt eingegebene Werte zurueck falls der Pin falsch ist
         pin[0] = -1;
         pin[1] = -1;
         pin[2] = -1;
         pin[3] = -1;
         aktuellePos = 0;
         login_versuche++;
         if(login_versuche >= 3){
             ATM.starteAlarm();
         }
      }
    }
            
    public void pinAendern() //Zeigt nur manche Objekte an; Eingeben eines neuen Pins mit Eingabefeldern
    {
      xy();
        S.allesAusblenden();
      Display.Taste5.setzeGroesse(2*x/9,x/20); //Anzeigen der Taste fuer Hauptmenue
      Display.Taste6.setzeGroesse(x/3,y/10); //Anzeigen der Taste fuer Bestaetigen     
      Display.TasteAbmelden.setzeGroesse(2*x/9,x/20); //Anzeigen des Abmelden-Taste      
      Display.Pin1.setzeGroesse(4*x/20,y/20); //Anzeigen des ersten Eingabefelds
      Display.Pin1.setzeAusgabetext("");
      pin[0] = -1;
      pin[1] = -1;
      pin[2] = -1;
      pin[3] = -1;
      aktuellePos = 0;
      Display.pinAendern.setzeGroesse(x,y/10); //Anzeigen des Textfeldes
      Display.update_pin = true;
    } 
    
    public void pinAendernBest() //Liest Werte in den Eingabefeldern ein und speichert sie
    {
        if(Display.Kontoangemeldet == 0) //Prueft in welches Konto angemeldet ist um zu wissen welchen Pin es veraendern soll
        {
        Display.MKonto1.Pinsetzen(pin);
        _pinAendernErfolgreich();
      }
        if(Display.Kontoangemeldet == 1) //Prueft in welches Konto angemeldet ist um zu wissen welchen Pin es veraendern soll
        {
        Display.MKonto2.Pinsetzen(pin);
        _pinAendernErfolgreich();
      }
        if(Display.Kontoangemeldet == 2) //Prueft in welches Konto angemeldet ist um zu wissen welchen Pin es veraendern soll
        {
        Display.MKonto3.Pinsetzen(pin);
        _pinAendernErfolgreich();
      }
        if(Display.Kontoangemeldet == 3) //Prueft in welches Konto angemeldet ist um zu wissen welchen Pin es veraendern soll
        {
        Display.MKonto4.Pinsetzen(pin);
        _pinAendernErfolgreich();
      }
        if(Display.Kontoangemeldet == 4) //Prueft in welches Konto angemeldet ist um zu wissen welchen Pin es veraendern soll
        {
        Display.MKonto5.Pinsetzen(pin);
        _pinAendernErfolgreich();
      }
    }
    
}
