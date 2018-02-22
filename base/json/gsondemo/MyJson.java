package demo;

import lombok.Builder;
import lombok.Data;

import java.util.HashMap;
import java.util.List;

@Data
@Builder
public class MyJson {
    private List<HashMap<String,Object>> alerts;
    private String session;
    private String result;
    private Integer code;
}
