<%-- 
    Document   : ShowProfiles
    Created on : Oct 6, 2019, 5:51:56 PM
    Author     : Alex
--%>

<%@page import="java.util.List"%>
<%@page import="logic.Profile"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="image/jpeg">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Profile> profiles = (List) request.getAttribute("profiles");
        %>
        <div > 
            <%
                for (Profile profile : profiles) {
            %>
            <div>
                <div ><img src="<%= profile.getPicturePath()%>" alt="profile picture" ><br>Name: <%= profile.getFirstName()%> <%= profile.getLastName()%><br>Birthday: <%= profile.getDateOfBirth().toString()%> </div>
            </div>
            <%
                }
            %>

        </div>
    </body>
</html>
