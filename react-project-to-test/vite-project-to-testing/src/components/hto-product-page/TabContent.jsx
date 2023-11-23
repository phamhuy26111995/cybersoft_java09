import { Form, Button, Space, Input } from "antd";
import React from "react";
import { PlusOutlined, CloseOutlined,MinusCircleOutlined } from "@ant-design/icons";
import TabContentDetail from "./TabContentDetail";


function TabContent(props) {
  const { tabKey } = props;
    
  return (
    <Form.List name={`tabContent${tabKey}`}>
      {(fields, { add, remove }) => (
        <>
          {fields.map(({ key, name, ...restField }) => (
            <TabContentDetail key={`${tabKey}-${name}`} tabContentKey={`tabContent${tabKey}`}   keyProp={name} remove={remove} />
          ))}
          <Form.Item>
            <Button
              type="dashed"
              onClick={() => add()}
              block
              icon={<PlusOutlined />}
            >
              Thêm trường
            </Button>
          </Form.Item>
        </>
      )}
    </Form.List>
  );
}

export default TabContent;
