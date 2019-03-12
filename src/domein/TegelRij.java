package domein;

import java.util.ArrayList;

public class TegelRij
{
    private ArrayList<Tegel> tegels = new ArrayList<>();

    
    public void vulTegelRij()
    {
        for (int i = 21; i <= 36; i++) 
        {
         Tegel tegel = new Tegel(i);
         tegels.add(tegel);
        }
        
    }
}
