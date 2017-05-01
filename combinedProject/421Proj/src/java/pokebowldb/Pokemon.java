/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokebowldb;

import javax.xml.rpc.handler.GenericHandler;

/**
 *
 * @author Jard
 */
public class Pokemon {
    String name;
    String gender;
    String shiny;
    String nature;
    String ability;
    int hp;
    int attack;
    int defense;
    int spAttack;
    int spDefense;
    int speed;
    String move1;   
    String move2;
    String move3;
    String move4;

    public Pokemon(String name, String gender, String shiny, String nature, String ability, int hp, int attack, int defense, int spAttack, int spDefense, int speed, String move1, String move2, String move3, String move4) {
        this.name = name;
        this.gender = gender;
        this.shiny = shiny;
        this.nature = nature;
        this.ability = ability;
        this.hp = hp;
        this.attack = attack;
        this.defense = defense;
        this.spAttack = spAttack;
        this.spDefense = spDefense;
        this.speed = speed;
        this.move1 = move1;   
        this.move2 = move2;
        this.move3 = move3;
        this.move4 = move4;
    }

    public boolean hpEntered() {
        return hp == -1;
    }
    public boolean attackEntered() {
        return attack == -1;
    }
    public boolean defenseEntered() {
        return defense == -1;
    }
    public boolean spAttackEntered() {
        return spAttack == -1;
    }
    public boolean spDefenseEntered() {
        return spDefense == -1;
    }
    public boolean speedEntered() {
        return speed == -1;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getShiny() {
        return shiny;
    }

    public void setShiny(String shiny) {
        this.shiny = shiny;
    }

    public String getNature() {
        return nature;
    }

    public void setNature(String nature) {
        this.nature = nature;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpAttack() {
        return spAttack;
    }

    public void setSpAttack(int spAttack) {
        this.spAttack = spAttack;
    }

    public int getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(int spDefense) {
        this.spDefense = spDefense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getMove1() {
        return move1;
    }

    public void setMove1(String move1) {
        this.move1 = move1;
    }

    public String getMove2() {
        return move2;
    }

    public void setMove2(String move2) {
        this.move2 = move2;
    }

    public String getMove3() {
        return move3;
    }

    public void setMove3(String move3) {
        this.move3 = move3;
    }

    public String getMove4() {
        return move4;
    }

    public void setMove4(String move4) {
        this.move4 = move4;
    }
    
    
}
