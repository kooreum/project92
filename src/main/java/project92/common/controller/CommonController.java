package project92.common.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collection;


@Controller
public class CommonController {


	@GetMapping("/")
	public String redirectToDashboard() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		for (GrantedAuthority grantedAuthority : authorities) {
			String role = grantedAuthority.getAuthority();
			switch (role) {
				case "ROLE_ADMIN":
					return "redirect:/admin";
				case "ROLE_MANAGER":
					return "redirect:/manager";
				case "ROLE_SUPPLIER":
					return "redirect:/supplier";
			}
		}
		return "redirect:/";
	}

}
