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
  const [toggleValue, setToggleValue] = useState(false);

  function onChangeType(value) {
    setInputType(value);
  }

  function onToggleValue(value) {
    console.log(value);
    setToggleValue(value)
  }

  useEffect(() => {
    setInputType(inputTypeProp.type);
  }, [inputTypeProp]);

  return (
    <>
      <div style={{ display: "flex", alignItems: "center", width: 500 }}>
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
          {toggleValue || !inputTypeProp.title  ? (
            <Input onBlur={() => onToggleValue(false)} placeholder="Tiêu đề" />
          ) : (
            <span onClick={() => onToggleValue(true)}>{inputTypeProp.title}</span>
          )}
          {/* <span>{inputTypeProp.title}</span> */}
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
      </div>
    </>
  );
}

export default TabContentDetail;
