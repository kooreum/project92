package project92.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import project92.member.dto.Member;
import project92.member.service.MemberService;

@Controller
@RequestMapping("/member")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/login")
    public String loginPage() {
        return "member/login";
    }

    @GetMapping("/signUp")
    public String joinPage() {
        return "member/signUp";
    }

    @PostMapping("/signUp")
    public String signUp(@ModelAttribute Member member){
             memberService.saveMember(member);
        return "/member/login";
    }
}
