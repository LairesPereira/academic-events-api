# Projeto III Semana Acadêmica de Ciência da Computação (Faculdade Católica da Paraíba)

Projeto sendo feito por um time de estudantes da Faculdade Catolica da Paraíba, onde ele servirá para um Projeto de um Evento do Curso de Ciência da Computação que durará uma semana, do dia 04/11 a 08/11. Onde nele ocorrerá uma mesa redonda com Especialistas dá área, 
também irá contar com minicursos. Esse projeto será uma API que contará com Página WEB, Backend, Banco de Dados Relacional, entre outras tecnologias. Contaremos com algumas etapas que irá ser citadas e implementadas de acordo com o tempo.

## Etapas

### Levantamento de Requisitos

  **O curso de Ciência da Computação, da Faculdade Católica da Paraíba, vai realizar o evento da *III Semana Acadêmica de Ciência da Computação*, que ocorrerá dos dias 4 à 8 de novembro. Com isso, foi pedido aos alunos de BD1 de 2024.2 para criarem o site onde será realizada a gestão, venda e esclarecimentos sobre o evento.**

  **O evento deve contar com administradores para gerenciá-lo, assim tendo acesso a todas as funcionalidades do site, o administrador poderá adicionar, editar e excluir minicursos. O evento também deve contar com Professores, que irão ministrar os minicursos, onde um ou mais professores poderão ministrar um minicurso para vários participantes.**
  
  **Cada minicurso terá duração de quatro dias, e cada participante irá receber um certificado de conclusão ao final do minicurso. Cada minicurso vai contar com atividades que serão deferidas pelos professores que irão ministrar o minicurso. Cada minicurso também irá contar com uma lista de presença, onde será feito o check-in/credenciamento de cada participante.**
  
  **Também contaremos com os participantes do evento, que poderão se inscrever no evento e em suas atividades, além de participar da palestra de abertura, que será apresentada por diversos palestrantes. Cada participante poderá se inscrever em apenas um minicurso, pois os minicursos ocorrerão no mesmo horário. Cada participante também deverá confirmar sua inscrição no evento, sua confirmação será recebida por um administrador.**
  
  **A palestra de abertura vai receber os palestrantes, patrocinadores e vai orientar como vai funcionar os minicursos e suas atividades.**
  
  - **O evento conta com os seguintes dados armazenados: código, instituição, endereço ( rua, número, bairro, cidade, estado), e data de início e término ;**
  - **Os administradores são cadastrados com os seguintes dados: cpf, nome, emails, telefones, endereço(rua, número, bairro, cidade, estado);**
  - **Os minicursos vão possuir código, título, descrição, dataInicio, dataFim, status e quantidade de participantes;**
  - **A lista de presença vai ser registrada com um código;**
  - **O certificado vai possuir número, nome, carga horária, e minicurso;**
  - **Os participantes são localizados pelo seu cpf, nome, telefone, email e endereço(rua, número, bairro, cidade, estado);**
  - **Os professores serão cadastrados com cpf, nome, telefones, email e endereço(rua, número, bairro, cidade, estado);**

#### **Requisitos Funcionais :**

- **Requisitos administrador**

  **R1 - O Programa deve permitir o cadastro de um administrador** <br>
  **R2 - O administrador pode fazer CRUD de evento** <br>
  **R3 - Administrador pode fazer CRUD de minicursos dentro de um evento** <br>
  **R4 - O administrador pode encerrar um minicurso, após encerrado o minicurso os participantes devem receber seus certificados em seus perfis.** <br>
  **R5 - O professor pode excluir um participante de algum minicurso** <br>
  **R6 - O administrador pode encerrar as inscrições de um minicurso.** <br>
  **R7 - O administrador pode permitir o check in de um participante no evento** <br>
  **R8 - O administrador pode permitir o check in de um participante no mini curso**

