package API;

import Lists.LinearNode;
import Lists.LinkedList;

public class Local extends java.lang.Object{
    protected long id;
    protected String type;
    protected String name;
    protected long energy;
    protected Player player;
    protected double latitude;
    protected double longitude;
    protected String conquererPlayer;
	protected String conquererTeam;
	protected long maxEnergy;
    protected long cooldown;
    protected LinkedList<LocalControl> LocalControl = new LinkedList<LocalControl>();
    protected LinkedList<TimeControl> timeControl = new LinkedList<TimeControl>();

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

    public Local(long id, String type, String name, double latitude, double longitude, long energy, long cooldown) {
        this.id = id;
        this.type = type;
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.energy = energy;
        this.cooldown = cooldown;
    }

	public long getId()
	{
		return this.id;
	}
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public long getEnergy() {
        return this.energy;
    }

    public String getName() {
        return this.name;
    }

    public String getType() {
        return this.type;
    }

    public double getLongitude(){
        return longitude;
    }

    public double getLatitude(){
        return latitude;
    }

    public void setLongitude(double longitude){
        this.longitude = longitude;
    }

    public void setLatitude(double latitude){
        this.latitude = latitude;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void loadPlayerEnergy(){
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

    public void addLocalControl(Local local, Double weight){
        LocalControl.add(new LocalControl(local, weight));
    }

    public void removeLocalControl(Local local){
        LinearNode<LocalControl> current = LocalControl.getHead();
        while(current != null && current.getElement().getLocal().equals(local)){
            current = current.getNext();
        }
        if(current != null){
            LocalControl.remove(current.getElement());
        }
    }

    public void loadEnergy(double energy) {
		this.energy += energy;
		if (this.energy > this.maxEnergy) {
			this.energy = this.maxEnergy;
		}
	}

	public void setMaxEnergy(int maxEnergy) {
		this.maxEnergy = maxEnergy;
	}
	public String getConquererPlayer() {
		return this.conquererPlayer;
	}

	public String getConquererTeam() {
		return this.conquererTeam;
	}

	public long getMaxEnergy() {
		return this.maxEnergy;
	}

	public void deloadEnergy(double energy) {
		this.energy -= energy;
		if (this.energy < 0) {
			this.energy = 0;
		}
	}

	public void conquer() {
		this.conquererPlayer = player.getName();
		this.conquererTeam = player.getTeam();
		player.setConqueredPortal();
	}

	public void reset() {
		this.conquererPlayer = "None";
		this.conquererTeam = "None";
	}

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
}
