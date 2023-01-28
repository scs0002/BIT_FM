package com.biz.fm.repository;

import java.util.List;
import java.util.Optional;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.fm.domain.dto.MemberDto.MemberRead;
import com.biz.fm.domain.dto.MemberDto.SignIn;
import com.biz.fm.domain.entity.Fimage;
import com.biz.fm.domain.entity.Member;

@Mapper
public interface MemberRepository {
	@Select("SELECT * FROM member WHERE delete_date is null")
	public List<MemberRead> findAll();
	
	@Select("SELECT * FROM member WHERE id = #{id} AND delete_date is null")
	public Member findEntityById(String Id);
	
	@Select("SELECT * FROM member WHERE email = #{email} AND delete_date is null")
	public Member findEntityByEmail(String email);
	
	@Select("SELECT * FROM member WHERE id = #{id} AND delete_date is null")
	public MemberRead findDtoById(String id);
	
	@Select("SELECT * FROM member WHERE email = #{email} AND delete_date is null")
	public MemberRead findDtoByEmail(String email);
	
	@Select("SELECT * FROM member WHERE password = #{password} AND delete_date is null")
	public MemberRead findDtoByPassword(String password);

	@Insert("INSERT INTO member VALUES "
			+ "(#{id}, #{name}, #{email}, #{password}, #{role}, #{phoneNumber}, #{birth}, #{gender}, #{address}, now(), null)")
	public int insert(SignIn member);
	
	@Update("UPDATE member SET "
			+ "name = #{name}, email = #{email}, password = #{password}, role = #{role}, phone_number = #{phoneNumber} "
			+ "birth = #{birth}, gender = #{gender}, address = #{address} WHERE id = #{id}")
	public int update(Member member);

	@Update("UPDATE member SET delete_Date = now() WHERE id = #{id}")
	public int delete(String id);

	
	
}
