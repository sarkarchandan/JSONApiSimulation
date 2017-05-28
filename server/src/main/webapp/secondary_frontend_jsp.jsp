<%@ page import="de.uniba.jsonApiSimulation.shared.model.FacultyGroup" %>
<%@ page import="java.util.List" %>
<%@ page import="de.uniba.jsonApiSimulation.backend.persistence.CourseEntity" %><%--
  Created by IntelliJ IDEA.
  User: chandan
  Date: 02.05.17
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Course Interface</title>
</head>
<body>

<form action="add_course" method="post">
    <table align="center">
        <tr>
            <td> Course Name:</td>
            <td><input type="text" name="courseName" required="required"> </td>
        </tr>
        <tr>
            <td> Course Abbreviation:</td>
            <td><input type="text" name="courseAbbreviation" required="required"> </td>
        </tr>

        <tr>
            <td> Course Term:</td>
            <td> <select name="termCourseOfferedIn" size="1" required="required" >
                <option value="Summer">Summer</option>
                <option value="Winter">Winter</option>
            </select>
            </td>
        </tr>

        <tr>
            <td> Faculty Group:</td>
            <td> <select name="courseFacultyGroup" size="1" required="required">
                    <%
                        try {
                            List<FacultyGroup> facultyGroupList = (List<FacultyGroup>) request.getAttribute("facultyGroupList");
                            for (FacultyGroup facultyGroup : facultyGroupList) {
                    %>

                    <option value="<%=facultyGroup.getFacultyGroupAbbreviation()%>" title="FacultyGroup">
                        <%=facultyGroup.getFacultyGroupAbbreviation()%>
                    </option>

                    <%}%>
                    <%
                        } catch (NullPointerException nP) {
                            nP.getMessage();
                        }
                    %>
                </select>
            </td>
        <tr>
            <td>&nbsp;</td>
            <td><input type="submit" value="Add"></td>
        </tr>

    </table>
</form>

<h2>Currently Offered Courses</h2>
<table style="width:50%">

    <tr>
        <td>Course Id | Course Abbreviation | Course Name | Course Term | Faculty Group</td>
    </tr>
    <%
        try {
            List<CourseEntity> courseEntityList = (List<CourseEntity>) request.getAttribute("currentlyOfferedCourses");
            for (CourseEntity courseEntity : courseEntityList) {
    %>
    <tr>
        <td><%=courseEntity.getCourseId()%> | <%=courseEntity.getCourseAbbreviation()%>
            | <%=courseEntity.getCourseName()%> | <%=courseEntity.getTermCourseOfferedIn()%>
            | <%= courseEntity.getFacultyGroupEntity().getFacultyGroupAbbreviation()%>
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
