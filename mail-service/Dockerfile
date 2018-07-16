FROM java:alpine
RUN apk update && apk add postgresql-client postgresql-bdr

ADD mail-service-rest/target/mail-service.jar /home/mail-service.jar
EXPOSE 8080

CMD /home/host-config/init_db.sh && java $_OPTIONS -jar /home/mail-service.jar