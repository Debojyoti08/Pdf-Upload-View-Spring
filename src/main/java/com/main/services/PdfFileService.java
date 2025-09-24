package com.main.services;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.PdfFile;
import com.main.repository.PdfFileRepository;

@Service
public class PdfFileService {
	
	@Autowired
	private PdfFileRepository repo;
	
	public PdfFile uploadPdfFile(MultipartFile file) throws IOException {
		PdfFile pdfFile = new PdfFile();
		pdfFile.setFilename(file.getOriginalFilename());
		pdfFile.setFiletype(file.getContentType());
		pdfFile.setFileContent(file.getBytes());
		return repo.save(pdfFile);
	}

}
