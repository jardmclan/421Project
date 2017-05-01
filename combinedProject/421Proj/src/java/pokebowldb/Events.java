/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokebowldb;

import java.io.Serializable;
import java.sql.PreparedStatement;
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

    public void getInfo() {
        
    }
    
    public boolean isTeams() {
        return infoType.equals("Teams");
    }
    
    public boolean isInfo() {
        return infoType.equals("Info");
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
