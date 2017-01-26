/**
 * Ein Dreieck kann sich selbst zeichnen im Programmfenster Zeichnung
 * 
 * @author Hans Witt
 * 
 * Version 1.1 (14.7.2008)
 *     Hinzufügen von Statusvariablen für Position ...
 * Version  1.1.1 (17.7.2008) 
 *        Neue Komponenten werden von Unten nach Oben aufgebaut, d.h.vor die alten gesetzt
 * Version: 1.2 (19.7.2008) 
 *        Dreiecksaurichtung N O S W 
 *        erzeugt symmetrisches Dreieck mit Spitze in Himmelsrichtung 
 *        
 * Version: 1.3 (9.8.2008) 
 *        Dreiecksaurichtung NO, SO, SW , NW  
 *        erzeugt rechtwinkeliges Dreieck mit einer Diagonalen als Basis und 
 *        weiterem eck laut Himmesrichtung
 * Version: 3 (9.8.2008) 
 *        ergänzt für Behälter für GUI-Elemente
 * Version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst		
 * @version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt 
 */

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;

public class Dreieck implements IComponente{

	private CDreieck obj;
	protected int breite = 0;
	protected int hoehe = 0;
	protected int xPos = 0;
	protected int yPos = 0;
	protected boolean sichtbar = true;
	protected boolean gefuellt = true;
	protected String farbe = StaticTools.leseNormalfarbe();

	private StaticTools.Richtung ausrichtung = StaticTools.Richtung.N;

