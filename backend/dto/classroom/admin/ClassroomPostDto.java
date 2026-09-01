package com.kauanrodrigues.backend.dto.classroom.admin;

import java.util.UUID;

public record ClassroomPostDto(String name, UUID teacherId) {
}
