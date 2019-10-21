package logic;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class ProfileControllerTest {

    ProfileController pc;
    Profile profile;

    @Before
    public void setUp(){
        pc = new ProfileController();
        profile = new Profile(1,"alex","test", LocalDate.now());
        pc.addProfile(profile);
    }

    @Test
    public void addProfile() {
        Profile profile2 = new Profile(1,"alex","test", LocalDate.now());
        pc.addProfile(profile2);

        assertEquals(2,pc.getProfiles().size());
    }

    @Test
    public void getProfiles() {
        assertEquals(1,pc.getProfiles().size());
    }

    @Test
    public void getAllProfilesString() {
        String expected = pc.getAllProfilesString();
        String result = pc.getAllProfilesString();
        assertEquals(expected,result);
    }
}