package de.uniba.jsonApiSimulation.shared.model;

import java.io.Serializable;
import java.util.List;

/**
 * Serves as the Model and DTO class for custom data type FacultyGroup
 * Created by chandan on 01.05.17.
 */
public class FacultyGroup implements Serializable {


    private int facultyGroupId;
    private String facultyGroupName;
    private String facultyGroupAbbreviation;
    private String getFacultyGroupHead;
    private List<Course> courseList;

    public FacultyGroup() {}

    public FacultyGroup(int facultyGroupId, String facultyGroupName, String facultyGroupAbbreviation, String getFacultyGroupHead, List<Course> courseList) {
        this.facultyGroupId = facultyGroupId;
        this.facultyGroupName = facultyGroupName;
        this.facultyGroupAbbreviation = facultyGroupAbbreviation;
        this.getFacultyGroupHead = getFacultyGroupHead;
        this.courseList = courseList;
    }

    public FacultyGroup(int facultyGroupId, String facultyGroupName, String facultyGroupAbbreviation, String getFacultyGroupHead) {
        this.facultyGroupId = facultyGroupId;
        this.facultyGroupName = facultyGroupName;
        this.facultyGroupAbbreviation = facultyGroupAbbreviation;
        this.getFacultyGroupHead = getFacultyGroupHead;
    }

    public FacultyGroup(String facultyGroupName, String facultyGroupAbbreviation, String getFacultyGroupHead) {
        this.facultyGroupName = facultyGroupName;
        this.facultyGroupAbbreviation = facultyGroupAbbreviation;
        this.getFacultyGroupHead = getFacultyGroupHead;
    }

    public FacultyGroup(String facultyGroupName, String facultyGroupAbbreviation) {
        this.facultyGroupName = facultyGroupName;
        this.facultyGroupAbbreviation = facultyGroupAbbreviation;
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

    public List<Course> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<Course> courseList) {
        this.courseList = courseList;
    }

    @Override
    public int hashCode() {

        final int prime = 17;
        int result = 1;
        result=prime*result+facultyGroupId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }else if(obj==null){
            return false;
        }else if(!(obj instanceof FacultyGroup)){
            return false;
        }
        FacultyGroup facultyGroup = new FacultyGroup();
        if(facultyGroupId!=facultyGroup.facultyGroupId){
            return false;
        }
        return true;
    }
}
