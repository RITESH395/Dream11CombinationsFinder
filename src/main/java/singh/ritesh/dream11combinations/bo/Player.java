package singh.ritesh.dream11combinations.bo;

import java.util.Objects;

public class Player
{
    private int id;
    private String playerName;
    private double credit;
    private Franchise playerFranchise;
    private PlayerType playerType;

    public Player(int id, String playerName, double playerDreamCredit, Franchise playerFranchise, PlayerType playerType)
    {
        this.id = id;
        this.playerName = playerName;
        this.credit = playerDreamCredit;
        this.playerFranchise = playerFranchise;
        this.playerType = playerType;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }

    public double getPlayerDreamCredit()
    {
        return credit;
    }

    public void setPlayerDreamCredit(double playerDreamCredit)
    {
        this.credit = playerDreamCredit;
    }

    public Franchise getPlayerFranchise()
    {
        return playerFranchise;
    }

    public void setPlayerFranchise(Franchise playerFranchise)
    {
        this.playerFranchise = playerFranchise;
    }

    public PlayerType getPlayerType()
    {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType)
    {
        this.playerType = playerType;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
            return true;
        else if (o == null || getClass() != o.getClass())
            return false;
        Player player = (Player) o;
        return /*Assuming combination of player name and franchise name will be unique*/
        playerName.equals(player.playerName)
                &&
        playerFranchise == player.playerFranchise;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(playerName, playerFranchise);
    }
}
