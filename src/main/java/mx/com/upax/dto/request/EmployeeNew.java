package mx.com.upax.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeNew {

	private String name;
	private String last_name;
	private Integer gender_id;
	private Long job_id;
	private String birthdate;
	
}
