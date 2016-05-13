package api.dao;

import java.util.List;

import javax.transaction.Transactional;

import obs.repository.GenericRepositoryImpl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import api.model.Employee;
/**
 * 
 * @author karthikeyan_v
 *
 */
@Repository
@Transactional
public class EmployeeDAO extends GenericRepositoryImpl<Employee> implements IEmployeeDAO{
	
	public EmployeeDAO() {
		super(Employee.class);
	}
	
	@Autowired
	SessionFactory sessionFactory;

	public final Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Employee findByName(String name) {
		List<Employee> list = null;
		try{
			list = getCurrentSession().createQuery("from Employee where name=?").setParameter(0,name).list();
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return (list!=null && list.size()>0)?(Employee)list.get(0):null;
	}
}