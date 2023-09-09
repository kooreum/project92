package project92.admin.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import project92.admin.service.AdminDashBoardService;
import project92.common.model.Notice;

import java.util.List;

@Controller
@RequestMapping("/admin")
@Slf4j
@AllArgsConstructor
public class AdminDashBoardController {
    private final AdminDashBoardService adminDashBoardService;
    public String getAdminDashBoard(Model model) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String adminId = authentication.getName();
        log.info("adminId: {}", adminId);

        List<Notice> documentUploadList = adminDashBoardService.getDocumentUploadList(adminId);
        List<Notice> requestEmailList = adminDashBoardService.getRequestEmailList(adminId);
        List<Notice> documentModifyList = adminDashBoardService.getDocumentModifyList(adminId);

        model.addAttribute("documentUploadList", documentUploadList);
        model.addAttribute("requestEmailList", requestEmailList);
        model.addAttribute("documentModifyList", documentModifyList);

        return "groups/admin/admin_dashboard";
    }
}
