package com.amicom.controller.form;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageBoardOutputForm {
	int boardId;
	String username;
	String imageFilePath;
	String title;
	String content;
}
