package co.edu.uniquindio.gri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniquindio.gri.dao.ProduccionDAO;
import co.edu.uniquindio.gri.model.Produccion;

/**
 * Class ProduccionController.
 */
@RestController
@RequestMapping("/rest/service")
public class ProduccionController { 

	/** DAO para producciones. */
	@Autowired
	ProduccionDAO produccionDAO;
	
	/**
	 * Obtiene las producciones de una entidad específica.
	 *
	 * @param type el tipo de la entidad (f: Facultad, p: Programa, c: Centro, g: Grupo de Investigación i: Investigador)
	 * @param entityId el id de la entidad
	 * @param tipoId el tipo de la producción a obtener
	 * @return lista de producciones
	 */
	@GetMapping("/producciones/{type}/{id}/{tipo}")
	public List getProducciones(@PathVariable("type") String type, @PathVariable("id") Long entityId,@PathVariable("tipo") Long tipoId) {
		return produccionDAO.getProducciones(type, entityId, tipoId);
	}
	
	/**
	 * Obtiene las producciones de acuerdo a una cadena de búsqueda.
	 *
	 * @param tipo, el tipo de búsqueda a realizar (i: CvLAC, g: GrupLAC)
	 * @param cadena la cadena de búsqueda
	 * @return lista de producciones correspondientes con la cadena de búsqueda.
	 */
	@GetMapping("/busqueda/{type}/{cadena}")
	public List getProduccionBBusqueda(@PathVariable("type") String tipo, @PathVariable("cadena") String cadena){
		String busqueda = cadena.replaceAll("\\+", " ").toUpperCase();
		return produccionDAO.getProduccionBusqueda(tipo, busqueda);
	}
	
	@PostMapping("/producciones/{type}/{estado}/{tipo}/{prodId}")
	public boolean updateInfoProduccion(@PathVariable("type") String type, @PathVariable("estado") int estado, @PathVariable("tipo") Long tipoId,@PathVariable("prodId") Long prodId){
		return produccionDAO.actualizarProducciones(type, estado, tipoId, prodId);
	}
}
