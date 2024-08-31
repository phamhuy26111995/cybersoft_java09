import React, { useState } from "react";
import ChildOneComponent from "./ChildOneComponent";
import ChildThreeComponent from "./ChildThreeComponent";
import ChildTwoComponent from "./ChildTwoComponent";
import "../styles/_example.scss";
import { Col, Divider, Form, Row } from "antd";
import DateRangePickerComponent from "./DateRangePickerComponent";
import Input from "antd/lib/input/Input";

function HomePage() {
  function onChange(data) {
    console.log(data);
  }

  return (
    <React.Fragment>
      <Form>
        <Row gutter={[16, 16]}>
          <Col span={4}>
            <Form.Item label="First Name">
              <Input />
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="Last Name">
              <Input />
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="Age">
              <Input />
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="Clazz">
              <Input />
            </Form.Item>
          </Col>
        </Row>
        <Row gutter={[16, 16]}>
          <Col span={4}>
            <Form.Item label="First Name">
              <Input />
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="Last Name">
              <Input />
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="Age">
              <Input />
            </Form.Item>
          </Col>
          <Col span={4}>
            <Form.Item label="Clazz">
              <Input />
            </Form.Item>
          </Col>
        </Row>
      </Form>
    </React.Fragment>
  );
}

export default HomePage;
