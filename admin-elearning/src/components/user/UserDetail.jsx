import React, { useEffect, useState } from "react";
import { PAGE_URL } from "../../consts/path";
import PageHeader from "../PageHeader";
import SaveButton from "../buttons/SaveButton";
import { useParams } from "react-router-dom";
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
import { init, save,getUserDetail, clearState } from "../../redux-slices/userSlice";
import { useSelector, useDispatch } from "react-redux";

const { Column } = Table;

const { Option } = Select;

function UserDetail() {
  const [form] = Form.useForm();
  const roleList = useSelector((state) => state.user.roleList);
  const userDetailInfo = useSelector((state) => state.user.userDetailInfo);
  const dispatch = useDispatch();
  const [fileList, setFileList] = useState([]);
  const { id } = useParams();
  const isEdit = id !== "new";

  console.log(userDetailInfo);
  useEffect(() => {
    if(isEdit) {
        dispatch(getUserDetail({id : id}));
    }
    dispatch(init());

    return () => {
        dispatch(clearState());
        form.resetFields();
    }
  }, []);

    useEffect(() => {
        if(userDetailInfo) {
            form.setFieldsValue({
                fullname : userDetailInfo.fullname,
                email : userDetailInfo.email,
                roleId: userDetailInfo.roleId,
                image : [userDetailInfo.fileFromServer]
            })
        }

    }, [userDetailInfo]);

  const normFile = (e) => {
    if (Array.isArray(e)) {
      return e;
    }
    return e && e.fileList;
  };

  const customUploadRequest = ({ file, onSuccess }) => {
    setTimeout(() => {
      onSuccess();
    }, 2000);
  };

  const saveUser = async () => {
    let formData = await form.validateFields();

    let body = commonProps.createFormData(
      {
        fullname: formData.fullname,
        email : formData.email,
        password : formData.password,
        roleId : formData.roleId
      },
      formData.image[0].originFileObj
    );

    dispatch(save(body));
  };

  const updateUser = () => {};

  console.log(userDetailInfo);

  return (
    <React.Fragment>
      <PageHeader title="Chi tiết người dùng" backUrl={PAGE_URL.USERS.INDEX} />
      <Card
        headStyle={{ backgroundColor: "gray", textAlign: "left" }}
        size="default"
        title="Thêm mới người dùng"
        style={{ marginBottom: "1.5rem" }}
      >
        <Form layout="vertical" form={form}>
          <Row {...commonProps.rowGutterForColTwo}>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Họ tên" name="fullname">
                <Input />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Email" name="email">
                <Input />
              </Form.Item>
            </Col>
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item label="Password" name="password">
                <Input type="password" />
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
            <Col className="gutter-row" {...commonProps.colWillDivideTwo}>
              <Form.Item
                label="Quyền"
                name="roleId"
                rules={[commonProps.rule.required]}
              >
                <Select>
                  {roleList.map((role) => (
                    <Option key={role.id} value={role.id}>
                      {role.description}
                    </Option>
                  ))}
                </Select>
              </Form.Item>
            </Col>
          </Row>
        </Form>
      </Card>
      {isEdit ? (
        <SaveButton handleEvent={updateUser} />
      ) : (
        <SaveButton handleEvent={saveUser} />
      )}
    </React.Fragment>
  );
}

export default UserDetail;
