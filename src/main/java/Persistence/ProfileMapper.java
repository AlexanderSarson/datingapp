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
import java.util.ArrayList;
import java.util.List;
import logic.Profile;
import logic.ProfileController;

/**
 *
 * @author Alex
 */
public class ProfileMapper {
    private BasisConnectionPool pool;

    public ProfileMapper(BasisConnectionPool pool) {
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pool.releaseConnection(conn);
        return profiles;
    }
//    public static void main(String[] args) {
//        BasisConnectionPool pool = new BasisConnectionPool();
//        ProfileMapper mapper = new ProfileMapper(pool);
//        ProfileController pc = mapper.getAllProfiles();
//        System.out.println(pc.getProfiles().get(0));
//    }
    
}
