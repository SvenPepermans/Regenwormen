package domein;

public class Tegel {

    private int nummer;
    private int waarde;
    

    public Tegel(int nummer) {
        this.nummer = nummer;
        berekenwaarde();

    }

    public int getWaarde() {
        return waarde;
    }

    public int getNummer() {
        return nummer;
    }

    public void setNummer(int nummer) {
        this.nummer = nummer;
    }

    public void berekenwaarde() {
        if (nummer >= 21 && nummer <= 24) {
            waarde = 1;
        } else if (nummer >= 25 && nummer <= 28) {
            waarde = 2;
        } else if (nummer >= 29 && nummer <= 32) {
            waarde = 3;
        } else if (nummer >= 33 && nummer <= 36) {
            waarde = 4;
        }
        
    }
    @Override
        public String toString() {
        return String.format("%s", getNummer());
}

}
