package controller;

import java.util.ArrayList;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import model.entity.Jogador;
import model.repository.JogadorRepository;

@Path("/jogador")

public class JogadorController {

	private JogadorRepository jogadorRepo = new JogadorRepository();
	
	@POST
	@Path("/salvar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Jogador salvar (Jogador novoJogador) {
		return jogadorRepo.salvar(novoJogador);
	}
	
	@DELETE
	@Path("/excluir")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean excluir(int id) {
		return jogadorRepo.excluir(id);
	}
	
	@PUT
	@Path("/alterar")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Boolean alterar(Jogador jogador) {
		return jogadorRepo.alterar(jogador);
	}
	
	@GET
	@Path("/consultarporid/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Jogador consultarPorId(@PathParam("id") int id){
		return jogadorRepo.consultarPorId(id);
	}
	
	@GET
	@Path("/consultartodos")
	@Produces(MediaType.APPLICATION_JSON)
	public ArrayList<Jogador> consultarTodos(){
		return jogadorRepo.consultarTodos();
	}
	
}






















