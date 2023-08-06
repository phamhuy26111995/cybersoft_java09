import { Divider } from "antd";
import React, { useState } from "react";
import { useEffect } from "react";

export default function ChildOneComponent({ parentState, setParentState, getInfoFromChildOne }) {
  const [childOneState, setChildOneState] = useState("");

    useEffect(() => {
        getInfoFromChildOne(`Child 1 info : ${childOneState}`)
    },[childOneState])

  return (
    <div className="child-one">
      <div style={{ color: "red" }}>{childOneState}</div>
      <Divider />
      <div style={{ color: "red" }}>{parentState}</div>
      <Divider />
      <button onClick={() => setChildOneState("State 1 change")}>
        Change Local State 1
      </button>
      <Divider />
      <button onClick={() => setParentState("Child One Change Parent State")}>
        Change Parent State
      </button>
    </div>
  );
}
