package pt.estg.Grupo9ED;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import API.Local;
import API.Map;

@RestController
public class PortalsConnectors {
	Map map = new Map();

	@PostMapping("/admin/portals/create")
	public int createPortal(
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
	public int createConnector(
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
}
