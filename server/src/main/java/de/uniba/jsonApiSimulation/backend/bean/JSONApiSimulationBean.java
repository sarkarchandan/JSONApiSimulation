package de.uniba.jsonApiSimulation.backend.bean;

import de.uniba.jsonApiSimulation.backend.persistence.CourseEntity;
import de.uniba.jsonApiSimulation.backend.persistence.FacultyGroupEntity;
import de.uniba.jsonApiSimulation.shared.businessinterface.JSONApiSimulation;
import de.uniba.jsonApiSimulation.shared.exception.CoursePersistenceException;
import de.uniba.jsonApiSimulation.shared.exception.FacultyGroupPersistenceException;
import de.uniba.jsonApiSimulation.shared.model.Course;
import de.uniba.jsonApiSimulation.shared.model.FacultyGroup;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import java.util.List;
import java.util.logging.Logger;

/**
 * Wraps up the server side logic dealing with data persistence.
 * Created by chandan on 01.05.17.
 */
@Stateless
@Remote(JSONApiSimulation.class)
public class JSONApiSimulationBean implements JSONApiSimulation {

    private static final Logger logger = Logger.getLogger(JSONApiSimulationBean.class.getName());


    @PersistenceContext(type = PersistenceContextType.TRANSACTION)
    EntityManager entityManager;


    /**
     * Persists a FacultyGroup in the database
     * @param facultyGroup
     */
    @Override
    public void createFacultyGroup(FacultyGroup facultyGroup) {
        if(facultyGroup != null && facultyGroup instanceof FacultyGroup){
            logger.info("Faculty Group Name: "+facultyGroup.getFacultyGroupName());
            logger.info("Faculty Group Abbreviation: "+facultyGroup.getFacultyGroupAbbreviation());
            logger.info("Faculty Group Head: "+facultyGroup.getGetFacultyGroupHead());

            FacultyGroupEntity facultyGroupEntity = new FacultyGroupEntity();
            facultyGroupEntity.setFacultyGroupName(facultyGroup.getFacultyGroupName());
            facultyGroupEntity.setFacultyGroupAbbreviation(facultyGroup.getFacultyGroupAbbreviation());
            facultyGroupEntity.setGetFacultyGroupHead(facultyGroup.getGetFacultyGroupHead());

            entityManager.persist(facultyGroupEntity);
        }
    }

    /**
     * Queries the database for all recorded FacultyGroups using the named-queries that we have defined in the
     * respective deployment descriptor.
     * @return
     * @throws FacultyGroupPersistenceException
     */
    @Override
    public List<FacultyGroup> getAllFacultyGroups() throws FacultyGroupPersistenceException {
        List<FacultyGroup> facultyGroupList = entityManager.createNamedQuery("findAllFacultyGroups").getResultList();
        if(facultyGroupList.size() > 0){
            FacultyGroup facultyGroup = facultyGroupList.get(0);
            if(!(facultyGroup instanceof FacultyGroup)){
                return  null;
            }
        }
        return facultyGroupList;
    }


    /**
     * Queries the database for all recorded Courses using the named-queries that we have defined in the
     * respective deployment descriptor.
     * @return
     * @throws CoursePersistenceException
     */
    @Override
    public List<CourseEntity> getAllCourses() throws CoursePersistenceException {
        List<CourseEntity> courseEntityList = entityManager.createNamedQuery("findAllCourses").getResultList();
        if(courseEntityList.size() > 0){
            CourseEntity courseEntity = courseEntityList.get(0);
            if(!(courseEntity instanceof CourseEntity)){
                return null;
            }
        }
        return courseEntityList;
    }

    /**
     * Queries the database and returns a particular FacultyGroup Entity based on the FacultyGroup Abbreviation passed in.
     * @param facultyGroupAbbreviation
     * @return
     */
    @Override
    public FacultyGroupEntity getFacultyGroupEntity(String facultyGroupAbbreviation) {
        FacultyGroupEntity facultyGroupEntity = null;
        if(facultyGroupAbbreviation != null){

            facultyGroupEntity = (FacultyGroupEntity) entityManager.createNamedQuery("findFacultyGroupEntityByAbbreviation")
                    .setParameter("facultyGroupAbbreviation",facultyGroupAbbreviation)
                    .getSingleResult();

        }
        if(!(facultyGroupEntity instanceof FacultyGroupEntity)){
            return null;
        }
        return facultyGroupEntity;
    }

    /**
     * FacultyGroup and Course share a relationship. This method adds a Course to a specific FacultyGroup.
     * @param facultyGroupAbbreviation
     * @param course
     */
    @Override
    public void addCourseToFacultyGroup(String facultyGroupAbbreviation, Course course) {
        FacultyGroupEntity facultyGroupEntity = null;
        if(facultyGroupAbbreviation != null){
            facultyGroupEntity = getFacultyGroupEntity(facultyGroupAbbreviation);
        }

        if(facultyGroupEntity instanceof FacultyGroupEntity && course instanceof Course){

            CourseEntity courseEntity = new CourseEntity();

            courseEntity.setCourseAbbreviation(course.getCourseAbbreviation());
            courseEntity.setCourseName(course.getCourseName());
            courseEntity.setTermCourseOfferedIn(course.getTermCourseOfferedIn());
            courseEntity.setFacultyGroupEntity(facultyGroupEntity);

            entityManager.persist(courseEntity);
        }

    }
}
