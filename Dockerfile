# Build stage
FROM gradle:8.13.0-jdk21 AS build
WORKDIR /app

# 設定 Gradle 環境變數
ENV GRADLE_OPTS="-Dorg.gradle.daemon=false -Dorg.gradle.parallel=true -Dorg.gradle.jvmargs=-Xmx2048m"

# 複製 Gradle 配置文件
COPY gradle ./gradle
COPY build.gradle settings.gradle ./

# 下載依賴項
RUN gradle dependencies --no-daemon

# 複製源碼
COPY src ./src

# 執行建置
RUN gradle clean build --no-daemon -x test --info \
    || (echo "Build failed. Check the logs above." && exit 1)

# Run stage
FROM openjdk:21-slim
WORKDIR /app

# 設定 Java 運行參數
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

# 複製建置產物（注意：Gradle 建置產物在 build/libs 目錄下）
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java ${JAVA_OPTS} -jar app.jar"]

