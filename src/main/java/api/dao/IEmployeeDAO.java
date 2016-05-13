package api.dao;

import obs.repository.GraphRepository;
import api.model.Employee;
/**
 * 
 * @author karthikeyan_v
 *
 */
public interface IEmployeeDAO extends GraphRepository<Employee>{

	public Employee findByName(String name);
	
}