package API;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import Exceptions.InvalidValue;
import Lists.ArrayUnorderedList;
import Lists.LinearNode;
import Lists.LinkedList;
import Lists.Network;

/** 
*Class that represents the map of the game
*It extends the Network class
*
*@author Guilherme Silva (8210190)
*@author David Francisco (8210088) 
*/
public class Map extends Network<Local>{
	protected int count;
	protected LinkedList<Local> locals;

	/** 
	* Constructor of the class 
	*/
	public Map() {
		this.count = 0;
		this.locals = new LinkedList<Local>();
	}

	/**
	* Method that adds a local to the map
	* 
	* @param local
	*/
	public void addLocal(Local local) {
		addVertex(local);
		locals.add(local);
		count++;
	}

	/**
	 * Method that edits a local
	 * @param local
	 * @param latitude
	 * @param longitude
	 * @param maxEnergy
	 * @throws InvalidValue
	*/
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

   	/**
	* Method that removes a local from the map
	* @param local
	*/
	public void removeLocal(Local local) {
		removeVertex(local);
		locals.remove(local);
		count--;
	}

	/**
	* Method that adds a connection between two locals
	* @param local1
	* @param local2
	* @param weight
	*/
	public void addLocalConnection(Local local1, Local local2, double weight) {
		addEdge(findIndexById(local1.getId()), findIndexById(local2.getId()), weight);
		local1.addLocalControl(local2, weight);
		local2.addLocalControl(local1, weight);
	}

	/**
	 * Method that removes a connection between two locals
	 * @param local1
	 * @param local2
	 */
	public void removeLocalConnection(Local local1, Local local2) {
		removeEdge(findIndexById(local1.getId()), findIndexById(local2.getId()));
		local1.removeLocalControl(local2);
		local2.removeLocalControl(local1);
	}

	/**
	* Method that returns a local by its id
	* @param id
	* @return Local or null if the local doesn't exist
	*/
	public Local findLocalById(long id){
		LinearNode<Local> current = this.locals.getHead();
		while(current != null){
			if(current.getElement().getId() == id){
				return current.getElement();
			}
			current = current.getNext();
		}

		System.out.println("Não existe nenhum local com este id");
		return null;
	}

	/**
	 * Method that returns the index of a local by its id
	 * @param id
	 * @return the index of the local or -1 if the local doesn't exist
	*/
	public int findIndexById(long id){
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

	/**
	 * Method that returns an array with all the locals of type connector
	 * @return array with all the locals of type connector
	*/
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

	/**
	 * Method that returns an array with all the locals of type portal
	 * @return array with all the locals of type portal
	*/
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

	/**
	 * Method that returns an array with all the locals ordered by id
	 * @return array with all the locals ordered by id
	*/
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


	/**
	 * Method that returns the shortest path between two locals passing by another local
	 * @param local1
	 * @param local2
	 * @param local3
	 * @return the shortest path between two locals passing by another local
	*/
	public String shortestPathBetweenAnother(Local local1, Local local2, Local local3) {
		String ret = "<h1>";
		double path1 = shortestPathWeight(findIndexById(local1.getId()), findIndexById(local3.getId()));
		double path2 = shortestPathWeight(findIndexById(local3.getId()), findIndexById(local2.getId()));
		int[] path = returnShortestPath(findIndexById(local1.getId()), findIndexById(local3.getId()));
		for (int i = 0; i < path.length; i++) {
			ret += getAllLocals()[path[i]].getId() + " -> ";
		}
		path = returnShortestPath(findIndexById(local3.getId()), findIndexById(local2.getId()));
		for (int i = 0; i < path.length; i++) {
			ret += getAllLocals()[path[i]].getId() + " -> " ;
		}
		ret += "</h1>";
		ret += "<h2>Distância total: " + (path1 + path2) + "</h2>";
		return ret;
	}

	/**
	 * Method that returns the shortest path between two locals
	 * @param local1
	 * @param local2
	 * @return the shortest path between two locals
	*/
	public String shortestPath(Local local1, Local local2) {
		String ret = "<h1>";
		double path = shortestPathWeight(findIndexById(local1.getId()), findIndexById(local2.getId()));
		int[] path2 = returnShortestPath(findIndexById(local1.getId()), findIndexById(local2.getId()));
		for (int i = 0; i < path2.length; i++) {
			ret += getAllLocals()[path2[i]].getId() + " -> ";
		}
		ret += "</h1>";
		ret += "<h2>Distância total: " + path + "</h2>";
		return ret;
	}

	/**
	 * Getter for the count
	 * @return the count
	*/
	public int getCount() {
		return count;
	}

	/**
	 * Method that returns an array with all the locals
	 * @return array with all the locals
	*/
	public Local[] getAllLocals(){
		Local[] locals = new Local[count];
		LinearNode<Local> current = this.locals.getHead();
		int i = 0;
		while(current != null){
			locals[i] = current.getElement();
			i++;
			current = current.getNext();
		}
		return locals;
	}

	/**
	 * Method that creates a json with all the routes of the map
	 * @return json with all the routes of the map
	*/
	public JSONObject getRoutesJson(){
		JSONObject obj = new JSONObject();
		JSONArray array = new JSONArray();
		obj.put("Routes", array);

		LinearNode<Local> current = this.locals.getHead();
		while(current !=  null){
			LinearNode<LocalControl> current2 = current.getElement().getLocalControl().getHead();
			while(current2 != null){
				JSONObject newObj2 = new JSONObject();
				newObj2.put("from", current.getElement().getId());
				newObj2.put("to", current2.getElement().getLocal().getId());
				newObj2.put("weight", current2.getElement().getWeight());
				array.add(newObj2);
				current2 = current2.getNext();
			}
		}
		return obj;
	}

	public Local findLocalByLatELon(double lat, double lon){
		LinearNode<Local> current = this.locals.getHead();
		while(current != null){
			if(current.getElement().getLatitude() == lat && current.getElement().getLongitude() == lon){
				return current.getElement();
			}
			current = current.getNext();
		}
		return null;
	}

	public void findConnectedLocalsById(long id){
		LinearNode<Local> current = this.locals.getHead();
		while(current != null){
			if(current.getElement().getId() == id){
				LinearNode<LocalControl> current2 = current.getElement().getLocalControl().getHead();
				while(current2 != null){
					System.out.println(current2.getElement().getLocal().getId());
					current2 = current2.getNext();
				}
			}
			current = current.getNext();
		}
	}
}
