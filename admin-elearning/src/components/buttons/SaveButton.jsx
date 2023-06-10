import React from "react";
import { Button } from "antd";
import { SaveOutlined } from "@ant-design/icons";
function SaveButton(props) {
  const { handleEvent } = props;

  return (
    <div style={{ display: "flex" }}>
      <Button
        onClick={handleEvent}
        style={{ backgroundColor: "gray", color: "white" }}
      >
        Lưu
        <SaveOutlined />
      </Button>
    </div>
  );
}

export default SaveButton;
