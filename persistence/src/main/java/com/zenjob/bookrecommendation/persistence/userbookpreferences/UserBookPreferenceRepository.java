package com.zenjob.bookrecommendation.persistence.userbookpreferences;

import com.zenjob.bookrecommendation.persistence.userbookpreferences.entity.UserBookPreference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * User: Mary Mirzoyan
 * Date: 10/7/18
 * Time: 7:31 PM
 */
@Repository
public interface UserBookPreferenceRepository extends JpaRepository<UserBookPreference, Long> {

    UserBookPreference findByUserId(final Long userId);
}
