# 🧪 API Testing with Kotlin
[![Build](https://github.com/michaelbrendo/api-testing-kotlin/actions/workflows/gradle.yml/badge.svg)](https://github.com/michaelbrendo/api-testing-kotlin/actions)
[![Tests](https://img.shields.io/badge/tests-passing-brightgreen.svg)](https://github.com/michaelbrendo/api-testing-kotlin/actions)
[![Allure Report](https://img.shields.io/badge/Allure-Report-blue?logo=allure)](https://michaelbrendo.github.io/api-testing-kotlin/index.html)
[![License: MIT](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)


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

## Setup VS Code / IntelliJ
To open the project in VS Code with WSL and ensure imports work:

- Configure the Workspace JDK to JavaSE-17.
- Install the following VS Code extensions (Remote WSL):
  - Extension Pack for Java (Microsoft)
  - Kotlin (the extension that works best for your setup)
- Run Gradle: Refresh Gradle Project to load dependencies.
> ⚠️ Note: Navigation, refactoring, and full Kotlin support work better in IntelliJ IDEA.

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

## License
This project is licensed under the MIT License — see the [LICENSE](./LICENSE) file for details.
