package API;

import Lists.LinearNode;

public class Portal extends Local{
	protected String conquererPlayer;
	protected String conquererTeam;
	protected int maxEnergy;

	public Portal(int id, String name, double latitude, double longitude, int maxEnergy, int energy) {
		super(id, name, latitude, longitude);
		this.conquererPlayer = "None";
		this.conquererTeam = "None";
		this.energy = energy;
		this.maxEnergy = maxEnergy;
	}
	public Portal(int id, String name, double latitude, double longitude, int maxEnergy) {
		super(id, name, latitude, longitude);
		this.conquererPlayer = "None";
		this.conquererTeam = "None";
		this.energy = 0;
		this.maxEnergy = maxEnergy;
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

	public int getMaxEnergy() {
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

	public void addConnector(Connector connector, Double weight){
		connectorControl.add(new ConnectorControl(connector, weight));
	}

	public void addPortal(Portal portal, Double weight){
		portalControl.add(new LocalControl(portal, weight));
	}

	public void removeConnector(Connector connector){
		LinearNode<ConnectorControl> current = connectorControl.getHead();
		while(current != null && !current.getElement().getConnector().equals(connector)){
			current = current.getNext();
		}
		if(current != null){
			connectorControl.remove(current.getElement());
		}
	}

	public void removePortal(Portal portal){
		LinearNode<LocalControl> current = portalControl.getHead();
		while(current != null && !current.getElement().getPortal().equals(portal)){
			current = current.getNext();
		}
		if(current != null){
			portalControl.remove(current.getElement());
		}
	}
}
