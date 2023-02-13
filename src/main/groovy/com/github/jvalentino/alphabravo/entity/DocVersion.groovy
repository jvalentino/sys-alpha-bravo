package com.github.jvalentino.alphabravo.entity

import groovy.transform.CompileDynamic

import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.Lob
import javax.persistence.ManyToOne
import javax.persistence.Table
import java.sql.Timestamp

/**
 * Represents an individual version of a document
 * @author john.valentino
 */
@CompileDynamic
@Entity
@Table(name = 'doc_version')
class DocVersion {

    @Id @GeneratedValue
    @Column(name = 'doc_version_id')
    Long docVersionId

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = 'doc_id', referencedColumnName = 'doc_id')
    Doc doc

    @Lob
    @Column(name = 'data', columnDefinition = 'BLOB')
    byte[] data

    @Column(name = 'created_datetime')
    Timestamp createdDateTime

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    @JoinColumn(name = 'created_by_user_id', referencedColumnName = 'auth_user_id')
    AuthUser createdByUser

}