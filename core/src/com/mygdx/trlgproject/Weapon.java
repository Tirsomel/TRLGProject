package com.mygdx.trlgproject;

public class Weapon {
    private int range; // Дальность оружия

    // Конструктор
    public Weapon(int range) {
        this.range = range;
    }

    // Геттер и сеттер для дальности оружия
    public int getRange() {
        return range;
    }

    public void setRange(int range) {
        this.range = range;
    }
}
