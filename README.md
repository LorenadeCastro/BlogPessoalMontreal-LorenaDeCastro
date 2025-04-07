Blog Pessoal - Projeto Montreal
Sistema backend para gerenciamento de usuários, postagens e temas. Projeto desenvolvido com foco em autenticação segura via JWT e operações CRUD completas para um blog pessoal.

📌 Funcionalidades
🔐 Usuário (/api/usuarios)
POST / – Cadastrar um novo usuário
PUT /{id} – Atualizar dados do usuário
DELETE /{id} – Excluir usuário
POST /login – Autenticar e gerar token JWT
📝 Postagem (/api/postagens)
POST / – Criar uma nova postagem
PUT /{id} – Atualizar uma postagem existente
DELETE /{id} – Excluir uma postagem
GET / – Listar todas as postagens
GET /filtro?autor={id}&tema={id} – Filtrar postagens por autor e/ou tema
🏷️ Tema (/api/temas)
POST / – Criar um novo tema
PUT /{id} – Atualizar um tema existente
DELETE /{id} – Excluir um tema
GET / – Listar todos os temas
⚙️ Tecnologias Utilizadas
Java 17+
Spring Boot
Spring Data JPA
Spring Security com JWT
MySQL
💻 Como Rodar o Projeto Localmente
Pré-requisitos
Java 17+
MySQL
IDE como Eclipse ou IntelliJ
Postman, Insomnia (opcional, para testes)
Passos
Clone este repositório:
git clone https://github.com/seuusuario/blog-pessoal-backend.git
📚 Documentação da API
Este projeto possui documentação interativa via Swagger:

📄 Swagger UI: http://localhost:8080/swagger-ui.html

Nessa interface, você pode:

Visualizar todos os endpoints disponíveis
Fazer requisições diretamente pela interface
Testar autenticação com JWT
About
No description, website, or topics provided.
Resources
 Readme
 Activity
Stars
 0 stars
Watchers
 1 watching
Forks
 0 forks
Report repository
Releases
No releases published
Packages
No packages published
Languages
Java
100.0%
Footer
© 2025 GitHub, In
