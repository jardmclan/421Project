/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokebowldb;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jard
 */
@ManagedBean(name = "Teams")
@SessionScoped
public class Teams implements Serializable {
    String name;
    char gender;
    char shiny;
    String nature;
    String ability;
    String hp;
    String attack;
    String defense;
    String spAttack;
    String spDefense;
    String speed;
    String move1;   
    String move2;
    String move3;
    String move4;
    PreparedStatement ps;
    ResultSet result;
    List<Pokemon> toAdd;

    public List<Pokemon> getToAdd() {
        return toAdd;
    }

    public void addPokemon() {
        if(toAdd == null) {
            toAdd = new ArrayList<Pokemon>();
        }
        if(toAdd.size() < 6) {
            int hpInt = -1;
            int attackInt = -1;
            int defenseInt = -1;
            int spAttackInt = -1;
            int spDefenseInt = -1;
            int speedInt = -1; 
            if(isInt(hp)) {
                hpInt = Integer.parseInt(hp);
            }
            if(isInt(attack)) {
                attackInt = Integer.parseInt(attack);
            }
            if(isInt(defense)) {
                defenseInt = Integer.parseInt(defense);
            }
            if(isInt(spAttack)) {
                spAttackInt = Integer.parseInt(spAttack);
            }
            if(isInt(spDefense)) {
                spDefenseInt = Integer.parseInt(spDefense);
            }
            if(isInt(speed)) {
                speedInt = Integer.parseInt(speed);
            }
            toAdd.add(new Pokemon(name, Character.toString(gender), Character.toString(shiny), nature, ability, hpInt, attackInt, defenseInt, spAttackInt, spDefenseInt, speedInt, move1, move2, move3, move4));
        }
    }
    
    public void addTeam() {
        
    }
    
    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
    
    public void clearPokemon() {
        toAdd = new ArrayList<Pokemon>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public char getShiny() {
        return shiny;
    }

    public void setShiny(char shiny) {
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

    public String getHp() {
        return hp;
    }

    public void setHp(String hp) {
        this.hp = hp;
    }

    public String getAttack() {
        return attack;
    }

    public void setAttack(String attack) {
        this.attack = attack;
    }

    public String getDefense() {
        return defense;
    }

    public void setDefense(String defense) {
        this.defense = defense;
    }

    public String getSpAttack() {
        return spAttack;
    }

    public void setSpAttack(String spAttack) {
        this.spAttack = spAttack;
    }

    public String getSpDefense() {
        return spDefense;
    }

    public void setSpDefense(String spDefense) {
        this.spDefense = spDefense;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
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
    
    public Teams() {
        toAdd = new ArrayList<Pokemon>();
    }
}
