package com.amicom.dao;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import com.amicom.dao.abs.AbstractBoard;
import com.amicom.dao.en.BoardType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardChart implements Serializable{
	@Id
	String boardName;
	
	@Enumerated(EnumType.STRING)
	BoardType type;
	
//	@OneToMany(mappedBy = "boardName", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//	Set<AbstractBoard> board;
	
	public BoardChart (String boardName){
		this.boardName = boardName;
	}
}
