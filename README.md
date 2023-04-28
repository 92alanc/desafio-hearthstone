# Desafio Hearthstone
Por Alan Camargo

## Resumo
O projeto é um desafio técnico para listar numa tela as diferentes categorias de cartas do jogo
Hearthstone e usá-las como filtros para a exibição das cartas numa outra tela.

## Arquitetura
A arquitetura escolhida foi a MVVM, também usando MVI, clean architecture e os princípios SOLID.

O app está modularizado por funcionalidade.

### Módulos
- app
Módulo principal do app. Somente executa e gerencia os demais.

- core
Ferramentas essenciais para o funcionamento do app como logger, provedor de API, provedor de
banco de dados, etc.

- core-data
Entidades da camada de dados utilizadas (ou potencialmente utilizadas) por um ou mais módulos.
Contém também mapeamentos para seus equivalentes na camada de domínio.

- core-design
Recursos e ferramentas de UI utilizados no app inteiro.

- core-domain
Entidades da camada de domínio utilizadas (ou potencialmente utilizadas) por um ou mais módulo.
Este módulo não deve conter nenhuma dependência externa.

- core-test
Ferramentas de teste unitário e de UI utilizadas no app inteiro.

- feature-cards
Funcionalidade de exibição de cartas e suas dependências internas.

- feature-filters
Funcionalidade de listagem de filtros e suas dependências internas.

- navigation
Interfaces de navegação entre módulos.

## Testes

### Testes unitários
Todas as camadas dos módulos de funcionalidade estão cobertas por testes unitários validando 
diferentes cenários.

### Testes de UI
Devido ao tempo somente o módulo `feature-filters` tem testes de UI e nem todos estão passando, 
porém eles estão estruturados de acordo com o padrão Robots, o que facilita na leitura e 
mitigação de possíveis problemas.
