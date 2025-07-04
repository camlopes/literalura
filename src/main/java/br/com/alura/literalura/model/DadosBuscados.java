package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosBuscados(@JsonAlias("count") Integer contagem,
                            @JsonAlias("results") List<DadosLivro> livrosBuscados) {
}
