package pt.estg.Grupo9ED;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
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
			return "<h1>Portals: " + i + "</h1><h1>Connectors: " + j + "</h1><h1>Invalid: " + k + "</h1>";
		} catch (ParseException e) {
			return "<h1>Json Invalido</h1>";
		}
	}

}
