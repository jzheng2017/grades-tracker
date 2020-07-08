package com.grades.tracker.api.entities;

import com.sun.istack.NotNull;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"course_name", "user_school_branch_id"})
})
public class UserSchoolBranchCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UserSchoolBranch userSchoolBranch;

    @Column(name = "course_name")
    @NotNull
    private String courseName;

    @Column(name = "created_at")
    @NotNull
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserSchoolBranch getUserSchoolBranch() {
        return userSchoolBranch;
    }

    public void setUserSchoolBranch(UserSchoolBranch userSchoolBranch) {
        this.userSchoolBranch = userSchoolBranch;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}
