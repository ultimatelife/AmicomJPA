package com.amicom.dao;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.amicom.dao.abs.AbstractFileMeta;
import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageFileMeta extends AbstractFileMeta implements Serializable {
	@OneToOne(optional = false, cascade=CascadeType.ALL)
	@JoinColumn(name = "boardID", nullable = false)
	@JsonBackReference
	public ImageBoard imageBoard;
}
