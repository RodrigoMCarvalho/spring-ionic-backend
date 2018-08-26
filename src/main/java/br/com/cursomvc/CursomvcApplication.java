package br.com.cursomvc;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.cursomvc.models.Categoria;
import br.com.cursomvc.models.Cidade;
import br.com.cursomvc.models.Cliente;
import br.com.cursomvc.models.Endereco;
import br.com.cursomvc.models.Estado;
import br.com.cursomvc.models.Produto;
import br.com.cursomvc.models.enums.TipoCliente;
import br.com.cursomvc.repositories.CategoriaRepository;
import br.com.cursomvc.repositories.CidadeRepository;
import br.com.cursomvc.repositories.ClienteRepository;
import br.com.cursomvc.repositories.EnderecoRepository;
import br.com.cursomvc.repositories.EstadoRepository;
import br.com.cursomvc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomvcApplication implements CommandLineRunner {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository	enderecoRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(CursomvcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informática");
		Categoria cat2 = new Categoria(null, "Escritório");
		
		Produto p1 = new Produto(null, "Computador", 2000.00);
		Produto p2 = new Produto(null, "Impressora", 800.00);
		Produto p3 = new Produto(null, "Mouse", 50.00);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1, p2, p3));
		
		Estado est1 = new Estado(null, "Rio de janeiro");
		Estado est2 = new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Rio das Ostras", est1);
		Cidade c2 = new Cidade(null, "Saquarema", est1);
		Cidade c3 = new Cidade(null, "Santos", est2);
		
		est1.getCidades().addAll(Arrays.asList(c1, c2));
		est2.getCidades().addAll(Arrays.asList(c3));
		
		estadoRepository.saveAll(Arrays.asList(est1, est2));
		cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
		
		Cliente cli1 = new Cliente(null, "Rodrigo", "rodrigo@mail.com", "121212121", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("9999-9999", "8888-8888"));
		
		Endereco e1 = new Endereco(null, "Rua ABC", "422", "bloco 5", "Abolição", "20751-200", cli1, c1);
		Endereco e2 = new Endereco(null, "Rua XYZ", "150", "bloco 1", "Centro", "52001-200", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1, e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(e1, e2));
		
	}
	
	
	
	
	
	
	
}
