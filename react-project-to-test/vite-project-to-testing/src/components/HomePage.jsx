import React, { useState } from "react";
import ChildOneComponent from "./ChildOneComponent";
import ChildThreeComponent from "./ChildThreeComponent";
import ChildTwoComponent from "./ChildTwoComponent";
import "../styles/_example.scss";
import { Divider } from "antd";

function HomePage() {
  const [parentState, setParentState] = useState("Parent State Default");

  function getInfoFromChildOne(childState) {
    setParentState(childState)
  }
  function getInfoFromChildTwo(childState) {
    setParentState(childState)
  }
  function getInfoFromChildThree(childState) {
    setParentState(childState)
  }

  return (
    <React.Fragment>
      <div className="parent">
        <h1>{`Parent get information from child ${parentState}`}</h1>
        <ChildOneComponent
          parentState={parentState}
          setParentState={setParentState}
          getInfoFromChildOne={getInfoFromChildOne}
        />
        <Divider />
        <ChildTwoComponent
          parentState={parentState}
          setParentState={setParentState}
          getInfoFromChildTwo={getInfoFromChildTwo}
        />
        <Divider />
        <ChildThreeComponent
          parentState={parentState}
          setParentState={setParentState}
          getInfoFromChildThree={getInfoFromChildThree}
        />
      </div>
    </React.Fragment>
  );
}

export default HomePage;
