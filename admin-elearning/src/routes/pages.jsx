import { PAGE_URL } from "../consts/path";
import { Route } from "react-router-dom";
import Login from "../components/Login";
import Category from "../components/category/Category";
import NotFound from "../components/NotFound";
import Users from "../components/Users";
import Course from "../components/course/Course";
import CategoryDetail from "../components/category/CategoryDetail";
import CourseDetail from "../components/course/CourseDetail";
import Test from "../components/Test";

export const PAGES = {
    noAuthPages : [
        <Route key={PAGE_URL.LOGIN} element={<Login/>} path={PAGE_URL.LOGIN} exact/>
    ],
    authPages : [
        <Route key={PAGE_URL.CATEGORIES.INDEX} element={<Category/>} path={PAGE_URL.CATEGORIES.INDEX} exact/>,
        <Route key={PAGE_URL.HOME} element={<Users/>} path={PAGE_URL.HOME} exact/>,
        <Route key={PAGE_URL.USERS.INDEX} element={<Users/>} path={PAGE_URL.USERS.INDEX} exact/>,
        <Route key={PAGE_URL.COURSES.INDEX} element={<Course/>} path={PAGE_URL.COURSES.INDEX} exact/>,
        <Route key={PAGE_URL.CATEGORIES.DETAIL} element={<CategoryDetail/>} path={PAGE_URL.CATEGORIES.DETAIL} />,
        <Route key={PAGE_URL.COURSES.DETAIL} element={<CourseDetail/>} path={PAGE_URL.COURSES.DETAIL} />,
        <Route key={PAGE_URL.ALL} element={<NotFound />} path={PAGE_URL.ALL} />, 
        <Route key={PAGE_URL.TEST} element={<Test />} path={PAGE_URL.TEST} />
    ]
        
};




