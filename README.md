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
Or use the provided Makefile for automation:

```bash
# Run tests
make test
```
```bash
# Full setup: install Allure, run tests, generate report and serve
make setup
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
- [JSONPlaceholder](https://jsonplaceholder.typicode.com/) REST API — provides mock data like posts, comments, users, etc.
- [JSONPlaceholder.org](https://jsonplaceholder.org/) — visual interface for testing endpoints (requires cookies enabled)

## 🛠 Makefile & Allure Report
- The Makefile automates test execution and report generation.
- Allure reports are generated in build/reports/allure-report.
- Serve the report locally with make serve-allure.
- Reports are automatically deployed to GitHub Pages via GitHub Actions.
```bash
# Install Allure CLI locally if not installed
make install-allure
```
```bash
# Generate Allure report only
make allure-report
```
```bash
# Serve Allure report locally
make allure-serve
```

## Technologies & Dependencies
- Kotlin 1.9.24
- Spring Boot 3.3.0
- JUnit 4 + JUnit Platform
- Rest-Assured with Kotlin Extensions
- Logback + SLF4J for logging
- Tomcat (provided runtime)
- Allure (test reporting)

## License
This project is licensed under the MIT License — see the [LICENSE](./LICENSE) file for details.
