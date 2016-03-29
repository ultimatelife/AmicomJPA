package com.amicom.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.amicom.dao.abs.AbstractBoard;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class ImageBoard extends AbstractBoard implements Serializable {
	@JsonManagedReference
	@OneToOne(fetch = FetchType.EAGER, mappedBy = "imageBoard", cascade = CascadeType.ALL)
	public ImageFileMeta fileMetas;

	@JsonManagedReference
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "board", cascade = CascadeType.ALL)
	public List<ImageReply> replys;

	@Transient
	@JsonIgnore
	List<MultipartFile> multipartFiles;
	
	public ImageBoard(BoardChart boardName, String title, String content, Timestamp timeStamp,
			AmicomMember amicomMember) {
		super(boardName, title, content, timeStamp, amicomMember);
	}

	public ImageBoard(int boardId) {
		setBoardId(boardId);
	}
	
	public ImageFileMeta getFileMetas() {
		return fileMetas;
	}

	public void setFileMetas(ImageFileMeta fileMetas) {
		this.fileMetas = fileMetas;
	}

	public List<ImageReply> getReplys() {
		return replys;
	}

	public void setReplys(List<ImageReply> replys) {
		this.replys = replys;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}

	
}
