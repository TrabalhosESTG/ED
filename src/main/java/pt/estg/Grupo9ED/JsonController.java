package pt.estg.Grupo9ED;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JsonController {
	@PostMapping("/admin/addJson/add")
	public String createPlayer(@RequestParam(value = "data", defaultValue = "Player") String data) {
		return data;
	}
}
