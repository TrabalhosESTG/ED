package pt.estg.Grupo9ED;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class listagemController {
	@GetMapping("/listagem/connectorsOrdered")
	public String connectorsOrdered() {
		return "listagem/connectoresOrdenados";
	}

	@GetMapping("/listagem/portaisOrdenados")
	public String portaisOrdenados() {
		return "listagem/portaisOrdenados";
	}

	@GetMapping("/listagem/encontrarCaminhoCurto")
	public String encontrarCaminhoCurto() {
		return "listagem/encontrarCaminhoCurto";
	}
}
