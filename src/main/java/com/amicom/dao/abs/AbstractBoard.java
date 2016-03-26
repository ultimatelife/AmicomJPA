package com.amicom.dao.abs;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotEmpty;

import com.amicom.controller.filter.MyDateSerializer;
import com.amicom.dao.AmicomMember;
import com.amicom.dao.BoardChart;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public abstract class AbstractBoard implements Serializable {
	public AbstractBoard(BoardChart boardName, String title, String content, Timestamp timeStamp,
			AmicomMember amicomMember) {
		this.boardName = boardName;
		this.title = title;
		this.content = content;
		this.timeStamp = timeStamp;
		this.amicomMember = amicomMember;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	int boardId;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "boardName")
	BoardChart boardName;

	@NotEmpty
	String title;

	@NotEmpty
	String content;
	
	@JsonSerialize(using=MyDateSerializer.class) 
	Timestamp timeStamp;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "amicomMemberID", nullable = false)
	AmicomMember amicomMember;

	// @OneToMany(fetch = FetchType.EAGER, mappedBy = "board", cascade =
	// CascadeType.ALL)
	// public Set<FileMeta> listFileMeta;
	//
	// @OneToMany(fetch = FetchType.EAGER, mappedBy = "board", cascade =
	// CascadeType.ALL)
	// public Set<Reply> listReply;
}
