package project92.admin.mapper;

import org.apache.ibatis.annotations.Mapper;
import project92.common.model.Notice;

import java.util.List;
import java.util.Map;

@Mapper
public interface AdminDashBoardMapper {
    List<Notice> selectAdminDashBoardList(Map<String, Object> params);
}
