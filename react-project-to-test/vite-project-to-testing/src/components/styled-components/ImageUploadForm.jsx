import React from 'react';
import { Form, Upload, Button } from 'antd';
import { UploadOutlined } from '@ant-design/icons';

const FileUploadComponent = () => {
  const onFinish = (values) => {
    // Tạo FormData để gửi lên server
    const formData = new FormData();
    if (values.files && values.files.length > 0) {
      // Chỉ thêm file đầu tiên vào FormData
      formData.append('file', values.files[0].originFileObj);
    }

    // Gửi formData đến server
    // fetch('/api/upload', { method: 'POST', body: formData });

    console.log('FormData:', formData);
  };

  const normFile = (e) => {
    if (Array.isArray(e)) {
      return e;
    }
    return e && e.fileList;
  };

  return (
    <Form onFinish={onFinish}>
      <Form.Item name="upload">
        <Form.List name="files">
          {(fields, { add, remove }) => (
            <>
              {fields.map(({ key, name, ...restField }) => (
                <Form.Item
                  key={key}
                  name={name}
                  valuePropName="fileList"
                  getValueFromEvent={normFile}
                  noStyle
                >
                  <Upload name="logo" listType="picture" beforeUpload={() => false}>
                    <Button icon={<UploadOutlined />}>Select Image</Button>
                  </Upload>
                </Form.Item>
              ))}
              {fields.length < 1 && <Button onClick={() => add()}>Add File</Button>}
            </>
          )}
        </Form.List>
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit">
          Submit
        </Button>
      </Form.Item>
    </Form>
  );
};

export default FileUploadComponent;