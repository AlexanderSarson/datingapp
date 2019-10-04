/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentation;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author Alex
 */
@RunWith(MockitoJUnitRunner.class)
public class FrontControllerTest {

    @InjectMocks
    private FrontController fc;

    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private RequestDispatcher rd;

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
        when(request.getParameter("cmd")).thenReturn("showProfiles");
        when(request.getRequestDispatcher("showProfiles.jsp")).thenReturn(rd);
        fc.processRequest(request, response);
        verify(request, times(1)).getRequestDispatcher("showProfiles.jsp");
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
