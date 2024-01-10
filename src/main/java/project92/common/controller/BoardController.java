package project92.common.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class BoardController {

	@GetMapping("/info")
	public String infoPage() {
		return "info";
	}

	@GetMapping("/foodie")
	public String foodiePage() {
		return "foodie";
	}
}
