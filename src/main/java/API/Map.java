package API;

import Exceptions.InvalidValue;
import Lists.Network;

public class Map extends Network<Local>{
	protected int count;

	public Map() {
		count = 0;
	}

	public  void addLocal(Local local) {
		addVertex(local);
		count++;
	}

	public void editLocal(Local local, double latitude, double longitude, int maxEnergy) throws InvalidValue {
		if(latitude < -90 || latitude > 90){
			throw new InvalidValue("Latitude fora dos limites");
		}else{
			local.setLatitude(latitude);
		}

		if(longitude < -180 || longitude > 180){
			throw new InvalidValue("Longitude fora dos limites");
		}else{
			local.setLongitude(longitude);
		}

		if(maxEnergy >= 0){
			if(local.getType().equals("Portal")){
				local.setMaxEnergy(maxEnergy);
			}else{
				local.setEnergy(maxEnergy);
			}
		}else{
			throw new InvalidValue("Energia não pode ser negativa");
		}
   }

	public void removeLocal(Local local) {
        removeVertex(local);
		count--;
    }

	public void addLocalConnection(Local local1, Local local2, double weight) {
		addEdge(local1, local2, weight);
		local1.addLocalControl(local2, weight);
		local2.addLocalControl(local1, weight);
	}

	public void removeLocalConnection(Local local1, Local local2) {
		removeEdge(local1, local2);
		local1.removeLocalControl(local2);
		local2.removeLocalControl(local1);
	}
}
