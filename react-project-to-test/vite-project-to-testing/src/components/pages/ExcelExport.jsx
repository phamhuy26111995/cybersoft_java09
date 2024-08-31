import { Button, Spin } from "antd";
import axios from "axios";
import fileDownload from "js-file-download";
import React, { useState, useEffect, useRef } from "react";

const ExcelExport = () => {
  const [taskId, setTaskId] = useState("");
  const [currentStatus, setCurrentStatus] = useState("Not Started");
  const checkStatusInterval = useRef(null);

  // create interval to check the status of the task
  useEffect(() => {
    if (!taskId) return;

    checkStatusInterval.current = setInterval(() => {
      checkTaskId();
    }, 5000);

    return () => {
      if (checkStatusInterval.current) {
        clearInterval(checkStatusInterval.current);
      }
    };
  }, [taskId]);

  // create interval to check the status of the task

  async function startDownloadExcel() {
    // Random user id
    const processStatusId = await axios.get(
      "http://localhost:8080/spring-boot-demo/api/excel/start-export/123",
      { responseType: "json" }
    );

    if(processStatusId.data) {
      setTaskId(processStatusId.data);
    }
  }

  async function checkTaskId() {
    try {
      const processStatus = await axios.get(
        `http://localhost:8080/spring-boot-demo/api/excel/export-status/${taskId}`,
        { responseType: "json" }
      );
  
      if (processStatus.data.status === "Completed") {
        downloadNow();
      }

      setCurrentStatus(processStatus.data.status);
    } catch (error) {
          
    setCurrentStatus("");
    clearInterval(checkStatusInterval.current);
    setTaskId("");
    }
    
  }

  async function downloadNow() {
    try {
        const response = await axios.get(
            `http://localhost:8080/spring-boot-demo/api/excel/download/${taskId}`,
            { responseType: "blob" }
          );

          fileDownload(response.data, "exported_data.xlsx");

    } catch (error) {
      console.log(error);
    }

    
    setCurrentStatus("");
    clearInterval(checkStatusInterval.current);
    setTaskId("");
  }

  return (
    <div>
      <Button disabled={taskId} type="primary" onClick={startDownloadExcel}>
        Export to Excel
        <Spin spinning={currentStatus === "In Progress"} />
      </Button>

      <h1>{currentStatus}</h1>
    </div>
  );
};

export default ExcelExport;
