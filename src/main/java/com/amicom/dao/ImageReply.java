package com.amicom.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.amicom.dao.abs.AbstractReply;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageReply extends AbstractReply implements Serializable{
	 @ManyToOne(fetch = FetchType.LAZY)
	 @JoinColumn(name="boardID", nullable=false)
	 private ImageBoard board;
}
