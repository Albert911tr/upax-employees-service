package mx.com.upax.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import mx.com.upax.dao.model.EmployeeDetail;

@Repository
public interface IEmployeesDetailDao extends CrudRepository<EmployeeDetail, Long> {

	@Query("SELECT e FROM EmployeeDetail e WHERE e.job.id = :jobId")
	public List<EmployeeDetail> getEmployeeDetailByJobId(Long jobId);
}
