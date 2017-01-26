/**

 * Zustand von nichtGedr�ckt auf gedr�ckt ge�ndert werden
 * 
 * Zur�ckgesetzt kann der Zustand nur durch das Programm werden.
 * 
 * �ber die Methode setzeTextNichtGedrueckt und setzeGedrueckt kann der
 * Anzeigetext f�r beide M�glichkeiten gesetzt werden
 * 
 * Im Konstruktor werden beide Anzeigetext �bergeben
 * 
 * �ber die Methode istGedrueckt() kann der Status abgefragt werden. Der Status
 * wird dadurch nicht zur�ckgesetzt !
 * 
 * setzeGewaehlt() und setzeNichtGewaehlt() setzen den Status der Umschalttaste
 * 
 * �ber Callback kann der Feststell-Tastendruck weitergeleitet werden
 * 
 * @author Hans Witt
 * 
 *         Version: 3.1 (16.8.2008)
 * 
 * @version: 3.2 (18.8.2008) Zustandsvariable auf protected gesetzt
 */

public class FeststellTaste implements ITuWas,IComponente {
	
	private Taste		knopf;
	protected int		breite						= 0;
	protected int		hoehe						= 0;
	protected int		xPos						= 0;
	protected int		yPos						= 0;
	protected int		fontGroesse					= -1;
	protected boolean	gedrueckt					= false;
	protected String	anzeigeTextNichtGedrueckt	= "Dr�ckmich";
	protected String	anzeigeTextGedrueckt		= "Gedrueckt";
	protected String	knopfFarbeNichtGedrueckt	= "blau";
	protected String	knopfFarbeGedrueckt			= "blau";
	protected String	schriftFarbe				= "schwarz";
	
	/**
	 * Konstruktor f�r Hauptfenster
	 */
	public FeststellTaste() {
		this(Zeichnung.gibZeichenflaeche());
	}
	
	/**
	 * Konstruktor f�r Hauptfenster
	 * 
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public FeststellTaste(String textNichtGedrueckt, String textGedrueckt,
			int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), textNichtGedrueckt, textGedrueckt,
				0, 0, neueBreite, neueHoehe);
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
	public FeststellTaste(String textNichtGedrueckt, String textGedrueckt,
			int neuesX, int neuesY, int neueBreite, int neueHoehe) {
		this(Zeichnung.gibZeichenflaeche(), textNichtGedrueckt, textGedrueckt,
				neuesX, neuesY, neueBreite, neueHoehe);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 */
	public FeststellTaste(IContainer behaelter) {
		this(behaelter, "Bitte Dr�cken", "Gedr�ckt! Bitte warten!", 0, 0, 180,
				80);
	}
	
	/**
	 * Konstruktor
	 * 
	 * @param behaelter
	 * @param neuerText
	 * @param neueBreite
	 * @param neueHoehe
	 */
	public FeststellTaste(IContainer behaelter, String textNichtGedrueckt,
			String textGedrueckt, int neuesX, int neuesY, int neueBreite,
			int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		
		anzeigeTextNichtGedrueckt = textNichtGedrueckt;
		anzeigeTextGedrueckt = textGedrueckt;
		
		knopf = new Taste(behaelter, anzeigeTextNichtGedrueckt, neuesX, neuesY,
				neueBreite, neueHoehe);
		gedrueckt = false;
		zeigeStatus();
		knopf.setzeLink(this, 0);
	}
	
	/**
	 *  Das Interface IComponente fordert eine Methode die eine BasisComponente zur�ckliefert.
	 *  Sie wird ben�tigt, um ein Objekt zu einem anderen Beh�lter hinzuzuf�gen
	 */
	public BasisComponente getBasisComponente() {
		return knopf.getBasisComponente() ;
	}

	public void setzePosition(int neuesX, int neuesY) {
		xPos = neuesX;
		yPos = neuesY;
		knopf.setzePosition(xPos, yPos);
	}
	
	
	// Methode n�tig zum Hinzuf�gen mit Anpassung beim Beh�lter
	// Die Enden werden relativ zur aktuellen position verschoben
	public void verschieben(int dx , int dy ) {
		setzePosition(xPos + dx, yPos + dy );
	}

