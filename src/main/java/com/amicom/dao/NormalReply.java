package com.amicom.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.amicom.dao.abs.AbstractReply;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NormalReply extends AbstractReply implements Serializable{
	 @ManyToOne(optional=false)
	 @JoinColumn(name="boardID", nullable=false)
	 @JsonBackReference
	 private NormalBoard board;

	public NormalBoard getBoard() {
		return board;
	}

	public void setBoard(NormalBoard board) {
		this.board = board;
	}
	 
}
