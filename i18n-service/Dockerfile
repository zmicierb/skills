FROM java:alpine
RUN apk update && apk add postgresql-client postgresql-bdr

ADD i18n-service-rest/target/i18n-service.jar /home/i18n-service.jar
EXPOSE 8080

CMD /home/host-config/init_db.sh && java $_OPTIONS -jar /home/i18n-service.jar