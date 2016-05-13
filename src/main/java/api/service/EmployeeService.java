package api.service;

import obs.repository.GraphRepository;
import obs.service.GenericService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import api.dao.IEmployeeDAO;
import api.model.Employee;
/**
 * 
 * @author karthikeyan_v
 *
 */
@Service("IEmployeeService")
public class EmployeeService extends GenericService<Employee> implements IEmployeeService{

	@Autowired
	public IEmployeeDAO userDao;

	@Override
	public Employee findByName(String name) {
		return userDao.findByName(name);
	}

	@Override
	public GraphRepository<Employee> getRepository() {
		return userDao;
	}
}