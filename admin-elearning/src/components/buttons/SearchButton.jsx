import React from "react";
import { Button } from "antd";
import { SearchOutlined } from "@ant-design/icons";
function SearchButton(props) {
  const { handleEvent } = props;

  return (
    <div style={{ display: "flex" }}>
      <Button
        onClick={handleEvent}
        style={{ backgroundColor: "gray", color: "white" }}
      >
        Tìm kiếm
        <SearchOutlined />
      </Button>
    </div>
  );
}

export default SearchButton;
