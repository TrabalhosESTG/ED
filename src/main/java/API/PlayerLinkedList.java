package API;

import Lists.LinearNode;
import Lists.LinkedList;
import org.json.simple.*;

public class PlayerLinkedList {
	LinkedList<Player> playerList = new LinkedList<Player>();

	public void addPlayer(Player player) {
		playerList.add(player);
	}
	
	public void removePlayer(Player player) {
		playerList.remove(player);
	}

	public Player getPlayer(int id) {
		if (playerList.isEmpty()) {
			return null;
		} else {
			LinearNode<Player> current = playerList.getHead();
			while (current != null && !(current.getElement().getId() == id)) {
				current = current.getNext();
			}
			if (current != null) {
				return current.getElement();
			} else {
				return null;
			}
		}
	}

	public void getTeamPlayer(String teamName) {
		if (playerList.isEmpty()) {
			System.out.println("There are no players");
			return;
		} else {
			LinearNode<Player> current = playerList.getHead();
			while (current != null) {
				if (current.getElement().getTeam().equals(teamName)) {
					System.out.println(current.getElement().getName());
				}
				current = current.getNext();
			}
		}
	}

	
	public String criarJSON(){
		JSONObject obj = new JSONObject();
		LinearNode<Player> current = playerList.getHead();
		while (current != null) {
			obj.put(current.getElement().getId(), current.getElement().criarPlayerJSON());
			current = current.getNext();
		}
		return obj.toJSONString();
	}
}
