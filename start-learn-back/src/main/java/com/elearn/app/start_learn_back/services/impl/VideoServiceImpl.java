package com.elearn.app.start_learn_back.services.impl;

import com.elearn.app.start_learn_back.dtos.VideoDto;
import com.elearn.app.start_learn_back.entities.Video;
import com.elearn.app.start_learn_back.exceptions.ResourceNotFoundException;
import com.elearn.app.start_learn_back.repositories.VideoRepo;
import com.elearn.app.start_learn_back.services.VideoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class VideoServiceImpl implements VideoService {

    @Autowired
    private VideoRepo videoRepo;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public VideoDto createVideo(VideoDto videoDto) {
        videoDto.setVideoId(UUID.randomUUID().toString());
        Video video = modelMapper.map(videoDto, Video.class);
        Video savedVideo = videoRepo.save(video);
        return modelMapper.map(savedVideo,VideoDto.class);
    }

    @Override
    public VideoDto updateVideo(String videoId, VideoDto videoDto) {
        Video video = videoRepo.findById(videoId).orElseThrow(() -> new ResourceNotFoundException("Video not found.."));
        modelMapper.map(videoDto,video);

        Video updatedVideo = videoRepo.save(video);
        return modelMapper.map(updatedVideo,VideoDto.class);
    }

    @Override
    public VideoDto getVideoById(String videoId) {
        Video video = videoRepo.findById(videoId).orElseThrow(() -> new ResourceNotFoundException("Video not found..."));
        return modelMapper.map(video,VideoDto.class);
    }

    @Override
    public Page<VideoDto> getAllVideos(Pageable pageable) {
        Page<Video> videos = videoRepo.findAll(pageable);
        List<VideoDto> dtos = videos.getContent().stream().map(video -> modelMapper.map(video,VideoDto.class)).collect(Collectors.toList());
        return new PageImpl<>(dtos,pageable,videos.getTotalElements());
    }

    @Override
    public void deleteVideo(String videoId) {
        videoRepo.deleteById(videoId);
    }

    @Override
    public List<VideoDto> searchVideos(String keyword) {
        List<Video> videos = videoRepo.findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(keyword,keyword);
        return videos.stream().map(video -> modelMapper.map(video,VideoDto.class)).collect(Collectors.toList());
    }
}
