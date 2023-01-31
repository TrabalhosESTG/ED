package pt.estg.Grupo9ED;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import API.Portal;

@RestController
public class Portals {
	@PostMapping("/admin/portals/create")
	public int createPortal(
		@RequestParam(value = "id", defaultValue = "1") int id,
		@RequestParam(value = "name", defaultValue = "Portal") String name,
		@RequestParam(value = "maxEnergy", defaultValue = "100") int maxEnergy,
		@RequestParam(value = "energy", defaultValue = "100") int energy,
		@RequestParam(value = "latitude", defaultValue = "0") double latitude,
		@RequestParam(value = "longitude", defaultValue = "0") double longitude) {
		Portal portal = new Portal(id,name, maxEnergy, energy, latitude, longitude);
		//adicionar portal ao grafo de portais
		return portal.getEnergy(); //retornar id do portal
	}
}
