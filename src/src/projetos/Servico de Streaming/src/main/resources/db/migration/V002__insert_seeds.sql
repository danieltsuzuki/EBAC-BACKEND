-- -- Filmes age_rating = 0 (Livre), duration entre 0 e 10
-- INSERT INTO films (id, title, age_rating, genre, plan_type, duration, created_at) VALUES
-- (gen_random_uuid(), 'A Aventura do Ursinho', 0, 'Animação', 1, 3, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'O Mundo Mágico das Cores', 0, 'Infantil', 1, 2, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Amigos da Floresta', 0, 'Aventura', 1, 4, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Cantando com os Animais', 0, 'Musical', 2, 1, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'O Pequeno Explorador', 0, 'Educativo', 3, 5, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Histórias da Vovó', 0, 'Família', 1, 2, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Brincando no Parque', 0, 'Infantil', 1, 3, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'A Festa dos Bichinhos', 0, 'Animação', 3, 4, CURRENT_TIMESTAMP),
--
-- (gen_random_uuid(), 'Viagem ao Fundo do Mar', 0, 'Aventura', 3, 6, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'O Reino Encantado', 0, 'Fantasia', 2, 7, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Descobrindo o Espaço', 0, 'Ficção Científica', 1, 5, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'A Grande Corrida', 0, 'Esporte', 1, 6, CURRENT_TIMESTAMP),
--
-- (gen_random_uuid(), 'A Jornada dos Heróis Mirins', 0, 'Aventura', 3, 9, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Contos de Fadas Modernos', 0, 'Fantasia', 2, 8, CURRENT_TIMESTAMP),
--
-- -- Filmes age_rating = 12, duration entre 0 e 10
-- (gen_random_uuid(), 'Mistério na Escola', 12, 'Suspense', 1, 2, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Os Jovens Detetives', 12, 'Aventura', 1, 3, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Corrida Contra o Tempo', 12, 'Ação', 3, 4, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'A Casa Assombrada', 12, 'Terror', 2, 1, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Amizade Verdadeira', 12, 'Drama', 1, 2, CURRENT_TIMESTAMP),
--
-- (gen_random_uuid(), 'O Segredo do Laboratório', 12, 'Ficção Científica', 3, 6, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Aventura nas Montanhas', 12, 'Aventura', 2, 5, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'A Última Batalha', 12, 'Ação', 3, 7, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Código Secreto', 12, 'Suspense', 1, 4, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'O Torneio de Campeões', 12, 'Esporte', 1, 5, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Viagem no Tempo', 12, 'Ficção Científica', 3, 6, CURRENT_TIMESTAMP),
--
-- (gen_random_uuid(), 'A Saga dos Guerreiros', 12, 'Fantasia', 3, 9, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Expedição ao Desconhecido', 12, 'Aventura', 2, 8, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'O Reino Perdido', 12, 'Fantasia', 3, 10, CURRENT_TIMESTAMP),
--
-- -- Filmes age_rating = 16, duration entre 0 e 10
-- (gen_random_uuid(), 'Noite de Crime', 16, 'Suspense', 1, 3, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'O Assassino Silencioso', 16, 'Terror', 3, 2, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Vingança Pessoal', 16, 'Ação', 2, 4, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Amor Proibido', 16, 'Romance', 1, 3, CURRENT_TIMESTAMP),
--
-- (gen_random_uuid(), 'A Conspiração', 16, 'Suspense', 3, 6, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Operação Resgate', 16, 'Ação', 3, 7, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'O Sequestro', 16, 'Thriller', 2, 5, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Cidade do Medo', 16, 'Terror', 1, 6, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Traição Mortal', 16, 'Drama', 1, 5, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'A Última Testemunha', 16, 'Suspense', 3, 6, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Fuga Impossível', 16, 'Ação', 2, 7, CURRENT_TIMESTAMP),
--
-- (gen_random_uuid(), 'O Império do Crime', 16, 'Crime', 3, 9, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Guerra nas Ruas', 16, 'Ação', 3, 10, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'A Queda do Reino', 16, 'Drama', 2, 8, CURRENT_TIMESTAMP),
--
-- -- Filmes age_rating = 18, duration entre 0 e 10
-- (gen_random_uuid(), 'Violência Urbana', 18, 'Crime', 3, 3, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'O Cartel', 18, 'Ação', 3, 4, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Noite Sangrenta', 18, 'Terror', 2, 2, CURRENT_TIMESTAMP),
--
-- (gen_random_uuid(), 'O Massacre', 18, 'Terror', 3, 6, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Império das Drogas', 18, 'Crime', 3, 7, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Assassino em Série', 18, 'Thriller', 3, 6, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Vingança Brutal', 18, 'Ação', 2, 5, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'A Casa dos Horrores', 18, 'Terror', 3, 6, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Submundo do Crime', 18, 'Crime', 2, 7, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Caçador de Almas', 18, 'Terror', 3, 6, CURRENT_TIMESTAMP),
--
-- (gen_random_uuid(), 'A Máfia', 18, 'Crime', 3, 9, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Guerra Total', 18, 'Ação', 3, 10, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'O Inferno na Terra', 18, 'Terror', 3, 8, CURRENT_TIMESTAMP),
-- (gen_random_uuid(), 'Império da Violência', 18, 'Crime', 3, 10, CURRENT_TIMESTAMP);


-- Filmes age_rating = 0 (Livre), duration entre 0 e 10
INSERT INTO films (id, title, age_rating, genre, plan_type, duration, created_at) VALUES
(gen_random_uuid(), 'A Aventura do Ursinho', 0, 'Animação', 0, 3, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'O Mundo Mágico das Cores', 0, 'Infantil', 0, 2, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Amigos da Floresta', 0, 'Aventura', 0, 4, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Cantando com os Animais', 0, 'Musical', 1, 1, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'O Pequeno Explorador', 0, 'Educativo', 2, 5, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Histórias da Vovó', 0, 'Família', 0, 2, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Brincando no Parque', 0, 'Infantil', 0, 3, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'A Festa dos Bichinhos', 0, 'Animação', 2, 4, CURRENT_TIMESTAMP),

(gen_random_uuid(), 'Viagem ao Fundo do Mar', 0, 'Aventura', 2, 6, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'O Reino Encantado', 0, 'Fantasia', 1, 7, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Descobrindo o Espaço', 0, 'Ficção Científica', 0, 5, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'A Grande Corrida', 0, 'Esporte', 0, 6, CURRENT_TIMESTAMP),

(gen_random_uuid(), 'A Jornada dos Heróis Mirins', 0, 'Aventura', 2, 9, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Contos de Fadas Modernos', 0, 'Fantasia', 1, 8, CURRENT_TIMESTAMP),

-- Filmes age_rating = 12, duration entre 0 e 10
(gen_random_uuid(), 'Mistério na Escola', 12, 'Suspense', 0, 2, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Os Jovens Detetives', 12, 'Aventura', 0, 3, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Corrida Contra o Tempo', 12, 'Ação', 2, 4, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'A Casa Assombrada', 12, 'Terror', 1, 1, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Amizade Verdadeira', 12, 'Drama', 0, 2, CURRENT_TIMESTAMP),

(gen_random_uuid(), 'O Segredo do Laboratório', 12, 'Ficção Científica', 2, 6, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Aventura nas Montanhas', 12, 'Aventura', 1, 5, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'A Última Batalha', 12, 'Ação', 2, 7, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Código Secreto', 12, 'Suspense', 0, 4, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'O Torneio de Campeões', 12, 'Esporte', 0, 5, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Viagem no Tempo', 12, 'Ficção Científica', 2, 6, CURRENT_TIMESTAMP),

(gen_random_uuid(), 'A Saga dos Guerreiros', 12, 'Fantasia', 2, 9, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Expedição ao Desconhecido', 12, 'Aventura', 1, 8, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'O Reino Perdido', 12, 'Fantasia', 2, 10, CURRENT_TIMESTAMP),

-- Filmes age_rating = 16, duration entre 0 e 10
(gen_random_uuid(), 'Noite de Crime', 16, 'Suspense', 0, 3, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'O Assassino Silencioso', 16, 'Terror', 2, 2, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Vingança Pessoal', 16, 'Ação', 1, 4, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Amor Proibido', 16, 'Romance', 0, 3, CURRENT_TIMESTAMP),

(gen_random_uuid(), 'A Conspiração', 16, 'Suspense', 2, 6, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Operação Resgate', 16, 'Ação', 2, 7, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'O Sequestro', 16, 'Thriller', 1, 5, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Cidade do Medo', 16, 'Terror', 0, 6, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Traição Mortal', 16, 'Drama', 0, 5, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'A Última Testemunha', 16, 'Suspense', 2, 6, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Fuga Impossível', 16, 'Ação', 1, 7, CURRENT_TIMESTAMP),

(gen_random_uuid(), 'O Império do Crime', 16, 'Crime', 2, 9, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Guerra nas Ruas', 16, 'Ação', 2, 10, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'A Queda do Reino', 16, 'Drama', 1, 8, CURRENT_TIMESTAMP),

-- Filmes age_rating = 18, duration entre 0 e 10
(gen_random_uuid(), 'Violência Urbana', 18, 'Crime', 2, 3, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'O Cartel', 18, 'Ação', 2, 4, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Noite Sangrenta', 18, 'Terror', 1, 2, CURRENT_TIMESTAMP),

(gen_random_uuid(), 'O Massacre', 18, 'Terror', 2, 6, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Império das Drogas', 18, 'Crime', 2, 7, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Assassino em Série', 18, 'Thriller', 2, 6, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Vingança Brutal', 18, 'Ação', 1, 5, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'A Casa dos Horrores', 18, 'Terror', 2, 6, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Submundo do Crime', 18, 'Crime', 1, 7, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Caçador de Almas', 18, 'Terror', 2, 6, CURRENT_TIMESTAMP),

(gen_random_uuid(), 'A Máfia', 18, 'Crime', 2, 9, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Guerra Total', 18, 'Ação', 2, 10, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'O Inferno na Terra', 18, 'Terror', 2, 8, CURRENT_TIMESTAMP),
(gen_random_uuid(), 'Império da Violência', 18, 'Crime', 2, 10, CURRENT_TIMESTAMP);