	/**
	 * Konstuktor für Hauptfenster
	 */
	public Dreieck() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor für Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Dreieck(int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor für Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Dreieck(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Dreieck(IContainer behaelter) {
		this(behaelter, 0, 0, 50, 50);
	}

	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Dreieck(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		obj = new CDreieck();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		behaelter.validate();
	}

	/**
	 *  Das Interface IComponente fordert eine Methode die eine BasisComponente zurückliefert.
	 *  Sie wird benötigt, um ein Objekt zu einem anderen Behälter hinzuzufügen
	 */
	public BasisComponente getBasisComponente() {
		return obj ;
	}

	/**
	 * Richtung der Dreiecksspitze
	 * 
	 * @param richtung
	 *            Mögliche Werte: "N","O","S","W","NO","SO","SW","NW"
	 */
	public void setzeAusrichtung(String richtung) {
		ausrichtung = StaticTools.getRichtung(richtung);
		obj.setzeAusrichtung(ausrichtung);
		obj.repaint();
	}

	public void sichtbarMachen() {
		sichtbar = true;
		obj.sichtbarMachen();
	}

	/**
	 * Mache unsichtbar. Wenn es bereits unsichtbar ist, tue nichts.
	 */
	public void unsichtbarMachen() {
		sichtbar = false;
		obj.unsichtbarMachen();
	}

	/**
	 * Bewege horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void nachRechtsBewegen() {
		horizontalBewegen(20);
	}

	/**
	 * Bewege einige Bildschirmpunkte nach links.
	 */
	public void nachLinksBewegen() {
		horizontalBewegen(-20);
	}

	/**
	 * Bewege einige Bildschirmpunkte nach oben.
	 */
	public void nachObenBewegen() {
		vertikalBewegen(-20);
	}

	/**
	 * Bewege einige Bildschirmpunkte nach unten.
	 */
	public void nachUntenBewegen() {
		vertikalBewegen(20);
	}

	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamVertikalBewegen(int entfernung) {
		int delta;

		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}

		for (int i = 0; i < entfernung; i++) {
			vertikalBewegen(delta);
			StaticTools.warte(10);
		}
	}

	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void langsamHorizontalBewegen(int entfernung) {
		int delta;

		if (entfernung < 0) {
			delta = -1;
			entfernung = -entfernung;
		} else {
			delta = 1;
		}

		for (int i = 0; i < entfernung; i++) {
			horizontalBewegen(delta);
			StaticTools.warte(10);
		}
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

	
	// Methode nötig zum Hinzufügen mit Anpassung beim Behälter
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

	/*
	 * gültige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(String neueFarbe) {
		farbe = neueFarbe;
		obj.setzeBasisfarbe(neueFarbe);
	}

	/**
	 * Bewege horizontal um 'entfernung' Bildschirmpunkte.
	 */
	public void horizontalBewegen(int entfernung) {
		xPos += entfernung;
		obj.setzePosition(xPos, yPos);
	}

	/**
	 * Bewege vertikal um 'entfernung' Bildschirmpunkte.
	 */
	public void vertikalBewegen(int entfernung) {
		yPos += entfernung;
		obj.setzePosition(xPos, yPos);
	}

	public void fuellen() {
		gefuellt = true;
		obj.fuellen();
	}

	public void rand() {
		gefuellt = false;
		obj.rand();
	}

	public static void main(String[] args) {
		Dreieck d = new Dreieck(0, 0, 300, 50);
		d.setzeAusrichtung("W");
		d.fuellen();
	}

}

class CDreieck extends BasisComponente {

	int[] xpoints = new int[3];
	int[] ypoints = new int[3];

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CDreieck() {
		// Anfangskoordinaten für Dreieck setzen
		setzeAusrichtung(StaticTools.Richtung.N);
	}

	private StaticTools.Richtung ausrichtung = StaticTools.Richtung.N;

	public void setzeAusrichtung(StaticTools.Richtung richtung) {
		ausrichtung = richtung;
		repaint();
	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */

	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width-1;
		hoehe = getSize().height-1;
		g2.setColor(farbe);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));

		switch (ausrichtung) {
		case N:
			xpoints[0] = 0;
			xpoints[1] = (breite / 2);
			xpoints[2] = breite;
			ypoints[0] = hoehe;
			ypoints[1] = 0;
			ypoints[2] = hoehe;
			break;
		case S:
			xpoints[0] = 0;
			xpoints[1] = (breite / 2);
			xpoints[2] = breite;
			ypoints[0] = 0;
			ypoints[1] = hoehe;
			ypoints[2] = 0;
			break;
		case O:
			xpoints[0] = 0;
			xpoints[1] = breite;
			xpoints[2] = 0;
			ypoints[0] = 0;
			ypoints[1] = hoehe / 2;
			ypoints[2] = hoehe;
			break;
		case W:
			xpoints[0] = breite;
			xpoints[1] = 0;
			xpoints[2] = breite;
			ypoints[0] = 0;
			ypoints[1] = hoehe / 2;
			ypoints[2] = hoehe;
			break;
		case NO:
			xpoints[0] = 0;
			xpoints[1] = breite;
			xpoints[2] = breite;
			ypoints[0] = 0;
			ypoints[1] = 0;
			ypoints[2] = hoehe;
			xpoints[0] = 1;
			xpoints[1] = breite - 1;
			xpoints[2] = breite - 1;
			ypoints[0] = 1;
			ypoints[1] = 1;
			ypoints[2] = hoehe - 1;
			break;
		case SW:
			xpoints[0] = 0;
			xpoints[1] = 0;
			xpoints[2] = breite;
			ypoints[0] = 0;
			ypoints[1] = hoehe;
			ypoints[2] = hoehe;
			xpoints[0] = 1;
			xpoints[1] = 1;
			xpoints[2] = breite - 1;
			ypoints[0] = 1;
			ypoints[1] = hoehe - 1;
			ypoints[2] = hoehe - 1;
			break;
		case SO:
			xpoints[0] = breite;
			xpoints[1] = breite;
			xpoints[2] = 0;
			ypoints[0] = 0;
			ypoints[1] = hoehe;
			ypoints[2] = hoehe;
			xpoints[0] = breite - 1;
			xpoints[1] = breite - 1;
			xpoints[2] = 1;
			ypoints[0] = 1;
			ypoints[1] = hoehe - 1;
			ypoints[2] = hoehe - 1;
			break;
		case NW:
			xpoints[0] = breite;
			xpoints[1] = 0;
			xpoints[2] = 0;
			ypoints[0] = 0;
			ypoints[1] = hoehe;
			ypoints[2] = 0;
			xpoints[0] = breite - 1;
			xpoints[1] = 1;
			xpoints[2] = 1;
			ypoints[0] = 1;
			ypoints[1] = 1;
			ypoints[2] = hoehe - 1;
			break;
		default:

		}

		if (gefuellt) {
			g2.fillPolygon(new Polygon(xpoints, ypoints, 3));
		} else {
			g2.drawPolygon(new Polygon(xpoints, ypoints, 3));
		}
	}
}
