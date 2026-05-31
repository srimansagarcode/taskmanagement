CREATE TABLE tasks(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    description TEXT,
    status VARCHAR(50)
        CHECK (status IN ('TODO', 'IN_PROGRESS', 'COMPLETED', 'BLOCKED')),
    priority VARCHAR(50)
    CHECK ( priority IN('LOW', 'MEDIUM', 'HIGH') ),
    project_id BIGINT,
    assigned_user_id BIGINT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_task_project
                  FOREIGN KEY (project_id)
                  REFERENCES projects(id),
    CONSTRAINT fk_task_user
                  FOREIGN KEY (assigned_user_id)
                  REFERENCES users(id)
);