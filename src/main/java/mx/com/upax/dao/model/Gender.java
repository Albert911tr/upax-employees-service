package mx.com.upax.dao.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "GENDERS")
public class Gender {

	@Id
	@Column(name = "ID")
	private Integer id;
	
	@Column(name = "NAME")
	private String name;
}
