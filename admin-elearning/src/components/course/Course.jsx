import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  showLoading,
  hideLoading,
  showError,
} from "../../redux-slices/globalSlice";
import { useNavigate } from "react-router-dom";
import { fetchCourseByCondition } from "../../redux-slices/courseSlice";
import { Card, Table, Image } from "antd";
import PageHeader from "../PageHeader";
import SearchBar from "./SearchBar";
import CreateButton from "../buttons/CreateButton";
import { PAGE_URL } from "../../consts/path";
import { Link } from "react-router-dom";
import { EyeFilled } from "@ant-design/icons";
import { commonProps } from "../../consts/common";

const { Column } = Table;

function Course() {
  const [body, setBody] = useState({ ...commonProps.defaultPaginate });
  let courseList = useSelector((state) => state.course.courses);
  let total = useSelector((state) => state.course.total);

  const navigate = useNavigate();
  let dispatch = useDispatch();

  const handleChangePage = (pagination) => {
    setBody({
      ...body,
      pageIndex: pagination.current,
      pageSize: pagination.pageSize,
    });
  };
  useEffect(() => {
    dispatch(fetchCourseByCondition(body));
  }, [body]);

  return (
    <React.Fragment>
      <PageHeader
        title="Quản lý khóa học"
        extra={
          <CreateButton
            handleEvent={() =>
              navigate(PAGE_URL.COURSES.DETAIL.replace(":id", "new"))
            }
          />
        }
      />
      <SearchBar
        body={body}
        setBody={setBody}
        dispatch={dispatch}
        fetchCourseByCondition={fetchCourseByCondition}
      />
      <Card
        headStyle={{ backgroundColor: "gray", textAlign: "left" }}
        size="default"
        title="Kết quả"
      >
        <Table
          dataSource={courseList}
          pagination={{
            total: total,
            onChange : handleChangePage
          }}
        >
          <Column
            key={"action"}
            title="Số thứ tự"
            render={(record) => (
              <Link to={PAGE_URL.COURSES.INDEX + "/" + record.id}>
                <EyeFilled style={{ fontSize: "30px" }} />
              </Link>
            )}
          />
          <Column
            key={"numberOrder"}
            title="Số thứ tự"
            dataIndex="numberOrder"
          />
          <Column key={"title"} title="Tiêu đề" dataIndex="title" />
          <Column
            key={"image"}
            title="Hình ảnh"
            dataIndex="image"
            render={(record) => <Image src={record} width="100px" />}
          />
          <Column
            key={"lectureCount"}
            title="Số lượng bài học"
            dataIndex="lectureCount"
          />
          <Column
            key={"price"}
            title="Giá"
            dataIndex="price"
            render={(record) => commonProps.formatCurrency(record)}
          />
          <Column key={"hourCount"} title="Số giờ" dataIndex="hourCount" />
          <Column
            key={"content"}
            title="Nội dung khóa học"
            dataIndex="content"
          />
          <Column key={"description"} title="Mô tả" dataIndex="description" />
          <Column key={"discount"} title="Giảm giá" dataIndex="discount" />
          <Column
            key={"promotionPrice"}
            title="Giá khuyến mãi"
            dataIndex="promotionPrice"
            render={(record) => commonProps.formatCurrency(record)}
          />
          <Column key={"hourCount"} title="Lượt view" dataIndex="viewCount" />
          <Column
            key={"userFullName"}
            title="Giảng viên"
            dataIndex="userFullName"
          />
          <Column
            key={"userEmail"}
            title="Email giảng viên"
            dataIndex="userEmail"
          />
          <Column
            key={"categoryTitle"}
            title="Danh mục"
            dataIndex="categoryTitle"
          />
        </Table>
      </Card>
    </React.Fragment>
  );
}

export default Course;
