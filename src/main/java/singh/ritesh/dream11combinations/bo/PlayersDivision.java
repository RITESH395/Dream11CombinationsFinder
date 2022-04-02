package singh.ritesh.dream11combinations.bo;

public class PlayersDivision {

    private int WKCount;
    private int BATCount;
    private int ALLRCount;
    private int BOWLCount;

    public int getWKCount() {
        return WKCount;
    }

    public void addWKCount() {
        this.WKCount += 1;
    }

    public int getBATCount() {
        return BATCount;
    }

    public void addBATCount() {
        this.BATCount += 1;
    }

    public int getALLCount() {
        return ALLRCount;
    }

    public void addALLRCount() {
        this.ALLRCount += 1;
    }

    public int getBOWLCount() {
        return BOWLCount;
    }

    public void addBOWLCount() {
        this.BOWLCount += 1;
    }
}
