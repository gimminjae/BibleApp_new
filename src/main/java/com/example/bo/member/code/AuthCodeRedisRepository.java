package com.example.bo.member.code;

import com.example.bo.member.refresh.entity.RefreshToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface AuthCodeRedisRepository extends CrudRepository<AuthCode, String> {
}
