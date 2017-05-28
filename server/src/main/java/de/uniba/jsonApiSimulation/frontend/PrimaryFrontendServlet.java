package de.uniba.jsonApiSimulation.frontend;

import de.uniba.jsonApiSimulation.shared.businessinterface.JSONApiSimulation;
import de.uniba.jsonApiSimulation.shared.exception.FacultyGroupPersistenceException;
import de.uniba.jsonApiSimulation.shared.model.FacultyGroup;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * Serves as the utility servlet class for the Primary frontend page
 * Created by chandan on 02.05.17.
 */
@WebServlet(name = "PrimaryFrontendServlet")
public class PrimaryFrontendServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(PrimaryFrontendServlet.class.getName());

    //Static error String to be used in case any necessary field is left empty while submitting.
    private static final String EMPTY_FIELD_ERROR = "This Field Cannot Be Empty";

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

        String facultyGroupName = request.getParameter("facultyGroupName");
        String facultyGroupAbbreviation = request.getParameter("facultyGroupAbbreviation");
        String facultyGroupHead = request.getParameter("facultyGroupHead");

        if(!facultyGroupName.equals("") && !facultyGroupAbbreviation.equals("") && !facultyGroupHead.equals("") && jsonApiSimulation!= null){
            //Instantiating FacultyGroup with overloaded constructor
            FacultyGroup newFacultyGroup = new FacultyGroup(facultyGroupName,facultyGroupAbbreviation,facultyGroupHead);
            if(newFacultyGroup instanceof FacultyGroup){
                jsonApiSimulation.createFacultyGroup(newFacultyGroup);
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
        request.getRequestDispatcher("/primary_frontend_jsp.jsp").forward(request,response);
    }

    /**
     * Fetches the records from the database to update the UI.
     * @param request
     * @param response
     */
    public void fetchUpdate(HttpServletRequest request,HttpServletResponse response){
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
    }
}
