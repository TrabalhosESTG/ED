package pt.estg.Grupo9ED;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller class to handle requests to the "/listagem" endpoints.
 *
 * @author Guilherme Silva (8210190)
 * @author David Francisco (8210088)
 */
@Controller
public class listagemController {

	/**
	* Handles requests to the "/listagem/connectorsOrdered" endpoint. Returns the "listagem/connectoresOrdenados" view.
	*
	* @return the "listagem/connectoresOrdenados" view
	*/
	@GetMapping("/listagem/connectorsOrdered")
	public String connectorsOrdered() {
		return "listagem/connectoresOrdenados";
	}

	/**
	* Handles requests to the "/listagem/portaisOrdenados" endpoint. Returns the "listagem/portaisOrdenados" view.
	*
	* @return the "listagem/portaisOrdenados" view
	*/
	@GetMapping("/listagem/portaisOrdenados")
	public String portaisOrdenados() {
		return "listagem/portaisOrdenados";
	}

	/**
	* Handles requests to the "/listagem/encontrarCaminhoCurto" endpoint. Returns the "listagem/encontrarCaminhoCurto" view.
	*
	* @return the "listagem/encontrarCaminhoCurto" view
	*/
	@GetMapping("/listagem/encontrarCaminhoCurto")
	public String encontrarCaminhoCurto() {
		return "listagem/encontrarCaminhoCurto";
	}
}
