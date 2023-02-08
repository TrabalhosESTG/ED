package pt.estg.Grupo9ED;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class to handle requests to the "/" and "/game" endpoints.
 *
 * @author Guilherme Silva (8210190)
 * @author David Francisco (8210088)
 */
@Controller
public class IndexController {

	/**
	* Handles requests to the "/" endpoint. Adds the name parameter to the model and returns the "index" view.
	*
	* @param name the name parameter to be added to the model
	* @param model the model to be used in the view
	* @return the "index" view
	*/
	@GetMapping("/")
	public String index(@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "index";
	}

	/**
	* Handles requests to the "/game" endpoint. Returns the "game" view.
	*
	* @return the "game" view
	*/
	@GetMapping("/game")
	public String game() {
		return "game";
	}
}

