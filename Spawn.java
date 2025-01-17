import java.util.Random;

/**
 * The {@code Spawn} class represents a spawn enemy in the RPG game.
 * It contains properties such as the spawn's health, attack, physical defense, sorcery defense,
 * and incantation defense.
 */
public class Spawn {
    private int health;
    private int attack;
    private double pDef; // physical defense
    private double sDef; // sorcery defense
    private double iDef; // incantation defense

    /**
     * The {@code Spawn} constructor initializes the spawn with the provided physical defense, sorcery defense,
     * incantation defense, health, and attack.
     *
     * @param pDef The physical defense of the spawn.
     * @param sDef The sorcery defense of the spawn.
     * @param iDef The incantation defense of the spawn.
     * @param health The health of the spawn.
     * @param attack The attack power of the spawn.
     */
    public Spawn(double pDef, double sDef, double iDef, int health, int attack) {
        this.pDef = pDef;
        this.sDef = sDef;
        this.iDef = iDef;
        this.health = health;
        this.attack = attack;
    }

    @Override
    public String toString() {
        return String.format("Health: %d, Attack: %d, Physical Defense: %.2f, Sorcery Defense: %.2f, Incantation Defense: %.2f",
                health, attack, pDef, sDef, iDef);
    }

    public double getpDef() {
        return pDef;
    }

    public void setpDef(double pDef) {
        this.pDef = pDef;
    }

    public double getsDef() {
        return sDef;
    }

    public void setsDef(double sDef) {
        this.sDef = sDef;
    }

    public double getiDef() {
        return iDef;
    }

    public void setiDef(double iDef) {
        this.iDef = iDef;
    }

    public int getHealth() {
        return health;
    }

    // Updated to take a parameter
    public void setHealth(int health) {
        this.health = health;
    }

    public int getAttack() {
        return attack;
    }

    // Updated to take a parameter
    public void setAttack(int attack) {
        this.attack = attack;
    }


    public static int pickHealth1() {
        Random random = new Random();
        return random.nextInt(11) + 20;
    }

    public static int pickAttack1() {
        Random random = new Random();
        return random.nextInt(11) + 70;
    }

    // Corrected pickHealth2 method (example correction, assuming similar behavior to pickHealth1)
    public static int pickHealth2() {
        Random random = new Random();
        return random.nextInt(11) + 25;
    }

    public static int pickAttack2(){
        Random random = new Random();
        return random.nextInt(11)+110;
    }

    public static int pickHealth3(){
        Random random = new Random();
        return random.nextInt(11) + 70;
    }

    public static int pickAttack3(){
        Random random = new Random();
        return random.nextInt(11) + 120;
    }

    public static Spawn[] initializeSpawn() {
        Spawn[] battles = new Spawn[3];
        // Corrected array indices to start from 0
        battles[0] = new Spawn(0.20, 0.15, 0.10, pickHealth1(), pickAttack1());
        battles[1] = new Spawn(0.50, 0.15, 0.20, pickHealth2(), pickAttack2());
        battles[2] = new Spawn(0.25, 0.25, 0.20, pickHealth3(), pickAttack3());
        return battles;
    }


}
