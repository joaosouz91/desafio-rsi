# Desafio RSI

API REST feita com SpringBoot para simular um e-commerce simples.

## Pré-requisitos

Possuir o [Docker](https://www.docker.com/) em execução na máquina.

Estar com a `porta 8080` da máquina liberada.

## Instalação

Após o `git clone`, execute o comando abaixo na pasta raiz do projeto.

```bash
docker-compose up --build
```

A execução do comando fará o deploy da aplicação a partir de uma imagem docker.

Ao término, a API estará disponível em: 

```bash
localhost:8080
```

É importante saber que nenhum dos recursos está automaticamente liberado. Para acessá-los é necessário passar um `accessToken` válido.

## Obtendo um accessToken

- Abra o [Postman](https://www.postman.com/)¹, inicie uma nova requisição e cole a URL abaixo na barra de endereços.

```bash
http://localhost:8080/oauth/token
```

- Escolha o método http `POST`

- Na aba `Authorization` especifique o `Type` como `BasicAuth`

- No campo `Username` passe o valor `rsi-app`

- No campo `Password` passe o valor `r$s&i0#`

- Vá para a aba `Body` (da requisição), escolha a opção `x-www-form-urlencoded` e crie as variáveis com seus respectivos valores conforme detalhado abaixo.

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`client` : `rsi-app`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`username` : `admin@rsi.com.br`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`password` : `admin`

&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;`grant_type` : `password`

- Clique em `Send` e observe o body de retorno.

- O token estará contido na propriedade `"access_token"`. Lembre-se que você terá um (1) minuto exatamente para utilizar esta chave nas próximas requisições.


## Requisições em recursos da API

Uma vez que a aplicação está no ar, é possível obter a documentação Swagger da API através da URL abaixo e montar suas próprias requisições.

```bash
http://localhost:8080/swagger-ui.html
```

Caso não deseje montar requisição por requisição, utilize as requests já prontas para esta API, colando o endereço abaixo no import do Postman (File>Import>Link>URL) .

```bash
https://www.getpostman.com/collections/025846c2604a26adb701
```

Para fazer, por exemplo, uma consulta de Produtos, dirija-se a aba `Authentication` da requisição, escolha o `Type Baerer Token` e cole o `accessToken` obtido através do passo anterior no campo exibido. As demais requests devem seguir este mesmo fluxo, até o accessToken expirar e ser necessário fazer uma nova requisição para obter nova chave.

## Refresh Token

Pensando em consumação da API por aplicações terceiras, é possível recuperar também um `refreshToken` após a requisição de um `accessToken`; seu valor estará em um Cookie retornado por esta requisição, podendo ser passado como parâmetro para obtenção de um novo accessToken sem a necessidade de inserir parâmetros de autenticação à nível de usuário (como os contidos no request-body) em todas as requisições.  

## Algumas regras de negócio

- Uma atualização na lista de produtos poderá ser realizada somente para o carrinho, enquanto este estiver sendo criado (carrinho não foi implementado ainda).

- Uma vez que um pedido foi criado, seus produtos não podem ser mais atualizados.

- Se houve um erro na concepção de um pedido, no tocante a ter itens a mais ou a menos, o mesmo somente poderá ser cancelado.

- Um pedido deverá ter seu fluxo de atualização de status considerando que não pode haver by-pass. Deverá passar por todas as fases sequencialmente.

- A exceção do by-pass é o status "Cancelado"

- Um pedido não pode ser deletado do sistema, somente cancelado.

- Não existe desconto por item. Desconto pode ser aplicado exclusivamente no pedido.

## Considerações sobre a API

- O usuário `admin@rsi.com.br` possui privilégios para acessar todos os recursos (leitura e modificação), enquanto que o usuário `maria@rsi.com.br` só possui acesso à consultas.

- Para testar o acesso aos recursos com o usuário Maria, mude a variável `username` para `maria@rsi.com.br` na aba `Body` da requisição de um novo `accessToken`. Sua senha foi mantida como `admin`, portanto não é necessária alterá-la na requisição.

- Nas requisições de `CRIAÇÃO` de Pedidos, Produtos e Clientes não especifique o campo `id` destes objetos, apenas os campos `id` dos objetos contidos neles (Lista de produtos, Endereços, etc). O Swagger especifica em seus exemplos para passá-los como `0`, mas isto é errado. Estas propriedades serão removidas posteriormente. No mais, há uma exceção preparada para requisições com estes parâmetros passados indevidamente. 

## Considerações finais

O objetivo desta POC é provar algumas capacidades técnicas solicitadas pela empresa RSI. O foco não foi  apresentar regras de negócio robustas, mas sim fazer um bom uso dos frameworks e ferramentas de mercado para conceber a arquitetura do projeto da melhor forma possível, considerando também o prazo de entrega.

Thanks in advance.