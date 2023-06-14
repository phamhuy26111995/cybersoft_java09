import React, { useState, useEffect } from "react";
import { Card, Col, Upload, Row, Form, Input, Button } from "antd";
import { useParams } from "react-router-dom";

import { commonProps } from "../../consts/common";
import PageHeader from "../PageHeader";
import { PlusOutlined, UploadOutlined } from "@ant-design/icons";
import SaveButton from "../buttons/SaveButton";
import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from "react-router-dom";
import { PAGE_URL } from "../../consts/path";
import CourseForm from "./CourseForm";
import CourseContentForm from "./CourseContentForm";
import {
  saveCourse,
  getDetail,
  editCourse,
  clearState
} from "../../redux-slices/courseSlice";

function CourseDetail() {
  const [fileList, setFileList] = useState([]);
  const [deleteCourseContent, setDeleteCourseContent] = useState([]);
  const [deleteVideo, setDeleteVideo] = useState([]);
  const courseDetail = useSelector((state) => state.course.courseDetail);
  const navigate = useNavigate();
  const dispatch = useDispatch();
  const [courseForm] = Form.useForm();
  const [courseContentForm] = Form.useForm();


  let { id } = useParams();
  let isEdit = id !== "new";

  useEffect(() => {
    if (isEdit) {
      dispatch(getDetail({ id: id }));
    }

    return () => {
      dispatch(clearState());
    }
  }, []);

  useEffect(() => {
    if (courseDetail) {
      courseForm.setFieldsValue({
        title: courseDetail.title,
        image: [courseDetail.fileFromServer],
        lecturesCount: courseDetail.lecturesCount,
        promotionPrice: courseDetail.promotionPrice,
        hourCount: courseDetail.hourCount,
        description: courseDetail.description,
        categoryId: courseDetail.categoryId,
        content: courseDetail.content,
        price: courseDetail.price,
      });

      courseContentForm.setFieldsValue({
        courseContentDtoList: courseDetail.courseContentDtoList,
      });
    }
  }, [courseDetail]);

  const getFormData = async () => {
    let courseFormData = await courseForm.validateFields();
    let courseContentFormData = await courseContentForm.validateFields();

    let file = courseFormData.image[0].originFileObj;

    delete courseFormData.image;

    let formData = commonProps.createFormData({ ...courseFormData }, file);

    let body = {
      courseInfo: formData,

      courseContentInfo: {
        ...courseContentFormData,
      },
    };
    dispatch(saveCourse(body));
  };

  const updateFormData = async () => {
    let courseFormData = await courseForm.validateFields();
    let courseContentFormData = await courseContentForm.validateFields();

    let file = courseFormData.image[0] && courseFormData.image[0].originFileObj;

    delete courseFormData.image;

    let formData = commonProps.createFormData(
      { ...courseFormData, id: +id },
      file
    );

    let body = {
      courseInfo: formData,
    };

    body.deleteCourseContent = deleteCourseContent;
    body.deleteVideo = deleteVideo;
    body.courseContentInfo = courseContentFormData;
    dispatch(editCourse(body));
    setDeleteCourseContent([]);
    setDeleteVideo([]);
  };

  return (
    <React.Fragment>
      <PageHeader title="Chi tiết khóa học" backUrl={PAGE_URL.COURSES.INDEX} />
      <CourseForm courseForm={courseForm} />
      <CourseContentForm
        courseContentForm={courseContentForm}
        setDeleteCourseContent={setDeleteCourseContent}
        setDeleteVideo={setDeleteVideo}
        isEdit={isEdit}
      />
      <br />
      {isEdit ? (
        <SaveButton handleEvent={updateFormData} />
      ) : (
        <SaveButton handleEvent={getFormData} />
      )}
    </React.Fragment>
  );
}

export default CourseDetail;
