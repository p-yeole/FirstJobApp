FirstJobApp — quick developer notes

Running tests
- Unit/integration tests are configured to use an in-memory H2 database so they do not depend on a local Postgres instance.
- To run the single smoke test that verifies ApplicationContext: 

```powershell
mvn -Dtest=FirstJobAppApplicationTests test
```

- Or run the full test suite:

```powershell
mvn test
```

Why H2 is used for tests
- `src/test/resources/application.properties` contains a test-only datasource pointing to H2 and provides small placeholders (e.g. `jwt.secret`) required by application beans so tests can load the Spring context without external services.

Local development database
- The application default properties (for local dev) are in `src/main/resources/application.properties` and currently point at a local Postgres instance:
  - `spring.datasource.url=jdbc:postgresql://localhost:5432/JobApp`
  - `spring.datasource.username` and `spring.datasource.password`

- Options to run the app locally against Postgres:
  1) Start a Postgres server locally and ensure credentials match `application.properties`.
  2) Use Docker Compose (add or update `docker-compose.yml`) to run Postgres. Example env values must match `application.properties`.
  3) Change `src/main/resources/application.properties` to match your database credentials or use Spring profiles / environment variables to override at runtime.

Secrets and recommended improvements
- Do not commit production secrets in `application.properties`. Prefer environment variables or a secrets manager.
- For tests, either provide test-safe placeholder values (as done) or mock beans that require secrets (e.g., security filters).

POM cleanup
- I removed a duplicate `maven-compiler-plugin` declaration and merged its configuration. If you still see a warning about plugin duplicates, run `mvn -X` to inspect the effective POM.

If you'd like, I can:
- Add a `docker-compose` Postgres service configured to the current `application.properties` values.
- Add a `Makefile` or simple Powershell script to start the Postgres container and run tests.
- Move secrets into environment variables and document how to run locally with them.
