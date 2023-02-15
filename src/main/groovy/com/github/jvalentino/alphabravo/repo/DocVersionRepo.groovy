package com.github.jvalentino.alphabravo.repo

import com.github.jvalentino.alphabravo.entity.DocVersion
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

/**
 * Repository interface for Doc, for all your customn HQL needs
 * @author john.valentino
 */
interface DocVersionRepo extends JpaRepository<DocVersion, Long> {

    @Query('''
        select new com.github.jvalentino.alphabravo.entity.DocVersion(
            v.docVersionId,
            v.versionNum,
            v.createdDateTime
        )
        from DocVersion v
        where v.doc.docId = ?1
        order by v.versionNum
    ''')
    List<DocVersion> getVersionsWithoutData(Long docId)

}
