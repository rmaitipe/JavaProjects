Docker Desktop install https://docs.docker.com/desktop
Docker install  https://docs.docker.com/engine/install/

To run Windows containers, you need Windows 10 or Windows 11 Professional or Enterprise edition.
Windows Home or Education editions only allow you to run Linux containers.

https://www.docker.com/blog/how-to-use-the-postgres-docker-official-image/
docker pull postgres

docker run --name some-postgres -e POSTGRES_PASSWORD=mysecretpassword -d postgres

docker run --name some-postgres -e POSTGRES_PASSWORD_FILE=/run/secrets/postgres-passwd -d postgres

Connect to your psql instance by executing: docker exec -it postgres bash
Login to your psql server: psql -U postgres
Run SQL.script contents.

https://www.docker.com/blog/how-to-use-the-alpine-docker-official-image/
docker pull alpine

FROM alpine:3.14
RUN apk add --no-cache mysql-client
ENTRYPOINT ["mysql"]


