package com.ideas2it.controller;

import com.ideas2it.model.Employee;
import com.ideas2it.datetimeutils.DateTimeUtils;
import com.ideas2it.model.EmployeeProject;
import com.ideas2it.model.LeaveRecord;
import com.ideas2it.service.*;

import jakarta.persistence.NoResultException;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class gets input from the servlet and pass the employee details
 * leave record and project details to service
 *
 * @author Nithish K
 * @version 1.0
 * @since 12.10.2022
 */
@Controller
public class EmployeeController {

    private final EmployeeService employeeServiceImpl;
    private final LeaveRecordService leaveRecordServiceImpl;
    private final EmployeeProjectService employeeProjectServiceImpl;
    public EmployeeController(EmployeeService employeeServiceImpl, LeaveRecordService leaveRecordServiceImpl, EmployeeProjectService employeeProjectServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
        this.leaveRecordServiceImpl = leaveRecordServiceImpl;
        this.employeeProjectServiceImpl = employeeProjectServiceImpl;
    }

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/employee")
    public String employee() {
        return "employee";
    }

    @RequestMapping(value = "/getEmployeeForProject")
    public String AssignProjects() {
        return "getEmployeeForProject";
    }

    @RequestMapping(value = "/performGetEmployee")
    public String getEmployee() {
        return "performGetEmployee";
    }

    @RequestMapping(value = "/performAddEmployee")
    public String createEmployee(Model model) {
        model.addAttribute("employee",new Employee());
        return "performAddEmployee";
    }

    /**
     * Gets the employee details from the employee servlet.
     * This method invokes another method for creating employee id
     * Gets input for employee name, employee Type, dateOfBirth, mobile number
     * email-id, designation
     * Every parameter is validating by validation util class
     * @param employee gets employee object
     */
    @PostMapping(value = "/addEmployee")
    public String addEmployee(@ModelAttribute("employee") Employee employee, Model model) {
        String status;
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        employee.setEmployeeId(employeeServiceImpl.createEmployeeId());
        boolean isValidMobileNumber = employeeServiceImpl.validateMobileNumber(employee.getMobileNumber());
        boolean isValidEmailId = employeeServiceImpl.validateEmailId(employee.getEmailId());
        employee.setCreatedAt(dateTimeUtils.getDate());
        employee.setModifiedAt(dateTimeUtils.getDate());

        if (isValidEmailId && isValidMobileNumber) {
            status = "Mobile Number and Email ID Already Exists";
        } else if(isValidEmailId) {
            status = "Email ID Already Exists";
        } else if(isValidMobileNumber) {
            status = "Mobile Number Already Exists";
        } else {
            status = "Employee Created with ID:"+employeeServiceImpl.addEmployee(employee);
        }
        model.addAttribute("status",status);
        return "performAddEmployee";
    }      

    /**
     * Get the employee details from the employee service
     */
    @GetMapping("/readEmployee")
    public String printEmployees(Model model) {
        List<Employee> employeeList = employeeServiceImpl.getEmployees();
        if(null != employeeList) {
            model.addAttribute("employeeList", employeeList);
            return "performReadAllEmployees";
        } else {
            model.addAttribute("message","No Employees in the database");
        }
        return "employee";
    }

    @RequestMapping(value = "/performReadEmployeeById")
    public String readEmployee() {
        return "performReadEmployeeById";
    }

    /**
     * Get the employee by using employee ID
     * @param employeeId gets the employeeId from the servlet
     */
    @GetMapping("/readEmployeeById")
    public String getEmployeeById(@RequestParam String employeeId, Model model) throws HibernateException, NoResultException {
        try {
            model.addAttribute("employee", employeeServiceImpl.getEmployeeById(employeeId));
        } catch(NoResultException e) {
            model.addAttribute("employee","Employee Not Found");
        }
        return "performReadEmployeeById";
    }

    @RequestMapping(value = "/performUpdateEmployee")
    public String updateEmployee() {
        return "performUpdateEmployee";
    }

