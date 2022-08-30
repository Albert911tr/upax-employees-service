package mx.com.upax.dao.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "EMPLOYEES")
public class EmployeeDetail{

	@Id
	@Column(name = "ID")
	private Long id;
	
	@Column(name = "NAME")
	private String name;
	
	@Column(name = "LAST_NAME")
	private String last_name;
	
	@Column(name = "BIRTHDATE")
	private Date birthdate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "JOB_ID")
	private Job job;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "GENDER_ID")
	private Gender gender;
	
}
