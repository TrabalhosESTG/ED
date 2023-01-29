package API;

import Exceptions.InvalidValue;
import Interfaces.NetworkADT;
import Lists.Network;

public class Map extends Network<String>{
	private int count;
	private NetworkADT network = new Network();
	
	public Map() {
		count = 0;
	}

	public <T> void addPortal(T portal) {
		network.addVertex(portal);
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

	public <T> void removePortal(T portal) {
        network.removeVertex(portal);
		count--;
    }
}
