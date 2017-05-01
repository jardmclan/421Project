/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokebowldb;

import java.io.Serializable;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Jard
 */
@ManagedBean(name = "Events")
@SessionScoped
public class Events implements Serializable {
    String name;
    String format;
    String location;
    String after;
    String before;
    String infoType;
    List<Event> eventDetails;
    List<Team> teams;
    PreparedStatement ps;
    ResultSet result;

    private boolean isDate(String date){

        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        f.setLenient(false);

        try {
            f.parse(date);
        } 
        catch (ParseException e) {
                return false;
        }

        return true;
    }
    
    public void getInfo() throws SQLException {
        if(infoType != null) {
            if(CreateConnection.con == null) {
                CreateConnection.getConnection();
            }
            boolean first = true;
            int nameIndex = -1;
            int formatIndex = -1;
            int locationIndex = -1;
            int afterIndex = -1;
            int beforeIndex = -1;
            int currentIndex = 1;
            if(isInfo()) {
                String query = "select * from \"Event\"";


                if(!name.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Name\")=?";
                    nameIndex = currentIndex;
                    currentIndex++;
                }
                if(!format.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Format\")=?";
                    formatIndex = currentIndex;
                    currentIndex++;
                }
                if(!location.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Location\")=?";
                    locationIndex = currentIndex;
                    currentIndex++;
                }
                if(isDate(before)) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "\"Start_date\"<?";
                    beforeIndex = currentIndex;
                    currentIndex++;
                }
                if(isDate(after)) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "\"Start_date\">?";
                    afterIndex = currentIndex;
                    currentIndex++;
                }

                ps = CreateConnection.con.prepareStatement(query);
                
                
                //apparently preparedstatement inputs can only be replaced by one thing rather than a string, so have to do all this after
                if(nameIndex != -1) {
                    ps.setString(nameIndex, name.toUpperCase());
                }
                if(formatIndex != -1) {
                    ps.setString(formatIndex, format.toUpperCase());
                }
                if(locationIndex != -1) {
                    ps.setString(locationIndex, location.toUpperCase());
                }
                if(beforeIndex != -1) {
                    ps.setDate(beforeIndex, Date.valueOf(before));
                }
                if(afterIndex != -1) {
                    ps.setDate(afterIndex, Date.valueOf(after));
                }



                result = ps.executeQuery();

                eventDetails = new ArrayList<Event>();
                while(result.next()){
                    Event r = new Event();

                    r.setName(result.getString("name"));
                    r.setLocation(result.getString("location"));
                    r.setFormat(result.getString("format"));
                    r.setStartDate(result.getDate("start_date").toString());
                    r.setEndDate(result.getDate("end_date").toString());
                    r.setRules(result.getString("rules"));
                    r.setDescription(result.getString("description"));
                    r.setType(result.getString("type"));

                    //store all data Stringo a List
                    eventDetails.add(r);
                }
            
            }
            if(isTeams()) {
                String query = "select * from \"Event\" join \"Registered_in\" on \"Event_name\" = \"Name\" join \"Team\" on \"TeamID\"=\"Team\" join \"Contains\" on \"TeamID\"=\"Contains\".\"Team\" join \"Pokemon\" on \"Pokemon_ID\"=\"Pokemon\" ";


                if(!name.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Event\".\"Name\")=?";
                    nameIndex = currentIndex;
                    currentIndex++;
                }
                if(!format.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Format\")=?";
                    formatIndex = currentIndex;
                    currentIndex++;
                }
                if(!location.equals("")) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "upper(\"Location\")=?";
                    locationIndex = currentIndex;
                    currentIndex++;
                }
                if(isDate(before)) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "\"Start_date\"<?";
                    beforeIndex = currentIndex;
                    currentIndex++;
                }
                if(isDate(after)) {
                    if(first) {
                        query += "where ";
                        first = false;
                    }
                    else {
                        query += " AND ";
                    }
                    query += "\"Start_date\">?";
                    afterIndex = currentIndex;
                    currentIndex++;
                }

                query += "order by \"TeamID\"";
                
                ps = CreateConnection.con.prepareStatement(query);
                
                
                //apparently preparedstatement inputs can only be replaced by one thing rather than a string, so have to do all this after
                if(nameIndex != -1) {
                    ps.setString(nameIndex, name.toUpperCase());
                }
                if(formatIndex != -1) {
                    ps.setString(formatIndex, format.toUpperCase());
                }
                if(locationIndex != -1) {
                    ps.setString(locationIndex, location.toUpperCase());
                }
                if(beforeIndex != -1) {
                    ps.setDate(beforeIndex, Date.valueOf(before));
                }
                if(afterIndex != -1) {
                    ps.setDate(afterIndex, Date.valueOf(after));
                }



                result = ps.executeQuery();

                teams = new ArrayList<Team>();
                while(result.next()){
                    Team bp = new Team();

                    bp.setName(result.getString("pokemon.name"));
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
        }
    }
    
    public boolean isTeams() {
        return infoType != null && infoType.equals("teams");
    }
    
    public boolean isInfo() {
        return infoType != null && infoType.equals("info");
    }
    
    
    public List<Event> getEventDetails() {
        return eventDetails;
    }
    
    public List<Team> getTeams() {
        return teams;
    }
    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getAfter() {
        return after;
    }

    public void setAfter(String after) {
        this.after = after;
    }

    public String getBefore() {
        return before;
    }

    public void setBefore(String before) {
        this.before = before;
    }

    public String getInfoType() {
        return infoType;
    }

    public void setInfoType(String infoType) {
        this.infoType = infoType;
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
    
    public Events() {
        
    }
}
