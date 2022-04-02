package singh.ritesh.dream11combinations.bo;

public enum Franchise
{
    MI("Mumbai Indians"),
    DC("Delhi Capitals"),
    RCB("Royal Challengers Bangalore"),
    KKR("Kolkata knight riders"),
    SRH("SunRisers Hyderabad"),
    CSK("Chennai Super kings"),
    RR("Rajasthan Royals"),
    PK("Punjab Kings"),
    GT("Gujarat Titans"),
    LSG("Lucknow Super Giants");

    private String name;

    Franchise(String s) {
        this.name = s;
    }
}
