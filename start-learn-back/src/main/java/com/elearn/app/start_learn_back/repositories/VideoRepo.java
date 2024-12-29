package com.elearn.app.start_learn_back.repositories;

import com.elearn.app.start_learn_back.entities.Course;
import com.elearn.app.start_learn_back.entities.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideoRepo extends JpaRepository<Video,String> {

    Optional<Video> findByTitle(String title);

    List<Video> findByCourse(Course course);

    List<Video> findByTitleContainingIgnoreCaseOrDescContainingIgnoreCase(String title,String desc);
}
