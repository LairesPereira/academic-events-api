CREATE TABLE Administrador (
	CPF INT,
	Nome VARCHAR(100),
	Rua VARCHAR(100),
	Numero INT,
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),

	PRIMARY KEY(CPF)
);

CREATE TABLE TelefoneAdmin(
	CPF_Admin INT,
	Telefone INT,
	PRIMARY KEY(CPF_Admin, Telefone),
	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF)
);

CREATE TABLE EmailAdmin(
	CPF_Admin INT,
	Email VARCHAR(100),
	PRIMARY KEY(CPF_Admin, Email),
	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF)
);

CREATE TABLE Evento(
	Codigo INT,
	Codigo_ListaPresenca INT,
	DataInicio DATE,
	DataFim DATE,
	Instituicao VARCHAR(100),
	Rua VARCHAR(100),
	Numero INT,
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),

	PRIMARY KEY(Codigo)
);

CREATE TABLE MiniCurso(
	Codigo INT,
	Codigo_ListaPresenca INT,
	Titulo VARCHAR(100),
	Descricao TEXT,
	DataInicio DATE,
	DataFim DATE,
	Status BOOLEAN,
	QTDDParticipantes INT,

	PRIMARY KEY(Codigo)
);

CREATE TABLE Administra_Ev_MC(
	CPF_Admin INT,
	Codigo_Ev INT,
	Codigo_MC INT,

	PRIMARY KEY(CPF_Admin, Codigo_Ev, Codigo_MC),
	FOREIGN KEY(CPF_Admin) REFERENCES Administrador(CPF),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo)
);

CREATE TABLE Professor(
	CPF INT,
	Nome VARCHAR(100),
	Email VARCHAR(100),
	Rua VARCHAR(100),
	Numero INT,
	Bairro VARCHAR(100),
	Cidade VARCHAR(100),
	Estado VARCHAR(100),

	PRIMARY KEY(CPF)
);

CREATE TABLE TelefoneProf(
	CPF_Prof INT,
	Telefone INT,

	PRIMARY KEY(CPF_Prof, Telefone),
	FOREIGN KEY(CPF_Prof) REFERENCES Professor(CPF)
);

CREATE TABLE Ministra_MC_Prof(
	Codigo_MC INT,
	CPF_Prof INT,

	PRIMARY KEY(Codigo_MC, CPF_Prof),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo),
	FOREIGN KEY(CPF_Prof) REFERENCES Professor(CPF)
);

CREATE TABLE Participante(
	CPF INT,
	Codigo_ListaPresenca INT,
	Nome VARCHAR(100),
	Telefone INT,
	Email VARCHAR(100),
	Rua VARCHAR(100),
	Numero INT,
	 Bairro VARCHAR(100),
	 Cidade VARCHAR(100),
	 Estado VARCHAR(100),

	 PRIMARY KEY(CPF)
);

CREATE TABLE ListaPresenca(
	Codigo INT,

	PRIMARY KEY(Codigo)
);

ALTER TABLE Evento
ADD CONSTRAINT FK_Evento FOREIGN KEY(Codigo_ListaPresenca) REFERENCES ListaPresenca(Codigo);

ALTER TABLE MiniCurso
ADD CONSTRAINT FK_MiniCurso FOREIGN KEY(Codigo_ListaPresenca) REFERENCES ListaPresenca(Codigo);

ALTER TABLE Participante
ADD CONSTRAINT FK_Participante FOREIGN KEY(Codigo_ListaPresenca) REFERENCES ListaPresenca(Codigo);

CREATE TABLE Participa(
	Codigo_Ev INT,
	CPF_Participante INT,
	Codigo_MC INT,

	PRIMARY KEY(Codigo_Ev, CPF_Participante, Codigo_MC),
	FOREIGN KEY(Codigo_Ev) REFERENCES Evento(Codigo),
	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo)
);

CREATE TABLE Certificado(
	Numero INT,
	Codigo_MC INT,
	CPF_Participante INT,
	Nome VARCHAR(100),
	CargaHoraria TIME,

	PRIMARY KEY(Numero),
	FOREIGN KEY(Codigo_MC) REFERENCES MiniCurso(Codigo),
	FOREIGN KEY(CPF_Participante) REFERENCES Participante(CPF)
);