	public void setzeGroesse(int neueBreite, int neueHoehe) {
		breite = neueBreite;
		hoehe = neueHoehe;
		knopf.setzeGroesse(breite, hoehe);
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
		knopf.setzeDimensionen(xPos, yPos, breite, hoehe);
	}
	
	public void setzeTextNichtGedrueckt(String textNichtGedrueckt) {
		anzeigeTextNichtGedrueckt = textNichtGedrueckt;
		zeigeStatus();
	}
	
	public void setzeTextGedrueckt(String textGedrueckt) {
		anzeigeTextGedrueckt = textGedrueckt;
		zeigeStatus();
	}
	
	public void setzeSchriftgroesse(int neueFontgroesse) {
		fontGroesse = neueFontgroesse;
		knopf.setzeSchriftgroesse(fontGroesse);
	}
	
	/**
	 * Wartet, bis die Taste gedr�ckt wurde. Dabei wird der Status zur�ckgesetzt
	 */
	public void warteBisGedrueckt() {
		knopf.warteBisGedrueckt();
		setzeNichtGewaehlt();
	}
	
	/**
	 * Status der Komponente abrufen
	 */
	public boolean istGedrueckt() {
		boolean erg = gedrueckt;
		return erg;
	}
	
	/**
	 * Status der Komponente auf >>gew�hlt<< setzen
	 */
	public void setzeGewaehlt() {
		gedrueckt = true;
		zeigeStatus();
	}
	
	/**
	 * Status der Komponente auf >>Nicht gew�hlt<< setzen
	 */
	public void setzeNichtGewaehlt() {
		gedrueckt = false;
		zeigeStatus();
	}
	
	/*
	 * g�ltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbeNichtGedrueckt(String neueFarbe) {
		knopfFarbeNichtGedrueckt = neueFarbe;
		zeigeStatus();
	}
	
	/*
	 * g�ltige Farben: "rot", "gelb", "blau", "gruen", "lila" , "schwarz" ,
	 * "weiss" , "grau","pink","magenta","orange","cyan","hellgrau"
	 */
	public void setzeFarbeGedrueckt(String neueFarbe) {
		knopfFarbeGedrueckt = neueFarbe;
		zeigeStatus();
	}
	
	private void zeigeStatus() {
		if (gedrueckt) {
			knopf.setzeAusgabetext(anzeigeTextGedrueckt);
			knopf.setzeHintergrundfarbe(knopfFarbeGedrueckt);
		} else {
			knopf.setzeAusgabetext(anzeigeTextNichtGedrueckt);
			knopf.setzeHintergrundfarbe(knopfFarbeNichtGedrueckt);
		}
	}
	
	public void setzeSchriftfarbe(String neueFarbe) {
		schriftFarbe = neueFarbe;
		knopf.setzeSchriftfarbe(schriftFarbe);
	}
	
	private ITuWas	linkObj;
	private int		id	= 0;	// ID der Komponente f�r Callback
	
	/**
	 * Eine Komponente mit Signalisierung muss eine eindeutige ID haben
	 * @param ID
	 */
	public void setzeID(int ID) {
		this.id = ID ;
	}

	/**
	 * Eine Komponente mit Signalisierung braucht eine Objekt, dem es signalisieren kann
	 * @param linkObj
	 */
	public void setzeLink(ITuWas linkObj) {
		this.linkObj= linkObj;
	}
	
	/**
	 * Callback-Funktion wird aufgerufen, wenn die Taste gedr�ckt wird
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
		this.linkObj = linkObj;
		id = ID;
	}
	
	/**
	 * Diese Methode wird von der Taste der Komponente selbst aufgerufen
	 */
	public void tuWas(int ID) { // Wird aufgerufen beim Dr�cken des Knopfs
		if (!gedrueckt) {
			gedrueckt = true;
			zeigeStatus();
			if (linkObj != null) linkObj.tuWas(id); // Weiterleitung
		}
		
	}
}
