package co.edu.uniquindio.gri.webcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import co.edu.uniquindio.gri.dao.ProduccionDAO;
import co.edu.uniquindio.gri.dao.TipoDAO;
import co.edu.uniquindio.gri.model.Tipo;

@Controller
public class ReporteController {
	
	@Autowired
	 TipologiasController tipologiasController;
	
	@Autowired
	TipoDAO tipoDAO;
	
	@Autowired
	ProduccionDAO produccionDAO;

	@GetMapping("reporte")
	public String getApropiacion(@RequestParam(name = "prod", required = false, defaultValue = "0") String tipoProduccion, @RequestParam(name = "type", required = false, defaultValue = "u") String type,
			@RequestParam(name = "id", required = false, defaultValue = "0") String id,  Model model) {

		String[] datos = tipologiasController.getDatos(id, type);
		
		Tipo tipo = tipoDAO.getTipoById(Long.parseLong(tipoProduccion));
		
		model.addAttribute("id", id);
		model.addAttribute("tipo", type);
		model.addAttribute("nombre", datos[0]);
		model.addAttribute("color", datos[1]);
		model.addAttribute("tipo", tipo.getNombre());
		model.addAttribute("producciones", produccionDAO.getProducciones(type, Long.parseLong(id), Long.parseLong(tipoProduccion)));

		if (tipo.getTipoProduccion().getId() == 3){
			model.addAttribute("esBibliografica", true);
		} else {
			model.addAttribute("esBibliografica", false);
		}		
		
		return "reporte";
	}
}
