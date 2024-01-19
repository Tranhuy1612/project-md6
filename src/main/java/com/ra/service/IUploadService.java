package com.ra.service;

import com.ra.model.entity.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IUploadService {
	
	List<Image> findAll();
	
	List<Image> addNewImage(List<MultipartFile> images);
	
	Image addSingleImage(MultipartFile image);
	
}
