import React from "react";
import { Button } from "antd";
import { PlusCircleOutlined  } from "@ant-design/icons";
function CreateButton(props) {
  const { handleEvent } = props;

  return (
    <div style={{ display: "flex" }}>
      <Button
        onClick={handleEvent}
        style={{ backgroundColor: "gray", color: "white" }}
      >
        Thêm mới
        <PlusCircleOutlined />
      </Button>
    </div>
  );
}

export default CreateButton;
