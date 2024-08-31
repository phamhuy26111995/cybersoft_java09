import React from "react";
import { useDispatch, useSelector } from "react-redux";
import styled from "styled-components";
import { setStateB } from "../../redux-slices/componentB-slice";
import { selectStateAInSliceA, selectStateBInASlice, setStateA, setStateBInASlice } from "../../redux-slices/componentA-slice";

const StyledContainer = styled.div`
background: ${(props) => props.$background};
color: ${(props) => props.$color};
`;

function ReduxOptimize() {
  return (
    <>
      <ComponentA />
      <ComponentB />
    </>
  );
}

function ComponentA() {
//   const { stateA } = useSelector((state) => state.ASlice);
  const stateA = useSelector((state) => selectStateAInSliceA(state));

  
  const dispatch = useDispatch();
  
  console.log('component A render', stateA)

  return (
    <StyledContainer $background="blue" $color="yellow">
      <h1>{stateA}</h1>
      <button onClick={() => dispatch(setStateA("State A Change"))}>Change State A</button>
    </StyledContainer>
  );
}

function ComponentB() {
  const { stateB } = useSelector((state) => state.BSlice);
//   const { stateBInASlice } = useSelector((state) => state.ASlice);
  const { stateBInASlice } = useSelector((state) => selectStateBInASlice(state));

  const dispatch = useDispatch();


  console.log('component B render')

  return (
    <StyledContainer $background="purple" $color="white">
      <h1>{stateB}</h1>
      <h1>{stateBInASlice}</h1>
      <button onClick={() => dispatch(setStateB("State B Change"))}>Change State B</button>
      <button onClick={() => dispatch(setStateBInASlice("State B In A Slice Change"))}>Change State B In A Slice</button>

    </StyledContainer>
  );
}

export default ReduxOptimize;
