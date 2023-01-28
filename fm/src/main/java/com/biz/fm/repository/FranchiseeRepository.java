package com.biz.fm.repository;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.One;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeCreate;
import com.biz.fm.domain.dto.FranchiseeDto.FranchiseeRead;
import com.biz.fm.domain.entity.Franchisee;

@Mapper

public interface FranchiseeRepository{
	
	@Select("SELECT * FROM franchisee WHERE delete_date is null")
	@Results(id="FranchiessDto", value = {
		@Result(property = "owner", column = "owner_id", one = @One(select = "com.biz.fm.repository.MemberRepository.findDtoById"))
	})
	public List<FranchiseeRead> findAll();
	
	@Select("SELECT * FROM franchisee WHERE business_number = #{businessNumber} AND delete_date is null")
	@ResultMap("FranchiessDto")
	public FranchiseeRead findDtoByBusinessNumber(String businessNumber);
	
	@Select("SELECT * FROM franchisee WHERE business_number = #{businessNumber} AND delete_date is null")
	public Franchisee findEntityByBusinessNumber(String businessNumber);
	
	@Select("SELECT hours FROM franchisee WHERE business_number = #{businessNumber} AND delete_date is null")
	public String findHoursByBusinessNumber(String businessNumber);
	
	@Insert("INSERT INTO franchisee VALUES (#{businessNumber}, #{name}, #{firstImg}, "
			+ "#{postalCode}, #{address}, #{x}, #{y}, #{phoneNumber}, #{ownerId}, #{intro}, #{hours}, now(), null)")
	public int insert(FranchiseeCreate franchisee);
	
	@Update("UPDATE franchisee SET name = #{name}, first_img = #{firstImg}, postal_code = #{postalCode},"
			+ "address = #{address}, x = #{x}, y = #{y}, phone_number = #{phoneNumber}, intro = #{intro},"
			+ "hours = #{hours} WHERE business_number = #{businessNumber}")
	public int update(Franchisee franchisee);
	
	@Update("UPDATE franchisee SET delete_date = now() WHERE business_number = #{businessNumber}")
	public int delete(String businessNumber);
}
