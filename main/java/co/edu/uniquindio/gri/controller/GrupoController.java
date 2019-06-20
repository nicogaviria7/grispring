package co.edu.uniquindio.gri.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uniquindio.gri.dao.GrupoDAO;
import co.edu.uniquindio.gri.model.Grupo;

@RestController
@RequestMapping("/rest/service")
public class GrupoController {

	@Autowired
	GrupoDAO grupoDAO;

	@GetMapping("/grupos")
	public List<Grupo> getAllGrupos() {
		return grupoDAO.findAll();
	}

	@GetMapping("/grupos/{id}")
	public ResponseEntity<Grupo> getGrupoById(@PathVariable(value = "id") Long grupoId) {

		Grupo grupo = grupoDAO.findOne(grupoId);

		if (grupo == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(grupo);
	}
	
	@GetMapping("/gruposprograma/{id}")
	public List<Grupo> getGruposPrograma (@PathVariable(value = "id") Long programaId){
		return grupoDAO.getGruposPrograma(programaId);
	}
	
	@GetMapping("/gruposcentro/{id}")
	public List<Grupo> getGruposCentro (@PathVariable(value = "id") Long centroId){
		return grupoDAO.getGruposCentro(centroId);
	}
	
	@GetMapping("/gruposfacultad/{id}")
	public List<Grupo> getGruposFacultad (@PathVariable(value = "id") Long facultadId){
		return grupoDAO.getGruposFacultad(facultadId);
	}
}
