package com.zenjob.bookrecommendation.persistence.feedback;

import com.zenjob.bookrecommendation.persistence.feedback.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 7:02 PM
 */
@Repository
public interface FeedBackRepository extends JpaRepository<Feedback, Long> {

    List<Feedback> findByBookId(final Long bookId);
}
