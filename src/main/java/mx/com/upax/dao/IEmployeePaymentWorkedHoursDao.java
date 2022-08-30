package mx.com.upax.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IEmployeePaymentWorkedHoursDao extends JpaRepository<Object, Double>{

	/**
	 * 
	 * @param employeeId
	 * @param iniDate
	 * @param endDate
	 * @return Double
	 */
	@Query(nativeQuery = true, value = "SELECT getEmployeePaymentWorkedHours(:employeeId,:iniDate, :endDate) FROM dual")
	public Double getEmployeePaymentWorkedHours(@Param("employeeId") Long employeeId,
			@Param("iniDate") Date iniDate, @Param("endDate") Date endDate);
}
