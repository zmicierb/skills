# Skills projet

### Run project

```sh
export DOCKER_HOSTNAME=$(hostname)
export DOCKER_IP=$(hostname -i)

$ cd ./docker
$ docker-compose build
$ docker-compose up -d $@

$ mvn clean install
$ java -jar ../i18n-service/i18n-service-rest/target/i18n-service.jar
$ java -jar ../mail-service/mail-service-rest/target/mail-service.jar
$ java -jar ../web-service/target/skills.jar
```

TODO value placeholder

### Start url

```
/app/index.html
```