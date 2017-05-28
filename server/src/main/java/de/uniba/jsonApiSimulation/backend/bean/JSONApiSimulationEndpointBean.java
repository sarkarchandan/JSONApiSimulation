package de.uniba.jsonApiSimulation.backend.bean;

import de.uniba.jsonApiSimulation.backend.persistence.FacultyGroupEntity;
import de.uniba.jsonApiSimulation.shared.model.Faculty;
import de.uniba.jsonApiSimulation.shared.model.SimpleCourse;
import javax.ejb.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.ArrayList;
import java.util.List;

/**
 * A SingletonBean class that deals with supporting methods for exposing our data as REST endpoint.
 * Created by chandan on 05.05.17.
 */
@Singleton
public class JSONApiSimulationEndpointBean {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager entityManager;


    /**
     * Queries the database and returns a collection of all recorded FacultyGroup entities.
     * @return
     */
    public List<FacultyGroupEntity> getAllFacultyGroupEntities(){
        List<FacultyGroupEntity> facultyGroupEntities = new ArrayList<>();
        try {
            facultyGroupEntities =
                    entityManager.createNamedQuery("findAllFacultyGroupEntity").getResultList();
        }catch (Exception e){
            e.getCause();
        }
        return facultyGroupEntities;
    }

    /**
     * Queries the database and returns a collection of the SimpleCourse types that are used to construct the JSON schema
     * that we expose through REST endpoint.
     * @param facultyGroupEntity
     * @return
     */
    public List<SimpleCourse> getAllCoursesForFacultyGroup(FacultyGroupEntity facultyGroupEntity){
        List<SimpleCourse> simpleCourseList = new ArrayList<>();

        try{
            simpleCourseList = entityManager.createNamedQuery("findAllSimpleCoursesOfferedByGivenFaculty")
                    .setParameter("facultyGroupEntity",facultyGroupEntity)
                    .getResultList();
        }catch (Exception e){
            e.getCause();
        }
        return simpleCourseList;
    }


    /**
     * Support method that queries the database and returns all recorded CourseEntity.
     * @return
     */
    public List<SimpleCourse> getAllCourses(){
        List<SimpleCourse> courseEntityList = entityManager.createNamedQuery("findAllSimpleCourses").getResultList();
        return courseEntityList;
    }

    /**
     * Constructs and returns a collection Faculties that we expose with JSON schema.
     * @return
     */
    public List<Faculty> getFaculties() {
        List<Faculty> facultyList = new ArrayList<>();

        List<FacultyGroupEntity> facultyGroupEntities = getAllFacultyGroupEntities();
        for(FacultyGroupEntity facultyGroupEntity: facultyGroupEntities){
            Faculty faculty = new Faculty(facultyGroupEntity.getFacultyGroupId()
                    ,facultyGroupEntity.getFacultyGroupName()
                    ,facultyGroupEntity.getFacultyGroupAbbreviation()
                    ,facultyGroupEntity.getGetFacultyGroupHead()
                    ,getAllCoursesForFacultyGroup(facultyGroupEntity));
            facultyList.add(faculty);
        }
        return facultyList;
    }
}
