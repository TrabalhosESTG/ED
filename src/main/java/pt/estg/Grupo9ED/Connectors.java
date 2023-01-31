package pt.estg.Grupo9ED;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import API.Connector;
import API.Map;

@RestController
public class Connectors {
	@PostMapping("/admin/connectors/create")
	public int createConnector(
		@RequestParam(value = "id", defaultValue = "1") int id,
		@RequestParam(value = "name", defaultValue = "Portal") String name,
		@RequestParam(value = "cooldown", defaultValue = "100") int cooldown,
		@RequestParam(value = "energy", defaultValue = "100") int energy,
		@RequestParam(value = "latitude", defaultValue = "0") double latitude,
		@RequestParam(value = "longitude", defaultValue = "0") double longitude) {
		Connector connector = new Connector(id,name, cooldown, energy, latitude, longitude);
		Map map = new Map();
		map.addConnector(connector);
		return connector.getEnergy(); //retornar id do portal
	}
}
