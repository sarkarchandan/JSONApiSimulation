package de.uniba.jsonApiSimulation.shared.model;

import java.util.List;

/**
 * Serves as the Model and DTO class for custom data type Faculty
 * Created by chandan on 05.05.17.
 */
public class Faculty {
    private int facultyGroupId;
    private String facultyGroupName;
    private String facultyGroupAbbreviation;
    private String getFacultyGroupHead;
    private List<SimpleCourse> simpleCourseList;

    public Faculty(){
    }

    public Faculty(int facultyGroupId, String facultyGroupName, String facultyGroupAbbreviation, String getFacultyGroupHead, List<SimpleCourse> simpleCourseList) {
        this.facultyGroupId = facultyGroupId;
        this.facultyGroupName = facultyGroupName;
        this.facultyGroupAbbreviation = facultyGroupAbbreviation;
        this.getFacultyGroupHead = getFacultyGroupHead;
        this.simpleCourseList = simpleCourseList;
    }

    public int getFacultyGroupId() {
        return facultyGroupId;
    }

    public void setFacultyGroupId(int facultyGroupId) {
        this.facultyGroupId = facultyGroupId;
    }

    public String getFacultyGroupName() {
        return facultyGroupName;
    }

    public void setFacultyGroupName(String facultyGroupName) {
        this.facultyGroupName = facultyGroupName;
    }

    public String getFacultyGroupAbbreviation() {
        return facultyGroupAbbreviation;
    }

    public void setFacultyGroupAbbreviation(String facultyGroupAbbreviation) {
        this.facultyGroupAbbreviation = facultyGroupAbbreviation;
    }

    public String getGetFacultyGroupHead() {
        return getFacultyGroupHead;
    }

    public void setGetFacultyGroupHead(String getFacultyGroupHead) {
        this.getFacultyGroupHead = getFacultyGroupHead;
    }

    public List<SimpleCourse> getSimpleCourseList() {
        return simpleCourseList;
    }

    public void setSimpleCourseList(List<SimpleCourse> simpleCourseList) {
        this.simpleCourseList = simpleCourseList;
    }
}
