package API;

import java.util.ArrayList;
import java.util.List;

import Exceptions.InvalidValue;
import Lists.Network;

public class Map extends Network<Local>{
	protected int count;

	public Map() {
		count = 0;
	}

	public  void addPortal(Portal portal) {
		addVertex(portal);
		count++;
	}

	public  void addConnector(Connector connector) {
		addVertex(connector);
		count++;
	}


	public void editPortal(Portal portal, double latitude, double longitude, int maxEnergy) throws InvalidValue {
		if(latitude < -90 || latitude > 90){
			throw new InvalidValue("Latitude fora dos limites");
		}else{
			portal.setLatitude(latitude);
		}

		if(longitude < -180 || longitude > 180){
			throw new InvalidValue("Longitude fora dos limites");
		}else{
			portal.setLongitude(longitude);
		}

		if(maxEnergy >= 0){
			portal.setMaxEnergy(maxEnergy);
		}else{
			throw new InvalidValue("Energia não pode ser negativa");
		}
   }

   public void editConnector(Connector connector, double latitude, double longitude, int energy) throws InvalidValue {
	if(latitude < -90 || latitude > 90){
		throw new InvalidValue("Latitude fora dos limites");
	}else{
		connector.setLatitude(latitude);
	}

	if(longitude < -180 || longitude > 180){
		throw new InvalidValue("Longitude fora dos limites");
	}else{
		connector.setLongitude(longitude);
	}

	if(energy >= 0){
		connector.setEnergy(energy);
	}else{
		throw new InvalidValue("Energia não pode ser negativa");
	}
}

	public void removePortal(Portal portal) {
        removeVertex(portal);
		count--;
    }

	public void removeConnector(Connector connector) {
		removeVertex(connector);
		count--;
	}

	public void addPConnection(Portal portal1, Portal portal2, double weight) {
		addEdge(portal1, portal2, weight);
		portal1.addPortal(portal2, weight);
		portal2.addPortal(portal1, weight);
	}

	public void addCConnection(Connector connector1, Connector connector2, double weight) {
		addEdge(connector1, connector2, weight);
		connector1.addConnector(connector2, weight);
		connector2.addConnector(connector1, weight);
	}

	public void addPCConnection(Portal portal, Connector connector, double weight) {
		addEdge(portal, connector, weight);
		portal.addConnector(connector, weight);
		connector.addPortal(portal, weight);
	}

	public void removePConnection(Portal portal1, Portal portal2) {
		removeEdge(portal1, portal2);
		portal1.removePortal(portal2);
		portal2.removePortal(portal1);
	}

	public void removeCConnection(Connector connector1, Connector connector2) {
		removeEdge(connector1, connector2);
		connector1.removeConnector(connector2);
		connector2.removeConnector(connector1);
	}

	public void removePCConnection(Portal portal, Connector connector) {
		removeEdge(portal, connector);
		portal.removeConnector(connector);
		connector.removePortal(portal);
	}

	public Connector[] getAllConnector(){
		List<Connector> connectorList = new ArrayList<>();
		for (Local local : this.vertices) {
			if (local instanceof Connector) {
				connectorList.add((Connector) local);
			}
		}
		return connectorList.toArray(new Connector[connectorList.size()]);
	}

}
