import { Table } from "antd";
import React from "react";

const { Column } = Table;
const data = [
  {
    key: "1",
    name: "test",
    age: 1000000000000,
    address: "New York No. 1 Lake Park",
    tags: "Test",
    clazz: "",
    eyes: "",
    homeTown: "",
  },
  {
    key: "2",
    name: "",
    age: 42,
    address: "London No. 1 Lake Park",
    tags: "Test",
    clazz: "",
    eyes: "",
    homeTown: "qweiopqwueioqwueiowqueiqowueiqwoeuqwioeuqwioeuwqioewuqioewuio",
  },
  {
    key: "3",
    name: "test",
    age: 32,
    address: "Sidney No. 1 Lake Park",
    tags: "Test",
    clazz: "",
    eyes: "",
    homeTown: "",
  },
];

function TableNumberOne() {
  return (
    <div className="table-number-one-wrapper">
      <Table tableLayout="auto" pagination={false} className="table-base-one" dataSource={data}>
        <Column width={40} dataIndex="name" title="Name" />
        <Column width={150} ellipsis={true} dataIndex="age" title="Age" />
        <Column dataIndex="address" title="Address" />
        <Column dataIndex="Tags" title="tags" />
        <Column dataIndex="Class" title="clazz" />
        <Column dataIndex="Eyes" title="eyes" />
      </Table>
    </div>
  );
}

export default TableNumberOne;
