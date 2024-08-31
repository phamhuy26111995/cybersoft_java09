import { Divider } from "antd";
import React, { useEffect, useState } from "react";

export default function ChildThreeComponent({ parentState, setParentState, getInfoFromChildThree }) {
  const [childThreeState, setChildThreeState] = useState("");

  useEffect(() => {
    getInfoFromChildThree(`Child 3 info : ${childThreeState}`);
  }, [childThreeState]);
  return (
    <div className="child-three">
      <div style={{ color: "yellow" }}>{childThreeState}</div>
      <Divider />
      <div style={{ color: "yellow" }}>{parentState}</div>
      <Divider />
      <button onClick={() => setChildThreeState("State 3 change")}>
        Change Local State 3
      </button>
      <Divider />
      <button onClick={() => setParentState("Child Three Change Parent State")}>
        Change Parent State
      </button>
    </div>
  );
}
