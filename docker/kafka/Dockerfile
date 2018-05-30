FROM java:8

# Переменные
ENV KAFKA_VERSION="0.10.2.1" SCALA_VERSION="2.11"
ENV KAFKA_HOME /opt/kafka_${SCALA_VERSION}-${KAFKA_VERSION}

# Скачиваем кафку
RUN wget -q http://apache.mirrors.spacedump.net/kafka/"$KAFKA_VERSION"/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz -O /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz && \
    tar xfz /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz -C /opt && \
    rm /tmp/kafka_"$SCALA_VERSION"-"$KAFKA_VERSION".tgz

# Команда запуска
ADD start-kafka.sh /usr/bin/start-kafka.sh
RUN chmod a+x /usr/bin/start-kafka.sh
CMD ["start-kafka.sh"]

# Выставляем порт
EXPOSE 9092
