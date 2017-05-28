package de.uniba.jsonApiSimulation.shared.businessinterface;

import de.uniba.jsonApiSimulation.backend.persistence.CourseEntity;
import de.uniba.jsonApiSimulation.backend.persistence.FacultyGroupEntity;
import de.uniba.jsonApiSimulation.shared.exception.CoursePersistenceException;
import de.uniba.jsonApiSimulation.shared.exception.FacultyGroupPersistenceException;
import de.uniba.jsonApiSimulation.shared.model.Course;
import de.uniba.jsonApiSimulation.shared.model.FacultyGroup;

import java.util.List;

/**
 * Business Interface for the JSONApiSimulationBean
 * Created by chandan on 01.05.17.
 */
public interface JSONApiSimulation {

    /**Adds a new FacultyGroup to the database*/
    void createFacultyGroup(FacultyGroup facultyGroup);
    /**Adds a new Course to a FacultyGroup*/
    void addCourseToFacultyGroup(String facultyGroupAbbreviation,Course course);
    /**Fetches a collection of all Faculty Groups*/
    List<FacultyGroup> getAllFacultyGroups() throws FacultyGroupPersistenceException;
    /**Fetches a collection of all Courses*/
    List<CourseEntity> getAllCourses() throws CoursePersistenceException;
    /**Fetches a particular FacultyGroupEntity by its abbreviation*/
    FacultyGroupEntity getFacultyGroupEntity(String facultyGroupAbbreviation);

}
