package de.uniba.jsonApiSimulation.frontend;

import de.uniba.jsonApiSimulation.backend.persistence.CourseEntity;
import de.uniba.jsonApiSimulation.backend.persistence.FacultyGroupEntity;
import de.uniba.jsonApiSimulation.shared.businessinterface.JSONApiSimulation;
import de.uniba.jsonApiSimulation.shared.exception.CoursePersistenceException;
import de.uniba.jsonApiSimulation.shared.exception.FacultyGroupPersistenceException;
import de.uniba.jsonApiSimulation.shared.model.Course;
import de.uniba.jsonApiSimulation.shared.model.FacultyGroup;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

/**
 * Serves as the utility servlet class for the Secondary frontend page
 * Created by chandan on 02.05.17.
 */
@WebServlet(name = "SecondaryFrontendServlet")
public class SecondaryFrontendServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(PrimaryFrontendServlet.class.getName());

    @EJB
    JSONApiSimulation jsonApiSimulation;

    /**
     * doPOST takes the data from the UI and calls the Bean method to persist the data.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String courseAbbreviation = request.getParameter("courseAbbreviation");
        String courseName = request.getParameter("courseName");
        String termCourseOfferedIn = request.getParameter("termCourseOfferedIn");
        String courseFacultyGroup = request.getParameter("courseFacultyGroup");

        if(jsonApiSimulation != null){
            Course newCourse = new Course(courseAbbreviation,courseName,termCourseOfferedIn);
            if(newCourse instanceof Course){
                jsonApiSimulation.addCourseToFacultyGroup(courseFacultyGroup,newCourse);
            }
        }
        doGet(request,response);
    }

    /**
     * Presents the UI.
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        fetchUpdate(request,response);
        request.getRequestDispatcher("/secondary_frontend_jsp.jsp").forward(request,response);
    }


    /**
     * Fetches the records from the database to update the UI.
     * @param request
     * @param response
     */
    public void fetchUpdate(HttpServletRequest request,HttpServletResponse response){
        List<CourseEntity> courseEntityList;
        List<FacultyGroup> facultyGroupList;

        try {
            facultyGroupList = jsonApiSimulation.getAllFacultyGroups();
            if(facultyGroupList.size() > 0){
                logger.info("The Faculty Group Count is: "+facultyGroupList.size());
                request.setAttribute("facultyGroupList",facultyGroupList);
            }
        }catch (FacultyGroupPersistenceException fGPE){
            fGPE.getCause();
        }

        try {
            courseEntityList = jsonApiSimulation.getAllCourses();
            if(courseEntityList.size() > 0){
                logger.info("The Course Count is: "+courseEntityList.size());
                request.setAttribute("currentlyOfferedCourses",courseEntityList);
            }
        }catch (CoursePersistenceException cPE){
            cPE.getCause();
        }
    }
}
