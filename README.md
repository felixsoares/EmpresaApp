
# README #

Estes documento README tem como objetivo fornecer as informações sobre o projeto desenvolvido.

### SOBRE O PROJETO ###

O escopo do projeto consistia em criar um aplicativo Android nativo que consumisse uma API específica. O projeto foi desenvolvido todo em Kotlin utilizando a arquitetura MVP.

### INSTALAÇÃO ###

Baixe o repositório

```
git clone git clone https://github.com/felixsoares/EmpresaApp.git
cd empresas-android
```

Abra o [Android Studio, importe o projeto e execute](https://www.youtube.com/watch?v=70PvCIIejvg) em um emulador ou dispositivo que preferir. O aplicativo está com o nome Ioasys e ícone padrão de um projeto novo gerado pelo Android Studio.

Usuário para login:

* email: testeapple@empresa.com.br
* senha: 12341234

Nomes das empresas disponíveis para pesquisa:

* Empresa 1
* Empresa 2
* Your Company

### INFORMAÇÕES DO PROJETO ###

* Versão mínima suportada: 21
* Linguagem utilizada: Kotlin
* Testes realizados: Unitários

#### Bibliotecas utilizadas ####

Nome| Objetivo
--------- | ------
Rx (Java, Kotlin, Android, Adapter)     | Criar requisições assíncronas, gerenciar e tratar itens de forma reativa
Material Design    | Prover componentes visuais de acordo com o design system proposto pela Google
Retrofit    | Realizar consumo de dados REST
Gson  | Desserializador de dados
Koin    | Usado para criar injeções de dependência
Mockk  | Utilizado para criar objetos e retorno de funções mocadas (retornos simulados)
Glide    | Utilizado para download e cache de imagens
Junit  | Utilizado para criar testes unitários e asserções de dados

#### Testes unitários ####

Os teste unitários foram feitos utilizando Junit, pois é a biblioteca padrão ao se criar um projeto Android do zero. Essa ferramenta provê o uso de asserções de dado que é essencial para validação de dados e regras de negócio. Além disso foi utilizado também o Mockk para criar retornos simulados e verificações de chamadas de métodos.

### CONSIDERAÇÕES FINAIS ###

O projeto teve boas implementações, mas como o tempo proposto foi relativamente pouco ficaram algumas pontas soltas como:

* Tratar melhor as exceções e possíveis erros geradas pelas requisições
* Tratar um possível vencimento do accesstoken
* Deixar a `SearchView` no mesmo visual proposto no design
* Criar animações para melhorar a experiência do usuário
* Criar um `presenter` para tela de detalhe da empresa
* Testar a tela de detalhamento
* Criar testes de `UI`
* Padronizar tamanhos de fontes e espaçamentos
* Testar e mocar a classe de `SharedPreferences`

Link para [API desenvolvida](https://github.com/felixsoares/EmpresasAPI)
