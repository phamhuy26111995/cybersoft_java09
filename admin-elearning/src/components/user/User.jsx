import React, { useEffect, useState } from "react";
import { useSelector, useDispatch } from "react-redux";
import {
  showLoading,
  hideLoading,
  showError,
} from "../../redux-slices/globalSlice";
import { useNavigate } from "react-router-dom";
import { fetchAllUser } from "../../redux-slices/userSlice";
import { Card, Table, Image } from "antd";
import PageHeader from "../PageHeader";
import SearchBar from "./SearchBar";
import CreateButton from "../buttons/CreateButton";
import { PAGE_URL } from "../../consts/path";
import { Link } from "react-router-dom";
import { EyeFilled } from "@ant-design/icons";
import { commonProps } from "../../consts/common";

const { Column } = Table;

function User() {
  const navigate = useNavigate();
  const userList = useSelector((state) => state.user.userList);
  const dispatch = useDispatch();
  const total = useSelector((state) => state.user.total);
  const [body, setBody] = useState({ ...commonProps.defaultPaginate });

  useEffect(() => {
    dispatch(fetchAllUser(body));
  }, [body]);

  return (
    <React.Fragment>
      <PageHeader
        title="Quản lý người dùng"
        extra={
          <CreateButton
            handleEvent={() =>
              navigate(PAGE_URL.USERS.DETAIL.replace(":id", "new"))
            }
          />
        }
      />
      <SearchBar setBody={setBody} body={body} />
      <Card
        headStyle={{ backgroundColor: "gray", textAlign: "left" }}
        size="default"
        title="Kết quả"
      >
        <Table dataSource={userList}>
          <Column
            key={"action"}
            title="Số thứ tự"
            render={(record) => (
              <Link to={PAGE_URL.USERS.DETAIL.replace(":id", record.id)}>
                <EyeFilled style={{ fontSize: "30px" }} />
              </Link>
            )}
          />
          <Column
            key={"numberOrder"}
            title="Số thứ tự"
            dataIndex="numberOrder"
          />
          <Column key={"fullname"} title="Họ tên" dataIndex="fullname" />
          <Column
            key={"avatar"}
            title="Avatar"
            dataIndex="avatar"
            render={(record) => (
              <Image src={record} style={{ maxWidth: "100px" }} />
            )}
          />
          <Column key={"email"} title="Email người dùng" dataIndex="email" />
          <Column key={"roleDesc"} title="Quyền" dataIndex="roleDesc" />
        </Table>
      </Card>
    </React.Fragment>
  );
}

export default User;
