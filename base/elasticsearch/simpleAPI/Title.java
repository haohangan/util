package es;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Title {
    private int  id;
    private String title;
    private String auth;
    private Date date;
    private String context;
    
}
