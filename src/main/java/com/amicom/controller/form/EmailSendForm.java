package com.amicom.controller.form;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmailSendForm {
	List<String> to;
	String subject;
	String content;
}
