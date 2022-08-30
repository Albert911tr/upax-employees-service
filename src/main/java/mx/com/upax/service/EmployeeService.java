package mx.com.upax.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import mx.com.upax.dao.IEmployeePaymentWorkedHoursDao;
import mx.com.upax.dao.IEmployeeWorkedHoursDao;
import mx.com.upax.dao.IEmployeesDao;
import mx.com.upax.dao.IEmployeesDetailDao;
import mx.com.upax.dao.model.Employee;
import mx.com.upax.dao.model.EmployeeDetail;
import mx.com.upax.dto.request.EmployeeFilter;
import mx.com.upax.dto.request.EmployeeNew;
import mx.com.upax.dto.request.EmployeesFilter;
import mx.com.upax.dto.request.JobFilter;
import mx.com.upax.dto.response.GeneralResponse;
import mx.com.upax.dto.response.ResponseV1;
import mx.com.upax.dto.response.ResponseV2;
import mx.com.upax.dto.response.ResponseV3;
import mx.com.upax.dto.response.ResponseV4;
import mx.com.upax.dto.response.ResponseV5;

@Service 
public class EmployeeService {

	private static final int VALID_AGE = 18;
	private static final String PATTERN_DATE = "yyyy-MM-dd";
	private static final SimpleDateFormat DATE_FMT = new SimpleDateFormat(PATTERN_DATE); 
	
	@Autowired
	private IEmployeesDao employeesDao;
	
	@Autowired
	private IEmployeesDetailDao employeesDetailDao;
	
	@Autowired
	private IEmployeeWorkedHoursDao employeeWorkedHoursDao;
	
	@Autowired
	private IEmployeePaymentWorkedHoursDao employeePaymentWorkedHoursDao;
	
	/**
	 * 
	 * @param request
	 * @return {@link ResponseV1}
	 */
	@Transactional
	public GeneralResponse contractV1(EmployeeNew request) {
		ResponseV1 response = new ResponseV1();
		response.setId(null);
		response.setSucess(false);
		
		if (request.getName() != null && !request.getName().isEmpty() &&
				request.getLast_name() != null && !request.getLast_name().isEmpty() &&
				request.getBirthdate() != null && !request.getBirthdate().isEmpty() &&
				request.getGender_id() != null && request.getJob_id() != null) {
			
			try {
				Date birthDate = DATE_FMT.parse(request.getBirthdate());
				if (validateAge(birthDate)) {
					Long idEmployee = employeesDao.insertEmployee(request.getName(), request.getLast_name(), 
							request.getGender_id(), request.getJob_id(), birthDate);
					if (idEmployee != null) {
						response.setId(idEmployee);
						response.setSucess(true);
					}
				}
			} catch (Exception e) {
				response.setSucess(false);
			}			
		}
		return response;
	}
	
	/**
	 * 
	 * @param request
	 * @return {@link ResponseV2}
	 */
	@Transactional(readOnly = true)
	public GeneralResponse contractV2(JobFilter request) {
		ResponseV2 response = new ResponseV2();
		response.setEmployees(null);
		response.setSucess(false);
		
		if (request.getJob_id() != null) {			
			List<EmployeeDetail> lstEmployeeDetails = employeesDetailDao.getEmployeeDetailByJobId(request.getJob_id());	
			
			if (lstEmployeeDetails != null) {
				response.setEmployees(
					lstEmployeeDetails.stream()
				    .filter(employee -> request.getJob_id().equals(employee.getJob().getId()))
				    .sorted(Comparator.comparing(EmployeeDetail::getLast_name))
				    .collect(Collectors.toList())
				);
				
				response.setSucess(true);
			}
		}
		return response;
	}
	
	/**
	 * 
	 * @param request
	 * @return {@link ResponseV3}
	 */
	@Transactional(readOnly = true)
	public GeneralResponse contractV3(EmployeesFilter request) {
		ResponseV3 response = new ResponseV3();
		response.setEmployees(null);
		response.setSucess(false);
		
		if (request.getEmployee_id() != null && !request.getEmployee_id().isEmpty() 
				&& validateNotEmptyDates(request)) {
			
			try {
				Date dateIni = DATE_FMT.parse(request.getStart_date());
				Date dateEnd = DATE_FMT.parse(request.getEnd_date());
				
				if (dateIni.getTime() < dateEnd.getTime()) {
					List<Employee> employees = employeesDao.getEmployeesByIds(request.getEmployee_id(), dateIni, dateEnd);
					if(employees !=  null) {
						response.setEmployees(employees);
						response.setSucess(true);
					}
				}
			} catch (Exception e) {
				response.setSucess(false);
			}			
		}
		return response;
	}
	
	/**
	 * 
	 * @param request
	 * @return {@link ResponseV4}
	 */
	@Transactional(readOnly = true)
	public GeneralResponse contractV4(EmployeeFilter request) {
		ResponseV4 response = new ResponseV4();
		response.setTotal_worked_hours(null);
		response.setSucess(false);
		
		if (request.getEmployee_id() != null && validateNotEmptyDates(request)) {			
			try {
				Date dateIni = DATE_FMT.parse(request.getStart_date());
				Date dateEnd = DATE_FMT.parse(request.getEnd_date());
				
				if (dateIni.getTime() < dateEnd.getTime()) {
					Long workedHours = employeeWorkedHoursDao.getEmployeeWorkedHours(request.getEmployee_id(), dateIni, dateEnd);
					if(workedHours !=  null) {
						response.setTotal_worked_hours(workedHours);
						response.setSucess(true);
					}
				}
			} catch (Exception e) {
				response.setSucess(false);
			}			
		}
		return response;
	}
	
	/**
	 * 
	 * @param request
	 * @return {@link ResponseV5}
	 */
	@Transactional(readOnly = true)
	public GeneralResponse contractV5(EmployeeFilter request) {	
		ResponseV5 response = new ResponseV5();
		response.setPayment(null);
		response.setSucess(false);
		
		if (request.getEmployee_id() != null && validateNotEmptyDates(request)) {
			
			try {
				Date dateIni = DATE_FMT.parse(request.getStart_date());
				Date dateEnd = DATE_FMT.parse(request.getEnd_date());
				
				if (dateIni.getTime() < dateEnd.getTime()) {
					Double payment = employeePaymentWorkedHoursDao.getEmployeePaymentWorkedHours(request.getEmployee_id(), dateIni, dateEnd);
					if(payment !=  null) {
						response.setPayment(payment);
						response.setSucess(true);
					}
				}
			} catch (Exception e) {
				response.setSucess(false);
			}			
		}
		
		return response;
	}

	/**
	 * 
	 * @param request
	 * @return boolean
	 */
	private boolean validateNotEmptyDates(EmployeeFilter request) {
		return (request.getEnd_date() != null && !request.getEnd_date().isEmpty() 
				&& request.getStart_date() != null && !request.getStart_date().isEmpty()); 
	}
	
	/**
	 * 
	 * @param request
	 * @return boolean
	 */
	private boolean validateNotEmptyDates(EmployeesFilter request) {
		return (request.getEnd_date() != null && !request.getEnd_date().isEmpty() 
				&& request.getStart_date() != null && !request.getStart_date().isEmpty()); 
	}
	
	/**
	 * 
	 * @param birthDate
	 * @return boolean
	 */
	private boolean validateAge(Date birthDate) {
		Calendar birth = Calendar.getInstance();
		birth.setTime(birthDate);
		Calendar now = Calendar.getInstance();
		int age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);		
		return age < VALID_AGE ? false : true;
	}
}
