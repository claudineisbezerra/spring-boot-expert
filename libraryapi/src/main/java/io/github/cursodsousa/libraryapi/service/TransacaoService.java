package io.github.cursodsousa.libraryapi.service;

import io.github.cursodsousa.libraryapi.model.Autor;
import io.github.cursodsousa.libraryapi.model.GeneroLivro;
import io.github.cursodsousa.libraryapi.model.Livro;
import io.github.cursodsousa.libraryapi.repository.AutorRepository;
import io.github.cursodsousa.libraryapi.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class TransacaoService {

    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private LivroRepository livroRepository;

    /// livro (titulo,..., nome_arquivo) -> id.png
    @Transactional
    public void salvarLivroComFoto(){
        // salva o livro
        // repository.save(livro);

        // pega o id do livro = livro.getId();
        // var id = livro.getId();

        // salvar foto do livro -> bucket na nuvem
        // bucketService.salvar(livro.getFoto(), id + ".png");

        // atualizar o nome arquivo que foi salvo
        // livro.setNomeArquivoFoto(id + ".png");
    }

    @Transactional
    public void atualizacaoSemAtualizar(){
        var livro = livroRepository
                .findById(UUID.fromString("d55d4b2a-f7c5-4407-afe5-3753575974c0"))
                .orElse(null);

        livro.setDataPublicacao(LocalDate.of(2024,6,1));
//        livroRepository.save(livro);
    }

    @Transactional
    public void executar(){

//        // salva o autor
//        Autor francisca = new Autor();
//        francisca.setNome("Francisca Júlia");
//        francisca.setNacionalidade("Brasileira");
//        francisca.setDataNascimento(LocalDate.of(1871, 8, 31));
//
//        autorRepository.save(francisca);
//
//        // salva o livro
//        Livro romance = new Livro();
//        romance.setIsbn("90000-84874");
//        romance.setPreco(BigDecimal.valueOf(100));
//        romance.setGenero(GeneroLivro.ROMANCE);
//        romance.setTitulo("Mármores");
//        romance.setDataPublicacao(LocalDate.of(1895, 1, 1));
//
//        romance.setAutor(francisca);
//
//        livroRepository.save(romance);
//
//        if(francisca.getNome().equals("Teste Francisco")){
//            throw new RuntimeException("Rollback!");
//        }

        // salva o autor
        Autor autor = new Autor();
        autor.setNome("Teste Francisco");
        autor.setNacionalidade("Brasileira");
        autor.setDataNascimento(LocalDate.of(1951, 1, 31));

        autorRepository.save(autor);

        // salva o livro
        Livro livro = new Livro();
        livro.setIsbn("90001-84874");
        livro.setPreco(BigDecimal.valueOf(100));
        livro.setGenero(GeneroLivro.FICCAO);
        livro.setTitulo("Teste Livro do Francisco");
        livro.setDataPublicacao(LocalDate.of(1980, 1, 2));

        livro.setAutor(autor);

        livroRepository.save(livro);

        if(autor.getNome().equals("Teste Francisco")){
            throw new RuntimeException("Rollback!");
        }
    }
}
