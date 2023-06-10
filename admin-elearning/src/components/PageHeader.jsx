import React from "react";
import { ArrowLeftOutlined } from "@ant-design/icons";
import CreateButton from "./buttons/CreateButton";
import { useNavigate } from "react-router-dom";

import { PAGE_URL } from "../consts/path";

function PageHeader(props) {
  const navigate = useNavigate();
  return (
    <div className="page-header">
      <div>
        {props.backUrl && (
          <ArrowLeftOutlined
            onClick={() => navigate(props.backUrl)}
            style={{ fontSize: "15px" }}
          />
        )}
      </div>
      <div>
        <h1>{props.title}</h1>
      </div>
      {props.extra && (
        <div style={{ position: "absolute", right: "22px" }}>{props.extra}</div>
      )}
    </div>
  );
}

export default PageHeader;
