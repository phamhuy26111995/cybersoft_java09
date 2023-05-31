package com.cybersoft.dto;

import com.cybersoft.common.BaseDTO;
import com.cybersoft.entity.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CourseDto extends BaseDTO {
    private Long id;
    private String title;
    private String image;
    private int lecturesCount;
    private double price;
    private int hourCount;
    private Integer categoryId;
    @JsonIgnore
    private Category category;
    private String categoryTitle;
    private String content;
    private String description;
    private int discount;
    private double promotionPrice;
    private int viewCount;
    private String userFullName;
    private String userEmail;
    private List<CourseContentDto> courseContentDtoList;

    public CourseDto(Long id, String title, String image, int lecturesCount, double price) {
        super();
        this.id = id;
        this.title = title;
        this.image = image;
        this.lecturesCount = lecturesCount;
        this.price = price;
    }

    public CourseDto() {
        super();
    }

    public CourseDto(Long id, String title, String image, double price) {
        super();
        this.id = id;
        this.title = title;
        this.image = image;
        this.price = price;
    }

    public CourseDto(Long id, String title, String image, int lecturesCount, double price, int hourCount,
                     String description, int discount, double promotionPrice, int viewCount) {
        super();
        this.id = id;
        this.title = title;
        this.image = image;
        this.lecturesCount = lecturesCount;
        this.price = price;
        this.hourCount = hourCount;
        this.description = description;
        this.discount = discount;
        this.promotionPrice = promotionPrice;
        this.viewCount = viewCount;
    }

    public CourseDto(Long id, String title, String image, int lecturesCount, double price, int hourCount,
                     String description, int discount, double promotionPrice, int viewCount, String content) {
        super();
        this.id = id;
        this.title = title;
        this.image = image;
        this.lecturesCount = lecturesCount;
        this.price = price;
        this.hourCount = hourCount;
        this.description = description;
        this.discount = discount;
        this.promotionPrice = promotionPrice;
        this.viewCount = viewCount;
        this.content = content;
    }

    public CourseDto(Long id, String title, String image, int lecturesCount, double price, int hourCount,
                     String description, int discount, double promotionPrice, int viewCount, String content, String userEmail, String userFullName) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.lecturesCount = lecturesCount;
        this.price = price;
        this.hourCount = hourCount;
        this.description = description;
        this.discount = discount;
        this.promotionPrice = promotionPrice;
        this.viewCount = viewCount;
        this.content = content;
        this.userEmail = userEmail;
        this.userFullName = userFullName;
    }

    public CourseDto(Long id, String title, String image, int lecturesCount, double price, int hourCount,
                     String description, int discount, double promotionPrice, int viewCount, String content, String userEmail, String userFullName,
                     String categoryTitle) {
        this.id = id;
        this.title = title;
        this.image = image;
        this.lecturesCount = lecturesCount;
        this.price = price;
        this.hourCount = hourCount;
        this.description = description;
        this.discount = discount;
        this.promotionPrice = promotionPrice;
        this.viewCount = viewCount;
        this.content = content;
        this.userEmail = userEmail;
        this.userFullName = userFullName;
		this.categoryTitle = categoryTitle;
    }


}
