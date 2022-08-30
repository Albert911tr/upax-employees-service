package mx.com.upax.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mx.com.upax.dao.model.Employee;

@Getter
@Setter
public class ResponseV3 extends GeneralResponse {

	private List<Employee> employees;
	
}
