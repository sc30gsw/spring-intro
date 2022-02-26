package com.example.demo.app.controller;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.domain.form.FileUploadForm;

@Controller
@RequestMapping("/file/upload")
public class FileUploadController {
	
	@GetMapping
	public String getUpload(@ModelAttribute("form") FileUploadForm form) {
		
		return "file/upload";
	}
	
	@PostMapping
	public String upload(FileUploadForm form, Model model) throws IOException {
		
		MultipartFile file = form.getFile();
		
		String contentType = file.getContentType();
		String parameterName = file.getName();
		String originalFilename = file.getOriginalFilename();
		long fileSize = file.getSize();
		
		try (InputStream content = file.getInputStream()) {
			// アップロードデータの永続化処理(DBに保存)
		}
		
		return "redirect:/file/upload?complete";
	}

}
