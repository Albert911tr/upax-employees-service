package mx.com.upax.dto.response;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import mx.com.upax.dao.model.EmployeeDetail;

@Getter
@Setter
public class ResponseV2 extends GeneralResponse {

	private List<EmployeeDetail> employees;
	
}
