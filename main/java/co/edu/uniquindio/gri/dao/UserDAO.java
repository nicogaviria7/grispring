package co.edu.uniquindio.gri.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.uniquindio.gri.model.User;
import co.edu.uniquindio.gri.repository.UserRepository;

/**
 * Class UserDAO.
 */
@Service
public class UserDAO {

	/** Repository para user. */
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Verifica la información del usuario.
	 *
	 * @param usar el usuario a verificar
	 * @return true, si la validación fue satisfactoria
	 */
	public boolean checkInfoUser(User user){
		User usuario = userRepository.findOne(user.getUsername());
		if(usuario!=null){
			if(usuario.getPassword().equals(user.getPassword()))
			return true;
		}
		return false;
	}
}
