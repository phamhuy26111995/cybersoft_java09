import { Divider } from "antd";
import React, { useEffect, useState } from "react";

export default function ChildTwoComponent({ parentState, setParentState, getInfoFromChildTwo }) {
  const [childTwoState, setChildTwoState] = useState("");
  useEffect(() => {
    getInfoFromChildTwo(`Child 2 info : ${childTwoState}`);
  }, [childTwoState]);
  return (
    <div className="child-two">
      <div style={{ color: "blue" }}>{childTwoState}</div>
      <Divider />
      <div style={{ color: "blue" }}>{parentState}</div>
      <Divider />
      <button onClick={() => setChildTwoState("State 2 change")}>
        Change Local State 2
      </button>
      <Divider />
      <button onClick={() => setParentState("Child Two Change Parent State")}>
        Change Parent State
      </button>
    </div>
  );
}
