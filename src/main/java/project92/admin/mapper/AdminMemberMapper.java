package project92.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import project92.common.model.Member;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminMemberMapper {
    List<Member> selectMembersByAdminId(Map<String, Object> params);

    int selectMembersByCount(Map<String, Object> params);

}
