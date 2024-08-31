import { createSlice, createAsyncThunk, createSelector } from "@reduxjs/toolkit";

const initialState = {
  stateA: "State A",
  stateBInASlice : "State B In A Slice"
};

export const BStateInASlice = state => state.ASlice.stateBInASlice;

export const stateASelect = state => state.ASlice.stateA;

export const selectStateBInASlice = createSelector(
  [state => state.ASlice.stateBInASlice],(stateBInASlice) => stateBInASlice
)

export const selectStateAInSliceA = createSelector(
  [state => state.ASlice.stateA],(stateA) => stateA
)

export const componentASlice = createSlice({
  name: "componentA",
  initialState,
  reducers: {
    setStateA(state, { payload }) {
      state.stateA =  payload ;
    },
    setStateBInASlice(state, { payload }) {
      state.stateBInASlice =  payload ;
    },
  },
});

export const { setStateA,setStateBInASlice } = componentASlice.actions;

export default componentASlice.reducer;
