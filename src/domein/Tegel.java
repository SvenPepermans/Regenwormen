package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Tegel
{
    private ArrayList<Integer> tegelWaarden = new ArrayList<>(Arrays.asList(21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36));
    int waarde;
    private ArrayList<Integer> spelerWaarden = new ArrayList<>();

    public int getWaarde() {
        return waarde;
    }

    public void setWaarde(int waarde) {
        this.waarde = waarde;
    }
    
    public ArrayList<Integer> getTegelWaarden() {
        return tegelWaarden;
    }    
    
    public boolean controle(){
        boolean controle = false;
        do{
        if(tegelWaarden.contains(waarde)){
            controle = true;
            break;
        } else if(waarde < 21){
            controle = false;
            break;
        }else{
            waarde = waarde-1;
            }
        }while(waarde >= 20);
        return controle;
    }
    
    public void voegTegelToe(){ // Verwijder tegel????
        if(waarde == Collections.max(tegelWaarden)){
        } else{
            tegelWaarden.remove((tegelWaarden.size()-1));
        }
    }
    
    public void neemTegel(){
        for(int i = 0; i <= tegelWaarden.size(); i++){
            if(tegelWaarden.get(i)== waarde){
                tegelWaarden.remove(i);
                spelerWaarden.add(waarde);
            }
        }
    }
    
}
