
# Classy.

![Home](https://ouvidoria.jaboatao.pe.gov.br/storage/2020/01/placeholder.png)
### 🎓 A solução simples para sua turmas!
O Classy.  é um software para web de organização de cronograma desenvolvido pensando no gap que existe hoje em soluções simples e de fácil acesso para postagens de cronograma escolar, seja de sua faculdade ou turminha de inglês.

## ⚙️Features

 - Criação de turmas com um administrador.
 - Postagem/Edição/Exclusão de atividades e provas desta turma.
 - Cadastro de alunos que acessarão a turma.
 - Fórum para troca de mensagens e dúvidas.

## 🔨Build


A aplicação foi criada utilizando a linguagem **Java** para *web*, fazendo uso de página jsp que recebem dados das classes de controle com os dados resgatados pelas Classes DAO

As páginas criadas são **html** com estilização feita em **css** puro, sem processadores.

Uso de **Javascript** para a criação de algumas funções básicas de retorno em páginas de sucesso e erro.

Lib de **Jquery** chamada [Validation Plugin](https://jqueryvalidation.org) para fazer as validações em todos os forms da aplicação.



### 📖Banco de Dados
Para a parte de banco de dados da aplicação, se foi utilizado o banco PostgreSQL.

### 📐Arquitetura
***Modelo MVC* ->** Model (modelo) View (visão) e Controller (Controle) é um padrão de arquitetura que facilita a troca de informações entre a interface do usuário aos dados no banco, fazendo com que as respostas sejam mais rápidas e dinâmicas.

***Padrão DAO* ->** Classes DAO são responsáveis por trocar informações com o SGBD e fornecer operações CRUD e de pesquisas, são capazes de buscar dados no banco e transformar esses em objetos ou lista de objetos, fazendo uso de listas genéricas.










