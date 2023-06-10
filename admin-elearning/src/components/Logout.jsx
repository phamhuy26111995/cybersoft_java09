import React from "react";
import { Button, Popover, Space } from "antd";
import { useNavigate } from "react-router-dom";
import { useSelector, useDispatch } from "react-redux";
import { PAGE_URL } from "../consts/path";
import { ACCESS_TOKEN } from "../consts/common";
import UserInfomation from "./category/UserInfomation";

function LogoutContent() {
  const navigate = useNavigate();

  const logout = () => {
    localStorage.removeItem(ACCESS_TOKEN);
    navigate(PAGE_URL.LOGIN);
  };
  return (
    <div>
      <Button onClick={logout}>Đăng xuất</Button>
    </div>
  );
}

function Logout() {
  const userInfo = useSelector((state) => state.user.userInfo);
  return (
    <React.Fragment>
      <Popover content={<LogoutContent />} title="Bạn muốn đăng xuát ?" trigger="hover">
        <div style={{ display: "flex" }}>
          <div>{userInfo && userInfo.avatar}</div>
          <div style={{ cursor : "pointer" , fontWeight : "bold" }}>{userInfo && userInfo.fullname}</div>
        </div>
      </Popover>
    </React.Fragment>
  );
}

export default Logout;
