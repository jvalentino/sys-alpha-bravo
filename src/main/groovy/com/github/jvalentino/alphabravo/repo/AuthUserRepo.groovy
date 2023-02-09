package com.github.jvalentino.alphabravo.repo

import com.github.jvalentino.alphabravo.entity.AuthUser
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository interface for the AuthUser entity
 * @author john.valentino
 */
interface AuthUserRepo extends JpaRepository<AuthUser, Long> {

}