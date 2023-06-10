import React from "react";
import { Card, Table, Image, Form, Row, Col, Input } from "antd";
import { commonProps } from "../../consts/common";
import SearchButton from "../buttons/SearchButton";

function SearchBar(props) {
  const [form] = Form.useForm();
  const {dispatch, fetchCourseByCondition, body , setBody} = props;
  
  const submitForm = () => {
    let formData = form.getFieldsValue();

    let body = {
      ...commonProps.defaultPaginate,
      ...formData
    }

    dispatch(fetchCourseByCondition(body));
    setBody(body);
  }

  return (
    <React.Fragment>
      <Card
        headStyle={{ backgroundColor: "gray", textAlign: "left" }}
        style={{ marginBottom: "1.5rem" }}
        size="default"
        title="Tìm kiếm"
      >
        <Form layout="vertical" form={form}>
          <Row {...commonProps.rowGutterForColTwo}>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item
                label="Tiêu đề khóa học"
                name="courseName"
              >
                <Input />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item
                label="Tên giảng viên"
                name="courseUserName"
              >
                <Input />
              </Form.Item>
            </Col>
          </Row>
          <SearchButton handleEvent={submitForm} />
        </Form>
      </Card>
    </React.Fragment>
  );
}

export default SearchBar;
