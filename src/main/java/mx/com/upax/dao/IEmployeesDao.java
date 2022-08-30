package mx.com.upax.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import mx.com.upax.dao.model.Employee;

@Repository
public interface IEmployeesDao extends JpaRepository<Employee, Long>{

	/**
	 * 
	 * @param name
	 * @param last_name
	 * @param gender_id
	 * @param job_id
	 * @param birthdate
	 * @return Long
	 */
	@Procedure("insertEmployee")
	public Long insertEmployee(String name, String last_name, 
			Integer gender_id, Long job_id, Date birthdate);
	
	/**
	 * 
	 * @param ids
	 * @param iniDate
	 * @param endDate
	 * @return List<Employee>
	 */
	@Query("SELECT e FROM Employee e WHERE e.id in(:ids) AND e.birthdate BETWEEN :iniDate AND :endDate")
	public List<Employee> getEmployeesByIds(List<Long> ids, Date iniDate, Date endDate);
}
