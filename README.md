# Library Management System — Git & Java Exercise

A collaborative exercise for **3 groups of 2**. Each group implements one service,
runs their tests, then merges their work via a Pull Request.

---

## Project structure

```
src/
├── main/java/org/example/
│   ├── Main.java                        ← runs once all groups are done
│   ├── model/
│   │   ├── Book.java                    ← provided, do not modify
│   │   ├── Member.java                  ← provided, do not modify
│   │   └── Loan.java                    ← provided, do not modify
│   ├── repository/
│   │   ├── BookRepository.java          ← provided, do not modify
│   │   ├── MemberRepository.java        ← provided, do not modify
│   │   └── LoanRepository.java          ← provided, do not modify
│   └── service/
│       ├── BookService.java             ← GROUP 1 implements this
│       ├── MemberService.java           ← GROUP 2 implements this
│       └── LoanService.java             ← GROUP 3 implements this
└── test/java/org/example/
    ├── BookServiceTest.java             ← GROUP 1 must make these pass
    ├── MemberServiceTest.java           ← GROUP 2 must make these pass
    └── LoanServiceTest.java             ← GROUP 3 must make these pass
```

---

## Groups and tasks

| Group | Branch name | File to implement | Tests to run |
|-------|-------------|-------------------|--------------|
| 1 | `feature/book-management` | `BookService.java` | `BookServiceTest` |
| 2 | `feature/member-management` | `MemberService.java` | `MemberServiceTest` |
| 3 | `feature/loan-management` | `LoanService.java` | `LoanServiceTest` |

---

## Git workflow (each group follows these steps)

### 1. Clone the repository

```bash
git clone <repository-url>
cd library-management
```

### 2. Create your feature branch

```bash
# Group 1
git checkout -b feature/book-management

# Group 2
git checkout -b feature/member-management

# Group 3
git checkout -b feature/loan-management
```

### 3. Implement your service

Open your assigned `*Service.java` file and replace every
`throw new UnsupportedOperationException(...)` with a real implementation.
Read the Javadoc comment above each method — it describes the exact behaviour
and validation rules expected.

### 4. Run your tests

```bash
# Run only your group's tests
./gradlew test --tests "org.example.BookServiceTest"    # Group 1
./gradlew test --tests "org.example.MemberServiceTest"  # Group 2
./gradlew test --tests "org.example.LoanServiceTest"    # Group 3

# Run the full test suite
./gradlew test
```

### 5. Commit and push

```bash
git add src/main/java/org/example/service/BookService.java   # adjust for your file
git commit -m "feat: implement BookService"
git push origin feature/book-management
```

### 6. Open a Pull Request

On GitHub / GitLab, open a Pull Request from your feature branch into **main**.
Write a short description of what you implemented.

### 7. Review another group's PR

Each group reviews one other group's Pull Request before it is merged.
Leave at least one comment (question, suggestion, or approval).

### 8. Merge and resolve conflicts (if any)

Once all three PRs are merged, pull main and run `Main.java`:

```bash
git checkout main
git pull
./gradlew run
```

---

## Tips

- **Read the tests first** — they tell you exactly what each method must do.
- **Commit early and often** — small commits are easier to review.
- **Communicate** — if Group 3 has questions about how books or members work,
  ask Groups 1 and 2.
- **Do not modify** model classes, repositories, or tests.
