package com.vote.vote.repository;

import java.util.ArrayList;

import javax.transaction.Transactional;

import com.vote.vote.db.dto.Member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface MemberJpaRepository extends JpaRepository<Member, String> {
	public Member findByUserid(String userid);
	public Member findByNo(int userid);
	public ArrayList<Member> findAll();

	@Modifying
	@Transactional
	@Query("update Member set userpw = :password, username = :name, gender = :gender, birth = :birth,"
			+ "nickname = :nickname, addr = :addr, addr2 = :addr2 , profile = :profile , phone = :phone where r_id = :rid")
	void userUpdate(@Param("password") String password, @Param("name") String name, @Param("gender") String gender,
			@Param("birth") String birth, @Param("nickname") String nickname, @Param("addr") String addr,
			@Param("addr2") String addr2, @Param("profile") String profile,@Param("phone") String phone, @Param("rid") int rid);

	@Modifying
	@Transactional
	@Query("update Member set userpw = :password, username = :name, gender = :gender, birth = :birth,"
			+ "nickname = :nickname, addr = :addr, addr2 = :addr2, phone = :phone  where r_id = :rid ")
	void kakaoUpdate(@Param("password") String password, @Param("name") String name, @Param("gender") String gender,
			@Param("birth") String birth, @Param("nickname") String nickname, @Param("addr") String addr,
			@Param("addr2") String addr2,@Param("phone") String phone, @Param("rid") int rid);

	@Modifying
	@Transactional
	@Query("update Member set role = 2 where r_id = :rid ")
	void managerUpdate(@Param("rid") int rid);

	@Transactional
	@Modifying
	@Query(value = " update r_user "
				 + " set point = ?1 "
				 + " where r_id = ?2", nativeQuery = true)
	void updatePoint(@Param("point") int point, @Param("no") int no) throws Exception;

	
	@Transactional
	@Modifying
	@Query(value ="select DISTINCT r_user.username, r_user.phone"
				 + "from a_detail, r_user"
				 + "where a_detail.r_id = r_user.r_id and  a_detail.apply_id = ?1", nativeQuery = true)
	public ArrayList<Member> showRecruits(@Param("applyId") int applyId) throws Exception;

}