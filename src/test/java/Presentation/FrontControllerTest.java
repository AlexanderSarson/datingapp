/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import Persistence.ProfileMapper;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import logic.ProfileController;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Alex
 */
@RunWith(MockitoJUnitRunner.class)
public class FrontControllerTest {

    @InjectMocks
    private FrontController fc;
    
    @Spy
    private FrontController spyFC;

    @Mock
    private HttpServletRequest request;
    
    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher rd;
    
    @Mock
    private ProfileController pc;
    
    @Mock
    private ProfileMapper pm;
       
    /**
     * Test of processRequest method, of class FrontController.
     */
    @Test
    public void testProcessRequestCreateProfile() throws Exception {
        when(request.getParameter("cmd")).thenReturn("createProfile");
        when(request.getRequestDispatcher("createProfile.html")).thenReturn(rd);
        fc.processRequest(request, response);
        verify(request, times(1)).getRequestDispatcher("createProfile.html");
    }

    /**
     * Test of processRequest method, of class FrontController.
     */
    @Test
    public void testProcessRequestShowProfiles() throws Exception {
        when(request.getParameter("cmd")).thenReturn("ShowProfiles");
        when(request.getRequestDispatcher("ShowProfiles.jsp")).thenReturn(rd);
        doReturn(pm).when(spyFC).makeProfileMapper();
        when(pm.getAllProfiles()).thenReturn(pc);
        spyFC.processRequest(request, response);
        verify(request, times(1)).getRequestDispatcher("ShowProfiles.jsp");
    }

    /**
     * Test of processRequest method, of class FrontController.
     */
    @Test
    public void testProcessRequestindex() throws Exception {
        when(request.getParameter("cmd")).thenReturn("");
        when(request.getRequestDispatcher("index.html")).thenReturn(rd);
        fc.processRequest(request, response);
        verify(request, times(1)).getRequestDispatcher("index.html");
    }

}
