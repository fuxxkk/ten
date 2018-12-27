FROM java:8
MAINTAINER ljm
RUN /bin/cp /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ADD distribution-system-1.0.jar /root/docker/container/
CMD ["java","-jar","/root/docker/container/distribution-system.jar"]

EXPOSE 8080
