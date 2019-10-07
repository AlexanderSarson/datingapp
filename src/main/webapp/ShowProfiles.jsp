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
        <link rel="stylesheet" href="css/uikit.min.css" />
        <script src="js/uikit.min.js"></script>
        <script src="js/uikit-icons.min.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            List<Profile> profiles = (List) request.getAttribute("profiles");
        %>
        <div class="uk-child-width-expand@s uk-text-center" uk-grid uk-height-match="target: > div > .uk-card">
            <%
                for (Profile profile : profiles) {
            %>
            <div>
                <div class="uk-card uk-card-default uk-card-body"><img src="c:/temp/pictures/butterfly.jpg" alt="Profile Picture" width="100" height="100"><br>Name: <%= profile.getFirstName()%> <%= profile.getLastName()%><br>Birthday: <%= profile.getDateOfBirth().toString()%> </div>
            </div>
            <%
                }
            %>

        </div>
    </body>
</html>
