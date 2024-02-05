-- mongo db annonce etat : -10 supprimer ,-5 refus ,0 creer,5 accepter,10 vendu

CREATE TABLE pays
(
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL,
    etat INTEGER
);

CREATE TABLE marque
(
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL,
    idpays INTEGER,
    etat INTEGER,
    FOREIGN KEY(idpays) REFERENCES pays(id)
);

CREATE TABLE categorie
(
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL,
    etat INTEGER
);

CREATE TABLE types
(
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL,
    etat INTEGER
);

CREATE TABLE carburant
(
    id SERIAL PRIMARY KEY,
    libelle VARCHAR(50) NOT NULL,
    etat INTEGER
);

CREATE TABLE utilisateur
(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100),
    prenom VARCHAR(100),
    email VARCHAR(100),
    dtn DATE,
    mdp VARCHAR(100),
    isAdmin INTEGER,
    actif INTEGER
);

CREATE TABLE commission
(
    id SERIAL PRIMARY KEY,
    min DECIMAL,
    max DECIMAL,
    valeur DECIMAL,
    dates DATE,
    etat INTEGER
);

CREATE TABLE historique
(
    id SERIAL PRIMARY KEY,
    idvendeur INTEGER,
    idacheteur INTEGER,
    idannonce VARCHAR(255),
    commission DECIMAL,
    -- tsy pourcentage intsony fa efa le valeur
    date TIMESTAMP,
    FOREIGN KEY(idvendeur) REFERENCES utilisateur(id),
    FOREIGN KEY(idacheteur) REFERENCES utilisateur(id)
);

