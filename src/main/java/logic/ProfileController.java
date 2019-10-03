/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alex
 */
public class ProfileController {
    List<Profile> profiles;

    public ProfileController() {
        profiles = new ArrayList<>();
    }
    
    public void addProfile(Profile profile){
        profiles.add(profile);
    }
    
    
}
