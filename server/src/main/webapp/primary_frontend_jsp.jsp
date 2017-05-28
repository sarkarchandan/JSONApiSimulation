<%@ page import="de.uniba.jsonApiSimulation.shared.model.FacultyGroup" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: chandan
  Date: 02.05.17
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Faculty Group Interface</title>
</head>
<body>

<form action="add_faculty" method="post">
    <table align="center">
        <tr>
            <td>Faculty Group Name:</td>
            <td><input type="text" name="facultyGroupName" required="required"> </td>
        </tr>
        <tr>
            <td> Faculty Group Abbreviation:</td>
            <td><input type="text" name="facultyGroupAbbreviation" required="required"> </td>

        </tr>
        <tr>
            <td>Faculty Group Head:</td>
            <td><input type="text" name="facultyGroupHead" required="required"> </td>
        </tr>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Add"></td>
        </tr>
    </table>
</form>

<h2>Currently Recorded Faculty Groups</h2>
<table style="width:50%">

    <tr>
        <td>FacultyGroup Id | FacultyGroup Name | FacultyGroup Abbreviation | FacultyGroup Head</td>
    </tr>
    <%
        try {
            List<FacultyGroup> facultyGroupList = (List<FacultyGroup>) request.getAttribute("facultyGroupList");
            for (FacultyGroup facultyGroup : facultyGroupList) {
    %>
    <tr>
        <td><%=facultyGroup.getFacultyGroupId()%> | <%=facultyGroup.getFacultyGroupName()%>
            | <%=facultyGroup.getFacultyGroupAbbreviation()%> | <%=facultyGroup.getGetFacultyGroupHead()%>
        </td>
    </tr>
    <%}%>
    <%
        } catch (NullPointerException nP) {
            nP.getMessage();
        }
    %>
</table>

</body>
</html>
