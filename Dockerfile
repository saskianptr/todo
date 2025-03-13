# Gunakan image OpenJDK sebagai base
FROM openjdk:17-jdk-slim

# Set working directory di dalam container
WORKDIR /app

# Copy semua file proyek ke dalam container
COPY . .

# Build aplikasi (karena belum ada .jar)
RUN ./mvnw clean package -DskipTests

# Jalankan aplikasi
CMD ["java", "-jar", "target/todo-0.0.1-SNAPSHOT.jar"]
