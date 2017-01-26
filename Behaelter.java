
/**

 *
 * Sie nimmt GUI-Elemente auf
 *
 * Diese werden relativ zum Container positioniert.
 *
 * Die Klasse kapselt die GUI-Darstellung der Elemente
 * Sie ist in einer "normalen" Java-Klasse die Erg�nzung,
 * in der die Graphische Darstellung der Objekte gekapselt ist.
 *
 * @author Hans Witt
 *
 * Version: 3 (9.8.2008)
 *        Containerklasse f�r GUI-Elemente
 * Version: 3.1 (14.8.2008)
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst
 * @version: 3.2 (22.10.2008)
 *        Erg�nzt um einfache Bewegungen (wie bei Rechteck, Dreieck,...)
 * @version: 4.0
 * Zoom f�r Beh�lter eingef�hrt
 * Anpassung bei BasisComponente f�r Wechseln zwischen Beh�ltern
 * Interface IComponente. Alle Klassen, die zum einem Beh�lter nachtr�glich hinzugef�gt werden k�nnen,
 * m�ssen das Interface Componente haben

 */
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;

import java.util.Vector;

import javax.swing.JComponent;
import javax.swing.JPanel;


@SuppressWarnings("unused")
public class Behaelter implements IContainer, IComponente {
    private CBehaelter obj;
    protected int breite = 0;
    protected int hoehe = 0;
    protected int xPos = 0;
    protected int yPos = 0;
    protected boolean sichtbar = true;
    protected double zoomInhalt = 1;
    protected String anzeigeText = "Anzeige";
    protected int fontGroesse = -1;
    protected String farbe = "schwarz";
    protected String hintergrundFarbe = null;

    /**
     * Konstuktor f�r Hauptfenster
     */
    public Behaelter() {
        this(Zeichnung.gibZeichenflaeche());
    }

    /**
     * Konstruktor f�r Hauptfenster
     *
     * @param neueBreite
     * @param neueHoehe
     */
    public Behaelter(int neueBreite, int neueHoehe) {
        this(Zeichnung.gibZeichenflaeche(), 0, 0, neueBreite, neueHoehe);
    }