    @RequestMapping(value = "/getEmployee")
    public String getEmployeeForUpdate(@RequestParam String employeeId, Model model) {
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
            model.addAttribute("employee",employee);
            return "performEmployeeUpdate";
        } catch(NoResultException e){
            model.addAttribute("message","Employee Not Found");
            return "leaveRecords";
        }
    }

   /**
     * Request the update employee method in employee service
     * to update the employee
     * @param employee Gets the updated employee object from servlet
     * @return boolean value
     */
   @PostMapping(value = "/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") Employee employee, Model model) {
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        employee.setModifiedAt(dateTimeUtils.getDate());
        boolean employeeUpdated = employeeServiceImpl.updateEmployee(employee);
        if(employeeUpdated)
            model.addAttribute("status","Employee Updated");
        else
            model.addAttribute("status","Employee Not Updated");
        return "performEmployeeUpdate";
    }

    @RequestMapping(value = "/performDeleteEmployee")
    public String deleteEmployee() {
        return "performDeleteEmployee";
    }

    /**
     * Gets the employee id from the servlet which the user
     * wants to remove from the database
     * @return int value
     */
    @GetMapping(value="/deleteEmployee")
    public String deleteEmployee(@RequestParam String employeeId, Model model) {
        int deleted = employeeServiceImpl.removeEmployee(employeeId);
        if (deleted == 1) {
            model.addAttribute("status", "Employee deleted successfully");
        } else {
            model.addAttribute("status", "Employee Not deleted");
        }
        return "performDeleteEmployee";
    }

    @RequestMapping(value = "/leaveRecords")
    public String leaveRecords() {
        return "leaveRecords";
    }

    @RequestMapping(value = "/performGetEmployeeForLeave")
    public String getEmployeeForLeave() {
        return "performGetEmployeeForLeave";
    }

    @RequestMapping(value = "/getEmployeeForLeave")
    public String createLeaveForEmployee(@RequestParam("employeeId") String employeeId, Model model) {
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
            model.addAttribute("leaveCount",getEmployeeLeaveCount(employeeId));
            model.addAttribute("leaveRecord",new LeaveRecord());
            model.addAttribute("employee", employee);
            return "performAddLeaveRecord";
        } catch(NoResultException e) {
            model.addAttribute("message", "Employee Not Found");
        }
        return "leaveRecords";
    }

    /**
     * Gets the leave records from the servlet
     * @param leaveRecord get leaveRecord object from leave record servlet
     * @return int
     */
    @PostMapping(value = "/addLeaveRecord")
    public String addLeaveRecord(@ModelAttribute("leaveRecord") LeaveRecord leaveRecord, @RequestParam("employeeId") String employeeId, Model model) {
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        String status;
        int leaveId;
        Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
        leaveRecord.setEmployee(employee);
        leaveRecord.setCreatedAt(dateTimeUtils.getDate());
        leaveRecord.setModifiedAt(dateTimeUtils.getDate());

        boolean isValidDate = dateTimeUtils.validateLeaveDate(leaveRecord.getFromDate(), leaveRecord.getToDate());
        if(isValidDate) {
            leaveId = leaveRecordServiceImpl.addLeaveRecord(leaveRecord);
            if (leaveId != 0) {
                status = "Leave Record Created";
                model.addAttribute("status", status);
            } else {
                status = "Leave Record Not created";
                model.addAttribute("status", status);
            }
        } else {
            status = "Invalid From and To dates";
            model.addAttribute("status", status);
        }
        return "performAddLeaveRecord";
    }

    /**
     * Get the number of leaves taken by the employee
     * It gets leaves of employee by employee id
     */
    public int getEmployeeLeaveCount(String employeeId) {
        final int MAX_LEAVES = 10;
        LocalDate firstDate;
        LocalDate secondDate;
        int leaveCount = 0;
        List<LeaveRecord> leaveRecords = leaveRecordServiceImpl.getLeaveRecordByEmployeeId(employeeId);

        if (!leaveRecords.isEmpty()) {
            DateTimeUtils dateTimeUtils = new DateTimeUtils();
            for (LeaveRecord leaveEntry : leaveRecords) {
                firstDate = dateTimeUtils.getLocalDateFormat(leaveEntry.getFromDate());
                secondDate = dateTimeUtils.getLocalDateFormat(leaveEntry.getToDate());
                int count = dateTimeUtils.findLeaveCount(firstDate, secondDate);
                leaveCount += count;
            }
        }
        return MAX_LEAVES - leaveCount;
    }

    @RequestMapping(value="/performGetLeaveRecord")
    public String readLeaveRecords() {
        return "performGetLeaveRecord";
    }

    /**
     * Get the leave records of the employees
     */
    @GetMapping(value="/readLeaveRecord")
    public String printLeaveRecords(Model model) {
        List<LeaveRecord> leaveRecords = leaveRecordServiceImpl.getLeaveRecords();
        if(null != leaveRecords) {
            model.addAttribute("leaveRecords", leaveRecords);
            return "performReadLeaveRecords";
        } else {
            model.addAttribute("message","No Leave Records in the database");
        }
        return "leaveRecords";
    }

    @RequestMapping(value="/performLeaveRecordEmployeeById")
    public String readLeaveRecordsByEmployeeId() {
        return "performLeaveRecordEmployeeById";
    }

    /**
     * Get leave Record by employee-Id
     * @param employeeId Gets the employeeId as an input
     * @return List of leave records
     */
    @GetMapping(value = "/readLeaveRecordByEmployeeId")
    public String getLeaveRecordByEmployeeId(@RequestParam("employeeId") String employeeId, Model model) {
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
            if (null != employee) {
                List<LeaveRecord> leaveRecords = leaveRecordServiceImpl.getLeaveRecordByEmployeeId(employeeId);
                if(!leaveRecords.isEmpty()) {
                    model.addAttribute("leaveRecords", leaveRecords);
                    return "performReadLeaveRecords";
                } else {
                    model.addAttribute("message","No Leaves taken by the employee");
                    return "leaveRecords";
                }
            } else {
                model.addAttribute("message","No leave records in the database");}
        } catch(NoResultException e) {
            model.addAttribute("message","Employee Not Found");
        }
        return "leaveRecords";
    }

    @RequestMapping(value = "/performEditLeaveRecord")
    public String editLeaveRecord() {
        return "performEditLeaveRecord";
    }

    @GetMapping(value = "/getEmployeeForUpdate")
    public String getEmployeeForLeaveUpdate(@RequestParam("employeeId") String employeeId, Model model) {
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
            if (null != employee) {
                List<LeaveRecord> leaveRecords = leaveRecordServiceImpl.getLeaveRecordByEmployeeId(employeeId);
                if (!leaveRecords.isEmpty()) {
                    model.addAttribute("employee", employee);
                    model.addAttribute("leaveRecords", leaveRecords);
                    return "performGetLeaveId";
                } else {
                    model.addAttribute("message", "No Leaves taken by the employee");
                }
            }
        } catch(NoResultException e) {
            model.addAttribute("message", "Employee Not Found");
        }
        return "leaveRecords";
    }

    /**
     * Gets the leave record by using leave ID for update leave Record
     * @param leaveId Gets the leaveID from the servlet
     * @param employeeId Gets employeeID from the servlet
     * @return leave record
     */
    @GetMapping(value = "/getLeaveId")
    public String getLeaveRecord(@RequestParam("leaveId") int leaveId, @RequestParam("employeeId") String employeeId, Model model) {
        List<LeaveRecord> leaveRecords = leaveRecordServiceImpl.getLeaveRecordByEmployeeId(employeeId);
        for (LeaveRecord leaveEntry: leaveRecords) {
            if (leaveEntry.getLeaveId() == leaveId) {
                model.addAttribute("leaveRecord",leaveEntry);
            }
        }
        return "performUpdateLeaveRecord";
    }

    /**
     * Request the update employee leave record method in employee leave record service 
     * to update the leave record
     *
     * @param leaveRecord gets updated leave record object from the servlet
     * @return boolean
     */
    @PostMapping(value = "/updateLeaveRecord")
    public String updateLeaveRecord(@ModelAttribute("leaveRecord") LeaveRecord leaveRecord, Model model) {
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        leaveRecord.setModifiedAt(dateTimeUtils.getDate());
        if (leaveRecordServiceImpl.updateLeaveRecord(leaveRecord))
            model.addAttribute("message","Leave Record Updated");
        else
            model.addAttribute("message","Leave Record Not Updated");
        return "leaveRecords";
    }

    @RequestMapping(value="/performDeleteLeaveRecord")
    public String removeLeaveRecord(){
        return "performDeleteLeaveRecord";
    }
    /**
     * Gets the employee id from the servlet
     * to remove the leave record the database
     * @param employeeId gets employeeId from the servlet
     * @return int
     */
    @GetMapping(value = "/deleteLeaveRecord")
    public String deleteLeaveRecord(@RequestParam String employeeId, Model model) {
        String message;
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
            int updatedRow = leaveRecordServiceImpl.removeLeaveRecord(employeeId);
            if(updatedRow != 0) {
                message = "Leave Record deleted Successfully";
                model.addAttribute("message", message);
            } else {
                message = "Leave Record Not deleted";
                model.addAttribute("message", message);
            }
        } catch(NoResultException e) {
            model.addAttribute("message","Employee Not Found");
        }
        return "leaveRecords";
    }
