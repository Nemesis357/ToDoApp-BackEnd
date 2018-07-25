package com.todoapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.todoapp.model.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
	public static final String FIND_USERS = "SELECT username FROM users";
	public static final String UPDATE_ROLE = "UPDATE users.user_roles SET role_id = 1 WHERE user_id = 25";
	
	Optional<User> findByEmail(String email);

    Optional<User> findByUsernameOrEmail(String username, String email);

    List<User> findByIdIn(List<Long> userIds);

    User findByUsername(String username);
    
    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
    
    Boolean deleteByUsername(String username);
    
    @Query(value = FIND_USERS, nativeQuery = true)
    List<String> getUsers();
    
    @Modifying(clearAutomatically = true)
    @Query(value="UPDATE users SET role_id =:roleId WHERE user_id =:userId", nativeQuery = true)
    void updateRoles(@Param("userId") Long userID, @Param("roleId") int roleId);
}
