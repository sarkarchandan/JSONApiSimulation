package de.uniba.jsonApiSimulation.backend.persistence;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Serves as the Entity class for persisting the data for the custom data type Faculty
 * Created by chandan on 01.05.17.
 */
@Entity
public class FacultyGroupEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int facultyGroupId;

    private String facultyGroupName;
    private String facultyGroupAbbreviation;
    private String getFacultyGroupHead;

    @OneToMany(mappedBy = "facultyGroupEntity")
    private List<CourseEntity> courseList;


    public FacultyGroupEntity(){}

    public FacultyGroupEntity(int facultyGroupId, String facultyGroupName, String facultyGroupAbbreviation, String getFacultyGroupHead) {
        this.facultyGroupId = facultyGroupId;
        this.facultyGroupName = facultyGroupName;
        this.facultyGroupAbbreviation = facultyGroupAbbreviation;
        this.getFacultyGroupHead = getFacultyGroupHead;
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

    public List<CourseEntity> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseEntity> courseList) {
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
        }else if(!(obj instanceof FacultyGroupEntity)){
            return false;
        }
        FacultyGroupEntity facultyGroupEntity = new FacultyGroupEntity();
        if(facultyGroupId!=facultyGroupEntity.facultyGroupId){
            return false;
        }
        return true;
    }
}
