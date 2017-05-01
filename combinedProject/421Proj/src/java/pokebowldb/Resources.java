/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pokebowldb;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.sql.rowset.spi.SyncFactoryException;

/**
 *
 * @author Jard
 */
@ManagedBean(name = "Resources")
@SessionScoped
public class Resources {
    String version;
    String category;
    String descriptionPart;
    List<Resource> resDetails;
    ResultSet result;
    PreparedStatement ps;

    public void getResources() throws SQLException  {
        if(CreateConnection.con == null) {
            CreateConnection.getConnection();
        }
        boolean first = true;
        int versionIndex = -1;
        int categoryIndex = -1;
        int descriptionPartIndex = -1;
        int currentIndex = 1;
        //String query = "select \"Pokemon\".*, \"Discord_Name\", \"Friend_Code\" from \"Pokemon\", \"Player\" ";
        String query = "select \"Category\", \"URL\", \"Description\", \"Generation\" from \"Res_Category\" join \"Resource\" on \"Res_Category\".\"Resource\" = \"Resource\".\"URL\" ";
        
        
        if(version != null && !version.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Generation\")=?";
            versionIndex = currentIndex;
            currentIndex++;
        }
        if(category != null && !category.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Category\")=?";
            categoryIndex = currentIndex;
            currentIndex++;
        }
        if(!descriptionPart.equals("")) {
            if(first) {
                query += "where ";
                first = false;
            }
            else {
                query += " AND ";
            }
            query += "upper(\"Description\")LIKE ?";
            descriptionPartIndex = currentIndex;
            currentIndex++;
        }
        
        ps = CreateConnection.con.prepareStatement(query);
        //apparently preparedstatement inputs can only be replaced by one thing rather than a string, so have to do all this after
        if(versionIndex != -1) {
            ps.setString(versionIndex, version.toUpperCase());
        }
        if(categoryIndex != -1) {
            ps.setString(categoryIndex, category.toUpperCase());
        }
        if(descriptionPartIndex != -1) {
            ps.setString(descriptionPartIndex, "%" + descriptionPart.toUpperCase() + "%");
        }
        
        
        
        result = ps.executeQuery();
        
        resDetails = new ArrayList<Resource>();
        while(result.next()){
                Resource r = new Resource();

                r.setCategory(result.getString("category"));
                r.setDescription(result.getString("description"));
                r.setGen(result.getString("generation"));
                r.setUrl(result.getString("url"));
                
                //store all data Stringo a List
                resDetails.add(r);
        }
    }
    
    public List<Resource> getResDetails() throws SyncFactoryException {
        return resDetails;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescriptionPart() {
        return descriptionPart;
    }

    public void setDescriptionPart(String descriptionPart) {
        this.descriptionPart = descriptionPart;
    }
    
    
}
