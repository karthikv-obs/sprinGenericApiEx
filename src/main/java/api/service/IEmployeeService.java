package api.service;

import obs.service.MainService;
import api.model.Employee;
/**
 * 
 * @author karthikeyan_v
 *
 */
public interface IEmployeeService extends MainService<Employee> {

	public Employee findByName(String name);
}