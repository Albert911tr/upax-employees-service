package mx.com.upax.dto.request;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeesFilter{

	private List<Long> employee_id;
	private String start_date;
	private String end_date;
	
}
