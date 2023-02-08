package pt.estg.Grupo9ED;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import API.Player;
public class PlayerTest {
    
    @Test
    void testGetName() {
        Player player = new Player("Player", 1);
        assertEquals("Player", player.getName());
    }

    @Test
    void testGetLevel() {
        Player player = new Player("Player", 1, "team", 99.1, 1, 1, 1, 1, 1,1);
        player.gainExp();
        assertEquals(2, player.getLevel());
    }

    @Test
    void testGetId() {
        Player player = new Player("Player", "team", 1);
        assertEquals(1, player.getId());
    }

    @Test
    void testGetTeam() {
        Player player = new Player("Player", "team", 1);
        player.setTeam("team1");
        assertEquals("team1", player.getTeam());
    }

    @Test
    void testGetExp() {
        Player player = new Player("Player", "team", 1);
        assertEquals(0, player.getExp());
    }

    @Test
    void testGetEnergy() {
        Player player = new Player("Player", 1, "team", 99.1, 1, 3, 1, 1, 1,1);
        player.loadEnergy(4);
        assertEquals(3, player.getEnergy());
    }

    @Test
    void testGetTotalEnergy() {
        Player player = new Player("Player", 1, "team", 99.1, 1, 3, 1, 1, 1,1);
        assertEquals(3, player.getTotalEnergy());
    }

    @Test
    void testGetLongitude() {
        Player player = new Player("Player", 1, "team", 99.1, 1, 3, 1, 1, 1,1);
        player.setLongitude(2);
        assertEquals(2, player.getLongitude());
    }

    @Test
    void testGetLatitude() {
        Player player = new Player("Player", 1, "team", 99.1, 1, 3, 1, 1, 1,1);
        player.setLatitude(2);
        assertEquals(2, player.getLatitude());
    }

    @Test
    void testGetConqueredPortal() {
        Player player = new Player("Player", 1, "team", 99.1, 1, 3, 1, 1, 1,1);
        player.setConqueredPortal();
        assertEquals(2, player.getConqueredPortal());
    }

    @Test
    void testRemoveEnergy() {
        Player player = new Player("Player", 1, "team", 99.1, -1, 3, 1, 1, 1,1);
        player.removeEnergy(1);
        assertEquals(0, player.getEnergy());
    }

}
