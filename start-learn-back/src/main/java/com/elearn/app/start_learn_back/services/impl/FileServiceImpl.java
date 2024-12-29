package com.elearn.app.start_learn_back.services.impl;

import com.elearn.app.start_learn_back.services.FileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;

@Service
public class FileServiceImpl implements FileService {

    @Override
    public String save(MultipartFile file, String outputPath,String fileName) throws IOException {

        //logic
        Path path = Paths.get(outputPath); // output directory

        //create output folder if not exists.
        Files.createDirectories(path);

        // you can write logic here for video/image/audio processing code like tools ffmpeg.

        // path ko join
        Path filePath = Paths.get(path.toString(),file.getOriginalFilename());

        System.out.println("Actual file Path :----------------- " + filePath);
        //file writes
        Files.copy(file.getInputStream(),filePath, StandardCopyOption.REPLACE_EXISTING);

        return filePath.toString();
    }
}
