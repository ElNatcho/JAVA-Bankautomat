/*
 * Autor: Sven Gebhard
 * Ziel der Klasse:
 * Abfangen und verwalten aller Druck-Events der Knoepfe die 
 * mit dieser Klasse verlinkt wurden.
 */

import java.util.TreeMap;
import java.util.Iterator;

public class CInputController implements ITuWas {

    // -- Member Vars --
    
    /*  Klasse (Struct in c++) um den Status und die ID eines Knopfes
     *  abzuspeichern ohne eine mehrdimensionales Map zu benutzen
     */
    private class _ButtonInfo{
        public int     ID;
        public boolean Status;
    }
    
    /*  Beinhaltet alle Knoepfe(Name via toString()) die Verlinkt
     *  wurden und deren aktuellen Status(_ButtonInfo).
     *  Den Namen des Knopfes als key den Status als Value
     */
    private TreeMap<String, _ButtonInfo> _buttonStatusList;
    
    // Ein temporaeres Objekt zum abspeichern eines neuen Knopfes in der _buttonStatusList
    private _ButtonInfo _tempButtonInfo;
    
    /* -- Konstruktor --
     *   Reserviert bzw weist _buttonStatusList Speicher zu
     */
    public CInputController(){
        _buttonStatusList = new TreeMap<String, _ButtonInfo>();     // Erstellen der _buttonStatusList (Alloc Memory)
    }
    
    /*  Methode fuegt Knoepfe hinzu bei denen das Druck-Event abgefangen werden soll
     *  und verarbeitet werden soll.
     *  @param taste: Tasten-Objekt deren Input-Events abgefangen werden sollen
     *  @param ID   : ID die der Knopf be
     */
    public void knopfHinzufuegen(Taste key, int ID){
        key.setzeLink(this, ID); // Verlinken des Tasten-Objektes mit dieser Klasse 
        _tempButtonInfo    = new _ButtonInfo(); // Neuen _tempButtonInfo erstellen
        _tempButtonInfo.ID = ID;        // Zuweisen der ID des Knopfes
        _tempButtonInfo.Status = false; // Status auf falsch setzen (Default)
        _buttonStatusList.put(key.toString(), _tempButtonInfo); // Hinzufuegen des Namen(key) und der _tempButtonInfo(value) in die _buttonStatusList
    }
    
    /*  Implementierung der Methode tuWas, da diese Klasse aus der Klasse
     *  ITuWas erbt. Die Methode fängt die Input-Events der verlinkten Objekte
     *  ab und aktualisiert den Status dieser Objekte
     *  @param ID: ID des Buttons der Gedrückt wurde 
     */
    public void tuWas(int ID){
        for(Object key : _buttonStatusList.keySet()){ // Durch alle Eintraege in der _buttonStatusList iterieren
            if(ID == _buttonStatusList.get(key).ID){ // Die ID jedes einzelnen Objektes mit der abgefangenen ID vergleichen
                _buttonStatusList.get(key).Status = true; // Der Status des Objektes wird auf true gesetzt
            }
        }
        
    }
    
    /*  Methode liefert den aktuellen Status der Taste zurueck die als Parameter übergeben wurde.
     *  @param taste: Taste deren Status abgefragt werden soll
     */
    public boolean wurdeGedrueckt(Taste taste){
        if(_buttonStatusList.get(taste.toString()).Status){
            this.resetStatus(taste);
            return true;
        }else
            return false;
            
    }
    
    /*  Setzt den Status der Taste die als Parameter übergeben wird auf falsch.
     *  @param taste: Taste deren Status auf falsch gesetzt werden soll
     */
    public void resetStatus(Taste taste) {
        _buttonStatusList.get(taste.toString()).Status = false;
    }
    
    /*  Gibt alle Eintraege der _buttonStatusList aus
     */
    public void info(){
        int i = 0;
        for(Object value : _buttonStatusList.keySet()){
            System.out.println("Eintrag " + i + ": " + value.toString() + " " + _buttonStatusList.get(value).ID + 
            " " + _buttonStatusList.get(value).Status);
            i++;
        }
    }

}

























