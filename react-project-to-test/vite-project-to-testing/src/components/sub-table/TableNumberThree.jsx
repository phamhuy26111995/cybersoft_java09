import { Table } from "antd";
import React from "react";

const { Column } = Table;
const data = [
  {
    key: "1",
    name: "test",
    age: 123,
    address:
      "New York No. 1 Lake Park New York No. 1 Lake Park New York No. 1 Lake Park New York No. 1 Lake Park",
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
    tags: "asdadasdasd",
    clazz: "",
    eyes: "dwqdqwdqwdqwdqwdqwdqwdqwdqwdwqd",
    homeTown: "",
  },
];

function TableNumberThree() {
  const dataTransfer = data.map((el) => {});

  return (
    <div className="table-number-three-wrapper" style={{ maxWidth: "1000px" }}>
      <Table
        tableLayout="auto"
        pagination={false}
        className="table-base-three"
        dataSource={data}
        scroll={{ y : 100}}
      >
        <Column fixed="left" dataIndex="name" title="Name" />
        <Column
          ellipsis={true}
          fixed="left"
          dataIndex="address"
          title="Address"
          
          render={(columnData) => <div className="text-address">
            {columnData}
          </div>}
        />
        <Column dataIndex="age" title="Age" />
        <Column dataIndex="tags" title="tags" />
        <Column width={150} dataIndex="class" title="clazz" />
        <Column dataIndex="eyes" title="eyes" />
       
      </Table>
    </div>
  );
}

export default TableNumberThree;
