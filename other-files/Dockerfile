# 添加 Java 8 镜像来源
FROM java:8
COPY target/*.jar /app.jar

CMD ["--server.port=8080"]

EXPOSE 8080

ENTRYPOINT ["java","-jar","/app.jar"]