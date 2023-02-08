package API;

import Exceptions.InvalidValue;
import Lists.ArrayUnorderedList;
import Lists.LinearNode;
import Lists.LinkedList;
import Lists.Network;

public class Map extends Network<Local>{
	protected int count;
	protected LinkedList<Local> locals;

	public Map() {
		this.count = 0;
		this.locals = new LinkedList<Local>();
	}

	public void addLocal(Local local) {
		addVertex(local);
		locals.add(local);
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

	public int findLocalById(long id){
		LinearNode<Local> current = this.locals.getHead();
		for(int i = 0; i < count; i++){
			if(current.getElement().getId() == id){
				return i;
			}
			current = current.getNext();
		}

		System.out.println("Não existe nenhum local com este id");
		return -1;
	}

	public Local[] getAllConnectors(){
		Local[] connectors = new Local[count];
		LinearNode<Local> current = this.locals.getHead();
		int i = 0;
		while(current != null){
			if(current.getElement().getType().equals("Connector")){
				connectors[i] = current.getElement();
				i++;
			}
			current = current.getNext();
		}
		return connectors;
	}

	public Local[] getAllPortals(){
		Local[] portals = new Local[count];
		LinearNode<Local> current = this.locals.getHead();
		int i = 0;
		while(current != null){
			if(current.getElement().getType().equals("Portal")){
				portals[i] = current.getElement();
				i++;
			}
			current = current.getNext();
		}
		return portals;
	}

	public Local[] orderLocalsById(String type){
		Local[] locals;
		if(type.equals("Connector")){
			locals = getAllConnectors();
		}else{
			locals = getAllPortals();
		}
		for (int j = 0; j < locals.length; j++) {
			for (int k = 0; k < locals.length; k++) {
				if(locals[j] != null && locals[k] != null){
					if(locals[j].getId() < locals[k].getId()){
					Local aux = locals[j];
					locals[j] = locals[k];
					locals[k] = aux;
				}
				}
			}
		}
		return locals;
	}


	public String shortestPathBetweenAnother(Local local1, Local local2, Local local3) {
		String ret = "<h1>";
		double path1 = shortestPathWeight(getIndex(local1), getIndex(local3));
		double path2 = shortestPathWeight(getIndex(local3), getIndex(local2));
		int[] path = returnShortestPath(getIndex(local1), getIndex(local3));
		for (int i = 0; i < path.length; i++) {
			ret += getVertices()[path[i]].getId() + " -> ";
		}
		path = returnShortestPath(getIndex(local3), getIndex(local2));
		for (int i = 0; i < path.length; i++) {
			ret += getVertices()[path[i]].getId() + " -> " ;
		}
		ret += "</h1>";
		ret += "<h2>Distância total: " + (path1 + path2) + "</h2>";
		return ret;
	}

	public String shortestPath(Local local1, Local local2) {
		String ret = "<h1>";
		double path = shortestPathWeight(getIndex(local1), getIndex(local2));
		int[] path2 = returnShortestPath(getIndex(local1), getIndex(local2));
		for (int i = 0; i < path2.length; i++) {
			ret += getVertices()[path2[i]].getId() + " -> ";
		}
		ret += "</h1>";
		ret += "<h2>Distância total: " + path + "</h2>";
		return ret;
	}

	public int getCount() {
		return count;
	}
}
