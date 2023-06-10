import { Alert, Spin } from "antd";
import React from "react";

function Loading() {
  return (
    <React.Fragment>
      <div className="loading">
        <Spin tip="Loading..."></Spin>
      </div>
    </React.Fragment>
  );
}

export default Loading;
