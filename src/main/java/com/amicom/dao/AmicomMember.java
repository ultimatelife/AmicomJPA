package com.amicom.dao;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class AmicomMember implements Serializable {
	public AmicomMember(String username) {
		this.username = username;
	}

	@Id
	@NotNull
	@NotEmpty
	@Email
	String username;

	@NotNull
	@Size(min = 4, max = 20)
	@NotEmpty
	@JsonIgnore
	String password;

	@NotNull
	@NotEmpty
	String name;

	@NotNull
	@NotEmpty
	String cellphone;

	@NotNull
	@NotEmpty
	String studentNumber;

	@NotNull
	@NotEmpty
	String major;

	@Temporal(TemporalType.DATE)
	@DateTimeFormat(iso = ISO.DATE)
	java.util.Date birthday;

	@NotEmpty
	String obyb;

	Timestamp timeStamp;

	String authority;

	boolean enabled = false;
	
	String uuid;
	
	String senior;
	

//	@Transient
//	@OneToMany(fetch = FetchType.EAGER, mappedBy = "boardID", cascade = CascadeType.ALL)
//	Set<AbstractBoard> abstractBoard;
}
