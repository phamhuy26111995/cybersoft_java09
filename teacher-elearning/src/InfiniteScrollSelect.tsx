import React, { useState } from "react";
import { Select } from "antd";

const { Option } = Select;

const InfiniteScrollSelect = () => {
  const [options, setOptions] = useState(Array.from({ length: 10 }, (_, i) => i));

  const handlePopupScroll = (e : any) => {
    const { scrollTop, scrollHeight, clientHeight } = e.target;
    if (scrollTop + clientHeight === scrollHeight) {
      // call API to load more data
      console.log('call api')
      const newOptions = Array.from({ length: 10 }, (_, i) => i + options.length);
      setOptions([...options, ...newOptions]);
    }
  };

  return (
    <Select width={100} onPopupScroll={handlePopupScroll}>
      {options.map((option) => (
        <Option  key={option} value={option}>
          Option {option}
        </Option>
      ))}
    </Select>
  );
};

export default InfiniteScrollSelect;