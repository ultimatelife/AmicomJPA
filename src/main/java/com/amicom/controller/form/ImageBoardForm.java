package com.amicom.controller.form;

import java.util.List;

import javax.annotation.Nullable;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ImageBoardForm {
	@NotNull
	String boardName;
	@NotNull
	String title;
	@NotNull
	String content;
	@Nullable
	List<MultipartFile> multipartFiles;
}
