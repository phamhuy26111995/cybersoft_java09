import React, { useState } from "react";
import ChildOneComponent from "./ChildOneComponent";
import ChildThreeComponent from "./ChildThreeComponent";
import ChildTwoComponent from "./ChildTwoComponent";
import "../styles/_example.scss";
import { Divider } from "antd";
import DateRangePickerComponent from "./DateRangePickerComponent";

function HomePage() {

    function onChange(data) {
      console.log(data);
    }
  
    return (
      <React.Fragment>
        <DateRangePickerComponent onChange={onChange} />
      </React.Fragment>
    )
}

export default HomePage;
