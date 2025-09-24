package com.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.main.entity.PdfFile;

@Repository
public interface PdfFileRepository extends JpaRepository<PdfFile, Long>{

}
