CREATE TABLE categorias (
                            id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                            nombre VARCHAR(100) NOT NULL,
                            descricao VARCHAR(100)
);