import { Table } from "antd";
import React from "react";
import { useEffect } from "react";
import { fetchUsers } from "../../redux-slices/userSlice";
import { useSelector, useDispatch } from "react-redux";


const { Column } = Table;


function UserInfoTable() {
    const dispatch = useDispatch();
    const {users} = useSelector(state => state.userSlice);

    useEffect(() => {
        dispatch(fetchUsers());
    },[])

    console.log(users)
   
  return (
    <div className="table-number-two-wrapper">
      <Table
        scroll={{  y : 700}}
        className="table-base-two"
        dataSource={users}
        pagination={false}
        tableLayout="auto"
      >
        <Column title="Id" dataIndex={"id"}  />
        <Column title="First name" dataIndex={"first_name"}  />
        <Column title="Last name" dataIndex={"last_name"}  />
        <Column title="Super Long Long LongLong Long Long Long LongLong Email" dataIndex={"email"} render={(data) => <div style={{minWidth : 300}}></div>}  />
        <Column title="Gender" dataIndex={"gender"}  />
        
      </Table>
    </div>
  );
}

export default UserInfoTable;
