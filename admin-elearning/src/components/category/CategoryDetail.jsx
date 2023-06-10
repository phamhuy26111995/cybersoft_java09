import React, { useState, useEffect } from "react";
import { Card, Col, Upload, Row, Form, Input, Button } from "antd";
import { useParams } from "react-router-dom";

import { commonProps } from "../../consts/common";
import PageHeader from "../PageHeader";
import { PlusOutlined, UploadOutlined } from "@ant-design/icons";
import SaveButton from "../buttons/SaveButton";
import { useSelector, useDispatch } from "react-redux";
import { useNavigate } from 'react-router-dom';
import categoryApi from "../../api/category/category_api";
import {
  showLoading,
  hideLoading,
  showError,
  showSuccess,
} from "../../redux-slices/globalSlice";
import { detail, clearState } from "../../redux-slices/categorySlice";
import { API_CALL } from "../../api/api_call";
import { API_URL, PAGE_URL } from "../../consts/path";




function CategoryDetail() {
  const [fileList , setFileList] = useState([]);
  const navigate = useNavigate();
  const categoryDetail = useSelector((state) => state.category.categoryDetail);
  const dispatch = useDispatch();
  const [form] = Form.useForm();
  let { id } = useParams();
  let isEdit = id !== "new";

  useEffect(() => {
    if (isEdit) {
      getDetail(id);
    }
    return () => {
      dispatch(clearState());
      form.resetFields();
    };
  }, []);

  useEffect(() => {
    if (categoryDetail) {
      form.setFieldsValue({
        title: categoryDetail.title,
        upload : [categoryDetail.fileFromServer]
      });
      setFileList([categoryDetail.fileFromServer]);

    }
  }, [categoryDetail]);

  const getDetail = async (id) => {
    dispatch(showLoading());
    let response = await API_CALL.get(
      API_URL.CATEGORIES.GET_BY_ID.replace(":id", `${id}`)
    );
    dispatch(detail(response));
    dispatch(hideLoading());
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

  const submitForm = async () => {
    let data = await form.validateFields();

    let formData = commonProps.createFormData(
      { title: data.title },
      data.upload[0].originFileObj
    );

    try {
      dispatch(showLoading());
      let response = await categoryApi.save(formData);
      dispatch(detail(response.data));
      navigate(PAGE_URL.CATEGORIES.DETAIL.replace(":id",`${response.data.id}`));
      dispatch(hideLoading());
      dispatch(showSuccess("Save danh mục thành công"));
    } catch (err) {
      dispatch(showError("Save danh mục không thành công"));
      dispatch(hideLoading());
    }
  };

  const submitFormEdit = async () => {
    let data = await form.validateFields(["title"]);

    let file = form.getFieldValue("upload");


    if (file) {
      if(file.length < 0) {
        let fileData = await form.validateFields(["upload"]);
        
      }

      file = file[0].originFileObj
      

    }


    let formData = commonProps.createFormData(
      { id: id, title: data.title },
      file
    );

    try {
      dispatch(showLoading());
      let response = await categoryApi.update(formData);
      dispatch(detail(response.data));
      dispatch(hideLoading());
      dispatch(showSuccess("Save danh mục thành công"));
    } catch (err) {
      dispatch(showError("Save danh mục không thành công"));
      dispatch(hideLoading());
    }
  };

  return (
    <React.Fragment>
      <PageHeader
        title="Chi tiết danh mục"
        backUrl={PAGE_URL.CATEGORIES.INDEX}
      />
      <Card title="Thông tin chung" style={{ marginBottom: 20 }}>
        <Form layout="vertical" form={form}>
          <Row {...commonProps.rowGutterForColTwo}>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item
                label="Tên danh mục"
                name="title"
                rules={[
                  commonProps.rule.required(
                    "Vui lòng không được bỏ trống tên danh mục"
                  ),
                ]}
              >
                <Input />
              </Form.Item>
            </Col>
          </Row>

          <Row {...commonProps.rowGutterForColTwo}>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
             {(fileList.length > 0 || !isEdit) &&  <Form.Item
                name="upload"
                label="Upload"
                valuePropName="fileList"
                getValueFromEvent={normFile}
                rules={[
                  commonProps.rule.required(
                    "Vui lòng không được bỏ trống tên danh mục"
                  ),
                ]}
              >
                <Upload
                  name="logo"
                  customRequest={customUploadRequest}
                  listType="picture"
                  maxCount={1}
                  fileList={[...fileList]}
                  // defaultFileList={fileList}
                  accept="image/*"
                >
                  <Button icon={<UploadOutlined />}>Select File</Button>
                </Upload>
              </Form.Item>}
            </Col>
          </Row>
        </Form>
      </Card>
      {isEdit ? (
        <SaveButton handleEvent={submitFormEdit} />
      ) : (
        <SaveButton handleEvent={submitForm} />
      )}
    </React.Fragment>
  );
}

export default CategoryDetail;
