package mx.com.upax.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeFilter {

	private Long employee_id;
	private String start_date;
	private String end_date;
	
}
