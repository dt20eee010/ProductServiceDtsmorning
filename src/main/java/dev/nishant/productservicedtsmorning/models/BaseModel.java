package dev.nishant.productservicedtsmorning.models;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public class BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date CreatedAt;
    private Date LastUpdatedAt;
    private boolean isDeleted;
}
