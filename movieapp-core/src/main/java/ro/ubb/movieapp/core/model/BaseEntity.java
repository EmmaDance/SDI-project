package ro.ubb.movieapp.core.model;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
@NoArgsConstructor
@Data
@Getter
@Setter
public abstract class BaseEntity<ID extends Serializable>
        implements Serializable {

    @Id
    @GeneratedValue
    private ID id;

}
