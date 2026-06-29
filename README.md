# 🎓 BrightFund

> **A comprehensive, full-stack scholarship management system built for students and institutions.**

[![Java](https://img.shields.io/badge/Java-21-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://openjdk.org/projects/jdk/21/)
[![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.3.4-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)](https://spring.io/projects/spring-boot)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-Latest-316192?style=for-the-badge&logo=postgresql&logoColor=white)](https://www.postgresql.org/)
[![Maven](https://img.shields.io/badge/Maven-3.x-C71A36?style=for-the-badge&logo=apache-maven&logoColor=white)](https://maven.apache.org/)
[![License](https://img.shields.io/badge/License-MIT-blue?style=for-the-badge)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Passing-brightgreen?style=for-the-badge)]()

---

## 📖 Overview

**BrightFund** is a full-stack scholarship management web application designed to streamline the end-to-end lifecycle of student scholarship applications. It provides a secure, centralized platform where students can register, apply for scholarships, upload academic documents, track semester results, and log their achievements — all from a single, intuitive dashboard.

The platform was built to address the inefficiencies of paper-based or fragmented scholarship processes in academic institutions. By digitizing every step — from initial registration to document submission and application tracking — BrightFund eliminates manual paperwork, reduces processing delays, and gives students real-time visibility into their application status.

BrightFund is built with enterprise-grade technologies: a **Spring Boot** backend, **Spring Security** for authentication and authorization, **JPA/Hibernate** for database persistence, and **Thymeleaf** for server-side rendered views. It is backed by **PostgreSQL** and follows a layered MVC architecture for maintainability and scalability.

---

## 🏗️ Architecture

```
┌─────────────────────────────────────────────────────────┐
│                        Browser / Client                  │
│              (Thymeleaf HTML + Bootstrap CSS)            │
└──────────────────────────┬──────────────────────────────┘
                           │  HTTP Requests
                           ▼
┌─────────────────────────────────────────────────────────┐
│              Spring Boot Application (Port 8080)         │
│                                                         │
│  ┌─────────────────────────────────────────────────┐    │
│  │           Spring Security Filter Chain           │    │
│  │  (Form Login / Session / BCrypt Authentication)  │    │
│  └────────────────────┬────────────────────────────┘    │
│                       │                                  │
│  ┌────────────────────▼────────────────────────────┐    │
│  │               Controllers (MVC Layer)             │    │
│  │  HomeController | LoginController | ContactCtrl  │    │
│  └────────────────────┬────────────────────────────┘    │
│                       │                                  │
│  ┌────────────────────▼────────────────────────────┐    │
│  │               Service Layer                       │    │
│  │               StudentService                     │    │
│  └────────────────────┬────────────────────────────┘    │
│                       │                                  │
│  ┌────────────────────▼────────────────────────────┐    │
│  │          Repository Layer (Spring Data JPA)       │    │
│  │  StudentRepo | ScholarshipRepo | DocumentsRepo   │    │
│  │  ResultRepo  | FeeReceiptRepo  | AchievementsRepo│    │
│  └────────────────────┬────────────────────────────┘    │
└───────────────────────┼─────────────────────────────────┘
                        │  JDBC / Hibernate ORM
                        ▼
┌─────────────────────────────────────────────────────────┐
│                   PostgreSQL Database                    │
│                   (brightfund schema)                   │
│                                                         │
│  [students] [scholarship_applications] [results]        │
│  [fee_receipts] [documents] [achievements] [contacts]   │
└─────────────────────────────────────────────────────────┘
```

---

## ✨ Features

- 🔐 **Secure Authentication** — Custom form-based login with Spring Security, BCrypt password hashing, and session management
- 📝 **Student Self-Registration** — Full signup flow with email/password confirmation validation
- 📋 **Scholarship Application** — Students can submit and track scholarship applications with real-time status updates (`PENDING`, `APPROVED`, `REJECTED`)
- 📁 **Document Management** — Upload and manage required documents (Aadhar Card, Bank Passbook, Income Certificate, College Admission Order, Photo, Signature)
- 📊 **Semester Results Tracking** — Upload and view result files per semester (up to 8 semesters)
- 🧾 **Fee Receipt Management** — Upload and track annual fee receipts per academic year
- 🏆 **Achievements Logging** — Record and display academic/extracurricular achievements with optional file attachments
- 👤 **Profile Management** — Students can update personal, academic, and contact details
- 🛡️ **Role-based Access** — Public pages vs. authenticated-only routes enforced at the security layer
- 📧 **Contact Form** — Visitors can send inquiries directly through the platform
- 🗓️ **Audit Trails** — Automatic `createdAt` / `updatedAt` timestamps on entities via Spring Data Auditing

---

## 🛠️ Tech Stack

| Layer | Technology | Version | Purpose |
|---|---|---|---|
| **Language** | Java | 21 (LTS) | Core application language |
| **Framework** | Spring Boot | 3.3.4 | Application framework & auto-configuration |
| **Web** | Spring MVC | 6.x | REST endpoints & MVC pattern |
| **Templating** | Thymeleaf | 3.x | Server-side HTML rendering |
| **Security** | Spring Security | 6.x | Authentication, authorization, CSRF |
| **Persistence** | Spring Data JPA | 3.x | Repository abstraction layer |
| **ORM** | Hibernate | 6.x | Object-relational mapping |
| **Database** | PostgreSQL | Latest | Relational data storage |
| **Validation** | Jakarta Validation | 3.x (JSR-380) | Bean validation & form input checks |
| **Build Tool** | Maven | 3.x | Dependency management & build lifecycle |
| **Boilerplate** | Lombok | Latest | Reduces boilerplate (getters/setters) |
| **Frontend** | HTML5 / CSS3 | — | Page structure & styling |
| **VCS** | Git / GitHub | — | Version control |

---

## 📋 Prerequisites

Before running BrightFund, ensure you have the following installed:

| Tool | Version | Check |
|---|---|---|
| **JDK** | 21 or higher | `java -version` |
| **Maven** | 3.8+ | `mvn -version` |
| **PostgreSQL** | 14+ | `psql --version` |
| **Git** | Any recent version | `git --version` |

> ⚠️ **Note:** BrightFund requires Java 21. Using an older JDK version will cause build failures.

---

## ⚡ Quick Start

Get up and running in under 5 minutes:

```bash
# 1. Clone the repository
git clone https://github.com/vishwanath0708/BrightFund.git
cd BrightFund

# 2. Create the PostgreSQL database
psql -U postgres -c "CREATE DATABASE brightfund;"
psql -U postgres -c "CREATE USER brightfund_user WITH PASSWORD 'your_password';"
psql -U postgres -c "GRANT ALL PRIVILEGES ON DATABASE brightfund TO brightfund_user;"

# 3. Configure your environment (see Configuration section)
cp src/main/resources/application.properties.example src/main/resources/application.properties
# Edit application.properties with your DB credentials

# 4. Build and run the application
./mvnw spring-boot:run

# Application is now running at http://localhost:8080
```

---

## 🔧 Installation & Setup

### Option 1: Run with Maven Wrapper (Recommended for Development)

The project includes a Maven Wrapper (`mvnw`) so you don't need a global Maven installation:

```bash
# Clone the project
git clone https://github.com/vishwanath0708/BrightFund.git
cd BrightFund

# Make the wrapper executable (Linux/macOS)
chmod +x mvnw

# Run in development mode (with auto-restart via DevTools if added)
./mvnw spring-boot:run
```

### Option 2: Build a JAR and Run

```bash
# Package the application
./mvnw clean package -DskipTests

# Run the packaged JAR
java -jar target/com-0.0.1-SNAPSHOT.jar
```

### Option 3: Build and Run with System Maven

```bash
mvn clean install
mvn spring-boot:run
```

### Development vs. Production Setup

**Development:**
- Use `spring.jpa.hibernate.ddl-auto=update` (auto-creates/migrates tables)
- Enable SQL logging: `spring.jpa.show-sql=true`
- Use a local PostgreSQL instance

**Production:**
- Change `ddl-auto` to `validate` or `none` and use a migration tool (e.g., Flyway/Liquibase)
- Externalize all secrets via environment variables (see [Configuration Reference](#-configuration-reference))
- Disable SQL logging
- Run behind a reverse proxy (nginx) with HTTPS

---

## ⚙️ Configuration Reference

All application configuration lives in `src/main/resources/application.properties`.

| Property | Default | Description |
|---|---|---|
| `spring.application.name` | `com` | Spring application name |
| `spring.datasource.url` | `jdbc:postgresql://localhost:5432/brightfund` | PostgreSQL JDBC connection URL |
| `spring.datasource.username` | `Vishwa` | Database username |
| `spring.datasource.password` | `matrix` | Database password |
| `spring.datasource.driver-class-name` | `org.postgresql.Driver` | JDBC driver class |
| `spring.jpa.hibernate.ddl-auto` | `update` | Schema management strategy (`update`, `validate`, `create`, `none`) |
| `spring.jpa.show-sql` | `true` | Print SQL queries to console |
| `spring.jpa.properties.hibernate.dialect` | `org.hibernate.dialect.PostgreSQLDialect` | Hibernate database dialect |
| `server.address` | `0.0.0.0` | Server bind address |
| `server.port` | `8080` | HTTP server port |

> 🔒 **Security Warning:** Never commit real credentials to version control. Use environment variable substitution for production:

```properties
# Use environment variables in production
spring.datasource.url=${DB_URL:jdbc:postgresql://localhost:5432/brightfund}
spring.datasource.username=${DB_USERNAME:brightfund_user}
spring.datasource.password=${DB_PASSWORD}
```

Then export environment variables before running:
```bash
export DB_URL=jdbc:postgresql://prod-host:5432/brightfund
export DB_USERNAME=prod_user
export DB_PASSWORD=your_secure_password
java -jar target/com-0.0.1-SNAPSHOT.jar
```

---

## 📚 Usage Guide

### Student Registration

1. Navigate to `http://localhost:8080/create`
2. Fill in name, email, password (with confirmation), and date of birth
3. Submit — you'll be redirected to the login page upon success

### Logging In

1. Navigate to `http://localhost:8080/signin`
2. Enter your registered email and password
3. On success, you are redirected to the **Dashboard**

### Submitting a Scholarship Application

1. From the dashboard, click **Apply for Scholarship**
2. Fill in your academic and personal details (course, semester, income, institute)
3. Submit — your application status appears on the dashboard as `PENDING`

### Uploading Documents

1. Navigate to **Documents** from the dashboard
2. For each required document type, click **Upload**:
   - Aadhar Card
   - Bank Passbook
   - Photo
   - Signature
   - Income Certificate
   - College Admission Order
3. Select the file and confirm — re-uploading replaces the previous file

### Managing Semester Results & Fee Receipts

1. Navigate to **Semester** from the dashboard
2. Select the semester number, choose a result PDF, and click **Upload**
3. For fee receipts, choose the academic year and upload the receipt file

### Logging Achievements

1. Navigate to **Achievements** from the dashboard
2. Add a title, description, date, and optionally attach a certificate or image
3. Your achievements appear listed on the page

### Updating Profile

1. Click **Update Profile** from the dashboard
2. Modify your personal, academic, or contact details
3. Save — you'll be redirected to the dashboard with a success message

---

## 🗂️ Project Structure

```
BrightFund/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── BrightFund/
│   │   │       └── com/
│   │   │           ├── ComApplication.java              # Spring Boot entry point
│   │   │           ├── Auditing/
│   │   │           │   ├── AuditAwarelml.java           # AuditorAware implementation
│   │   │           │   └── BaseEntity.java              # createdAt/updatedAt base class
│   │   │           ├── Controllers/
│   │   │           │   ├── HomeController.java          # Main MVC controller (all features)
│   │   │           │   ├── ContactController.java       # Contact form handler
│   │   │           │   └── LoginAndLogoutController.java # Auth page routing
│   │   │           ├── Models/
│   │   │           │   ├── Student.java                 # Core student entity
│   │   │           │   ├── Studentlogin.java            # Registration form DTO
│   │   │           │   ├── ScholarshipApplication.java  # Scholarship application entity
│   │   │           │   ├── Result.java                  # Semester result entity
│   │   │           │   ├── FeeReceipt.java              # Fee receipt entity
│   │   │           │   ├── Documents.java               # Uploaded document entity
│   │   │           │   ├── Achievements.java            # Achievement entity
│   │   │           │   └── Contact.java                 # Contact form entity
│   │   │           ├── Repositories/
│   │   │           │   ├── StudentRepo.java             # Student JPA repository
│   │   │           │   ├── ScholarshipRepo.java         # Scholarship JPA repository
│   │   │           │   ├── ResultRepo.java              # Result JPA repository
│   │   │           │   ├── FeeReceiptRepo.java          # FeeReceipt JPA repository
│   │   │           │   ├── DocumentsRepo.java           # Documents JPA repository
│   │   │           │   └── AchievementsRepo.java        # Achievements JPA repository
│   │   │           ├── Security/
│   │   │           │   ├── ProjectSecurity.java         # SecurityFilterChain configuration
│   │   │           │   └── UsernamePasswordAuthenticationProvider.java  # Custom auth provider
│   │   │           └── Service/
│   │   │               └── StudentService.java          # Business logic layer
│   │   └── resources/
│   │       ├── application.properties                   # App configuration
│   │       └── templates/
│   │           ├── fragments/
│   │           │   └── header.html                      # Shared navigation header
│   │           ├── home.html                            # Public landing page
│   │           ├── about.html                           # About page
│   │           ├── contact.html                         # Contact form page
│   │           ├── create.html                          # Student registration page
│   │           ├── customlogin.html                     # Custom login page
│   │           ├── Dashboard.html                       # Student dashboard
│   │           ├── apply.html                           # Scholarship application form
│   │           ├── updateprofile.html                   # Profile update page
│   │           ├── semester.html                        # Semester results & fee receipts
│   │           ├── documents.html                       # Document upload page
│   │           └── Achievements.html                    # Achievements page
│   └── test/
│       └── java/                                        # Unit & integration tests
├── .mvn/                                                # Maven wrapper configuration
├── mvnw                                                 # Maven wrapper script (Unix)
├── mvnw.cmd                                             # Maven wrapper script (Windows)
├── pom.xml                                              # Maven project descriptor
└── README.md                                            # This file
```

---

## 🧑‍💻 Development Guide

### Setting Up the Development Environment

```bash
# 1. Fork and clone
git clone https://github.com/<your-username>/BrightFund.git
cd BrightFund

# 2. Set up PostgreSQL locally
sudo -u postgres psql
CREATE DATABASE brightfund;
\q

# 3. Update application.properties with your local credentials

# 4. Import into your IDE
# - IntelliJ IDEA: File > Open > select the BrightFund folder
# - Eclipse: File > Import > Existing Maven Projects

# 5. Enable annotation processing for Lombok in your IDE
# IntelliJ: Settings > Build > Compiler > Annotation Processors > Enable
```

### Running Tests

```bash
# Run all tests
./mvnw test

# Run a specific test class
./mvnw test -Dtest=ComApplicationTests

# Run tests with coverage report
./mvnw test jacoco:report
```

### Building the Project

```bash
# Clean and compile
./mvnw clean compile

# Package as JAR (skip tests for speed)
./mvnw clean package -DskipTests

# Full build with tests
./mvnw clean install
```

### Code Style Guidelines

- Follow standard **Java naming conventions**: `PascalCase` for classes, `camelCase` for methods/variables
- Use **Lombok** annotations (`@Getter`, `@Setter`, `@NoArgsConstructor`, `@AllArgsConstructor`) instead of writing boilerplate
- Keep **Controllers thin** — delegate all business logic to the `Service` layer
- Use **Spring Data JPA** `Repository` interfaces for all database interactions; avoid raw SQL unless necessary
- Add **Jakarta Bean Validation** annotations (`@NotBlank`, `@Email`, `@Size`) to all form-bound model classes
- Keep `application.properties` free of hardcoded secrets in production

### Contributing a Feature

```bash
# Create a feature branch
git checkout -b feature/your-feature-name

# Make your changes and commit
git add .
git commit -m "feat: add your feature description"

# Push and open a pull request
git push origin feature/your-feature-name
```

---

## 🔗 API / URL Reference

BrightFund uses server-side rendering (Thymeleaf), so the URLs below are page routes, not REST endpoints.

| Method | URL | Auth Required | Description |
|---|---|---|---|
| `GET` | `/` or `/home` | No | Public landing page |
| `GET` | `/about` | No | About page |
| `GET` | `/contact` | No | Contact form |
| `GET` | `/create` | No | Registration page |
| `POST` | `/register` | No | Submit registration form |
| `GET` | `/signin` | No | Login page |
| `POST` | `/signin` | No | Process login |
| `GET` | `/logout` | Yes | Logout and invalidate session |
| `GET` | `/dashboard` | Yes | Student dashboard |
| `GET` | `/apply` | Yes | Scholarship application form |
| `POST` | `/submitApplication` | Yes | Submit scholarship application |
| `GET` | `/updateprofile` | Yes | Profile update form |
| `POST` | `/savechanges` | Yes | Save profile changes |
| `GET` | `/semester` | Yes | Semester results & fee receipts |
| `POST` | `/uploadResult` | Yes | Upload semester result file |
| `POST` | `/uploadReceipt` | Yes | Upload fee receipt file |
| `GET` | `/documents` | Yes | Document management page |
| `POST` | `/savedocument` | Yes | Upload a document |
| `GET` | `/files/{id}` | Yes | Download a stored document by ID |
| `GET` | `/achievements` | Yes | Achievements listing |
| `POST` | `/saveAchievement` | Yes | Save a new achievement |

---

## 🐛 Troubleshooting

### Application fails to start — `Connection refused` to PostgreSQL

**Cause:** PostgreSQL is not running, or credentials in `application.properties` are incorrect.

```bash
# Check if PostgreSQL is running
sudo systemctl status postgresql

# Start it if stopped
sudo systemctl start postgresql

# Verify connection manually
psql -U Vishwa -d brightfund -h localhost
```

**Fix:** Ensure `spring.datasource.username` and `spring.datasource.password` match your PostgreSQL user.

---

### `Access is denied` on protected pages after login

**Cause:** The custom `UsernamePasswordAuthenticationProvider` could not authenticate the user (email not found or password mismatch).

**Fix:**
1. Confirm the student is registered in the database (`SELECT * FROM studentlogin WHERE email='...'`).
2. Check that BCrypt hashing is applied consistently — passwords stored during registration must be hashed with `BCryptPasswordEncoder`.

---

### File upload fails with `Maximum upload size exceeded`

**Cause:** Spring Boot's default file upload limit (1MB) is too small.

**Fix:** Add to `application.properties`:
```properties
spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=10MB
```

---

### `Table 'students' does not exist` on first run

**Cause:** The database exists but tables haven't been created yet.

**Fix:** With `spring.jpa.hibernate.ddl-auto=update`, Hibernate auto-creates tables on startup. Ensure the database `brightfund` exists:
```bash
psql -U postgres -c "CREATE DATABASE brightfund;"
```

---

### `Build failure` — `java.version` mismatch

**Cause:** The project requires Java 21, but the system JDK is older.

**Fix:**
```bash
java -version  # Must be 21+
# Install JDK 21 if needed (Ubuntu/Debian)
sudo apt install openjdk-21-jdk
```

---

## ⚡ Performance & Optimization

### Database

- **Index key columns:** Add indexes on `email` (students), `student_id` (foreign keys) for query performance on large datasets.
- **Lazy loading:** Use `FetchType.LAZY` for large collections (results, documents) that are not always needed. The current `Student` entity uses `EAGER` for `ScholarshipApplication` — review if this becomes a bottleneck.
- **Connection pooling:** Spring Boot uses HikariCP by default. Tune `spring.datasource.hikari.maximum-pool-size` based on your server capacity.

### File Storage

- Currently, files (documents, results, receipts) are stored as `BYTEA` (binary large objects) directly in PostgreSQL. This is convenient for small datasets but does not scale well.
- **Production recommendation:** Migrate file storage to an object store (e.g., AWS S3, MinIO) and store only file references (URL/key) in the database.

### Caching

- Add `spring-boot-starter-cache` with an in-memory cache (Caffeine) for frequently read, rarely changed data (e.g., student profile during a single session).

### Pagination

- For admin dashboards with many students, implement `Pageable` in Spring Data JPA repositories to avoid loading entire tables into memory.

---

## 🔒 Security

### Current Security Posture

- **Authentication:** Custom `UsernamePasswordAuthenticationProvider` with BCrypt password hashing
- **Authorization:** Spring Security protects all routes except public pages (`/home`, `/about`, `/contact`, `/create`, `/register`)
- **Session Management:** HTTP sessions with `JSESSIONID` cookie, invalidated on logout
- **CSRF:** Spring Security's default CSRF protection applies to all POST endpoints

### Security Best Practices for Production

| Area | Recommendation |
|---|---|
| **Secrets** | Never hardcode DB credentials; use environment variables or a secret manager (HashiCorp Vault, AWS Secrets Manager) |
| **HTTPS** | Deploy behind HTTPS using a reverse proxy (nginx + Let's Encrypt). Never run on plain HTTP in production |
| **Password policy** | Enforce minimum password length/complexity via validation annotations |
| **Session timeout** | Configure `server.servlet.session.timeout=30m` to automatically expire idle sessions |
| **File validation** | Validate file MIME types and extensions on upload; restrict to PDF/JPEG/PNG only |
| **SQL injection** | Already protected via Spring Data JPA parameterized queries — do not introduce raw `@Query` with string concatenation |
| **XSS** | Thymeleaf auto-escapes output by default — avoid `th:utext` unless strictly necessary |
| **Header hardening** | Add security headers (HSTS, X-Content-Type-Options, X-Frame-Options) via Spring Security's `headers()` configuration |
| **Logging** | Disable `spring.jpa.show-sql=true` in production — it may expose sensitive query parameters in logs |

---

## 🗺️ Roadmap

| Status | Feature |
|---|---|
| ✅ Done | Student registration & login |
| ✅ Done | Scholarship application submission & status tracking |
| ✅ Done | Document upload & management |
| ✅ Done | Semester result & fee receipt uploads |
| ✅ Done | Achievement logging |
| ✅ Done | Profile update |
| 🔄 Planned | Admin portal — view/approve/reject scholarship applications |
| 🔄 Planned | Email notifications (application status changes) |
| 🔄 Planned | Migrate file storage to object store (S3/MinIO) |
| 🔄 Planned | REST API + React/Angular frontend option |
| 🔄 Planned | Dockerize with Docker Compose |
| 🔄 Planned | Flyway/Liquibase database migrations |
| 🔄 Planned | Role-based admin vs. student views |
| 🔄 Planned | Application analytics dashboard |
| 🔄 Planned | PDF report generation for scholarship applications |

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork** the repository on GitHub
2. **Clone** your fork locally
3. **Create a branch** for your feature or bug fix: `git checkout -b feature/my-feature`
4. **Make your changes** — follow the code style guidelines above
5. **Write or update tests** for your changes
6. **Commit** with a clear message: `git commit -m "feat: describe your change"`
7. **Push** to your fork: `git push origin feature/my-feature`
8. Open a **Pull Request** on the original repository, describing your changes clearly

Please ensure:
- All existing tests pass: `./mvnw test`
- No hardcoded credentials or secrets are introduced
- New routes are properly secured in `ProjectSecurity.java`

---

## 📄 License

This project is licensed under the **MIT License**.

```
MIT License

Copyright (c) 2026 BrightFund Contributors

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
```

---

## 🙏 Acknowledgments

- **[Spring Boot](https://spring.io/projects/spring-boot)** — For making enterprise Java development fast and opinionated
- **[Spring Security](https://spring.io/projects/spring-security)** — For the robust, flexible authentication and authorization framework
- **[Thymeleaf](https://www.thymeleaf.org/)** — For the clean, natural server-side templating engine
- **[Hibernate](https://hibernate.org/)** — For the industry-standard Java ORM
- **[PostgreSQL](https://www.postgresql.org/)** — For the powerful, open-source relational database
- **[Project Lombok](https://projectlombok.org/)** — For significantly reducing Java boilerplate code
- **[Maven](https://maven.apache.org/)** — For reliable dependency management and build lifecycle
- **Open-source community** — For the countless libraries, tutorials, and contributions that make projects like this possible

---

<div align="center">
  <p>Built with ❤️ for students and institutions</p>
  <p>
    <a href="https://github.com/vishwanath0708/BrightFund/issues">🐛 Report Bug</a> ·
    <a href="https://github.com/vishwanath0708/BrightFund/issues">💡 Request Feature</a> ·
    <a href="https://github.com/vishwanath0708/BrightFund">⭐ Star this repo</a>
  </p>
</div>
