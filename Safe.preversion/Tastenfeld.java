public class Tastenfeld implements ITuWas
{
    Taste T1 =new Taste("0",200,20,70,40);
    Taste T2 =new Taste("0",290,20,70,40);

    Taste T3 =new Taste("0",380,20,70,40);

    Taste T4 =new Taste("0",470,20,70,40);

    int a = 0;
    int b = 0;
    int c = 0;
    int d = 0;

    public Tastenfeld(){
        

                StaticTools.warte(50);   
            
            T1.setzeLink(this,1);
            T2.setzeLink(this,2);
            T3.setzeLink(this,3);
            T4.setzeLink(this,4);

        }
    

    public void tuWas (int ID)
    {
        if (ID==1)
        {
            a++; 
            a=a%10;
            T1.setzeAusgabetext(""+a); 
        }
        if (ID==2)
        {
            b++; 
            b=b%10;
            T2.setzeAusgabetext(""+b);
        }
        if (ID==3)
        {
            c++; 
            c=c%10;
            T3.setzeAusgabetext(""+c);
        }
        if (ID==4)
        {
            d++; 
            d=d%10;
            T4.setzeAusgabetext(""+d);
        }
    }

    
}
