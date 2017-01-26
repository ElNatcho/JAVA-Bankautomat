/**
 * Ein JLabel kann sich selbst zeichnen im Programmfenster Zeichnung
 *
 * @author Hans Witt
 *
 * Version 1.1 (14.7.2008)
 *        Hinzufügen von Statusvariablen für Position ...
 * Version: 1.1.1 (17.7.2008)
 *        Neue Komponenten werden von Unten nach Oben aufgebaut, d.h.vor die alten gesetzt
 * Version: 3 (9.8.2008)
 *        ergänzt für Behälter für GUI-Elemente
 * Version: 3.1 (14.8.2008)
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst
 * @version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt
 *
 */
import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JLabel;


public class Ausgabe implements IComponente {
    private CAusgabe obj;
    protected int breite = 0;
    protected int hoehe = 0;
    protected int xPos = 0;
    protected int yPos = 0;
    protected String anzeigeText = "Anzeige";
    protected int fontGroesse = -1;
    protected String farbe = "schwarz";

    /**
     * Konstruktor für Hauptfenster
     */
    public Ausgabe() {
        this(Zeichnung.gibZeichenflaeche());
    }

    /**
     * Konstruktor für Hauptfenster
     *
     * @param neueBreite
     * @param neueHoehe
     */
    public Ausgabe(String neuerText, int neueBreite, int neueHoehe) {
        this(Zeichnung.gibZeichenflaeche(), neuerText, 0, 0, neueBreite,
            neueHoehe);
    }

    /**
     * Konstruktor für Hauptfenster
     *
     * @param neuerText
     * @param neuesX
     * @param neuesY
     * @param neueBreite
     * @param neueHoehe
     */
    public Ausgabe(String neuerText, int neuesX, int neuesY, int neueBreite,
        int neueHoehe) {
        this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
            neueBreite, neueHoehe);
    }

    /**
     * Konstruktor
     *
     * @param behaelter
     */
    public Ausgabe(IContainer behaelter) {
        this(behaelter, "Anzeige", 0, 0, 100, 50);
    }

    /**
     * Konstruktor
     *
     * @param behaelter
     * @param neuerText
     * @param neueBreite
     * @param neueHoehe
     */
    public Ausgabe(IContainer behaelter, String neuerText, int neuesX,
        int neuesY, int neueBreite, int neueHoehe) {
        anzeigeText = neuerText;
        obj = new CAusgabe(anzeigeText);
        behaelter.add(obj, 0);
        setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
        setzeFarbe(farbe);
        behaelter.validate();
    }

    /**
     *  Das Interface IComponente fordert eine Methode die eine BasisComponente zurückliefert.
     *  Sie wird benötigt, um ein Objekt zu einem anderen Behälter hinzuzufügen
     */
    public BasisComponente getBasisComponente() {
        return obj;
    }

    public void setzeAusgabetext(String neuerText) {
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

    /**
     * Größe des Anzeigefelds ändern *
     */
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
        obj.setzeDimensionen(xPos, yPos, breite, hoehe);
    }
}


class CAusgabe extends BasisComponente {
    private JLabel label;

    /**
     * Konstruktor 
     */
    public CAusgabe(String text) {
        this.setLayout(new BorderLayout());
        label = new JLabel(text);
        label.setForeground(farbe);
        label.setFont(f);
        this.add(label);
        repaint();
    }

    public void setzeSchriftgroesse(int i) {
        setFontsize(i);
        label.setFont(f);
        repaint();
    }

    public void setText(String s) {
        label.setText(s);
    }

    public void setzeSchriftFarbe(String farbname) {
        farbe = StaticTools.getColor(farbname);
        label.setForeground(farbe);
        repaint();
    }

    @Override
    public void paintComponentSpezial(Graphics g) {
        // Hier nichts zu tun
    }
}
