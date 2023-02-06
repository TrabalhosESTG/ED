package pt.estg.Grupo9ED;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import API.Connector;
import API.Map;
import API.Portal;

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
		Portal portal = new Portal(id,name, maxEnergy, energy, latitude, longitude);
		map.addPortal(portal);
		return portal.getEnergy(); //retornar id do portal
	}

	@PostMapping("/admin/connectors/create")
	public int createConnector(
		@RequestParam(value = "id", defaultValue = "1") int id,
		@RequestParam(value = "name", defaultValue = "Portal") String name,
		@RequestParam(value = "cooldown", defaultValue = "100") int cooldown,
		@RequestParam(value = "energy", defaultValue = "100") int energy,
		@RequestParam(value = "latitude", defaultValue = "0") double latitude,
		@RequestParam(value = "longitude", defaultValue = "0") double longitude) {
		Connector connector = new Connector(id,name, cooldown, energy, latitude, longitude);
		map.addConnector(connector);
		return connector.getEnergy(); //retornar id do portal
	}

	public String getConnectorsTable(){
		String str = "<table>";
		Connector[] conectores =  map.getAllConnector();
		for (Connector connector : conectores) {
			str += connector.getConnectorRow();
		}
		str += "</table>";
		return str;
	}
}
