import java.awt.event.*;
/**
 * Gruppe: Sven,Marc,Andreas,Michael
 * Autor: Michael Doeberl (Simple edits: Sven Gebhard)
 * Der Pin ist Anfangs 0000
 * Die x/y/b/h Angaben sind so komisch da sie immer von x/y abhaengig sein muessen. -->  530 ~ x/2+2*x/33
 * Die Parameter von Display() sind x-Position, y-Position,Breite, Hoehe
 * Wenn moeglich x/y > 750 setzen, da sonst bei manchen Tasten skalierungsprobleme auftreten
 * Wenn moeglich x=y, da sonst der Hintergrund nicht mehr passt, da dieser Quadratisch ist und alle Schriftgroessen von x abhaengig sind
 * Das Konto wird im Konstruktor am Anfang auf 100000 gesetzt
 * Die Fenstergroesse ist x+50/y+60, wenn man den Rahmen entfernt muss diese zu x/y gesetzt werden (Zeile 81)
 * Wer Schreib-/Grammatikfehler findet darf sie behalten
 * 
 * Sven, wenn du magst kannst du versuchen den KeyListener nach dem Abmelden beim zweiten Anmelden zum
 * laufen zu bringen. Ich habs nicht geschafft. Habe dafuer Eingabefelder gemacht.
 * Wenn man den Keylistener manuell startet funktioniert es, wenn man es mit einer Taste versucht nicht
 *  (Auch nicht wenn man zuerst eine Funktion in einer anderen Klasse startet, die den KeyListener startet)
 * 
 */

public class Display //implements KeyListener
{
    public Zeichnung Zeichnung; //Zeichenebene
    public Bild Hintergrund; //Hintergrund mit Farbverlauf
    public static Taste TasteKontoauszug,Taste1,Taste11,Taste2,Taste21,Taste3,Taste31,Taste4,Taste5,Taste6,Taste7,Taste8,TasteAusBes,TasteEinBes,TasteAbmelden; //Tasten
    public static Segment8x Anzeige; //Siebensegmentreihe
    public static Ausgabe Kontotext,Gesperrt,pinTipp,Kontonummertext,pinAendern,anderesGesperrt,geldAus,Abheben,Einzahlen,Stern1,Stern2,Stern3,Stern4,Kontoauszug, geld_einheit, Kontonummer, Pin, GeldBetragAnzeige; //Textfelder und Sterne
    public static int GeldAbheben,DevisenAbheben,DeGe,x,y,pos_x,pos_y,counter,counter1,counter2,counter3,counter4,counter5,counterS,a,b,K,sternCounter,A,B,C,D,Kontrolle,Kontoangemeldet; //diverse Variablen
    public static Eingabefeld eingabeAbheben,pin1,pin2,pin3,pin4,Pin1,Pin2,Pin3,Pin4; //Eingabefelder
    public static int EingegebenerPinAusgabe[] = {-1,-1,-1,-1}; //Werte -1, da der KeyCode -1 nicht Existiert und beim weglassen eine Fehlermeldung kommt
    public static Rechteck pinHintergrund,kontoHintergrund; //Weisser Hintergrund fuer Passworteingabe und fuer Kontostandsanzeige  
    public static Konto MKonto1,MKonto2,MKonto3,MKonto4,MKonto5;
    public static boolean update_konto, update_kontonummer, update_pin, kreditkarteGesperrt;
    
    public static D_Kontonummerzeugs KonZ;
    public static D_Standartfkt S;
    public static D_Pinzeugs P;
    public static D_Kontozeugs Kon;
    
    private CInputController _inputController = new CInputController();
    
