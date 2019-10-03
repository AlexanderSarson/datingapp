/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logic;

import java.time.LocalDate;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Alex
 */
public class ProfileTest {
    Profile profile;

    @Before
    public void setUp(){
        profile = new Profile(1, "Søren", "Larsen", LocalDate.now(), "test");
    }

    /**
     * Test of getFirstName method, of class Profile.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("getFirstName");
        Profile instance = profile;
        String expResult = "Søren";
        String result = instance.getFirstName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setFirstName method, of class Profile.
     */
    @Test
    public void testSetFirstName() {
        System.out.println("setFirstName");
        String firstName = "Hans";
        Profile instance = profile;
        instance.setFirstName(firstName);
        assertEquals(firstName, profile.getFirstName());
    }

    /**
     * Test of getLastName method, of class Profile.
     */
    @Test
    public void testGetLastName() {
        System.out.println("getLastName");
        Profile instance = profile;
        String expResult = "Larsen";
        String result = instance.getLastName();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLastName method, of class Profile.
     */
    @Test
    public void testSetLastName() {
        System.out.println("setLastName");
        String lastName = "Sørensen";
        Profile instance = profile;
        instance.setLastName(lastName);
        assertEquals(lastName, profile.getLastName());
    }

    /**
     * Test of getDateOfBirth method, of class Profile.
     */
    @Test
    public void testGetDateOfBirth() {
        System.out.println("getDateOfBirth");
        Profile instance = profile;
        LocalDate expResult = LocalDate.now();
        LocalDate result = instance.getDateOfBirth();
        assertEquals(expResult, result);
    }

    /**
     * Test of setDateOfBirth method, of class Profile.
     */
    @Test
    public void testSetDateOfBirth() {
        System.out.println("setDateOfBirth");
        LocalDate dateOfBirth = LocalDate.now().minusDays(1);
        Profile instance = profile;
        instance.setDateOfBirth(dateOfBirth);
        assertTrue(dateOfBirth == profile.getDateOfBirth());
    }

    /**
     * Test of getPicturePath method, of class Profile.
     */
    @Test
    public void testGetPicturePath() {
        System.out.println("getPicturePath");
        Profile instance = profile;
        String expResult = "test";
        String result = instance.getPicturePath();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPicturePath method, of class Profile.
     */
    @Test
    public void testSetPicturePath() {
        System.out.println("setPicturePath");
        String picturePath = "testtest";
        Profile instance = profile;
        instance.setPicturePath(picturePath);
        assertEquals(picturePath, profile.getPicturePath());
    }
    
}
