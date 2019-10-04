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
import logic.ProfileController;
import org.junit.Test;
import static org.junit.Assert.*;


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
    private BasisConnectionPool mockBasisConnectionPool;
    
    @Mock
    private Connection mockConnection;
    
    @Mock
    private PreparedStatement MockStmt;
    
    @Mock
    private ResultSet rs;
    
    /**
     * Test of getAllProfiles method, of class ProfileMapper.
     */
    @Test
    public void testGetAllProfiles() throws Exception {
        when(mockBasisConnectionPool.getConnection()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(MockStmt);
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
}
