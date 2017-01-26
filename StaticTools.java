/**
 * Statische Methoden für FrameworkGUI
 *
 * Methoden für Warten, Farben, Zeit
 *
 * @author Hans Witt
 *
 * @version: 1.2 (19.7.2008)
 *        Übersetzung NOSW in ENUM Richtung NOSW
 * @version: 1.3 (12.12.2008)
 *        gibZufall(int zahl) verbessert
 */
import java.awt.Color;

import java.util.Calendar;
import java.util.Random;


public class StaticTools {
    /**
     * Statische Methoden für alle
     */
    private static String normalFarbe = "hellgrau";
    static Color CF01;
    static Color CF02;
    static Color CF03;
    static Color CF04;
    static Color CF05;
    static Color CF06;
    static Color CF07;
    static Color CF08;
    static Color CF09;
    static Color CF10;
    private static Random zufall;

    /**
     * Rückgabe einer Standardfarbe
     *
     * @return Farbstring
     */
    public static String leseNormalfarbe() {
        return normalFarbe;
    }

    /**
     * Gibt den Farbwert (Color) für Java zurück Gültige Parameter sind "rot",
     * "gelb", "blau", "gruen", "lila" , "schwarz" , "weiss" ,
     * "grau","pink","magenta","orange","cyan","hellgrau"
     */
    public static Color getColor(String farbname) {
        if (farbname.equals("rot")) {
            return Color.red;
        } else if (farbname.equals("schwarz")) {
            return Color.black;
        } else if (farbname.equals("blau")) {
            return Color.blue;
        } else if (farbname.equals("gelb")) {
            return Color.yellow;
        } else if (farbname.equals("gruen")) {
            return Color.green;
        } else if (farbname.equals("lila")) {
            return Color.magenta;
        } else if (farbname.equals("weiss")) {
            return Color.white;
        } else if (farbname.equals("grau")) {
            return Color.gray;
        } else if (farbname.equals("pink")) {
            return Color.pink;
        } else if (farbname.equals("magenta")) {
            return Color.magenta;
        } else if (farbname.equals("orange")) {
            return Color.orange;
        } else if (farbname.equals("cyan")) {
            return Color.cyan;
        } else if (farbname.equals("hellgrau")) {
            return Color.lightGray;
        } else if (farbname.equals("dunkelgrau")) {
            return Color.darkGray;
        } else if (farbname.equals("F01")) {
            return getF01();
        } else if (farbname.equals("F02")) {
            return getF02();
        } else if (farbname.equals("F03")) {
            return getF03();
        } else if (farbname.equals("F04")) {
            return getF04();
        } else if (farbname.equals("F05")) {
            return getF05();
        } else if (farbname.equals("F06")) {
            return getF06();
        } else if (farbname.equals("F07")) {
            return getF07();
        } else if (farbname.equals("F08")) {
            return getF08();
        } else if (farbname.equals("F09")) {
            return getF09();
        } else if (farbname.equals("F10")) {
            return getF10();
        } else {
            return Color.lightGray;
        }
    }

    public static Color leseNormalZeichenfarbe() {
        return getColor(normalFarbe);
    }

    public static void setzeFarbe(String farbname, int r, int g, int b) {
        if (farbname.equals("F01")) {
            setF01(r, g, b);
        } else if (farbname.equals("F02")) {
            setF02(r, g, b);
        } else if (farbname.equals("F03")) {
            setF03(r, g, b);
        } else if (farbname.equals("F04")) {
            setF04(r, g, b);
        } else if (farbname.equals("F05")) {
            setF05(r, g, b);
        } else if (farbname.equals("F06")) {
            setF06(r, g, b);
        } else if (farbname.equals("F07")) {
            setF07(r, g, b);
        } else if (farbname.equals("F08")) {
            setF08(r, g, b);
        } else if (farbname.equals("F09")) {
            setF09(r, g, b);
        } else if (farbname.equals("F10")) {
            setF10(r, g, b);
        }
    }

    private static Color getF01() {
        if (CF01 == null) {
            CF01 = new Color(127, 127, 127);
        }

        return CF01;
    }

    private static void setF01(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF01 = new Color(r, g, b);
    }

    private static Color getF02() {
        if (CF02 == null) {
            CF02 = new Color(127, 127, 127);
        }

        return CF02;
    }

    private static void setF02(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF02 = new Color(r, g, b);
    }

    private static Color getF03() {
        if (CF03 == null) {
            CF03 = new Color(127, 127, 127);
        }

        return CF03;
    }

    private static void setF03(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF03 = new Color(r, g, b);
    }

    private static Color getF04() {
        if (CF04 == null) {
            CF04 = new Color(127, 127, 127);
        }

        return CF04;
    }

    private static void setF04(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF04 = new Color(r, g, b);
    }

    private static Color getF05() {
        if (CF05 == null) {
            CF05 = new Color(127, 127, 127);
        }

        return CF05;
    }

    private static void setF05(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF05 = new Color(r, g, b);
    }

