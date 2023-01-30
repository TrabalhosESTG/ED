package API;
import java.sql.Time;

import Lists.LinearNode;
import Lists.LinkedList;

public class Connector {
	protected Player player;
	protected String name;
	protected double energy;
	protected double latitude;
	protected double longitude;
	protected LinkedList<TimeControl> timeControl = new LinkedList<TimeControl>();
	protected LinkedList<ConnectorControl> connectorControl = new LinkedList<ConnectorControl>();
	protected LinkedList<PortalControl> portalControl = new LinkedList<PortalControl>();
	


	public Connector(String name) {
		this.name = name;
		this.energy = 0;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public void setEnergy(double energy) {
		this.energy = energy;
	}

	public double getEnergy() {
		return energy;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
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
			Time time = new Time(System.currentTimeMillis());
			int diff = (int) ((time.getTime() - timeControl.getTime())/60000);
			if(diff >= 5){
				player.loadEnergy(energy);
				current.getElement().setTime(System.currentTimeMillis());
			}else{
				System.out.println("You can't load energy yet");
			}
		}

	}

	public void addConnector(Connector connector, Double weight){
		connectorControl.add(new ConnectorControl(connector, weight));
	}

	public void addPortal(Portal portal, Double weight){
		portalControl.add(new PortalControl(portal, weight));
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
		LinearNode<PortalControl> current = portalControl.getHead();
		while(current != null && !current.getElement().getPortal().equals(portal)){
			current = current.getNext();
		}
		if(current != null){
			portalControl.remove(current.getElement());
		}
	}
}

