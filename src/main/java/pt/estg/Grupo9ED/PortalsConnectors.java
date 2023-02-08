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
import API.Player;
import API.PlayerLinkedList;

/**
 * PortalsConnectors is a class that handles the creation and manipulation of Portals and Connectors.
 * It uses a Map object to store the Portals and Connectors and offers endpoints for adding, removing, and importing data.
 *
 * @author Guilherme Silva (8210190)
 * @author David Francisco (8210088)
 */
@RestController
public class PortalsConnectors {
	PlayerLinkedList playerList = new PlayerLinkedList();
	Map map = new Map();

	/**
	 * Endpoint for creating a Portal.
	 *
	 * @param id the identifier of the Portal
	 * @param name the name of the Portal
	 * @param maxEnergy the maximum energy of the Portal
	 * @param energy the current energy of the Portal
	 * @param latitude the latitude of the Portal's location
	 * @param longitude the longitude of the Portal's location
	 *
	 * @return the identifier of the created Portal
	 */
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

		/**
	 * Endpoint for creating a Connector.
	 *
	 * @param id the identifier of the Connector
	 * @param name the name of the Connector
	 * @param cooldown the cooldown time of the Connector
	 * @param energy the energy of the Connector
	 * @param latitude the latitude of the Connector's location
	 * @param longitude the longitude of the Connector's location
	 *
	 * @return the identifier of the created Connector
	 */
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

	@PostMapping("/admin/addRota")
	public String addRota(
		@RequestParam(value = "id_de", defaultValue = "-1") long id_de,
		@RequestParam(value = "id_para", defaultValue = "-1") long id_para,
		@RequestParam(value = "peso", defaultValue = "1") long weight) {
			if(map.findLocalById(id_de) == null || map.findLocalById(id_para) == null)
				return "<h1> Rota não adicionada </h1>";
			map.addLocalConnection(map.findLocalById(id_de), map.findLocalById(id_para), weight);
			return "<h1> Rota adicionada com sucesso </h1>";
		}

