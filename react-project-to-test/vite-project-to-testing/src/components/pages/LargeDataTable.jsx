import React, { useEffect, useState } from "react";
import { Table, Column, Cell } from "fixed-data-table-2";
import "fixed-data-table-2/dist/fixed-data-table.css";
import { set } from "lodash";
import { useRef } from "react";
import { Button } from "antd";
import axios from "axios";
import fileDownload from "js-file-download";

const LargeDataTable = () => {
  const rowCount = 200; // 1 triệu records
  const [collapsedRows, setCollapsedRows] = useState(new Set());
  const [scrollToRow, setScrollToRow] = useState(null);
  const [subRowHeightMap, setSubRowHeightMap] = useState({});
  const [task, setTask] = useState("");
  const [currentProcess, setCurrentProcess] = useState("");
  const [measureColWidth, setMeasureColWidth] = useState(100);
  const [measureColMapWidth, setMeasureColMapWidth] = useState({});
  const [isMeasured, setIsMeasured] = useState(false);
  const [loading, setLoading] = useState(false);
  const contentRef = useRef();

  // Tạo dữ liệu giả
  const data = new Array(rowCount).fill().map((_, index) => ({
    id: index,
    name: `Item ${index}`,
    value: `Value ${index}`,
  }));

  const rowGetter = (index) => data[index];

  function onCollapseClick(rowIndex) {
    const shallowCopyOfCollapsedRows = new Set([...collapsedRows]);
    let scrollToRow = rowIndex;

    if (shallowCopyOfCollapsedRows.has(rowIndex)) {
      shallowCopyOfCollapsedRows.delete(rowIndex);
      scrollToRow = null;
    } else {
      shallowCopyOfCollapsedRows.add(rowIndex);
    }

    setCollapsedRows(shallowCopyOfCollapsedRows);
  }

  function _subRowHeightGetter(index) {
    if (collapsedRows.has(index)) {
      const currentDiv = document.getElementById(`sub-row-${index}`);

      if (currentDiv) return currentDiv.getBoundingClientRect().height;

      return 500;
    }

    return 0;
  }
  const downloadExcel = async () => {
    try {
      const response = await axios.get(
        "http://localhost:8080/spring-boot-demo/demo/download",
        {
          responseType: "blob",
        }
      );
      // const url = window.URL.createObjectURL(new Blob([response.data]));
      // const link = document.createElement('a');
      // link.href = url;
      // link.setAttribute('download', 'downloaded_data.xlsx'); // Tên file để lưu
      // document.body.appendChild(link);
      // link.click();
      // link.parentNode.removeChild(link);
      // window.URL.revokeObjectURL(url); // Giải phóng bộ nhớ
      fileDownload(response.data, "full_data.xlsx");
    } catch (error) {
      console.error("Download error:", error);
    }
  };

  async function startDownloadExcel() {
    const processStatusId = await axios.post(
      "http://localhost:8080/spring-boot-demo/api/excel/start-export",
      { responseType: "json" }
    );

    setTask(processStatusId.data);

  }

  async function checkTaskId() {
    const processStatus = await axios.get(
      `http://localhost:8080/spring-boot-demo/api/excel/export-status/${task}`,
      { responseType: "json" }
    );

    if(processStatus.data.status === "Completed") {
      setCurrentProcess("Completed");
    }
    
  }

  async function downloadNow() {
    console.log("Download now", task);
    const response = await axios.get(
      `http://localhost:8080/spring-boot-demo/api/excel/download/${task}`,
      { responseType: "blob" }
    );

    fileDownload(response.data, "exported_data.xlsx");
  }


  

  function calculateTextLength() {
    const content = contentRef.current;
    if (!content) return;

    const dataLenght = content.textContent.length;

    console.log(dataLenght);
  }

  const exportExcel = async (type) => {
    // fetch(`http://localhost:8080/spring-boot-demo/demo/download-excel-with-${type === "POI" ? "apache-poi" : "fastexcel"}`)
    // .then(response => response.blob())
    // .then(blob => {
    //   const url = window.URL.createObjectURL(blob);
    //   const a = document.createElement('a');
    //   a.href = url;
    //   a.download = `exported_data_${type}.xlsx`;
    //   document.body.appendChild(a);
    //   a.click();
    //   a.remove();
    // })

    // .catch(e => console.error('Error downloading the file:', e));

    axios
      .get(
        `http://localhost:8080/spring-boot-demo/demo/download-excel-with-${
          type === "POI" ? "apache-poi" : "fastexcel"
        }`,
        {
          responseType: "blob",
        }
      )
      .then((response) => {
        // fileDownload(response.data, `exported_data_${type}.xlsx`);
        const url = window.URL.createObjectURL(new Blob([response.data]));
        const link = document.createElement("a");
        link.href = url;
        link.setAttribute("download", `exported_data_${type}.xlsx`);
        document.body.appendChild(link);
        link.click();
        link.parentNode.removeChild(link);
      });
  };

  function exportExcelMultipleThread() {
    axios
      .get(
        "http://localhost:8080/spring-boot-demo/demo/download-excel-with-fastexcel-multithreading-simple",
        {
          responseType: "blob",
        }
      )
      .then((response) => {
        fileDownload(response.data, "exported_data_multiple_thread.xlsx");
      });
  }

  return (
    <div style={{ width: 1000, height: 500 }}>
      <Table
        rowsCount={rowCount}
        width={1000}
        height={500}
        headerHeight={50}
        // scrollToRow={scrollToRow}
        rowHeightGetter={(index) => {
          if (index % 2 === 0) {
            return 100;
          } else {
            return 150;
          }
        }}
        subRowHeightGetter={_subRowHeightGetter}
        rowExpanded={(props) => (
          <ExpandedRow
            key={props.rowIndex}
            {...props}
            collapsedRows={collapsedRows}
          />
        )}
      >
        {/* <Column
        cell={({ rowIndex }) => (
          <button onClick={() => onCollapseClick(rowIndex)}>+</button>
        )}
        fixed={true}
        width={30}
      /> */}

        <Column
          header={<Cell>ID</Cell>}
          cell={({ rowIndex, width, height }) => (
            <Cell height={100}>{rowGetter(rowIndex).id}</Cell>
          )}
          width={100}
        />
        <Column
          header={<Cell>Measure Column</Cell>}
          cell={({ rowIndex, width, height }) => (
            <CellMeasure
              rowIndex={rowIndex}
              measureColWidth={measureColWidth}
              setMeasureColWidth={setMeasureColWidth}
              isMeasured={isMeasured}
              rowGetter={rowGetter}
              data={data}
            />
          )}
          width={measureColWidth}
        />
        <Column
          header={<Cell height={100}>Name</Cell>}
          cell={({ rowIndex }) => (
            <Cell height={100}>{rowGetter(rowIndex).name}</Cell>
          )}
          width={300}
        />
        <Column
          header={<Cell>Value</Cell>}
          cell={({ rowIndex }) => (
            <Cell height={100}>{rowGetter(rowIndex).value}</Cell>
          )}
          width={1000}
        />
      </Table>

      <Button onClick={() => exportExcel("POI")}>
        Export Excel By Apache Poi
      </Button>
      <Button onClick={() => exportExcel("FAST")}>
        Export Excel By FastExcel
      </Button>
      <Button onClick={exportExcelMultipleThread}>
        Export Excel By FastExcel MultiThreading
      </Button>
      <Button onClick={calculateTextLength}>Calculate Text Length</Button>
      <Button onClick={startDownloadExcel}>Start Download Excel</Button>
      <Button onClick={checkTaskId}>Check TaskId</Button>
      <Button onClick={downloadNow}>Download Excel Now</Button>

      <div ref={contentRef} id="content">
        <span>1</span>
        <span>2</span>
        <span>3</span>
        <span>
          4
          <span>
            5<span>6</span>
            <div>7</div>
          </span>
        </span>
      </div>
    </div>
  );
};

