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
@ManagedBean(name = "Players")
@SessionScoped
public class Players implements Serializable {
    String discordName;
    long friendCode;
    String ign;
    String recordType;
    PreparedStatement ps;
    ResultSet result;
    List<Player> playerDetails;
    List<BreedingProject> projects;
    List<Team> teams;

    public boolean isGeneral() {
        return recordType != null && recordType.equals("general");
    }
    
    public boolean isEvents() {
        return recordType != null && recordType.equals("events");
    }
    
    public boolean isTeams() {
        return recordType != null && recordType.equals("teams");
    }
    public boolean isBreeding() {
        return recordType != null && recordType.equals("breeding");
    }
    
    public List<Player> getPlayerDetails() {
        return playerDetails;
    }
    
    public List<BreedingProject> getProjects() {
        return projects;
    }
    
    public List<Team> getTeams() {
        return teams;
    }
    
    public void getRecords() throws SQLException {
        if(recordType != null) {
            if(CreateConnection.con == null) {
                CreateConnection.getConnection();
            }
            boolean first = true;
            int discordIndex = -1;
            int friendIndex = -1;
            int ignIndex = -1;
            int currentIndex = 1;
            if(isGeneral()) {

                //String query = "select \"Pokemon\".*, \"Discord_Name\", \"Friend_Code\" from \"Pokemon\", \"Player\" ";
                String query = "select \"IGN\", \"Version\", \"Battle_Points\", \"Discord_Name\", \"Friend_Code\" from \"Player\" join \"Game_Played\" on \"Friend_Code\"=\"Player\"";


                if(!discordName.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Discord_Name\")=?";
                    discordIndex = currentIndex;
                    currentIndex++;
                }
                //ok to use 0, friend code can never be 0 (if it's valid at least)
                if(friendCode != 0) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "\"Friend_Code\"=?";
                    friendIndex = currentIndex;
                    currentIndex++;
                }
                if(!ign.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"IGN\")=?";
                    ignIndex = currentIndex;
                    currentIndex++;
                }

                ps = CreateConnection.con.prepareStatement(query);
                
                
                //apparently preparedstatement inputs can only be replaced by one thing rather than a string, so have to do all this after
                if(discordIndex != -1) {
                    ps.setString(discordIndex, discordName.toUpperCase());
                }
                if(friendIndex != -1) {
                    ps.setLong(friendIndex, friendCode);
                }
                if(ignIndex != -1) {
                    ps.setString(ignIndex, ign.toUpperCase());
                }



                result = ps.executeQuery();

                playerDetails = new ArrayList<Player>();
                while(result.next()){
                    Player r = new Player();

                    r.setDiscordName(result.getString("discord_name"));
                    r.setFriendCode(result.getLong("friend_code"));
                    r.setIgn(result.getString("ign"));
                    r.setBp(result.getInt("battle_points"));
                    r.setVersion(result.getString("version"));

                    //store all data Stringo a List
                    playerDetails.add(r);
                }
            }
            else if(isTeams()) {
                //String query = "select \"Pokemon\".*, \"Discord_Name\", \"Friend_Code\" from \"Pokemon\", \"Player\" ";
                String query = "select * from (select \"Player\".*, \"IGN\" from \"Player\" join \"Game_Played\" on \"Player\" = \"Friend_Code\"";


                if(!discordName.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Discord_Name\")=?";
                    discordIndex = currentIndex;
                    currentIndex++;
                }
                //ok to use 0, friend code can never be 0 (if it's valid at least)
                if(friendCode != 0) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "\"Friend_Code\"=?";
                    friendIndex = currentIndex;
                    currentIndex++;
                }
                if(!ign.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"IGN\")=?";
                    ignIndex = currentIndex;
                    currentIndex++;
                }

                query += ") A join \"Team\" on \"Player\" = \"Friend_Code\" join \"Contains\" on \"TeamID\" = \"Team\" join \"Pokemon\" on \"Pokemon\" = \"Pokemon_ID\" order by \"TeamID\"";
                
                ps = CreateConnection.con.prepareStatement(query);
                //apparently preparedstatement inputs can only be replaced by one thing rather than a string, so have to do all this after
                if(discordIndex != -1) {
                    ps.setString(discordIndex, discordName.toUpperCase());
                }
                if(friendIndex != -1) {
                    ps.setLong(friendIndex, friendCode);
                }
                if(ignIndex != -1) {
                    ps.setString(ignIndex, ign.toUpperCase());
                }



                result = ps.executeQuery();

                teams = new ArrayList<Team>();
                while(result.next()){
                    Team bp = new Team();

                    bp.setName(result.getString("name"));
                    bp.setGender(result.getString("gender"));
                    bp.setShiny(result.getString("shiny"));
                    bp.setNature(result.getString("nature"));
                    bp.setAbility(result.getString("ability"));
                    bp.setHp(result.getInt("hp"));
                    bp.setAttack(result.getInt("attack"));
                    bp.setDefense(result.getInt("defense"));
                    bp.setSpAttack(result.getInt("special_attack"));
                    bp.setSpDefense(result.getInt("special_defense"));
                    bp.setSpeed(result.getInt("speed"));
                    bp.setMove1(result.getString("move1"));
                    bp.setMove2(result.getString("move2"));
                    bp.setMove3(result.getString("move3"));
                    bp.setMove4(result.getString("move4"));
                    bp.setFriendCode(result.getLong("friend_code"));
                    bp.setDiscordName(result.getString("discord_name"));

                    //store all data Stringo a List
                    teams.add(bp);
                }
            }
            else if(isBreeding()) {
                //String query = "select \"Pokemon\".*, \"Discord_Name\", \"Friend_Code\" from \"Pokemon\", \"Player\" ";
                String query = "select * from (select P.*, \"Number_Complete\", \"Player\" from (select \"Pokemon_ID\", \"Name\", \"Gender\", \"Shiny\", \"Nature\", \"Ability\", \"HP\", \"Attack\", \"Defense\", \"Special_Attack\", \"Special_Defense\", \"Speed\", \"Move1\", \"Move2\", \"Move3\", \"Move4\" from \"Pokemon\") P join \"Breeding\" on \"Pokemon\" = \"Pokemon_ID\") R join \"Player\" on \"Friend_Code\" = \"Player\" join \"Game_Played\" on \"Friend_Code\" = \"Game_Played\".\"Player\"";


                if(!discordName.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Discord_Name\")=?";
                    discordIndex = currentIndex;
                    currentIndex++;
                }
                //ok to use 0, friend code can never be 0 (if it's valid at least)
                if(friendCode != 0) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "\"Friend_Code\"=?";
                    friendIndex = currentIndex;
                    currentIndex++;
                }
                if(!ign.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"IGN\")=?";
                    ignIndex = currentIndex;
                    currentIndex++;
                }

