public class Player {
    private String name;
    private int level;
    private String team;
    private double exp;
    private double energy;
    private int totalEnergy;

    public Player(String name) {
        this.name = name;
        this.level = 1;
        this.team = "None";
        this.exp = 0;
        this.energy = 100;
        this.totalEnergy = 100;
    }

    public Player(String name, String team) {
        this.name = name;
        this.level = 1;
        this.team = team;
        this.exp = 0;
        this.energy = 100;
        this.totalEnergy = 100;
    }

    public void levelUp() {
        this.level++;
        this.totalEnergy += 10;
        this.exp = 0;
    }

    public void gainExp() {
        this.exp += Math.pow((this.level/0.07), 2);
        if (this.exp >= 100) {
            levelUp();
        }
    }

    public String getName() {
        return this.name;
    }

    public int getLevel() {
        return this.level;
    }

    public String getTeam() {
        return this.team;
    }

    public double getExp() {
        return this.exp;
    }

    public double getEnergy() {
        return this.energy;
    }

    public int getTotalEnergy() {
        return this.totalEnergy;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void loadEnergy(double energy) {
        this.energy += energy;
        if (this.energy > this.totalEnergy) {
            this.energy = this.totalEnergy;
        }
    }

    public void removeEnergy(double energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }
}
