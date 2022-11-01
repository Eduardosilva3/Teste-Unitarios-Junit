package servicos;

import java.lang.reflect.Method;

import org.junit.Before;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import br.edu.ifpe.jaboatao.ts.entidades.Cliente;
import br.edu.ifpe.jaboatao.ts.entidades.Locacao;
import br.edu.ifpe.jaboatao.ts.entidades.Roupa;
import br.edu.ifpe.jaboatao.ts.exception.LocacaoException;
import br.edu.ifpe.jaboatao.ts.servicos.LocacaoService;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LocacaoServiceTest {
	
	static Cliente cliente;
	
	
	@BeforeAll
	public static void criandoCliente() {
		cliente = new Cliente("Eduardo");
	}
	
	@Test
	@Order(3)
	public void teste01() throws LocacaoException {
		//Cenario
		LocacaoService service = new LocacaoService();
		
		Roupa roupa = new Roupa("Roupa", "M", 5, 50.20);
		Locacao locacao = service.alugarRoupa(cliente, roupa);
		
		Assertions.assertEquals(50.20, locacao.getValorLocacao());
	
	}
	
	@Test
	@DisplayName("Teste de Roupa nula - Modo Try /Catch")
	@Order(1)
	public void teste02() {
		LocacaoService service = new LocacaoService();
		
		Roupa roupa = new Roupa("Roupa", "M", 5, 50.20);
		
		try {
			Locacao locacao = service.alugarRoupa(cliente, null);
			Assertions.fail("Deveria ter ocorrido uma exceção");
		} catch (LocacaoException e) {
			// TODO Auto-generated catch block
			Assertions.assertEquals("Exceção: Roupa nula.", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Teste de Roupa nula - Modo throws")
	@Order(2)
	public void teste03() throws LocacaoException {
		LocacaoService service = new LocacaoService();
		
		Roupa roupa = new Roupa("Roupa", "M", 5, 50.20);
		Locacao locacao = service.alugarRoupa(cliente, roupa);
		
		LocacaoException e = Assertions.assertThrows(LocacaoException.class, () -> {
			service.alugarRoupa(cliente, null);
		}, "Devera ter ocorrido uma exceção");
	}
	
	@Test
	@DisplayName("Teste de Roupa sem Estoque - Modo Try /Catch")
	@Order(4)
	public void teste04() {
		LocacaoService service = new LocacaoService();
		
		Roupa roupa = new Roupa("Roupa", "M", 0, 50.20);
		try {
			Locacao locacao = service.alugarRoupa(cliente, roupa);
			Assertions.fail("Deveria ter ocorrido uma Exceção.");
		} catch (LocacaoException e) {
			Assertions.assertEquals("Exceção: Roupa sem estoque.", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Teste de Roupa sem Estoque - Modo thown")
	@Order(5)
	public void teste05() {
		LocacaoService service = new LocacaoService();
		
		Roupa roupa = new Roupa("Roupa", "M", 0, 50.20);
		LocacaoException e = Assertions.assertThrows(LocacaoException.class, ()-> {
			service.alugarRoupa(cliente, roupa);
		}, "Deveria ter ocorrido uma exceção");
	}
	
	@Test
	@DisplayName("Teste de Cliente Nome - Vazio - Modo Try /Catch")
	@Order(6)
	public void teste06() {
		LocacaoService service = new LocacaoService();
		
		Cliente cliente01 = new Cliente("");
		Roupa roupa = new Roupa("Roupa", "M", 5, 50.20);
		try {
			Locacao locacao = service.alugarRoupa(cliente01, roupa);
			Assertions.fail("Deveria ter ocorrido uma exceção - cliente sem não está com nome vazio.");
		} catch (LocacaoException e) {
			Assertions.assertEquals("Exceção: Cliente com nome vazio.", e.getMessage());
		}
	}
	
	@Test
	@DisplayName("Teste de Cliente Nome - Vazio - Modo thrown")
	@Order(7)
	public void teste07() throws LocacaoException {
		LocacaoService service = new LocacaoService();
		
		Cliente cliente01 = new Cliente("");
		Roupa roupa = new Roupa("Roupa", "M", 5, 50.20);
		
		LocacaoException e = Assertions.assertThrows(LocacaoException.class, ()->{
			service.alugarRoupa(cliente01, roupa);
		}, "Deveria ter ocorrido uma exceção de nome vazio, mas não ocorreu");
	}
	
	
	
	
	
	
	
}
