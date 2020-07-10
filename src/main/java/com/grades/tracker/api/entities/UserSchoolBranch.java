package com.grades.tracker.api.entities;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_school_branch", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"branch_name", "user_school_id", "begin_date"})
})
public class UserSchoolBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, name = "branch_name")
    private String branchName;

    @ManyToOne
    private UserSchool userSchool;

    @Column(nullable = false, name = "begin_date")
    private LocalDateTime beginDate;


    @Column(nullable = false, name = "end_date")
    private LocalDateTime endDate;

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

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public UserSchool getUserSchool() {
        return userSchool;
    }

    public void setUserSchool(UserSchool userSchool) {
        this.userSchool = userSchool;
    }

    public LocalDateTime getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(LocalDateTime beginDate) {
        this.beginDate = beginDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
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
