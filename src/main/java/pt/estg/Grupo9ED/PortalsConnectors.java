package pt.estg.Grupo9ED;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import API.Local;
import API.Map;

@RestController
public class PortalsConnectors {
	Map map = new Map();

	@PostMapping("/admin/portals/create")
	public long createPortal(
		@RequestParam(value = "id", defaultValue = "1") int id,
		@RequestParam(value = "name", defaultValue = "Portal") String name,
		@RequestParam(value = "maxEnergy", defaultValue = "100") int maxEnergy,
		@RequestParam(value = "energy", defaultValue = "100") int energy,
		@RequestParam(value = "latitude", defaultValue = "0") double latitude,
		@RequestParam(value = "longitude", defaultValue = "0") double longitude) {
		Local local = new Local(id,"Portal", name,  latitude, longitude, energy, maxEnergy,null, null);
		map.addLocal(local);
		return local.getId(); //retornar id do portal
	}

	@PostMapping("/admin/connectors/create")
	public long createConnector(
		@RequestParam(value = "id", defaultValue = "1") int id,
		@RequestParam(value = "name", defaultValue = "Portal") String name,
		@RequestParam(value = "cooldown", defaultValue = "100") int cooldown,
		@RequestParam(value = "energy", defaultValue = "100") int energy,
		@RequestParam(value = "latitude", defaultValue = "0") double latitude,
		@RequestParam(value = "longitude", defaultValue = "0") double longitude) {
		Local local = new Local(id, "Connector", name, latitude, longitude, energy, cooldown);
		map.addLocal(local);
		return local.getId(); //retornar id do portal
	}

	@PostMapping("/admin/locals/jsonImport")
	public String importLocalsJson(
		@RequestParam(value = "json", defaultValue = "1") String jsonString){

		try {
			int i = 0;
			int j = 0;
			int k = 0;
			JSONParser parser = new JSONParser();
			JSONObject json = (JSONObject) parser.parse(jsonString);
			JSONArray locals = (JSONArray) json.get("locals");

			for (Object local : locals) {
				JSONObject localObject = (JSONObject) local;
				if(localObject.get("type").equals("Portal"))
				{
					long id = (long) localObject.get("id");
					String type = (String) localObject.get("type");
					String name = (String) localObject.get("name");
					JSONObject coordinates = (JSONObject) localObject.get("coordinates");
					double latitude = (double) coordinates.get("latitude");
					double longitude = (double) coordinates.get("longitude");
					JSONObject gameSettings = (JSONObject) localObject.get("gameSettings");
					long energy = (long) gameSettings.get("energy");
					long maxEnergy = (long) gameSettings.get("maxEnergy");

					Local newLocal = new Local(id, type, name, latitude, longitude, energy, maxEnergy, null, null);
					map.addLocal(newLocal);
					i++;
				}
				else if(localObject.get("type").equals("Connector"))
				{
					long id = (long) localObject.get("id");
					String type = (String) localObject.get("type");
					String name = (String) localObject.get("name");
					JSONObject coordinates = (JSONObject) localObject.get("coordinates");
					double latitude = (double) coordinates.get("latitude");
					double longitude = (double) coordinates.get("longitude");
					JSONObject gameSettings = (JSONObject) localObject.get("gameSettings");
					long energy = (long) gameSettings.get("energy");
					long cooldown = (long) gameSettings.get("cooldown");

					Local newLocal = new Local(id, type, name, latitude, longitude, energy, cooldown);
					map.addLocal(newLocal);
					j++;
				}else{
					k++;
				}
			}
			return "<h3>Portals: " + i + "</h3><h3>Connectors: " + j + "</h3><h3>Invalid: " + k + "</h3>";
		} catch (ParseException e) {
			return "<h3>Json Invalido</h3>";
		}
	}

	@PostMapping("/admin/routes/jsonImport")
	public String importRoutesJson(
		@RequestParam(value = "json", defaultValue = "1") String jsonString){

		JSONParser parser = new JSONParser();

		try {
			JSONObject jsonObject = (JSONObject) parser.parse(jsonString);
			JSONArray routesArray = (JSONArray) jsonObject.get("routes");
			int i = 0;
			for (Object obj : routesArray) {
				JSONObject route = (JSONObject) obj;
				long from = (long) route.get("from");
				long to = (long) route.get("to");
				long weight = (long) route.get("weight");
				i++;
				//Local de = map.findLocalById(from);
				//Local para = map.findLocalById(to);
				map.addEdge(map.findLocalById(from), map.findLocalById(to), weight);
			}
			return "<h3>Importadas " + i + " rotas</h3>";
		} catch (ParseException e) {
			return "<h3>Json Invalido</h3>";
		}
	}

	@PostMapping("/admin/map/getTable")
	public String mapGetTable(){
		String ret = "";
		ret += map.getAllConnectors()[0].getName();
		return ret;
	}

	@PostMapping("/listagem/connectorsOrderedID")
	public String connectorsOrderedID(){
		String ret = "<table><tr><th>ID</th><th>Nome</th><th>Latitude</th><th>Longitude</th><th>Energy</th></tr>";
		for (Local connector : map.orderLocalsById("Connector")) {
			if(connector != null)
				ret += "<tr><td>" + connector.getId() + "</td><td>" + connector.getName() + "</td><td>" + connector.getLatitude() + "</td><td>" + connector.getLongitude() + "</td><td>" + connector.getEnergy() + "</td></tr>";
		}
		ret += "</table>";
		return ret;
	}

	@PostMapping("/listagem/portaisOrderedID")
	public String portaisOrderedID(){
		String ret = "<table><tr><th>ID</th><th>Nome</th><th>Latitude</th><th>Longitude</th><th>Energy</th></tr>";
		for (Local portal : map.orderLocalsById("Portal")) {
			if(portal != null)
				ret += "<tr><td>" + portal.getId() + "</td><td>" + portal.getName() + "</td><td>" + portal.getLatitude() + "</td><td>" + portal.getLongitude() + "</td><td>" + portal.getEnergy() + "</td></tr>";
		}
		ret += "</table>";
		return ret;
	}

	@GetMapping("/JSON/locals")
	public String jsonLocals() {
		JSONObject ret = new JSONObject();
		JSONArray localsArray = new JSONArray();
		for (Local local : map.getAllLocals()) {
			if (local != null) {
				JSONObject localObject = new JSONObject();
				localObject.put("id", local.getId());
				localObject.put("type", local.getType());
				localObject.put("name", local.getName());
				JSONObject coordinates = new JSONObject();
				coordinates.put("latitude", local.getLatitude());
				coordinates.put("longitude", local.getLongitude());
				localObject.put("coordinates", coordinates);
				JSONObject gameSettings = new JSONObject();
				if (local.getType().equals("Portal")) {
					gameSettings.put("energy", local.getEnergy());
					gameSettings.put("maxEnergy", local.getMaxEnergy());
				} else if (local.getType().equals("Connector")) {
					gameSettings.put("energy", local.getEnergy());
					gameSettings.put("cooldown", 1);//local.getCooldown());
				}
				localObject.put("gameSettings", gameSettings);
				localsArray.add(localObject);
			}
		}
		ret.put("locals", localsArray);
		return ret.toJSONString();
	}

	@GetMapping("/JSON/routes")
	public String jsonRoutes() {
		return map.getRoutesJson().toJSONString();
	}
}
