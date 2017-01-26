/**
 * Ein Segment kann sich selbst zeichnen im Programmfenster Zeichnung
 * Ziel: Siebensegementanzeige
 * 
 * @author Hans Witt
 * 
 * Version 1.1 (14.7.2008)
 *     Hinzufügen von Statusvariablen für Position ...
 * Version: 1.1.1 (17.7.2008) 
 *        Neue Komponenten werden von Unten nach Oben aufgebaut, d.h.vor die alten gesetzt
 * Version: 3 (9.8.2008) 
 *        Containerklasse für GUI-Elemente
 * Version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst		
 * @version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt 
 * 
 */

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;

public class Segment implements IComponente{

	private CSegment obj;
	protected int breite = 0;
	protected int hoehe = 0;
	protected int xPos = 0;
	protected int yPos = 0;
	protected boolean sichtbar = true;
	protected boolean gefuellt = true;
	protected String farbe = "rot";
	protected String farbeRand = StaticTools.leseNormalfarbe();

	/**
	 * Konstruktor für Hauptfenster
	 */
	public Segment() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor für Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Segment(int neueBreite, int neueHoehe) {
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
	public Segment(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Segment(IContainer behaelter) {
		this(behaelter, 0, 0, 50, 25);
	}

	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public Segment(IContainer behaelter, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		obj = new CSegment();
		behaelter.add(obj, 0);
		setzeFarbe("rot");
		setzeRandfarbe("grau");
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

	/**
	 * gültige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(String neueFarbe) {
		farbe = neueFarbe;
		obj.setzeBasisfarbe(neueFarbe);
	}

	/**
	 * gültige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeRandfarbe(String neueFarbe) {
		farbeRand = neueFarbe;
		obj.setzeRandfarbe(farbeRand);
	}

	public void fuellen() {
		gefuellt = true;
		obj.fuellen();
	}

	public void rand() {
		gefuellt = false;
		obj.rand();
	}

}

class CSegment extends BasisComponente {
	// Zustand der Komponente
	protected Color randFarbe = StaticTools.getColor("hellgrau");

	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CSegment() {

	}

	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */

	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width - 2;
		hoehe = getSize().height - 2;
		g.setColor(farbe);

		if (hoehe > 2 * breite) {
			int[] xpoints = { breite / 2, breite, breite, breite / 2, 1, 1 };
			int[] ypoints = { 1, breite / 2, hoehe - breite / 2, hoehe,
					hoehe - breite / 2, breite / 2 };
			// int[] xpoints2 = { breite/2 , breite - 1 , breite -1 , breite /2
			// , 2 , 2 };
			// int[] ypoints2 = { 2 , breite /2 +1 , hoehe - breite / 2 -1 ,
			// hoehe-1 , hoehe - breite / 2 - 1 , breite / 2+1 };
			if (gefuellt) {
				g.setColor(farbe);
				g2.fillPolygon(new Polygon(xpoints, ypoints, 6));
			} else {
				g.setColor(randFarbe);
				g2.drawPolygon(new Polygon(xpoints, ypoints, 6));
				// g2.drawPolygon(new Polygon(xpoints2, ypoints2, 6));
			}
		} else if (breite > 2 * hoehe) {
			int[] xpoints = { 1, hoehe / 2, breite - hoehe / 2, breite,
					breite - hoehe / 2, hoehe / 2 };
			int[] ypoints = { hoehe / 2, 1, 1, (hoehe / 2), hoehe, hoehe };
			// int[] xpoints2 = { 2 , hoehe/2+1, breite - hoehe/2-1 , breite-1
			// ,breite - hoehe/2 - 1 , hoehe/2 +1 };
			// int[] ypoints2 = { hoehe/2, 2 , 2 , (hoehe/2) , hoehe -1 , hoehe
			// -1 };
			if (gefuellt) {
				g.setColor(farbe);
				g2.fillPolygon(new Polygon(xpoints, ypoints, 6));
			} else {
				g.setColor(randFarbe);
				g2.drawPolygon(new Polygon(xpoints, ypoints, 6));
				// g2.drawPolygon(new Polygon(xpoints2, ypoints2, 6));
			}
		} else {
			int[] xpoints = { 1, (breite / 3), 2 * (breite / 3), breite,
					2 * (breite / 3), (breite / 3) };
			int[] ypoints = { (hoehe) / 2, 1, 1, (hoehe / 2), hoehe, hoehe };
			// int[] xpoints2 = { 2 , (breite / 3)+1, 2*(breite / 3)-1, breite
			// -1 ,2*(breite / 3)-1 , (breite / 3) +1};
			// int[] ypoints2 = { (hoehe)/2, 2 , 2 , (hoehe/2) , hoehe -1 ,
			// hoehe -1 };
			if (gefuellt) {
				g2.fillPolygon(new Polygon(xpoints, ypoints, 6));
			} else {
				g2.drawPolygon(new Polygon(xpoints, ypoints, 6));
				// g2.drawPolygon(new Polygon(xpoints2, ypoints2, 6));
			}
		}
	}

	public void setzeRandfarbe(String farbname) {
		randFarbe = StaticTools.getColor(farbname);
		repaint();
	}

}