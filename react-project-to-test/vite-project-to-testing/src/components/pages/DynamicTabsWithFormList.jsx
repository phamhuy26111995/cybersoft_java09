import React, { useState } from 'react';
import { Form, Input, Button, Tabs, Space } from 'antd';
import { PlusOutlined, MinusCircleOutlined } from '@ant-design/icons';

const { TabPane } = Tabs;


const DynamicTabsWithFormList = () => {
    const [form] = Form.useForm();
    const [tabCount, setTabCount] = useState(1);
    const [activeTab, setActiveTab] = useState('tab1');

    const addTab = () => {
        const newTabCount = tabCount + 1;
        const newTabKey = `tab${newTabCount}`;
        setTabCount(newTabCount);
        setActiveTab(newTabKey);
    };

    const onTabChange = (key) => {
        setActiveTab(key);
    };

    const onFinish = (values) => {
        console.log('Received values:', values);
    };

    return (
        <Form form={form} onFinish={onFinish} layout="vertical">
            <Tabs
                type="editable-card"
                onChange={onTabChange}
                activeKey={activeTab}
                onEdit={(targetKey, action) => {
                    console.log(action)
                    if (action === 'add') {
                        addTab();
                    }
                }}
            >
                {Array.from({ length: tabCount }, (_, i) => i + 1).map(tabIndex => (
                    <TabPane  tab={`Tab ${tabIndex}`} key={`tab${tabIndex}`}>
                        <Form.List name={`fields_${tabIndex}`}>
                            {(fields, { add, remove }) => (
                                <>
                                    {fields.map(({ key, name, ...restField }) => (
                                        <Space key={key} style={{ display: 'flex', marginBottom: 8 }} align="baseline">
                                            <Form.Item
                                                {...restField}
                                                name={[name, 'firstName']}
                                                rules={[{ required: true, message: 'Vui lòng nhập tên đầu tiên' }]}
                                            >
                                                <Input placeholder="Tên đầu tiên" />
                                            </Form.Item>
                                            <MinusCircleOutlined onClick={() => remove(name)} />
                                        </Space>
                                    ))}
                                    <Form.Item>
                                        <Button type="dashed" onClick={() => add()} icon={<PlusOutlined />}>
                                            Thêm trường
                                        </Button>
                                    </Form.Item>
                                </>
                            )}
                        </Form.List>
                    </TabPane>
                ))}
            </Tabs>
            <Form.Item>
                <Button type="primary" htmlType="submit">
                    Submit
                </Button>
            </Form.Item>
        </Form>
    );
};

export default DynamicTabsWithFormList;