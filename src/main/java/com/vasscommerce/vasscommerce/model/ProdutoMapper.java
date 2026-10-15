
package com.vasscommerce.vasscommerce.model;

import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto paraProduto(ProdutoCreateRequest req) {
        Produto produto = new Produto();
        produto.setNome(req.getNome());
        produto.setDescricao(req.getDescricao());
        produto.setValorUnitario(req.getValorUnitario());
        return produto;
    }

    public ProdutoResponse paraResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getValorUnitario()
        );
    }
}
