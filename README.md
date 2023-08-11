# many-to-one-bidirecional

Repositório de testes com o Legado Hibernate 3, Java 6, HSQLDB com fins de realizar testes no mapeamento das entidades.

É necessário prover um banco de dados em MYSQL, para tanto, pode-se optar pelo docker usando o compose, conforme abaixo, para
prover o banco para testes, conforme configuração em src/main/resources/hibernate.cfg.xml:

1. Instalar Docker;
2. Instalar Docker compose.

```yml
version: "3.6"
services:
  mysql:
    image: mysql:5.7
    container_name: mysqldb
    restart: always
    environment:
      MYSQL_DATABASE: 'db'
      MYSQL_USER: 'mysql-user'
      MYSQL_PASSWORD: 'mysql-password'
      MYSQL_ROOT_PASSWORD: 'root-password'
    ports:
      - "3306:3306"
    expose:
      - "3306"
    volumes:
      - "~/Documentos/Desenv/docker/volumes:/var/lib/mysql"
networks:
  mysql:
    driver: bridge
```
