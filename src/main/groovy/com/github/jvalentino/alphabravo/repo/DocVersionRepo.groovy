package com.github.jvalentino.alphabravo.repo

import com.github.jvalentino.alphabravo.entity.DocVersion
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Repository interface for Doc, for all your customn HQL needs
 * @author john.valentino
 */
interface DocVersionRepo extends JpaRepository<DocVersion, Long> {

}