    /**
     * Konstruktor f�r Hauptfenster
     *
     * @param neuesX
     * @param neuesY
     * @param neueBreite
     * @param neueHoehe
     */
    public Behaelter(int neuesX, int neuesY, int neueBreite, int neueHoehe) {
        this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, neueBreite,
            neueHoehe);
    }

    /**
     * Konstruktor
     *
     * @param behaelter
     */
    public Behaelter(IContainer behaelter) {
        this(behaelter, 0, 0, 100, 50);
    }

    /**
     * allgemeiner Konstuktor
     *
     * @param behaelter
     * @param neueBreite
     * @param neueHoehe
     */
    public Behaelter(IContainer behaelter, int neuesX, int neuesY,
        int neueBreite, int neueHoehe) {
        obj = new CBehaelter();
        behaelter.add(obj, 0);
        setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
        behaelter.validate();
    }

    public void setzeZoomfaktor(double zf) {
        zoomInhalt = obj.setzeZoomfaktor(zf);
    }

    public double getBehaelterZoom() {
        return obj.getBehaelterZoom();
    }

    public void hinzufuegen(IComponente comp) {
        comp.getBasisComponente().ausContainerEntfernen();
        obj.add(comp.getBasisComponente(), 0);
    }

    public void hinzufuegenUndAnpassen(IComponente comp) {
        comp.getBasisComponente().ausContainerEntfernen();
        obj.add(comp.getBasisComponente(), 0);
        comp.getBasisComponente().verschieben(-xPos, -yPos);
    }

    public void setzeBeschreibungstext(String neuerText) {
        anzeigeText = neuerText;
        obj.setText(anzeigeText);
    }

    public void setzeSchriftgroesse(int neueFontgroesse) {
        fontGroesse = neueFontgroesse;
        obj.setzeSchriftgroesse(fontGroesse);
    }

    public void setzeFarbe(String neueFarbe) {
        farbe = neueFarbe;
        obj.setzeSchriftFarbe(farbe);
    }

    public void setzeHintergrundfarbe(String neueFarbe) {
        hintergrundFarbe = neueFarbe;
        obj.setzeHintergrundfarbe(hintergrundFarbe);
    }

    public void setzeMitRand(boolean mitRand) {
        obj.setzeMitRand(mitRand);
    }

    /**
     * Das Interface IComponente fordert eine Methode die eine BasisComponente
     * zur�ckliefert. Sie wird ben�tigt, um ein Objekt zu einem anderen Beh�lter
     * hinzuzuf�gen
     */
    public BasisComponente getBasisComponente() {
        return obj;
    }

    /**
     * Gr��e des Anzeigefelds �ndern *
     */
    public void setzeGroesse(int neueBreite, int neueHoehe) {
        breite = neueBreite;
        hoehe = neueHoehe;
        obj.setzeGroesse((int) (breite / zoomInhalt), (int) (hoehe / zoomInhalt));
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
        obj.setzePosition((int) (xPos / zoomInhalt), (int) (yPos / zoomInhalt));
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
        obj.setzeDimensionen((int) (xPos / zoomInhalt),
            (int) (yPos / zoomInhalt), (int) (breite / zoomInhalt),
            (int) (hoehe / zoomInhalt));
    }

    /**
     * Mache sichtbar.
     */
    public void sichtbarMachen() {
        sichtbar = true;
        obj.sichtbarMachen();
    }

    /**
     * Mache unsichtbar.
     */
    public void unsichtbarMachen() {
        sichtbar = false;
        obj.unsichtbarMachen();
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

    public void setzeMitRaster(boolean mitRaster) {
        obj.setzeMitRaster(mitRaster);
    }

    public void setzeDeltaX(int deltaX) {
        obj.setzeDeltaX(deltaX);
    }

    public void setzeDeltaY(int deltaY) {
        obj.setzeDeltaY(deltaY);
    }

    /**
     * F�r Interface IContainer
     */
    public Component add(Component comp, int index) {
        return this.obj.add(comp, index);
    }

    public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
        int width, int height) {
        this.obj.setzeKomponentenKoordinaten(obj, x, y, width, height);
    }

    public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
        this.obj.setzeKomponentenGroesse(obj, width, height);
    }

    public void setzeKomponentenPosition(JComponent obj, int x, int y) {
        this.obj.setzeKomponentenPosition(obj, x, y);
    }

    public void validate() {
        obj.validate();
    }

    /*
     * Ende Interface IContainer
     */

    /**
     * gibt z.B. f�r den Dialog das JPanel-Objekt zur�ck
     */
    public JPanel getPanel() {
        return obj;
    }
}


class CBehaelter extends BasisComponente implements IContainer {
    private boolean mitRaster = false;
    private int deltaX = 100;
    private int deltaY = 100;
    private Vector<BasisComponente> unterComponenten = new Vector<BasisComponente>();
    private String anzeige = null;
    private boolean mitRand = false;
    int anzeigenbreite = 0;
    int anzeigenhoehe = 0;
    int ascend = 0;
    private String hintergrundFarbe = null;
    Color hfarbe;

    public CBehaelter() {
        this.setLayout(null);
    }

    public Component add(Component comp, int index) {
        Component erg = super.add(comp, index);
        unterComponenten.addElement((BasisComponente) comp);

        return erg;
    }

    /**
     * liefert den Zoomfaktor f�r den Beh�lter
     *
     * @return
     */
    public double getBehaelterZoom() {
        return zoomFaktor * ((IContainer) this.getParent()).getBehaelterZoom();
    }

