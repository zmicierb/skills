FROM java:alpine

ADD target/skills.jar /home/web-service.jar
EXPOSE 8080

CMD java $_OPTIONS -jar /home/web-service.jar