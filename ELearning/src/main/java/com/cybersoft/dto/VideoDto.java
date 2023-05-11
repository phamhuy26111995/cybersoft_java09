package com.cybersoft.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VideoDto {
	private int id;
	private String title;
	private String url;
	private String image;
	private int timeCount;

	public VideoDto(int id, String title, String url, String image, int timeCount) {
		super();
		this.id = id;
		this.title = title;
		this.url = url;
		this.image = image;
		this.timeCount = timeCount;
	}
	public VideoDto() {
		
	}

	public VideoDto(int id, String title, String url, String image, int timeCount, int courseId) {
		this.id = id;
		this.title = title;
		this.url = url;
		this.image = image;
		this.timeCount = timeCount;
	}

	
}
