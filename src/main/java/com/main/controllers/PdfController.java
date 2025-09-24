package com.main.controllers;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.main.entity.PdfFile;
import com.main.repository.PdfFileRepository;
import com.main.services.PdfFileService;

@RestController
@RequestMapping("/api/pdf")
@CrossOrigin(origins = "http://127.0.0.1.5500")
public class PdfController {
	
	@Autowired
	private PdfFileService service;
	
	@Autowired
	private PdfFileRepository repo;
	
	@PostMapping("/upload")
	public ResponseEntity<PdfFile> uploadPdf(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty() || !file.getContentType().equals("application/pdf")) {
            // Returns a 400 Bad Request status with no body.
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
        }

        try {
            PdfFile uploadedFile = service.uploadPdfFile(file);
            // Returns a 200 OK status with the JSON object as the body.
            return new ResponseEntity<>(uploadedFile, HttpStatus.OK);
        } catch (IOException e) {
            // Returns a 500 Internal Server Error status with no body.
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
	
	@GetMapping("/view/{id}")
	public ResponseEntity<byte[]> viewPdf(@PathVariable Long id) {
		Optional<PdfFile> pdfFileOptional = repo.findById(id);
		
		if (pdfFileOptional.isPresent()) {
			PdfFile pdfFile = pdfFileOptional.get();
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + pdfFile.getFilename() + "\"")
					.contentType(MediaType.parseMediaType(pdfFile.getFiletype()))
					.body(pdfFile.getFileContent());
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
	}

}
