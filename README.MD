# Skills projet

### Run project

```sh
export DOCKER_HOSTNAME=$(hostname)
export DOCKER_IP=$(hostname -i)

$ cd ./docker
$ docker-compose build
$ docker-compose up -d $@

$ cd ../
$ mvn clean install
$ java -jar ./target/skills.jar
```


### Start url

```
/app/index.html
```