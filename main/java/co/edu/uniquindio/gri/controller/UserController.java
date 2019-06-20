package co.edu.uniquindio.gri.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniquindio.gri.dao.UserDAO;
import co.edu.uniquindio.gri.model.User;

/**
 * Class UserController.
 */
@RestController
@RequestMapping("/rest/service")
public class UserController {
	
	/** DAO para Users. */
	@Autowired
	UserDAO userDAO;
	
	/**
	 * Verifica la información del usuario.
	 *
	 * @param usar el usuario a verificar
	 * @return true, si la validación fue satisfactoria
	 */
	@PostMapping("/user")
	public boolean checkInfoUser(@RequestBody User user){
		return userDAO.checkInfoUser(user);
	}
}
