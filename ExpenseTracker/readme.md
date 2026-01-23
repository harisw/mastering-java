Folder Structure Practice

expense-tracker/
├─ pom.xml
└─ src/
   ├─ main/
   │  ├─ java/
   │  │  └─ com/example/expensetracker/
   │  │
   │  │     ├─ application/
   │  │     │   ├─ ExpenseService.java
   │  │     │   └─ UserService.java
   │  │
   │  │     ├─ domain/
   │  │     │   ├─ model/
   │  │     │   │   ├─ User.java
   │  │     │   │   ├─ Expense.java
   │  │     │   │   ├─ Category.java
   │  │     │   │   └─ Money.java
   │  │     │   │
   │  │     │   └─ repository/
   │  │     │       ├─ UserRepository.java
   │  │     │       └─ ExpenseRepository.java
   │  │
   │  │     ├─ infrastructure/
   │  │     │   ├─ db/
   │  │     │   │   ├─ DbConnection.java
   │  │     │   │   └─ DataSourceFactory.java
   │  │     │   │
   │  │     │   └─ repository/
   │  │     │       ├─ JdbcUserRepository.java
   │  │     │       └─ JdbcExpenseRepository.java
   │  │
   │  │     ├─ api/
   │  │     │   ├─ dto/
   │  │     │   │   ├─ CreateExpenseRequest.java
   │  │     │   │   └─ ExpenseResponse.java
   │  │     │   │
   │  │     │   └─ ExpenseController.java
   │  │
   │  │     └─ Main.java
   │  │
   │  └─ resources/
   │     ├─ application.properties
   │     └─ db/
   │        └─ migration/
   │           └─ V1__init_schema.sql
   │
   └─ test/
      └─ java/
         └─ com/example/expensetracker/
            ├─ domain/
            ├─ application/
            └─ infrastructure/
