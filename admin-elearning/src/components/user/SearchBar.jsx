import React, {useEffect} from "react";
import { Card, Table, Image, Form, Row, Col, Input } from "antd";
import { commonProps } from "../../consts/common";
import SearchButton from "../buttons/SearchButton";

function SearchBar(props) {
  const [form] = Form.useForm();
  const { setBody, body } = props;

  const eventEnter = (event) => {
    if (event.keyCode === 13) {
      submitForm();
    }
  }

  useEffect(() => {
    document.addEventListener('keydown', eventEnter);
    return () => {
      document.removeEventListener('keydown', eventEnter);
    }
  },[])

  const submitForm = async () => {
    let formData = form.getFieldsValue();
    setBody({ ...body, ...formData });
  };

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
              <Form.Item label="Tên người dùng" name="userName">
                <Input />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Email người dùng" name="userEmail">
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
