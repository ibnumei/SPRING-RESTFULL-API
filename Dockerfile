# Menggunakan base image OpenJDK
FROM openjdk:11-jre-slim

# Buat direktori aplikasi di dalam container
RUN mkdir /app

# Copy JAR file ke dalam container
COPY target/MsJavaMicroResTfullApiApplication.jar /app/MsJavaMicroResTfullApiApplication.jar

# Set working directory
WORKDIR /app

# Eksekusi aplikasi saat container dijalankan
CMD ["java", "-jar", "MsJavaMicroResTfullApiApplication.jar"]