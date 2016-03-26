package com.amicom.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.amicom.dao.abs.AbstractFileMeta;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class NormalFileMeta extends AbstractFileMeta implements Serializable {
	@ManyToOne(optional = false)
	@JoinColumn(name = "boardID", nullable = false)
	@JsonBackReference
	public NormalBoard normalBoard;

	public NormalBoard getNormalBoard() {
		return normalBoard;
	}

	public void setNormalBoard(NormalBoard normalBoard) {
		this.normalBoard = normalBoard;
	}
	
}
