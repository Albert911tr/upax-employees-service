package mx.com.upax.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
public class Employee {

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LAST_NAME")
	private String last_name;
	
	@Column(name = "GENDER_ID")
	private Integer gender_id;
	
	@Column(name = "JOB_ID")
	private Long job_id;
	
	@Column(name = "BIRTHDATE")
	private Date birthdate;
	
}
