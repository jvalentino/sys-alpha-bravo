package com.github.jvalentino.alphabravo.repo

import com.github.jvalentino.alphabravo.entity.Doc
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Repository interface for Doc, for all your customn HQL needs
 * @author john.valentino
 */
interface DocRepo extends JpaRepository<Doc, Long> {

    @Query('''
        select distinct d from Doc d
        left join fetch d.updatedByUser
        order by d.name, d.updatedDateTime
    ''')
    List<Doc> allDocs()

}
