package com.example.studenthalfstack.securty;

import com.google.common.collect.Sets;

import java.util.Set;

import static com.example.studenthalfstack.securty.ApplicationUserPermission.*;

public enum ApplicationUserRole {
    STUDENT(Sets.newHashSet()),
    ADMIN(Sets.newHashSet(COURSE_READ, COURSE_WRITE, STUDENT_READ, STUDENT_WRITE)),
    STUDENTGOV(Sets.newHashSet(COURSE_READ, STUDENT_READ));

    private final Set<ApplicationUserPermission> permissions;

    ApplicationUserRole(Set<ApplicationUserPermission> permissions) {
        this.permissions = permissions;
    }
}
