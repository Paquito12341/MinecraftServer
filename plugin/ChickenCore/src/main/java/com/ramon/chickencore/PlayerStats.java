package com.ramon.chickencore;

public class PlayerStats {

    private int level;
    private int xp;
    private int deaths;
    private int kills;
    private int blocksBroken;
    private int attributePoints;
    private int strength;
    private int defense;
    private int speed;
    private int vitality;





    public void addKill() {
        this.kills++;
    }

    public void addXp(int amount) {
        this.xp += amount;
    }

    public PlayerStats() {
        this.level = 1;
        this.xp = 0;
        this.deaths = 0;
        this.kills = 0;
        this.blocksBroken = 0;

        this.attributePoints = 0;
        this.strength = 0;
        this.defense = 0;
        this.speed = 0;
        this.vitality = 0;
    }
    //constructor


    //getters
    public int getLevel() {
        return level;
    }

    public int getXp() {
        return xp;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getKills() {
        return kills;
    }

    public int getBlocksBroken() {
        return blocksBroken;
    }

    public void addDeath() {
        this.deaths++;
    }

    public int getAttributePoints() {
        return attributePoints;
    }

    public int getStrength() {
        return strength;
    }

    public int getDefense() {
        return defense;
    }

    public int getSpeed() {
        return speed;
    }

    public int getVitality() {
        return vitality;
    }



}