    public double setzeZoomfaktor(double zf) {
        zoomFaktor = zf;
        bzf = ((IContainer) this.getParent()).getBehaelterZoom();
        fontGroesse = (int) Math.round(originalFontGroesse * zoomFaktor * bzf);
        setzeSchriftgroesse(fontGroesse);

        originalXPos = (int) (originalXPos / zoomFaktor);
        originalYPos = (int) (originalYPos / zoomFaktor);

        for (int i = 0; i < unterComponenten.size(); i++) {
            unterComponenten.get(i).zommfaktorAnpassen();
        }

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

    public void setzeMitRand(boolean mitRand) {
        this.mitRand = mitRand;
        Zeichnung.gibZeichenflaeche().repaint();
    }

    public void setzeSchriftgroesse(int i) {
        setFontsize(i);
        repaint();
    }

    public void setText(String s) {
        setzeMitRand(true);
        anzeige = s;
    }

    public void setzeSchriftFarbe(String farbname) {
        farbe = StaticTools.getColor(farbname);
        repaint();
    }

    public void setzeHintergrundfarbe(String neueFarbe) {
        hintergrundFarbe = neueFarbe;

        if (hintergrundFarbe != null) {
            hfarbe = StaticTools.getColor(hintergrundFarbe);
        }
    }

    public void setzeMitRaster(boolean mitRaster) {
        this.mitRaster = mitRaster;
        //		Zeichnung.gibJFrame().repaint();
        Zeichnung.gibZeichenflaeche().repaint();
    }

    public void setzeDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public void setzeDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public void setzeSichtbarkeit(boolean sichtbar) {
        this.getParent().setVisible(sichtbar);
    }

    // Wird von der Graaphikkomponente zum Positionieren im Beh�lter aufgerufen
    public void setzeKomponentenKoordinaten(JComponent obj, int x, int y,
        int width, int height) {
        obj.setBounds(x, y, width, height);
        repaint();
        validate();
        //		Zeichnung.gibJFrame().validate();
        Zeichnung.gibZeichenflaeche().validate();
    }

    public void setzeKomponentenGroesse(JComponent obj, int width, int height) {
        obj.setSize(width, height);
        obj.repaint();
        repaint();
        validate();
        //		Zeichnung.gibJFrame().repaint();
        Zeichnung.gibZeichenflaeche().repaint();
    }

    public void setzeKomponentenPosition(JComponent obj, int x, int y) {
        obj.setLocation(x, y);
        obj.repaint();
        repaint();
        validate();
        Zeichnung.gibZeichenflaeche().validate();

        //		Zeichnung.gibJFrame().validate();
    }

    /**
     * Die Darstellung der Komponente wird hier programmiert.
     */
    public void paintComponentSpezial(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;

        // Graphik-Abmessungen
        int breite = getSize().width - 1;
        int hoehe = getSize().height - 1;

        if (mitRand) {
            if (hintergrundFarbe != null) {
                g2.setColor(hfarbe);
                g2.fill3DRect(0, 0, breite, hoehe, true);
            }

            g2.setColor(farbe);

            if (anzeige != null) {
                Rectangle2D rec = (g2.getFontMetrics()).getStringBounds(" " +
                        anzeige + " ", g2);
                anzeigenbreite = (int) rec.getWidth();
                anzeigenhoehe = (int) rec.getHeight();
                ascend = g2.getFontMetrics().getMaxAscent();
            }

            if (anzeige != null) {
                g2.drawString(anzeige, 6, ascend);
                g2.drawLine(0, anzeigenhoehe / 2, 4, anzeigenhoehe / 2);
                g2.drawLine(anzeigenbreite + 1, anzeigenhoehe / 2, breite,
                    anzeigenhoehe / 2);
                g2.drawLine(0, anzeigenhoehe / 2, 0, hoehe);
                g2.drawLine(breite, anzeigenhoehe / 2, breite, hoehe);
                g2.drawLine(0, hoehe, breite, hoehe);
            } else {
                g2.draw3DRect(0, 0, breite, hoehe, true);
            }
        }

        if (mitRaster) {
            Color farbe = StaticTools.getColor("schwarz");
            g.setColor(farbe);

            int hor = deltaX;

            while (hor < breite) {
                g2.drawLine(hor, 0, hor, hoehe);
                hor += deltaX;
            }

            int ver = deltaY;

            while (ver < hoehe) {
                g2.drawLine(0, ver, breite, ver);
                ver += deltaY;
            }

            g2.draw3DRect(0, 0, breite, hoehe, true);
            g2.draw3DRect(1, 1, breite - 2, hoehe - 2, true);
        }
    }
}
