package com.amicom.dao;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

import com.amicom.dao.abs.AbstractBoard;
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
	public Set<ImageReply> replys;

	@Transient
	List<MultipartFile> multipartFiles;
	
	public ImageBoard(BoardChart boardName, String title, String content, Timestamp timeStamp,
			AmicomMember amicomMember) {
		super(boardName, title, content, timeStamp, amicomMember);
	}

	public ImageFileMeta getFileMetas() {
		return fileMetas;
	}

	public void setFileMetas(ImageFileMeta fileMetas) {
		this.fileMetas = fileMetas;
	}

	public Set<ImageReply> getReplys() {
		return replys;
	}

	public void setReplys(Set<ImageReply> replys) {
		this.replys = replys;
	}

	public List<MultipartFile> getMultipartFiles() {
		return multipartFiles;
	}

	public void setMultipartFiles(List<MultipartFile> multipartFiles) {
		this.multipartFiles = multipartFiles;
	}
	
}
