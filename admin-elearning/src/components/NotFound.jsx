import React from "react";
import { Button, Result } from "antd";
import { PAGE_URL } from "../consts/path";
import { Navigate, Link } from 'react-router-dom';
import { ACCESS_TOKEN } from "../consts/common";


function NotFound() {

  const token = localStorage.getItem(ACCESS_TOKEN);

  if(!token) {
    return <Navigate to={PAGE_URL.LOGIN} />
  }

  return (
    <Result
      status="404"
      title="404"
      subTitle="Xin lỗi , trang bạn ghé thăm không tồn tại"
      extra={
        <Link to={PAGE_URL.HOME}>
          <Button type="primary">Back Home</Button>
        </Link>
      }
    />
  );
}

export default NotFound;
