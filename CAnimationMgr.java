

/*
 * Autor: Sven Gebhard
 * Ziel der Klasse:
 * Ausführen und anzeigen der Animationen von Geld Aus/Eingabe
 * und der Kreditkarte
 */

import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collections;

public class CAnimationMgr {
        
    /*  Eine Klasse (Struktur) die ein Bild speichert und wie
     *  oft dieses Bild animiert werden muss
     */
    private class Bild_Info{
        public Bild bild;
        public int  times;
    }
    
    /*  Objekt in dem die Anteile der jeweiligen Geldscheine bzw Geldstuecke
     *  gespeichert werden
     */
    private CGeldAnteil _geldAnteil;
    
    /*  Ein Map die alle Geldscheine bzw Geldstuecke und wie oft sie verschoben werden muessen 
     *  speichert um die Aus/Eingabe einfacher zu animieren
     */
    private TreeMap<Double, Bild_Info> _geldBilder_Liste;
    private Bild_Info _tempBildInfo; // Temporaeres Bild_Info Objekt um Objekt in die _geldBilder_Liste zu integrieren
    
    // Bild das unter die Kreditkarte geschoben wird
    private Bild _atmKKAusschnitt;
    // Bild das die Geldscheine überdeckt wenn sie in den atm geschoben werden
    private Bild _atmAusschnitt;
    // Bild der Kreditkarte
    private Bild _kreditkarte_Bild;
    // Bild des Kontoauszuges
    private Bild _kontoauszug_Bild;
    // Grafik der GeldKlappe
    private Rechteck _geldKlappe;
    
    private Bild _tempBild;
    private int x_geldKlappe, y_geldKlappe;
    
    
    // -- Konstruktor --
    // @param x: x-Position an der die Geldklappe gesetzt werden soll
    // @param y: y-Position an der die Geldklappe gesetzt werden soll
    public CAnimationMgr(int x, int y) {
        
        x_geldKlappe = x;
        y_geldKlappe = y;
        
        _geldAnteil = new CGeldAnteil(); // Alloc Memory
        
        _geldBilder_Liste = new TreeMap<Double, Bild_Info>(Collections.reverseOrder()); // Alloc Memory
        
        _atmKKAusschnitt = new Bild("ATM_Kreditkarteausschnitt.png");
        _atmKKAusschnitt.setzeDimensionen(408, 559, 311, 161);
        
        this.lade_Kreditkarte(new Bilddatei("Kreditkarte.jpg", 100, 500)); // Kreditkarte laden und skalieren
        _kreditkarte_Bild.setzeGroesse(100, 500);
        
        _kontoauszug_Bild = new Bild("Kontoauszug.png"); // Alloc Memory
        _kontoauszug_Bild.setzeGroesse(316, 160);
        _kontoauszug_Bild.unsichtbarMachen();
        
        _geldKlappe = new Rechteck();
        _geldKlappe.setzeDimensionen(x_geldKlappe, y_geldKlappe, 50, 170);
        
        // Laden der Bilder der Geldscheine bzw Geldstuecke
        this.lade_Geld(0.01d, new Bilddatei("Geld_Bilder\\1Ct.jpg", 119, 123), 119, 123);
        this.lade_Geld(0.02d, new Bilddatei("Geld_Bilder\\2Ct.jpg", 139, 142), 139, 142);
        this.lade_Geld(0.05d, new Bilddatei("Geld_Bilder\\5Ct.jpg", 157, 160), 157, 160);
        this.lade_Geld(0.10d, new Bilddatei("Geld_Bilder\\10Ct.jpg", 145, 148), 145, 148);
        this.lade_Geld(0.20d, new Bilddatei("Geld_Bilder\\20Ct.jpg", 167, 167), 167, 167);
        this.lade_Geld(0.50d, new Bilddatei("Geld_Bilder\\50Ct.jpg", 177, 183), 177, 183);
        this.lade_Geld(1.d, new Bilddatei("Geld_Bilder\\1Euro.jpg", 172, 174), 172, 174);
        this.lade_Geld(2.d, new Bilddatei("Geld_Bilder\\2Euro.jpg", 192, 193), 192, 193);
        this.lade_Geld(5.d, new Bilddatei("Geld_Bilder\\5Euro.jpg", 474, 250), 474, 250);
        this.lade_Geld(10.d, new Bilddatei("Geld_Bilder\\10Euro.jpg", 491, 261), 491, 261);
        this.lade_Geld(20.d, new Bilddatei("Geld_Bilder\\20Euro.jpg", 513, 281), 513, 281);
        this.lade_Geld(50.d, new Bilddatei("Geld_Bilder\\50Euro.jpg", 507, 283), 507, 283);
        this.lade_Geld(100.d, new Bilddatei("Geld_Bilder\\100Euro.jpg", 562, 300), 562, 300);
        this.lade_Geld(200.d, new Bilddatei("Geld_Bilder\\200Euro.jpg", 582, 311), 582, 311);
        this.lade_Geld(500.d, new Bilddatei("Geld_Bilder\\500Euro.jpg", 622, 316), 622, 316);      
        
    }

    /*  Methode die das Bild der Kreditkarte laed und abspeichert
     *  @param file: Bild das fuer die Kreditkarte verwendent werden soll
     */
    public void lade_Kreditkarte(Bilddatei file)
    {
        if(_kreditkarte_Bild == null) // Pruefen ob schon speicher fuer das bild belegt ist
            _kreditkarte_Bild = new Bild(file); // Speicher reservieren
        else
            _kreditkarte_Bild.wechsleBild(file); // Bild ueberschreiben
        _kreditkarte_Bild.unsichtbarMachen();
    }
    
