# 使用官方 OpenJDK 17 映像作为基础镜像
FROM openjdk:17.0.1-jdk-oracle

#日期问题，8小时差解决
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone

ADD ./target/components_service-0.0.1.jar /app.jar

# 设置容器启动时执行的命令
ENTRYPOINT ["java","-jar","/app.jar"]