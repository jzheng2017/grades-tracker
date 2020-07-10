package com.grades.tracker.api.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_school_branch_course", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"course_name", "user_school_branch_id"})
})
public class UserSchoolBranchCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UserSchoolBranch userSchoolBranch;

    @Column(nullable = false, name = "course_name")
    private String courseName;

    @Column(nullable = false, name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
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
