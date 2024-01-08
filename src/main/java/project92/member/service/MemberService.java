package project92.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import project92.member.dto.Member;
import project92.member.mapper.MemberMapper;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberMapper memberMapper;

    public Long saveMember(Member member) {
        memberMapper.save(member);
        return member.getId();
    }
}
