CREATE INDEX idx_tasks_status
    ON tasks(status);

CREATE INDEX idx_tasks_project_id
    ON tasks(project_id);

CREATE INDEX idx_tasks_assigned_user_id
    ON tasks(assigned_user_id);