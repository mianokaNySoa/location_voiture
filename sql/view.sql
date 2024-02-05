CREATE VIEW vw_stat_commission AS
SELECT
    ROW_NUMBER() OVER () as id,
    EXTRACT(YEAR FROM date) AS annee,
    EXTRACT(MONTH FROM date) AS mois,
    SUM(commission) AS total
FROM historique
GROUP BY 
    annee,mois
ORDER BY
    annee,mois;

CREATE VIEW vw_nb_vente AS
SELECT
    ROW_NUMBER() OVER () as id,
    EXTRACT(YEAR FROM date) AS annee,
    EXTRACT(MONTH FROM date) AS mois,
    COUNT(commission) AS nombre
FROM historique
GROUP BY 
    annee,mois
ORDER BY
    annee,mois;