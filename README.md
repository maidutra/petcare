## PetCare — Sistema de Agendamento e Gestão para Petshops

O **PetCare** é uma plataforma web desenvolvida para automatizar o fluxo de agendamentos e otimizar o gerenciamento integrado da rotina operacional de um petshop. O sistema substitui processos manuais por uma jornada digital centralizada, eliminando conflitos de horários na agenda do estabelecimento.

---

## Funcionalidades Principais

### Módulo do Cliente (Tutor)
* **Cadastro e Autenticação:** Criação de conta e login para acesso à área do cliente.
* **Gestão de Pets:** Cadastro e visualização de animais vinculados ao perfil do tutor (nome, espécie, raça e porte).
* **Agendamento Online:** Solicitação de serviços de banho e tosa com escolha de data e horário em tempo real.
* **Serviço de TáxiDog:** Opção integrada para solicitar a busca e entrega do pet no endereço residencial informado.
* **Histórico Particular:** Acompanhamento do status atualizado de cada serviço solicitado (Pendente, Em Andamento, Concluído).

### Módulo do Administrador (Equipe do Petshop)
* **Dashboard Gerencial:** Painel com métricas de faturamento previsto, total de atendimentos diários e clientes ativos.
* **Controle da Agenda do Dia:** Visualização e atualização dinâmica do status dos atendimentos e das rotas de busca (TáxiDog).
* **Consulta da Base de Dados:** Módulo centralizado para visualizar todos os tutores e pets cadastrados.
* **Histórico Geral:** Relatório cronológico e retroativo de todas as operações do estabelecimento para auditoria.

---

## Tecnologias Utilizadas

O ecossistema técnico do projeto foi desenhado sob o paradigma da orientação a objetos e arquitetura em camadas (MVC):

* **Backend:** Java 21 / Spring Boot
* **Banco de Dados:** PostgreSQL / Spring Data JPA
* **Frontend:** HTML5, CSS3 eThymeleaf 

---

## Para Executar o Projeto Localmente

### Pré-requisitos
* Java JDK 21 instalado
* PostgreSQL configurado e em execução
* Maven (ou uso do wrapper `./mvnw`)

### Passo a Passo

1. **Clonar o repositório:**
```bash
   git clone [https://github.com/mairadutra/petcare.git](https://github.com/mairadutra/petcare.git)
   cd petcare

Configurar o Banco de Dados:
Abra o arquivo src/main/resources/application.properties e insira suas credenciais locais do PostgreSQL:

Properties
   spring.datasource.url=jdbc:postgresql://localhost:5405/petcare_db
   spring.datasource.username=seu_usuario
   spring.datasource.password=sua_senha
   spring.jpa.hibernate.ddl-auto=update

Compilar e Executar a Aplicação:

Bash
   ./mvnw spring-boot:run
Acessar a aplicação:
Abra o navegador e acesse: http://localhost:8080

✒️ Autores
Maira Luiza Ferreira Dutra — Desenvolvimento Backend e Banco de Dados.
Beatriz Borges Ribeiro — Co-criadora, Design de UI/UX e Prototipagem.
