package mx.com.upax.dao;

import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface IEmployeeWorkedHoursDao extends JpaRepository<Object, Long>{

	/**
	 * 
	 * @param employeeId
	 * @param iniDate
	 * @param endDate
	 * @return Long
	 */
	@Query(nativeQuery = true, value = "SELECT getEmployeeWorkedHours(:employeeId,:iniDate, :endDate) FROM dual")
	public Long getEmployeeWorkedHours(@Param("employeeId") Long employeeId,
			@Param("iniDate") Date iniDate, @Param("endDate") Date endDate);
}
