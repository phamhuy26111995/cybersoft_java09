import { createSlice, createAsyncThunk  } from "@reduxjs/toolkit";
import categoryApi from "../api/category/category_api";

const initialState = {
  categories: [],
  categoryDetail : undefined,
};

export const fetchCategories = createAsyncThunk(
  "category/fetchCategories",
  async () => {
    const data = await categoryApi.getAll();
    return data;
  }
);


export const categorySlice = createSlice({
  name: "category",
  initialState,
  reducers: {
    getAll:   (state, action) => {
      state.categories = action.payload.map(el => {
        el.key = el.id;
        return el;
      });
    },

    detail : (state, action) => {
      state.categoryDetail = action.payload;
      state.categoryDetail.fileFromServer = {
        uid: '-1',
        name : `${state.categoryDetail.icon}`,
        status : 'done',
        url : `${state.categoryDetail.icon}`,
        thumbUrl: `${state.categoryDetail.icon}`
      }
    },
    clearState : (state, action) => {
      return initialState;
    }
  },
});

export const { getAll, detail, clearState } = categorySlice.actions;

export default categorySlice.reducer;


