import {useContext, useMemo, useState} from "react";
import {Form, Input, Tabs, Button, Popconfirm} from "antd";
import {PlusOutlined, CloseOutlined} from "@ant-design/icons";
import TabContent from "./TabContent";
import {FormContext} from "../form-context/FormProvider";

function getRandomInteger(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

function EditableTabs() {
    const form = useContext(FormContext);
    const [tabs, setTabs] = useState([
        {key: "productCode0", name: "Default Tab", isEditing: false},
    ]);
    const [activeKey, setActiveKey] = useState("productCode0");

    function addTab() {
        const newKey = `productCode${getRandomInteger(1, 500)}`;
        const newName = `New Tab`;
        setTabs([...tabs, {key: newKey, name: newName}]);
        setActiveKey(newKey);
    };

    function removeTab(targetKey) {
        let newActiveKey = activeKey;
        let lastIndex;
        tabs.forEach((tab, i) => {
            if (tab.key === targetKey) {
                lastIndex = i - 1;
            }
        });
        const newTabs = tabs.filter((tab) => tab.key !== targetKey);
        if (newTabs.length && newActiveKey === targetKey) {
            if (lastIndex >= 0) {
                newActiveKey = newTabs[lastIndex].key;
            } else {
                newActiveKey = newTabs[0].key;
            }
        }
        setTabs(newTabs);
        setActiveKey(newActiveKey);
    }

    function handleTabNameChange(e, key, isChange = false) {
        const newTabs = tabs.map((tab) => {
            if (tab.key === key) {
                return {...tab, name: e.target.value, isEditing: isChange};
            }
            return tab;
        });
        setTabs(newTabs);
    }

    function toggleEdit(key, isEditing) {
        const newTabs = tabs.map((tab) => {
            if (tab.key === key) {
                return {...tab, isEditing};
            }
            return tab;
        });
        setTabs(newTabs);
    }

    function handleKeyDown(event) {
        if (event.key === " ") event.stopPropagation();
    }

    function submitForm() {
        const productTabs = form.getFieldsValue();

        const data = Object.keys(productTabs).map(key => ({
                title: tabs.find(item => item.key === key) && tabs.find(item => item.key === key).name,
                tabContents: productTabs[key]
            })
        )
    }

    const items = useMemo(() => {
        return tabs.map((tab) => ({
            label: tab.isEditing ? (
                <Input
                    autoFocus
                    defaultValue={tab.name}
                    onBlur={() => toggleEdit(tab.key, false)}
                    onKeyDown={handleKeyDown}
                    onPressEnter={(e) => {
                        handleTabNameChange(e, tab.key);
                    }}
                />
            ) : (
                <span onDoubleClick={() => toggleEdit(tab.key, true)}>{tab.name}</span>
            ),
            key: tab.key,
            closable: tab.key !== "productCode0",
            children: <TabContent key={tab.key} tabKey={tab.key}/>,
            closeIcon: (
                <Popconfirm
                    title="Bạn có chắc chắn muốn đóng tab này không?"
                    onConfirm={() => removeTab(tab.key)}
                    okText="Có"
                    cancelText="Không"
                    placement="bottomLeft"
                >
                    <CloseOutlined/>
                </Popconfirm>
            ),
        }));
    }, [tabs]);

    return (
        <Form form={form}>
            <Tabs
                items={items}
                type="editable-card"
                onChange={setActiveKey}
                activeKey={activeKey}
                onEdit={(targetKey, action) => {
                    if (action === "add") addTab();
                }}
            />
            <Button
                onClick={() =>
                    form.setFieldsValue({
                        tabContentproductCode0: [
                            {
                                title: "test",
                                content: "new",
                                tabContentId: 1,
                                type: "textarea",
                            },
                        ],
                    })
                }
            >
                Set Field
            </Button>
            <Button onClick={submitForm}>Submit</Button>
        </Form>
    );
}

export default EditableTabs;
