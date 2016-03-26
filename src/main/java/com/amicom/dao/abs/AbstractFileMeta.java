package com.amicom.dao.abs;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractFileMeta implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	private int fileMetaID;
	
	@NotNull
	private String fileName;
	
	@NotNull
	private String fileSize;
	
	@NotNull
	private String fileType;
	
	private Timestamp timeStamp;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name="boardID", nullable=false)
//	private Board board;
}