/*------------------------Employee Project*----------------------------*/

    @RequestMapping(value = "/projects")
    public String projects() {
        return "projects";
    }

    @RequestMapping(value = "/performGetEmployeeForProject")
    public String navigateToEmployeeForProject() {
        return "performGetEmployeeForProject";
    }

    @GetMapping(value = "/getEmployeeForProject")
    public String getEmployeeForProject(@RequestParam("employeeId") String employeeId, Model model) {
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
            model.addAttribute("employee",employee);
            return "performGetProjectManager";
        } catch(NoResultException e) {
            model.addAttribute("message", "Employee Not Found");
        }
        return "projects";
    }

    @RequestMapping(value = "/getProjectManager")
    public String getProjectManagerForProject(@ModelAttribute("employee") Employee employee, @RequestParam("projectManagerId") String projectManagerId, Model model) {
        try {
            System.out.println(employee);
            Employee projectManager = employeeServiceImpl.getEmployeeById(projectManagerId);
            model.addAttribute("projectManager",projectManager);
            model.addAttribute("employee",employee);
            model.addAttribute("project",new EmployeeProject());
            return "performAddProject";
        } catch(NoResultException e) {
            model.addAttribute("message", "Employee Not Found");
        }
        return "projects";
    }

    /**
     * Gets the employee project from the employee project servlet
     * @param project getting project object from the servlet
     * @return int
     */
    @PostMapping(value = "/addProject")
    public String addEmployeeProject(@ModelAttribute("project") EmployeeProject project, @RequestParam("employeeId") String employeeId, Model model) {
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        List<Employee> employees = new ArrayList<>();
        employees.add(employeeServiceImpl.getEmployeeById(employeeId));
        project.setCreatedAt(dateTimeUtils.getDate());
        project.setModifiedAt(dateTimeUtils.getDate());
        project.setEmployees(employees);
        int projectId =  employeeProjectServiceImpl.addEmployeeProject(project);

        if(projectId != 0)
            model.addAttribute("message","Project created Successfully");
        else
            model.addAttribute("message","Project not created");
        return "projects";
    }

    @RequestMapping(value = "/performGetProject")
    public String getProjects() {
        return "performGetProject";
    }


    /**
     * Get List of employee projects of the employees
     * @return List of Employee Project
     */
    @RequestMapping(value = "/getProjects")
    public String printEmployeeProjects(Model model) {
        List<EmployeeProject> projects = employeeProjectServiceImpl.getEmployeeProjects();
        if(null != projects) {
            model.addAttribute("projects", projects);
            return "performGetProjects";
        } else {
            model.addAttribute("message","No Projects in the database");
        }
        return "projects";
    }

    @RequestMapping(value = "/performGetProjectByEmployeeId")
    public String getProjectByEmployeeID() {
        return "performGetProjectByEmployeeId";
    }

    /**
     * Get Employee Project by using employee_Id
     * @param employeeId Get employeeId from the servlet
     * @return List of Employee project
     */
    @GetMapping(value="/getProjectsByEmployeeId")
    public String getEmployeeProjectByEmployeeId(@RequestParam("employeeId") String employeeId, Model model) {
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
            List<EmployeeProject> projects = employeeProjectServiceImpl.getEmployeeProjectByEmployeeId(employeeId);
            if (!projects.isEmpty()) {
                model.addAttribute("projects", projects);
                return "performGetProjects";
            } else {
                model.addAttribute("message", "Employee is not assigned to project");
            }
        } catch(NoResultException e) {
            model.addAttribute("message","Employee Not found");
        }
        return "projects";
    }

    @RequestMapping(value = "/performGetProjectId")
    public String getEmployeeForProjectUpdate() {
        return "performGetProjectId";
    }

    /**
     * Get the employee project using project ID
     * @param projectId Get the project ID from the servlet
     * @return Employee project
     */
    @GetMapping("/getProjectId")
    public String getProjectById(@RequestParam("projectId") int projectId, Model model) {
        List<EmployeeProject> projects = employeeProjectServiceImpl.getEmployeeProjects();

        for(EmployeeProject projectById : projects) {
            if(projectId == projectById.getProjectId()) {
                model.addAttribute("project",projectById);
                return "performUpdateProject";
            }
        }
        model.addAttribute("message","Invalid Project Id");
        return "projects";
    }

    /**
     * Request the update employee project method in employee project service 
     * to update the project details
     *
     * @param project Get updated employee project object from the servlet
     * @return boolean
     */
    @PostMapping(value = "/updateProject")
    public String updateEmployeeProject(@ModelAttribute EmployeeProject project,Model model) {
        DateTimeUtils dateTimeUtils = new DateTimeUtils();
        project.setModifiedAt(dateTimeUtils.getDate());
        if(employeeProjectServiceImpl.updateEmployeeProject(project))
            model.addAttribute("message","Project Updated Successfully");
        else
            model.addAttribute("message","Project not updated");
        return "projects";
    }

    @RequestMapping(value="/performGetEmployeeForProjectAssign")
    public String navigateToGetEmployee() {
        return "performGetEmployeeForProjectAssign";
    }
    @GetMapping(value = "/getEmployeeForAssignProject")
    public String getEmployeeForAssignToProject(@RequestParam("employeeId") String employeeId, Model model) {
        try {
            Employee employee = employeeServiceImpl.getEmployeeById(employeeId);
            List<EmployeeProject> projects = employeeProjectServiceImpl.getEmployeeProjects();
            if (null != projects ) {
                model.addAttribute("employee", employee);
                model.addAttribute("projects", projects);
                return "performAssignEmployee";
            } else {
                model.addAttribute("message","No projects found");
            }
        } catch (NoResultException e) {
            model.addAttribute("employee", "Employee Not Found");
        }
        return "projects";
    }

    /**
     * Assign the employee for the project
     * @param employeeId get employee object from the employee servlet
     * @param projectId get project object from the employeeProject servlet
     * @return boolean
     */
    @PostMapping(value="/assignEmployee")
    public String assignProject(@RequestParam("employeeId") String employeeId, @RequestParam("projectId") String projectId, Model model) {
        System.out.println(employeeId);
        List<EmployeeProject> projects = employeeProjectServiceImpl.getEmployeeProjects();
        for(EmployeeProject project : projects) {
            if(Integer.parseInt(projectId) == project.getProjectId()) {
                if(employeeServiceImpl.assignProject(employeeServiceImpl.getEmployeeById(employeeId), project))
                    model.addAttribute("message","Employee successfully assigned to the project");
                else
                    model.addAttribute("message","Employee Not Assigned");
            } else {
                model.addAttribute("message","Project Not Found");
            }
        }
        return "projects";
    }
}