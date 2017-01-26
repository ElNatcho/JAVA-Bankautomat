/**
 * Ein Eingabefeld kann sich selbst zeichnen im Programmfenster Zeichnung </br>
 *

 *
 * �bergibt man dem Eingabefeld den Link auf eine Klasse mit dem Interface ITuwas,
 * so wird bei einem Druck auf die <ENTER>-Taste die dort definierte Methode aufgerufen
 *
 * @author Hans Witt
 *
 *  * Version 1.1 (14.7.2008)
 *     Hinzuf�gen von Statusvariablen f�r Position ...
 * Version: 1.1.1 (17.7.2008)
 *        Neue Komponenten werden von Unten nach Oben aufgebaut, d.h.vor die alten gesetzt
 * Version: 3.1 (14.8.2008)
 *        Konstruktor auf int neuesX, int neuesY , int neueBreite, int neueHoehe angepasst
 * @version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt
 */
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class Eingabefeld implements IComponente {
    private CEingabefeld obj;
    protected int breite = 0;
    protected int hoehe = 0;
    protected int xPos = 0;
    protected int yPos = 0;
    protected String anzeigeText = "Eingabe...";
    protected int fontGroesse = -1;
    protected String schriftFarbe = "schwarz";
    protected String hintergrundFarbe = "weiss";

    /**
     * Konstuktor f�r Hauptfenster
     */
    public Eingabefeld() {
        this(Zeichnung.gibZeichenflaeche());
    }

    /**
     * Konstruktor f�r Hauptfenster
     *
     * @param neueBreite
     * @param neueHoehe
     */
    public Eingabefeld(String neuerText, int neueBreite, int neueHoehe) {
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
    public Eingabefeld(String neuerText, int neuesX, int neuesY,
        int neueBreite, int neueHoehe) {
        this(Zeichnung.gibZeichenflaeche(), neuerText, neuesX, neuesY,
            neueBreite, neueHoehe);
    }

    /**
     * Konstruktor
     *
     * @param behaelter
     */
    public Eingabefeld(IContainer behaelter) {
        this(behaelter, "Eingabe...", 0, 0, 150, 80);
    }

    public Eingabefeld(IContainer behaelter, String neuerText, int neuesX,
        int neuesY, int neueBreite, int neueHoehe) {
        anzeigeText = neuerText;
        obj = new CEingabefeld(anzeigeText);
        behaelter.add(obj, 0);
        setzeSchriftfarbe(schriftFarbe);
        setzeDimensionen(neuesX, neuesY, neueBreite, neueHoehe);
        behaelter.validate();
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
     * Eine Komponente mit Signalisierung muss eine eindeutige ID haben
     *
     * @param ID
     */
    public void setzeID(int ID) {
        obj.setzeID(ID);
    }

    /**
     * Callback-Funktion wird aufgerufen, wenn <ENTER> gedr�ckt wird Eine
     * Komponente mit Signalisierung braucht eine Objekt, dem es signalisieren
     * kann
     *
     * @param linkObj
     */
    public void setzeLink(ITuWas linkObj) {
        obj.setzeLink(linkObj);
    }

    /**
     * Callback-Funktion wird aufgerufen, wenn <ENTER> gedr�ckt wird
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
        obj.setzeHintergrundfarbe(hintergrundFarbe);
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

    public void setzeAusgabetext(String neuerText) {
        anzeigeText = neuerText;
        obj.linksbuendig();
        obj.setText(neuerText);
    }

    public void setzeTextRechtsbuendig(String neuerText) {
        anzeigeText = neuerText;
        obj.rechtsbuendig();
        obj.setText(neuerText);
    }

    public String leseText() {
        anzeigeText = obj.getText();

        return anzeigeText;
    }


    public String toString() {
        return leseText();
    }

    public void zentrieren() {
        obj.zentrieren();
    }


    public void setzeInteger(int zahl) {
        obj.rechtsbuendig();
        obj.setText("" + zahl + " ");
    }

    public int leseInteger(int def) {
        int value = 0;
        String neu = obj.getText();
        neu = neu.trim();

        try {
            value = Integer.parseInt(neu);
        } catch (Exception e) {
            value = def;
        }

        setzeInteger(value);

        return value;
    }

    public int leseIntegerGerundet(int def) {
        int value = 0;
        value = (int) Math.round(leseDouble(def));
        setzeInteger(value);

        return value;
    }

    public void setzeDouble(double zahl) {
        obj.rechtsbuendig();
        obj.setText("" + zahl + " ");
    }

    public double leseDouble(double def) {
        double value = 0;
        String neu = obj.getText().replace(',', '.');

        try {
            value = Double.parseDouble(neu);
        } catch (Exception e) {
            value = def;
        }

        setzeDouble(value);

        return value;
    }

    /**
     * Lese Wert als longFeld ( F�r Klasse Dezimal )
     *
     * erg[0] : Ziffernfolge erg[1] : Nachkommastellen
     *
     * @param longFeld
     * @return longFeld
     */
    public long[] leseLongFeld(long[] longFeld) {
        long[] erg = new long[2];
        erg[0] = longFeld[0]; // Ziffernfolge
        erg[1] = longFeld[1]; // Nachkommastellen

        boolean fehler = false;
        String neu = obj.getText().replace(',', '.');
        neu = neu.trim(); // Leerzeichen am Anfang uind Ende entfernt

        long nk = -1;
        long zf = 0;
        long vz = 1;

        for (int i = 0; !fehler && (i < neu.length()); i++) {
            char c = neu.charAt(i);

            if ((i == 0) && (c == '-')) {
                vz = -1;
            } else if ((i == 0) && (c == '+')) {
                vz = +1;
            } else if (Character.isDigit(c)) {
                zf = (zf * 10) + Character.digit(c, 10);

                if (nk > -1) {
                    nk++;
                }
            } else if (c == '.') {
                if (nk == -1) {
                    nk++;
                } else {
                    fehler = true; // zweiter Punkt!!
                }
            } else {
                fehler = true;
            }
        }

        if (!fehler) {
            erg[0] = vz * zf;

            if (nk == -1) {
                nk = 0;
            }

            erg[1] = nk;
        }

        setzeLongFeld(erg);

        return erg;
    }

    /**
     * Defaultwert 0
     *
     * @return
     */
    public long[] leseLongFeld() {
        long[] feld = new long[2];
        feld[0] = 0;
        feld[1] = 0;

        return leseLongFeld(feld);
    }

    public void setzeLongFeld(long[] longFeld) {
        obj.rechtsbuendig();

        String text = "" + longFeld[0];
        int i = (int) longFeld[1];

        if (i > 0) {
            int ln = text.length() - (int) longFeld[1];

            if (ln > 0) {
                text = text.substring(0, text.length() - (int) longFeld[1]) +
                    "," + text.substring(text.length() - (int) longFeld[1]);
            } else {
                ln = -ln;
                text = "0," +
                    "00000000000000000000000000000000000000000000".substring(0,
                        ln) + text;
            }
        }

        obj.setText("" + text + " ");
    }

    public static void main(String[] args) {
        Eingabefeld f = new Eingabefeld("Text", 300, 100);
        long[] feld = { 123456L, 15L };
        f.setzeAusgabetext("12.3.654");
        feld = f.leseLongFeld(feld);
    }
}


class CEingabefeld extends BasisComponente implements ActionListener {
    private JTextField eingabe;
    protected Color schriftFarbe = StaticTools.getColor("schwarz");

    /**
     * Konstruktor f�r Objekte der Klasse Taste
     */
    public CEingabefeld(String text) {
        this.setLayout(new BorderLayout());
        eingabe = new JTextField(text);
        setzeBasisfarbe("weiss");
        eingabe.setFont(f);
        eingabe.setHorizontalAlignment(SwingConstants.CENTER);
        eingabe.addActionListener(this);
        eingabe.updateUI();
        this.add(eingabe);
    }

    public void setzeSchriftgroesse(int i) {
        setFontsize(i);
        eingabe.setFont(f);
        repaint();
    }

    public void actionPerformed(ActionEvent arg0) {
        if (linkObj != null) {
            linkObj.tuWas(id);
        }
    }

    public void setText(String txt) {
        eingabe.setText(txt);
    }

    public String getText() {
        return eingabe.getText();
    }

    public void zentrieren() {
        eingabe.setHorizontalAlignment(SwingConstants.CENTER);
    }

    public void linksbuendig() {
        eingabe.setHorizontalAlignment(SwingConstants.LEFT);
    }

    public void rechtsbuendig() {
        eingabe.setHorizontalAlignment(SwingConstants.RIGHT);
    }

    public void setzeSchriftfarbe(String farbname) {
        schriftFarbe = StaticTools.getColor(farbname);
        eingabe.setForeground(schriftFarbe);
        repaint();
    }

    public void setzeHintergrundfarbe(String farbname) {
        farbe = StaticTools.getColor(farbname);
        eingabe.setBackground(farbe);
        repaint();
    }

    public void paintComponentSpezial(Graphics g) {
        // Hier nichts zu tun
    }
}
