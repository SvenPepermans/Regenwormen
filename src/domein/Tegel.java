package domein;



public class Tegel
{
    int nummer;
    int waarde;

    public Tegel(int nummer) 
    {
        this.nummer = nummer;
        berekenwaarde();
        
    }
    public void berekenwaarde()
    {
        if (nummer >= 21 && nummer <= 24) 
        {
            waarde = 1;
        }
        else
        {
            if (nummer >=25 && nummer <= 28) 
            {
                waarde = 2;
            }
            else
            {
                if (nummer>=29 && nummer <= 32) 
                {
                 waarde = 3;
                }
                else
                {
                    if (nummer >= 33 && nummer <=36) 
                    {
                        waarde = 4;
                    }
                }
            }
        }
        
    }

    
}
