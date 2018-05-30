FROM java:8

# Переменные
ENV ZOOKEEPER_VERSION 3.4.10
ENV ZK_HOME /opt/zookeeper-${ZOOKEEPER_VERSION}

# Скачиваем zookeeper
RUN wget -q http://mirror.vorboss.net/apache/zookeeper/zookeeper-${ZOOKEEPER_VERSION}/zookeeper-${ZOOKEEPER_VERSION}.tar.gz && \
wget -q https://www.apache.org/dist/zookeeper/KEYS && \
wget -q https://www.apache.org/dist/zookeeper/zookeeper-${ZOOKEEPER_VERSION}/zookeeper-${ZOOKEEPER_VERSION}.tar.gz.asc && \
wget -q https://www.apache.org/dist/zookeeper/zookeeper-${ZOOKEEPER_VERSION}/zookeeper-${ZOOKEEPER_VERSION}.tar.gz.md5

# Проверяем md5
RUN md5sum -c zookeeper-${ZOOKEEPER_VERSION}.tar.gz.md5 && \
gpg --import KEYS && \
gpg --verify zookeeper-${ZOOKEEPER_VERSION}.tar.gz.asc

# Устанавливаем
RUN tar -xzf zookeeper-${ZOOKEEPER_VERSION}.tar.gz -C /opt

# Конфигурируем
RUN mv /opt/zookeeper-${ZOOKEEPER_VERSION}/conf/zoo_sample.cfg /opt/zookeeper-${ZOOKEEPER_VERSION}/conf/zoo.cfg
RUN sed  -i "s|/tmp/zookeeper|$ZK_HOME/data|g" $ZK_HOME/conf/zoo.cfg; mkdir $ZK_HOME/data

# Команда запуска
ADD start-zk.sh /usr/bin/start-zk.sh
RUN chmod a+x /usr/bin/start-zk.sh
CMD ["start-zk.sh"]

# Выставляем порт
EXPOSE 2181

