package pt.estg.Grupo9ED;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import API.Player;
import API.PlayerLinkedList;

@RestController
public class Players {
	PlayerLinkedList playerList = new PlayerLinkedList();

	@PostMapping("/admin/players/create")
	public int createPlayer(@RequestParam(value = "name", defaultValue = "Player") String name, @RequestParam(value = "equipa", defaultValue = "Sparks") String equipa) {
		Player player = new Player(name, equipa, playerList.getPlayerCount());
		playerList.addPlayer(player);
		return player.getId();
	}

	@GetMapping("/admin/players/list")
	public String getPlayerList() {
		return playerList.criarJSON();
	}
}
