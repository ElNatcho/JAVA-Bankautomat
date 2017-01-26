/**
 * Ein AusgabePanel kann sich selbst zeichnen im Programmfenster Zeichnung
 * 
 * @author Hans Witt
 * 
 * Version 1.1 (14.7.2008) 

 * Version: 1.1.1 (17.7.2008) 
 *        Neue Komponenten werden von Unten nach Oben aufgebaut, d.h.vor die alten gesetzt
 * Version: 3 (9.8.2008) 
 *        erg�nzt f�r Beh�lter f�r GUI-Elemente
 * Version: 3.1 (14.8.2008) 
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst		
 * @version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt 
 * 
 */
import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class AusgabePanel implements IComponente{

	private CAusgabePanel obj;
	protected int breite = 0;
	protected int hoehe = 0;
	protected int xPos = 0;
	protected int yPos = 0;
	protected String anzeigeText = "Anzeige";
	protected int fontGroesse = -1;
	protected String hintergrundFarbe = StaticTools.leseNormalfarbe();
	protected String schriftFarbe = "schwarz";

	private int ausrichtung = 1; // 0 = links , 1 = mitte , 2 =rechts

	/**
	 * Konstuktor f�r Hauptfenster
	 */
	public AusgabePanel() {
		this(Zeichnung.gibZeichenflaeche());
	}

	/**
	 * Konstruktor f�r Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public AusgabePanel(String neuerText, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, 0, 0, neueBreite,
				neueHoehe);
	}

	/**
	 * 
	 * Konstruktor f�r Hauptfenster
	 * 
	 * @param neuerText
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public AusgabePanel(String neuerText, int neuesX, int neuesY,
			int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
				neueBreite, neueHoehe);
	}

	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public AusgabePanel(IContainer behaelter) {
		this(behaelter, "Anzeige", 0, 0, 100, 50);
	}

	public AusgabePanel(IContainer behaelter, String neuerText, int neuesX,
			int neuesY, int neueBreite, int neueHoehe) {
		anzeigeText = neuerText;
		obj = new CAusgabePanel(anzeigeText, ausrichtung);
		behaelter.add(obj, 0);
		setzeHintergrundfarbe(hintergrundFarbe);
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

	public void setzeAusgabetext(String neuerText) {
		anzeigeText = neuerText;
		obj.setText(anzeigeText);
	}

	public void setzeSchriftgroesse(int neueFontgroesse) {
		fontGroesse = neueFontgroesse;
		obj.setzeSchriftgroesse(fontGroesse);
	}

	/**
	 * Ausrichtung des Texts setzen </br> Linksb�ndig: 0 </br> Zentriert: 1
	 * </br> Rechtsb�ndig: 2 </br>
	 */
	public void setzeAusrichtung(int i) {
		ausrichtung = i;
		obj.setzeAusrichtung(i);
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
}

class CAusgabePanel extends BasisComponente {

	private JPanel panel;
	private JLabel label;

	public void setzeSchriftgroesse(int i) {
		setFontsize(i);
		label.setFont(f);
		panel.setFont(f);
		repaint();
	}

	public void setzeAusrichtung(int ausrichtung) {
		switch (ausrichtung) {
		case 0:
			label.setHorizontalAlignment(SwingConstants.LEFT);
			break;
		case 1:
			label.setHorizontalAlignment(SwingConstants.CENTER);
			break;
		case 2:
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		default:
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
	}

	/**
	 * Konstruktor
	 */
	public CAusgabePanel(String text, int ausrichtung /*
													 * 0 = links , 1 = mitte , 2
													 * =rechts
													 */) {
		this.setLayout(new BorderLayout());
		panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.setBackground(farbe);

		panel.setBorder(BorderFactory.createCompoundBorder(BorderFactory
				.createRaisedBevelBorder(), BorderFactory
				.createLoweredBevelBorder()));

		label = new JLabel(text);

		switch (ausrichtung) {
		case 0:
			label.setHorizontalAlignment(SwingConstants.LEFT);
			break;
		case 1:
			label.setHorizontalAlignment(SwingConstants.CENTER);
			break;
		case 2:
			label.setHorizontalAlignment(SwingConstants.RIGHT);
			break;
		default:
			label.setHorizontalAlignment(SwingConstants.CENTER);
		}
		label.setVerticalAlignment(SwingConstants.CENTER);

		label.setFont(f);
		panel.add(label, BorderLayout.CENTER);
		this.add(panel);
		repaint();
	}

	public void setText(String s) {
		label.setText(s);
	}

	public void setzeBasisfarbe(String farbname) {
		farbe = StaticTools.getColor(farbname);
		panel.setBackground(farbe);
		repaint();
	}

	public void setzeSchriftfarbe(String farbname) {
		label.setForeground(StaticTools.getColor(farbname));
		repaint();
	}

	public void paintComponentSpezial(Graphics g) {
		// nichts zu tun
	}
}
