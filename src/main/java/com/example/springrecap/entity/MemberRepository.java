package com.example.springrecap.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,Long> {
    boolean existsByUsername(String username);
    Member findByUsername(String username);
}
