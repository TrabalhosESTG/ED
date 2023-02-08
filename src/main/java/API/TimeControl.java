package API;

/**
 * This class is used to store the time of a player passes in a "Connector"
*/
public class TimeControl {
	protected String playerName;
	protected long time;

	/**
	 * Creates a new TimeControl object
	 *
	 * @param playerName Name of the player
	 * @param time Time of when the player passes the "Connector"
	*/
	public TimeControl(String playerName, long time) {
		this.playerName = playerName;
		this.time = time;
	}

	/**
	* Obtains the name of the player
	*
	* @return Name of the player
	*/
	public String getPlayerName() {
		return playerName;
	}

	/**
	* Obtains the time of when the player passes the "Connector"
	*
	* @return Time of when the player passes the "Connector"
	*/
	public long getTime() {
		return time;
	}

	/**
	* Sets the time of when the player passes the "Connector"
	*/
	public void setTime(long time) {
		this.time = time;
	}

}
