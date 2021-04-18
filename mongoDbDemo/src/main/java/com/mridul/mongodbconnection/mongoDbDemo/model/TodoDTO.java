package com.mridul.mongodbconnection.mongoDbDemo.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection="todos")


public class TodoDTO {
	@Id
	private String id;

	
	private String todo;

	private String description;

	private Boolean completed;

	private Date createdAt;

	private Date updatedDate;

	@Override
	public String toString() {
		return "TodoDTO [id=" + id + ", todo=" + todo + ", description=" + description + ", completed=" + completed
				+ ", createdAt=" + createdAt + ", updatedDate=" + updatedDate + "]";
	}
    
	

}
