import React from "react";
import useToggle from "../hooks/useToggle";


function ToggleComponent() {

    const [value , toggleValue] = useToggle(false);

    console.log(value)

    return (
        <React.Fragment>
            <div>{value.toString()}</div>
            <button onClick={toggleValue}>Toggle</button>
        </React.Fragment>
    )
}

export default ToggleComponent;