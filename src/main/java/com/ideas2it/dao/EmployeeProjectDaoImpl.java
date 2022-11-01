package com.ideas2it.dao;

import com.ideas2it.databaseconnection.DatabaseConnection;
import com.ideas2it.model.EmployeeProject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements the employee Project DAO
 * Contains create and display methods
 * Deals with the employee project data
 *
 * @author Nithish K
 * @version 1.0
 * @since 17.09.2022
 */
@Repository
public class EmployeeProjectDaoImpl implements EmployeeProjectDao {
    private final SessionFactory sessionFactory = DatabaseConnection.getConnection();
  
    @Override
    public int addEmployeeProject(EmployeeProject employeeProject) {
        int projectId = 0;
        Session session = sessionFactory.openSession();

        try {
            Transaction transaction = session.beginTransaction();
            projectId = (Integer) session.save(employeeProject);
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e); 
        } finally {
            session.close();
        }
        return projectId;
    }

    @Override
    public List<EmployeeProject> getEmployeeProjectByEmployeeId(String employeeId){
        Session session = sessionFactory.openSession();
        List<EmployeeProject> employeeProjects = new ArrayList<>();
        try {
            Transaction transaction = session.beginTransaction();
            String projectInfo = "FROM EmployeeProject p WHERE p.projectId = "
                                 +"(SELECT p.id FROM p.employees e WHERE e.employeeId = :employeeId)";
            Query query = session.createQuery(projectInfo);
            query.setParameter("employeeId",employeeId);
            employeeProjects = query.getResultList();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
	return employeeProjects;
    }

    @Override
    public List<EmployeeProject> getEmployeeProjects() {
        Session session = sessionFactory.openSession();
        List<EmployeeProject> employeeProjects = new ArrayList<>();

        try {
            Transaction transaction = session.beginTransaction();
            employeeProjects = session.createQuery("FROM EmployeeProject").list();
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return employeeProjects;
    }


    @Override
    public boolean updateEmployeeProject(EmployeeProject employeeProject) {
        Session session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();
            session.update(employeeProject); 
            transaction.commit();
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return true;
    }
}