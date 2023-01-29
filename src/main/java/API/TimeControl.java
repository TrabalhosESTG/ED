package API;

import java.sql.Time;

public class TimeControl {
    protected String playerName;
    protected long time;

    public TimeControl(String playerName, long time) {
        this.playerName = playerName;
        this.time = time;
    }

    public String getPlayerName() {
        return playerName;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }
    
}
