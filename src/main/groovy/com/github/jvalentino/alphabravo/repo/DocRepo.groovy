package com.github.jvalentino.alphabravo.repo

import com.github.jvalentino.alphabravo.entity.Doc
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository interface for Doc, for all your customn HQL needs
 * @author john.valentino
 */
interface DocRepo extends JpaRepository<Doc, Long> {

}
