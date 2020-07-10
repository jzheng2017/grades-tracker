package com.grades.tracker.api.entities;


import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_course_assignment", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"user_school_branch_course_id", "assignment_name", "attempt_no"})
})
public class UserCourseAssignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private UserSchoolBranchCourse userSchoolBranchCourse;

    @Column(nullable = false, name = "assignment_name")
    private String assignmentName;

    @Column(nullable = false, name = "attempt_no")
    private int attemptNo;

    @Column(name = "grade")
    private float grade;

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

    public UserSchoolBranchCourse getUserSchoolBranchCourse() {
        return userSchoolBranchCourse;
    }

    public void setUserSchoolBranchCourse(UserSchoolBranchCourse userSchoolBranchCourse) {
        this.userSchoolBranchCourse = userSchoolBranchCourse;
    }

    public String getAssignmentName() {
        return assignmentName;
    }

    public void setAssignmentName(String assignmentName) {
        this.assignmentName = assignmentName;
    }

    public int getAttemptNo() {
        return attemptNo;
    }

    public void setAttemptNo(int attemptNo) {
        this.attemptNo = attemptNo;
    }

    public float getGrade() {
        return grade;
    }

    public void setGrade(float grade) {
        this.grade = grade;
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
