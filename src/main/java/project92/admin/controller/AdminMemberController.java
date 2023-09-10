package project92.admin.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import project92.admin.service.AdminMemberService;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/admin")
@Slf4j
@AllArgsConstructor
public class AdminMemberController {

    private final AdminMemberService adminMemberService;

        @GetMapping("/members")
        public String getAdminMemberView(
                @RequestParam(required = false, defaultValue = "1") Integer page,
                @RequestParam(required = false, defaultValue = "") String company,
                @RequestParam(required = false, defaultValue = "") String name,
                @RequestParam(required = false, defaultValue = "") String mobile,
                @RequestParam(required = false) String status,
                Model model) {

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String adminId = authentication.getName();

            log.info("adminId : {}", adminId);

            Map<String, Object> searchParameters = new HashMap<>();
            searchParameters.put("company", company);
            searchParameters.put("name", name);
            searchParameters.put("mobile", mobile);
            searchParameters.put("status", status);

            int pageSize = 10;
            int totalCount = adminMemberService.countMembers(adminId, searchParameters);
            log.info("Total count: {}", totalCount);
            int totalPages = (int) Math.ceil((double) totalCount / pageSize);

            int windowSize = 10;
            int startPage = Math.max(page - windowSize / 2, 1);
            int endPage = Math.min(startPage + windowSize - 1, totalPages);
            if (endPage - startPage < windowSize - 1) {
                startPage = Math.max(endPage - windowSize + 1, 1);
            }

            model.addAttribute("title", "매니저 관리");
            model.addAttribute("memberList", adminMemberService.findByAdminId(adminId, page, searchParameters));

            model.addAttribute("totalPages", totalPages);
            model.addAttribute("totalCount", totalCount);
            model.addAttribute("currentPage", page);
            model.addAttribute("startPage", startPage);
            model.addAttribute("endPage", endPage);
            model.addAttribute("searchParameters", searchParameters);

        return "admin/admin_members";
    }

}
