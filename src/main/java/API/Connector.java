package API;
import java.sql.Time;

import Lists.LinearNode;
import Lists.LinkedList;

public class Connector {
    protected Player player;
    protected String name;
    protected double energy;
    protected LinkedList<TimeControl> timeControl = new LinkedList<TimeControl>();
    


    public Connector(String name) {
        this.name = name;
        this.energy = 0;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    
    public void loadPlayerEnergy(){
        LinearNode<TimeControl> current = timeControl.getHead();
        while( current != null && !current.getElement().getPlayerName().equals(player.getName())){
            current = current.getNext();
        }
        if(current == null){
            timeControl.add(new TimeControl(player.getName(), (System.currentTimeMillis())));
            player.loadEnergy(energy);
        }else{
            TimeControl timeControl = current.getElement();
            Time time = new Time(System.currentTimeMillis());
            int diff = (int) ((time.getTime() - timeControl.getTime())/60000);
            if(diff >= 5){
                player.loadEnergy(energy);
                current.getElement().setTime(System.currentTimeMillis());
            }else{
                System.out.println("You can't load energy yet");
            }
        }

    }

}