    public Display(int posx,int posy,int _x,int _y, Zeichnung z) //erstellt das Display mit x=Breite und y=Hoehe
    {       
        x = _x; //Setzt x gleich der eingegebenen Zahl (Breite)
        y = _y; //Setzt y gleich der eingegebenen Zahl (Hoehe)
        pos_x = posx; //Setzt pos_x gleich der eingegebenen Zahl (x Position)
        pos_y = posy; //Setzt pos_y gleich der eingegebenen Zahl (y Position)
         
        KonZ = new D_Kontonummerzeugs();
        S    = new D_Standartfkt();
        P    = new D_Pinzeugs();
        Kon  = new D_Kontozeugs();
        
        Zeichnung =z; //Erstellt leere Zeichenebene
        Hintergrund = new Bild("hintergrund.jpg");  //Erstellen des  Hintergrunds
        pinHintergrund = new Rechteck(x/2-x/20+pos_x,y/2+y/13+pos_y,1000*x/6666,1000*x/16666); //Hintergrund fuer Sterne bei Pineingabe im Sperrbildschirm; Grosse Zahlen um Ungenauigkeit von Int auszugleichen         (x,y,b,h)      
        kontoHintergrund = new Rechteck(x/2-28*x/100+pos_x,y/2+y/4-y/200+pos_y,11*x/20,y/10); //Hintergrund fuer Kontostandsanzeige                                                                                      (x,y,b,h)
        Anzeige = new Segment8x(x/6+pos_x,y/2+y/4+pos_y,9,x/8); //Erstellt neue Reihe an Siebensegmenten                                                                                                       (x,y,Stellenzahl,h)
        //Ausgabefelder
        Kontotext = new Ausgabe("Ihr Kontostand beträgt: ",x/2-x/4+pos_x,y/3+pos_y,x,x/10); //Erstellt Schriftzeile ueber Kontostand                                                                                     (Anzeige,x,y,b,h)
        Gesperrt = new Ausgabe("Bitte geben Sie auf dem Tastenfeld ihren Pin ein!",pos_x,y/3+pos_y,x,x/10); //Textfeld fuer Sperrbildschirm                                                                              (Anzeige,x,y,b,h)
        pinTipp = new Ausgabe("(Vierstellig + Enter)",0+pos_x,y/3+y/10+pos_y,x,x/10); //Textfeld fuer Spreebildschirm                                                                                                    (Anzeige,x,y,b,h)
        Kontonummertext = new Ausgabe("Bitte geben Sie auf dem Tastenfeld ihre Kontonummer ein!",0+pos_x,y/3+pos_y,x,x/10); //Textfeld fuer zweite Kontonummereingabe
        anderesGesperrt = new Ausgabe("Bitte geben Sie ihren Pin ein!",x/2-x/3+pos_x,y/3+pos_y,x,x/10); //Textfeld fuer zweiten Sperrbildschirm                                                                          (Anzeige,x,y,b,h)
        geldAus = new Ausgabe("Ihr Guthaben reicht nicht um den eingegebenen Betrag abzuheben",x/80+pos_x,y/3+y/15+pos_y,x,x/10); //Textfeld fuer Geldabheben                                                            (Anzeige,x,y,b,h)
        Kontoauszug = new Ausgabe("Ihr Kontoauszug wird Ausgedruckt. Bitte warten sie einen Moment!",pos_x,y/3+pos_y,x,x/10);
        pinAendern = new Ausgabe("Bitte geben Sie Ihren neuen Pin ein!",x/10+pos_x,y/5+y/100+pos_y,x,y/10); //Textfeld ueber Pineingabe                                                                                  (Anzeige,x,y,b,h)
        Stern1 = new Ausgabe("*",x/2-x/33+pos_x,y/2+y/15+pos_y,x/10,x/10); //Sterne fuer Passworteingabe                                                                                                                 (Anzeige,x,y,b,h)
        Stern2 = new Ausgabe("*",x/2+pos_x,y/2+y/15+pos_y,x/10,x/10); //Sterne fuer Passworteingabe                                                                                                                      (Anzeige,x,y,b,h)
        Stern3 = new Ausgabe("*",x/2+x/33+pos_x,y/2+y/15+pos_y,x/10,x/10); //Sterne fuer Passworteingabe                                                                                                                 (Anzeige,x,y,b,h)
        Stern4 = new Ausgabe("*",x/2+2*x/33+pos_x,y/2+y/15+pos_y,x/10,x/10); //Sterne fuer Passworteingabe                                                                                                               (Anzeige,x,y,b,h)
        Abheben = new Ausgabe("Abheben",x/2-x/10+pos_x,-y/200+pos_y,x/3,y/20); //Textfeld fuer Erinnerung                                                                                                                (Anzeige,x,y,b,h)
        Einzahlen = new Ausgabe("Einzahlen",x/2-x/10+pos_x,-y/200+pos_y,x/3,y/20); //Textfeld fuer Erinnerung                                                                                                            (Anzeige,x,y,b,h)
        geld_einheit = new Ausgabe("€",23*x/100+pos_x+11*x/20,y/2+y/4+pos_y,100,x/10); // Textfeld fuer die Einheit des Geldes 
        Kontonummer = new Ausgabe("",0,0);
        Pin = new Ausgabe("",0,0);
        GeldBetragAnzeige = new Ausgabe("",0,0);
        
        //Eingabefelder
        pin1 = new Eingabefeld("",x/20,y/20); //neues Eingabefeld fuer Festlegung der ersten Stelle des Pins                                                                                                             (Anzeige,b,h)
        pin2 = new Eingabefeld("",x/20,y/20); //neues Eingabefeld fuer Festlegung der zweiten Stelle des Pins                                                                                                            (Anzeige,b,h)
        pin3 = new Eingabefeld("",x/20,y/20); //neues Eingabefeld fuer Festlegung der dritten Stelle des Pins                                                                                                            (Anzeige,b,h)
        pin4 = new Eingabefeld("",x/20,y/20); //neues Eingabefeld fuer Festlegung der vierten Stelle des Pins                                                                                                            (Anzeige,b,h)
        Pin1 = new Eingabefeld("",x/20,y/20); //neues Eingabefeld fuer erste Stelle Passwortabfrage                                                                                                                      (Anzeige,b,h)
        Pin2 = new Eingabefeld("",x/20,y/20); //neues Eingabefeld fuer zweite Stelle Passwortabfrage                                                                                                                     (Anzeige,b,h)
        Pin3 = new Eingabefeld("",x/20,y/20); //neues Eingabefeld fuer dritte Stelle Passwortabfrage                                                                                                                     (Anzeige,b,h)
        Pin4 = new Eingabefeld("",x/20,y/20); //neues Eingabefeld fuer vierte Stelle Passwortabfrage                                                                                                                     (Anzeige,b,h)
        eingabeAbheben = new Eingabefeld(); //Erstellt neues Eingabefeld um Konto veraendern zu koennen
        //Kontos
        MKonto1 = new Konto();
        MKonto2 = new Konto();
        MKonto3 = new Konto();
        MKonto4 = new Konto();
        MKonto5 = new Konto();
        
        //Tasten              
        Taste1 = new Taste("Geld Abheben (€)",x*1/6+pos_x,y/10+pos_y,x*1/3,y/10); //Erstellen von Taste1 fuer Geldabheben und Anpassen der Groesse abhaengig von x/y
        Taste11 = new Taste("Devisen Abheben ($)",x*3/6+pos_x,y/10+pos_y,x*1/3,y/10); //Erstellen von Taste1 fuer Geldabheben und Anpassen der Groesse abhaengig von x/y
        Taste2 = new Taste("Geld Einzahlen (€)",x*1/6+pos_x,y/5+y/100+pos_y,x*1/3,y/10); //Erstellen von Taste2 fuer Geldeinzahlen und Anpassen der Groesse abhaengig von x/y
        Taste21 = new Taste("Devisen Einzahlen ($)",x*3/6+pos_x,y/5+y/100+pos_y,x*1/3,y/10); //Erstellen von Taste2 fuer Geldeinzahlen und Anpassen der Groesse abhaengig von x/y
        Taste3 = new Taste("Kontostand Anzeigen (€)",x*1/6+pos_x,y/3-y/75+pos_y,x*1/3,y/10); //Erstellen von Taste3 fuer Kontostandanzeige und Anpassen der Groesse abhaengig von x/y
        Taste31 = new Taste("Devisen Anzeigen ($)",x*3/6+pos_x,y/3-y/75+pos_y,x*1/3,y/10); //Erstellen von Taste2 fuer Geldeinzahlen und Anpassen der Groesse abhaengig von x/y
        Taste4 = new Taste("Pin aendern",x/2-x/6+pos_x,y/2-y/14+pos_y,x/3,y/10); //Erstellen von Taste4 fuer Pinaenderung und Anpassen der Groesse abhaengig von x/y
        Taste5 = new Taste("Hauptmenue",0+pos_x,0+pos_y,15*x/100,x/20); //Erstellen von Taste5 um Hauptmenue anzuzeigen und Anpassen der Groesse abhaengig von x/y
        Taste6 = new Taste("Bestaetigen",x/2-x/6+pos_x,y/2+y/5+pos_y,x/3,y/10); //Erstellen von Taste6 zur Bestaetigung der Pinaenderung und Anpassen der Groesse abhaengig von x/y
        Taste7 = new Taste("Bestaetigen",x/2-x/6+pos_x,y/2+y/5+pos_y,x/3,y/10); //Erstellen von Taste7 zur Bestaetigung der Anmeldung und Anpassen der Groesse abhaengig von x/y
        Taste8 = new Taste("Bestaetigen",x/2-x/6+pos_x,y/2+y/5+pos_y,x/3,y/10); //Erstellen von Taste8 zur Bestaetigung der Kontonummer und Anpassen der Groesse abhaengig von x/y
        TasteAusBes = new Taste("Bestaetigen",x/2-x/6+pos_x,y/2+pos_y,x/3,y/10); //Erstellen von TasteAusBestaetigen fuer Auszahlbestaetigung und Anpassen der Groesse abhaengig von x/y
        TasteEinBes = new Taste("Bestaetigen",x/2-x/6+pos_x,y/2+pos_y,x/3,y/10);//Erstellen von TasteEinBestaetigen fuer Einzahlbesetaetigung und Anpassen der Groesse abhaengig von x/y
        TasteAbmelden = new Taste("Abmelden",7*x/9+pos_x,0+pos_y,2*x/9,x/20); //Erstellen von TasteAbheben fuer Abmelden und Anpassen der Groesse abhaengig von x/y
        TasteKontoauszug = new Taste("Kontoauszug",x/3+pos_x,0+pos_y,x/3,x/20); // Erstellen von TasteKontoauszug fuer das drucken des Kontoauszuges
        
        //Hintergrundbild
        Zeichnung.setzeFenstergroesse(x+50,y+60); //Passt Fenstergroesse auf Hintergrund an um diesen nicht zu verdecken
        Hintergrund.einpassen(); //Einpassen des Hintergrunds
        Hintergrund.setzeDimensionen(pos_x,pos_y,x,y); //Groesse des Hintergrunds anpassen       
        kontoHintergrund.setzeFarbe("weiss");
              
        Taste1.setzeSchriftgroesse(x/45); //Anpassen der Schriftgroesse von Taste1
        Taste11.setzeSchriftgroesse(x/45); //Anpassen der Schriftgroesse von Taste1
        Taste2.setzeSchriftgroesse(x/45); //Anpassen der Schriftgroesse von Taste2
        Taste21.setzeSchriftgroesse(x/45); //Anpassen der Schriftgroesse von Taste2
        Taste3.setzeSchriftgroesse(x/45); //Anpassen der Schriftgroesse von Taste3
        Taste31.setzeSchriftgroesse(x/45); //Anpassen der Schriftgroesse von Taste3
        Taste4.setzeSchriftgroesse(x/45); //Anpassen der Schriftgroesse von Taste4
        Taste5.setzeSchriftgroesse(x/50); //Anpassen der Schriftgroesse von Taste5
        Taste6.setzeSchriftgroesse(x/50); //Anpassen der Schriftgroesse von Taste6
        Taste7.setzeSchriftgroesse(x/50); //Anpassen der Schriftgroesse von Taste7
        Taste8.setzeSchriftgroesse(x/50); //Anpassen der Schriftgroesse von Taste8
        TasteAusBes.setzeSchriftgroesse(x/50); //Anpassen der Schriftgroesse von TasteAusBes
        TasteEinBes.setzeSchriftgroesse(x/50); //Anpassen der Schriftgroesse von TasteEinBes
        TasteAbmelden.setzeSchriftgroesse(x/50); //Anpassen der Schriftgroesse von TasteAbmelden
        Stern1.setzeSchriftgroesse(x/20); //Legt Groesse der Passwortsterne Fest
        Stern2.setzeSchriftgroesse(x/20); //Legt Groesse der Passwortsterne Fest
        Stern3.setzeSchriftgroesse(x/20); //Legt Groesse der Passwortsterne Fest
        Stern4.setzeSchriftgroesse(x/20); //Legt Groesse der Passwortsterne Fest
        pinAendern.setzeSchriftgroesse(x/20); //Legt Schriftgroesse des Textfelds fuer Pineingabe fest 
        Kontotext.setzeSchriftgroesse(x/20); //Legt Schriftgroesse des Textes bei Abheben/Einzahlen/Kontostandanzeige fest       
        Kontonummertext.setzeSchriftgroesse(x/27); //Legt Schriftgroesse des Textes bei Kontonummerabfrage fest
        Gesperrt.setzeSchriftgroesse(2222*x/49000); //Anpassen der Schriftgroesse fuer Pineingabe, grosse Zahlen um Ungenauigkeit von Int auszugleichen
        anderesGesperrt.setzeSchriftgroesse(2222*x/49000); //Anpassen der Schriftgroesse fuer zweite Pineingabe, grosse Zahlen um Ungenauigkeit von Int auszugleichen
        pinTipp.setzeSchriftgroesse(2222*x/49000); //Anpassen der Schriftgroesse fuer unteres Textfelds bei Pineingabe
        Abheben.setzeSchriftgroesse(x/20); //Anpassen der Schriftgroesse 
        Einzahlen.setzeSchriftgroesse(x/20); //Anpassen der Schriftgroesse
        geldAus.setzeSchriftgroesse(x/30); //Anpassen der Schriftgroesse
        Kontoauszug.setzeSchriftgroesse(x/30); //Anpassen der Schriftgroesse
        TasteKontoauszug.setzeSchriftgroesse(x/50);
        geld_einheit.setzeSchriftgroesse(x/10);
        
        pin1.setzeDimensionen(x/2-x/5+2*x/20+pos_x,y/2+pos_y,x/20,y/20); //Legt Dimensionen (x,y,b,h) der Eingabefelder fuer die Festlegung des Pins fest
        pin2.setzeDimensionen(x/2-x/5+3*x/20+pos_x,y/2+pos_y,x/20,y/20); //Legt Dimensionen (x,y,b,h) der Eingabefelder fuer die Festlegung des Pins fest
        pin3.setzeDimensionen(x/2-x/5+4*x/20+pos_x,y/2+pos_y,x/20,y/20); //Legt Dimensionen (x,y,b,h) der Eingabefelder fuer die Festlegung des Pins fest
        pin4.setzeDimensionen(x/2-x/5+5*x/20+pos_x,y/2+pos_y,x/20,y/20); //Legt Dimensionen (x,y,b,h) der Eingabefelder fuer die Festlegung des Pins fest
        Pin1.setzeDimensionen(x/2-x/5+2*x/20+pos_x,y/2+pos_y,x/20,y/20); //Legt Dimensionen (x,y,b,h) der Eingabefelder fuer Passworteingabe fest
        Pin2.setzeDimensionen(x/2-x/5+3*x/20+pos_x,y/2+pos_y,x/20,y/20); //Legt Dimensionen (x,y,b,h) der Eingabefelder fuer Passworteingabe fest
        Pin3.setzeDimensionen(x/2-x/5+4*x/20+pos_x,y/2+pos_y,x/20,y/20); //Legt Dimensionen (x,y,b,h) der Eingabefelder fuer Passworteingabe fest
        Pin4.setzeDimensionen(x/2-x/5+5*x/20+pos_x,y/2+pos_y,x/20,y/20); //Legt Dimensionen (x,y,b,h) der Eingabefelder fuer Passworteingabe fest        
        eingabeAbheben.setzeDimensionen(x/2-15*x/50/2+pos_x,y/3+pos_y,15*x/50,5*x/100); //Legt Dimensionen (x,y,b,h) des Eingabefelds fuer Abheben/Einzahlen fest
        
        
        MKonto1.Kontonummer[0] = 0;MKonto1.Kontonummer[1] = 0;MKonto1.Kontonummer[2] = 0;MKonto1.Kontonummer[3] = 0;
        MKonto2.Kontonummer[0] = 1;MKonto2.Kontonummer[1] = 1;MKonto2.Kontonummer[2] = 1;MKonto2.Kontonummer[3] = 1;
        MKonto3.Kontonummer[0] = 2;MKonto3.Kontonummer[1] = 2;MKonto3.Kontonummer[2] = 2;MKonto3.Kontonummer[3] = 2;
        MKonto4.Kontonummer[0] = 3;MKonto4.Kontonummer[1] = 3;MKonto4.Kontonummer[2] = 3;MKonto4.Kontonummer[3] = 3;
        MKonto5.Kontonummer[0] = 4;MKonto5.Kontonummer[1] = 4;MKonto5.Kontonummer[2] = 4;MKonto5.Kontonummer[3] = 5;
        
        _inputController.knopfHinzufuegen(Taste1, 1);
        _inputController.knopfHinzufuegen(Taste2, 2);
        _inputController.knopfHinzufuegen(Taste3, 3);
        _inputController.knopfHinzufuegen(Taste4, 4);
        _inputController.knopfHinzufuegen(Taste5, 5);
        _inputController.knopfHinzufuegen(TasteAusBes, 6);
        _inputController.knopfHinzufuegen(TasteEinBes, 7);
        _inputController.knopfHinzufuegen(Taste6, 8);
        _inputController.knopfHinzufuegen(TasteAbmelden, 9);
        _inputController.knopfHinzufuegen(Taste7, 10);
        _inputController.knopfHinzufuegen(Taste8, 11);
        _inputController.knopfHinzufuegen(Taste11, 12);
        _inputController.knopfHinzufuegen(Taste21, 13);
        _inputController.knopfHinzufuegen(Taste31, 14);
        _inputController.knopfHinzufuegen(TasteKontoauszug, 15);
               
        counter = 0; //setzt Counter fuer Passwortabfrage auf 0
        counter1 = 0; //setze counter1 fuer Kontonummereingabe auf 0
        GeldAbheben = 0; //setzt GeldAbheben gleich 0 um Probleme beim Aufrufen von geldAbheben()/geldEinzahlen() zu verhindern
        sternCounter = 0; //setzt angezeigte Sterne bei Passworteingabe auf 0
        
        update_pin = false;
        update_konto = false;
        update_kontonummer = false;
        
        //KonZ.Kontonummereingabe(); //Startet das Display
        KonZ.andereKontonummereingabe();
    }
   
