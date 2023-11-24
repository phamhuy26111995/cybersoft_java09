import React, { useContext } from "react";
import FormProvider, { FormContext } from "../form-context/FormProvider";
import EditableTabs from "./EditableTabs";
import { Button, Col, Form, Input, Row, Select } from "antd";
import TextArea from "antd/lib/input/TextArea";

const { Option } = Select;

function ProductPage() {
  const [productForm] = Form.useForm();
  const form = useContext(FormContext);

  function submitFormData() {
    console.log(productForm.getFieldsValue());
    console.log(form.getFieldsValue());
  }

  return (
    <React.Fragment>
      <Form layout="vertical" form={productForm}>
        <Row>
          <Col span={8}>
            <Form.Item
              style={{ width: 300 }}
              label={"Tên sản phẩm"}
              name="title"
            >
              <Input />
            </Form.Item>
          </Col>
          <Col span={8}>
            <Form.Item
              style={{ width: 300 }}
              label="Mô tả sản phẩm"
              name="description"
            >
              <TextArea />
            </Form.Item>
          </Col>
          <Col span={8}>
            <Form.Item label={"Danh mục"} name="categoryId">
              <Select>
                <Option value={1}>Du học</Option>
                <Option value={2}>Du lịch</Option>
                <Option value={3}>Định cư</Option>
              </Select>
            </Form.Item>
          </Col>
        </Row>
      </Form>
      <EditableTabs />
      <Button onClick={submitFormData}>Submit Form Data</Button>
    </React.Fragment>
  );
}

function ProductPageWrapper() {
  return (
    <FormProvider>
      <ProductPage />
    </FormProvider>
  );
}

export default ProductPageWrapper;