                ps = CreateConnection.con.prepareStatement(query);
                //apparently preparedstatement inputs can only be replaced by one thing rather than a string, so have to do all this after
                if(discordIndex != -1) {
                    ps.setString(discordIndex, discordName.toUpperCase());
                }
                if(friendIndex != -1) {
                    ps.setLong(friendIndex, friendCode);
                }
                if(ignIndex != -1) {
                    ps.setString(ignIndex, ign.toUpperCase());
                }



                result = ps.executeQuery();

                projects = new ArrayList<BreedingProject>();
                while(result.next()){
                        BreedingProject bp = new BreedingProject();

                        bp.setName(result.getString("name"));
                        bp.setGender(result.getString("gender"));
                        bp.setShiny(result.getString("shiny"));
                        bp.setNature(result.getString("nature"));
                        bp.setAbility(result.getString("ability"));
                        bp.setHp(result.getInt("hp"));
                        bp.setAttack(result.getInt("attack"));
                        bp.setDefense(result.getInt("defense"));
                        bp.setSpAttack(result.getInt("special_attack"));
                        bp.setSpDefense(result.getInt("special_defense"));
                        bp.setSpeed(result.getInt("speed"));
                        bp.setMove1(result.getString("move1"));
                        bp.setMove2(result.getString("move2"));
                        bp.setMove3(result.getString("move3"));
                        bp.setMove4(result.getString("move4"));
                        bp.setFriendCode(result.getLong("friend_code"));
                        bp.setDiscordName(result.getString("discord_name"));
                        bp.setComplete(result.getInt("number_complete"));

                        //store all data Stringo a List
                        projects.add(bp);
                }
            }
            else if(isEvents()) {
                //String query = "select \"Pokemon\".*, \"Discord_Name\", \"Friend_Code\" from \"Pokemon\", \"Player\" ";
                String query = "select * from (select \"Player\".*, \"IGN\" from \"Player\" join \"Game_Played\" on \"Player\" = \"Friend_Code\"";


                if(!discordName.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Discord_Name\")=?";
                    discordIndex = currentIndex;
                    currentIndex++;
                }
                //ok to use 0, friend code can never be 0 (if it's valid at least)
                if(friendCode != 0) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "\"Friend_Code\"=?";
                    friendIndex = currentIndex;
                    currentIndex++;
                }
                if(!ign.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"IGN\")=?";
                    ignIndex = currentIndex;
                    currentIndex++;
                }

                query += ") A join \"Team\" on \"Player\" = \"Friend_Code\" join \"Registered_in\" on \"TeamID\" = \"Team\" join \"Event\" on \"Event_name\" = \"Event\".\"Name\"";
                
                ps = CreateConnection.con.prepareStatement(query);
                //apparently preparedstatement inputs can only be replaced by one thing rather than a string, so have to do all this after
                if(discordIndex != -1) {
                    ps.setString(discordIndex, discordName.toUpperCase());
                }
                if(friendIndex != -1) {
                    ps.setLong(friendIndex, friendCode);
                }
                if(ignIndex != -1) {
                    ps.setString(ignIndex, ign.toUpperCase());
                }



                result = ps.executeQuery();

                teams = new ArrayList<Team>();
                while(result.next()){
                    Team r = new Team();

                    r.setEventName(result.getString("name"));
                    r.setDiscordName(result.getString("discord_name"));
                    r.setFriendCode(result.getLong("friend_code"));
                    r.setPlace(result.getInt("place"));

                    //store all data Stringo a List
                    teams.add(r);
                }
            }
        }
    }
    
    
    public String getDiscordName() {
        return discordName;
    }

    public void setDiscordName(String discordName) {
        this.discordName = discordName;
    }

    public long getFriendCode() {
        return friendCode;
    }

    public void setFriendCode(long friendCode) {
        this.friendCode = friendCode;
    }

    public String getIgn() {
        return ign;
    }

    public void setIgn(String ign) {
        this.ign = ign;
    }

    public String getRecordType() {
        return recordType;
    }

    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
    
}
