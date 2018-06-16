# Skills projet

### Run project

```sh
export DOCKER_HOSTNAME=$(hostname)
export DOCKER_IP=$(hostname -i)

$ cd ./docker
$ docker-compose build
$ docker-compose up -d $@

$ mvn clean install
$ java -jar -Dserver.port=8001 ../i18n-service/i18n-service-rest/target/i18n-service.jar
$ java -jar -Dservices.i18n.url=http://localhost:8001 -Dserver.port=8002 ../mail-service/mail-service-rest/target/mail-service.jar
$ java -jar -Dservices.mail.url=http://localhost:8002 ../web-service/target/skills.jar
```

### Start url

```
/app/index.html
```