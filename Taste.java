/**
 * Eine Taste kann sich selbst zeichnen im Programmfenster Zeichnung </br>
 * 
 *  �ber die Methode wurdeGedrueckt() kann der Status abgefragt werden.
 *  Es wird gemeldet, ob seit der letzten Abfrage die Taste gedr�ckt wurde
 *  
 *       ruecksetzenGedrueckt() setzt den Status zur�ck
 *       warteBisGedrueckt() wartet bis gedr�ckt wird
 *      
 *  �bergibt man Taste den Link auf eine Klasse mit dem Interface ITuwas,
 *  so wird beim Dr�cken der TasteSelect tuWasSelect(id) aufgerufen
 * 
 * @author Hans Witt
 * 
 * Version 1.1 (14.7.2008)
 *     Hinzuf�gen von Statusvariablen f�r Position ...
 * Version: 1.1.1 (17.7.2008) 
 *        Neue Komponenten werden von Unten nach Oben aufgebaut, d.h.vor die alten gesetzt
 * Vversion: 1.3 (21.7.2008)
 *       �ber die Methode wurdeGedrueckt() kann der Status abgefragt werden.
 *       Es wird gemeldet, ob seit der letzten Abfrage die Taste gedr�ckt wurde
 *       	
 *       ruecksetzenGedrueckt() setzt den Status zur�ck
 *       warteBisGedrueckt() wartet bis gedr�ckt wird
 * Version: 2 (3.8.2008)
 *       angepasst an ge�ndertes ITuWas
 * Version: 3 (9.8.2008) 
 *        erg�nzt f�r Beh�lter f�r GUI-Elemente
 * Version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst		
 * @version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt 
 *       
 */

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class Taste implements IComponente{

	private CTaste obj;
	protected int breite = 0;
	protected int hoehe = 0;
	protected int xPos = 0;
	protected int yPos = 0;
	protected String anzeigeText = "Dr�ckmich";
	protected int fontGroesse = -1;
	protected String schriftFarbe = "schwarz";
	protected String hintergrundFarbe = "weiss";

	protected boolean gedrueckt = false;

	/**
	 * Konstruktor f�r Hauptfenster
	 */
	public Taste() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor f�r Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Taste(String neuerText, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, 0, 0, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor f�r Hauptfenster
	 * 
	 * @param neuerText
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Taste(String neuerText, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
				neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Taste(IContainer behaelter) {
		this(behaelter, "Dr�ckmich", 0, 0, 100, 50);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param neuerText
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Taste(IContainer behaelter, String neuerText, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		anzeigeText = neuerText;
		obj = new CTaste(anzeigeText);
		behaelter.add(obj, 0);
		setzeSchriftfarbe(schriftFarbe);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}

	/**
	 *  Das Interface IComponente fordert eine Methode die eine BasisComponente zur�ckliefert.
	 *  Sie wird ben�tigt, um ein Objekt zu einem anderen Beh�lter hinzuzuf�gen
	 */
	public BasisComponente getBasisComponente() {
		return obj ;
	}

	/**
	 * Eine Komponente mit Signalisierung muss eine eindeutige ID haben
	 * @param ID
	 */
	public void setzeID(int ID) {
		obj.setzeID(ID);
	}

	/**
	 * Eine Komponente mit Signalisierung braucht eine Objekt, dem es signalisieren kann
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj) {
		obj.setzeLink(linkObj);
	}


	/**
	 * Callback-Funktion wird aufgerufen, wenn die Taste gedr�ckt wird
	 * 
	 * @param linkObj
	 * 
	 * @param ID
	 *            Parameter, der als ID an die Callback-Funktion �bergeben wird.
	 *            In der Callbackfunktion kann dann durch die ID das aufrufende
	 *            Objekt identifiziert werden.
	 * 
	 */
	public void setzeLink(ITuWas linkObj, int ID) {
		obj.setzeLink(linkObj, ID);
	}

	public void setzeAusgabetext(String neuerText) {
		anzeigeText = neuerText;
		obj.setText(anzeigeText);
	}

	public void setzeSchriftgroesse(int neueFontgroesse) {
		fontGroesse = neueFontgroesse;
		obj.setzeSchriftgroesse(fontGroesse);
	}

	/*
	 * g�ltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeSchriftfarbe(String neueFarbe) {
		schriftFarbe = neueFarbe;
		obj.setzeSchriftfarbe(schriftFarbe);
	}

	/*
	 * g�ltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeHintergrundfarbe(String neueFarbe) {
		hintergrundFarbe = neueFarbe;
		obj.setzeBasisfarbe(hintergrundFarbe);
	}

	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeGroesse(breite, hoehe);
	}

	/**
	 * neue Position
	 * 
	 * @param x
	 * @param y
	 */
	public void setzePosition(int neuesX, int neuesY) {
		xPos = neuesX;
		yPos = neuesY;
		obj.setzePosition(xPos, yPos);
	}

	
	// Methode n�tig zum Hinzuf�gen mit Anpassung beim Beh�lter
	// Die Enden werden relativ zur aktuellen position verschoben
	public void verschieben(int dx , int dy ) {
		setzePosition(xPos + dx, yPos + dy );
	}

	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public void setzeDimensionen(int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		xPos = neuesX;
		yPos = neuesY;
		breite = neueBreite;
		hoehe = neueHoehe;
		obj.setzeDimensionen(xPos, yPos, breite, hoehe);
	}

	/**
	 * Status der Komponente abrufen und r�cksetzen
	 */
	public boolean wurdeGedrueckt() {
		boolean wert = obj.istGedrueckt();
		ruecksetzenGedrueckt();
		return wert;
	}

	public void ruecksetzenGedrueckt() {
		gedrueckt = false;
		obj.resetGedrueckt();
	}

	public void warteBisGedrueckt() {
		while (!wurdeGedrueckt()) {
			StaticTools.warte(20); // warte 20ms
		}
	}

}

class CTaste extends BasisComponente implements ActionListener {
	private JButton taste;

	// Zustand der Komponente

	private boolean gedrueckt = false;

//	Font f = new Font("Dialog", Font.PLAIN, 18);

	public void setzeSchriftgroesse(int i) {
		f = new Font("Dialog", Font.PLAIN, i);
		taste.setFont(f);
		repaint();
	}

	/**
	 * Konstruktor f�r Objekte der Klasse Taste
	 */
	public CTaste(String text) {
		this.setLayout(new BorderLayout());
		taste = new JButton(text);
		taste.setBackground(farbe);
		taste.setFont(f);
		taste.addActionListener(this);
		taste.updateUI();
		this.add(taste);
	}

	public void actionPerformed(ActionEvent arg0) {
		gedrueckt = true;
		if (linkObj != null)
			linkObj.tuWas(id);
	}

	/**
	 * @return gibt zur�ck, ob der Knopf gedr�ckt ist
	 */
	public boolean istGedrueckt() {
		return gedrueckt;
	}

	public void resetGedrueckt() {
		this.gedrueckt = false;
	}

	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		taste.setBackground(farbe);
		repaint();
	}

	public void setzeSchriftfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		taste.setForeground(farbe);
		repaint();
	}

	public void setText(String s) {
		taste.setText(s);
	}

	public void paintComponentSpezial(Graphics g) {
		// Hier nichts zu tun

	}
}
