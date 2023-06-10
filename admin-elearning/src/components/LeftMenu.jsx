import React from "react";
import { Link } from "react-router-dom";
import {
    UserOutlined,
    BookOutlined, 
    VideoCameraOutlined,
    ReadOutlined, 
  } from '@ant-design/icons';
import { PAGE_URL } from "../consts/path";


export const LEFT_MENU = [
    {
        key : 1,
        icon : <Link to={PAGE_URL.CATEGORIES.INDEX}>
            <BookOutlined />
        </Link>,
        label : `Quản lý danh mục`
    },
    {
        key : 2,
        icon : <Link to={PAGE_URL.COURSES.INDEX}>
            <ReadOutlined />
        </Link>,
        label : `Quản lý khóa học`
    },
    {
        key : 3,
        icon : <Link to={PAGE_URL.USERS.INDEX}>
            <UserOutlined />
        </Link>,
        label : `Quản lý người dùng`
    }
]