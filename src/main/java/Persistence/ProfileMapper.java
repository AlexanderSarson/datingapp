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

    public ProfileMapper() {
    }
    
    public ProfileMapper(DataSource pool) {
        this.pool = pool;
    }

    public ProfileController getAllProfiles() {
        String sql = "SELECT * FROM datingprofiles";
        ProfileController profiles = new ProfileController();
        Profile profile = null;
        try ( Connection conn = pool.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);  ResultSet rs = stmt.executeQuery();) {
            while (rs.next()) {
                int id = rs.getInt("profile_id");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                Date birthday = rs.getDate("birthday");
                LocalDate date = birthday.toLocalDate();
                String picturePath = rs.getString("picture_path");
                profile = new Profile(id, firstName, lastName, date, picturePath);
                profiles.addProfile(profile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profiles;
    }

    public int getNextProfileId() {
        String sql = "Select max(profile_id) from datingprofiles";
        int profile_id = 0;
        try ( Connection conn = pool.getConnection();  PreparedStatement stmt = conn.prepareStatement(sql);  ResultSet rs = stmt.executeQuery();) {
            if(rs.next()){
                profile_id = rs.getInt("max(profile_id)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return profile_id + 1;
    }
    
    public boolean createProfile(Profile profile){
        String updateSql = "INSERT INTO datingprofiles (profile_id, first_name, last_name, birthday,picture_path) VALUES (?,?,?,?,?)";
        boolean isUpdated = false;
        try ( Connection conn = pool.getConnection();  PreparedStatement stmt = conn.prepareStatement(updateSql);) {
            stmt.setInt(1, profile.getId());
            stmt.setString(2, profile.getFirstName());
            stmt.setString(3, profile.getLastName());
            stmt.setDate(4, java.sql.Date.valueOf(profile.getDateOfBirth()));
            stmt.setString(5, profile.getPicturePath());
            int update = stmt.executeUpdate();
            if(update > 0) isUpdated = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isUpdated;
    }
}
