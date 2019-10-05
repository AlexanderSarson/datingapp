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
import java.time.LocalDate;
import javax.sql.DataSource;
import logic.Profile;
import logic.ProfileController;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;


import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.*;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Alex
 */
@RunWith(MockitoJUnitRunner.class)
public class ProfileMapperTest {
    
    @InjectMocks
    private ProfileMapper pm;
    
    @Mock
    private DataSource mockBasisConnectionPool;
    
    @Mock
    private Connection mockConnection;
    
    @Mock
    private PreparedStatement MockStmt;
    
    @Mock
    private ResultSet rs;
    
    @Before
    public void setUp() throws Exception{
        when(mockBasisConnectionPool.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(MockStmt);
    }
    
    /**
     * Test of getAllProfiles method, of class ProfileMapper.
     */
    @Test
    public void testGetAllProfiles() throws Exception {
        when(MockStmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(rs.getInt("profile_id")).thenReturn(1);
        when(rs.getString("first_name")).thenReturn("Oscar");
        when(rs.getString("last_name")).thenReturn("sørensen");
        Date date = new Date(1970);
        when(rs.getDate("birthday")).thenReturn(date);
        ProfileController pc = pm.getAllProfiles();
        assertEquals("Oscar", pc.getProfiles().get(0).getFirstName());
    }
    
    @Test
    public void testgetNextProfileId() throws Exception{
        when(MockStmt.executeQuery()).thenReturn(rs);
        when(rs.next()).thenReturn(Boolean.TRUE).thenReturn(Boolean.FALSE);
        when(rs.getInt("max(profile_id)")).thenReturn(10);
        int expected = 11;
        int result = pm.getNextProfileId();
        assertEquals(expected, result);
    }
    
    @Test
    public void testCreateProfilePostive() throws Exception{
        Profile profile = new Profile(1, "hej", "med", LocalDate.now());
        when(MockStmt.executeUpdate()).thenReturn(1);
        boolean result = pm.createProfile(profile);
        assertTrue(result);
    }
    
    @Test
    public void testCreateProfileNegative() throws Exception{
        Profile profile = new Profile(1, "hej", "med", LocalDate.now());
        when(MockStmt.executeUpdate()).thenReturn(0);
        boolean result = pm.createProfile(profile);
        assertFalse(result);
    }
}
