package com.ideas2it.service;

import com.ideas2it.dao.EmployeeDao;
import com.ideas2it.model.Employee;
import com.ideas2it.model.EmployeeProject;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Service;

import jakarta.persistence.NoResultException;
import java.util.List;

/**
 * This class implements Employee service interface 
 * It contains methods for manipulating employee details
 *
 * @author Nithish K
 * @version 1.0
 * @since 14.09.2022
 */

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeDao employeeDaoImpl;

    public EmployeeServiceImpl(EmployeeDao employeeDaoImpl) {
        this.employeeDaoImpl = employeeDaoImpl;
    }


    @Override 
    public String addEmployee(Employee employee) {
        return employeeDaoImpl.addEmployee(employee);
    }


    @Override 
    public String createEmployeeId() {
        return employeeDaoImpl.generateEmployeeId();
    }

    @Override
    public Employee getEmployeeById(String employeeId) throws HibernateException, NoResultException {
	return employeeDaoImpl.getEmployeeById(employeeId);
    }

    @Override
    public boolean updateEmployee(Employee employee) {
        return employeeDaoImpl.updateEmployee(employee);
    } 

    @Override
    public int removeEmployee(String employeeId) {  
        return employeeDaoImpl.removeEmployee(employeeId);              
    }

    @Override
    public List<Employee> getEmployees() {
        return employeeDaoImpl.getEmployees();
    }

    @Override
    public boolean assignProject(Employee employee, EmployeeProject project) {
        return employeeDaoImpl.assignProject(employee, project);
    }

    @Override
    public boolean validateEmailId(String emailId) {
        List<Employee> employees = employeeDaoImpl.getEmployees();
        boolean isValid = false;
        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                if (employee.getEmailId().equals(emailId)) {
                    isValid = true;
                    break;
                }
            }
        }
        return isValid;
    }

    @Override
    public boolean validateMobileNumber(String mobileNumber) {
        List<Employee> employees = employeeDaoImpl.getEmployees();
        boolean isValid = false;
        if (!employees.isEmpty()) {
            for (Employee employee : employees) {
                if (employee.getMobileNumber().equals(mobileNumber)) {
                    isValid = true;
                    break;
                }
            }
        }
        return isValid;
    }
}