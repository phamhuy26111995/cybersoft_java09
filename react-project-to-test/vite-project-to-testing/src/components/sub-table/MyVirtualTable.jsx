import React, { useMemo, useRef, useState } from 'react';
import { VariableSizeGrid as Grid } from 'react-window';

const COLUMN_WIDTH = 100;
const ROW_HEIGHT = 35;
const EXTRA_HEIGHT = 100; // Chiều cao thêm khi hàng được mở rộng
const HEADER_HEIGHT = 35;
const COLUMN_COUNT = 5;
const ROW_COUNT = 10000;

// Tạo dữ liệu mẫu
const generateData = (rowCount, columnCount) => {
  return Array.from({ length: rowCount }, (_, rowIndex) =>
    Array.from({ length: columnCount }, (_, columnIndex) => `Hàng ${rowIndex}, Cột ${columnIndex}`)
  );
};

const Header = ({ columns, scrollLeft }) => (
  <div style={{ overflow: 'hidden', width: COLUMN_COUNT * COLUMN_WIDTH }}>
    <div style={{ display: 'flex', transform: `translateX(-${scrollLeft}px)` }}>
      {columns.map((column, index) => (
        <div key={index} style={{ width: COLUMN_WIDTH, height: HEADER_HEIGHT, border: '1px solid black' }}>
          {column}
        </div>
      ))}
    </div>
  </div>
);

const MyVirtualTable = () => {
  const data = useMemo(() => generateData(ROW_COUNT, COLUMN_COUNT), []);
  const [expandedRows, setExpandedRows] = useState(new Set());
  const [scrollLeft, setScrollLeft] = useState(0);
  const gridRef = useRef();

  const getRowHeight = index => (expandedRows.has(index) ? ROW_HEIGHT + EXTRA_HEIGHT : ROW_HEIGHT);

  const toggleRowExpansion = rowIndex => {
    setExpandedRows(prevExpandedRows => {
      const newExpandedRows = new Set(prevExpandedRows);
      if (newExpandedRows.has(rowIndex)) {
        newExpandedRows.delete(rowIndex);
      } else {
        newExpandedRows.add(rowIndex);
      }
    //   gridRef.current.resetAfterIndex(rowIndex);
      return newExpandedRows;
    });
  };

  const Cell = ({ columnIndex, rowIndex, style }) => (
    <div style={{ ...style, display: 'flex', alignItems: 'center', justifyContent: 'center', border: '1px solid black' }}>
      {columnIndex === 0 && (
        <button onClick={() => toggleRowExpansion(rowIndex)}>
          {expandedRows.has(rowIndex) ? 'Thu gọn' : 'Mở rộng'}
        </button>
      )}
      <span>{data[rowIndex][columnIndex]}</span>
      {expandedRows.has(rowIndex) && columnIndex === 0 && (
        <div style={{ position: 'absolute', top: ROW_HEIGHT, left: 0, right: 0, height: EXTRA_HEIGHT, backgroundColor: '#eee' }}>
          {/* Thêm nội dung của hàng mở rộng ở đây */}
          Nội dung mở rộng
        </div>
      )}
    </div>
  );

  return (
    <div>
      <Header columns={Array.from({ length: COLUMN_COUNT }, (_, index) => `Header ${index}`)} scrollLeft={scrollLeft} />
      <Grid
        ref={gridRef}
        columnCount={COLUMN_COUNT}
        columnWidth={index => COLUMN_WIDTH}
        height={300}
        rowCount={ROW_COUNT}
        rowHeight={getRowHeight}
        width={COLUMN_COUNT * COLUMN_WIDTH}
        onScroll={({ scrollLeft }) => setScrollLeft(scrollLeft)}
      >
        {Cell}
      </Grid>
    </div>
  );
};

export default MyVirtualTable;
