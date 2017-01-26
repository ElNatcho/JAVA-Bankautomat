/**
 * Eine Linie kann sich selbst zeichnen im Programmfenster Zeichnung
 * 
 * @author Hans Witt
 * 
 * @version: 3.2 (18.8.2008)
 * 
 */
import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

public class Linie implements IComponente {
	
	private CLinie		obj;
	protected int		x1			= 0;
	protected int		y1			= 0;
	protected int		x2			= 100;
	protected int		y2			= 50;
	protected boolean	sichtbar	= true;
	protected String	farbe		= StaticTools.leseNormalfarbe();
	protected int		linienDicke	= 4;
	
	/**
	 * Konstruktor für Hauptfenster
	 */
	public Linie() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor für Hauptfenster
	 * 
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public Linie(int x1, int y1, int x2, int y2) {
		this(Zeichnung.gibZeichenflaeche(), x1, y1, x2, y2);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public Linie(IContainer behaelter) {
		this(behaelter, 0, 0, 100, 50);
	}
	
	/**
	 * allgemeiner Konstruktor
	 * 
	 * @param behaelter
	 * @param x2
	 * @param y2
	 */
	public Linie(IContainer behaelter, int x1, int y1, int x2, int y2) {
		obj = new CLinie();
		behaelter.add(obj, 0);
		setzeEndpunkte(x1, y1, x2, y2);
		behaelter.validate();
	}
	
	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente
	 * zurückliefert. Sie wird benötigt, um ein Objekt zu einem anderen Behälter
	 * hinzuzufügen
	 */
	public BasisComponente getBasisComponente() {
		return obj;
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
	 * 
	 * @param neuesX1
	 * @param neuesY1
	 * @param neuesX2
	 * @param neuesY2
	 */
	public void setzeEndpunkte(int neuesX1, int neuesY1, int neuesX2,
			int neuesY2) {
		x1 = neuesX1;
		y1 = neuesY1;
		x2 = neuesX2;
		y2 = neuesY2;
		obj.setzeEndpunkte(x1, y1, x2, y2);
	}
	
	// Methode nötig zum Hinzufügen mit Anpassung beim Behälter
	// Die Enden werden relativ zur aktuellen position verschoben
	public void verschieben(int dx, int dy) {
		setzeEndpunkte(x1 + dx, y1 + dy, x2 + dx, y2 + dy);
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
	 * Liniendicke setzen
	 * 
	 * @param neueDicke
	 */
	public void setzeLinienDicke(int neueDicke) {
		linienDicke = neueDicke;
		obj.setzeLinienDicke(linienDicke);
	}
}

class CLinie extends BasisComponente {
	
	private int	originalLinienDicke	= 4;
	private int	linienDicke			= 4;
	
	public void setzeLinienDicke(int neueDicke) {
		originalLinienDicke = neueDicke;
		zoomen();
		int differenz = neueDicke - linienDicke;
		linienDicke = neueDicke;
		xPos -= differenz;
		yPos -= differenz;
		breite = breite + 2 * differenz;
		hoehe = hoehe + 2 * differenz;
		
		startX += differenz;
		startY += differenz;
		endX += differenz;
		endY += differenz;
		super.setzeDimensionen(xPos, yPos, breite, hoehe);
	}
	
	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CLinie() {
		
	}
	
	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width;
		hoehe = getSize().height;
		g.setColor(farbe);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g2.setStroke(new BasicStroke(linienDicke, BasicStroke.CAP_ROUND,
				BasicStroke.JOIN_MITER));
		
		g2.drawLine(startX, startY, endX, endY);
	}
	
	int	startX		= 10;
	int	startY		= 10;
	int	endX		= 100;
	int	endY		= 100;
	
	int	originalX1	= 10;
	int	originalY1	= 10;
	int	originalX2	= 100;
	int	originalY2	= 100;
	
	int	lX1			= 10;
	int	lY1			= 10;
	int	lX2			= 100;
	int	lY2			= 100;
	
	protected void zoomen() {
		linienDicke = (int) Math.round(originalLinienDicke * zoomFaktor * bzf);
		lX1 = (int) Math.round((originalX1 + originalVX) * zoomFaktor * bzf );
		lY1 = (int) Math.round((originalY1 + originalVY) * zoomFaktor * bzf );
		lX2 = (int) Math.round((originalX2 + originalVX) * zoomFaktor * bzf );
		lY2 = (int) Math.round((originalY2 + originalVY) * zoomFaktor * bzf );
		
		if (lX1 < lX2) {
			xPos = lX1 - linienDicke;
			breite = lX2 - lX1 + 2 * linienDicke;
		} else {
			xPos = lX2 - linienDicke;
			breite = lX1 - lX2 + 2 * linienDicke;
		}
		if (lY1 < lY2) {
			yPos = lY1 - linienDicke;
			hoehe = lY2 - lY1 + 2 * linienDicke;
		} else {
			hoehe = lY1 - lY2 + 2 * linienDicke;
			yPos = lY2 - linienDicke;
		}
		
		startX = lX1 - xPos;
		startY = lY1 - yPos;
		endX = lX2 - xPos;
		endY = lY2 - yPos;
	}
	
	public void setzeEndpunkte(int neuesX1, int neuesY1, int neuesX2,
			int neuesY2) {
		originalX1 = neuesX1;
		originalY1 = neuesY1;
		originalX2 = neuesX2;
		originalY2 = neuesY2;
		zoomen();
		
		super.setzeDimensionen(xPos, yPos, breite, hoehe);
	}
	
}
