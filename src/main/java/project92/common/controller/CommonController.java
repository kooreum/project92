package project92.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class CommonController {


	@GetMapping("/")
	public String mainPage() {
		return "main";
	}

}
