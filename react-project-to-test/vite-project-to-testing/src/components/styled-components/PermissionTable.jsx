import React, { useState } from "react";
import { Table, Checkbox, Form, Button } from "antd";

const PermissionTable = () => {
  const [form] = Form.useForm();
  const [data, setData] = useState([
    {
      key: "1",
      name: "Quyền màn hình user",
      code : "user.screen",
      read: false,
      create: false,
      update: false,
      delete: false,
    },
    // Bạn có thể thêm nhiều dòng khác nếu cần
  ]);

  const columns = [
    {
      title: "Tên quyền",
      dataIndex: "name",
      render: (text, record, index) => (
        <Checkbox
          checked={
            data[index].read &&
            data[index].create &&
            data[index].update &&
            data[index].delete
          }
          onChange={(e) => handleRowCheck(e, index)}
        >
          {text}
        </Checkbox>
      ),
    },
    {
      title: "Xem",
      dataIndex: "read",
      render: (text, record, index) => renderCheckbox("read", index),
    },
    {
      title: "Tạo mới",
      dataIndex: "create",
      render: (text, record, index) => renderCheckbox("create", index),
    },
    {
      title: "Thay đổi",
      dataIndex: "update",
      render: (text, record, index) => renderCheckbox("update", index),
    },
    {
      title: "Xóa",
      dataIndex: "delete",
      render: (text, record, index) => renderCheckbox("delete", index),
    },
  ];

  const renderCheckbox = (field, index) => (
    <Checkbox
      checked={data[index][field]}
      onChange={(e) => handleCheck(e, field, index)}
    />
  );

  const handleCheck = (e, field, index) => {
    const newData = [...data];
    newData[index][field] = e.target.checked;
    setData(newData);
  };

  const handleRowCheck = (e, index) => {
    const newData = [...data];
    newData[index] = {
      ...newData[index],
      read: e.target.checked,
      create: e.target.checked,
      update: e.target.checked,
      delete: e.target.checked,
    };
    setData(newData);
  };

  const handleSubmit = () => {
    // Xử lý dữ liệu form tại đây
    console.log(data);
    console.log(transformData(data));
  };

  function transformData(inputData) {
    const result = [];
    inputData.forEach(item => {
        ['read', 'create', 'update', 'delete'].forEach(action => {
            if (item[action]) {
                result.push(`${item.code}.${action}`);
            }
        });
    });
    return result;
}

  return (
    <Form form={form} onFinish={handleSubmit}>
      <Table columns={columns} dataSource={data} pagination={false} />
      <Form.Item>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};

export default PermissionTable;
