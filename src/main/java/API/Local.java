package API;

import Lists.LinearNode;
import Lists.LinkedList;

/**
* Class that represents a local in the game
* A local can be a "Portal" or a "Connector"
*
*@author Guilherme Silva (8210190)
*@author David Francisco (8210088)
*/

public class Local{
    protected long id;
	protected String name;
    protected String type;
    protected Player player;
    protected double latitude;
    protected double longitude;
    protected String conquererPlayer;
	protected String conquererTeam;
	protected long energy;
	protected long maxEnergy;
    protected long cooldown;
    protected LinkedList<LocalControl> LocalControl = new LinkedList<LocalControl>();
    protected LinkedList<TimeControl> timeControl = new LinkedList<TimeControl>();

	/**
	* Constructor for the Local class when @param type is "Portal"
	* Initializes the variables with the values passed as arguments
	*
	* @param id ID of the local
	* @param type Type of the local, can be "Portal"
	* @param name Name of the local
	* @param latitude Latitude of the local
	* @param longitude Longitude of the local
	* @param energy Energy of the local
	* @param maxEnergy Maximum energy of the local
	* @param conquererPlayer Name of the player that conquered the local
	* @param conquererTeam Name of the team that conquered the local
	*/
    public Local(long id, String type, String name, double latitude, double longitude, long energy, long maxEnergy, String conquererPlayer, String conquererTeam) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.energy = energy;
        this.maxEnergy = maxEnergy;
		this.conquererPlayer = conquererPlayer;
		this.conquererTeam = conquererTeam;
    }

	/**
	* Constructor for the Local class when @param type is "Connector"
	* Initializes the variables with the values passed as arguments
	*
	* @param id ID of the local
	* @param type Type of the local, can be "Connector"
	* @param name Name of the local
	* @param latitude Latitude of the local
	* @param longitude Longitude of the local
	* @param energy Energy of the local
	* @param cooldown Cooldown of the local
	*/
    public Local(long id, String type, String name, double latitude, double longitude, long energy, long cooldown) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.energy = energy;
        this.cooldown = cooldown;
    }

	/**
	*Getter for the ID of the local
	*
	* @return ID of the local
	*/
	public long getId()
	{
		return this.id;
	}

	/**
	*Setter for the player of the local
	*
	* @param player Player of the local
	*/
    public void setPlayer(Player player) {
        this.player = player;
    }

	/**
	*Getter for the player of the local
	*
	* @return Player of the local
	*/
    public Player getPlayer() {
        return this.player;
    }

	/**
	*Setter for the energy of the local
	*
	* @param energy Energy of the local
	*/
    public void setEnergy(int energy) {
        this.energy = energy;
    }

	/**
	*Getter for the energy of the local
	*
	* @return Energy of the local
	*/
    public long getEnergy() {
        return this.energy;
    }

	/**
	*Getter for the name of the local
	*
	* @return Name of the local
	*/
    public String getName() {
        return this.name;
    }

	/**
	*Getter for the type of the local
	*
	* @return Type of the local
	*/
    public String getType() {
        return this.type;
    }

	/**
	*Getter for the longitude of the local
	*
	* @return Longitude of the local
	*/
    public double getLongitude(){
        return longitude;
    }

	/**
	*Getter for the latitude of the local
	*
	* @return Latitude of the local
	*/
    public double getLatitude(){
        return latitude;
    }

	/**
	*Setter for the longitude of the local
	*
	* @param longitude longitude of the local
	*/
    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

	/**
	*Setter for the latitude of the local
	*
	* @param latitude latitude of the local
	*/
    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

	/**
	*Setter for the name of the local
	*
	* @param name Name of the local
	*/
    public void setName(String name) {
        this.name = name;
    }

	/**
	* Method that loads the energy of the player when the local is a "Connector" and the player is not in the cooldown
	*/
    public void loadPlayerEnergy(){
		if(this.type.equals("Portal"))
			return;
		LinearNode<TimeControl> current = timeControl.getHead();
		while( current != null && !current.getElement().getPlayerName().equals(player.getName())){
			current = current.getNext();
		}
		if(current == null){
			timeControl.add(new TimeControl(player.getName(), (System.currentTimeMillis())));
			player.loadEnergy(energy);
		}else{
			TimeControl timeControl = current.getElement();
		    Long time = System.currentTimeMillis();
			int diff = (int) ((time - timeControl.getTime())/60000);
			if(diff >= cooldown){
				player.loadEnergy(energy);
				current.getElement().setTime(System.currentTimeMillis());
			}else{
				System.out.println("You can't load energy yet");
			}
		}

	}

	/**
	* Method that adds a local control to the local to keep track of routes between locals
	*/
    public void addLocalControl(Local local, Double weight){
        LocalControl.add(new LocalControl(local, weight));
    }

	/**
	* Method that removes a local control from the local
	*/
    public void removeLocalControl(Local local){
        LinearNode<LocalControl> current = LocalControl.getHead();
        while(current != null && !current.getElement().getLocal().equals(local)){
            current = current.getNext();
        }
        if(current != null){
            LocalControl.remove(current.getElement());
        }
    }

	/**
	* Method that transfers energy from the player to the local if the latter is a "Portal"
	*
	* @param energy Energy to be transferred
	*/
    public void loadEnergy(double energy) {
		this.energy += energy;
		if (this.energy > this.maxEnergy) {
			this.energy = this.maxEnergy;
		}
	}

	/**
	* Setter for the max energy of the local when the local is a "Portal"
	*/
	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}

	/**
	*Getter for the conquerer player of the local
	*
	* @return Conquerer player of the local
	*/
	public String getConquererPlayer() {
		return this.conquererPlayer;
	}

	/**
	*Getter for the conquerer team of the local
	*
	* @return Conquerer team of the local
	*/
	public String getConquererTeam() {
		return this.conquererTeam;
	}

	/**
	*Getter for the max energy of the local
	*
	* @return Max energy of the local
	*/
	public long getMaxEnergy() {
		return this.maxEnergy;
	}

	/**
	* Method that removes energy from the local when the local is a "Portal"
	*/
	public void deloadEnergy(double energy) {
		this.energy -= energy;
		if (this.energy < 0) {
			this.energy = 0;
		}
	}

	/**
	* Method that conquers the local when the local is a "Portal"
	*/
	public void conquer() {
		this.conquererPlayer = player.getName();
		this.conquererTeam = player.getTeam();
		player.setConqueredPortal();
	}

	/**
	* Method that resets the local when the local is a "Portal"
	*/
	public void reset() {
		this.conquererPlayer = "None";
		this.conquererTeam = "None";
	}

	/**
	* Method that asks the player if he wants to load energy from the local when the local is a "Portal"
	*/
	public void askToLoadEnergy() {
        if(this.type.equals("Connector")){
            System.out.println("Este local não permite carregar energia.");
            return;
        }
		if(this.energy == this.maxEnergy) {
			System.out.println("This portal is already full of energy.");
			return;
		}
		System.out.println("Do you want to load energy? (Y/N)");
		String answer = System.console().readLine();
		if (answer.equals("Y")) {
			System.out.println("You have " + this.energy + " energy.");
			System.out.println("How much energy do you want to load?");
			double energy = Double.parseDouble(System.console().readLine());
			loadEnergy(energy);
		}
	}

	/**
	* Method that asks the player if he wants to deload energy from the local when the local is a "Portal"
	*/
	public void askToDeloadEnergy() {
        if(this.type.equals("Connector")){
            System.out.println("Este local não permite descarregar energia.");
            return;
        }
		if(this.energy == 0) {
			System.out.println("This portal is already empty of energy.");
			return;
		}
		String answer;
		do{
		System.out.println("Do you want to deload energy? (Y/N)");
		answer = System.console().readLine();
		if (answer.equals("Y")) {
			System.out.println("You have " + this.energy + " energy.");
			System.out.println("How much energy do you want to deload?");
			double energy = Double.parseDouble(System.console().readLine());
			deloadEnergy(energy);
		}
	}while(answer.equals("Y"));
	}

	/**
	* Method that asks the player if he wants to conquer the local when the local is a "Portal"
	*/
	public void askToConquer() {
        if(this.type.equals("Connector")){
            System.out.println("Este local não permite conquistar");
            return;
        }
		if (player.getEnergy() < (player.getTotalEnergy() * 0.25)) {
			System.out.println("You don't have enough energy to conquer this portal.");
			return;
		}
		System.out.println("Do you want to conquer this portal? (Y/N)");
		String answer = System.console().readLine();
		if (answer.equals("Y")) {
			if (player.getEnergy() < (player.getTotalEnergy() * 0.25)) {
				System.out.println("You don't have enough energy to conquer this portal.");
				return;
			}
			System.out.println("The minimum energy to conquer this portal is" + (player.getTotalEnergy() * 0.25) + "energy. You have " + player.getEnergy() + "energy.");
			System.out.println("How much energy do you want to use to conquer this portal?");
			double energy = Double.parseDouble(System.console().readLine());
			player.removeEnergy(energy);
			conquer();
			System.out.println("You have conquered" + this.name + "!");
		}
	}

	/**
	* Gets the LocalControl list
	*
	* @return LocalControl list
	*/
	public LinkedList<LocalControl> getLocalControl() {
		return LocalControl;
	}
}
