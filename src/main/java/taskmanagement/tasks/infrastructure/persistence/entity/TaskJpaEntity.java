package taskmanagement.tasks.infrastructure.persistence.entity;

import jakarta.persistence.*;
import taskmanagement.projects.infrastructure.persistence.entity.ProjectJpaEntity;
import taskmanagement.users.infrastructure.persistence.entity.UserJpaEntity;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "tasks")
public class TaskJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @Column(nullable = false)
    private String status;

    @Column(nullable = false)
    private String priority;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private ProjectJpaEntity project;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assigned_user_id")
    private UserJpaEntity assignedUser;

    public TaskJpaEntity() {}

    public TaskJpaEntity(
            Long id,
            String title,
            String description,
            String status,
            String priority,
            LocalDateTime createdAt,
            LocalDate dueDate,
            ProjectJpaEntity project,
            UserJpaEntity assignedUser
            ) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.createdAt = createdAt;
        this.dueDate = dueDate;
        this.project = project;
        this.assignedUser = assignedUser;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public LocalDate getDueDate() { return dueDate; }
    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ProjectJpaEntity getProject() {
        return project;
    }

    public void setProject(ProjectJpaEntity project) {
        this.project = project;
    }

    public UserJpaEntity getAssignedUser() {
        return assignedUser;
    }

    public void setAssignedUser(UserJpaEntity assignedUser) {
        this.assignedUser = assignedUser;
    }
}
