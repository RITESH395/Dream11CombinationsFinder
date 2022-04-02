package singh.ritesh.dream11combinations.bo;

import java.util.Objects;

public class Player implements Comparable<Player>{
	private int id;
	private String playerName;
	private double credit;
	private Franchise playerFranchise;
	private PlayerType playerType;
	private String includePlayer;


	public Player(int id, String playerName, double playerDreamCredit, Franchise playerFranchise,
			PlayerType playerType, String includePlayer) {
		this.id = id;
		this.playerName = playerName;
		this.credit = playerDreamCredit;
		this.playerFranchise = playerFranchise;
		this.playerType = playerType;
		this.includePlayer = includePlayer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public double getPlayerDreamCredit() {
		return credit;
	}

	public void setPlayerDreamCredit(double playerDreamCredit) {
		this.credit = playerDreamCredit;
	}

	public Franchise getPlayerFranchise() {
		return playerFranchise;
	}

	public void setPlayerFranchise(Franchise playerFranchise) {
		this.playerFranchise = playerFranchise;
	}

	public PlayerType getPlayerType() {
		return playerType;
	}

	public void setPlayerType(PlayerType playerType) {
		this.playerType = playerType;
	}

	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

	public String getIncludePlayer() {
		return includePlayer;
	}

	public void setIncludePlayer(String includePlayer) {
		this.includePlayer = includePlayer;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		else if (o == null || getClass() != o.getClass())
			return false;
		Player player = (Player) o;
		return /* Assuming combination of player name and franchise name will be unique */
		playerName.equals(player.playerName) && playerFranchise == player.playerFranchise;
	}

	@Override
	public int hashCode() {
		return Objects.hash(playerName, playerFranchise);
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", playerName=" + playerName + ", credit=" + credit + ", playerFranchise="
				+ playerFranchise + ", playerType=" + playerType + "]";
	}

	@Override
	public int compareTo(Player p) {
		if (this.getPlayerType().getId() > p.getPlayerType().getId())
			return 1;
		else if(this.getPlayerType().getId() < p.getPlayerType().getId())
			return -1;
		else
			return 0;
	}
}
