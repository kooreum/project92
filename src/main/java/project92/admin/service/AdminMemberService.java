package project92.admin.service;

import project92.common.model.Member;

import java.util.List;
import java.util.Map;

public interface AdminMemberService {

    int countMembers(String adminId, Map<String, Object> searchParameters);

    List<Member> findByAdminId(String adminId, Integer page, Map<String, Object> searchParameters);
}
