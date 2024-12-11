package io.github.cursodsousa.libraryapi.repository;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class AutorRepositoryTest {

    @Autowired
    AutorRepository autorRepository;

    @Autowired
    LivroRepository livroRepository;

    @Test
    public void salvarTest(){
        Autor joao = new Autor();
        joao.setNome("João");
        joao.setNacionalidade("Brasileiro");
        joao.setDataNascimento(LocalDate.of(1950, 3, 31));

        Autor maria = new Autor();
        maria.setNome("Maria");
        maria.setNacionalidade("Brasileira");
        maria.setDataNascimento(LocalDate.of(1951, 1, 31));


        var joaoSalvo = autorRepository.save(joao);
        System.out.println("Autor João Salvo: " + joaoSalvo);

        var mariaSalvo = autorRepository.save(maria);
        System.out.println("Autor Maria Salvo: " + mariaSalvo);
    }


    @Test
    public void atualizarTest(){
        var id = UUID.fromString("4210c1d5-17c3-4dbb-9070-2f832a9a361b");

        Optional<Autor> possivelAutor = autorRepository.findById(id);

        if(possivelAutor.isPresent()){

            Autor autorEncontrado =  possivelAutor.get();
            System.out.println("Dados do Autor:");
            System.out.println(autorEncontrado);

            autorEncontrado.setDataNascimento(LocalDate.of(1960, 1, 30));

            autorRepository.save(autorEncontrado);

        }
    }

    @Test
    public void listarTest(){
        List<Autor> lista = autorRepository.findAll();
        lista.forEach(System.out::println);
    }

    @Test
    public void countTest(){
        System.out.println("Contagem de autores: " + autorRepository.count());
    }

    @Test
    public void deletePorIdTest(){
        var id = UUID.fromString("a4d8a190-1a05-4de5-951e-1eb8d777e1c5");
        autorRepository.deleteById(id);
    }

    @Test
    public void deleteTest(){
        var id = UUID.fromString("abc082bf-1d23-4767-b3d9-9f322856ca6a");
        var maria = autorRepository.findById(id).get();
        autorRepository.delete(maria);
    }

    @Test
    void salvarAutorComLivrosTest(){
        Autor autor = new Autor();
        autor.setNome("Antonio Houaiss");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1915, 10, 15));

        Livro livro = new Livro();
        livro.setIsbn("20847-84801");
        livro.setPreco(BigDecimal.valueOf(204));
        livro.setGenero(GeneroLivro.CIENCIA);
        livro.setTitulo("Dicionário Houaiss da Língua Portuguesa");
        livro.setDataPublicacao(LocalDate.of(2001, 1, 2));
        livro.setAutor(autor);

        Livro livro2 = new Livro();
        livro2.setIsbn("99999-84802");
        livro2.setPreco(BigDecimal.valueOf(650));
        livro2.setGenero(GeneroLivro.CIENCIA);
        livro2.setTitulo("Dicionário inglês-português");
        livro2.setDataPublicacao(LocalDate.of(2005, 1, 2));
        livro2.setAutor(autor);

        Livro livro3 = new Livro();
        livro3.setIsbn("99999-84803");
        livro3.setPreco(BigDecimal.valueOf(350));
        livro3.setGenero(GeneroLivro.CIENCIA);
        livro3.setTitulo("A nova ortografia da lingua Portuguesa");
        livro3.setDataPublicacao(LocalDate.of(1991, 1, 2));
        livro3.setAutor(autor);

        Livro livro4 = new Livro();
        livro4.setIsbn("99999-84804");
        livro4.setPreco(BigDecimal.valueOf(450));
        livro4.setGenero(GeneroLivro.CIENCIA);
        livro4.setTitulo("A nova ortografia da lingua Portuguesa");
        livro4.setDataPublicacao(LocalDate.of(1991, 1, 2));
        livro4.setAutor(autor);


        autor.setLivros(new ArrayList<>());
        autor.getLivros().add(livro);
        autor.getLivros().add(livro2);
        autor.getLivros().add(livro3);
        autor.getLivros().add(livro4);

        autorRepository.save(autor);
//        livroRepository.saveAll(autor.getLivros());
    }

    @Test
    void listarLivrosAutor(){
        var id = UUID.fromString("0f7eadb0-7698-4776-9495-6c377394d38a");

        var autor = autorRepository.findById(id).get();

        // buscar os livros do autor manualmente
        List<Livro> livrosLista = livroRepository.findByAutor(autor);
        autor.setLivros(livrosLista);

        autor.getLivros().forEach(System.out::println);
    }

}
