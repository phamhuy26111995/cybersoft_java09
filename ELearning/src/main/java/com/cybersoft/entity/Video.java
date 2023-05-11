package com.cybersoft.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "videos")
@Getter
@Setter
public class Video {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String title;
	private String url;
	private String image;

	@ManyToOne
	@JoinColumn(name = "course_content_id")
	private CourseContentEntity courseContent;

	@Column(name = "time_count")
	private int timeCount;

	public Video(String title, String url, String image, int timeCount) {
		super();
		this.title = title;
		this.url = url;
		this.image = image;
		this.timeCount = timeCount;

	}

	public Video() {

	}



	

}
