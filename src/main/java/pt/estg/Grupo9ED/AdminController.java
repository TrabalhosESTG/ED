package pt.estg.Grupo9ED;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
	@GetMapping("/admin/createPlayer")
	public String createPlayer() {
		return "admin/createPlayer";
	}
}