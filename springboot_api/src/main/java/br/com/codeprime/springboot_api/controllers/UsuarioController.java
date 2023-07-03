package br.com.codeprime.springboot_api.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.codeprime.springboot_api.model.Usuario;
import br.com.codeprime.springboot_api.repository.UsuarioRepository;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
	
	@Autowired /* IC/CD ou CDI - Injeção de dependencia */
	private UsuarioRepository usuarioRepository;
	
	
	@GetMapping(value = "/listar") /* Nosso primeiro método de API */
	@ResponseBody /* Retorna os dados par ao corpo da resposta */
	public ResponseEntity<List<Usuario>> listaUsuario() {

		List<Usuario> usuarios = usuarioRepository.findAll();/* executa a consulta no banco de dados */

		return new ResponseEntity<List<Usuario>>(usuarios, HttpStatus.OK);/* Retorna a lista em JSON */

	}
	@PostMapping(value = "/salvar") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<Usuario> salvar(@RequestBody Usuario usuario) { /* Recebe os dados para salvar */

		Usuario user = usuarioRepository.save(usuario);

		return new ResponseEntity<Usuario>(user, HttpStatus.CREATED);

	}
	@PostMapping(value = "/salvarlista") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<List<Usuario>> salvarLista(@RequestBody List<Usuario> list) { /* Recebe os dados para salvar */
		List<Usuario> users = new ArrayList<Usuario>();
		for(Usuario usuario: list) {
			Usuario user = usuarioRepository.save(usuario);
			users.add(user);
		}

		return new ResponseEntity<List<Usuario>>(users, HttpStatus.CREATED);

	}
	
	@GetMapping(value = "/buscarPorNome") /* mapeia a url */
	@ResponseBody /* Descricao da resposta */
	public ResponseEntity<List<Usuario>> buscarPorNome(@RequestParam(name = "nome") String name) { /* Recebe os dados para consultar */

		List<Usuario> usuario = usuarioRepository.buscarPorNome(name.trim().toUpperCase());

		return new ResponseEntity<List<Usuario>>(usuario, HttpStatus.OK);

	}
}
