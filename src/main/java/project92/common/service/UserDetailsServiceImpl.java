package project92.common.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import project92.common.mapper.MemberMapper;
import project92.common.model.Member;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {

        Member member;

        try {
            member = memberMapper.findById(id);
            log.info("member :{}", member.toString());
            log.info("Fetched member from database: " + member.toString());
        } catch (Exception e) {
            throw new UsernameNotFoundException("회원이 존재하지 않습니다.");
        }

        if (member.getStatus() == 0) {
            throw new UsernameNotFoundException("회원이 존재하지 않습니다.");
        }

        UserDetails userDetails =
                org.springframework.security.core.userdetails.User
                        .withUsername(member.getId())
                        .password(member.getPassword())
                        .roles(member.getLevel().toString())
                        .build();

        log.info("UserDetails: " + userDetails.toString());
        return userDetails;

    }
}