    private static Color getF06() {
        if (CF06 == null) {
            CF06 = new Color(127, 127, 127);
        }

        return CF06;
    }

    private static void setF06(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF06 = new Color(r, g, b);
    }

    private static Color getF07() {
        if (CF07 == null) {
            CF07 = new Color(127, 127, 127);
        }

        return CF07;
    }

    private static void setF07(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF07 = new Color(r, g, b);
    }

    private static Color getF08() {
        if (CF08 == null) {
            CF08 = new Color(127, 127, 127);
        }

        return CF08;
    }

    private static void setF08(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF08 = new Color(r, g, b);
    }

    private static Color getF09() {
        if (CF09 == null) {
            CF09 = new Color(127, 127, 127);
        }

        return CF09;
    }

    private static void setF09(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF09 = new Color(r, g, b);
    }

    private static Color getF10() {
        if (CF10 == null) {
            CF10 = new Color(127, 127, 127);
        }

        return CF10;
    }

    private static void setF10(int r, int g, int b) {
        if ((r < 0) || (r > 255)) {
            r = 127;
        }

        if ((g < 0) || (g > 255)) {
            g = 127;
        }

        if ((b < 0) || (b > 255)) {
            b = 127;
        }

        CF10 = new Color(r, g, b);
    }

    /**
     * Warte für die angegebenen Millisekunden. Mit dieser Operation wird eine
     * Verzögerung definiert, die für animierte Zeichnungen benutzt werden kann.
     *
     * @param millisekunden
     *            die zu wartenden Millisekunden
     */
    public static void warte(int millisekunden) {
        try {
            Thread.sleep(millisekunden);
        } catch (Exception e) {
            // Exception ignorieren
        }
    }

    public static Richtung getRichtung(String r) {
        if (r == "N") {
            return Richtung.N;
        } else if (r == "O") {
            return Richtung.O;
        } else if (r == "S") {
            return Richtung.S;
        } else if (r == "W") {
            return Richtung.W;
        } else if (r == "NO") {
            return Richtung.NO;
        } else if (r == "SO") {
            return Richtung.SO;
        } else if (r == "SW") {
            return Richtung.SW;
        } else if (r == "NW") {
            return Richtung.NW;
        } else {
            return Richtung.N;
        }
    }

    /**
     * liefert die aktuelle Tageszeit
     *
     * @return Tageszeit in Sekunden
     */
    public static long jetzt() {
        long zeit = 500;
        Calendar cal = Calendar.getInstance();
        zeit = cal.get(Calendar.SECOND) + (60 * cal.get(Calendar.MINUTE)) +
            (3600 * cal.get(Calendar.HOUR_OF_DAY));

        return zeit;
    }

    /**
     * liefert die aktuelle Zeit
     *
     * @return Stunde
     */
    public static int jetzt_Stunde() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * liefert die aktuelle Zeit
     *
     * @return Minute in der aktuellen Stunde
     */
    public static int jetzt_Minute() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.MINUTE);
    }

    /**
     * liefert die aktuelle Zeit
     *
     * @return Sekunde in der aktuellen Minute
     */
    public static int jetzt_Sekunde() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.SECOND);
    }

    /**
     * liefert das aktuelle Datum
     *
     * @return Tag im Monat
     */
    public static int jetzt_Tag() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * liefert das aktuelle Datum
     *
     * @return Tag im Monat
     */
    public static int jetzt_WochenTag() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * liefert das aktuelle Datum
     *
     * @return Monat im Jahr. Januar = 1
     */
    public static int jetzt_Monat() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.MONTH) + 1; // Erster Monat ist 1 !
    }

    /**
     * liefert das aktuelle Datum
     *
     * @return Jahr
     */
    public static int jetzt_Jahr() {
        Calendar cal = Calendar.getInstance();

        return cal.get(Calendar.YEAR);
    }

    /**
     * Bestimmt eine zufällige Zahl zwischen 0 und 1.
     *
     * @return Zufallszahl zwischen 0 und 1.
     */
    public static double gibZufall() {
        if (zufall == null) {
            zufall = new Random();
        }

        return zufall.nextDouble();
    }

    /**
     * Bestimmt eine symmetrisch zu 0 verteilte zufällige Zahl.
     *
     * @param radius
     *            gibt den Bereich für die erzeugten Zufallszahlen an.
     * @return Zufallszahl zwischen -radius und radius.
     */
    public static double gibSymZufall(double radius) {
        if (zufall == null) {
            zufall = new Random();
        }

        return ((2 * zufall.nextDouble()) - 1) * radius;
    }

    /**
     * Bestimmt eine zufällige ganze Zahl zwischen 0 und zahl.
     *
     * @return Zufallszahl zwischen 0 und zahl (einschließlich).
     */
    public static int gibZufall(int zahl) {
        if (zufall == null) {
            zufall = new Random();
        }

        return zufall.nextInt(zahl + 1);
    }

    /**
     * Ausrichtung
     *
     * @author Witt
     */
    public enum Richtung {N,
        O,
        S,
        W,
        NO,
        SO,
        SW,
        NW;
    }
}
