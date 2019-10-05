/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.sql.DataSource;
import logic.Profile;
import logic.ProfileController;

/**
 *
 * @author Alex
 */
public class ProfileMapper {
    private DataSource pool;

    public ProfileMapper(DataSource pool) {
        this.pool = pool;
    }
    
    
    
    public ProfileController getAllProfiles(){
        String sql = "SELECT * FROM datingprofiles";
        ProfileController profiles = new ProfileController();
        Connection conn = null;
        PreparedStatement stmt;
        ResultSet rs = null;
        try {
            conn = pool.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Profile profile = null;
        try {
            while (rs.next()) {
                int id = rs.getInt("profile_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date birthday = rs.getDate("birthday");
                LocalDate date = birthday.toLocalDate();
                String picturePath = rs.getString("picture_path");
                profile = new Profile(id, firstName,lastName,date,picturePath);
                profiles.addProfile(profile);
            }
            if(conn != null){
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profiles;
    }
    
    public int getNextProfileId(){
        String sql = "Select max(profile_id) from datingprofiles";
        Connection conn = null;
        PreparedStatement stmt;
        ResultSet rs = null;
        int profile_id = 0;
        try {
            conn = pool.getConnection();
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            while (rs.next()) {
                profile_id = rs.getInt("max(profile_id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile_id + 1;
    }
//    public static void main(String[] args) {
//        BasisConnectionPool pool = new BasisConnectionPool();
//        ProfileMapper mapper = new ProfileMapper(pool);
//        ProfileController pc = mapper.getAllProfiles();
//        int number = mapper.getNextProfileId();
//        System.out.println(pc.getProfiles().get(0));
//        System.out.println(number);
//    }
    
}
