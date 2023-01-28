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

    private void levelUp() {
        this.level++;
        this.totalEnergy += 10;
        this.exp = 0;
    }

    private void gainExp() {
        this.exp += Math.pow((this.level/0.07), 2);
        if (this.exp >= 100) {
            levelUp();
        }
    }

    private String getName() {
        return this.name;
    }

    private int getLevel() {
        return this.level;
    }

    private String getTeam() {
        return this.team;
    }

    private double getExp() {
        return this.exp;
    }

    private double getEnergy() {
        return this.energy;
    }

    private int getTotalEnergy() {
        return this.totalEnergy;
    }

    private void setTeam(String team) {
        this.team = team;
    }

    private void setEnergy(double energy) {
        this.energy += energy;
        if (this.energy > this.totalEnergy) {
            this.energy = this.totalEnergy;
        }
    }

    private void removeEnergy(double energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }
}