export default LargeDataTable;

function ExpandedRow(props) {
  const { rowIndex, width, height, collapsedRows } = props;

  if (!collapsedRows.has(rowIndex)) {
    return null;
  }

  const data = new Array(1000).fill().map((_, index) => ({
    id: index,
    name: `Item ${index}`,
    value: `Value ${index}`,
  }));

  const style = {
    height,
    width: width - 2,
  };
  return (
    <div id={`sub-row-${rowIndex}`}>
      {data.map((el) => {
        return (
          <div
            style={{ padding: 5, background: "aqua", color: "white" }}
            key={el.id}
          >
            {el.value}
          </div>
        );
      })}
    </div>
  );
}

function CellMeasure({
  rowIndex,
  measureColWidth,
  setMeasureColWidth,
  isMeasured,
  rowGetter,
  data,
}) {
  const [contentData, setContentData] = useState(true);
  const contentRef = useRef();

  useEffect(() => {
    if (isMeasured) return;

    setContentData(content);
  }, [isMeasured]);

  function handleMeasure(newWidth) {
    setMeasureColWidth(newWidth);
    setContentData(undefined);
  }

  return (
    <Cell>
      {contentData ? (
        <MeasureComponents
          item={contentData}
          onMeasured={handleMeasure}
          rowIndex={rowIndex}
        />
      ) : null}
      <div ref={contentRef} style={{ whiteSpace: "nowrap" }}>
        Xin chào các bạn + {rowIndex === 99 ? "Super long test test" : rowIndex}
      </div>
    </Cell>
  );
}

const MeasureComponents = ({ item, onMeasured, rowIndex }) => {
  const cellRef = useRef();

  useEffect(() => {
    const measurements = cellRef.current ? cellRef.current.offsetWidth : 100;
    onMeasured(measurements);
  }, [onMeasured]);

  return (
    <div style={{ visibility: "visible" }}>
      {" "}
      {/* Ẩn các components để không hiển thị trên UI */}
      <div
        style={{ display: "inline-block", padding: "3px 10px" }}
        ref={cellRef}
        key={rowIndex}
      >
        {item}
      </div>
    </div>
  );
};
