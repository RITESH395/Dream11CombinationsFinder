package singh.ritesh.dream11combinations.bo;

public enum PlayerType
{
    WK(1),
    BAT(2),
    AR(3),
    BOWL(4);

    int id;

    public int getId() {
        return id;
    }

    PlayerType(int i) {
        this.id = i;
    }
}