    public void update()
    {
        if(_inputController.wurdeGedrueckt(Taste1)){
            Kon.geldAbheben();
            _inputController.resetStatus(Taste1);
        }else if(_inputController.wurdeGedrueckt(Taste2)){
            Kon.geldEinzahlen();
            _inputController.resetStatus(Taste2);
        }else if(_inputController.wurdeGedrueckt(Taste3)){
            Kon.kontostandAnzeigen();
            _inputController.resetStatus(Taste3);
        }else if(_inputController.wurdeGedrueckt(Taste4)){
            P.pinAendern();
            _inputController.resetStatus(Taste4);
        }else if(_inputController.wurdeGedrueckt(Taste5)){
            S.hauptmenueAnzeigen();
            _inputController.resetStatus(Taste5);
        }else if(_inputController.wurdeGedrueckt(TasteAusBes)){
            if(DeGe == 0){
                Kon.geldAbheben();
                Kon.abhebenBest(); //Um Kontoanzeige zu aktualisieren
            }if(DeGe == 1){   
               Kon.DevisenAbheben();
               Kon.DevisenAbhebenBest();
            }
            _inputController.resetStatus(TasteAusBes);
        }else if(_inputController.wurdeGedrueckt(TasteEinBes)){
            if(DeGe == 0){
                Kon.geldEinzahlen();
                Kon.einzahlenBest(); //Um Kontoanzeige zu aktualisieren
            }if(DeGe == 1){
                Kon.DevisenEinzahlen();
                Kon.DevisenEinzahlenBest();
            }
            _inputController.resetStatus(TasteEinBes);
        }else if(_inputController.wurdeGedrueckt(Taste6)){
            P.pinAendernBest();
            _inputController.resetStatus(Taste6);
        }else if(_inputController.wurdeGedrueckt(TasteAbmelden)){
            Pin1.setzeAusgabetext("");
            Pin2.setzeAusgabetext("");
            Pin3.setzeAusgabetext("");
            Pin4.setzeAusgabetext("");
            if(ATM.getKKStatus())
                ATM.KKlogout();
            KonZ.andereKontonummereingabe();
            _inputController.resetStatus(TasteAbmelden);
        }else if(_inputController.wurdeGedrueckt(Taste7)){
            P.Anmeldebestaetigung();
            _inputController.resetStatus(Taste7);
        }else if(_inputController.wurdeGedrueckt(Taste8)){
            KonZ.andereKontonummereingabebest();
            _inputController.resetStatus(Taste8);
        }else if(_inputController.wurdeGedrueckt(Taste11)){
            Kon.DevisenAbheben();
            _inputController.resetStatus(Taste11);
        }else if(_inputController.wurdeGedrueckt(Taste21)){
            Kon.DevisenEinzahlen();
            _inputController.resetStatus(Taste21);
        }else if(_inputController.wurdeGedrueckt(Taste31)){
            Kon.DevisenKontostandAnzeigen();
            _inputController.resetStatus(Taste31);
        }else if(_inputController.wurdeGedrueckt(TasteKontoauszug)){
            Kon.Kontoauszug();
            _inputController.resetStatus(TasteKontoauszug);
        }
    }
  
    public void sendKeyEvent(int ID){
        _inputController.tuWas(ID);
    }
    
    public void loginKontonummer(int konto){
        Kontoangemeldet = konto;
        P.anderePinAbfrageAnzeigen();
    }
    
    public void KontoAbmelden(){
        KonZ.andereKontonummereingabe();
    }
    
    public static void handle_tastenfeldInput(int ziffer) {
        if(update_konto){
            Kon.fuegeBetragZifferHinzu(ziffer);
        }else if(update_kontonummer){
            KonZ.fuegeKontonummerZifferHinzu(ziffer);
        }else if(update_pin){
            P.fuegePinZifferHinzu(ziffer);
        }
    }
    
   
    public void sterneRuecksetzen() //soll ueberaus boeckische Sterne ausblenden (╯°□°)╯︵ ┻━┻
    {
      sternCounter = 0;
      Stern1.setzeGroesse(0,0);
      Stern2.setzeGroesse(0,0);
      Stern3.setzeGroesse(0,0);
      Stern4.setzeGroesse(0,0);   
    }
}