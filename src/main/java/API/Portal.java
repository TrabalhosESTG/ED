package API;
public class Portal {
    private Player player;
    private String name;
    private String conquererPlayer;
    private String conquererTeam;
    private double energy;
    private int maxEnergy;

    public Portal(String name, int maxEnergy) {
        this.name = name;
        this.conquererPlayer = "None";
        this.conquererTeam = "None";
        this.energy = 0;
        this.maxEnergy = maxEnergy;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void loadEnergy(double energy) {
        this.energy += energy;
        if (this.energy > this.maxEnergy) {
            this.energy = this.maxEnergy;
        }
    }

    public String getName() {
        return this.name;
    }

    public String getConquererPlayer() {
        return this.conquererPlayer;
    }

    public String getConquererTeam() {
        return this.conquererTeam;
    }

    public double getEnergy() {
        return this.energy;
    }

    public int getMaxEnergy() {
        return this.maxEnergy;
    }

    public void deloadEnergy(double energy) {
        this.energy -= energy;
        if (this.energy < 0) {
            this.energy = 0;
        }
    }

    public void conquer() {
        this.conquererPlayer = player.getName();
        this.conquererTeam = player.getTeam();
    }

    public void reset() {
        this.conquererPlayer = "None";
        this.conquererTeam = "None";
    }

    public void askToLoadEnergy() {
        if(this.energy == this.maxEnergy) {
            System.out.println("This portal is already full of energy.");
            return;
        }
        System.out.println("Do you want to load energy? (Y/N)");
        String answer = System.console().readLine();
        if (answer.equals("Y")) {
            System.out.println("You have " + this.energy + " energy.");
            System.out.println("How much energy do you want to load?");
            double energy = Double.parseDouble(System.console().readLine());
            loadEnergy(energy);
        }
    }

    public void askToDeloadEnergy() {
        if(this.energy == 0) {
            System.out.println("This portal is already empty of energy.");
            return;
        }
        String answer;
        do{
        System.out.println("Do you want to deload energy? (Y/N)");
        answer = System.console().readLine();
        if (answer.equals("Y")) {
            System.out.println("You have " + this.energy + " energy.");
            System.out.println("How much energy do you want to deload?");
            double energy = Double.parseDouble(System.console().readLine());
            deloadEnergy(energy);
        }
    }while(answer.equals("Y"));
    }

    public void askToConquer() {
        if (player.getEnergy() < (player.getTotalEnergy() * 0.25)) {
            System.out.println("You don't have enough energy to conquer this portal.");
            return;
        }
        System.out.println("Do you want to conquer this portal? (Y/N)");
        String answer = System.console().readLine();
        if (answer.equals("Y")) {
            if (player.getEnergy() < (player.getTotalEnergy() * 0.25)) {
                System.out.println("You don't have enough energy to conquer this portal.");
                return;
            }
            System.out.println("The minimum energy to conquer this portal is" + (player.getTotalEnergy() * 0.25) + "energy. You have " + player.getEnergy() + "energy.");
            System.out.println("How much energy do you want to use to conquer this portal?");
            double energy = Double.parseDouble(System.console().readLine());
            player.removeEnergy(energy);
            conquer();
            System.out.println("You have conquered" + this.name + "!");
        }
    }
}
