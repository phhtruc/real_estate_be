# Sử dụng hình ảnh maven làm base image
FROM maven:3.8-openjdk-17-slim AS build

# Đặt thư mục làm việc trong container
WORKDIR /app

# Sao chép toàn bộ mã nguồn vào container
COPY . .

# Chạy lệnh mvn clean install để build dự án
RUN mvn clean install -DskipTests

# Sử dụng hình ảnh java cho ứng dụng production
FROM openjdk:17-jdk-slim

# Đặt thư mục làm việc trong container
WORKDIR /usr/app

# Sao chép file JAR từ container build sang container production
COPY --from=build /app/target/real_estate_be-0.0.1-SNAPSHOT.jar /usr/app/real_estate_be-0.0.1-SNAPSHOT.jar

# Mở cổng mà ứng dụng của bạn sẽ chạy
EXPOSE 8080

# Chạy ứng dụng Java
CMD ["java", "-jar", "real_estate_be-0.0.1-SNAPSHOT.jar"]