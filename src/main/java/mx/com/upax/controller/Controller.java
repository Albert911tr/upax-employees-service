package mx.com.upax.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import mx.com.upax.dto.request.EmployeeFilter;
import mx.com.upax.dto.request.EmployeeNew;
import mx.com.upax.dto.request.EmployeesFilter;
import mx.com.upax.dto.request.JobFilter;
import mx.com.upax.dto.response.GeneralResponse;
import mx.com.upax.service.EmployeeService;


@RestController
@RequestMapping("/contrato")
public class Controller{

	@Autowired
	private EmployeeService employeeService;
	
	/**
	 * 
	 * @param request
	 * @return {@link GeneralResponse}
	 */
	@RequestMapping(value = "/c1", method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse contractV1(@RequestBody EmployeeNew request) { 
		return employeeService.contractV1(request);
	}
	
	
	/**
	 * 
	 * @param request
	 * @return {@link GeneralResponse}
	 */
	@RequestMapping(value = "/c2", method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse contractV2(@RequestBody JobFilter request) {
		return employeeService.contractV2(request);
	}
	
	
	/**
	 * 
	 * @param request
	 * @return {@link GeneralResponse}
	 */
	@RequestMapping(value = "/c3", method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse contractV3(@RequestBody EmployeesFilter request) {
		return employeeService.contractV3(request);
	}
	
	
	/**
	 * 
	 * @param request
	 * @return {@link GeneralResponse}
	 */
	@RequestMapping(value = "/c4", method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse contractV4(@RequestBody EmployeeFilter request) {
		return employeeService.contractV4(request);
	}
	
	/**
	 * 
	 * @param request
	 * @return {@link GeneralResponse}
	 */
	@RequestMapping(value = "/c5", method = RequestMethod.POST)
	@ResponseBody
	public GeneralResponse contractV5(@RequestBody EmployeeFilter request) {
		return employeeService.contractV5(request);
	}
}
