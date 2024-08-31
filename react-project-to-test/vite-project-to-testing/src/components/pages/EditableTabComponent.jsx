import React, { useState } from 'react';
import { Form, Input, Tabs, Button } from 'antd';
import { PlusOutlined, EditOutlined } from '@ant-design/icons';

const EditableTabs = () => {
    const [form] = Form.useForm();
    const [tabs, setTabs] = useState([{ key: 'tab1', name: 'Tab 1', isEditing: false }]);
    const [activeKey, setActiveKey] = useState('tab1');

    // Logic thêm và xóa tab giữ nguyên...

    const addTab = () => {
        const newKey = `tab${tabs.length + 1}`;
        const newName = `Tab ${tabs.length + 1}`;
        setTabs([...tabs, { key: newKey, name: newName }]);
        setActiveKey(newKey);
    };

    const removeTab = (targetKey) => {
        let newActiveKey = activeKey;
        let lastIndex;
        tabs.forEach((tab, i) => {
            if (tab.key === targetKey) {
                lastIndex = i - 1;
            }
        });
        const newTabs = tabs.filter(tab => tab.key !== targetKey);
        if (newTabs.length && newActiveKey === targetKey) {
            if (lastIndex >= 0) {
                newActiveKey = newTabs[lastIndex].key;
            } else {
                newActiveKey = newTabs[0].key;
            }
        }
        setTabs(newTabs);
        setActiveKey(newActiveKey);
    };

    const onTabChange = (key) => {
        setActiveKey(key);
    };

    const onTabEdit = (targetKey, action) => {
        if (action === 'add') {
            addTab();
        } else {
            removeTab(targetKey);
        }
    };

    const handleTabNameChange = (e, key) => {
        console.log(e.target.value, key )
        const newTabs = tabs.map(tab => {
            if (tab.key === key) {
                return { ...tab, name: e.target.value };
            }
            return tab;
        });
        console.log(newTabs)
        setTabs(newTabs);
    };


    const toggleEdit = (key, isEditing) => {
        const newTabs = tabs.map(tab => {
            if (tab.key === key) {
                return { ...tab, isEditing };
            }
            return tab;
        });
        setTabs(newTabs);
    };

    return (
        <Form form={form} layout="vertical">
            <Tabs
                type="editable-card"
                activeKey={activeKey}
                onChange={onTabChange}
                onEdit={onTabEdit}
            >
                {tabs.map(tab => (
                    <Tabs.TabPane
                        tab={
                            tab.isEditing ? (
                                <Input
                                    autoFocus
                                    defaultValue={tab.name}
                                    onBlur={() => toggleEdit(tab.key, false)}
                                    onPressEnter={(e) => handleTabNameChange(e, tab.key)}
                                    onKeyDown={(e) => {
                                        if (e.key === 'Enter') {
                                            toggleEdit(tab.key, false);
                                        }
                                    }}
                                />
                            ) : (
                                <span onDoubleClick={() => toggleEdit(tab.key, true)}>
                  {tab.name}
                </span>
                            )
                        }
                        key={tab.key}
                    >
                        Content of {tab.name}
                    </Tabs.TabPane>
                ))}
            </Tabs>
            <Button type="primary" onClick={addTab} icon={<PlusOutlined />}>
                Add Tab
            </Button>
        </Form>
    );
};

export default EditableTabs;