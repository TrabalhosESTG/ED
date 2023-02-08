package pt.estg.Grupo9ED;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import API.TimeControl;
public class TimeControlTest {

	@Test
	void testGetTime() {
		TimeControl time = new TimeControl("player", 11);
		time.setTime(12);
		assertEquals(12, time.getTime());
	}

	@Test
	void testGetPlayer() {
		TimeControl time = new TimeControl("player", 11);
		assertEquals("player", time.getPlayerName());
	}
}
