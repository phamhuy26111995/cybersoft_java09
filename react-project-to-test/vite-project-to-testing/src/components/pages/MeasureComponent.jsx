import React, { useState, useEffect, useRef } from "react";
import styled from "styled-components";
const MeasureComponents = ({
  items,
  rowData,
  rowIndex,
  cellMap,
  heightMap,
  widthMap,
  isLastElement,
  setIsCalculateContent
}) => {
  const refs = useRef(items.map(() => React.createRef()));

  useEffect(() => {
    refs.current.forEach((ref, index) => {
      let width = 0;
      let height = 0;

      if (ref.current) {
        width = ref.current.offsetWidth;
        height = ref.current.offsetHeight;
      }

      if (!heightMap.current.has(rowIndex)) {
        heightMap.current.set(rowIndex, height);
      } else {
        const currentMaxHeight = heightMap.current.get(rowIndex);
        if (height > currentMaxHeight) {
          heightMap.current.set(rowIndex, height);
        }
      }

      if (!widthMap.current.has(items[index])) {
        widthMap.current.set(items[index], width);
      } else {
        const currentMaxWidth = widthMap.current.get(items[index]);

        if (width > currentMaxWidth) {
          widthMap.current.set(items[index], width);
        }
      }

      if(index === items.length - 1 && isLastElement) {
        setIsCalculateContent(false);
      }
    });

    
  }, [items]);

  return (
    <div>
      {items.map((item, index) => (
        <div
          style={{
            display: "inline-block",
            padding: "3px 10px",
            whiteSpace: "nowrap",
            border: "1px solid aqua",
          }}
          ref={refs.current[index]}
          key={index}
        >
          {cellMap(rowData)[item]}
        </div>
      ))}
    </div>
  );
};

const MeasurePage = ({ dataList, cellMap, setIsCalculateContent ,heightMap , widthMap}) => {


  return (
    <div>
      {dataList.map((el, index) => {
        return (
          <MeasureComponents
            rowData={el}
            rowIndex={index}
            isLastElement={index === dataList.length - 1}
            setIsCalculateContent={setIsCalculateContent}
            key={index}
            cellMap={cellMap}
            items={Object.keys(el)}
            heightMap={heightMap}
            widthMap={widthMap}
          />
        );
      })}
    </div>
  );
};

export default MeasurePage;
