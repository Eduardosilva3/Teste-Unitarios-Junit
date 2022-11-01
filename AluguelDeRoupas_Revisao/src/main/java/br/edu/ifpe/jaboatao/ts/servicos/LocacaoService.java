package br.edu.ifpe.jaboatao.ts.servicos;

//br.edu.ifpe.jaboata.ts.servicos
import java.util.Date;

import br.edu.ifpe.jaboatao.ts.entidades.Cliente;
import br.edu.ifpe.jaboatao.ts.entidades.Locacao;
import br.edu.ifpe.jaboatao.ts.entidades.Roupa;
import br.edu.ifpe.jaboatao.ts.exception.LocacaoException;
import br.edu.ifpe.jaboatao.ts.utils.ManipulandoDatas;


public class LocacaoService {

	public Locacao alugarRoupa(Cliente cliente, Roupa roupa) throws LocacaoException {
		
		
		
		if (roupa == null) {
			throw new LocacaoException("Exceção: Roupa nula.");
		}
		if (roupa.getEstoque().equals(0)) {
			throw new LocacaoException("Exceção: Roupa sem estoque.");
		}
		if (cliente.getNome().equals("")) {
			throw new LocacaoException("Exceção: Cliente com nome vazio.");
		}
		
		Locacao locacao = new Locacao();
		locacao.setRoupa(roupa);
		locacao.setCliente(cliente);
		locacao.setDataLocacao(new Date());
		locacao.setValorLocacao(roupa.getValor());

		// Definir a entrega para 3 dias depois.
		Date dataEntrega = ManipulandoDatas.novaDataComDiferencaDeDias(3);
		locacao.setDataRetorno(dataEntrega);

		// Salvando a locacao...
		// O método salvar() será implementado com o avançar do curso.

		return locacao;
	}

	public static void main(String[] args) {
		System.out.println("Funcionando.");
	}
}