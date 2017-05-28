package de.uniba.jsonApiSimulation.backend.persistence;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Serves as the Entity class for persisting the data for the custom data type Course
 * Created by chandan on 01.05.17.
 */
@Entity
public class CourseEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int courseId;

    private String courseAbbreviation;
    private String courseName;
    private String termCourseOfferedIn;

    @ManyToOne
    private FacultyGroupEntity facultyGroupEntity;

    public CourseEntity(){}

    public CourseEntity(int courseId,String courseAbbreviation, String courseName, String termCourseOfferedIn, FacultyGroupEntity facultyGroupEntity) {
        this.courseId = courseId;
        this.courseAbbreviation = courseAbbreviation;
        this.courseName = courseName;
        this.termCourseOfferedIn = termCourseOfferedIn;
        this.facultyGroupEntity = facultyGroupEntity;
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

    public FacultyGroupEntity getFacultyGroupEntity() {
        return facultyGroupEntity;
    }

    public void setFacultyGroupEntity(FacultyGroupEntity facultyGroupEntity) {
        this.facultyGroupEntity = facultyGroupEntity;
    }

    @Override
    public int hashCode() {

        final int prime = 17;
        int result = 1;
        result=prime*result+courseId;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if(this==obj){
            return true;
        }else if(obj==null){
            return false;
        }else if(!(obj instanceof CourseEntity)){
            return false;
        }
        CourseEntity courseEntity = new CourseEntity();
        if(courseId!=courseEntity.courseId){
            return false;
        }
        return true;
    }
}
