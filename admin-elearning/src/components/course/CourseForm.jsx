import React, { useState, useEffect } from "react";
import {
  Card,
  Table,
  Image,
  Form,
  Row,
  Col,
  Input,
  InputNumber,
  Upload,
  Button,
  Select,
} from "antd";
import { commonProps } from "../../consts/common";
import { UploadOutlined } from "@ant-design/icons";
import { useSelector, useDispatch } from "react-redux";
import { getAll, clearState } from "../../redux-slices/categorySlice";
import categoryApi from "../../api/category/category_api";
import {
  hideLoading,
  showError,
  showLoading,
} from "../../redux-slices/globalSlice";

const currencyFormatter = (value) => {
  if (value) {
    let newValue = new Intl.NumberFormat("vi-VN", {
      style: "currency",
      currency: "VND",
    })
      .format(value)
      .toString();
    return newValue;
  }
  return "";
};

const currencyParser = (value) => {
  // Remove non-digits from the input value
  const parsedValue = value.replace(/[^\d]/g, "");
  return parseInt(parsedValue, 10);
};

const { Option } = Select;

function CourseForm(props) {
  const { courseForm } = props;
  const [fileList, setFileList] = useState([]);
  const categories = useSelector((state) => state.category.categories);
  const dispatch = useDispatch();

  useEffect(() => {
    getCategories();

    return () => {
      dispatch(clearState());
    };
  }, []);

  const getCategories = async () => {
    dispatch(showLoading());
    try {
      let data = await categoryApi.getAll();
      dispatch(getAll(data));
      dispatch(hideLoading());
    } catch (err) {
      dispatch(showError("Không thể lấy được danh mục"));
      dispatch(hideLoading());
    }
  };

  const customUploadRequest = ({ file, onSuccess }) => {
    setTimeout(() => {
      onSuccess();
    }, 2000);
  };

  const normFile = (e) => {
    if (Array.isArray(e)) {
      return e;
    }
    return e && e.fileList;
  };


  return (
    <React.Fragment>
      <Card
        headStyle={{ backgroundColor: "gray", textAlign: "left" }}
        size="default"
        title="Thông tin khóa học"
        style={{ marginBottom: "1.5rem" }}
      >
        <Form layout="vertical" form={courseForm}>
          <Row {...commonProps.rowGutterForColTwo}>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Tiêu đề khóa học" name="title">
                <Input />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Giá bán" name="price">
                <InputNumber
                  formatter={currencyFormatter}
                  parser={currencyParser}
                  defaultValue={0}
                  style={{ width: "100%" }}
                />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Số bài học" name="lecturesCount">
                <InputNumber style={{ width: "100%" }} />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Giá khuyến mãi" name="promotionPrice">
                <InputNumber
                  formatter={currencyFormatter}
                  parser={currencyParser}
                  defaultValue={0}
                  style={{ width: "100%" }}
                />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Số giờ học" name="hourCount">
                <InputNumber style={{ width: "100%" }} />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Mô tả" name="description">
                <Input />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Danh mục" name="categoryId" rules={[commonProps.rule.required]}>
                <Select>
                  {categories.map((category) => (
                    <Option key={category.id} value={category.id}>
                      {category.title}
                    </Option>
                  ))}
                </Select>
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item
                label="Hình đại diện"
                name="image"
                valuePropName="fileList"
                getValueFromEvent={normFile}
              >
                <Upload
                  customRequest={customUploadRequest}
                  listType="picture"
                  maxCount={1}
                  fileList={[...fileList]}
                  accept="image/*"
                >
                  <Button icon={<UploadOutlined />}>Select File</Button>
                </Upload>
              </Form.Item>
            </Col>
          </Row>
          <Row>
            <Col span={24}>
              <Form.Item label="Nội dung" name="content">
                <Input.TextArea rows={4} />
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Card>
    </React.Fragment>
  );
}

export default CourseForm;
