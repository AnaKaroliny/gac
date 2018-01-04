package br.com.gac.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import br.com.gac.constantes.Perfil;
import br.com.gac.infra.cdi.CDIServiceLocator;
import br.com.gac.model.Usuario;
import br.com.gac.service.UsuarioService;

@WebListener
public class InitServletContext implements ServletContextListener {
	private ServletContext context;
	private UsuarioService usuarioService = CDIServiceLocator.getBean(UsuarioService.class);

	@Override
	public void contextInitialized(ServletContextEvent event) {
		context = event.getServletContext();

		executarAoIniciarServidor();
	}

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {

	}

	private void executarAoIniciarServidor() {
		criaUsuario(buscaUsuarioPadrao());
	}

	private Usuario buscaUsuarioPadrao() {
		context.log("Buscando usuário com perfil de ADMINISTRADOR...");
		return usuarioService.findByMatricula("uncqadmin"); //1031
	}

	private void criaUsuario(Usuario usuario) {
		if (usuario == null) {
			context.log("Criando usuário padrão...");
			usuario = new Usuario();
			usuario.setNome("Administrador");
			usuario.setCpf("448.484.101-00");
			usuario.setEmail("sistemas_lapis@unicatolicaquixada.edu.br");
			usuario.setMatricula("uncqadmin");
			usuario.setPerfil(Perfil.ADMINISTRADOR);

			context.log("Usuário \"uncqadmin\" criado com sucesso!");

			usuarioService.saveOrUpdate(usuario);
		} else {
			context.log("Usuário padrão encontrado! OK...");
		}
	}

}