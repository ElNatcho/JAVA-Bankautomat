
public class ATM
{

    public Bild da;
    public Taste T1;
    public Taste T2;
    public Taste T3; 
    public Taste T4; 
    public Taste T5; 
    public Taste T6;
    public Taste T7; 
    public Taste T8; 
    public Taste T9; 
    public Taste T0; 
    public Taste T00; 
    public Taste Tback;  
    public Taste Tkarterein;
    public Taste Tkarteraus;
    public Zeichnung zeichnung;
    
    private static Display _display;
    private CInputController _inputController;
    private static CAnimationMgr _animationMgr;
    private static boolean _kontoAngemeldet, _kreditkarteEingeschoben;
    private static Alarm _alarm;
    
    public ATM()
    {
        zeichnung = new Zeichnung();
        
        _alarm=new Alarm();
        _animationMgr = new CAnimationMgr(720, 120);
        da=new Bild(0, 0, 720,720,"ATM3.0.png" );
        T1 = new Taste("1", 80,520,50,40);
        T2 = new Taste ("2",140,520,50,40);
        T3 = new Taste ("3",200,520,50,40);
        T4 = new Taste ("4",80,565,50,40);
        T5 = new Taste ("5",140,565,50,40);
        T6 = new Taste ("6",200,565,50,40);
        T7 = new Taste ("7",80,610,50,40);
        T8 = new Taste ("8",140,610,50,40);
        T9 = new Taste ("9",200,610,50,40);
        T0 = new Taste ("0",80,660,50,40);
        T00 = new Taste ("00",200,660,60,40);
        Tback = new Taste ("<",138,660,55,40);
        Tkarterein = new Taste ("karterein",720,560,150,80);
        Tkarteraus = new Taste ("karteraus",720,640,150,80);
        _inputController = new CInputController();

        T0.setzeSchriftfarbe("schwarz");
        T0.setzeSchriftgroesse(20);

        T7.setzeSchriftfarbe("schwarz");
        T7.setzeSchriftgroesse(20);
    
        T4.setzeSchriftfarbe("schwarz");
        T4.setzeSchriftgroesse(20);
 
        T1.setzeSchriftfarbe("schwarz");
        T1.setzeSchriftgroesse(20);
 
        T2.setzeSchriftfarbe("schwarz");
        T2.setzeSchriftgroesse(20);
       
        T3.setzeSchriftfarbe("schwarz");
        T3.setzeSchriftgroesse(20);

        T5.setzeSchriftfarbe("schwarz");
        T5.setzeSchriftgroesse(20);

        T8.setzeSchriftfarbe("schwarz");
        T8.setzeSchriftgroesse(20);

        T6.setzeSchriftfarbe("schwarz");
        T6.setzeSchriftgroesse(20);

        T9.setzeSchriftfarbe("schwarz");
        T9.setzeSchriftgroesse(20);

        T00.setzeSchriftfarbe("schwarz");
        T00.setzeSchriftgroesse(20);

        Tback.setzeSchriftfarbe("gelb");
        Tback.setzeSchriftgroesse(30);
       
        Tkarterein.setzeSchriftfarbe("schwarz");
        Tkarterein.setzeSchriftgroesse(20);

        Tkarteraus.setzeSchriftfarbe("schwarz");
        Tkarteraus.setzeSchriftgroesse(20);
       
        _inputController.knopfHinzufuegen(T0, 50);
        _inputController.knopfHinzufuegen(T1, 1);
        _inputController.knopfHinzufuegen(T2, 2);
        _inputController.knopfHinzufuegen(T3, 3);
        _inputController.knopfHinzufuegen(T4, 4);
        _inputController.knopfHinzufuegen(T5, 5);
        _inputController.knopfHinzufuegen(T6, 6);
        _inputController.knopfHinzufuegen(T7, 7);
        _inputController.knopfHinzufuegen(T8, 8);
        _inputController.knopfHinzufuegen(T9, 9);
        _inputController.knopfHinzufuegen(T00, 10);
        _inputController.knopfHinzufuegen(Tback, 11);
        _inputController.knopfHinzufuegen(Tkarterein, 16);
        _inputController.knopfHinzufuegen(Tkarteraus, 17);
        
        _display = new Display(40,30,450,450, zeichnung);
        _kontoAngemeldet = false;
        _kreditkarteEingeschoben = false;
        this.Update();
   
    }
    
    public void Update() 
    {
        while(true){
            this.handleKeyEvents();
            _display.update();
        }
    }
    
    public static void starteAlarm(){
        _alarm.Alarm_an();
    }
    
    public static void KontoauszugDrucken(){
        _animationMgr.kontoauszugVerschieben(400, 125, 350, 0);
    }
    
    public static void geld_auszahlen(int betrag){
        _animationMgr.geldBetragBewegen(betrag, 400, 125, 480, 0);
    }
    
    public static void geld_einzahlen(int betrag){
        _animationMgr.geldBetragBewegen(betrag, 880, 125, -480, 0);
    }
    
    public static void setKontoAngemeldet(boolean value){
        _kontoAngemeldet = value;
    }
    
    public static boolean getKKStatus(){
        return _kreditkarteEingeschoben;
    }
    
    public static void KKlogout(){
            _display.KontoAbmelden();
            _animationMgr.kreditkarteVerschieben(500, 400, 0, 500);
            _animationMgr.kreditkarteAnzeigen(false);
            _kreditkarteEingeschoben = false;
    }
    
    public void handleKeyEvents() {
        if(_inputController.wurdeGedrueckt(T0)){
            _display.handle_tastenfeldInput(0);
        }else if(_inputController.wurdeGedrueckt(T1)){
            _display.handle_tastenfeldInput(1);
        }else if(_inputController.wurdeGedrueckt(T2)){
            _display.handle_tastenfeldInput(2);
        }else if(_inputController.wurdeGedrueckt(T3)){
            _display.handle_tastenfeldInput(3);
        }else if(_inputController.wurdeGedrueckt(T4)){
            _display.handle_tastenfeldInput(4);
        }else if(_inputController.wurdeGedrueckt(T5)){
            _display.handle_tastenfeldInput(5);
        }else if(_inputController.wurdeGedrueckt(T6)){
            _display.handle_tastenfeldInput(6);
        }else if(_inputController.wurdeGedrueckt(T7)){
            _display.handle_tastenfeldInput(7);
        }else if(_inputController.wurdeGedrueckt(T8)){
            _display.handle_tastenfeldInput(8);
        }else if(_inputController.wurdeGedrueckt(T9)){
            _display.handle_tastenfeldInput(9);
        }else if(_inputController.wurdeGedrueckt(T00)){
            _display.handle_tastenfeldInput(0);
            _display.handle_tastenfeldInput(0);
        }else if(_inputController.wurdeGedrueckt(Tback)){
            _display.handle_tastenfeldInput(-1);
        }else if(_inputController.wurdeGedrueckt(Tkarterein) && !_kontoAngemeldet && !_kreditkarteEingeschoben){
            _animationMgr.kreditkarteVerschieben(500, 900, 0, -500);
            _animationMgr.kreditkarteAnzeigen(false);
            _display.loginKontonummer(0);
            _kreditkarteEingeschoben = true;
        }else if(_inputController.wurdeGedrueckt(Tkarteraus) && _kontoAngemeldet && !_display.kreditkarteGesperrt && _kreditkarteEingeschoben){
            KKlogout();
        }
    }
   
}























