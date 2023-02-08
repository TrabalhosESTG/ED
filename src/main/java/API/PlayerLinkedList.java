package API;

import Lists.LinearNode;
import Lists.LinkedList;
import org.json.simple.*;

/**
*Class that represents a list of players
*
*@author Guilherme Silva (8210190)
*@author David Francisco (8210088)
*/
public class PlayerLinkedList {
	protected LinkedList<Player> playerList = new LinkedList<Player>();
	protected int count = 0;

	/**
	* Obtains the number of players in the list
	*/
	public int getPlayerCount()
	{
		return count;
	}

	/**
	* Adds a player to the list
	*
	* @param player Player to be added
	*/
	public void addPlayer(Player player) {
		playerList.add(player);
		count++;
	}

	/**
	* Removes a player from the list
	*
	* @param player Player to be removed
	*/
	public void removePlayer(Player player) {
		playerList.remove(player);
		count--;
	}

	/**
	* Obtains a player from the list searching by ID
	*
	* @param id ID of the player to be obtained
	* @return Player with the given ID
	*/
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

	/**
	* Prints the name of all the players in a given team
	*
	* @param teamName Name of the team
	*/
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

	/**
	* Method that creates a JSON with all the players in the list
	*
	* @return JSON with all the players in the list
	*/
	public String criarJSON(){
		JSONObject obj = new JSONObject();
		LinearNode<Player> current = playerList.getHead();
		while (current != null) {
			obj.put(current.getElement().getId(), current.getElement().criarPlayerJSON());
			current = current.getNext();
		}
		return obj.toJSONString();
	}

	/**
	* Method that sorts the players by level and prints them
	*/
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

	/**
	* Method that sorts the players by portals conquered and prints them
	*/
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

	/**
	* Method that prints the name of all the players in the list
	*/
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
