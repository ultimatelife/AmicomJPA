package com.amicom.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.amicom.dao.abs.AbstractBoard;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@JsonAutoDetect
public class NormalBoard extends AbstractBoard implements Serializable {
	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "normalBoard", cascade = CascadeType.ALL)
	public Set<NormalFileMeta> fileMetas;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "board", cascade = CascadeType.ALL)
	public Set<NormalReply> replys;

	boolean highlight;
	
	public NormalBoard(int boardId) {
		this.setBoardId(boardId);
	}

	@Transient
	List<MultipartFile> multipartFiles;

	public Set<NormalFileMeta> getFileMetas() {
		return fileMetas;
	}

	public void setFileMetas(Set<NormalFileMeta> fileMetas) {
		this.fileMetas = fileMetas;
	}

	public Set<NormalReply> getReplys() {
		return replys;
	}

	public void setReplys(Set<NormalReply> replys) {
		this.replys = replys;
	}

	public boolean isHighlight() {
		return highlight;
	}

	public void setHighlight(boolean highlight) {
		this.highlight = highlight;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}
	
}
