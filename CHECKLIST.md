
# Checklist — VassCommerce

## Model e injeção de dependência
- [x] Interface ProdutoModel criada
- [x] Implementação ProdutoModelMemoria criada
- [x] Implementação ProdutoModelSql criada
- [x] Seleção de implementação por @Profile
- [x] Controller depende da interface pelo construtor

## API de produtos
- [x] POST /produto
- [x] GET /produto/{id}
- [x] GET /produto com filtros
- [x] Respostas 201, 200 e 404
- [x] DTOs e Mapper
- [x] Validação com @Valid
- [x] Tratamento padronizado de erros

## Testes e entrega
- [ ] Compilação sem erros
- [ ] Testar cadastro com resposta 201
- [ ] Testar consulta com resposta 200
- [ ] Testar ID inexistente com resposta 404
- [ ] Testar dados inválidos com resposta 400
- [ ] Salvar evidências do Postman
