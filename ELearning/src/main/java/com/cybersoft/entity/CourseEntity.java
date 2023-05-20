package com.cybersoft.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "courses")
@Getter
@Setter
public class CourseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String image;
	
	@Column(name = "lectures_count")
	private int lecturesCount;
	
	@Column(name = "hour_count")
	private int hourCount;
	
	@Column(name = "view_count")
	private int viewCount;
	private double price;
	private int discount;
	@Column(name = "promotion_price")
	private double promotionPrice;

	@Column(name = "target")
	private String target;
	
	private String description;
	
	private String content;

	
	@Column(name = "last_update")
	@Temporal(TemporalType.DATE)
	private Date lastUpdate;
	
	@ManyToOne
	@JoinColumn(name = "category_id")
	@JsonIgnore
	private Category category;

	@ManyToMany(mappedBy = "userCourse", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> users = new HashSet<>();
	
	@OneToMany(mappedBy = "course", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	@JsonIgnore
	private List<CourseContentEntity> courseContents;


	public CourseEntity(Long id, String title, String image, int leturesCount, int hourCount, int viewCount, double price,
						int discount, double promotionPrice, String description, String content, int categoryId, Date lastUpdate,
						Category category) {

		this.id = id;
		this.title = title;
		this.image = image;
		this.lecturesCount = leturesCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
		this.content = content;

		this.lastUpdate = lastUpdate;
		this.category = category;
	}

	public CourseEntity() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public CourseEntity(Long id, String title, String image, int leturesCount, int hourCount, int viewCount, double price,
						int discount, double promotionPrice, String description) {
		super();
		this.id = id;
		this.title = title;
		this.image = image;
		this.lecturesCount = leturesCount;
		this.hourCount = hourCount;
		this.viewCount = viewCount;
		this.price = price;
		this.discount = discount;
		this.promotionPrice = promotionPrice;
		this.description = description;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", title=" + title + ", image=" + image + ", leturesCount=" + lecturesCount
				+ ", hourCount=" + hourCount + ", viewCount=" + viewCount + ", price=" + price + ", discount="
				+ discount + ", promotionPrice=" + promotionPrice + ", description=" + description + ", content="
				+ content + ", categoryId=" + ", lastUpdate=" + lastUpdate + ", category=" + category
				+ ", userCourses=" + "]";
	}
	
	
	

	

	
	
	
}
