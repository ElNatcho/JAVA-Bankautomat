import java.util.TreeMap;
import java.util.Collections;

/*  Klasse die die Anteile aller Geldscheine bzw Geldstuecke in 
 *  in einem bestimmten Geldbetrag berechnet und speichert
 */
public class CGeldAnteil {
    
    /*  In _geldAnteile werden alle Anteile der Geldscheine bzw
     *  der Geldstuecke eines bestimmten Betrages gespeichert
     */
    private TreeMap<Double, Integer> _geldAnteile;
    
    // -- Konstruktor --
    public CGeldAnteil() {
        _geldAnteile = new TreeMap<Double, Integer>(Collections.reverseOrder());
        
        _geldAnteile.put(500.d, 0);
        _geldAnteile.put(200.d, 0);
        _geldAnteile.put(100.d, 0);
        _geldAnteile.put(50.d, 0);
        _geldAnteile.put(20.d, 0);
        _geldAnteile.put(10.d, 0);
        _geldAnteile.put(5.d, 0);
        _geldAnteile.put(2.d, 0);
        _geldAnteile.put(1.d, 0);
        _geldAnteile.put(0.5d, 0);
        _geldAnteile.put(0.2d, 0);
        _geldAnteile.put(0.1d, 0);
        _geldAnteile.put(0.05d, 0);
        _geldAnteile.put(0.02d, 0);
        _geldAnteile.put(0.01d, 0);
    }
    
    /*  Methode die die jeweiligen Anteile in einer bestimmten Zahl berechnet
     *  @param betrag: zahl deren Anteile berechnet werden soll
     */
    public void berechneAnteile(double betrag){
        if(berechneNachkommastellen(betrag) > 2 || berechneNachkommastellen(betrag) < 0) {
            System.out.println("NACHKOMMASTELLEN_SIND_IM_FALSCHEN_BEREICH_EXCEPTION: Nackkommastellen = " + berechneNachkommastellen(betrag));
        } else {           
            for(double key : _geldAnteile.keySet()){ // Durch jeden Eintrag in _geldAnteile iterieren
                _geldAnteile.put(key, berechneAnteil(betrag, key)); // Den berechneten Anteil in _geldAnteile speichern
                betrag -= _geldAnteile.get(key) * key; // Den berechneten Anteil abziehen
            }              
        }
    }
    
    public TreeMap<Double, Integer> getGeldAnteilMap(){
        return _geldAnteile;
    }
    
    /*  Methode gibt den Anteil eines bestimmten geld betrages zurück
     * 
     */
    public int getAnteil(double geld_betrag){
        for(double key : _geldAnteile.keySet()){
            if(key == geld_betrag){
                return _geldAnteile.get(key);
            }
        }
        return 0;
    }
    
    /*  Methode berechnet den Anteil von geld_betrag in betrag und
     *  zieht den Anteil multipliziert mit dem geld_betrag von betrag ab
     *  @param betrag
     *  @param geld_betrag
     */
    public int berechneAnteil(double betrag, double geld_betrag){
        betrag = Math.round(100.0 * betrag) / 100.0; // Betrag auf 2 Nachkommastellen runden um Ungenauigkeiten zu vermeiden
        if(betrag > geld_betrag){ // Testen ob betrag goesser als geld_betrag ist
            return (int)(betrag / geld_betrag); // Den Anteil von geld_betrag in betrag ausrechnen      
        }else if(betrag == geld_betrag){
            return 1;
        }else{
            return 0;
        }
    }
          
    /*  Methode die die Nachkommastellen eines double berechnet
     *  @param zahl: double dessen Nachkommastellen berechnet werden sollen
     */
    private int berechneNachkommastellen(double zahl) {
        String s = Double.toString(zahl); // Zahl zu String konvertieren und in s speichern
        s = s.substring(s.indexOf(".") + 1); // Alles vor dem Komma abtrennen und in s speichern
        //System.out.println(s); 
        return s.length(); // Laenge zurueckgeben
    }
    
    /*  Method die alle aktuellen Anteile ausgibt
     */
    void info() {      
        for(Object key : _geldAnteile.keySet()){
            System.out.println("Key=" + key.toString() + " Value="+ _geldAnteile.get(key));
        }
    }
}
