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
import API.Player;
import API.PlayerLinkedList;

@RestController
public class Players {
	PlayerLinkedList playerList = new PlayerLinkedList();

	@PostMapping("/admin/players/create")
	public long createPlayer(@RequestParam(value = "name", defaultValue = "Player") String name, @RequestParam(value = "equipa", defaultValue = "Sparks") String equipa) {
		Player player = new Player(name, equipa, playerList.getPlayerCount());
		playerList.addPlayer(player);
		return player.getId();
	}

	@GetMapping("/admin/players/list")
	public String getPlayerList() {
		return playerList.criarJSON();
	}

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
}
