import { Form, Input, Space, Select, Popconfirm } from "antd";
import React, { useContext, useEffect, useState } from "react";
import { MinusCircleOutlined } from "@ant-design/icons";
import TextArea from "antd/lib/input/TextArea";
import { FormContext } from "../form-context/FormProvider";

const { Option } = Select;

function TabContentDetail(props) {
  const { keyProp, remove, tabContentKey } = props;
  const form = useContext(FormContext);
  const inputTypeProp = form.getFieldValue(tabContentKey)[keyProp] || "text";
  const [inputType, setInputType] = useState("text");

  function onChangeType(value) {
    setInputType(value);
  }

  useEffect(() => {
    setInputType(inputTypeProp.type);
  }, [inputTypeProp]);

  return (
    <div>
      <Popconfirm
        title="Bạn có chắc chắn muốn đóng tab này không?"
        onConfirm={() => remove(keyProp)}
        okText="Có"
        cancelText="Không"
        placement="bottomLeft"
      >
        <MinusCircleOutlined />
      </Popconfirm>
      <Form.Item
        name={[keyProp, "title"]}
        noStyle
        rules={[{ required: true, message: "Vui lòng nhập tên đầu tiên" }]}
      >
        <Input placeholder="Tiêu đề" />
      </Form.Item>
      <Form.Item
        noStyle
        name={[keyProp, "content"]}
        rules={[{ required: true, message: "Vui lòng nhập họ" }]}
      >
        {inputType === "text" ? (
          <Input placeholder="Nội dung" />
        ) : (
          <TextArea placeholder="Nội dung" />
        )}
      </Form.Item>
      <Form.Item noStyle name={[keyProp, "type"]} initialValue={inputType}>
        <Select onChange={onChangeType}>
          <Option value="text">Text</Option>
          <Option value="textarea">TextArea</Option>
        </Select>
      </Form.Item>
      <Form.Item noStyle name={[keyProp, "tabContentId"]}>
        <Input disabled width={50} />
      </Form.Item>
    </div>
  );
}

export default TabContentDetail;
