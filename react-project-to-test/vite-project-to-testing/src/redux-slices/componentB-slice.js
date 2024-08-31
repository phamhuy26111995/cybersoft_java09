import { createSlice, createAsyncThunk } from "@reduxjs/toolkit";

const initialState = {
  stateB: "State B",
};

export const componentBSlice = createSlice({
  name: "componentB",
  initialState,
  reducers: {
    setStateB(state, { payload }) {
      state.stateB =  payload ;
    },
  },
});

export const { setStateB } = componentBSlice.actions;

export default componentBSlice.reducer;
