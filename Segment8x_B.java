/**

 * 
 * Siebensegment8x
 * 
 * Beispiel f�r Siebensegmentanzeigen
 * 
 * Methoden anzeige(long zahl) f�r ganze Zahlen anzeige(double dZahl, int dp)
 * f�r Dezimalzahlen : dp: Anzahl der Stellen nach dem Komma dp = -1 -> kein
 * Dezimalpunkt
 * 
 * 
 * 
 * @author Hans Witt
 * 
 *         Version: 1.2 ( 19.7.2007 ) Fehler in der Anzeige Exception beim
 *         �berlauf bei Dezimalanzeigen Version: 3 (4.8.2008) erg�nzt f�r
 *         Containerklasse f�r GUI-Elemente
 * Version: 3.1 (14.8.2008) Konstruktor auf int neuesX, int neuesY , int
 *           neueBreite, int neueHoehe angepasst
 * @version: 3.2 (18.8.2008)
 *        Zustandsvariable auf protected gesetzt 
 */

public class Segment8x_B implements IComponente{

	private SiebenSegment_B[] s;
	private Behaelter anzeige;

	protected int hoehe = 100;
	protected int stellen = 6;

	protected int xPos = 0;
	protected int yPos = 0;

	public Segment8x_B(int stellen, int hoehe) {
		this(Zeichnung.gibZeichenflaeche(), 0,0, stellen, hoehe);
	}

	public Segment8x_B(int neuesX, int neuesY, int stellen, int hoehe) {
		this(Zeichnung.gibZeichenflaeche(), neuesX, neuesY, stellen, hoehe);
	}

	public Segment8x_B(IContainer behaelter, int neuesX, int neuesY,
			int stellen, int hoehe) {
		anzeige = new Behaelter(behaelter,0,0,1,1); // m�glichst klein
		s = new SiebenSegment_B[stellen];
		this.stellen = stellen;
		this.hoehe = hoehe;
		setzePositionSiebensegmentanzeige();
		setzePosition(neuesX, neuesY);
	}

	/**
	 *  Das Interface IComponente fordert eine Methode die eine BasisComponente zur�ckliefert.
	 *  Sie wird ben�tigt, um ein Objekt zu einem anderen Beh�lter hinzuzuf�gen
	 */
	public BasisComponente getBasisComponente() {
		return anzeige.getBasisComponente() ;
	}

	public void setzePositionSiebensegmentanzeige() {
		for (int i = 0; i < stellen; i++) {
			if (s[i] == null) {
				s[i] = new SiebenSegment_B(anzeige, hoehe * 6 / 10
						* (stellen - i - 1), 0, hoehe);
			} else {
				s[i].setzePosition(hoehe * 6 / 10 * (stellen - i - 1), 0);
			}
		}
		anzeige.setzeGroesse(hoehe * 6 / 10 * stellen, hoehe);
	}

	public void setzePosition(int x, int y) {
		xPos = x;
		yPos = y;
		anzeige.setzePosition(xPos, yPos);
		// for (int i = 0; i < stellen; i++) {
		// s[i].setzePosition( x + hoehe * 6/10 * (stellen - i - 1), y);
		// }
	}

	
	// Methode n�tig zum Hinzuf�gen mit Anpassung beim Beh�lter
	// Die Enden werden relativ zur aktuellen position verschoben
	public void verschieben(int dx , int dy ) {
		setzePosition(xPos + dx, yPos + dy );
	}

	public void setzeGroesse(int hoehe) {
		this.hoehe = hoehe;
		for (int i = 0; i < stellen; i++) {
			s[i].setzeGroesse(hoehe);
		}
		setzePositionSiebensegmentanzeige();
		// setzePosition(xPos, yPos);
	}

	/*
	 * g�ltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbe(String neueFarbe) {
		for (int i = 0; i < stellen; i++) {
			s[i].setzeFarbe(neueFarbe);
		}
		setzePosition(xPos, yPos);
	}

	/**
	 * Anzeige einer ganzen Zahl
	 * 
	 * @param zahl
	 */
	public void anzeige(long zahl) {
		int i = 0;
		for (i = 0; i < stellen; i++) {
			s[i].anzeige((int) (zahl % 10));
			zahl = zahl / 10;
		}
		if (zahl != 0) {
			for (i = 0; i < (stellen); i++) {
				s[i].zeigeDP(true);
			}
		}
	}

	/**
	 * DeAnzeige einer Dezimalzahl dp Stelle des Dezimalpunkts -1 = ohne 0 =
	 * ganz rechts n = neben der n-ten Stelle von rechts
	 */
	public void anzeige(double dZahl, int dp) {
		int i;
		boolean neg = false;
		if (dZahl < 0) {
			dZahl = -dZahl;
			s[stellen - 1].anzeigeMinus();
			neg = true;
		}

		for (i = 0; i < dp; i++) {
			dZahl = dZahl * 10;
		}

		long zahl = Math.round(Math.floor(dZahl));

		for (i = 0; i < (stellen - (neg ? 1 : 0)); i++) {
			s[i].anzeige((int) (zahl % 10));
			s[i].zeigeDP(i == (dp));
			zahl = zahl / 10;
		}

		if (zahl != 0) {
			for (i = 0; i <= (stellen - (neg ? 1 : 0)); i++) {
				if (i < stellen) {
					s[i].zeigeDP(true);
				}
			}
		}
	}

	public void laufen() {
		/**
		 * long i = 5999999952L; while (true) { anzeige(i); i++;
		 * OttoTools.warte(02); if (i > 9999999999L) i = 0; }
		 */

		double d = 2158917.234873;
		anzeige(d, 0);
		StaticTools.warte(2000);
		for (int i = 0; i < 7; i++) {
			StaticTools.warte(1000);
			anzeige(d, i);
		}
	}

	public static void main(String[] args) {
		Segment8x_B t = new Segment8x_B(6, 40);
		t.setzeGroesse(120);
		t.anzeige(15.756, 2);
	}

}
