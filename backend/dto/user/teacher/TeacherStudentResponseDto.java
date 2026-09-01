package com.kauanrodrigues.backend.dto.user.teacher;

import java.util.UUID;

public record TeacherStudentResponseDto(UUID id, String name, String email) {
}
