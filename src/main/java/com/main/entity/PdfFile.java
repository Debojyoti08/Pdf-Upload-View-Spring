package com.main.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "pdf_files")
@Data
public class PdfFile {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String filename;
	
	private String filetype;
	
	@Lob
	@Column(name = "file_content", columnDefinition = "LONGBLOB")
	private byte[] fileContent;

}
