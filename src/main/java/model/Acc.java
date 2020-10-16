package model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class Acc {
    private String username;
    private long id;
    private Set<Acc> followed;
    private Set<Acc> following;
    private boolean isParent;
    private LocalDateTime lastPostDate;
}
