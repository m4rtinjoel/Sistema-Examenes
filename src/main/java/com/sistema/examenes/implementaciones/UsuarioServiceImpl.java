package com.sistema.examenes.implementaciones;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sistema.examenes.entidades.Usuario;
import com.sistema.examenes.entidades.UsuarioRol;
import com.sistema.examenes.repositorios.RolRepository;
import com.sistema.examenes.repositorios.UsuarioRepository;
import com.sistema.examenes.servicios.UsuarioService;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	@Autowired
	UsuarioRepository usuarioRepository;
	
	@Autowired
	private RolRepository rolRepository;

	@Override
	public Usuario guardarUsuario(Usuario usuario, Set<UsuarioRol> usuariosRoles) throws Exception {
		Usuario usuarioLocal=usuarioRepository.findByUsername(usuario.getUsername());
		if(usuarioLocal!=null) {
			System.out.println("El usuario ya existe");
			throw new Exception("El usuario ya esta presente");
		}else {
			for(UsuarioRol usuarioRol:usuariosRoles) {
				rolRepository.save(usuarioRol.getRol());
			}
			usuario.getUsuariosRoles().addAll(usuariosRoles);
			usuarioLocal=usuarioRepository.save(usuario);
		}
		return usuario;
	}
}
