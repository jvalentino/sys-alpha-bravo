package com.github.jvalentino.alphabravo.entity

import groovy.transform.CompileDynamic

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id
import javax.persistence.Table

/**
 * Represents an authorized user
 * @author john.valentino
 */
@CompileDynamic
@Entity
@Table(name = 'auth_user')
class AuthUser {

    @Id @GeneratedValue
    @Column(name = 'auth_user_id')
    Long authUserId

    String email

    String password

    String salt

    @Column(name = 'first_name')
    String firstName

    @Column(name = 'last_name')
    String lastName

}
