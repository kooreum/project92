package project92.admin.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import project92.admin.mapper.AdminMemberMapper;
import project92.admin.service.AdminMemberService;
import project92.common.model.Member;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminMemberServiceImpl implements AdminMemberService {

    private final AdminMemberMapper adminMemberMapper;


    @Override
    public int countMembers(String adminId, Map<String, Object> searchParameters) {
        Map<String, Object> params = new HashMap<>();
        params.put("adminId", adminId);
        params.putAll(searchParameters);
        return adminMemberMapper.selectMembersByCount(params);
    }

    @Override
    public List<Member> findByAdminId(String adminId, Integer page, Map<String, Object> searchParameters) {
        Map<String, Object> params = new HashMap<>();
        params.put("adminId", adminId);
        page = Math.max(1, page);
        params.put("offset", (page - 1) * 10);

        params.putAll(searchParameters);

        log.info("params : {}", params);

        return adminMemberMapper.selectMembersByAdminId(params);
    }
}
