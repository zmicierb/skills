FROM java:alpine
RUN apk update && apk add postgresql-client postgresql-bdr

ADD target/skills.jar /home/web-service.jar
EXPOSE 8080

CMD /home/host-config/init_db.sh && java $_OPTIONS -jar /home/web-service.jar