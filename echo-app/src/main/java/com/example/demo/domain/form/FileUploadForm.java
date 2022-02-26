package com.example.demo.domain.form;

import java.io.Serializable;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class FileUploadForm implements Serializable {
	
	private MultipartFile file;
	
}
