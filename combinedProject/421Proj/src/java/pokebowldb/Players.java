/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokebowldb;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
        return recordType.equals("General");
    }
    
    public boolean isEvents() {
        return recordType.equals("Events");
    }
    
    public boolean isTeams() {
        return recordType.equals("Teams");
    }
    public boolean isBreeding() {
        return recordType.equals("Breeding");
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
    
    public void getRecords() {
        
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
