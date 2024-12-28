package com.elearn.app.start_learn_back.repositories;

import com.elearn.app.start_learn_back.entities.Category;
import com.elearn.app.start_learn_back.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepo extends JpaRepository<Course,String> {

    Optional<Course> findByTitle(String title);

    List<Course> findByLive(boolean live);

/*    @Query("select c from Course where c.catgeoryList =:catId")
    List<Course> findByCategoryId(@Category catId);*/
}
