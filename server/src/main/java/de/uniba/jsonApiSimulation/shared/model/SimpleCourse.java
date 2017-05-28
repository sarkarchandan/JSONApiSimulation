package de.uniba.jsonApiSimulation.shared.model;

import java.io.Serializable;

/**
 * Serves as the model and DTO class for the custom data type SimpleCourse
 * Created by chandan on 05.05.17.
 */
public class SimpleCourse implements Serializable {
    private int courseId;
    private String courseAbbreviation;
    private String courseName;
    private String termCourseOfferedIn;
    private String courseFacultyGroup;

    public SimpleCourse(){}

    public SimpleCourse(int courseId, String courseAbbreviation, String courseName, String termCourseOfferedIn, String courseFacultyGroup) {
        this.courseId = courseId;
        this.courseAbbreviation = courseAbbreviation;
        this.courseName = courseName;
        this.termCourseOfferedIn = termCourseOfferedIn;
        this.courseFacultyGroup = courseFacultyGroup;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseAbbreviation() {
        return courseAbbreviation;
    }

    public void setCourseAbbreviation(String courseAbbreviation) {
        this.courseAbbreviation = courseAbbreviation;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTermCourseOfferedIn() {
        return termCourseOfferedIn;
    }

    public void setTermCourseOfferedIn(String termCourseOfferedIn) {
        this.termCourseOfferedIn = termCourseOfferedIn;
    }

    public String getCourseFacultyGroup() {
        return courseFacultyGroup;
    }

    public void setCourseFacultyGroup(String courseFacultyGroup) {
        this.courseFacultyGroup = courseFacultyGroup;
    }
}
