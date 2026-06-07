package taskmanagement.users.infrastructure.persistence.entity;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<UserJpaEntity> users;

    public RoleJpaEntity() {}

    public RoleJpaEntity(
            Long id,
            String name,
            String description,
            Set<UserJpaEntity> users
    ) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.users = users;
    }

    public Long getId() { return id;}
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Set<UserJpaEntity> getUsers() { return users; }
    public void setUsers(Set<UserJpaEntity> users) { this.users = users; }
}
