package com.project.questapp.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.project.questapp.entities.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	List<Comment> findByUserIdAndPostId(Long userId, Long postId);

	List<Comment> findByUserId(Long userId);

	List<Comment> findByPostId(Long postId);
	
	@Query(value = "select 'commented on' , 'post_id: ' , c.post_id, 'avatar_id: ' ,u.avatar, 'user_name: ' ,u.user_name from "
			+ "comment c left join user u on u.id = c.user_id "
			+ "where c.post_id in :postIds limit 5", nativeQuery = true)
	List<Object> findUserCommentsByPostId(@Param("postIds") List<Long> postIds);

}

