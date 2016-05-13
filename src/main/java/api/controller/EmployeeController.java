package api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import obs.controller.MainController;
import obs.response.ResponseMessage;
import obs.response.ResponseMessages;
import obs.response.ResponseStatus;
import obs.response.ResponseStatusCode;
import obs.service.MainService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import api.model.Employee;
import api.service.IEmployeeService;
/**
 * 
 * @author karthikeyan_v
 *
 */
@Controller
@SuppressWarnings({"unchecked","rawtypes"})
public class EmployeeController extends MainController<Employee>{	

	@Autowired
	private IEmployeeService empService;

	ResponseStatus status = null;

	@RequestMapping(value="/insert",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResponseMessage<Employee> save(String name,String email,String password){
		Employee emp=null;
		try{
			emp=new Employee();
			emp.setName(name);
			emp.setEmail(email);
			emp.setPassword(password);	
			this.save(emp);
			status=new ResponseStatus(ResponseStatusCode.STATUS_OK,"Success");
		}catch(Exception e){
			e.printStackTrace();
			status=new ResponseStatus(ResponseStatusCode.STATUS_NOT_ACCEPTABLE,"Data not saved..!");
		}		
		return new ResponseMessage<Employee>(status,emp);
	}

	@RequestMapping(value="/update",method=RequestMethod.POST,produces="application/json")
	public @ResponseBody ResponseMessage<Employee> update(String id,String name,String email,String password){
		List<Employee> list=null;
		Employee emp =null;
		try{
			Map map=new HashMap();
			map.put("id", Long.parseLong(id));
			list=(List<Employee>) this.findByParams(map);
			if(list.size()>0){
				emp = list.get(0);
				if(emp!=null){
					emp.setName(name);
					emp.setEmail(email);
					emp.setPassword(password);	
					this.update(emp);
					status=new ResponseStatus(ResponseStatusCode.STATUS_OK,"Success");
				}	}		
		}catch(Exception e){
			e.printStackTrace();
			status=new ResponseStatus(ResponseStatusCode.STATUS_NOT_ACCEPTABLE,"Update failed..!");
		}		
		return new ResponseMessage<Employee>(status,emp);
	}

	@RequestMapping(value="/find",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody ResponseMessage<Employee> find(String id){		
		List<Employee> list=null;
		Employee emp =null;
		try{
			Map map=new HashMap();
			map.put("id", Long.parseLong(id));
			list=(List<Employee>) this.findByParams(map);
			if(list.size()>0){
				emp = list.get(0);
				status=new ResponseStatus(ResponseStatusCode.STATUS_OK,"Success");
			}
		}catch(Exception e){
			e.printStackTrace();
			status=new ResponseStatus(ResponseStatusCode.STATUS_INVALID,"Invalid Request..!");
		}
		return new ResponseMessage<Employee>(status,emp);
	}		

	@RequestMapping(value="/findbyemail",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody ResponseMessage<Employee> findbymail(String email){		
		List<Employee> list=null;
		Employee emp =null;		
		try{
			Map map=new HashMap();
			map.put("email", email);
			list=(List<Employee>) this.findByParams(map);
			if(list.size()>0){
				emp = list.get(0);
				status=new ResponseStatus(ResponseStatusCode.STATUS_OK,"Success");
			}
		}catch(Exception e){
			e.printStackTrace();
			status=new ResponseStatus(ResponseStatusCode.STATUS_NOT_ACCEPTABLE,"Invalid Request..!");
		}
		return new ResponseMessage<Employee>(status,emp);
	}

	@RequestMapping(value="/findbyname",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody ResponseMessage<Employee> findByName(String name){		
		Employee emp=null;
		try{
			emp=empService.findByName(name);
			status=new ResponseStatus(ResponseStatusCode.STATUS_OK,"Success");
		}catch(Exception e){
			e.printStackTrace();
			status=new ResponseStatus(ResponseStatusCode.STATUS_NOT_ACCEPTABLE,"Invalid Request..!");
		}
		return new ResponseMessage<Employee>(status,emp);
	}

	@RequestMapping(value="/findall",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody ResponseMessages<Employee> findall(String id){		
		List<Employee> emp=null;
		try{
			emp=this.findAll();
			status=new ResponseStatus(ResponseStatusCode.STATUS_OK,"Success");
		}catch(Exception e){
			e.printStackTrace();
			status=new ResponseStatus(ResponseStatusCode.STATUS_NOT_ACCEPTABLE,"Invalid Request..!");
		}
		return new ResponseMessages<Employee>(status,emp);
	}

	@RequestMapping(value="/delete",method=RequestMethod.GET,produces="application/json")
	public @ResponseBody ResponseMessage<Employee> delete(String id){		
		List<Employee> list=null;
		Employee emp =null;
		try{
			Map map=new HashMap();
			map.put("id", Long.parseLong(id));
			list=(List<Employee>) this.findByParams(map);
			if(list.size()>0){
				emp = list.get(0);	
				if(emp!=null){
					this.delete(emp);
					status=new ResponseStatus(ResponseStatusCode.STATUS_OK,"Success");
				}
			}
		}catch(Exception e){
			e.printStackTrace();
			status=new ResponseStatus(ResponseStatusCode.STATUS_NOT_ACCEPTABLE,"Invalid Request..!");
		}
		return new ResponseMessage<Employee>(status,emp);
	}

	@Override
	public MainService<Employee> getService() {
		return empService;
	}
}