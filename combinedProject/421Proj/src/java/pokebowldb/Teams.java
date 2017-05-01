/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokebowldb;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
    
    public void addTeam() throws SQLException {
        if(CreateConnection.con == null) {
            CreateConnection.getConnection();
        }
        int teamId = (int)(Math.random() * Integer.MAX_VALUE);
        String update3 = "insert into \"Team\" values(?, 386680301688)";
        ps = CreateConnection.con.prepareStatement(update3);
        ps.setInt(1, teamId);
        ps.executeUpdate();
        for(Pokemon p : toAdd) {
            String update1 = "insert into \"Pokemon\" values(?, ?, ?, ?, ?, ?, ?, 386680301688, ?, ?,?,?,?,?,?,?,?,?,?)";
            String update2 = "insert into \"Contains\" values(?, ?, null)";
            
            
            ps = CreateConnection.con.prepareStatement(update1);
            int rand = (int)(Math.random() * Integer.MAX_VALUE);
            ps.setInt(1, rand);

            ps.setString(2, p.name);

            ps.setString(3, "0");

            if(p.gender.equals("M") || p.gender.equals("F")) {
                ps.setString(4, p.gender);
            }
            else {
                ps.setString(4, null);
            }
            if(p.shiny.equals("y") || p.shiny.equals("n")) {
                ps.setString(5, p.shiny);
            }
            else {
                ps.setString(5, "n");
            }


            ps.setString(6, p.nature);
            ps.setString(7, p.ability);


            ps.setInt(8, 0);

            if(p.hp != -1) {
                ps.setInt(9, p.hp);
            }
            else {
                ps.setString(9, null);
            }
            if(p.attack != -1) {
                ps.setInt(10, p.attack);
            }
            else {
                ps.setString(10, null);
            }
            if(p.defense != -1) {
                ps.setInt(11, p.defense);
            }
            else {
                ps.setString(11, null);
            }
            if(p.spAttack != -1) {
                ps.setInt(12, p.spAttack);
            }
            else {
                ps.setString(12, null);
            }
            if(p.spDefense != -1) {
                ps.setInt(13, p.spDefense);
            }
            else {
                ps.setString(13, null);
            }
            if(p.speed != -1) {
                ps.setInt(14, p.speed);
            }
            else {
                ps.setString(14, null);
            }


            if(p.move1.equals("")) {
                ps.setString(15, null);
            }
            else {
                ps.setString(15, p.move1);
            }
            if(p.move2.equals("")) {
                ps.setString(16, null);
            }
            else {
                ps.setString(16, p.move2);
            }
            if(p.move3.equals("")) {
                ps.setString(17, null);
            }
            else {
            ps.setString(17, p.move3);
            }
            if(p.move4.equals("")) {
                ps.setString(18, null);
            }
            else {
            ps.setString(18, p.move4);
            }

            ps.executeUpdate();
            
            
            
            
            ps = CreateConnection.con.prepareStatement(update2);
            ps.setInt(1, teamId);
            ps.setLong(2, rand);
            ps.executeUpdate();
        }
        
        toAdd = new ArrayList<Pokemon>();
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
