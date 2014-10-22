CREATE TABLE row_article(
  id int NOT NULL PRIMARY KEY,
  paper_title varchar(255),
  year int,
  publication_venue varchar(255),
  abstract text
);

CREATE TABLE references(
  id int NOT NULL PRIMARY KEY,
  reference_id int NOT NULL,
  FOREIGN KEY (id) REFERENCES row_article(id)
);

CREATE TABLE test_article_set(
  id int NOT NULL PRIMARY KEY,
  paper_title varchar(255),
  year int,
  publication_venue varchar(255),
  abstract text
);
