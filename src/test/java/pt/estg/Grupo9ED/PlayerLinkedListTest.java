package pt.estg.Grupo9ED;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import API.*;

public class PlayerLinkedListTest {

    @Test
    void testGetPlayer() {
        PlayerLinkedList playerLinkedList = new PlayerLinkedList();
        Player player = new Player("Player", 1, "team", 99.1, 1, 1, 1, 1, 1,1);
        playerLinkedList.addPlayer(player);
        playerLinkedList.addPlayer(new Player("Player", 2, "team", 99.1, 1, 1, 2, 1, 1,1));
        playerLinkedList.addPlayer(new Player("Player", 3, "team", 99.1, 1, 1, 3, 1, 1,1));
        assertEquals(player, playerLinkedList.getPlayer(1));
    }

    @Test
    void testAddPlayer() {
        PlayerLinkedList playerLinkedList = new PlayerLinkedList();
        playerLinkedList.addPlayer(new Player("Player", 1, "team", 99.1, 1, 1, 1, 1, 1,1));
        playerLinkedList.addPlayer(new Player("Player", 2, "team", 99.1, 1, 1, 1, 1, 1,1));
        playerLinkedList.addPlayer(new Player("Player", 3, "team", 99.1, 1, 1, 1, 1, 1,1));
        assertEquals(3, playerLinkedList.getPlayerCount());
    }

    @Test
    void testRemovePlayer() {
        PlayerLinkedList playerLinkedList = new PlayerLinkedList();
        Player player = new Player("Player", 1, "team", 99.1, 1, 1, 1, 1, 1,1);
        playerLinkedList.addPlayer(player);
        playerLinkedList.addPlayer(new Player("Player", 2, "team", 99.1, 1, 1, 1, 1, 1,1));
        playerLinkedList.addPlayer(new Player("Player", 3, "team", 99.1, 1, 1, 1, 1, 1,1));
        playerLinkedList.removePlayer(player);
        assertEquals(2, playerLinkedList.getPlayerCount());
    }

    @Test
    void testCriarLista() {
        PlayerLinkedList playerLinkedList = new PlayerLinkedList();
        playerLinkedList.addPlayer(new Player("Player", 1, "team", 99.1, 1, 1, 1, 1, 1,1));
        String ret = ""+"<option value=\""+ 1 +"\">"+ "Player" +"</option>";
        assertEquals(ret, playerLinkedList.criarLista());
    }
}
