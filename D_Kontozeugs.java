public class D_Kontozeugs
{
    public D_Standartfkt S = new D_Standartfkt();
    public int x,y,pos_x,pos_y, aktuelleposition;
    public int geldBetrag[];
    
    public D_Kontozeugs(){
        geldBetrag = new int[9];
        geldBetrag[0] = -1;
        geldBetrag[1] = -1;
        geldBetrag[2] = -1;
        geldBetrag[3] = -1;
        geldBetrag[4] = -1;
        geldBetrag[5] = -1;
        geldBetrag[6] = -1;
        geldBetrag[7] = -1;
        geldBetrag[8] = -1;
        aktuelleposition = 0;
    }   
    
    public void xy()
    {
        x = Display.x;
        y = Display.y;
        pos_x = Display.pos_x;
        pos_y = Display.pos_y;
    }
    
    public void fuegeBetragZifferHinzu(int ziffer){
        if(aktuelleposition < 9 && ziffer >= 0 && ziffer <= 9){
            geldBetrag[aktuelleposition] = ziffer;
            aktuelleposition++;
            Display.eingabeAbheben.setzeAusgabetext(Display.eingabeAbheben.leseText() + ziffer);
        }else if(ziffer == -1 && aktuelleposition > 0){
            aktuelleposition--;
            geldBetrag[aktuelleposition] = -1;
            Display.eingabeAbheben.setzeAusgabetext(Display.eingabeAbheben.leseText().substring(0, aktuelleposition));
        }
    }
    
    public void geldAbheben() //Zeigt nur manche Objekte an; zeigt ein Feld an, in welches man den gewuenschten Betrag eingeben kann und liest es aus
    {      
        xy();
        S.allesAusblenden();
        Display.Taste5.setzeGroesse(2*x/9,x/20); //Anzeigen der Hauptmenuetaste
        Display.TasteAusBes.setzeGroesse(x/3,y/10); //Anzeigen der Bestaetigungstaste
        Display.TasteAbmelden.setzeGroesse(2*x/9,x/20); //Anzeigen der Abmelden-Taste
        Display.Anzeige.setzeGroesse(x/10); //Anzeigen des Siebensegments
        Display.Anzeige.anzeige(Kontozahlausgabe()); //Anzeigen des Kontostandes auf der Siebensegmentreihe
        Display.kontoHintergrund.setzeGroesse(11*x/20,y/10);//Anzeigen des weissen Kastens hinter dem Siebensegment
        Display.Kontotext.setzeDimensionen(x/2-x/4+pos_x,y/2+y/7+pos_y,x,x/10); //Anzeigen des Textfeldes
        Display.Abheben.setzeGroesse(x/3,y/20); //Anzeigen des Textfeldes 
        Display.eingabeAbheben.setzeGroesse(15*x/50,5*x/100); //Anzeigen des Eingabefeldes      
        Display.GeldAbheben = Display.eingabeAbheben.leseInteger(0); //Auslesen des Eingabefeldes fuer Display.GeldAbheben
        Display.eingabeAbheben.setzeAusgabetext(""); //Setzt vorher eingegebene Geldbetraege zurueck 
        Display.geld_einheit.setzeAusgabetext("€");
        Display.geld_einheit.setzeDimensionen(23*x/100+pos_x+11*x/20,y/2+y/4+pos_y,100,x/10);
        Display.DeGe = 0;
        geldBetrag[0] = -1;
        geldBetrag[1] = -1;
        geldBetrag[2] = -1;
        geldBetrag[3] = -1;
        geldBetrag[4] = -1;
        geldBetrag[5] = -1;
        geldBetrag[6] = -1;
        geldBetrag[7] = -1;
        geldBetrag[8] = -1;
        aktuelleposition = 0;
        Display.update_konto = true;
    }
    public void abhebenBest() //Gleicht Kontostand an, wird durch Taste ausgeloest, jenachdem welches Konto angemeldet ist
    {
        xy();
        if(Display.Kontoangemeldet == 0){ //Prueft welches Konto angemeldet ist
        if(Display.MKonto1.Guthaben >= Display.GeldAbheben && Display.GeldAbheben >= 0){Display.MKonto1.Guthaben = Display.MKonto1.Guthaben - Display.GeldAbheben; ATM.geld_auszahlen(Display.GeldAbheben);} //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.Guthaben < Display.GeldAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto1.Guthaben); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
       if(Display.Kontoangemeldet == 1){ //Prueft welches Konto angemeldet ist
        if( Display.MKonto2.Guthaben >= Display.GeldAbheben && Display.GeldAbheben >= 0){Display.MKonto2.Guthaben = Display.MKonto2.Guthaben - Display.GeldAbheben; ATM.geld_auszahlen(Display.GeldAbheben);} //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.Guthaben < Display.GeldAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto2.Guthaben); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
       if(Display.Kontoangemeldet == 2){ //Prueft welches Konto angemeldet ist
        if( Display.MKonto3.Guthaben >= Display.GeldAbheben && Display.GeldAbheben >= 0){Display.MKonto3.Guthaben = Display.MKonto3.Guthaben - Display.GeldAbheben; ATM.geld_auszahlen(Display.GeldAbheben);} //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.Guthaben < Display.GeldAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto3.Guthaben); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
       if(Display.Kontoangemeldet == 3){ //Prueft welches Konto angemeldet ist
        if( Display.MKonto4.Guthaben >= Display.GeldAbheben && Display.GeldAbheben >= 0){Display.MKonto4.Guthaben = Display.MKonto4.Guthaben - Display.GeldAbheben; ATM.geld_auszahlen(Display.GeldAbheben);} //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.Guthaben < Display.GeldAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto4.Guthaben); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
       if(Display.Kontoangemeldet == 4){ //Prueft welches Konto angemeldet ist
        if( Display.MKonto5.Guthaben >= Display.GeldAbheben && Display.GeldAbheben >= 0){Display.MKonto5.Guthaben = Display.MKonto5.Guthaben - Display.GeldAbheben; ATM.geld_auszahlen(Display.GeldAbheben);} //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.Guthaben < Display.GeldAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto5.Guthaben); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
    }
    
    public void DevisenAbheben()
    {
        xy();
        S.allesAusblenden();
        Display.Taste5.setzeGroesse(2*x/9,x/20); //Anzeigen der Hauptmenuetaste
        Display.TasteAusBes.setzeGroesse(x/3,y/10); //Anzeigen der Bestaetigungstaste
        Display.TasteAbmelden.setzeGroesse(2*x/9,x/20); //Anzeigen der Abmelden-Taste
        Display.Anzeige.setzeGroesse(x/10); //Anzeigen des Siebensegments
        Display.Anzeige.setzeFarbe("lila");
        Display.Anzeige.anzeige(Devisenzahlausgabe()); //Anzeigen des Kontostandes auf der Siebensegmentreihe
        Display.kontoHintergrund.setzeGroesse(11*x/20,y/10);//Anzeigen des weissen Kastens hinter dem Siebensegment
        Display.Kontotext.setzeDimensionen(x/2-x/4+pos_x,y/2+y/7+pos_y,x,x/10); //Anzeigen des Textfeldes
        Display.Abheben.setzeGroesse(x/3,y/20); //Anzeigen des Textfeldes 
        Display.eingabeAbheben.setzeGroesse(15*x/50,5*x/100); //Anzeigen des Eingabefeldes      
        Display.DevisenAbheben = Display.eingabeAbheben.leseInteger(0); //Auslesen des Eingabefeldes fuer Display.GeldAbheben
        Display.eingabeAbheben.setzeAusgabetext(""); //Setzt vorher eingegebene Geldbetraege zurueck   
        Display.geld_einheit.setzeAusgabetext("$");
        Display.geld_einheit.setzeDimensionen(23*x/100+pos_x+11*x/20,y/2+y/4+pos_y,100,x/10);
        Display.DeGe = 1;
        geldBetrag[0] = -1;
        geldBetrag[1] = -1;
        geldBetrag[2] = -1;
        geldBetrag[3] = -1;
        geldBetrag[4] = -1;
        geldBetrag[5] = -1;
        geldBetrag[6] = -1;
        geldBetrag[7] = -1;
        geldBetrag[8] = -1;
        aktuelleposition = 0;
        Display.update_konto = true;
    }
    public void DevisenAbhebenBest() //Gleicht Kontostand an, wird durch Taste ausgeloest, jenachdem welches Konto angemeldet ist
    {
        xy();
        if(Display.Kontoangemeldet == 0){ //Prueft welches Konto angemeldet ist
        if(Display.MKonto1.GuthabenDevisen >= Display.DevisenAbheben && Display.DevisenAbheben >= 0){Display.MKonto1.GuthabenDevisen = Display.MKonto1.GuthabenDevisen - Display.DevisenAbheben; } //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.GuthabenDevisen < Display.DevisenAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto1.GuthabenDevisen); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
       if(Display.Kontoangemeldet == 1){ //Prueft welches Konto angemeldet ist
        if( Display.MKonto2.GuthabenDevisen >= Display.DevisenAbheben && Display.DevisenAbheben >= 0){Display.MKonto2.GuthabenDevisen = Display.MKonto2.GuthabenDevisen - Display.DevisenAbheben; } //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.GuthabenDevisen < Display.DevisenAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto2.GuthabenDevisen); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
       if(Display.Kontoangemeldet == 2){ //Prueft welches Konto angemeldet ist
        if( Display.MKonto3.GuthabenDevisen >= Display.DevisenAbheben && Display.DevisenAbheben >= 0){Display.MKonto3.GuthabenDevisen = Display.MKonto3.GuthabenDevisen - Display.DevisenAbheben; } //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.GuthabenDevisen < Display.DevisenAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto3.GuthabenDevisen); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
       if(Display.Kontoangemeldet == 3){ //Prueft welches Konto angemeldet ist
        if( Display.MKonto4.GuthabenDevisen >= Display.DevisenAbheben && Display.DevisenAbheben >= 0){Display.MKonto4.GuthabenDevisen = Display.MKonto4.GuthabenDevisen - Display.DevisenAbheben; } //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.GuthabenDevisen < Display.DevisenAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto4.GuthabenDevisen); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
       if(Display.Kontoangemeldet == 4){ //Prueft welches Konto angemeldet ist
        if( Display.MKonto5.GuthabenDevisen >= Display.DevisenAbheben && Display.DevisenAbheben >= 0){Display.MKonto5.GuthabenDevisen = Display.MKonto5.GuthabenDevisen - Display.DevisenAbheben; } //Prueft ob abzuhebender Betrag auf dem Konto vorhanden ist, wenn ja zieht es ihn vom Konto ab
        else if(Display.MKonto1.GuthabenDevisen < Display.DevisenAbheben){Display.geldAus.setzeGroesse(x,x/10);}
        Display.Anzeige.anzeige(Display.MKonto5.GuthabenDevisen); //Anzeigen des Kontostandes von KontoX auf der Siebensegmentreihe
       }
    }
    
    
    public void geldEinzahlen() //Zeigt nur manche Objekte an; zeigt ein Feld an, in welches man den gewuenschten Betrag eingeben kann und liest es aus
    {      
        xy();
        S.allesAusblenden();
        Display.Taste5.setzeGroesse(2*x/9,x/20); //Anzeigen  der Hauptmenuetaste
        Display.TasteEinBes.setzeGroesse(x/3,y/10); //Anzeigen der Bestaetigungstaste
        Display.TasteAbmelden.setzeGroesse(2*x/9,x/20); //Anzeigen der Abmelden-Taste
        Display.Anzeige.setzeGroesse(x/10); //Anzeigen des Siebensegments
        Display.Anzeige.anzeige(Kontozahlausgabe()); //Anzeigen des Kontostandes auf der Siebensegmentreihe        
        Display.kontoHintergrund.setzeGroesse(11*x/20,y/10); //Anzeigen des weissen Kastens hinter dem Siebensegment
        Display.Kontotext.setzeDimensionen(x/2-x/4+pos_x,y/2+y/7+pos_y,x,x/10);//Anzeigen des Textfeldes
        Display.Einzahlen.setzeGroesse(x/3,y/20); //Anzeigen des Textfeldes
        Display.eingabeAbheben.setzeGroesse(15*x/50,5*x/100); //Anzeigen des Eingabefeldes   
        Display.GeldAbheben = Display.eingabeAbheben.leseInteger(0); //Auslesen des Eingabefeldes fuer Geldeinzahlen
        Display.eingabeAbheben.setzeAusgabetext(""); //Setzt vorher eingegebene Geldbetraege zurueck
        //Display.GeldBetragAnzeige.setzeDimensionen(x/2-15*x/50/2+pos_x,y/3+pos_y,15*x/50,5*x/100);
        Display.geld_einheit.setzeAusgabetext("€");
        Display.geld_einheit.setzeDimensionen(23*x/100+pos_x+11*x/20,y/2+y/4+pos_y,100,x/10);
        Display.DeGe = 0;
        geldBetrag[0] = -1;
        geldBetrag[1] = -1;
        geldBetrag[2] = -1;
        geldBetrag[3] = -1;
        geldBetrag[4] = -1;
        geldBetrag[5] = -1;
        geldBetrag[6] = -1;
        geldBetrag[7] = -1;
        geldBetrag[8] = -1;
        aktuelleposition = 0;
        Display.update_konto = true;
    }
    public void einzahlenBest() //Gleicht Kontostand an, wird durch Taste ausgeloest, jenachdem weches Konto angemeldet ist
    {
       xy();
        if(Display.Kontoangemeldet == 0 && Display.GeldAbheben >= 0){
        Display.MKonto1.Guthaben = Display.MKonto1.Guthaben + Display.GeldAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto1.Guthaben); //Anzeigen des Kontostandes auf der Siebensegmentreihe
        ATM.geld_einzahlen(Display.GeldAbheben);
       }
        if(Display.Kontoangemeldet == 1 && Display.GeldAbheben >= 0){
        Display.MKonto2.Guthaben = Display.MKonto2.Guthaben + Display.GeldAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto2.Guthaben); //Anzeigen des Kontostandes auf der Siebensegmentreihe
        ATM.geld_einzahlen(Display.GeldAbheben);
       }
        if(Display.Kontoangemeldet == 2 && Display.GeldAbheben >= 0){
        Display.MKonto3.Guthaben = Display.MKonto3.Guthaben + Display.GeldAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto3.Guthaben); //Anzeigen des Kontostandes auf der Siebensegmentreihe
        ATM.geld_einzahlen(Display.GeldAbheben);
       }
        if(Display.Kontoangemeldet == 3 && Display.GeldAbheben >= 0){
        Display.MKonto4.Guthaben = Display.MKonto4.Guthaben + Display.GeldAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto4.Guthaben); //Anzeigen des Kontostandes auf der Siebensegmentreihe
        ATM.geld_einzahlen(Display.GeldAbheben);
       }
        if(Display.Kontoangemeldet == 4 && Display.GeldAbheben >= 0){
        Display.MKonto5.Guthaben = Display.MKonto5.Guthaben + Display.GeldAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto5.Guthaben); //Anzeigen des Kontostandes auf der Siebensegmentreihe
        ATM.geld_einzahlen(Display.GeldAbheben);
       }
    }
    
    public void DevisenEinzahlen()
    {
        xy();
        S.allesAusblenden();
        Display.Taste5.setzeGroesse(2*x/9,x/20); //Anzeigen  der Hauptmenuetaste
        Display.TasteEinBes.setzeGroesse(x/3,y/10); //Anzeigen der Bestaetigungstaste
        Display.TasteAbmelden.setzeGroesse(2*x/9,x/20); //Anzeigen der Abmelden-Taste
        Display.Anzeige.setzeGroesse(x/10); //Anzeigen des Siebensegments
        Display.Anzeige.setzeFarbe("lila");
        Display.Anzeige.anzeige(Devisenzahlausgabe()); //Anzeigen des Kontostandes auf der Siebensegmentreihe        
        Display.kontoHintergrund.setzeGroesse(11*x/20,y/10); //Anzeigen des weissen Kastens hinter dem Siebensegment
        Display.Kontotext.setzeDimensionen(x/2-x/4+pos_x,y/2+y/7+pos_y,x,x/10);//Anzeigen des Textfeldes
        Display.Einzahlen.setzeGroesse(x/3,y/20); //Anzeigen des Textfeldes
        Display.eingabeAbheben.setzeGroesse(15*x/50,5*x/100); //Anzeigen des Eingabefeldes   
        Display.DevisenAbheben = Display.eingabeAbheben.leseInteger(0); //Auslesen des Eingabefeldes fuer Geldeinzahlen
        Display.eingabeAbheben.setzeAusgabetext(""); //Setzt vorher eingegebene Geldbetraege zurueck  
        Display.geld_einheit.setzeAusgabetext("$");
        Display.geld_einheit.setzeDimensionen(23*x/100+pos_x+11*x/20,y/2+y/4+pos_y,100,x/10);
        Display.DeGe = 1;
        geldBetrag[0] = -1;
        geldBetrag[1] = -1;
        geldBetrag[2] = -1;
        geldBetrag[3] = -1;
        geldBetrag[4] = -1;
        geldBetrag[5] = -1;
        geldBetrag[6] = -1;
        geldBetrag[7] = -1;
        geldBetrag[8] = -1;
        aktuelleposition = 0;
        Display.update_konto = true;
    }
    public void DevisenEinzahlenBest() //Gleicht Kontostand an, wird durch Taste ausgeloest, jenachdem welches Konto angemeldet ist
    {
        xy(); 
        if(Display.Kontoangemeldet == 0 &&Display.DevisenAbheben >= 0){
        Display.MKonto1.GuthabenDevisen = Display.MKonto1.GuthabenDevisen +Display.DevisenAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto1.GuthabenDevisen); //Anzeigen des Kontostandes auf der Siebensegmentreihe
       }
        if(Display.Kontoangemeldet == 1 &&Display.DevisenAbheben >= 0){
        Display.MKonto2.GuthabenDevisen = Display.MKonto2.GuthabenDevisen +Display.DevisenAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto2.GuthabenDevisen); //Anzeigen des Kontostandes auf der Siebensegmentreihe
       }
        if(Display.Kontoangemeldet == 2 &&Display.DevisenAbheben >= 0){
        Display.MKonto3.GuthabenDevisen = Display.MKonto3.GuthabenDevisen +Display.DevisenAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto3.GuthabenDevisen); //Anzeigen des Kontostandes auf der Siebensegmentreihe
       }
        if(Display.Kontoangemeldet == 3 &&Display.DevisenAbheben >= 0){
        Display.MKonto4.GuthabenDevisen = Display.MKonto4.GuthabenDevisen +Display.DevisenAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto4.GuthabenDevisen); //Anzeigen des Kontostandes auf der Siebensegmentreihe
       }
        if(Display.Kontoangemeldet == 4 &&Display.DevisenAbheben >= 0){
        Display.MKonto5.GuthabenDevisen = Display.MKonto5.GuthabenDevisen +Display.DevisenAbheben; //Fuegt eingegebenen Betrag zum Konto hinzu
        Display.Anzeige.anzeige(Display.MKonto5.GuthabenDevisen); //Anzeigen des Kontostandes auf der Siebensegmentreihe
       }
    }
    
    public void kontostandAnzeigen() //Zeigt eine Siebensegmentreihe, die den Kontostand fuer das derzeitige Konto ausgibt, und einen Text an
    {
        xy();
        S.allesAusblenden(); 
        Display.Taste5.setzeGroesse(2*x/9,x/20); //Anzeigen der Hauptmenuetaste
        Display.TasteAbmelden.setzeGroesse(2*x/9,x/20); //Anzeigen der Abmelden-Taste
        Display.Kontotext.setzeGroesse(x,x/10); //Anzeigen des Textfeldes
        Display.Anzeige.setzeGroesse(x/10); //Anzeigen des Siebensegments
        Display.Anzeige.setzePosition(x/2-27*x/100+pos_x,y/2+pos_y); //Verschieben des Siebensegments wegen veraendertem Layout
        Display.kontoHintergrund.setzeDimensionen(x/2-28*x/100+pos_x,y/2-y/200+pos_y,11*x/20,y/10); //Verschieben/aendern der Groesse des weissen Kastens hinter dem Siebensegment wegen veraendertem Layout        
        Display.Anzeige.anzeige(Kontozahlausgabe()); //Anzeigen des Kontostandes auf der Siebensegmentreihe
        Display.geld_einheit.setzeAusgabetext("€");
        Display.geld_einheit.setzeDimensionen(23*x/100+pos_x+11*x/20,y/2+pos_y,x/10,x/10);
    }
     public void DevisenKontostandAnzeigen() //Zeigt eine Siebensegmentreihe, die den Kontostand fuer das derzeitige Konto ausgibt, und einen Text an
    {
        xy();
        S.allesAusblenden(); 
        Display.Taste5.setzeGroesse(2*x/9,x/20); //Anzeigen der Hauptmenuetaste
        Display.TasteAbmelden.setzeGroesse(2*x/9,x/20); //Anzeigen der Abmelden-Taste
        Display.Kontotext.setzeGroesse(x,x/10); //Anzeigen des Textfeldes
        Display.Anzeige.setzeGroesse(x/10); //Anzeigen des Siebensegments
        Display.Anzeige.setzeFarbe("lila");
        Display.Anzeige.setzePosition(x/2-27*x/100+pos_x,y/2+pos_y); //Verschieben des Siebensegments wegen veraendertem Layout
        Display.kontoHintergrund.setzeDimensionen(x/2-28*x/100+pos_x,y/2-y/200+pos_y,11*x/20,y/10); //Verschieben/aendern der Groesse des weissen Kastens hinter dem Siebensegment wegen veraendertem Layout        
        Display.Anzeige.anzeige(Devisenzahlausgabe()); //Anzeigen des Kontostandes auf der Siebensegmentreihe
        Display.geld_einheit.setzeAusgabetext("$");
        Display.geld_einheit.setzeDimensionen(23*x/100+pos_x+11*x/20,y/2+pos_y,x/10,x/10);
    }
    public int Kontozahlausgabe() //Gibt KontoX zurueck, jenachdem welches Konto angemeldet ist
    {
        if(Display.Kontoangemeldet == 0){return Display.MKonto1.Guthaben;}
        else if(Display.Kontoangemeldet == 1){return Display.MKonto2.Guthaben;}
        else if(Display.Kontoangemeldet == 2){return Display.MKonto3.Guthaben;}
        else if(Display.Kontoangemeldet == 3){return Display.MKonto4.Guthaben;}
        else if(Display.Kontoangemeldet == 4){return Display.MKonto5.Guthaben;}
        else return 0;
    }
    public int Devisenzahlausgabe() //Gibt KontoX zurueck, jenachdem welches Konto angemeldet ist
    {
        if(Display.Kontoangemeldet == 0){return Display.MKonto1.GuthabenDevisen;}
        else if(Display.Kontoangemeldet == 1){return Display.MKonto2.GuthabenDevisen;}
        else if(Display.Kontoangemeldet == 2){return Display.MKonto3.GuthabenDevisen;}
        else if(Display.Kontoangemeldet == 3){return Display.MKonto4.GuthabenDevisen;}
        else if(Display.Kontoangemeldet == 4){return Display.MKonto5.GuthabenDevisen;}
        else return 0;
    }
     
    public void Kontoauszug()
    {
        xy();
        S.allesAusblenden();
        Display.Kontoauszug.setzeGroesse(x,x/10);   
        Display.Taste5.setzeGroesse(15*x/100,x/20);
        ATM.KontoauszugDrucken();
    }
}