		@PostMapping("/admin/removeRota")
	public String removeRota(
		@RequestParam(value = "id_de", defaultValue = "-1") long id_de,
		@RequestParam(value = "id_para", defaultValue = "-1") long id_para){
			if(map.findLocalById(id_de) == null || map.findLocalById(id_para) == null)
				return "<h1> Rota não encontrada </h1>";
			map.removeLocalConnection(map.findLocalById(id_de), map.findLocalById(id_para));
			return "<h1> Rota adicionada com sucesso </h1>";
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
				map.addLocalConnection(map.findLocalById(from), map.findLocalById(to), weight);
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
					gameSettings.put("cooldown", local.getCooldown());
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

	@PostMapping("/admin/local/remove")
	public String removeLocal(
		@RequestParam(value = "id", defaultValue = "1") int id) {
		map.removeLocal(map.findLocalById(id));
		return "<h1>Local removido com sucesso</h1>";
	}

	@PostMapping("/rotas/maisCurta")
	public String RotaMaisCurta(
		@RequestParam(value = "id_inicio", defaultValue = "1") int id_inicio,
		@RequestParam(value = "id_fim", defaultValue = "1") int id_fim) {

		if(id_inicio == id_fim)
			return "<h1>IDs iguais</h1>";

		if(map.findLocalById(id_inicio) == null || map.findLocalById(id_fim) == null)
			return "<h1>IDs invalidos</h1>";

		return "<h1>" + map.shortestPath(map.findLocalById(id_inicio), map.findLocalById(id_fim)) +"</h1>";
	}

	@PostMapping("/rotas/maisCurtaIntermedio")
	public String RotaMaisCurtaIntermedio(
		@RequestParam(value = "id_inicio", defaultValue = "1") int id_inicio,
		@RequestParam(value = "id_intermedio", defaultValue = "1") int id_intermedio,
		@RequestParam(value = "id_fim", defaultValue = "1") int id_fim) {

		if(id_inicio == id_intermedio || id_intermedio == id_fim || id_inicio == id_fim)
			return "<h1>IDs iguais</h1>";

		if(map.findLocalById(id_inicio) == null || map.findLocalById(id_intermedio) == null || map.findLocalById(id_fim) == null)
			return "<h1>IDs invalidos</h1>";

		return "<h1>" + map.shortestPathBetweenAnother(map.findLocalById(id_inicio), map.findLocalById(id_fim), map.findLocalById(id_intermedio)) +"</h1>";
	}

	@PostMapping("/rotas/aPartitDe")
	public String getLocaisConnected(
		@RequestParam(value = "id", defaultValue = "-1") long id
	)
	{
		return map.findConnectedLocalsById(id);
	}

	@PostMapping("/locals/findByID")
	public String LocalsFindByID(
		@RequestParam(value = "id", defaultValue = "-1") long id
	)
	{
		Local local = map.findLocalById(id);
		return "{\"id\": "+ local.getId() + ", \"name\": \"" + local.getName() + "\"}";
	}

	@PostMapping("/locals/FindByCoordinates")
	public String LocalsFindByCoordinates(
		@RequestParam(value = "latitude", defaultValue = "-1") double latitude,
		@RequestParam(value = "longitude", defaultValue = "-1") double longitude
	)
	{
		Local local = map.findLocalByLatELon(latitude, longitude);
		if(local.getType().equals("Portal")){
			return "{\"id\": "+ local.getId() + ", \"name\": \"" + local.getName() + "\", \"energy\": " + local.getEnergy() + ", \"team\": " + local.getConquererTeam() + "}";
		}
		return "{\"id\": "+ local.getId() + ", \"name\": \"" + local.getName() + "\"}";
	}

	/**
	 * Creates a new player with the given name and team and adds the player to the player list.
	 *
	 * @param name the name of the player
	 * @param equipa the team of the player
	 * @return the ID of the newly created player
	 */
	@PostMapping("/admin/players/create")
	public long createPlayer(@RequestParam(value = "name", defaultValue = "Player") String name, @RequestParam(value = "equipa", defaultValue = "Sparks") String equipa) {
		Player player = new Player(name, equipa, playerList.getPlayerCount());
		playerList.addPlayer(player);
		return player.getId();
	}

	@PostMapping("/players/getPlayer/")
	public String getPlayer(@RequestParam(value = "id", defaultValue = "1") long id) {
		Player player = playerList.getPlayer(id);
		if (player != null) {
			return player.criarPlayerJSON().toJSONString();
		} else {
			return "Player not found";
		}
	}

	/**
	 * Returns the player list as a JSON string.
	 *
	 * @return the player list as a JSON string
	 */
	@GetMapping("/JSON/Players")
	public String getPlayerJson() {
		return playerList.criarJSON();
	}

	/**
	 * Returns the player list in human-readable format.
	 *
	 * @return the player list in human-readable format
	 */
	@GetMapping("players/getList")
	public String getPlayerList() {
		return playerList.criarLista();
	}

	/**
	 * Imports player data from a JSON string and adds the players to the player list.
	 *
	 * @param jsonString the JSON string containing the player data
	 * @return the number of players imported
	 */
	@PostMapping("/admin/players/jsonImport")
	public String importPlayersJson(
		@RequestParam(value = "json", defaultValue = "1") String jsonString){
			try {
				int j = 0;
				JSONParser parser = new JSONParser();
				JSONObject json = (JSONObject) parser.parse(jsonString);
				JSONArray players = (JSONArray) json.get("players");

				for (Object player : players) {
					JSONObject playerJSON = (JSONObject) player;
					Long id = (Long) playerJSON.get("id");
					String name = (String) playerJSON.get("name");
					String team = (String) playerJSON.get("team");
					Long level = (Long) playerJSON.get("level");
					Long experiencePoints = (Long) playerJSON.get("experiencePoints");
					Long currentEnergy = (Long) playerJSON.get("currentEnergy");
					Long totalEnergy = (Long) playerJSON.get("totalEnergy");
					Long conqueredPortals = (Long) playerJSON.get("conqueredPortals");
					JSONObject coordinates = (JSONObject) playerJSON.get("coordinates");
					Double latitude = (Double) coordinates.get("latitude");
					Double longitude = (Double) coordinates.get("longitude");

					Player newPlayer = new Player(name, level, team, experiencePoints, currentEnergy, totalEnergy, id, latitude, longitude, conqueredPortals) ;
					playerList.addPlayer(newPlayer);
					j++;
				}
				return "<h3>Players importados: " + j + "</h3>";
			} catch (ParseException e) {
				return "<h3>Json Invalido</h3>";
			} catch(Exception e)
			{
				return "<h3>Erro de servidor</h3>";
			}
	}

	@PostMapping("/admin/portals/attack")
    public long attackPortal(
        @RequestParam(value = "id_Jogador", defaultValue = "1") int id_Player,
        @RequestParam(value = "id_Portal", defaultValue = "Portal") int id_Portal) {
        map.deloadEnergy(map.findLocalById(id_Portal), playerList.getPlayer((long) id_Player));;
        return map.findLocalById(id_Portal).getEnergy();
    }

	@PostMapping("/admin/portals/conquer")
    public String conquerPortal(
        @RequestParam(value = "id_Jogador", defaultValue = "1") int id_Player,
        @RequestParam(value = "id_Portal", defaultValue = "Portal") int id_Portal) {
        map.ConquerPortal(map.findLocalById(id_Portal), playerList.getPlayer((long) id_Player));;
        return map.findLocalById(id_Portal).getConquererPlayer();
    }

	@PostMapping("/admin/player/move")
    public String movePlayer(
        @RequestParam(value = "id_Jogador", defaultValue = "1") int id_Player,
        @RequestParam(value = "id_Local", defaultValue = "Portal") int id_Local) {
        map.movePlayer(map.findLocalById(id_Local), playerList.getPlayer((long) id_Player));;
        return "longitude: " + playerList.getPlayer((long) id_Player).getLongitude() + " latitude: " + playerList.getPlayer((long) id_Player).getLatitude();
    }

	@PostMapping("/admin/player/loadEnergy")
    public Double loadPlayerEnergy(
        @RequestParam(value = "id_Jogador", defaultValue = "1") int id_Player,
        @RequestParam(value = "id_Local", defaultValue = "Portal") int id_Local) {
        map.loadPlayerEnergy(map.findLocalById(id_Local), playerList.getPlayer((long) id_Player));;
        return playerList.getPlayer((long) id_Player).getEnergy();
    }
}
