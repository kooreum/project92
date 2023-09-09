package project92.admin.service;

import project92.common.model.Notice;

import java.util.List;

public interface AdminDashBoardService {
    List<Notice> getDocumentUploadList(String adminId);

    List<Notice> getRequestEmailList(String adminId);

    List<Notice> getDocumentModifyList(String adminId);
}
