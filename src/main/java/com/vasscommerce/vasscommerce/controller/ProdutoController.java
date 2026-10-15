
package com.vasscommerce.vasscommerce.controller;

import com.vasscommerce.vasscommerce.model.Produto;
import com.vasscommerce.vasscommerce.model.ProdutoCreateRequest;
import com.vasscommerce.vasscommerce.model.ProdutoMapper;
import com.vasscommerce.vasscommerce.model.ProdutoModel;
import com.vasscommerce.vasscommerce.model.ProdutoResponse;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoModel produtoModel;
    private final ProdutoMapper mapper;

    public ProdutoController(ProdutoModel produtoModel,
                             ProdutoMapper mapper) {
        this.produtoModel = produtoModel;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(
            @Valid @RequestBody ProdutoCreateRequest req) {

        Produto produto = produtoModel.criar(req);
        ProdutoResponse resposta = mapper.paraResponse(produto);

        return ResponseEntity
                .created(URI.create("/produto/" + produto.getId()))
                .body(resposta);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> obterPorId(
            @PathVariable Long id) {

        return produtoModel.obterPorId(id)
                .map(mapper::paraResponse)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> buscar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) BigDecimal valorMinimo,
            @RequestParam(required = false) BigDecimal valorMaximo) {

        List<ProdutoResponse> produtos = produtoModel
                .buscar(nome, valorMinimo, valorMaximo)
                .stream()
                .map(mapper::paraResponse)
                .toList();

        return ResponseEntity.ok(produtos);
    }
}
