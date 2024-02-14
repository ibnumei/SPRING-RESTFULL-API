package msjavamicro.restfull.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.UuidGenerator;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "db_users_v2")
public class DbUser {
    
    @Id
    @UuidGenerator
    @GeneratedValue(generator = "system-uuid")
    @Column(name = "id_user")
    private String idUser;
    private String username;
    private String password;
    private String name;
    private String token;
    @Column(name = "token_expired_at")
    private Long tokenExpiredAt;
    private Integer balance;
}
