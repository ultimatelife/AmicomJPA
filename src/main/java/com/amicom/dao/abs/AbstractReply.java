package com.amicom.dao.abs;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import com.amicom.dao.AmicomMember;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractReply implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	int replyId;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="username", nullable=false)
	AmicomMember amicomMember;

	Timestamp timeStamp;

	@NotNull
	String content;

	public int getReplyId() {
		return replyId;
	}

	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}

	public AmicomMember getAmicomMember() {
		return amicomMember;
	}

	public void setAmicomMember(AmicomMember amicomMember) {
		this.amicomMember = amicomMember;
	}

	public Timestamp getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Timestamp timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	// @ManyToOne(fetch = FetchType.EAGER)
	// @JoinColumn(name="boardID", nullable=false)
	// private Board board;
	
	
}
