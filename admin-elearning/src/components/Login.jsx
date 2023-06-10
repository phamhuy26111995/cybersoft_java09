import React, { useEffect } from "react";
import { Button, Form, Input } from "antd";
import authApi from "../api/auth/auth_api";
import { useNavigate } from "react-router-dom";
import { PAGE_URL } from "../consts/path";
import { useDispatch } from "react-redux";
import ElearningImage from "../assets/Elearning.png";
import {
  showLoading,
  hideLoading,
  showError,
} from "../redux-slices/globalSlice";
import { setUserInfo } from "../redux-slices/userSlice";
import { ACCESS_TOKEN } from "../consts/common";

function Login() {
  const [form] = Form.useForm();
  let navigate = useNavigate();
  const dispatch = useDispatch();

  useEffect(() => {
    if (localStorage.getItem(ACCESS_TOKEN)) {
      navigate(PAGE_URL.HOME);
    }
  }, []);
  const submitForm = async () => {
    let formData = await form.validateFields();
    try {
      let userLogin = await authApi.login(formData);
      if (userLogin && userLogin.token) {
        localStorage.setItem(ACCESS_TOKEN, userLogin.token);
        dispatch(setUserInfo(userLogin));
        navigate(PAGE_URL.HOME);
      }
    } catch (err) {
      dispatch(showError("Email hoặc password không hợp lệ"));
    }
  };

  return (
    <React.Fragment>
      <div className="form-container">
        <div style={{display :'flex' , justifyContent: 'center' , flexDirection: 'column'}}>
          <div style={{display :'flex' , justifyContent: 'center'}}>
            <div style={{ width: "200px" }}>
              <img src={ElearningImage} style={{ width: "100%" }} />
            </div>
          </div>
          <Form
            name="basic"
            labelCol={{
              span: 8,
            }}
            wrapperCol={{
              span: 16,
            }}
            style={{
              padding: "50px",
              border: "1px solid",
              borderRadius: "20px",
            }}
            initialValues={{
              remember: true,
            }}
            autoComplete="off"
            form={form}
          >
            <Form.Item
              label="Username"
              name="email"
              rules={[
                {
                  required: true,
                  message: "Please input your email!",
                },
              ]}
            >
              <Input />
            </Form.Item>

            <Form.Item
              label="Password"
              name="password"
              rules={[
                {
                  required: true,
                  message: "Please input your password!",
                },
              ]}
            >
              <Input.Password />
            </Form.Item>

            <Form.Item
              wrapperCol={{
                offset: 8,
                span: 16,
              }}
            >
              <Button onClick={submitForm} type="primary" htmlType="submit">
                Submit
              </Button>
            </Form.Item>
          </Form>
        </div>
      </div>
    </React.Fragment>
  );
}

export default Login;
