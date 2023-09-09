package project92.common.mapper;

import org.apache.ibatis.annotations.Mapper;
import project92.common.model.Member;

@Mapper
public interface MemberMapper {

    Member findById(String id);

}
