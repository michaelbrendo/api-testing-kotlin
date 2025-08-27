# 🧪 API Testing with Kotlin

Automated API testing project using Kotlin, Spring Boot, Rest-Assured, and JUnit.

---

## 🚀 How to Run Tests
```bash
./gradlew test
```

## Requirements
- [Java 17](https://adoptium.net/en-GB/temurin/releases/?version=17) — OpenJDK distribution from Adoptium
- [Gradle](https://gradle.org/install/) — Build automation tool
- [WSL2 with Ubuntu](https://learn.microsoft.com/en-us/windows/wsl/install) — Windows Subsystem for Linux (for Windows users)

## APIs Used
This project interacts with public REST APIs for testing purposes:
- JSONPlaceholder REST API — provides mock data like posts, comments, users, etc.
- JSONPlaceholder.org — visual interface for testing endpoints (requires cookies enabled)

## Technologies & Dependencies
- Kotlin 1.9.24
- Spring Boot 3.3.0
- JUnit 4 + JUnit Platform
- Rest-Assured with Kotlin Extensions
- Logback + SLF4J for logging
- Tomcat (provided runtime)