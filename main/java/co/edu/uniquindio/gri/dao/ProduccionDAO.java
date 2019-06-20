package co.edu.uniquindio.gri.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import co.edu.uniquindio.gri.model.Grupo;
import co.edu.uniquindio.gri.model.Produccion;
import co.edu.uniquindio.gri.model.ProduccionBGrupo;
import co.edu.uniquindio.gri.model.ProduccionGrupo;
import co.edu.uniquindio.gri.model.Tipo;
import co.edu.uniquindio.gri.model.User;
import co.edu.uniquindio.gri.repository.ProduccionRepository;
import co.edu.uniquindio.gri.repository.TipoRepository;

/**
 * Class ProduccionDAO.
 */
@Service
public class ProduccionDAO {

	/** Repository para producciones. */
	@Autowired
	ProduccionRepository produccionRepository;

	/** Repository para tipos. */
	@Autowired
	TipoRepository tipoRepository;

	/**
	 * Obtiene las producciones de una entidad específica.
	 *
	 * @param type
	 *            el tipo de la entidad (f: Facultad, p: Programa, c: Centro, g:
	 *            Grupo de Investigación i: Investigador)
	 * @param entityId
	 *            el id de la entidad
	 * @param tipoId
	 *            el tipo de la producción a obtener
	 * @return lista de producciones
	 */
	public List getProducciones(String type, Long entityId, Long tipoId) {
		Tipo tipo = tipoRepository.findOne(tipoId);
		long idTipoProd = tipo.getTipoProduccion().getId();

		if (type.equals("i")) {
			if (idTipoProd == 3) {
				return produccionRepository.getProduccionB(entityId, tipoId);
			} else if (tipoId == 1) {
				return produccionRepository.getTrabajosDirigidos(entityId);
			} else {
				return produccionRepository.getProduccion(entityId, tipoId);
			}

		} else if (type.equals("g")) {
			if (idTipoProd == 3) {
				return produccionRepository.getProduccionBGrupo(entityId, tipoId);
			} else if (tipoId == 1) {
				return produccionRepository.getTrabajosDirigidosGrupo(entityId);
			} else {
				return produccionRepository.getProduccionGrupo(entityId, tipoId);
			}

		} else if (type.equals("p")) {
			if (idTipoProd == 3) {
				return produccionRepository.getProduccionBPrograma(entityId, tipoId);
			} else if (tipoId == 1) {
				return produccionRepository.getTrabajosDirigidosPrograma(entityId);
			} else {
				return produccionRepository.getProduccionPrograma(entityId, tipoId);
			}

		} else if (type.equals("c")) {
			if (idTipoProd == 3) {
				return produccionRepository.getProduccionBCentro(entityId, tipoId);
			} else if (tipoId == 1) {
				return produccionRepository.getTrabajosDirigidosCentro(entityId);
			} else {
				return produccionRepository.getProduccionCentro(entityId, tipoId);
			}

		} else if (type.equals("f")) {
			if (idTipoProd == 3) {
				List<ProduccionBGrupo> prod_programas = produccionRepository.getProduccionBFacultadPrograma(entityId,
						tipoId);
				List<ProduccionBGrupo> prod_centros = produccionRepository.getProduccionBFacultadCentro(entityId,
						tipoId);

				for (ProduccionBGrupo produccion : prod_centros) {
					if (!prod_programas.contains(produccion)) {
						prod_programas.add(produccion);
					}
				}

				return prod_programas;
			} else if (tipoId == 1) {
				List<ProduccionGrupo> prod_programas = produccionRepository
						.getTrabajosDirigidosFacultadPrograma(entityId);
				List<ProduccionGrupo> prod_centros = produccionRepository.getTrabajosDirigidosFacultadCentro(entityId);

				for (ProduccionGrupo produccion : prod_centros) {
					if (!prod_programas.contains(produccion)) {
						prod_programas.add(produccion);
					}
				}

				return prod_programas;
			} else {
				List<ProduccionGrupo> prod_programas = produccionRepository.getProduccionFacultadPrograma(entityId,
						tipoId);
				List<ProduccionGrupo> prod_centros = produccionRepository.getProduccionFacultadCentro(entityId, tipoId);

				for (ProduccionGrupo produccion : prod_centros) {
					if (!prod_programas.contains(produccion)) {
						prod_programas.add(produccion);
					}
				}

				return prod_programas;
			}

		} else {
			if (idTipoProd == 3) {
				return produccionRepository.getProduccionBGeneral(tipoId);
			} else if (tipoId == 1) {
				return produccionRepository.getTrabajosDirigidosGeneral();
			} else {
				return produccionRepository.getProduccionGeneral(tipoId);
			}

		}
	}

	/**
	 * Obtiene las producciones de acuerdo a una cadena de búsqueda.
	 *
	 * @param tipo,
	 *            el tipo de búsqueda a realizar (i: CvLAC, g: GrupLAC)
	 * @param cadena
	 *            la cadena de búsqueda
	 * @return lista de producciones correspondientes con la cadena de búsqueda.
	 */
	@SuppressWarnings({ "rawtypes" })
	public List getProduccionBusqueda(String tipo, String cadena) {
		if (tipo.equals("g")) {
			return produccionRepository.getProduccionGBusqueda(cadena);
		} else {
			return produccionRepository.getProduccionBusqueda(cadena);
		}
	}

	public boolean actualizarProducciones(String type, int estado, Long tipoId, Long prodId) {

		Tipo tipo = tipoRepository.findOne(tipoId);
		long idTipoProd = tipo.getTipoProduccion().getId();

		if (type.equals("g")) {
			if (idTipoProd == 3) {
				if (estado==0) {
					produccionRepository.updateProduccionBGrupo(prodId,1);
					return true;
				} else {
					produccionRepository.updateProduccionBGrupo(prodId,0);
					return true;
				}
			} else {
				if (estado==0) {
					produccionRepository.updateProduccionGrupo(prodId,1);
					return true;
				} else {
					produccionRepository.updateProduccionGrupo(prodId,0);
					return true;
				}
			}
		} else {
			if (idTipoProd == 3) {
				if (estado==0) {
					produccionRepository.updateProduccionB(prodId,1);
					return true;
				} else {
					produccionRepository.updateProduccionB(prodId,0);
					return true;
				}
			} else {
				if (estado==0) {
					produccionRepository.updateProduccion(prodId,1);
					return true;
				} else {
					produccionRepository.updateProduccion(prodId,0);
					return true;
				}
				
			}
		}
	}
}
