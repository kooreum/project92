package project92.admin.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project92.admin.mapper.AdminDashBoardMapper;
import project92.admin.service.AdminDashBoardService;
import project92.common.model.Notice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class AdminDashBoardServiceImpl implements AdminDashBoardService {
    private final AdminDashBoardMapper adminDashBoardMapper;

    @Override
    public List<Notice> getRequestEmailList(String adminId) {
        return getNoticeList(adminId, 1);
    }

    @Override
    public List<Notice> getDocumentUploadList(String adminId) {
        return getNoticeList(adminId, 2);
    }

    @Override
    public List<Notice> getDocumentModifyList(String adminId) {
        return getNoticeList(adminId, 3);
    }

    private List<Notice> getNoticeList(String adminId, int type) {
        Map<String, Object> params = new HashMap<>();
        params.put("adminId", adminId);
        params.put("type", type);
        return adminDashBoardMapper.selectAdminDashBoardList(params);
    }
}
