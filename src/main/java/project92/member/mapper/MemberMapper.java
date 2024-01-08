package project92.member.mapper;

import org.apache.ibatis.annotations.Mapper;
import project92.member.dto.Member;

@Mapper
public interface MemberMapper {

    void save(Member member);
}
