package com.github.jvalentino.alphabravo.service

import com.github.jvalentino.alphabravo.entity.AuthUser
import com.github.jvalentino.alphabravo.entity.Doc
import com.github.jvalentino.alphabravo.entity.DocVersion
import com.github.jvalentino.alphabravo.repo.DocRepo
import com.github.jvalentino.alphabravo.repo.DocVersionRepo
import groovy.transform.CompileDynamic
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile

import java.sql.Timestamp

/**
 * General service for interacting with documents
 * @author john.valentino
 */
@CompileDynamic
@Service
@Slf4j
class DocService {

    @Autowired
    DocRepo docRepo

    @Autowired
    DocVersionRepo docVersionRepo

    Long countDocuments() {
        docRepo.count()
    }

    DocVersion uploadNewDoc(AuthUser user, MultipartFile file, Date date) {
        String ext = file.originalFilename.split('\\.').last()

        Doc parentDoc = new Doc()
        parentDoc.with {
            name = file.originalFilename
            mimeType = "application/${ext}"
            createdByUser = user
            updatedByUser = user
            createdDateTime = new Timestamp(date.time)
            updatedDateTime = new Timestamp(date.time)
        }
        docRepo.save(parentDoc)

        DocVersion version = new DocVersion()
        version.with {
            doc = parentDoc
            data = file.bytes
            createdDateTime = new Timestamp(date.time)
            createdByUser = user
            versionNum = 1
        }
        docVersionRepo.save(version)
    }

}
