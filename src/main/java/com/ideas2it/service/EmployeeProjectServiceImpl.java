package com.ideas2it.service;

import com.ideas2it.dao.EmployeeProjectDao;
import com.ideas2it.dao.EmployeeProjectDaoImpl;
import com.ideas2it.model.EmployeeProject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeProjectServiceImpl implements EmployeeProjectService {

    private final EmployeeProjectDao employeeProjectDaoImpl;

    public EmployeeProjectServiceImpl (EmployeeProjectDao employeeProjectDaoImpl) {
        this.employeeProjectDaoImpl = employeeProjectDaoImpl;
    }

    @Override 
    public int addEmployeeProject(EmployeeProject employeeProject) {
        return employeeProjectDaoImpl.addEmployeeProject(employeeProject);
    }

    @Override
    public List<EmployeeProject> getEmployeeProjectByEmployeeId(String employeeId) {
	return employeeProjectDaoImpl.getEmployeeProjectByEmployeeId(employeeId);
    } 

    @Override
    public List<EmployeeProject> getEmployeeProjects() {
        return employeeProjectDaoImpl.getEmployeeProjects();
    }

    @Override
    public boolean updateEmployeeProject(EmployeeProject employeeProject) {
        return employeeProjectDaoImpl.updateEmployeeProject(employeeProject);
    }

}