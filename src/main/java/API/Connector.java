package API;
import java.sql.Time;


public class Connector {
    private Player player;
    private String name;
    private double energy;
    private Time time;


    public Connector(String name) {
        this.name = name;
        this.energy = 0;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }
}
