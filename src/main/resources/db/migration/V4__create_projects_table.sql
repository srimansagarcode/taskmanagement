CREATE TABLE projects(
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    status VARCHAR(50)
        CHECK (status IN ('PLANNED', 'IN_PROGRESS', 'COMPLETED')),
    created_by BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_projects_creator
                     FOREIGN KEY (created_by)
                     REFERENCES users(id)
);