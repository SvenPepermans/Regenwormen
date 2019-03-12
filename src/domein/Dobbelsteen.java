package domein;

import java.util.Random;

public class Dobbelsteen {

    private  final String[] Waarde = {"1", "2", "3", "4", "5", "Worm"};

    public  String rolDobbelsteen() {

        int index = new Random().nextInt(Waarde.length);
        String random = (Waarde[index]);
        return random;
    }
}


