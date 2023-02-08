package pt.estg.Grupo9ED;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller to handle administrative tasks.
 *
 * @author Guilherme Silva (8210190)
 * @author David Francisco (8210088)
 */
@Controller
public class AdminController {

	/**
     * Handles the HTTP GET request for "/admin/createPlayer".
     * Returns the view page for creating a player.
     *
     * @return The view page for creating a player.
     */
	@GetMapping("/admin/createPlayer")
	public String createPlayer() {
		return "admin/createPlayer";
	}

	/**
     * Handles the HTTP GET request for "/admin/createConnector".
     * Returns the view page for adding a connector.
     *
     * @return The view page for adding a connector.
     */
	@GetMapping("/admin/createConnector")
	public String createConnector() {
		return "admin/addConnector";
	}

	/**
     * Handles the HTTP GET request for "/admin/addPortal".
     * Returns the view page for adding a portal.
     *
     * @return The view page for adding a portal.
     */
	@GetMapping("/admin/addPortal")
	public String addPortal() {
		return "admin/addPortal";
	}

	/**
     * Handles the HTTP GET request for "/admin/importLocalsJson".
     * Returns the view page for importing locals from a JSON file.
     *
     * @return The view page for importing locals from a JSON file.
     */
	@GetMapping("/admin/importLocalsJson")
	public String importLocalsJson() {
		return "admin/importLocalsJson";
	}

	/**
     * Handles the HTTP GET request for "/admin/importPlayersJson".
     * Returns the view page for importing players from a JSON file.
     *
     * @return The view page for importing players from a JSON file.
     */
	@GetMapping("/admin/importPlayersJson")
	public String importPlayersJson() {
		return "admin/importPlayersJson";
	}

	/**
     * Handles the HTTP GET request for "/admin/importRoutesJson".
     * Returns the view page for importing routes from a JSON file.
     *
     * @return The view page for importing routes from a JSON file.
     */
	@GetMapping("/admin/importRoutesJson")
	public String importRoutesJson() {
		return "admin/importRoutesJson";
	}

	/**
     * Handles the HTTP GET request for "/admin/importConfigJson".
     * Returns the view page for importing the configuration from a JSON file.
     *
     * @return The view page for importing the configuration from a JSON file.
     */
	@GetMapping("/admin/importConfigJson")
	public String importConfigJson() {
		return "admin/importConfigJson";
	}

	/**
     * Handles the HTTP GET request for "/admin/addRota".
     * Returns the view page for adding a route.
     *
     * @return The view page for adding a route.
     */
	@GetMapping("/admin/addRota")
	public String addRota() {
		return "admin/addRota";
	}

	/**
     * Handles request for removing a route.
     *
     * @return "admin/removeRota" string
     */
	@GetMapping("/admin/removeRota")
	public String removeRota() {
		return "admin/removeRota";
	}

	/**
     * Handles request for removing a local.
     *
     * @return "admin/removeLocal" string
     */
	@GetMapping("/admin/removeLocal")
	public String removeLocal() {
		return "admin/removeLocal";
	}

}