- **Requisitos Participantes**

  **R1 - Os participantes podem criar uma conta** <br>
  **R2 - O participante pode se inscrever em N eventos** <br>
  **R3 - O participante só pode se inscrever em um minicurso para cada evento.** <br>
  **R4 - O participante pode visualizar os eventos e minicursos onde está inscrito** <br>
  **R5 - O participante faz check in no evento** <br>
  **R6 - O participante faz check in no minicurso**

- **Requisitos Professor**

  **R1 - O professor pode fazer login** <br>
  **R2 - O professor pode ver os eventos associados a ele** <br>
  **R3 - O professor pode ver os minicursos que irá ministrar** <br>
  **R4 - O professor pode ver uma lista de participantes de cada mini curso** <br>
  **R5 - O professor pode excluir um participante de algum minicurso**
  

#### **Modelo Conceitual (MER)**
    
![MER](https://github.com/user-attachments/assets/f39f3d75-c9e7-4045-bcf7-7ee532ae0834)


#### **Modelo Relacional (MR)**
  Informações: Palavras em Negrito: <br>
  Chave Primária (Primary Key) <br> 
  Palavras Sublinhadas: Chave Estrangeira (Foreign Key)
	
  - Administrador(CPF, Nome, Rua, Número, Bairro, Cidade,  Estado);
  - TelefoneAdministrador(CPF_Administrador, telefone);
  - EmailAdministrador(CPF_Administrador, email);
  - Evento(Código, Código_Lista_Presença, Data_Inínio, Data_Término, Instituição, Rua,                Número, Bairro, Cidade, Estado);
  - Mini_curso(Código, Código_Lista_Presença, Título, Descrição, Data_Início, Data_Fim, Status, Quantidade_Participantes);
  - Administra(CPF_Administrador, Código_Evento, Código_Mini_curso);
  - Ministra_MC_Prof(Código_Mini_curso, CPF_Professor);
  - Professor(CPF, Nome, Email, Rua, Número, Bairro, Cidade, Estado);
  - TelefoneProfessor(CPF_Professor, telefone);
  - Participante(CPF, Código_Lista_Presença, Nome, telefone, Email, Rua, Número, Bairro, Cidade, Estado);
  - Lista_Presença(Código).
  - Participa(Código_Evento, CPF_Participante, Código_Mini_curso)
  - Certificado(Número, Código_Mini_Curso, CPF_Participante, Nome, Carga_Horária)

Documento contendo o levantamento de requisitos: [Levantamento de requisitos](https://docs.google.com/document/d/1Z4unOrwe4_CRkiPxMaOxHOgyZ-3G50BNHkAdOwTEQYM/edit?usp=sharing)

## Tecnologias Utilizadas

### Frontend
<img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/html5/html5-original.svg" width="40" height="40" title="HTML5" /> <img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/css3/css3-original.svg" width="40" height="40" title="CSS3" /> <img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/javascript/javascript-original.svg" width="40" height="40" title="JavaScript" /> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/bootstrap/bootstrap-original.svg" width="40" height="40" title="bootstrap"  />

### Backend
<img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/java/java-original.svg" width="40" height="40" title="Java" /> <img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/javascript/javascript-original.svg" width="40" height="40" title="JavaScript" /> <img src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/spring/spring-original.svg" width="40" height="40" title="springboot"  />
          

### Banco de Dados
<img loading="lazy" src="https://cdn.jsdelivr.net/gh/devicons/devicon@latest/icons/postgresql/postgresql-original.svg" width="40" height="40"/> 


## Time:
- [Dalina Dantas](https://github.com/dalina21)
- [Emilton Pereira](https://github.com/MiltogroDEV)
- [Felipe Freitas](https://github.com/FelipeFreitas-oliveira)
- [Giôvanni Bandeira](https://github.com/GiovanniBandeira)
- [Laires Pereira](https://github.com/LairesPereira)
- [Matheus Alves](https://github.com/mthy41)