    /*  Methode die einen Geldscheinem/Geldstueck laedt und in _geldBilderListe einfuegt
     *  @param geld_betrag: Betrag den der Schein/das Stueck hat
     *  @param file       : Bilddatei in der das Bild des Scheins/Stuecks gespeichert ist
     *  @param x_size     : Breite des Bildes
     *  @param y_size     : Hoehe des Bildes
     */
    public void lade_Geld(double geld_betrag, Bilddatei file, int x_size, int y_size)
    {
        _tempBildInfo = new Bild_Info();
        _tempBildInfo.bild = new Bild(file);
        _tempBildInfo.bild.setzeGroesse(x_size / 2, y_size / 2);
        _tempBildInfo.bild.einpassen();
        _tempBildInfo.bild.unsichtbarMachen();
        _tempBildInfo.times = 0;
        _geldBilder_Liste.put(geld_betrag, _tempBildInfo);
    }
    
    /*  Methode die dazu genutzt wird die times der _geldBilder_Liste zu aktuallisieren
     *  @param betrag: Betrag der animiert werden soll
     */
    private void _build_geldBilder_Liste(double betrag){
        for(double key : _geldBilder_Liste.keySet()){
            _geldBilder_Liste.get(key).times = 0;
        }
        _geldAnteil.berechneAnteile(betrag);
        for(double key : _geldBilder_Liste.keySet()){
            _geldBilder_Liste.get(key).times = _geldAnteil.getAnteil(key);
        }
    
    }
    
    /*  Method mit der die Einzahlung von Geld animiert wird
     *  @param Betrag: Betrag der eingezahlt wird
     *  @param x: x-Position an der die Animation statt finden soll
     *  @param y: y-Position an der die Animation statt finden soll
     *  @param x_amount: x Wert um wie viel das Geld verschoben werden sollen
     *  @parma y_amount: y Wert um wie viel das Geld verschoben werden sollen
     */
    public void geldBetragBewegen(double Betrag, int x, int y, int x_amount, int y_amount) {
        this._build_geldBilder_Liste(Betrag);
        _atmAusschnitt = new Bild("ATM_Geldausschnitt.png");
        _atmAusschnitt.setzeDimensionen(x_geldKlappe-371, y_geldKlappe, 370, 169);
        for(double key : _geldBilder_Liste.keySet()){
            if(_geldBilder_Liste.get(key).times > 0){
                _geldBilder_Liste.get(key).bild.sichtbarMachen();
                for(int i = 0; i < _geldBilder_Liste.get(key).times; i++){
                    _geldBilder_Liste.get(key).bild.setzePosition(x, y);
                    _geldBilder_Liste.get(key).bild.langsamHorizontalBewegen(x_amount);
                    _geldBilder_Liste.get(key).bild.langsamVertikalBewegen(y_amount);
                }
                _geldBilder_Liste.get(key).bild.unsichtbarMachen();
            }
        }     
        //_geldBilder_Liste.clear();
        _atmAusschnitt.unsichtbarMachen();
        _atmAusschnitt = null;
    }
    
    /*  Method die das Verschieben der Kreditkarte animiert
     *  @param x: x-Position an der die Animation stattfinden soll
     *  @param y: y-Position an der die Animation stattfinden soll
     *  @param move_amount_x: Betrag wie weit die Karte nach links oder rechts bewegt werden soll
     *  @param move_amount_y: Betrag wie weit die Karte nach oben oder unten bewegt werden soll
     */
    public void kreditkarteVerschieben(int x, int y, int move_amount_x, int move_amount_y)
    {
        _kreditkarte_Bild.sichtbarMachen();
        _kreditkarte_Bild.setzePosition(x, y);
        _kreditkarte_Bild.langsamVertikalBewegen(move_amount_y);
        _kreditkarte_Bild.langsamHorizontalBewegen(move_amount_x);
        _kreditkarte_Bild.unsichtbarMachen();
    }
    
    /*  Methode mit der man bestimmen kann ob die Kreditkarte angezeigt
     *  werden soll oder nicht
     *  @param wert: Wert der bestimmt ob die Kk angezeigt werden soll 
     *               oder nicht
     */
    public void kreditkarteAnzeigen(boolean wert)
    {
        if(wert)
            _kreditkarte_Bild.sichtbarMachen();
        else
            _kreditkarte_Bild.unsichtbarMachen();
    }
    
    /*  Methode mit der man die Bewegung eines Kontoauszuges animieren kann
     *  @param x: x-Pos an der die Animation gestartet wird
     *  @param y: y-Pos an der die Animation gestartet wird
     *  @param move_amount_x: Wert der bestimmt wie weit sich das bild in x verschiebt
     *  @param move_amount_y: Wert der bestimmt wie weit sich das bild in y verschiebt
     */
    public void kontoauszugVerschieben(int x, int y, int move_amount_x, int move_amount_y)
    {
        _kontoauszug_Bild.sichtbarMachen();
        _kontoauszug_Bild.setzePosition(x, y);
        _atmAusschnitt = new Bild("ATM_Geldausschnitt.png");
        _atmAusschnitt.setzeDimensionen(x_geldKlappe-371, y_geldKlappe, 370, 169);
        _kontoauszug_Bild.langsamHorizontalBewegen(move_amount_x);
        _kontoauszug_Bild.langsamVertikalBewegen(move_amount_y);
        _kontoauszug_Bild.unsichtbarMachen();
        _atmAusschnitt.unsichtbarMachen();
        _atmAusschnitt = null;
    }
}



































