package com.kreuterkeule.authvuejsapp.data;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.relational.core.mapping.Table;

@Table("USERS")
@Getter @Setter @ToString
public class UserEntity {

    @Id
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public static UserEntity of(String firstName, String lastName, String email, String password) {
        return new UserEntity(null, firstName, lastName, email, password);
    }

    @PersistenceCreator
    private UserEntity(Long id, String firstName, String lastName, String email, String password) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
    }
}
