import { DatePicker } from "antd";
import React, { useState } from "react";
import moment from "moment";

const DateRangePickerComponent = () => {
    const currentDate = moment();
    const [fromDate, setFromDate] = useState(currentDate);
    const [toDate, setToDate] = useState(currentDate);
  
    const handleFromDateChange = (date) => {
      if (date.isAfter(toDate)) {
        setFromDate(toDate);
      } else {
        setFromDate(date);
      }
    };
  
    const handleToDateChange = (date) => {
      if (date.isBefore(fromDate)) {
        setFromDate(date.subtract(1, 'months'));
      }
      setToDate(date);
    };
  
    const disabledDate = (current) =>
      current.isAfter(currentDate) || current.isBefore(currentDate.subtract(3, 'years'));
  
    return (
      <div>
        <DatePicker
          value={fromDate}
          onChange={handleFromDateChange}
          disabledDate={disabledDate}
        />
        <DatePicker
          value={toDate}
          onChange={handleToDateChange}
          disabledDate={disabledDate}
        />
      </div>
    );
  };
  
  export default DateRangePickerComponent;