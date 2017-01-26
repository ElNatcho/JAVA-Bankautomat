/**
 * Ein Bild kann sich selbst zeichnen im Programmfenster Zeichnung
 *
 * @author Hans Witt, Claus Behl
 *
 * Version 1 (4.12.08)
 *
 */
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Bild implements IComponente {
	private CBild			obj;
	private BufferedImage	bild;
	protected int			breite		= 0;
	protected int			hoehe		= 0;
	
	protected int			xPos		= 0;
	protected int			yPos		= 0;
	protected boolean		sichtbar	= true;
	protected boolean		zoomen		= false;
	
	/**
	 * 
	 * @param filename
	 */
	public Bild(String filename) {
		this(Zeichnung.gibZeichenflaeche(), filename);
	}
	
	/**
	 * 
	 * @param datei
	 */
	public Bild(BilddateiAbstrakt datei) {
		this(Zeichnung.gibZeichenflaeche(), datei);
	}
	
	/**
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 * @param filename
	 */
	public Bild(int neueBreite, int neueHoehe, String filename) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe,
				filename);
	}
	
	/**
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 * @param datei
	 */
	public Bild(int neueBreite, int neueHoehe, BilddateiAbstrakt datei) {
		this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe, datei);
	}
	
	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param filename
	 */
	public Bild(int neuesX, int neuesY, int neueBreite, int neueHoehe,
			String filename) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe, filename);
	}
	
	/**
	 * 
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param datei
	 */
	public Bild(int neuesX, int neuesY, int neueBreite, int neueHoehe,
			BilddateiAbstrakt datei) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
				neueHoehe, datei);
	}
	
	/**
	 * 
	 * @param behaelter
	 * @param filename
	 */
	public Bild(IContainer behaelter, String filename) {
		this(behaelter, 0, 0, 100, 100, filename);
	}
	
	/**
	 * 
	 * @param behaelter
	 * @param datei
	 */
	public Bild(IContainer behaelter, BilddateiAbstrakt datei) {
		this(behaelter, 0, 0, 100, 100, datei);
	}
	
	/**
	 * 
	 * @param behaelter
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param filename
	 */
	public Bild(IContainer behaelter, int neuesX, int neuesY, int neueBreite,
			int neueHoehe, String filename) {
		bild = setzeBilddatei(filename);
		obj = new CBild(bild);
		behaelter.add(obj, 0);
		breite = neueBreite;
		hoehe= neueHoehe;
		
/*		if (bild != null) {
			setzeDimensionen(neuesX, neuesY, bild.getWidth(), bild.getHeight());
		} else {
			setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		}
*/
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);

		
		behaelter.validate();
	}
	
	/**
	 * 
	 * @param behaelter
	 * @param neuesX
	 * @param neuesY
	 * @param neueBreite
	 * @param neueHoehe
	 * @param datei
	 */
	public Bild(IContainer behaelter, int neuesX, int neuesY, int neueBreite,
			int neueHoehe, BilddateiAbstrakt datei) {
		bild = datei.leseBild();
		obj = new CBild(bild);
		behaelter.add(obj, 0);
		breite = neueBreite;
		hoehe= neueHoehe;

/*		if (bild != null) {
			setzeDimensionen(neuesX, neuesY, bild.getWidth(), bild.getHeight());
		} else {
			setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		}
*/
		setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
		
		behaelter.validate();
	}
	
	/**
	 * Das Interface IComponente fordert eine Methode die eine BasisComponente

	 * hinzuzuf�gen
	 */
	public BasisComponente getBasisComponente() {
		return obj;
	}
	
	static int	lesen	= 0;
	
	/*
	 * W�hle das Bild aus!
	 */
	private BufferedImage setzeBilddatei(String filename) {
		BufferedImage bBild;
		try {
			
			if (!Zeichnung.applet) {
				bBild = ImageIO.read(new File(filename));
			} else {
				lesen++;
				int height = -1;
				int width = -1;
				
				Image bild = Zeichnung.pApplet.getImage(Zeichnung.pApplet
						.getDocumentBase(), filename);
				height = bild.getHeight(null);
				width = bild.getWidth(null);
				if ((height < 0) || (width < 0)) {
					bBild = new BufferedImage(100, 100,
							BufferedImage.TYPE_BYTE_BINARY);
					
					Graphics2D g2 = bBild.createGraphics();
					g2.drawString("!" + lesen, 20, 50);
					
				} else {
					bBild = new BufferedImage(width, height,
							BufferedImage.TYPE_4BYTE_ABGR);
					Graphics2D g2 = bBild.createGraphics();
					g2.drawImage(bild, 0, 0, null);
				}
			}
		} catch (IOException e) {
			// javax.swing.JOptionPane.showMessageDialog(null,"Das Bild ist nicht vorhanden");
			bBild = new BufferedImage(100, 100, BufferedImage.TYPE_BYTE_BINARY);
			
			Graphics2D g2 = bBild.createGraphics();
			g2.drawString("Kein Bild !", 20, 50);
		}
		
		return bBild;
	}
	
	/*
	 * Bild wird in das Fenster eingepasst
	 */
	public void einpassen() {
		zoomen = true;
		
		double faktor = 1;
		
		if (bild != null) {
			faktor = (breite * 1.0) / (bild.getWidth() * 1.0);
			
			double faktor2 = (hoehe * 1.0) / (bild.getHeight() * 1.0);
			
			if (faktor2 < faktor) {
				faktor = faktor2;
			}
		} else {
			faktor = 1;
		}
		
		obj.setzeAnpassungsfaktor(faktor);
	}
	
	/*
	 * Keine Anpassung des Bilds
	 */
	public void normal() {
		zoomen = false;
		obj.setzeAnpassungsfaktor(1);
	}
	
	/*
	 * @param String filename Der Name der gew�nschten BilddateiAbstrakt
	 */
	public void wechsleBild(String filename) {
		bild = setzeBilddatei(filename);
		
		if (bild != null) {
			if (zoomen) {
				setzeDimensionen(xPos, yPos, breite, hoehe);
			} else {
				setzeDimensionen(xPos, yPos, bild.getWidth(), bild.getHeight());
			}
		}
		
		obj.wechsleBild(bild);
	}
	
	/*
	 * @param String filename Der Name der gew�nschten BilddateiAbstrakt
	 */
	public void wechsleBild(BilddateiAbstrakt datei) {
		bild = datei.leseBild();
		
		if (bild != null) {
			if (zoomen) {
				setzeDimensionen(xPos, yPos, breite, hoehe);
			} else {
				setzeDimensionen(xPos, yPos, bild.getWidth(), bild.getHeight());
			}
		}
		
		obj.wechsleBild(bild);
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
		
		if (zoomen) {
			einpassen();
		}
		
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
	public void verschieben(int dx, int dy) {
		setzePosition(xPos + dx, yPos + dy);
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
		
		if (zoomen) {
			einpassen();
		}
		
		obj.setzeDimensionen(xPos, yPos, breite, hoehe);
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
}

class CBild extends BasisComponente {
	private BufferedImage	bild;
	private Image			bildM;
	private double			anpassung	= 1;	// Faktor zum Einpassen an das
												
	// Fenstert
	
	/**
	 * Konstruktor ohne Beschriftung
	 */
	public CBild(BufferedImage bild) {
		this.bild = bild;
		this.bildM = bild;
	}
	
	public void wechsleBild(BufferedImage bild) {
		this.bild = bild;
		setzeZoomfaktor(zoomFaktor);
	}
	
	/**
	 * Die Darstellung der Komponente wird hier programmiert.
	 */
	public void paintComponentSpezial(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		// Graphik-Abmessungen
		breite = getSize().width;
		hoehe = getSize().height;
		
		g2.drawImage(bildM, 0, 0, null);
	}
	
	/*
	 * Anpassung f�r Einf�gen in das Fenster
	 */
	public void setzeAnpassungsfaktor(double zf) {
		anpassung = zf;
		setzeZoomfaktor(zoomFaktor);
	}
	
	public double setzeZoomfaktor(double zf) {
		zoomFaktor = zf;
		bzf = ((IContainer) this.getParent()).getBehaelterZoom();
		bildM = bild.getScaledInstance((int) (bild.getWidth() * zoomFaktor
				* bzf * anpassung),
				(int) (bild.getHeight() * zoomFaktor * bzf * anpassung),
				Image.SCALE_SMOOTH);
		zoomen();
		
		if (sichtbar) {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, breite, hoehe);
		} else {
			((IContainer) this.getParent()).setzeKomponentenKoordinaten(this,
					xPos, yPos, 0, 0);
		}
		
		return zoomFaktor;
	}
}

abstract class BilddateiAbstrakt {
	BufferedImage	bild;
	
	public BufferedImage leseBild() {
		return bild;
	}
	
}
