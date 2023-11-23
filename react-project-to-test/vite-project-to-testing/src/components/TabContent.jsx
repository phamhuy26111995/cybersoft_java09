import React, {useState} from "react";
import {Form, Input, Select} from "antd";
import {CloseOutlined} from "@ant-design/icons";

const {Option} = Select;
const {TextArea} = Input;
function TabContent(props) {
    const {subField, subOpt} = props;
    const [inputType, setInputType] = useState("input");

    function onChangeSelect(value) {
        setInputType(value)
    }

    return (
        <>
            <Form.Item noStyle name={[subField.name, 'label']}>
                <Input placeholder="first" />
            </Form.Item>
            <Form.Item noStyle name={[subField.name, 'second']}>
                {inputType === "input" ? <Input placeholder="second" />
                      : <TextArea />}
            </Form.Item>
            <Form.Item noStyle name={[subField.name, 'type']}>
                <Select defaultValue={inputType} onChange={onChangeSelect}>
                    <Option value={"input"}>
                        Input
                    </Option>
                    <Option value={"textarea"}>
                        TextArea
                    </Option>
                </Select>
            </Form.Item>
            <CloseOutlined
                onClick={() => {
                    subOpt.remove(subField.name);
                }}
            />
        </>
    )
}

export default TabContent;