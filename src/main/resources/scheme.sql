CREATE TABLE IF NOT EXISTS books (
    isbn VARCHAR(20) PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    author VARCHAR(255) NOT NULL,
    category VARCHAR(100),
    status VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS members (
    member_id VARCHAR(50) PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    role VARCHAR(20) NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE IF NOT EXISTS loans (
    loan_id VARCHAR(50) PRIMARY KEY,
    book_isbn VARCHAR(20) REFERENCES books(isbn),
    member_id VARCHAR(50) REFERENCES members(member_id),
    issue_date DATE NOT NULL,
    due_date DATE NOT NULL,
    return_date DATE,
    status VARCHAR(20) NOT NULL
);