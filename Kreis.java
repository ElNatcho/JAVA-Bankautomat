/**
 * Ein Kreis kann sich selbst zeichnen im Programmfenster Zeichnung
 * 
 * @author Hans Witt
 * 
 * Version 1.1 (14.7.2008)
 *     Hinzufügen von Statusvariablen für Position ...
 * Version: 1.1.1 (17.7.2008) 
 *        Neue Komponenten werden von Unten nach Oben aufgebaut, d.h.vor die alten gesetzt
 * Version: 1.1.2 (23.7.2008) 
 *        setzeRadius verbessert ( Größe = radius * >>2<< )
 *        
 * Version: 3 (9.8.2008) 
 *        Containerklasse für GUI-Elemente
 * Version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst		
 * Version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt 
 * @version: 3.3 (29.8.2008)
 *        Zu den sonst übliche Metheode setzeGröße undd setzePosition kommen
 *        
 *         setzeRadius - behält den Mittelpunkt bei !
 *         
 *         setzeMittelpunkz
 *         
 *         setzeMittelpunktUndRadius 
 *        
 */

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Kreis implements IComponente{
	
	private CKreis		obj;
	protected int		radius		= 25;
	protected int		xPos		= 0;
	protected int		yPos		= 0;
	protected boolean	sichtbar	= true;
	protected boolean	gefuellt	= true;
	protected String	farbe		= StaticTools.leseNormalfarbe();
	
	/**
	 * Konstruktor für Hauptfenster
	 */
	public Kreis() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor für Hauptfenster
	 * 
	 * @param neuerRadius
	 */
	public Kreis(int neuerRadius) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neuerRadius);
	}
	
	/**
	 * Konstruktor für Hauptfenster
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neuerRadius
	 */
	public Kreis(int neuesX, int neuesY, int neuerRadius) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neuerRadius);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Kreis(IContainer behaelter) {
		this(behaelter, 0, 0, 25);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param neuerRadius
	 */
	public Kreis(IContainer behaelter, int neuesX, int neuesY, int neuerRadius) {
		obj = new CKreis();
		behaelter.add(obj, 0);
		setzeDimensionen(neuesX, neuesY, neuerRadius);
		behaelter.validate();
	}
	
	/**
	 *  Das Interface IComponente fordert eine Methode die eine BasisComponente zurückliefert.
	 *  Sie wird benötigt, um ein Objekt zu einem anderen Behälter hinzuzufügen
	 */
	public BasisComponente getBasisComponente() {
		return obj ;
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
	
	/**
	 * neuer Mittelpunkt
	 * 
	 * @param neuesMX
	 * @param neuesMY
	 */
	public void setzeMittelpunkt(int neuesMX, int neuesMY) {
		xPos = neuesMX - radius;
		yPos = neuesMY - radius ;
		obj.setzePosition(xPos, yPos);
	}

	
	/**
	 * neuer radius *
	 */
	public void setzeRadius(int neuerRadius) {
		int mx = xPos + radius;
		int my = yPos + radius ;
		radius = neuerRadius;
		xPos = mx - radius;
		yPos = my - radius ;
		obj.setzeDimensionen(xPos, yPos, 2 * radius, 2 * radius);
	}
	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */

	public void setzeMittelpunktUndRadius(int neuesMX, int neuesMY, int neuerRadius) {
		radius = neuerRadius;
		xPos = neuesMX - radius;
		yPos = neuesMY - radius ;
		obj.setzeDimensionen(xPos, yPos, 2 * radius, 2 * radius);
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
	 * neuer radius *
	 */
	public void setzeGroesse(int neuerRadius) {
		radius = neuerRadius;
		obj.setzeGroesse(2 * radius, 2 * radius);
	}

	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public void setzeDimensionen(int neuesX, int neuesY, int neuerRadius) {
		xPos = neuesX;
		yPos = neuesY;
		radius = neuerRadius;
		obj.setzeDimensionen(xPos, yPos, 2 * radius, 2 * radius);
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
	
	/**
	 * gefüllter Kreis
	 */
	public void fuellen() {
		gefuellt = true;
		obj.fuellen();
	}
	
	/**
	 * Kreislinie
	 */
	public void rand() {
		gefuellt = false;
		obj.rand();
	}
	
}

class CKreis extends BasisComponente {
	
	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CKreis() {
		
	}
	
	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width;
		hoehe = getSize().height;
		g2.setColor(farbe);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setStroke(new BasicStroke(2, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));

		if (gefuellt) {
			g2.fillOval(0, 0, breite, hoehe);
		} else {
			
			g2.drawOval(1, 1, breite-3, hoehe-3);
//			g2.drawOval(1, 1, breite - 3, hoehe - 3);
		}
	}
}
