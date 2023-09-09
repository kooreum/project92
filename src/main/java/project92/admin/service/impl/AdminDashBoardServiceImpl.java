package project92.admin.service.impl;

import org.springframework.stereotype.Service;
import project92.admin.service.AdminDashBoardService;
import project92.common.model.Notice;

import java.util.List;

@Service
public class AdminDashBoardServiceImpl implements AdminDashBoardService {

    @Override
    public List<Notice> getDocumentUploadList(String adminId) {
        return null;
    }

    @Override
    public List<Notice> getRequestEmailList(String adminId) {
        return null;
    }

    @Override
    public List<Notice> getDocumentModifyList(String adminId) {
        return null;
    }
}
