import React, { useState } from 'react';
import { Form, Input, Button, Select, Space } from 'antd';
import { MinusCircleOutlined, PlusOutlined } from '@ant-design/icons';

const { Option } = Select;

const DynamicFormWithDropdown = () => {
  const [form] = Form.useForm();
  const [formCount, setFormCount] = useState(2);

  const handleDropdownChange = (value) => {
    setFormCount(value);
    form.setFieldsValue({ forms: Array(value).fill({}) });
  };

  return (
    <Form form={form} name="dynamic_form_nest_item" onFinish={(values) => console.log(values)}>
      <Form.Item label="Select Number of Forms">
        <Select defaultValue={2} onChange={handleDropdownChange}>
          {Array.from({ length: 19 }, (_, i) => i + 2).map((num) => (
            <Option key={num} value={num}>
              {num}
            </Option>
          ))}
        </Select>
      </Form.Item>
      <Form.List name="forms">
        {(fields, { add, remove }) => (
          <>
            {fields.map(({ key, name, fieldKey, ...restField }) => (
              <Space key={key} style={{ display: 'flex', marginBottom: 8 }} align="baseline">
                <Form.Item
                  {...restField}
                  name={[name, 'name']}
                  fieldKey={[fieldKey, 'name']}
                >
                  <Input placeholder="Name" />
                </Form.Item>
                <Form.Item
                  {...restField}
                  name={[name, 'age']}
                  fieldKey={[fieldKey, 'age']}
                >
                  <Input placeholder="Age" />
                </Form.Item>
              </Space>
            ))}
          </>
        )}
      </Form.List>
      <Form.Item>
        <Button type="dashed" onClick={() => form.submit()} block icon={<PlusOutlined />}>
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};

export default DynamicFormWithDropdown;
