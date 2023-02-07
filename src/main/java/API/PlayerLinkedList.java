package API;

import Lists.LinearNode;
import Lists.LinkedList;
import org.json.simple.*;

public class PlayerLinkedList {
	protected LinkedList<Player> playerList = new LinkedList<Player>();
	protected int count = 0;

	public int getPlayerCount()
	{
		return count;
	}

	public void addPlayer(Player player) {
		playerList.add(player);
		count++;
	}

	public void removePlayer(Player player) {
		playerList.remove(player);
		count--;
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

	// ordenar os jogadores por nível
	public void sortPlayersByLevel() {
		LinearNode<Player> current = playerList.getHead();
		LinearNode<Player> next = current.getNext();
		while (current != null) {
			while (next != null) {
				if (current.getElement().getLevel() < next.getElement().getLevel()) {
					Player temp = current.getElement();
					current.setElement(next.getElement());
					next.setElement(temp);
				}
				next = next.getNext();
			}
			current = current.getNext();
			next = current.getNext();
		}
		printPlayers();
	}

	// ordenar os jogadores por portais conquistados
	public void sortPlayersByPortals() {
		LinearNode<Player> current = playerList.getHead();
		LinearNode<Player> next = current.getNext();
		while (current != null) {
			while (next != null) {
				if (current.getElement().conqueredPortals < next.getElement().getConqueredPortal()) {
					Player temp = current.getElement();
					current.setElement(next.getElement());
					next.setElement(temp);
				}
				next = next.getNext();
			}
			current = current.getNext();
			next = current.getNext();
		}
		printPlayers();
	}

	public void printPlayers() {
		if (playerList.isEmpty()) {
			System.out.println("There are no players");
			return;
		} else {
			LinearNode<Player> current = playerList.getHead();
			while (current != null) {
				System.out.println(current.getElement().getName());
				current = current.getNext();
			}
		}
	}

}
