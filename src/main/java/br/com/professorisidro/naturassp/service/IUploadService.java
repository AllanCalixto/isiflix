package br.com.professorisidro.naturassp.service;

import org.springframework.web.multipart.MultipartFile;

public interface IUploadService {

    public String uploadFile(MultipartFile arquivo);
}
