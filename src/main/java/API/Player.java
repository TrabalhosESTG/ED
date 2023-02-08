package API;

import org.json.simple.JSONObject;

/**
*Class that represents a player in the game.
*
* @author Guilherme Silva (8210190)
* @author David Francisco (8210088)
*/
public class Player {
	protected String name;
	protected long level;
	protected String team;
	protected double exp;
	protected double energy;
	protected long totalEnergy;
	protected long id;
	protected double latitude;
	protected double longitude;
	protected long conqueredPortals;

	/**
	* Constructor for the Player class.
	* Initializes the variables with the values passed as arguments.
	*
	* @param name Name of the player
	* @param id ID of the player
	*/
	public Player(String name, long id) {
		this.name = name;
		this.level = 1;
		this.team = "None";
		this.exp = 0;
		this.energy = 100;
		this.totalEnergy = 100;
		this.id = id;
		this.conqueredPortals = 0;
		this.latitude = 0;
		this.longitude = 0;
	}

	/**
	* Constructor for the Player class.
	* Initializes the variables with the values passed as arguments.
	*
	* @param name Name of the player
	* @param team Team of the player
	* @param id ID of the player
	*/
	public Player(String name, String team, long id) {
		this.name = name;
		this.level = 1;
		this.team = team;
		this.exp = 0;
		this.energy = 100;
		this.totalEnergy = 100;
		this.id = id;
		this.conqueredPortals = 0;
		this.latitude = 0;
		this.longitude = 0;
	}

	/**
	* Constructor for the Player class.
	* Initializes the variables with the values passed as arguments.
	*
	* @param name Name of the player
	* @param level Level of the player
	* @param team Team of the player
	* @param exp Experience of the player
	* @param energy Energy of the player
	* @param totalEnergy Total energy of the player
	* @param id ID of the player
	* @param latitude Latitude of the player
	* @param longitude Longitude of the player
	* @param conqueredPortals Number of portals conquered by the player
	*/
	public Player(String name, long level, String team, double exp, double energy, long totalEnergy, long id, double latitude, double longitude, long conqueredPortals) {
		this.name = name;
		this.level = level;
		this.team = team;
		this.exp = exp;
		this.energy = energy;
		this.totalEnergy = totalEnergy;
		this.id = id;
		this.latitude = latitude;
		this.longitude = longitude;
		this.conqueredPortals = conqueredPortals;
	}

	/**
	* Method that increases the level of the player.
	*/
	public void levelUp() {
		this.level++;
		this.totalEnergy += 10;
		this.exp = 0;
	}

	/**
	* Method that sets the latitude of the player.
	*
	* @param latitude Latitude of the player
	*/
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	/**
	* Method that sets the longitude of the player.
	*
	* @param longitude Longitude of the player
	*/
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	/**
	* Method that returns the latitude of the player.
	*
	* @return Latitude of the player
	*/
	public double getLatitude() {
		return this.latitude;
	}

	/**
	* Method that returns the longitude of the player.
	*
	* @return Longitude of the player
	*/
	public double getLongitude() {
		return this.longitude;
	}

	/**
	* Method that increases the experience of the player.
	*/
	public void gainExp() {
		this.exp += Math.pow((this.level/0.07), 2);
		if (this.exp >= 100) {
			levelUp();
		}
	}

	/**
	* Adds one to the number of portals conquered by the player.
	*/
	public void setConqueredPortal() {
		this.conqueredPortals++;
	}

	/**
	* Method that returns the number of portals conquered by the player.
	*
	* @return Number of portals conquered by the player
	*/
	public long getConqueredPortal() {
		return this.conqueredPortals;
	}

	/**
	* Method that returns the name of the player.
	*
	* @return Name of the player
	*/
	public String getName() {
		return this.name;
	}

	/**
	* Method that returns the level of the player.
	*
	* @return Level of the player
	*/
	public long getLevel() {
		return this.level;
	}

	/**
	* Method that returns the ID of the player.
	*
	* @return ID of the player
	*/
	public long getId() {
		return this.id;
	}

	/**
	* Method that returns the team of the player.
	*
	* @return Team of the player
	*/
	public String getTeam() {
		return this.team;
	}

	/**
	* Method that returns the experience of the player.
	*
	* @return Experience of the player
	*/
	public double getExp() {
		return this.exp;
	}

	/**
	* Method that returns the energy of the player.
	*
	* @return Energy of the player
	*/
	public double getEnergy() {
		return this.energy;
	}

	/**
	* Method that returns the total energy of the player.
	*
	* @return Total energy of the player
	*/
	public long getTotalEnergy() {
		return this.totalEnergy;
	}

	/**
	* Method that sets the team of the player.
	*
	* @param team Team of the player
	*/
	public void setTeam(String team) {
		this.team = team;
	}

	/**
	* Method that loads energy to the player.
	*
	* @param energy Energy to be loaded
	*/
	public void loadEnergy(double energy) {
		this.energy += energy;
		if (this.energy > this.totalEnergy) {
			this.energy = this.totalEnergy;
		}
	}

	/**
	* Method that removes energy from the player.
	*
	* @param energy Energy to be removed
	*/
	public void removeEnergy(double energy) {
		this.energy -= energy;
		if (this.energy < 0) {
			this.energy = 0;
		}
	}

	/**
	* Method that returns the player in JSON format.
	*
	* @return Player in JSON format
	*/
	public JSONObject criarPlayerJSON() {
		JSONObject playerJSON = new JSONObject();
		playerJSON.put("id", this.getId());
		playerJSON.put("name", this.getName());
		playerJSON.put("team", this.getTeam());
		playerJSON.put("energy", this.getEnergy());
		playerJSON.put("latitude", this.getLatitude());
		playerJSON.put("longitude", this.getLongitude());
		return playerJSON;
	}
}
