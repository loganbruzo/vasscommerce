
package com.vasscommerce.vasscommerce.model;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Profile("sql")
public class ProdutoModelSql implements ProdutoModel {

    private final List<Produto> produtos = new ArrayList<>();
    private final AtomicLong proximoId = new AtomicLong(1);

    @Override
    public Produto criar(ProdutoCreateRequest req) {
        Produto produto = new Produto(
                proximoId.getAndIncrement(),
                req.getNome(),
                req.getDescricao(),
                req.getValorUnitario()
        );

        produtos.add(produto);
        return produto;
    }

    @Override
    public Optional<Produto> obterPorId(Long id) {
        return produtos.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst();
    }

    @Override
    public List<Produto> buscar(String nome,
                                BigDecimal valorMinimo,
                                BigDecimal valorMaximo) {
        return produtos.stream()
                .filter(p -> nome == null
                        || p.getNome().toLowerCase()
                        .contains(nome.toLowerCase()))
                .filter(p -> valorMinimo == null
                        || p.getValorUnitario().compareTo(valorMinimo) >= 0)
                .filter(p -> valorMaximo == null
                        || p.getValorUnitario().compareTo(valorMaximo) <= 0)
                .toList();
    }
}
