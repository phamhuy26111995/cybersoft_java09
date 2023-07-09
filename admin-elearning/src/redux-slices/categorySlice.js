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


export const exportExcel = createAsyncThunk(
  "category/exportExcel",
  async () => {
    const blob = await categoryApi.exportExcel();
    const excelUrl = URL.createObjectURL(new Blob([blob], {type: 'application/vnd.ms-excel'}));

    // Biểu thức dưới đây không thể sử dụng trong addCase
    const downloadLink = document.createElement('a');
    downloadLink.href = excelUrl;
    downloadLink.download = 'category.xlsx';
    downloadLink.textContent = 'Download Excel File';
    
    downloadLink.click();
    URL.revokeObjectURL(excelUrl);
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


