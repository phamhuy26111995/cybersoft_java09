import React, { useState, useEffect, useRef } from 'react';
import { Cell } from 'fixed-data-table-2';

const AutoResizingCell = ({ rowIndex, columnKey, data, cellRenderer, ...props }) => {
  const [cellHeight, setCellHeight] = useState(30); // Default height
  const measureRef = useRef(null);

  useEffect(() => {
    if (measureRef.current) {
      const height = measureRef.current.offsetHeight;
      setCellHeight(height + 10); // Add some padding
    }
  }, [data, rowIndex, columnKey]);

  const renderContent = () => {
    if (cellRenderer) {
      return cellRenderer({ rowIndex, columnKey, data });
    }
    return data[rowIndex][columnKey];
  };

  return (
    <Cell {...props} height={cellHeight}>
      <div style={{ height: cellHeight, overflow: 'hidden' }}>
        {renderContent()}
      </div>
      <div
        ref={measureRef}
        style={{
          position: 'absolute',
          visibility: 'hidden',
          height: 'auto',
          width: props.width
        }}
      >
        {renderContent()}
      </div>
    </Cell>
  );
};

export default AutoResizingCell;