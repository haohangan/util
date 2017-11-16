package es;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Twitter {
	private String user;
	private Date post_date;
	private String message;

}
